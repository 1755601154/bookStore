package com.example.bookStore.database.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author yuanlei
 * @description 动态切换数据
 * @date 2020-11-24
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    /**
     * 抽象类AbstractRoutingDataSource中的resolvedDataSources存储了最终解析后的数据源
     * 由于没有提供get的方法，所以此处自己维护一个Map保存数据源，如果后续业务需要获取数据源可以从此处获取
     */
    private static Map<Object, Object> targetDataSources;

    /**
     * 保存当前线程使用了事务的数据库连接(connection)
     * 当我们自己管理事务的时候即可从此处获取到当前线程使用了哪些连接从而让这些被使用的连接commit/rollback/close
     */
    private ThreadLocal<Map<String, Connection>> connectionThreadLocal = new ThreadLocal<>();

    public MultipleDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        this.targetDataSources=targetDataSources;
        // afterPropertiesSet()方法调用时用来将targetDataSources的属性写入resolvedDataSources中的
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return MultipleDataSourceContextHolder.getDataSourceType();
    }

    public DataSource getDataSource(String key) {
        return (DataSource) targetDataSources.get(key);
    }

    /**
     * mybatis在使用mapper接口执行sql的时候会从该方法获取connection执行sql
     * 如果事务是spring或者mybatis在管理，那么直接返回原生的connection
     * 如果是我们自己控制事务，则返回我们自己实现的ConnetWarp
     *
     * @return Connection
     * @throws SQLException SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        Map<String, Connection> stringConnectionMap = connectionThreadLocal.get();
        if (stringConnectionMap == null) {
            // 没开事物 直接返回
            return determineTargetDataSource().getConnection();
        } else {
            // 开了事物 从当前线程中拿 而且拿到的是 包装过的connect 只有手动去提交和关闭连接
            String currentName = (String) determineCurrentLookupKey();
            return stringConnectionMap.get(currentName);
        }
    }
    /**
     * 开启事物的时候,把连接放入 线程中,后续crud 都会拿对应的连接操作
     *
     * @param key        子公司code
     * @param connection 连接
     */
    public void bindConnection(String key, Connection connection) {
        Map<String, Connection> connectionMap = connectionThreadLocal.get();
        if (connectionMap == null) {
            connectionThreadLocal.set(connectionMap);
        }
        connectionMap.put(key, connection);
    }

    /**
     * 提交事物
     *
     * @throws SQLException SQLException
     */
    public void doCommit() throws SQLException {
        Map<String, Connection> stringConnectionMap = connectionThreadLocal.get();
        if (stringConnectionMap == null) {
            return;
        }
        for (String dataSourceName : stringConnectionMap.keySet()) {
            Connection connection = stringConnectionMap.get(dataSourceName);
            connection.commit();
            connection.close();
        }
        removeConnectionThreadLocal();
    }

    /**
     * 回滚事物
     *
     * @throws SQLException SQLException
     */
    public void rollback() throws SQLException {
        Map<String, Connection> stringConnectionMap = connectionThreadLocal.get();
        if (stringConnectionMap == null) {
            return;
        }
        for (String dataSourceName : stringConnectionMap.keySet()) {
            Connection connection = stringConnectionMap.get(dataSourceName);
            connection.rollback();
            connection.close();
        }
        removeConnectionThreadLocal();
    }

    protected void removeConnectionThreadLocal() {
        connectionThreadLocal.remove();
    }
}

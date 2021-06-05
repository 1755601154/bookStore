package com.example.bookStore.database.util;

import com.example.bookStore.database.datasource.MultipleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Collection;

/**
 * @author yuanlei
 * @description
 * @date 2020-11-28
 */
@Component
@Scope("prototype")
public class TransactionUtils {

    @Autowired
    private MultipleDataSource multiRouteDataSource;

    /**
     * @param codes           用到的数据源的key
     * @param transactionType 事务隔离级别
     * @param executor        执行器
     */
    public void execute(Collection<String> codes, int transactionType, Executor executor) throws Exception {
        for (String code : codes) {
            DataSource dataSource = multiRouteDataSource.getDataSource(code);
            if (dataSource == null) {
                continue;
            }
            Connection connection = dataSource.getConnection();
            if (connection != null) {
                // 设置事务隔离级别
                connection.setTransactionIsolation(transactionType == 0 ? Connection.TRANSACTION_NONE : transactionType);
                if (connection.getAutoCommit()) {
                    connection.setAutoCommit(false);
                }
            }

            // 将连接绑定到当前线程
            multiRouteDataSource.bindConnection(code, connection);
        }

        try {
            executor.invoke();
            multiRouteDataSource.doCommit();
        } catch (Exception e) {
            multiRouteDataSource.rollback();
            throw e;
        } finally {

        }
    }

    public void execute(Collection<String> codes, Executor executor) throws Exception {
        // 默认的事务隔离就别
        execute(codes, TransactionDefinition.ISOLATION_DEFAULT, executor);
    }

    public interface Executor {
        void invoke();
    }
}

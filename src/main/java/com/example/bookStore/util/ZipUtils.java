package com.example.bookStore.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author yuanlei
 * @description
 * @date 2021-02-27
 */
@Slf4j
public class ZipUtils {

    /**
     * @Author AlphaJunS
     * @Date 11:32 2020/3/8
     * @Description
     * @param filename 压缩目的地址
     * @param srcFiles 压缩的源文件
     * @return void
     */
    public static void zipFile( String filename , File[] srcFiles ) {
        try {
            if( filename.endsWith(".zip") || filename.endsWith(".ZIP") ){
                FileOutputStream fos = new FileOutputStream(new File(filename));
                ZipOutputStream _zipOut = new ZipOutputStream(fos) ;
                for( File _f : srcFiles ){
                    handlerFile(filename , _zipOut , _f , "");
                }
                _zipOut.close();
                fos.close();
            }else{
                log.error("target file[" + filename + "] is not .zip type file");
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }

    public static void zipFileByHttp(String filename , File[] srcFiles , HttpServletResponse response) {
        try {
            if( filename.endsWith(".zip") || filename.endsWith(".ZIP") ){
                response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
                response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "utf-8"));
                response.flushBuffer();
                ZipOutputStream _zipOut = new ZipOutputStream(response.getOutputStream()) ;
                for( File _f : srcFiles ){
                    handlerFile(filename , _zipOut , _f , "");
                }
                _zipOut.close();
                response.getOutputStream().close();
            }else{
                log.error("target file[" + filename + "] is not .zip type file");
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }
    /**
     * @Author AlphaJunS
     * @Date 11:33 2020/3/8
     * @Description
     * @param zip 压缩的目的地址
     * @param zipOut
     * @param srcFile 被压缩的文件信息
     * @param path 在zip中的相对路径
     * @return void
     */
    private static void handlerFile(String zip , ZipOutputStream zipOut , File srcFile , String path) throws IOException {
        log.info(" begin to compression file[" + srcFile.getName() + "]");
        if( !"".equals(path) && ! path.endsWith(File.separator)){
            path += File.separator ;
        }
        if( ! srcFile.getPath().equals(zip) ){
            if( srcFile.isDirectory() ){
                File[] _files = srcFile.listFiles() ;
                if( _files.length == 0 ){
                    zipOut.putNextEntry(new ZipEntry( path + srcFile.getName() + File.separator));
                    zipOut.closeEntry();
                }else{
                    for( File _f : _files ){
                        handlerFile( zip ,zipOut , _f , path + srcFile.getName() );
                    }
                }
            }else{
                InputStream _in = new FileInputStream(srcFile) ;
                zipOut.putNextEntry(new ZipEntry(path + srcFile.getName()));
                int len = 0 ;
                byte[] _byte = new byte[1024];
                while( (len = _in.read(_byte)) > 0  ){
                    zipOut.write(_byte, 0, len);
                }
                _in.close();
                zipOut.closeEntry();
            }
        }
    }

    /**
     * @Author AlphaJunS
     * @Date 11:34 2020/3/8
     * @Description 解压缩ZIP文件，将ZIP文件里的内容解压到targetDIR目录下
     * @param zipPath 待解压缩的ZIP文件名
     * @param descDir 目标目录
     * @return java.util.List<java.io.File>
     */
    public static List<File> unzipFile(String zipPath, String descDir) {
        return unzipFile(new File(zipPath) , descDir) ;
    }

    /**
     * @Author AlphaJunS
     * @Date 11:36 2020/3/8
     * @Description 对.zip文件进行解压缩
     * @param zipFile 解压缩文件
     * @param descDir 压缩的目标地址，如：D:\\测试 或 /mnt/d/测试
     * @return java.util.List<java.io.File>
     */
    @SuppressWarnings("rawtypes")
    public static List<File> unzipFile(File zipFile, String descDir) {
        List<File> _list = new ArrayList<File>() ;
        try {
            ZipFile _zipFile = new ZipFile(zipFile) ;
            for(Enumeration entries = _zipFile.entries(); entries.hasMoreElements() ; ){
                ZipEntry entry = (ZipEntry)entries.nextElement() ;
                File _file = new File(descDir + File.separator + entry.getName()) ;
                if( entry.isDirectory() ){
                    _file.mkdirs() ;
                }else{
                    File _parent = _file.getParentFile() ;
                    if( !_parent.exists() ){
                        _parent.mkdirs() ;
                    }
                    InputStream _in = _zipFile.getInputStream(entry);
                    OutputStream _out = new FileOutputStream(_file) ;
                    int len = 0 ;
                    byte[] _byte = new byte[1024];
                    while( (len = _in.read(_byte)) > 0){
                        _out.write(_byte, 0, len);
                    }
                    _in.close();
                    _out.flush();
                    _out.close();
                    _list.add(_file) ;
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return _list ;
    }

    /**
     * @Author AlphaJunS
     * @Date 11:36 2020/3/8
     * @Description 对临时生成的文件夹和文件夹下的文件进行删除
     * @param delpath
     * @return void
     */
    public static void deletefile(String delpath) {
        try {
            File file = new File(delpath);
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] fileList = file.list();
                for (int i = 0; i < fileList.length; i++) {
                    File delfile = new File(delpath + File.separator + fileList[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                    } else if (delfile.isDirectory()) {
                        deletefile(delpath + File.separator + fileList[i]);
                    }
                }
                file.delete();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}

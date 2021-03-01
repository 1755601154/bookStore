package com.example.bookStore;

import com.example.bookStore.util.ZipUtils;

import java.io.File;


/**
 * @author yuanlei
 * @description
 * @date 2021-03-01
 */
public class BookStoreApplicationTest {
    public static void main(String[] args){
        File file = new File("README.md");
        File[] files = new File[1];
        files[0] = file;
        ZipUtils.zipFile("README.md.zip",files);
    }
}

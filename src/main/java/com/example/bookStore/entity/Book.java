package com.example.bookStore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanlei
 * @date 2020-11-18
 */
@Data
public class Book implements Serializable {
    private static final long serialVersionUID = -91969758749726312L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private Double price;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
}

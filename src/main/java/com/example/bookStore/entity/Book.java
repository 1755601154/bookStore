package com.example.bookStore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.bookStore.annotation.ExcelColumn;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @ExcelColumn(value = "id", col = 1)
    private Integer id;
    @ExcelColumn(value = "名称", col = 2)
    private String title;
    @ExcelColumn(value = "价格", col = 3)
    private Double price;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ExcelColumn(value = "发布日期", col = 4)
    private Date publishDate;
}

package com.yc.piclib.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;


@Data      //  lombok 注解，节省getter, setter
@Table(name = "tbl_user")
public class User {
    @Id  // JPA注解,指定此属性为表中的主键
    private Integer uid;   //注意: 对应的数据表中的字段名叫 book_id
    private String uname;
    private String upass;
    private String head;
    private Timestamp regtime;
    private Integer gender;


}

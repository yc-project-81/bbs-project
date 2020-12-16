package com.yc.piclib.domain;

import lombok.Data;

import java.sql.Timestamp;

/*
 用于页面呈现的内容
 */
@Data
public class UserDomain extends PageDomain {
    private Integer uid;   //注意: 对应的数据表中的字段名叫 book_id
    private String uname;
    private String upass;
    private String head;
    private Timestamp regtime;
    private Integer gender;


    /**
     * 一张图片的其它属于，通过程序获得.
     */
    private Long fileSize;
    private String extension;
    private String realPath;

    public UserDomain(Integer uid, String uname, String upass, String head, Timestamp regtime, Integer gender) {
        this.uid = uid;
        this.uname = uname;
        this.upass = upass;
        this.head = head;
        this.regtime = regtime;
        this.gender = gender;
    }

    public UserDomain() {

    }
}

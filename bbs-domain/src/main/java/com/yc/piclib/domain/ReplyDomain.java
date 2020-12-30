package com.yc.piclib.domain;

import lombok.Data;

import java.sql.Timestamp;

/*
 用于页面呈现的内容
 */
@Data
public class ReplyDomain extends PageDomain {
    private Integer replyid;   //注意: 对应的数据表中的字段名叫 book_id
    private String title;
    private String content;
    private Timestamp publishtime;
    private Timestamp modifytime;
    private Integer uid;
    private Integer topicid;

    /**
     * 一张图片的其它属于，通过程序获得.
     */
    private Long fileSize;
    private String extension;
    private String realPath;

    public ReplyDomain(Integer replyid, String title, String content, Timestamp pPublishtime, Timestamp publishtime, Timestamp modifytime, Integer uid, Integer topicid) {
        this.replyid = replyid;
        this.title = title;
        this.content = content;
        this.publishtime = publishtime;
        this.modifytime = modifytime;
        this.uid = uid;
        this.topicid = topicid;
    }

    public ReplyDomain() {

    }
}

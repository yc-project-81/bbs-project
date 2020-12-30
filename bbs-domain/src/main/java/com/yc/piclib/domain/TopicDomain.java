package com.yc.piclib.domain;

import lombok.Data;

import java.sql.Timestamp;

/*
 用于页面呈现的内容
 */
@Data
public class TopicDomain extends PageDomain {
    private Integer topicid;   //注意: 对应的数据表中的字段名叫 book_id
    private String title;
    private String content;
    private String publishtime;
    private String modifytime;
    private Integer uid;
    private Integer boardid;

    /**
     * 一张图片的其它属于，通过程序获得.
     */
    private Long fileSize;
    private String extension;
    private String realPath;

    public TopicDomain(Integer topicid, String title, String content, String publishtime, String modifytime, Integer pUid, Integer uid, Integer boardid) {
        this.topicid = topicid;
        this.title = title;
        this.content = content;
        this.publishtime = publishtime;
        this.modifytime = modifytime;
        this.uid = uid;
        this.boardid = boardid;
    }

    public TopicDomain() {

    }
}

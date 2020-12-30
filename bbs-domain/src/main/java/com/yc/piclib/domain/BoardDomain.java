package com.yc.piclib.domain;

import lombok.Data;

/*
 用于页面呈现的内容
 */
@Data
public class BoardDomain extends PageDomain {
    private Integer boardid;   //注意: 对应的数据表中的字段名叫 book_id
    private String boardname;
    private String parentname;

    /**
     * 一张图片的其它属于，通过程序获得.
     */
    private Long fileSize;
    private String extension;
    private String realPath;

    public BoardDomain(Integer boardid, String boardname, String parentname) {
        this.boardid = boardid;
        this.boardname = boardname;
        this.parentname = parentname;
    }

    public BoardDomain() {
    }
}

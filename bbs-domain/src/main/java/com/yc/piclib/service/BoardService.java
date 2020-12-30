package com.yc.piclib.service;

import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.BoardDomain;

import java.util.List;

public interface BoardService {

    public List<BoardDomain> list();

    public PageDomain<BoardDomain> listByPage(BoardDomain boardDomain);

    public void save(BoardDomain boardDomain);

    public void delete(Integer id);
}

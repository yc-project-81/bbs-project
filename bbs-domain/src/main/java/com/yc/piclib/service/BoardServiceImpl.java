package com.yc.piclib.service;

import com.yc.piclib.dao.impl.BoardMapper;
import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.BoardDomain;
import com.yc.piclib.entity.Board;
import com.yc.piclib.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    @Autowired(required = false)
    private BoardMapper boardMapper;

    @Transactional(readOnly = true)
    @Override
    public List<BoardDomain> list() {
        List<Board> list = boardMapper.selectAll();
        List<BoardDomain> r = new ArrayList<BoardDomain>();
        for (Board p : list) {
            BoardDomain pd = new BoardDomain(p.getBoardid(), p.getBoardname(), p.getParentname());
            r.add(pd);
        }
        return r;
    }

    @Override
    public PageDomain<BoardDomain> listByPage(BoardDomain boardDomain) {
        Example example = new Example(Board.class);
        if (CommonUtils.isNotNull(boardDomain.getBoardname())) {
            example.createCriteria()
                    .andLike("parentname", "%" + boardDomain.getBoardname() + "%");
        }
        long total = boardMapper.selectCountByExample(example);

        PageDomain<BoardDomain> pageDomain = new PageDomain<BoardDomain>();
        pageDomain.setTotal(total);
        pageDomain.setPage(boardDomain.getPage());
        pageDomain.setPageSize(boardDomain.getPageSize());


        List<Board> list = boardMapper.selectByExample(example);
        List<BoardDomain> r = new ArrayList<BoardDomain>();
        for (Board p : list) {
            BoardDomain pd = new BoardDomain(p.getBoardid(), p.getBoardname(), p.getParentname());
            r.add(pd);
        }
        pageDomain.setData(r);
        return pageDomain;
    }

    @Override
    public void save(BoardDomain boardDomain) {
        Board board = new Board();
        board.setBoardname(boardDomain.getBoardname());
        board.setParentname(boardDomain.getParentname());
        this.boardMapper.insert(board);
        // 在这里  mybatis完成了两步操作: 1. insert   2. select 到最新的id后，存到pic中
        //pic中的id已经获取到
        //关键:
        boardDomain.setBoardid(board.getBoardid());
    }


    @Override
    public void delete(Integer id) {
        this.boardMapper.deleteByPrimaryKey(id);
    }
}

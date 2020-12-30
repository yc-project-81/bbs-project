package com.yc;


import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.BoardDomain;
import com.yc.piclib.service.BoardService;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
public class TestService {

    private static final Logger logger = Logger.getLogger(TestService.class);
    @Autowired
    private BoardService boardService;

    @Test
    public void testList() {
        logger.info("调用BoardService.list");
        List<BoardDomain> list = boardService.list();
        System.out.println(list);
    }

    @Test
    public void testListByPage() {
        BoardDomain picDomain = new BoardDomain(1, "abc", 1);
        logger.info("调用BoardService.listByPage");
        PageDomain<BoardDomain> list = boardService.listByPage(picDomain);
        System.out.println(list);
    }


    @Test
    public void testSave() {
        Random r = new Random();
        BoardDomain boardDomain = new BoardDomain(15, "new board" + r.nextInt(9999), 10000);
        boardService.save(boardDomain);
        logger.info("新增的产品编号:" + boardDomain.getBoardid());
        //断言.
    }

}

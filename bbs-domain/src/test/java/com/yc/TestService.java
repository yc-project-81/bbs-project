package com.yc;


import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.BoardDomain;
import com.yc.piclib.domain.TopicDomain;
import com.yc.piclib.domain.UserDomain;
import com.yc.piclib.service.BoardService;
import com.yc.piclib.service.TopicService;
import com.yc.piclib.service.UserService;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
public class TestService {

    private static final Logger logger = Logger.getLogger(TestService.class);
    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Test
    public void testList() {
        logger.info("调用BoardService.list");
        List<BoardDomain> list = boardService.list();
        System.out.println(list);
    }

    @Test
    public void testListByPage() {
        BoardDomain picDomain = new BoardDomain(1, "abc", "1");
        logger.info("调用BoardService.listByPage");
        PageDomain<BoardDomain> list = boardService.listByPage(picDomain);
        System.out.println(list);
    }


    @Test
    public void testSave() {
        Random r = new Random();
        BoardDomain boardDomain = new BoardDomain(15, "new board" + r.nextInt(9999), "10000");
        boardService.save(boardDomain);
        logger.info("新增的产品编号:" + boardDomain.getBoardid());
        //断言.
    }

    @Test
    public void regSave() {
        Random r = new Random();
        UserDomain userDomain = new UserDomain("111","123","1.png",1);
        userService.save(userDomain);
        logger.info("新增的产品编号:" + userDomain.getUid());
        //断言.
    }

    @Test
    public void testOneList() {
        logger.info("调用TopicService.findOne");
        List<TopicDomain> list = topicService.findOne(8);
        System.out.println(list);
    }

    @Test
    public void testOne1List() {
        logger.info("调用TopicService.findOne");
        TopicDomain list = topicService.find(8);
        System.out.println(list);
    }

}

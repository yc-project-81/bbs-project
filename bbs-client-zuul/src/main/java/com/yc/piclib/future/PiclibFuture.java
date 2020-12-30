package com.yc.piclib.future;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.google.gson.Gson;
import com.yc.piclib.domain.BoardDomain;
import com.yc.piclib.domain.TopicDomain;
import com.yc.piclib.domain.UserDomain;
import com.yc.piclib.service.PiclibRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class PiclibFuture {
    @Resource
    private PiclibRestService piclibRestService;

    @Async
    public CompletableFuture<String> findPage(Integer page, Integer pageSize,
                                              String description) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.findAll(page, pageSize, description);
        });
    }

    @Async
    public CompletableFuture<String> create(BoardDomain boardDomain) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.create(boardDomain);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.delete(id);
        });
    }

    @Async
    public CompletableFuture<String> login(String uname, String upass) {
        return CompletableFuture.supplyAsync(() -> {
            UserDomain userDomain=new UserDomain();
            userDomain.setUname(uname);
            userDomain.setUpass(upass);
            return piclibRestService.login(userDomain);
        });
    }


    @Async
    public CompletableFuture<String> push( String content,String title) {
        return CompletableFuture.supplyAsync(() -> {
            TopicDomain topicDomain=new TopicDomain();

            topicDomain.setContent(content);
            topicDomain.setTitle(title);
            topicDomain.setBoardid(1);
            topicDomain.setUid(1);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            topicDomain.setPublishtime(df.format(new Date()));
            System.out.println("2");
            return piclibRestService.push(topicDomain);
        });
    }
}

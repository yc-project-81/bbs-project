package com.yc.piclib.future;

import com.google.gson.Gson;
import com.yc.piclib.domain.BoardDomain;
import com.yc.piclib.domain.TopicDomain;
import com.yc.piclib.domain.UserDomain;
import com.yc.piclib.service.PiclibRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class PiclibFuture {
    @Resource
    private PiclibRestService piclibRestService;


    @Async
   public CompletableFuture<String> findAll( ) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.findAll( );
        });
    }

    @Async
    public CompletableFuture<String> TopicfindAll( ) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.TopicfindAll();
        });
    }


    @Async
    public CompletableFuture<String> Topicfindone( Integer id ) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.Topicfindone(id);
        });
    }

    @Async
    public CompletableFuture<String> UserfindAll( ) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.UserfindAll();
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
    public CompletableFuture<String> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.findById(id);
        });
    }

    @Async
    public CompletableFuture<String> findId(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.findId(id);
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
    public CompletableFuture<String> reg(UserDomain userDomain) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.reg(userDomain);
        });
    }

    @Async
    public CompletableFuture<String> push(TopicDomain topicDomain) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.push(topicDomain);
        });
    }

    @Async
    public CompletableFuture<String> updata(TopicDomain topicDomain,Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.updata(topicDomain,id);
        });
    }
}

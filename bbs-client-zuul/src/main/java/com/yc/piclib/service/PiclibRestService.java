package com.yc.piclib.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.piclib.client.BBSClient;
import com.yc.piclib.domain.BoardDomain;
import com.yc.piclib.domain.TopicDomain;
import com.yc.piclib.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//Hystrix服务层:  调用 PiclibClient,实现断路器功能
@Service
public class PiclibRestService {
    @Resource
    private BBSClient piclibClient;



    private String findByIdFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public String findAll(Integer page, Integer pageSize,
                          String description) {
        return piclibClient.findAll(page, pageSize, description);
    }

    private String findAllFallback(Integer page, Integer pageSize,
                                   String description) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "createFallback")
    public String create(BoardDomain boardDomain) {
        return piclibClient.create(boardDomain);
    }

    private String createFallback(BoardDomain boardDomain) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加" + boardDomain.getBoardname());
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return piclibClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "loginFallback")
    public String login(UserDomain userDomain) {
        System.out.println("1"+userDomain.getUname()+userDomain.getUpass());
        return piclibClient.login(userDomain.getUname(),userDomain.getUpass());
    }

    private String loginFallback(UserDomain userDomain) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "用户名或密码错误");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "pushFallback")
    public String push(TopicDomain topicDomain) {
        System.out.println("3");
        return piclibClient.push(topicDomain.getTitle(),topicDomain.getContent(),topicDomain.getPublishtime(),topicDomain.getBoardid(),topicDomain.getUid());
    }

    private String pushFallback(TopicDomain topicDomain) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "发布失败");
        return new Gson().toJson(map);
    }
}


package com.yc.piclib.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.piclib.client.BBSClient;
import com.yc.piclib.domain.BoardDomain;
import com.yc.piclib.domain.TopicDomain;
import com.yc.piclib.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//Hystrix服务层:  调用 PiclibClient,实现断路器功能
@Service
public class PiclibRestService {
    @Resource
    private BBSClient piclibClient;


    @HystrixCommand(fallbackMethod = "findAllFallback")
   public String findAll(String boardname, String parentname) {
       return piclibClient.findAll( boardname,parentname);
   }

   private String findAllFallback( String boardname, String parentname) {
       Map map = new HashMap();
        map.put("code", "-1");
       map.put("msg", "服务异常");
       return new Gson().toJson(map);
   }

    @HystrixCommand(fallbackMethod = "topicfindAllFallback")
    public String TopicfindAll() {
        return piclibClient.TopicfindAll();
    }

    private String topicfindAllFallback() {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "userfindAllFallback")
    public String UserfindAll() {
        return piclibClient.UserfindAll();
    }

    private String userfindAllFallback() {
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

    @HystrixCommand(fallbackMethod = "regFallback")
    public String reg(UserDomain userDomain) {
        return piclibClient.reg(userDomain);
    }

    private String regFallback(UserDomain userDomain) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加" + userDomain.getUname());
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public String findById(Integer id) {
        return piclibClient.findById(id);
    }

    private String findByIdFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法找到" + id);
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findIdFallback")
    public String findId(Integer id) {
        return piclibClient.findId(id);
    }

    private String findIdFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法找到" + id);
        return new Gson().toJson(map);
    }
}


package com.yc.piclib.restapi.controllers;


import com.google.gson.Gson;
import com.yc.piclib.domain.*;
import com.yc.piclib.entity.Board;
import com.yc.piclib.entity.Topic;
import com.yc.piclib.entity.User;
import com.yc.piclib.service.*;
import com.yc.piclib.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bbs")
public class PiclibRestController {
    private static Logger logger = LoggerFactory.getLogger(PiclibRestController.class);

    @Autowired
    private BoardService boardService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TopicService topicService;
    
    @Autowired
    private ReplyService replyService;
//    @RequestMapping(value = "/{id}")
//    //  @HystrixCommand(fallbackMethod = "errorCallBack")   //模仿没有这个数据时，服务降级
//    public CompletableFuture<String> findById(@PathVariable Integer id) {
//        // static CompletableFuture<U> supplyAsync(Supplier<U> supplier)
//        //   Supplier就是一个接口
//        //    接口中的方法:   T get();
//
////        return CompletableFuture.supplyAsync(new Supplier() {
////            @Override
////            public Object get() {  //回调方法,   当请求有响应，由  jvm 调用.
////                  PicDomain pic = picService.findOne(id);
//        //            Map<String, Object> map = new HashMap<>();
//        //            map.put("code", 1);
//        //            map.put("data", pic);
//        //            return new Gson().toJson(map);
////            }
////        });
//        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
//        return CompletableFuture.supplyAsync(() -> {
//            BoardDomain pic = boardService.findOne(id);
//            //协议
//            Map<String, Object> map = new HashMap<>();
//            map.put("code", 1);
//            map.put("data", pic);
//            //map.put("msg","");
//            return new Gson().toJson(map);
//        });
//    }

//    //指定一个降级的方法
//    public String errorCallBack(@PathVariable("id") Integer id) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", 404);
//        map.put("msg", "查无此图片");
//        //map.put("msg","");
//        return new Gson().toJson(map);
//    }

//    //TODO: 用于测试异步与非异步的区别: test: local call
//    @RequestMapping(value = "/test/{id}")
//    // @HystrixCommand(fallbackMethod = "errorCallBack2")
//    public String testFindById(@PathVariable Integer id) {
//        BoardDomain pic = boardService.findOne(id);
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", 1);
//        map.put("data", pic);
//        return new Gson().toJson(map);
//    }

//    public String errorCallBack2(@PathVariable("id") Integer id) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", 404);
//        map.put("msg", "查无此图片2, 此处没有用到future");
//        //map.put("msg","");
//        return new Gson().toJson(map);
//    }

    @RequestMapping(value = "/board/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(1);
            List<BoardDomain> list = boardService.list();
            System.out.println(2);
            Map<String,Object> map=new HashMap<>();
            System.out.println(3);
            map.put("code",1);
            map.put("data",list);
            return new Gson().toJson(map);
        });
    }

    @RequestMapping(value = "/topic/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> TopicfindAll() {
        return CompletableFuture.supplyAsync(() -> {
            List<TopicDomain> list = topicService.list();
            Map<String,Object> map=new HashMap<>();
            map.put("code",1);
            map.put("data",list);
            return new Gson().toJson(map);
        });
    }

    @RequestMapping(value = "/user/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> UserfindAll() {
        return CompletableFuture.supplyAsync(() -> {
            List<UserDomain> list = userService.list();
            Map<String,Object> map=new HashMap<>();
            map.put("code",1);
            map.put("data",list);
            return new Gson().toJson(map);
        });
    }


    @RequestMapping(value = "/board/create",method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody BoardDomain boardDomain) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            boardService.save(boardDomain);
            logger.info("新增->ID=" + boardDomain.getBoardid());
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", boardDomain);
            return new Gson().toJson(map);
        });
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            boardService.delete(id);
            logger.info("删除->ID=" + id);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            return new Gson().toJson(map);
        });
    }

    //用户注册
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public CompletableFuture<String> reg(@RequestBody UserDomain userDomain) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            userService.save(userDomain);
            System.out.println(userDomain);
            logger.info("新增用户名=" +userDomain.getUname());
            logger.info("新增用户密码=" +userDomain.getUpass());
            logger.info("新增用户头像=" +userDomain.getHead());
            logger.info("新增用户性别="+userDomain.getGender());
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", userDomain);
            return new Gson().toJson(map);
        });
    }

    //用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CompletableFuture<String> login(@RequestParam String uname,@RequestParam String upass) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                User user=new User();
                user.setUname(uname);
                user.setUpass(upass);
                UserDomain userDomain=userService.findOne(user);
                System.out.println(uname+upass);
                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                map.put("data", userDomain.getGender());
                String msg=("登录成功，欢迎:"+uname);
                System.out.println(msg);
                return new Gson().toJson(map);
            } catch (Exception e) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", -1);
                map.put("data", "用户名或密码错误，请重试");
                String msg1=("登录失败");
                System.out.println(msg1);
            }
            return null;
        });
    }

    @RequestMapping(value = "/topic/{id}" ,method = RequestMethod.GET)
    public CompletableFuture<String> findById1(@RequestParam Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            List<TopicDomain> topic = topicService.findOne(id);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", topic);
            return new Gson().toJson(map);
        });
    }
    @RequestMapping(value = "/topicone" ,method = RequestMethod.GET)
    public CompletableFuture<String> findById(@RequestParam Integer id) {
        return CompletableFuture.supplyAsync(() -> {
           TopicDomain topic = topicService.find(id);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", topic);
            return new Gson().toJson(map);
        });
    }

    @RequestMapping(value = "topic/id/{id}" ,method = RequestMethod.GET)
    public CompletableFuture<String> findId(@PathVariable Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            TopicDomain topic = topicService.find(id);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", topic);
            return new Gson().toJson(map);
        });
    }


    //用户发布
    @RequestMapping(value = "/push", method = RequestMethod.GET)
    public CompletableFuture<String> push(@RequestParam String title, @RequestParam String content) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TopicDomain topicDomain=new TopicDomain();
                topicDomain.setTitle(title);
                topicDomain.setContent(content);
                topicDomain.setUid(1);
                topicDomain.setBoardid(1);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                topicDomain.setPublishtime(df.format(new Date()));
                topicService.save(topicDomain);

                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                map.put("data", "发布成功！！！");
                return new Gson().toJson(map);
            } catch (Exception e) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", -1);
                map.put("data", "发布失败，请联系管理员！！！ ");
                return new Gson().toJson(map);

            }
        });
    }

    //删除发布
    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> deletelist(@PathVariable Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            topicService.delete(id);
            logger.info("删除->ID=" + id);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            return new Gson().toJson(map);
        });
    }

    //用户评论
    @RequestMapping(value = "/topic", method = RequestMethod.POST)
    public CompletableFuture<String> reply(@PathVariable Integer replyid,@PathVariable String title,@PathVariable String content,@PathVariable Timestamp publishtime,@PathVariable Timestamp modifytime,@PathVariable Integer uid,@PathVariable Integer topicid) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
                ReplyDomain replyDomain=new ReplyDomain();
                replyDomain.setReplyid(replyid);
                replyDomain.setTitle(title);
                replyDomain.setContent(content);
                replyDomain.setPublishtime(publishtime);
                replyDomain.setModifytime(modifytime);
                replyDomain.setUid(uid);
                replyDomain.setTopicid(topicid);

                replyService.reply(replyDomain);

            Map<String, Object> map = new HashMap<>();
            map.put("提示", "评论发布成功");
            return new Gson().toJson(map);
        });
    }

    //删除评论
    @RequestMapping(value = "/reply/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> deleteReply(@PathVariable Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            replyService.delete(id);
            logger.info("删除->ID=" + id);
            Map<String, Object> map = new HashMap<>();
            map.put("code", "已删除评论");
            return new Gson().toJson(map);
        });
    }


    @RequestMapping(value = "/topic/updata/{id}" ,method = RequestMethod.POST)
    public CompletableFuture<String> updata(@RequestBody TopicDomain topicDomain,@PathVariable Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            topicService.updata(topicDomain,id);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", "修改成功");
            return new Gson().toJson(map);
        });
    }
}

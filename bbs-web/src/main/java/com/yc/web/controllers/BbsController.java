package com.yc.web.controllers;


import com.google.gson.Gson;
import com.yc.piclib.client.BBSClient;
import com.yc.piclib.domain.TopicDomain;
import com.yc.piclib.domain.UserDomain;
import com.yc.piclib.future.PiclibFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bbs")
public class BbsController {

    private static Logger logger = LoggerFactory.getLogger(BbsController.class.getName());

    @Resource
    private PiclibFuture bbsFuture;

//    @Value("${file.path.head:http://121.196.110.249/}")
//    private String pathHead;

//    @Autowired
//    private BBSClient bbsClient;

//    //数据库操作
//    private CompletableFuture<String> saveUser(MultipartFile multipartFile, String name, String upass) throws Exception {
//        BufferedImage image = ImageIO.read(multipartFile.getInputStream());
//        UserDomain userDomain = new UserDomain(name,upass);
//        return bbsUserFuture.create(userDomain).thenApply(info -> {
//            return info;
//        });
//    }

    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public CompletableFuture<String> reg(@RequestParam String uname, @RequestParam String upass,@RequestParam String head,@RequestParam Integer gender) {
        UserDomain userDomain=new UserDomain();
        userDomain.setUname(uname);
        userDomain.setUpass(upass);
        userDomain.setHead(head);
        userDomain.setGender(gender);
        return bbsFuture.reg(userDomain);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CompletableFuture<String> login(@RequestParam String uname, @RequestParam String upass) {
        return bbsFuture.login(uname,upass);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public CompletableFuture<String> findAll( String boardname, String parentname) {
        return bbsFuture.findAll(boardname,parentname);
    }

    @RequestMapping(value = "topic/findAll", method = RequestMethod.POST)
    public CompletableFuture<String> TopicfindAll() {
        return bbsFuture.TopicfindAll();
    }

    @RequestMapping(value = "user/findAll", method = RequestMethod.POST)
    public CompletableFuture<String> UserfindAll() {
        return bbsFuture.UserfindAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        return bbsFuture.findById(id);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.POST)
    public CompletableFuture<String> findId(@PathVariable Integer id) {
        return bbsFuture.findId(id);
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public CompletableFuture<String> save(@RequestBody UserDomain userDomain) throws Exception {
//        return bbsUserFuture.create(userDomain);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
//        return bbsUserFuture.delete(id);
//    }

}

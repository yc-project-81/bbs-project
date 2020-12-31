package com.yc.piclib.client;


import com.yc.piclib.config.FeignClientConfig;
import com.yc.piclib.domain.BoardDomain;
import com.yc.piclib.domain.TopicDomain;
import com.yc.piclib.domain.UserDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// feign客户端要访问的是  zuul服务 BASE-MICROSERVICE-ZUUL-GATEWAY
@FeignClient(name = "BASE-MICROSERVICE-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig

public interface BBSClient {

    //访问的路径value要修改成zuul指定的服务路由路径
//    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbs-proxy/piclib/{id}")
//    String findById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/bbs-proxy/topic/updata/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String updata(@RequestBody TopicDomain topicDomain,@RequestParam("id") Integer id);


    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbs-proxy/bbs/topicone",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findOne(@RequestParam("id") Integer id);


    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbs-proxy/bbs/board/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll();

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbs-proxy/bbs/topic/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String TopicfindAll();

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbs-proxy/bbs/user/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String UserfindAll();

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/bbs-proxy/board/create",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody BoardDomain boardDomain);

    @RequestMapping(method = RequestMethod.DELETE, value = "/yc-api/piclib-proxy/piclib/{id}")
    String delete(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/bbs-proxy/bbs/login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String login(@RequestParam("uname") String uname, @RequestParam("upass") String upass);

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/bbs-proxy/bbs/reg",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String reg(@RequestBody UserDomain userDomain);

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbs-proxy/bbs/topic/{id}")
    String findById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbs-proxy/bbs/topic/id/{id}")
    String findId(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbs-proxy/bbs/push")
    String push(@RequestParam("title") String title,@RequestParam("content") String content);

}





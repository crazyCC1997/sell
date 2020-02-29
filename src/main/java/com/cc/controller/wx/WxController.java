package com.cc.controller.wx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * WxController（手动获取openid）
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/14 22:53
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WxController {
    
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入了auth()方法...");
        log.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx50616d5b9b8d81aa&secret=593d3a9a06e05df87d4c7e75d3efd92a&code=" + code +"&grant_type=authorization_code ";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        //响应的结果(包含openid)
        log.info("result={}", response);
    }
}

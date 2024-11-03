package com.yoong.javaspring2.web;

import com.yoong.javaspring2.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor

public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
       String url = request.getRequestURL().toString();
       myLogger.setRequestUrl(url);

       myLogger.log("controller test");
       logDemoService.logic("testId");

       return "OK";
    }
}

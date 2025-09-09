package com.van.train.member.controller;

import com.van.train.member.service.memberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class memberController {

    @Resource
    private memberService memberService;

    @GetMapping("/count")
    public Integer count() {
        return memberService.count();
    }

    //注册接口
    @PostMapping("/register")
    public long Register(String mobile) {

        return memberService.Register(mobile);

    }
}

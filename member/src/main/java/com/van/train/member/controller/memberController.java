package com.van.train.member.controller;

import com.van.train.common.resp.CommonResp;
import com.van.train.member.Resp.MemberLoginResp;
import com.van.train.member.Req.MemberLoginReq;
import com.van.train.member.Req.MemberSendCodeReq;
import com.van.train.member.Req.MembersRegiserRec;
import com.van.train.member.service.memberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public CommonResp<Long> Register(@Valid MembersRegiserRec rec) {
        CommonResp<Long> resp = new CommonResp<>();
        long id = memberService.Register(rec);
        resp.setContent( id);
        return resp;
    }

        //注册接口
         @PostMapping("/send-code")
        public CommonResp<Long> sendCode(MemberSendCodeReq req) {
            memberService.sendCode(req);

            return new CommonResp<>();
    }

    //登录接口
    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@RequestBody MemberLoginReq req) {
        MemberLoginResp resp = memberService.Login(req);
        return new CommonResp<>(resp);
    }



}


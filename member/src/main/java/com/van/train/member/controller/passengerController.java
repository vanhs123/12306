package com.van.train.member.controller;

import com.van.train.common.Context.LoginMemberContext;
import com.van.train.common.resp.CommonResp;
import com.van.train.common.resp.PageResp;
import com.van.train.member.Req.PassengerSaveReq;
import com.van.train.member.Resp.PassengerQueryResp;
import com.van.train.member.service.passengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/passenger")
@RestController
public class passengerController {



    @Resource
    private passengerService passengerService;


    //注册接口
    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        passengerService.save(req);
        return resp;
    }


    //查询接口
    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid  PassengerSaveReq req) {
        //从线程池里获取memberid
        req.setMemberId(LoginMemberContext.getId());
        Long id = LoginMemberContext.getId();
        log.info("LoginMemberContext.getId() = {}",id);
        PageResp<PassengerQueryResp> list = passengerService.queryList(req);

        return new CommonResp<>(list);

    }


}


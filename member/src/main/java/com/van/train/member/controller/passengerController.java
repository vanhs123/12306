package com.van.train.member.controller;

import com.van.train.common.resp.CommonResp;
import com.van.train.member.Req.PassengerSaveReq;
import com.van.train.member.service.passengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class passengerController {



    @Resource
    private passengerService passengerService;


    //注册接口
    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq rec) {
        CommonResp<Object> resp = new CommonResp<>();
        passengerService.save(rec);
        return resp;
    }


}


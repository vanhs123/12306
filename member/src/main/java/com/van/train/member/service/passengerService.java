package com.van.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.van.train.common.Util.SnowUtil;
import com.van.train.member.Req.PassengerSaveReq;
import com.van.train.member.domain.passenger;
import com.van.train.member.mapper.passengerMapper;
import org.springframework.stereotype.Service;

@Service
public class passengerService {

    private final passengerMapper passengerMapper;

    public passengerService(passengerMapper passengerMapper) {
        this.passengerMapper = passengerMapper;
    }

    public void save(PassengerSaveReq passengerSaveReq) {

        DateTime now = DateTime.now();

        //将前端传来的请求参数中的存储在数据库里
        passenger passenger= BeanUtil.copyProperties(passengerSaveReq, passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);




    }

}

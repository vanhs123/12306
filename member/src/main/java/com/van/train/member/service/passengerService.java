package com.van.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.van.train.common.Util.SnowUtil;
import com.van.train.member.Req.PassengerSaveReq;
import com.van.train.member.Domain.Passenger;
import com.van.train.member.mapper.PassengerMapper;
import org.springframework.stereotype.Service;

@Service
public class passengerService {

    private final PassengerMapper passengerMapper;

    public passengerService(PassengerMapper passengerMapper) {
        this.passengerMapper = passengerMapper;
    }

    public void save(PassengerSaveReq passengerSaveReq) {

        DateTime now = DateTime.now();

        //将前端传来的请求参数中的存储在数据库里
        Passenger passenger= BeanUtil.copyProperties(passengerSaveReq, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);




    }

}

package com.van.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.van.train.common.Util.SnowUtil;
import com.van.train.member.Domain.PassengerExample;
import com.van.train.member.Req.PassengerQueryReq;
import com.van.train.member.Req.PassengerSaveReq;
import com.van.train.member.Domain.Passenger;
import com.van.train.member.mapper.PassengerMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<PassengerQueryReq> queryList(@Valid PassengerSaveReq passengerQueryReq) {

        //首先我们应该去根据条件筛选
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotEmpty(passengerQueryReq.getMemberId())) {
            criteria.andMemberIdEqualTo(passengerQueryReq.getMemberId());
        }

        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);
        return BeanUtil.copyToList(passengerList,PassengerQueryReq.class);

    }

}

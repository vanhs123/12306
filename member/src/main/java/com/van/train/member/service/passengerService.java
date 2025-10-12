package com.van.train.member.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.van.train.common.Util.SnowUtil;
import com.van.train.common.resp.PageResp;
import com.van.train.member.Domain.Passenger;
import com.van.train.member.Domain.PassengerExample;
import com.van.train.member.Req.PassengerSaveReq;
import com.van.train.member.Resp.PassengerQueryResp;
import com.van.train.member.mapper.PassengerMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class passengerService {

    private static final Logger LOG = LoggerFactory.getLogger(passengerService.class);

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

    /**
     * 分页查询
     * @param passengerQueryReq
     * @return
     */
    public PageResp<PassengerQueryResp> queryList(@Valid PassengerSaveReq passengerQueryReq) {

        //首先我们应该去根据条件筛选
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotEmpty(passengerQueryReq.getMemberId())) {
            criteria.andMemberIdEqualTo(passengerQueryReq.getMemberId());
        }


        LOG.info("查询页码：{}", passengerQueryReq.getPage());
        LOG.info("每页条数：{}", passengerQueryReq.getSize());
        //分页查询
        PageHelper.startPage(passengerQueryReq.getPage(), passengerQueryReq.getSize());
        //PageHelper.startPage(1,1);

        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);

        //直接用，就是日志
        PageInfo<Passenger> pageInfo = new PageInfo<>(passengerList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());



        //将查询到的结果封装到返回参数里
        List<PassengerQueryResp> list = BeanUtil.copyToList(passengerList, PassengerQueryResp.class);

        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;

        //return BeanUtil.copyToList(passengerList,PassengerQueryReq.class);

    }

}

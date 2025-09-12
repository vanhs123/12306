package com.van.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.van.train.member.domain.member;
import com.van.train.member.domain.memberExample;
import com.van.train.member.mapper.memberMapper;
import com.van.train.member.ref.MemberSendCodeReq;
import com.van.train.member.ref.MembersRegiserRec;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Repository("/member")
public class memberService {
    @Resource
    private memberMapper memberMapper;

    @GetMapping("/count")
    public Integer count() {
        return (int) memberMapper.countByExample(null);
    }

    private static final Logger LOG = LoggerFactory.getLogger(memberService.class);

    //注册手机号
    public long Register(MembersRegiserRec rec) {
        memberExample example = new memberExample();
        example.createCriteria().andMobileEqualTo(rec.getMobile());
        List<member> list = memberMapper.selectByExample(example);

        if(CollUtil.isNotEmpty(list)) {
            throw new RuntimeException("手机号已注册");
        }

        member member = new member();
        member.setMobile(rec.getMobile());
        member.setId(System.currentTimeMillis());


        memberMapper.insert(member);
        return member.getId();
    }

    //注册手机号
    public void sendCode(MemberSendCodeReq req) {

        //调用代码生成器模块里api生成查询条件
        memberExample example = new memberExample();
        example.createCriteria().andMobileEqualTo(req.getMobile());
        List<member> list = memberMapper.selectByExample(example);
        //手机号为空就注册
        if(CollUtil.isEmpty(list)) {
            LOG.info("手机号不存在，插入一条记录");
            member member = new member();
            member.setMobile(req.getMobile());
            member.setId(System.currentTimeMillis());
            memberMapper.insert(member);
        }else {
            LOG.info("手机号存在，不插入记录");
        }
        //生成验证码
        // String code = RandomUtil.randomString(4);
        String code = "8888";
        LOG.info("生成短信验证码：{}", code);

        // 保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        LOG.info("保存短信记录表");

        // 对接短信通道，发送短信
        LOG.info("对接短信通道");



    }


}

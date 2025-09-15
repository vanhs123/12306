package com.van.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.van.train.common.Util.SnowUtil;
import com.van.train.common.exception.BusinessException;
import com.van.train.common.exception.BusinessExceptionEnum;
import com.van.train.member.Resp.MemberLoginResp;
import com.van.train.member.domain.member;
import com.van.train.member.domain.memberExample;
import com.van.train.member.mapper.memberMapper;
import com.van.train.member.ref.MemberLoginReq;
import com.van.train.member.ref.MemberSendCodeReq;
import com.van.train.member.ref.MembersRegiserRec;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;


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
    public long Register(MembersRegiserRec req) {
        String mobile = req.getMobile();
        //调用代码生成器模块里api生成查询条件
        member memberDB= selectByMobile(mobile);
        //手机号为空就注册
        if(ObjectUtil.isEmpty(memberDB)) {
            throw new RuntimeException("手机号已注册");
        }

        member member = new member();
        member.setMobile(req.getMobile());
        member.setId(System.currentTimeMillis());


        memberMapper.insert(member);
        return member.getId();
    }

    //注册手机号
    public void sendCode(MemberSendCodeReq req) {

        //调用代码生成器模块里api生成查询条件
        String mobile = req.getMobile();
        //调用代码生成器模块里api生成查询条件
        member memberDB= selectByMobile(mobile);
        //手机号为空就注册
        if(ObjectUtil.isEmpty(memberDB)) {
            LOG.info("手机号不存在，插入一条记录");
            member member = new member();
            member.setMobile(req.getMobile());
            member.setId(SnowUtil.getSnowflakeNextId());
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

    /*
    登录部分
     */
    public MemberLoginResp Login(MemberLoginReq req) {

        String code = req.getCode();

        String mobile = req.getMobile();
        //调用代码生成器模块里api生成查询条件
        member memberDB= selectByMobile(mobile);
        //手机号不存在就抛异常
        if(ObjectUtil.isEmpty(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }
        //校验短信验证码
        if(!"8888".equals(code)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        //封装返回参数
        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
        /*
        * 使用hutool工具包生成jwt
        * */
        Map<String, Object> map = BeanUtil.beanToMap(memberLoginResp);
        String key = "vanhs12306";
        String token = JWTUtil.createToken(map, key.getBytes());
        memberLoginResp.setToken(token);

        return memberLoginResp;


    }


    private member selectByMobile(String req) {
        memberExample example = new memberExample();
        example.createCriteria().andMobileEqualTo(req);
        List<member> list = memberMapper.selectByExample(example);
        LOG.info("查询手机号结果: {}", list);
        //手机号为空就注册
        if(CollUtil.isEmpty(list)) {
            return null;
        }else {
            return list.get(0);
        }

    }


}

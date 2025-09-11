package com.van.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.van.train.member.domain.member;
import com.van.train.member.domain.memberExample;
import com.van.train.member.mapper.memberMapper;
import com.van.train.member.ref.MembersRegiserRec;
import jakarta.annotation.Resource;
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


}

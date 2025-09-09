package com.van.train.member.service;

import com.van.train.member.mapper.memberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Repository("/member")
public class memberService {
    @Resource
    private memberMapper memberMapper;

    @GetMapping("/count")
    public Integer count() {
        return (int) memberMapper.countByExample(null);
    }


}

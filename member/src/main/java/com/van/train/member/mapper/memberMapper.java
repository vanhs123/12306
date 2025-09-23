package com.van.train.member.mapper;

import com.van.train.member.Domain.member;
import com.van.train.member.Domain.memberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface memberMapper {
    long countByExample(memberExample example);

    int deleteByExample(memberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(member record);

    int insertSelective(member record);

    List<member> selectByExample(memberExample example);

    member selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") member record, @Param("example") memberExample example);

    int updateByExample(@Param("record") member record, @Param("example") memberExample example);

    int updateByPrimaryKeySelective(member record);

    int updateByPrimaryKey(member record);
}
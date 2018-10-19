package com.wux.rcb.elf.biz.dao;

import com.wux.rcb.elf.biz.model.DataConvertRuleDo;

import java.util.List;

public interface DataConvertRuleDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataConvertRuleDo record);

    int insertSelective(DataConvertRuleDo record);

    DataConvertRuleDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataConvertRuleDo record);

    int updateByPrimaryKey(DataConvertRuleDo record);

    List<DataConvertRuleDo> selectByCondition(DataConvertRuleDo record);
}
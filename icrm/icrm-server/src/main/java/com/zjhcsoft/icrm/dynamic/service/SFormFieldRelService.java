package com.zjhcsoft.icrm.dynamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.icrm.dynamic.domain.SFormFieldRel;
import com.zjhcsoft.icrm.dynamic.mapper.SFormFieldRelMapper;

@Service
public class SFormFieldRelService {

	@Autowired
	private SFormFieldRelMapper sformFieldRelMapper;

	public int deleteByPrimaryKey(String id) {
		return sformFieldRelMapper.deleteByPrimaryKey(id);
	}

	public int insert(SFormFieldRel record) {
		return sformFieldRelMapper.insert(record);
	}

	public int insertSelective(SFormFieldRel record) {
		return sformFieldRelMapper.insertSelective(record);
	}

	SFormFieldRel selectByPrimaryKey(String id) {
		return sformFieldRelMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(SFormFieldRel record) {
		return sformFieldRelMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(SFormFieldRel record) {
		return sformFieldRelMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(SFormFieldRel record) {
		return sformFieldRelMapper.updateByPrimaryKey(record);
	}

	public List<SFormFieldRel> selectAllEffective() {
		return sformFieldRelMapper.selectAllEffective();
	}

}

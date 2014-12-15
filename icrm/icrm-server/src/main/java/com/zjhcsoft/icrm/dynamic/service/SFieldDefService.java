package com.zjhcsoft.icrm.dynamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.icrm.dynamic.domain.SFieldDef;
import com.zjhcsoft.icrm.dynamic.domain.SFormDef;
import com.zjhcsoft.icrm.dynamic.mapper.SFieldDefMapper;

@Service
public class SFieldDefService {

	@Autowired
	private SFieldDefMapper sFieldDefMapper;

	public int deleteByPrimaryKey(String id) {
		return sFieldDefMapper.deleteByPrimaryKey(id);
	}

	public int insert(SFieldDef record) {
		return sFieldDefMapper.insert(record);
	}

	public int insertSelective(SFieldDef record) {
		return sFieldDefMapper.insertSelective(record);
	}

	public SFieldDef selectByPrimaryKey(String id) {
		return sFieldDefMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(SFieldDef record) {
		return sFieldDefMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(SFieldDef record) {
		return sFieldDefMapper.updateByPrimaryKey(record);
	}
	
	public List<SFieldDef> selectAllEffective() {
		return sFieldDefMapper.selectAllEffective();
	}
}

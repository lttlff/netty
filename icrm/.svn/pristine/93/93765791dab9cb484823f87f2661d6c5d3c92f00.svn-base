package com.zjhcsoft.icrm.dynamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zjhcsoft.icrm.dynamic.domain.SFormDef;
import com.zjhcsoft.icrm.dynamic.mapper.SFormDefMapper;

@Service
public class SFormDefService {

	@Autowired
	private SFormDefMapper sFormDefMapper;

	public int deleteByPrimaryKey(String id) {
		return sFormDefMapper.deleteByPrimaryKey(id);
	}

	public int insert(SFormDef record) {
		return sFormDefMapper.insert(record);
	}

	public int insertSelective(SFormDef record) {
		return sFormDefMapper.insertSelective(record);
	}

	public SFormDef selectByPrimaryKey(String id) {
		return sFormDefMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(SFormDef record) {
		return sFormDefMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(SFormDef record) {
		return sFormDefMapper.updateByPrimaryKey(record);
	}

	public List<SFormDef> selectAllEffective() {
		return sFormDefMapper.selectAllEffective();
	}

	
	
}

package com.zjhcsoft.icrm.cache.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.icrm.cache.domain.Dictionary;
import com.zjhcsoft.icrm.cache.domain.SysConfigDefDao;
import com.zjhcsoft.icrm.cache.domain.SysConfigValue;
import com.zjhcsoft.icrm.cache.mapper.SysConfigDefMapper;
import com.zjhcsoft.rpc.context.impl.Request;

/**
 * 
* @Description:值列表缓存
* @Author zhunb 
* @Date 2014-5-5 上午9:23:06
*
 */
@Service
public class SysConfigCache {

	@Autowired
	private SysConfigDefMapper sysConfigDefMapper;
	
	private static List<SysConfigDefDao> sysConfigDefList;
	private static Map<String,List<SysConfigValue>> configCacheMap;
 	private static Map<String,List<Dictionary>> dicCache;
	
 	
 	public Map<String, List<Dictionary>> getAllDic(){
 		return getDicCacheMap();
 	}
	
	public List<Dictionary> getDataDicByCode(String fieldCode){
		if(dicCache==null){
			buildDicCache();
		}
		return dicCache.get(fieldCode);
	}
 	
	public List<Dictionary> getDataDicByCode(){
		if(dicCache==null){
			buildDicCache();
		}
		String fieldCode = (String) Request.getAttribute("fieldCode");
		return dicCache.get(fieldCode);
	}
 	
	
 	
	@PostConstruct
	private void initCache() {
		loadData();
	}

	private void loadData() {
		sysConfigDefList = sysConfigDefMapper.getSysConfigDefList();
		buildCache();
	}

	private void buildCache() {
		configCacheMap = getConfigCacheMap();
		for(int i=0;i<sysConfigDefList.size();i++){
			configCacheMap.put(sysConfigDefList.get(i).getFieldCode().toLowerCase(), sysConfigDefList.get(i).getConfigValueList());
		}
		buildDicCache();
	}
	
	private void buildDicCache() {
		if(configCacheMap==null){
			buildCache();
		}
		if(dicCache==null){
			dicCache = new HashMap<String,List<Dictionary>>();
		}
		Set<String> fieldCodes = configCacheMap.keySet();
		Iterator<String> it = fieldCodes.iterator();
		String key;
		List<Dictionary> dicList;
		while(it.hasNext()){
			key = it.next();
			List<SysConfigValue> list = configCacheMap.get(key);
			Dictionary dic;
			dicList = new ArrayList<Dictionary>();
			for(int i=0;i<list.size();i++){
				dic = new Dictionary();
				dic.setText(list.get(i).getAttrValueName());
				dic.setValue(list.get(i).getAttrValue());
				dicList.add(dic);
			}
			dicCache.put(key, dicList);
		}
		
		
	}

	private Map<String, List<Dictionary>> getDicCacheMap() {
		buildDicCache();
		return dicCache;
	}

	private Map<String, List<SysConfigValue>> getConfigCacheMap() {
		if(configCacheMap==null){
			configCacheMap = new HashMap<String,List<SysConfigValue>>();
		}
		return configCacheMap;
	}
	
	
}

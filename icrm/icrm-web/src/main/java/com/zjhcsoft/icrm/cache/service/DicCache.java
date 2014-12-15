package com.zjhcsoft.icrm.cache.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.icrm.cache.domain.Dictionary;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

/**
 * 
 * @Description: 字典缓存
 * @Author zhunb
 * @Date 2014-5-6 上午9:59:05
 * 
 */
@Service
public class DicCache {

	private final static String sysConfigCache = "sysConfigCache";

	private static Map<String, List<Dictionary>> dicCache;

	@PostConstruct
	private void initCache() {
		System.out.println("getAllDic................");
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				sysConfigCache, "getAllDic", null);
		if (result != null) {
			dicCache = (Map<String, List<Dictionary>>) result.getResult();
		}
	}

	public void reloadCache(String fieldCode){
		if(StringUtils.isEmpty(fieldCode)){
			Map map = new HashMap();
			map.put("fieldCode", fieldCode);
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					"sysConfigCache", "getDataDicByCode", map);
			dicCache.put(fieldCode, (List<Dictionary>) result.getResult());
		}else{
			initCache();
		}
	}
	
	public List<Dictionary> getDicListByCode(String key) {
		if (dicCache == null) {
			initCache();
		}
		return dicCache.get(key);
	}

}

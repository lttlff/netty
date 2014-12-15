package com.zjhcsoft.icrm.dynamic.page.web;

import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.icrm.dynamic.domain.SFormDef;
import com.zjhcsoft.icrm.dynamic.domain.SFormFieldRel;
import com.zjhcsoft.icrm.dynamic.mapper.SFormDefMapper;
import com.zjhcsoft.icrm.dynamic.mapper.SFormFieldRelMapper;

/**
 * 
* @Description: 处理页面配置的动态表单页面
* @author zhunb 
* @date 2014-3-21 下午2:23:41
*
 */
@Service
public class ManagerEngine {

	@Autowired
	private SFormDefMapper sformDefMapper;
	@Autowired
	private SFormFieldRelMapper sformFieldRelMapper; 
	
	
	
	public void saveFormDefine(String jsonStr){
		try {
			JSONArray jsonArray = JSONArray.fromObject(jsonStr);
			Iterator it = jsonArray.iterator();
			while (it.hasNext()) {
				JSONObject obj = (JSONObject) it.next();
				saveFormDef(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveFormDef(JSONObject formObj){
		SFormDef sformDef = new SFormDef();
		sformDef.setFormCode("newformautocode");
		sformDef.setFormName(formObj.getString("name"));
		sformDef.setGroupNum(formObj.getLong("group"));
		sformDef.setStatus("1");
		String id = UUIDGenerator.getUUID();
		sformDef.setId(id);
		sformDefMapper.insertSelective(sformDef);
		for(int i=0;i<formObj.getLong("group");i++){
			try {
				Iterator it = formObj.getJSONArray("group" + i).iterator();
				long orderBy = 1;
				while (it.hasNext()) {
					JSONObject obj = (JSONObject) it.next();
					saveFormFieldRel(String.valueOf(i),orderBy,id, obj);
					orderBy++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void saveFormFieldRel(String groupId,long orderBy,String formId,JSONObject obj) throws Exception {
		SFormFieldRel record = new SFormFieldRel();
		record.setId(UUIDGenerator.getUUID());
		record.setFieldDefId(obj.getString("fieldId"));
		record.setFormDefId(formId);
		record.setOrderBy(orderBy);;
		record.setGroupId(groupId);
		record.setAllowblankFlg(obj.get("allowblank")==null? "0": (obj.getBoolean("allowblank")? "1" : "0"));
		record.setReadonlyFlg(obj.get("readonly")==null? "0": (obj.getBoolean("readonly")? "1" : "0"));
		record.setHiddenFlg(obj.get("hidden")==null? "0": (obj.getBoolean("hidden")? "1" : "0"));
		record.setDefaultVal(obj.get("defaultval")!=null?obj.getString("defaultval"):"");
		sformFieldRelMapper.insertSelective(record);
	}
	public static void main(String[] args) {
		System.out.println(true);
		System.out.println();
	}
}

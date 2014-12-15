package com.zjhcsoft.common;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.zjhcsoft.common.page.Page;
import com.zjhcsoft.common.page.PageState;
import com.zjhcsoft.common.page.PageUtil;
import com.zjhcsoft.common.util.CamelCaseUtils;
import com.zjhcsoft.common.util.MethodUtil;
import com.zjhcsoft.icrm.cache.domain.Dictionary;
import com.zjhcsoft.icrm.cache.service.DicCache;

@Component
public class BaseController {

	@Autowired
	private DicCache dicCache;
	
	private final static String SYSTEM_STRING_TEXT = "_text";
	
	@InitBinder  
	protected void initBinder(HttpServletRequest request,  
	                              ServletRequestDataBinder binder) throws Exception {  
	    //对于需要转换为Date类型的属性，使用DateEditor进行处理  
	    binder.registerCustomEditor(Date.class, new DateEditor());  
	} 
	
   protected Page executePage(HttpServletRequest request,Long totalCount){  
       if(null == totalCount){  
           totalCount = 0L;  
       }  
       /**页面状态,这个状态是分页自带的,与业务无关*/  
       String pageAction = request.getParameter("pageAction");  
       String value = request.getParameter("pageKey");  
         
       /**获取下标判断分页状态*/  
       int index = PageState.getOrdinal(pageAction);                 
         
       Page page = null;         
       /** 
        * index < 1 只有二种状态 
        * 1 当首次调用时,分页状态类中没有值为 NULL 返回 -1 
        * 2 当页面设置每页显示多少条: index=0,当每页显示多少条时,分页类要重新计算 
        * */  
       Page sessionPage = getPage(request);  
         
       if(index < 1){             
           page = PageUtil.inintPage(totalCount,index,value,sessionPage);  
       }else{                
           page = PageUtil.execPage(index,value,sessionPage);  
       }         
       setSession(request,page);     
       return page;  
   }     
     
   private Page getPage(HttpServletRequest request) {  
       Page page = (Page)request.getSession().getAttribute(PageUtil.SESSION_PAGE_KEY);  
       if(page == null){  
           page = new Page();  
       }  
       return page;          
   }     
     
   private void setSession(HttpServletRequest request,Page page) {  
       request.getSession().setAttribute(PageUtil.SESSION_PAGE_KEY,page);        
   }     
 

   /**
    * 
    * @param prefix
    * @param field_codes
    */
   protected void addDicList(HttpServletRequest request,String prefix, String[] field_codes) {
	   for(int i=0;i<field_codes.length;i++){
		   List list = dicCache.getDicListByCode(prefix+"-"+field_codes[i]);
		   request.setAttribute(CamelCaseUtils.toCamelCase(field_codes[i])+"DicList", list);
	   }
   }
   
   
   protected void setDicTextInList(List list,String[] fieldCodes){
	   String regex = "(\\w)+(-)";
	   String cruAttrName;
	   String cruAttrNameText;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < fieldCodes.length; j++) {
				Object obj = list.get(i);
				cruAttrName = CamelCaseUtils.toCamelCase(fieldCodes[j].replaceAll(regex, ""));
				cruAttrNameText = cruAttrName+SYSTEM_STRING_TEXT;
				List<Dictionary> codeList =  dicCache.getDicListByCode(fieldCodes[j]);
				if (codeList == null) {
					// 没查到对应的field_code 将原值写到对应的 _text 属性
					MethodUtil.invokeSet(obj,  cruAttrNameText,
							MethodUtil.invokeGet(list.get(i), cruAttrName));
				} else {
					boolean flag = false;
					for (int k = 0; k < codeList.size(); k++) {
						if (MethodUtil.invokeGet(obj, cruAttrName) != null) {
							if (codeList.get(k).getValue().equals(
									MethodUtil.invokeGet(obj, cruAttrName).toString())) {
								MethodUtil.invokeSet(list.get(i), cruAttrNameText, codeList.get(k).getText());
								flag = true;
								break;
							}
						}
					}
					if (!flag) {//没有匹配的值，则写入原值
						MethodUtil.invokeSet(list.get(i), cruAttrNameText, MethodUtil.invokeGet(obj, cruAttrName));
					}
				}
			}
		}
   }
   
}  
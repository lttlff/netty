package com.zjhcsoft.icrm;

import java.util.HashMap;
import java.util.Map;

import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

public class DynamicFormTest {

	public static void main(String[] args) {

		ClientTestTask task = new ClientTestTask();
		task.start();
	}

}

class ClientTestTask extends Thread {
	public ClientTestTask() {
	}

	public void run() {
		Map<Object,Object> paramMap=new HashMap<Object,Object>();
		paramMap.put("id", "1");
		ResponseContext result =RequestDeployRoute.call(AppConstant.APP1, "formValueEngine", "getFormHtmlById", paramMap);
		if (result != null) {
			System.out.println("from server value is"+(String)result.getResult());
		}
		String formInnerHtml = (String)result.getResult();
		System.out.println(formInnerHtml);
		
	}
}

package com.zjhcsoft.rpc.tcp.nio;

import java.util.HashMap;
import java.util.Map;

import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

public class DefaultTcpNettyNioClientTest {
	public static void main(String[] args) {
		for(int i=0;i<1;i++){
			ClientTestTask task = new ClientTestTask(i);
			task.start();
		}
	}
}

class ClientTestTask extends Thread{
	private int i;
	public ClientTestTask(int i){
		this.i = i;
	}
	public void run(){
		System.out.println("task["+i+"] start ");
		long startTime = System.currentTimeMillis();
		for (int j = 0; j < 5; j++) {
			Map<Object,Object> paramMap=new HashMap<Object,Object>();
			
			paramMap.put("1", "测试_" + j);
			long perStartTime = System.currentTimeMillis();
//			try{
				ResponseContext result =RequestDeployRoute.call(AppConstant.APP1, "user", "getName2", paramMap);
			if (result != null) {
				System.out.println("from server value is"+(String)result.getResult());
			}
			System.out.println("[" + i + "][" + j + "]["+result.getResult()+"] invoke time:"+ (System.currentTimeMillis() - perStartTime));
//			}catch(Throwable e){
//				e.printStackTrace();
//			}
		}
		System.out.println("task["+i+"] end "+(System.currentTimeMillis() - startTime)+"["+((System.currentTimeMillis() - startTime))/10000+"]");
	}
}

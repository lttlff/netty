package com.zjhcsoft.icrm.addr;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.icrm.customer.domain.Addr;
import com.zjhcsoft.icrm.customer.mapper.AddrMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class AddrLoad {

	@Autowired
	private AddrMapper addrMapper;

	
	@Test
	public void loadDataToDB(){

		try {
			File file = new File("D:\\Documents\\Desktop\\jd地址\\zhejiang.txt");
			FileInputStream fi = new FileInputStream(file);
			int x = fi.available();
			byte b[] = new byte[x];
			fi.read(b);
			String zhejaing = new String(b);
			fi.close();
			String[] str = zhejaing.split("\r");
			
			System.out.println(str.length);
			Addr addr = new Addr();
			addr.setCreateDate(new Date());
			addr.setCreatePerson("1");
			
			String addressProvice = "";
			String addressCity = "";
			String parentProvinceId = null;
			String parentCityId = null;
			for(int i=0;i<str.length;i++){
				addr.setRowId(UUIDGenerator.getUUID());
				String[] addrDescs = str[i].split(":");				
				if(addrDescs[0].indexOf("province")>-1){
					addr.setAddrLevel(2);
					addr.setAddrGrade(2);
					addressProvice = addrDescs[1];
					addr.setParentAddrId(null);
					parentProvinceId = addr.getRowId();
				}else if(addrDescs[0].indexOf("city")>-1){
					addr.setAddrLevel(3);
					addr.setAddrGrade(3);
					addressCity =addressProvice+ addrDescs[1] ;
					addr.setAddrFull(addressCity);
					addr.setParentAddrId(parentProvinceId);
					parentCityId = addr.getRowId();
				}else if(addrDescs[0].indexOf("county")>-1){
					addr.setAddrLevel(4);
					addr.setAddrGrade(4);
					addr.setAddrFull(addressCity+addrDescs[1]);
					addr.setParentAddrId(parentCityId);
				}
				addr.setAddress(addrDescs[1]);
				
				addrMapper.insert(addr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
		
	}
	
}

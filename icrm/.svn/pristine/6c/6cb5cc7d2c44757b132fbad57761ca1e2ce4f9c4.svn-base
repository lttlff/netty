package com.zjhcsoft.icrm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CRMAddrLoad {

	private static String filePath = "D:\\Documents\\Desktop\\jd地址\\jd_area.txt";
	
	private void loadFile() throws IOException{
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		int x =fis.available();
		byte b[] = new byte[x];
		fis.read(b);
		
		String addrs = new String(b);
		System.out.println(addrs);
		fis.close();
	}

	
	public static void main(String[] args) throws IOException {
		CRMAddrLoad loader = new CRMAddrLoad();
		loader.loadFile();
		
		
		
	}
}

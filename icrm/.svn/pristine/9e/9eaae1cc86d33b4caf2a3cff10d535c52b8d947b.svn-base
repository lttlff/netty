package com.zjhcsoft.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class ResponseEnvelope<T> implements Serializable{
	private static final long serialVersionUID = -1077987826590987513L;
	private boolean success = true;
	private String message = "ok";
	private String data;
	private long totalSize;
	private boolean state = true;
	private List<T> list;

	public ResponseEnvelope() {
		this.totalSize = 0;
		this.list = new ArrayList<T>();
	}

	public ResponseEnvelope(String message,boolean isSuccess){
		this.message = message;
		this.success = isSuccess;
	}
	
	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}

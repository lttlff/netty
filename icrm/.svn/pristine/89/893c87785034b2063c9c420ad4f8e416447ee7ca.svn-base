package com.zjhcsoft.icrm.dynamic.page.web.annotation;

public enum WebElementType {
	TEXT, TEXTAREA, HIDDEN, NUMBERER, COMBO, DATE, TRIGGER, CHECKBOX, ERRORTYPE;

	public static WebElementType toType(String type) {
		try {
			return valueOf(type.toUpperCase());
		} catch (Exception ex) {
			return ERRORTYPE;
		}
	}

}

package com.cattail.utility;

import java.io.Serializable;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/20 22:49
 * @description 返回数据的代码标记
 */
public enum ResultCodeType implements Serializable {
	/**
	 * 成功
	 */
	ok(200),
	
	/**
	 * 找不到
	 */
	notFound(404);
	
	private int code;
	
	ResultCodeType() {
	}
	
	ResultCodeType(int code) {
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
	
	@Override
	public String toString(){
		return Integer.toString(code);
	}
}

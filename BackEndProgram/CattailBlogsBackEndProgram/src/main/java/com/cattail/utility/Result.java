package com.cattail.utility;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/20 22:54
 * @description 返回数据统一封装
 */
@Data
public class Result implements Serializable {
	
	/**
	 * 返回代码
	 */
	private int code;
	
	/**
	 * 返回消息
	 * notice: 如果要修改返回数据信息，请通过setter进行手动修改即可。集成方法只提供基于enum的默认信息（即网络状态信息和网络状态码）
	 */
	private ResultCodeType msg;
	
	/**
	 * 数据体
	 */
	private Object data;
	
	/**
	 * 成功获得数据的结果封装
	 * @param resultCode ResultCodeType enum类型，表示成功代码
	 * @param data object 数据体
	 * @return result 类型的统一封装结果
	 */
	public static Result succ(ResultCodeType resultCode, Object data){
		Result result = new Result();
		result.setCode(resultCode.getCode());
		result.setData(data);
		result.setMsg(resultCode);
		return result;
	}
	
	/**
	 * 成功获得数据的结果封装
	 * 对succ方法的重载与简化
	 * @param data 数据体
	 * @return result 统一封装结果
	 */
	public static Result succ(Object data){
		return succ(ResultCodeType.ok, data);
	}
	
	/**
	 * 获得数据失败结果封装
	 * @param code ResultCodeType, 结果枚举
	 * @param data 数据体
	 * @return result 类型的统一封装结果
	 */
	public static Result fail(ResultCodeType code, Object data){
		Result result = new Result();
		result.setCode(code.getCode());
		result.setMsg(code);
		result.setData(data);
		return result;
	}
	
	/**
	 * 获得数据失败结果封装
	 * 对fail方法的重载与简化
	 * @param code ResultCodeType, 结果枚举
	 * @return result 类型的统一封装结果
	 */
	public static Result fail(ResultCodeType code){
		return fail(code, null);
	}
}

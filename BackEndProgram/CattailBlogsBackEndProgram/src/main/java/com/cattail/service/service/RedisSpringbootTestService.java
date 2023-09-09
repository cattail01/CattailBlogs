package com.cattail.service.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/9/2 17:25
 * @description service 层集成 redis（手动编写）
 */

public interface RedisSpringbootTestService {
	
	// region generic putter getter
	
	// region put methods
	<T> void put(String key, T value);
	<T> void put(String key, T value, int timeout);
	<T> void put(String key, T value, int timeout, TimeUnit timeUnit);
	// endregion put method
	
	// region get methods
	<T> T get(String key, Class<T> tClass);
//	<E,T extends Collection<E>> T get(String key, Class<E> cls, Class<T> collectionCls);
	// endregion get method
	
	// region put if absent (不存在就放置)
	<T> T putIfAbsent(String key, Class<T> cls, Supplier<T> supplier);
	<T> T putIfAbsent(String key, Class<T> cls, Supplier<T> supplier, int timeout);
//	<E,T extends Collection<E>> T putIfAbsent(String key, Class<E> cls, Class<T> collectionCls, Supplier<T> supplier);
	// endregion put if absent
	
	// endregion generic putter getter
	
	// region Non-generic
	boolean exists(String key);
	boolean del(String key);
	boolean expire(String key, long timeout);
	boolean expire(String key, long timeout, TimeUnit timeUnit);
	
	void put(String key, String value);
	void put(String key, String value, int timeout);
	void put(String key, String value, int timeout, TimeUnit timeUnit);
	
	String get(String key);
	// endregion Non-generic
	
	// region hash
	void pushHash(String key, Map<Object, Object> map);
	Map<Object, Object> getHash(String key);
	// endregion hash
}

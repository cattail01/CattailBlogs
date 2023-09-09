package com.cattail.service.impl;
import cn.hutool.json.JSONUtil;
import com.cattail.service.service.RedisSpringbootTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
/**
 * Redis服务实现类
 */
@Slf4j
@Service
@Component
public class RedisSpringbootTestServiceImpl implements RedisSpringbootTestService {
	/**
	 * Redis数据管理器
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	/**
	 * 将对象存入Redis
	 * @param key 键
	 * @param value 值
	 */
	@Override
	public <T> void put(String key, T value) {
		ValueOperations valueOperations = stringRedisTemplate.opsForValue();
		valueOperations.set(key, JSONUtil.toJsonStr(value));
	}
	/**
	 * 将对象存入Redis，并设置过期时间
	 * @param key 键
	 * @param value 值
	 * @param timeout 过期时间
	 */
	@Override
	public <T> void put(String key, T value, int timeout) {
		put(key, value, timeout, TimeUnit.MINUTES);
	}
	/**
	 * 将对象存入Redis，并设置过期时间和时间单位
	 * @param key 键
	 * @param value 值
	 * @param timeout 过期时间
	 * @param timeUnit 时间单位
	 */
	@Override
	public <T> void put(String key, T value, int timeout, TimeUnit timeUnit) {
		ValueOperations valueOperations = stringRedisTemplate.opsForValue();
		valueOperations.set(key, JSONUtil.toJsonStr(value), timeout, timeUnit);
	}
	/**
	 * 从Redis中获取对象
	 * @param key 键
	 * @param tClass 对象类型
	 * @return 获取到的对象
	 */
	@Override
	public <T> T get(String key, Class<T> tClass) {
		Object value = stringRedisTemplate.opsForValue().get(key);
		String valueJsonString = JSONUtil.toJsonStr(value);
		T bean = JSONUtil.toBean(valueJsonString, tClass);
		return bean;
	}
	/**
	 * 获取对象集合
	 * @param key 键
	 * @param cls 集合元素类型
	 * @param collectionCls 集合类型
	 * @return 获取到的对象集合
	 */
	private <E, T extends Collection<E>> T get(String key, Class<E> cls, Class<T> collectionCls) {
		return null;
	}
	/**
	 * 如果键不存在则存入Redis，并返回存入的对象；如果键已存在，则直接返回已存在的对象
	 * @param key 键
	 * @param cls 对象类型
	 * @param supplier 对象提供者
	 * @return 存入或获取到的对象
	 */
	@Override
	public <T> T putIfAbsent(String key, Class<T> cls, Supplier<T> supplier) {
		T value = get(key, cls);
		if (value != null) {
			return value;
		}
		value = supplier.get();
		if (value == null) {
			return null;
		}
		put(key, value);
		return value;
	}
	/**
	 * 如果键不存在则存入Redis，并返回存入的对象；如果键已存在，则直接返回已存在的对象
	 * @param key 键
	 * @param cls 对象类型
	 * @param supplier 对象提供者
	 * @param timeout 过期时间
	 * @return 存入或获取到的对象
	 */
	@Override
	public <T> T putIfAbsent(String key, Class<T> cls, Supplier<T> supplier, int timeout) {
		T t = get(key, cls);
		if (t != null) {
			return t;
		}
		t = supplier.get();
		if (t == null) {
			return t;
		}
		put(key, t, timeout);
		return t;
	}
	/**
	 * 存入对象集合，如果键不存在则存入Redis，并返回存入的对象集合；如果键已存在，则直接返回已存在的对象集合
	 * @param key 键
	 * @param cls 集合元素类型
	 * @param collectionCls 集合类型
	 * @param supplier 对象集合提供者
	 * @return 存入或获取到的对象集合
	 */
	private <E, T extends Collection<E>> T putIfAbsent(String key, Class<E> cls, Class<T> collectionCls,
	                                                   Supplier<T> supplier) {
		return null;
	}
	/**
	 * 判断键是否存在于Redis中
	 * @param key 键
	 * @return 是否存在
	 */
	@Override
	public boolean exists(String key) {
		return stringRedisTemplate.hasKey(key);
	}
	/**
	 * 删除Redis中的键值对
	 * @param key 键
	 * @return 是否删除成功
	 */
	@Override
	public boolean del(String key) {
		stringRedisTemplate.delete(key);
		return !stringRedisTemplate.hasKey(key);
	}
	/**
	 * 设置键的过期时间
	 * @param key 键
	 * @param timeout 过期时间
	 * @return 是否设置成功
	 */
	@Override
	public boolean expire(String key, long timeout) {
		return stringRedisTemplate.expire(key, timeout, TimeUnit.MINUTES);
	}
	/**
	 * 设置键的过期时间
	 * @param key 键
	 * @param timeout 过期时间
	 * @param timeUnit 时间单位
	 * @return 是否设置成功
	 */
	@Override
	public boolean expire(String key, long timeout, TimeUnit timeUnit) {
		return stringRedisTemplate.expire(key, timeout, timeUnit);
	}
	/**
	 * 将字符串存入Redis
	 * @param key 键
	 * @param value 值
	 */
	@Override
	public void put(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}
	/**
	 * 将字符串存入Redis，并设置过期时间
	 * @param key 键
	 * @param value 值
	 * @param timeout 过期时间
	 */
	@Override
	public void put(String key, String value, int timeout) {
		put(key, value, timeout, TimeUnit.MINUTES);
	}
	/**
	 * 将字符串存入Redis，并设置过期时间和时间单位
	 * @param key 键
	 * @param value 值
	 * @param timeout 过期时间
	 * @param timeUnit 时间单位
	 */
	@Override
	public void put(String key, String value, int timeout, TimeUnit timeUnit) {
		stringRedisTemplate.opsForValue().set(key, value, timeout, timeUnit);
	}
	/**
	 * 从Redis中获取字符串
	 * @param key 键
	 * @return 获取到的字符串
	 */
	@Override
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}
	/**
	 * 将哈希表存入Redis
	 * @param key 键
	 * @param map 哈希表
	 */
	@Override
	public void pushHash(String key, Map<Object, Object> map) {
		stringRedisTemplate.opsForHash().putAll(key, map);
	}
	/**
	 * 从Redis中获取哈希表
	 * @param key 键
	 * @return 获取到的哈希表
	 */
	@Override
	public Map<Object, Object> getHash(String key) {
		try {
			return stringRedisTemplate.opsForHash().entries(key);
		} catch (Exception e) {
			log.debug(e.getStackTrace()[0].toString());
			return null;
		}
	}
}

package testredis;



import cn.hutool.json.JSONUtil;
import com.cattail.BlogApplication;
import com.cattail.service.impl.RedisSpringbootTestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.mockito.Mockito.when;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/9/8 23:45
 * @description RedisSpringbootTestServiceImpl的单元测试类(ai写的，全是bug)
 */
@SpringBootTest(classes = BlogApplication.class)
class RedisSpringbootTestServiceImplTest {
	@Mock
	private StringRedisTemplate stringRedisTemplate;
	@Mock
	private ValueOperations valueOperations;
	@InjectMocks
	private RedisSpringbootTestServiceImpl redisSpringbootTestService;
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	/**
	 * 测试将对象存入Redis
	 */
	@Test
	@DisplayName("测试将对象存入Redis")
	void put() {
		String key = "test_key";
		String value = "test_value";
		redisSpringbootTestService.put(key, value);
		Mockito.verify(valueOperations, Mockito.times(1)).set(key, JSONUtil.toJsonStr(value));
	}
	/**
	 * 测试将对象存入Redis，并设置过期时间
	 */
	@Test
	@DisplayName("测试将对象存入Redis，并设置过期时间")
	void putWithTimeout() {
		String key = "test_key";
		String value = "test_value";
		int timeout = 5;
		TimeUnit timeUnit = TimeUnit.MINUTES;
		redisSpringbootTestService.put(key, value, timeout, timeUnit);
		Mockito.verify(valueOperations, Mockito.times(1)).set(key, JSONUtil.toJsonStr(value), timeout, timeUnit);
	}
	/**
	 * 测试从Redis中获取对象
	 */
	@Test
	@DisplayName("测试从Redis中获取对象")
	void get() {
		String key = "test_key";
		String value = "test_value";
		Class<String> tClass = String.class;
		when(valueOperations.get(key)).thenReturn(value);
		String result = redisSpringbootTestService.get(key, tClass);
		Assertions.assertEquals(value, result);
	}
	/**
	 * 测试如果键不存在则存入Redis，并返回存入的对象；如果键已存在，则直接返回已存在的对象
	 */
	@Test
	@DisplayName("测试如果键不存在则存入Redis，并返回存入的对象；如果键已存在，则直接返回已存在的对象")
	void putIfAbsent() {
		String key = "test_key";
		String value = "test_value";
		Class<String> cls = String.class;
		Supplier<String> supplier = () -> "new_value";
		when(valueOperations.get(key)).thenReturn(null).thenReturn(value);
		String result = redisSpringbootTestService.putIfAbsent(key, cls, supplier);
		Assertions.assertEquals("new_value", result);
		result = redisSpringbootTestService.putIfAbsent(key, cls, supplier);
		Assertions.assertEquals(value, result);
	}
	/**
	 * 测试如果键不存在则存入Redis，并返回存入的对象；如果键已存在，则直接返回已存在的对象，并设置过期时间
	 */
	@Test
	@DisplayName("测试如果键不存在则存入Redis，并返回存入的对象；如果键已存在，则直接返回已存在的对象，并设置过期时间")
	void putIfAbsentWithTimeout() {
		String key = "test_key";
		String value = "test_value";
		Class<String> cls = String.class;
		Supplier<String> supplier = () -> "new_value";
		int timeout = 5;
		when(valueOperations.get(key)).thenReturn(null).thenReturn(value);
		String result = redisSpringbootTestService.putIfAbsent(key, cls, supplier, timeout);
		Assertions.assertEquals("new_value", result);
		result = redisSpringbootTestService.putIfAbsent(key, cls, supplier, timeout);
		Assertions.assertEquals(value, result);
	}
	/**
	 * 测试判断键是否存在于Redis中
	 */
	@Test
	@DisplayName("测试判断键是否存在于Redis中")
	void exists() {
		String key = "test_key";
		when(stringRedisTemplate.hasKey(key)).thenReturn(true);
		boolean result = redisSpringbootTestService.exists(key);
		Assertions.assertTrue(result);
	}
	/**
	 * 测试删除Redis中的键值对
	 */
	@Test
	@DisplayName("测试删除Redis中的键值对")
	void del() {
		String key = "test_key";
		when(stringRedisTemplate.hasKey(key)).thenReturn(false);
		boolean result = redisSpringbootTestService.del(key);
		Assertions.assertTrue(result);
	}
	/**
	 * 测试设置键的过期时间
	 */
	@Test
	@DisplayName("测试设置键的过期时间")
	void expire() {
		String key = "test_key";
		long timeout = 5;
		when(stringRedisTemplate.expire(key, timeout, TimeUnit.MINUTES)).thenReturn(true);
		boolean result = redisSpringbootTestService.expire(key, timeout);
		Assertions.assertTrue(result);
	}
	/**
	 * 测试设置键的过期时间
	 */
	@Test
	@DisplayName("测试设置键的过期时间")
	void expireWithTimeUnit() {
		String key = "test_key";
		long timeout = 5;
		TimeUnit timeUnit = TimeUnit.MINUTES;
		when(stringRedisTemplate.expire(key, timeout, timeUnit)).thenReturn(true);
		boolean result = redisSpringbootTestService.expire(key, timeout, timeUnit);
		Assertions.assertTrue(result);
	}
	/**
	 * 测试将字符串存入Redis
	 */
	@Test
	@DisplayName("测试将字符串存入Redis")
	void putString() {
		String key = "test_key";
		String value = "test_value";
		redisSpringbootTestService.put(key, value);
		Mockito.verify(stringRedisTemplate.opsForValue(), Mockito.times(1)).set(key, value);
	}
	/**
	 * 测试将字符串存入Redis，并设置过期时间
	 */
	@Test
	@DisplayName("测试将字符串存入Redis，并设置过期时间")
	void putStringWithTimeout() {
		String key = "test_key";
		String value = "test_value";
		int timeout = 5;
		TimeUnit timeUnit = TimeUnit.MINUTES;
		redisSpringbootTestService.put(key, value, timeout, timeUnit);
		Mockito.verify(stringRedisTemplate.opsForValue(), Mockito.times(1)).set(key, value, timeout, timeUnit);
	}
	/**
	 * 测试从Redis中获取字符串
	 */
	@Test
	@DisplayName("测试从Redis中获取字符串")
	void getString() {
		String key = "test_key";
		String value = "test_value";
		when(stringRedisTemplate.opsForValue().get(key)).thenReturn(value);
		String result = redisSpringbootTestService.get(key);
		Assertions.assertEquals(value, result);
	}
	/**
	 * 测试将哈希表存入Redis
	 */
	@Test
	@DisplayName("测试将哈希表存入Redis")
	void pushHash() {
		String key = "test_key";
		Map<Object, Object> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		redisSpringbootTestService.pushHash(key, map);
		Mockito.verify(stringRedisTemplate.opsForHash(), Mockito.times(1)).putAll(key, map);
	}
	/**
	 * 测试从Redis中获取哈希表
	 */
	@Test
	@DisplayName("测试从Redis中获取哈希表")
	void getHash() {
		String key = "test_key";
		Map<Object, Object> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		when(stringRedisTemplate.opsForHash().entries(key)).thenReturn(map);
		Map<Object, Object> result = redisSpringbootTestService.getHash(key);
		Assertions.assertEquals(map, result);
	}
}
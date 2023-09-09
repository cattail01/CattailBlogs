package testredis;

import com.cattail.BlogApplication;
import com.cattail.service.service.RedisSpringbootTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/9/2 12:53
 * @description
 */

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class RedisSpringbootTest {
	
	@Autowired
	RedisSpringbootTestService redisSpringbootTestService;
	
	@Test
	public void testConnectToRedis(){
		redisSpringbootTestService.put("mykey", "hello redis!");
		System.out.println(redisSpringbootTestService.get("mykey"));
		redisSpringbootTestService.del("mykey");
		
	}

}

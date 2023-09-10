package testspringboot;

import com.cattail.BlogApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/9/2 17:42
 * @description
 */
@SpringBootTest(classes = BlogApplication.class)
public class SpringBootTestTest {
	@Test
	public void testSpringBootTest(){
		System.out.println("hello world spring boot test!");
		
	}
}

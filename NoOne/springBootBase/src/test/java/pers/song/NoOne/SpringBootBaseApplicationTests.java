package pers.song.NoOne;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class SpringBootBaseApplicationTests {

	@Autowired
	private StringEncryptor encryptor;
	@Test
	public void encry(){
		String username = encryptor.encrypt("root");
		System.out.println(username);        //加密123
		String password = encryptor.encrypt("Abcd123!");
		System.out.println(password);
	}

}

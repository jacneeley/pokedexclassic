package local.classicdex.pokedexclassic.app;

import java.nio.charset.Charset;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class AppKey {
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String generateKey() {
		byte[] array = new byte[10];
		new Random().nextBytes(array);
		return new String(array, Charset.forName("UTF-8"));
	}
}
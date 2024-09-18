package local.classicdex.pokedexclassic.app;

import org.springframework.stereotype.Component;

@Component
public class AppConstants {
	private final String pwd = "papyo";

	public String getPwd() {
		return pwd;
	}
}

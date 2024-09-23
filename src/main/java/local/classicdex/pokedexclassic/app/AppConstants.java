package local.classicdex.pokedexclassic.app;

import org.springframework.stereotype.Component;

@Component
public class AppConstants {
	private final String pwd = "";
	private final String ver = "Ver. 0.5";

	public String getPwd() {
		return pwd;
	}

	public String getVer() {
		return ver;
	}
}
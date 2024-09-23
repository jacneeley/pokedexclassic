package local.classicdex.pokedexclassic.app;

import org.springframework.stereotype.Component;

@Component
public class AppConstants {
	private final String pwd = "";
	private final String ver = "Ver. 0.5";
	private final String pokeballPath = "https://raw.githubusercontent.com/jacneeley/gen1spriterepo/refs/heads/main/misc/pokeball.png";

	public String getPwd() {
		return pwd;
	}

	public String getVer() {
		return ver;
	}

	public String getPokeballPath() {
		return pokeballPath;
	}
}
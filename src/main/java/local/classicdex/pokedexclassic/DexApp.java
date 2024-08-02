package local.classicdex.pokedexclassic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import local.classicdex.pokedexclassic.pokemon.Pokemon;

@SpringBootApplication
public class DexApp {

	private static final Logger log = LoggerFactory.getLogger(DexApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DexApp.class, args);
		log.info("App Startup Successful");
	}
	
	@Bean
	CommandLineRunner runline() {//test pokemon object
		List<String> pokemonType = new ArrayList<String>();
		pokemonType.add("electric");
		return args -> {
			Pokemon pokemon = new Pokemon(999, "Pikachu", "Mouse", pokemonType, "0ft 0inches", 12, "An electric rat.");
			log.info("Pokemon: " + pokemon.toString());
		};
	}
}

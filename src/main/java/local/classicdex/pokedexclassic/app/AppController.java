package local.classicdex.pokedexclassic.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import local.classicdex.pokedexclassic.pokemon.PokemonController;
import local.classicdex.pokedexclassic.pokemon.PokemonResponse;
import local.classicdex.pokedexclassic.pokemon.exceptions.InternalErrorException;

@Controller
@RequestMapping("/")
public class AppController {
	
	private final Logger log = LoggerFactory.getLogger(AppController.class);
	private final PokemonController _pokeController;
	private final AppConstants _const;
	private final AppKey _key;
	
	public AppController (PokemonController pokeController, AppConstants _const, AppKey key) {
		this._pokeController = pokeController;
		this._const = _const;
		this._key = key;
	}
	
	@GetMapping("/")
	public String viewAllPokemon(Model model) {
		ResponseEntity<List<PokemonResponse>> response = _pokeController.findAll();
		model.addAttribute("allPokemon", response.getBody());
		return "index";
	}
	
	@GetMapping("/{Id}")
	public String viewPokemon(Model model, @PathVariable Integer Id) {
		ResponseEntity<PokemonResponse> response = _pokeController.getPokemon(Id);
		model.addAttribute("pokemon",response.getBody());
		return "pokemon";
	}
	
	@GetMapping("/about")
	public String viewAbout() {
		return "about";
	}
	
	@GetMapping("/key/{pwd}")
	public ResponseEntity<AppKey> getKey(@PathVariable String pwd){
		if(!pwd.equals(_const.getPwd())) {
			log.warn(pwd + " is incorrect.");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		String key = _key.getKey();
		if(key.isEmpty()) {
			log.error("key is null.");
			throw new InternalErrorException("Key is null...");
		}
		return ResponseEntity.ok(_key);
	}
}

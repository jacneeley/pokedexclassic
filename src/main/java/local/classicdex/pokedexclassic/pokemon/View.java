package local.classicdex.pokedexclassic.pokemon;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class View {
	
	private final PokemonController _pokeController;
	
	public View (PokemonController pokeController) {
		this._pokeController = pokeController;
	}
	
	@GetMapping("/")
	public String viewAllPokemon(Model model) {
		ResponseEntity<List<PokemonResponse>> response = _pokeController.findAll();
		model.addAttribute("allPokemon",response.getBody());
		return "index";
	}
	
	@GetMapping("/{Id}")
	public String viewPokemon(Model model, @PathVariable Integer Id) {
		ResponseEntity<PokeResponse> response = _pokeController.getPokemon(Id);
		model.addAttribute("pokemon",response.getBody());
		return "pokemon";
	}
}

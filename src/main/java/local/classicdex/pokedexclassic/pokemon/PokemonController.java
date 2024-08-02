package local.classicdex.pokedexclassic.pokemon;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import local.classicdex.pokedexclassic.DexApp;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
	
	private static final Logger log = LoggerFactory.getLogger(DexApp.class);
	
	private final PokemonService _pokeSrv;
	
	public PokemonController(PokemonService pokeSrv) {
		this._pokeSrv = pokeSrv;
	}
	
	@GetMapping("")
	public ResponseEntity<List<PokemonResponse>> findAll(){
		try {
			List<Pokemon> getAllPokemon = _pokeSrv.GetAllPokemon();
			List<PokemonResponse> result = new ArrayList<PokemonResponse>();
			
			if(getAllPokemon.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			for (var pokemon : getAllPokemon) {
				PokemonResponse response = new PokemonResponse(
						pokemon.getId(),
						pokemon.getName(),
						pokemon.getSpecies(),
						pokemon.getPokemonType(),
						pokemon.getHeight(),
						pokemon.getWeight(),
						pokemon.getDesc());
				result.add(response);
			}
			return new ResponseEntity<List<PokemonResponse>>(result, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<PokemonResponse> getPokemon(@PathVariable Integer Id) {
		Pokemon pokemon = _pokeSrv.GetPokemon(Id);
		
		if(!_pokeSrv.pokemonExists(Id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		PokemonResponse response = new PokemonResponse(
				pokemon.getId(),
				pokemon.getName(),
				pokemon.getSpecies(),
				pokemon.getPokemonType(),
				pokemon.getHeight(),
				pokemon.getWeight(),
				pokemon.getDesc());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public ResponseEntity createPokemon(@Valid @RequestBody PokemonRequest request) {
		try {
			Pokemon pokemon = new Pokemon(
					request.id(),
					request.name(),
					request.species(),
					request.pokemonType(),
					request.height(),
					request.weight(),
					request.desc() );
			_pokeSrv.CreatePokemon(pokemon);
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			log.info("error: " + e.toString());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("")
	public ResponseEntity upsertPokemon(@Valid @RequestBody PokemonRequest request) {
		try {
			Pokemon pokemon = new Pokemon(
					request.id(),
					request.name(),
					request.species(),
					request.pokemonType(),
					request.height(),
					request.weight(),
					request.desc() );
			
			if(_pokeSrv.pokemonExists(pokemon.getId())) {
				return createPokemon(request);
			}
			else {
				_pokeSrv.UpsertPokemon(pokemon);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			log.info("error: " + e.toString());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{Id}")
	public ResponseEntity deletePokemon(@PathVariable Integer Id) {
		if(_pokeSrv.pokemonExists(Id)) {
			_pokeSrv.DeletePokemon(Id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
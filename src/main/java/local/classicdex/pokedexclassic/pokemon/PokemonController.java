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
import local.classicdex.pokedexclassic.pokemon.exceptions.InternalErrorException;
import local.classicdex.pokedexclassic.pokemon.exceptions.NoDataFoundException;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
	
	private static final Logger log = LoggerFactory.getLogger(DexApp.class);
	
	private final PokemonService _pokeSrv;
	
	public PokemonController(PokemonService pokeSrv) {
		this._pokeSrv = pokeSrv;
	}
	
	@GetMapping("/")
	public List<PokemonResponse> findAll(){
		try {
			
			List<Pokemon> getAllPokemon = _pokeSrv.GetAllPokemon();
			List<PokemonResponse> result = new ArrayList<PokemonResponse>();
			
			if(getAllPokemon.isEmpty()){
				throw new NoDataFoundException();
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
			return ResponseEntity.ok(result).getBody();
		} catch(Exception e) {
			throw new NoDataFoundException();
		}
	}
	
	@GetMapping("/{Id}")
	public PokemonResponse getPokemon(@PathVariable Integer Id) {
		Pokemon pokemon = _pokeSrv.GetPokemon(Id);
		
		if(!_pokeSrv.PokemonExists(Id)) {
			throw new NoDataFoundException();
		}
		
		PokemonResponse response = new PokemonResponse(
				pokemon.getId(),
				pokemon.getName(),
				pokemon.getSpecies(),
				pokemon.getPokemonType(),
				pokemon.getHeight(),
				pokemon.getWeight(),
				pokemon.getDesc());
		
		return ResponseEntity.ok(response).getBody();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public ResponseEntity<Object> createPokemon(@Valid @RequestBody PokemonRequest request) {
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
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("")
	public ResponseEntity<Object> upsertPokemon(@Valid @RequestBody PokemonRequest request) {
		try {
			Pokemon pokemon = new Pokemon(
					request.id(),
					request.name(),
					request.species(),
					request.pokemonType(),
					request.height(),
					request.weight(),
					request.desc() );
			
			if(_pokeSrv.PokemonExists(pokemon.getId())) {
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
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{Id}")
	public ResponseEntity<Object> deletePokemon(@PathVariable Integer Id) {
		if(_pokeSrv.PokemonExists(Id)) {
			_pokeSrv.DeletePokemon(Id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		throw new NoDataFoundException();
	}
}
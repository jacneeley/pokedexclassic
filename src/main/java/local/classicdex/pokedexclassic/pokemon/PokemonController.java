package local.classicdex.pokedexclassic.pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import local.classicdex.pokedexclassic.app.AppKey;
import local.classicdex.pokedexclassic.pokemon.exceptions.InternalErrorException;
import local.classicdex.pokedexclassic.pokemon.exceptions.NoDataFoundException;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
	
	private static final Logger log = LoggerFactory.getLogger(DexApp.class);
	
	private final PokemonService _pokeSrv;
	private final AppKey _appKey;
	
	public PokemonController(PokemonService pokeSrv, AppKey appKey) {
		this._pokeSrv = pokeSrv;
		this._appKey = appKey;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PokemonResponse>> findAll(){
		List<Pokemon> getAllPokemon = _pokeSrv.GetAllPokemon();
		List<PokemonResponse> result = new ArrayList<PokemonResponse>();
		
		if(getAllPokemon.isEmpty()){
			throw new NoDataFoundException("Response Empty");
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
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<PokemonResponse> getPokemon(@PathVariable Integer id) {
		Optional<Pokemon> pokemon = _pokeSrv.GetPokemon(id);
		
		if(!_pokeSrv.PokemonExists(id)) {
			throw new NoDataFoundException(id.toString());
		}
		
		PokemonResponse response = new PokemonResponse(
				pokemon.get().getId(),
				pokemon.get().getName(),
				pokemon.get().getSpecies(),
				pokemon.get().getPokemonType(),
				pokemon.get().getHeight(),
				pokemon.get().getWeight(),
				pokemon.get().getDesc());
		
		return ResponseEntity.ok(response);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/{key}")
	public ResponseEntity<Object> createPokemon(@PathVariable String key, @Valid @RequestBody PokemonRequest request) {
		if(!key.equals(_appKey.getKey())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN); 
		}
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
			throw new InternalErrorException("Pokemon could not be created. Something went wrong...");
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{key}")
	public ResponseEntity<Object> upsertPokemon(@PathVariable String key, @Valid @RequestBody PokemonRequest request) {
		if(!key.equals(_appKey.getKey())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN); 
		}
		try {
			Pokemon pokemon = new Pokemon(
					request.id(),
					request.name(),
					request.species(),
					request.pokemonType(),
					request.height(),
					request.weight(),
					request.desc() );
			
			if(!_pokeSrv.PokemonExists(pokemon.getId())) {
				return createPokemon(key, request);
			}
			_pokeSrv.UpsertPokemon(pokemon);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.info("error: " + e.toString());
			throw new InternalErrorException("Pokemon could not be updated or created. Something went wrong...");
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{key}/{id}")
	public ResponseEntity<Object> deletePokemon(@PathVariable String key, @PathVariable Integer id) {
		if(!key.equals(_appKey.getKey())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN); 
		}
		if(_pokeSrv.PokemonExists(id)) {
			_pokeSrv.DeletePokemon(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		throw new NoDataFoundException(id.toString());
	}
}
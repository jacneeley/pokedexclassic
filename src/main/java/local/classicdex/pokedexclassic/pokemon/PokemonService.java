package local.classicdex.pokedexclassic.pokemon;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import local.classicdex.pokedexclassic.app.AppKey;

@Service
public class PokemonService implements IPokemonSrv{
	
	private final Logger log = LoggerFactory.getLogger(PokemonService.class);
	private final PokemonRepo _pokeRepo;
	private final AppKey _key;
	
	public PokemonService(PokemonRepo pokeRepo, AppKey key) {
		this._pokeRepo = pokeRepo;
		this._key = key;
	}
	
	@Override
	public List<Pokemon> GetAllPokemon() {
			return _pokeRepo.GetAllPokemon();
	}
	
//	@Override
//	public List<Pokemon> FindAllByType() {
//		List<Pokemon> pokemon = new ArrayList<Pokemon>(_pokeRepo.values());
//		return pokemon;
//	}

	@Override
	public Optional<Pokemon> GetPokemon(Integer Id) {
		return _pokeRepo.FindById(Id);
	}

	@Override
	public void CreatePokemon(Pokemon pokemon) {
		_pokeRepo.CreatePokemon(pokemon);
	}

	@Override
	public void DeletePokemon(Integer Id) {
		_pokeRepo.DeletePokemon(Id);
	}

	@Override
	public void UpsertPokemon(Pokemon pokemon) {
		_pokeRepo.UpdatePokemon(pokemon, pokemon.getId());
	}

	@Override
	public boolean PokemonExists(Integer Id) {
		return _pokeRepo.FindById(Id).isPresent();
	}
	
	@PostConstruct
	private void init() {
		_key.setKey(_key.generateKey());
		log.info("key: " + _key.getKey());
	}
}

package local.classicdex.pokedexclassic.pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import local.classicdex.pokedexclassic.DexApp;

@Service
public class PokemonService implements IPokemonSrv{
	
	private final Logger log = LoggerFactory.getLogger(PokemonService.class);
	private final PokemonRepo _pokeRepo;
	
	public PokemonService(PokemonRepo pokeRepo) {
		this._pokeRepo = pokeRepo;
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
		return _pokeRepo.FindById(Id).get().getId().equals(Id);
	}
	
//	@PostConstruct
//	private void init() {
//		boolean noData = this.GetAllPokemon().isEmpty();
//		if(noData) {
//			List<String> pokemonTypes = new ArrayList<String>();
//			pokemonTypes.add("electric");
//			Pokemon pokemon = new Pokemon(998, "Pikachu", "Mouse", pokemonTypes, 5, 12, "An electric rat.");
//			this.CreatePokemon(pokemon);
//		}
//		else {
//			log.info("Data exists. Skipping Post Construct.");
//		}
//	}
}

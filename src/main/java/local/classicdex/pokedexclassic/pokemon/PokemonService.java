package local.classicdex.pokedexclassic.pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PokemonService implements IPokemonSrv{
	
	private static HashMap<Integer, Pokemon> _pokemon = new HashMap<Integer, Pokemon>();
	
	private final PokemonRepo _pokeRepo;
	
	public PokemonService(PokemonRepo pokeRepo) {
		this._pokeRepo = pokeRepo;
	}
	
	@Override
	public List<Pokemon> GetAllPokemon() {
			return _pokeRepo.GetAllPokemon();
	}
	
	@Override
	public List<Pokemon> FindAllByType() {
		List<Pokemon> pokemon = new ArrayList<Pokemon>(_pokemon.values());
		return pokemon;
	}

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
}

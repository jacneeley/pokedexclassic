package local.classicdex.pokedexclassic.pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PokemonService implements IPokemonSrv{
	
	private static HashMap<Integer, Pokemon> _pokemon = new HashMap<Integer, Pokemon>();
	
	@Override
	public List<Pokemon> GetAllPokemon() {
		// TODO Auto-generated method stub
		List<Pokemon> pokemons = new ArrayList<Pokemon>(_pokemon.values());
		return pokemons;
	}

	@Override
	public Pokemon GetPokemon(Integer Id) {
		return _pokemon.get(Id);
	}

	@Override
	public void CreatePokemon(Pokemon pokemon) {
		_pokemon.put(pokemon.getId(), pokemon);
	}

	@Override
	public void DeletePokemon(Integer Id) {
		_pokemon.remove(Id);
	}

	@Override
	public void UpsertPokemon(Pokemon pokemon) {
		_pokemon.put(pokemon.getId(), pokemon);
	}

	@Override
	public boolean pokemonExists(Integer Id) {
		return _pokemon.containsKey(Id);
	}
}

package local.classicdex.pokedexclassic.pokemon;

import java.util.List;
import java.util.Optional;

public interface IPokemonRepo {
	public List<Pokemon>GetAllPokemon();
	public List<Pokemon>FindAllByType();
	public Optional<Pokemon> FindById(Integer Id);
	void CreatePokemon(Pokemon pokemon);
	void UpdatePokemon(Pokemon pokemon, Integer Id);
	void DeletePokemon(Integer Id);
}
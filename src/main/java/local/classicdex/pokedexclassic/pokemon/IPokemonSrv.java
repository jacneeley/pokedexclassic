package local.classicdex.pokedexclassic.pokemon;

import java.util.List;
import java.util.Optional;

public interface IPokemonSrv {
	public List<Pokemon>GetAllPokemon();
	public List<Pokemon>FindAllByType();
	public Optional<Pokemon> GetPokemon(Integer Id);
	void CreatePokemon(Pokemon pokemon);
	void UpsertPokemon(Pokemon pokemon);
	void DeletePokemon(Integer Id);
	boolean PokemonExists(Integer Id);
}
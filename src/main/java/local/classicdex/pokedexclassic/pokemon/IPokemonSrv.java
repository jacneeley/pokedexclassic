package local.classicdex.pokedexclassic.pokemon;

import java.util.List;

public interface IPokemonSrv {
	public List<Pokemon> GetAllPokemon();
	public Pokemon GetPokemon(Integer Id);
	void CreatePokemon(Pokemon pokemon);
	void UpsertPokemon(Pokemon pokemon);
	void DeletePokemon(Integer Id);
	boolean pokemonExists(Integer Id);
}
package local.classicdex.pokedexclassic.pokemon;

import java.util.List;

public interface IPokemonRepo {
	public List<Pokemon>GetAllPokemon();
	public List<Pokemon>FindAllByType();
	public Pokemon FindById(Integer Id);
	void CreatePokemon(Pokemon pokemon);
	void UpsertPokemon(Pokemon pokemon);
	void DeletePokemon(Integer Id);
	boolean PokemonExists(Integer Id);
}
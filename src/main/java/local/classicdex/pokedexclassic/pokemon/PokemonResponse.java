package local.classicdex.pokedexclassic.pokemon;

import java.util.List;

public record PokemonResponse(
		Integer Id,
		String Name,
		String Species,
		List<String> PokemonType,
		String Height,
		double Weight,
		String Desc
		) {}
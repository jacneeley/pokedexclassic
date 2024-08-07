package local.classicdex.pokedexclassic.pokemon;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record PokemonRequest(
		@NotEmpty
		Integer id,
		@NotEmpty
		String name,
		String species,
		List<String> pokemonType,
		double height,
		@Positive
		double weight,
		String desc) {
			public PokemonRequest {
				if(id.equals(0) || id == null){
					throw new IllegalArgumentException("'Pokemon Id' must be provided");
				}
				if(name.isEmpty()){
					throw new IllegalArgumentException("'Name' cannot be empty");
				}
				if(weight < 0) {
					throw new IllegalArgumentException("'Weight' cannot be negative");
				}
			}
	}

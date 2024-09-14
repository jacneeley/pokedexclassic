package local.classicdex.pokedexclassic.pokemon;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record PokemonRequest(
		@NotEmpty
		Integer id,
		@NotEmpty
		String name,
		@NotEmpty
		String species,
		@NotEmpty
		List<String> pokemonType,
		@Positive
		double height,
		@Positive
		double weight,
		String desc) {
			public PokemonRequest {
				if(id.equals(0) || id == null){
					throw new IllegalArgumentException("'Pokemon Id' must be provided");
				}
				else if (id > 999) {
					throw new IllegalArgumentException("'Pokemon Id' is invalid");
				}
				if(name.isEmpty()){
					throw new IllegalArgumentException("'Name' cannot be empty");
				}
				if(species.isBlank()) {
					throw new IllegalArgumentException("'species' cannot be empty");
				}
				if(pokemonType.isEmpty()) {
					throw new IllegalArgumentException("'pokemonType' cannot be empty");
				}
				if(height < 0) {
					throw new IllegalArgumentException("'height' cannot be negative");
				}
				if(weight < 0) {
					throw new IllegalArgumentException("'Weight' cannot be negative");
				}
			}
	}

package local.classicdex.pokedexclassic.pokemon;

import java.util.List;

public record PokeResponse(
	String Id,
	String Name,
	String Species,
	List<String> PokemonType,
	String Height,
	double Weight,
	String Desc
	) {
	
	public String getImg() {
		String id = Id.split("NO.")[1].strip();
		return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + Integer.valueOf(id) + ".png";
	}
}
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
		String id = this.Id.split("NO.")[1].strip();
		return "assets/pokesprites/" + Integer.valueOf(id) + ".png";
	}
	
	public String[] parseDesc() {
		return this.Desc.split("\n\n");
	}
}
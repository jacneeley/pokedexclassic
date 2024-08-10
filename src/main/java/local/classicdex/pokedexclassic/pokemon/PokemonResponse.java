package local.classicdex.pokedexclassic.pokemon;

import java.util.List;

public record PokemonResponse(
	int Id,
	String Name,
	String Species,
	List<String> PokemonType,
	double Height,
	double Weight,
	String Desc
	) {
	
	public String formatId() {
		if(Id > 999) {
			throw new IllegalArgumentException("Id is out of bounds...");
		}
		else if(Id < 100){
			if(Id >= 10) {
				return "NO. 0" + Integer.toString(Id);
			}
			else {
				return "NO. 00" + Integer.toString(Id);
			}
		}
		else if (Id > 99) {
			return "NO. " + Integer.toString(Id);
		}
		else {
			throw new IllegalArgumentException("Bad Request...");
		}
	}
	
	public String getImg() {
		return "assets/pokesprites/" + Integer.valueOf(Id) + ".png";
	}
	
	public String[] parseDesc() {
		return this.Desc.split("\n\n");
	}

	public double getWeightLbs(){
		return Math.round(this.Weight * .22046226);	
	}
	
	public double getHeightFt() {
		return Math.round(this.Height * .328);
	}
	
	public boolean isFirstOne() {
		return Integer.valueOf(Id).equals(1);
	}
	
	public boolean isLastOne() {
		return Integer.valueOf(Id).equals(151);
	}
}
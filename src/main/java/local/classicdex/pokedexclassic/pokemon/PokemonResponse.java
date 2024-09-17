package local.classicdex.pokedexclassic.pokemon;

import java.text.DecimalFormat;
import java.util.List;

public record PokemonResponse(
	int Id,
	String Name,
	String Species,
	String[] PokemonType,
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

	public double getWeightMetric(){
		return this.Weight * .100;	
	}
	
	public double getHeightMetric() {
		DecimalFormat df = new DecimalFormat("####.#");
		return Double.valueOf(df.format(this.Height * .100));
	}
	
	public boolean isFirstOne() {
		return Integer.valueOf(Id).equals(1);
	}
	
	public boolean isLastOne() {
		return Integer.valueOf(Id).equals(151);
	}
}
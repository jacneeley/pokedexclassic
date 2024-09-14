package local.classicdex.pokedexclassic.pokemon;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;


public class Pokemon {
	private Integer Id;
	private String Name;
	private String Species;
	private List<String> PokemonType;
	private double Height;
	private double Weight;
	private String Desc;
	
	public Integer getId() {
		return Id;
	}
	
	public String getName() {
		return Name;
	}
	
	public String getSpecies() {
		return Species;
	}
	
	public List<String> getPokemonType() {
		return PokemonType;
	}
	
	public double getHeight() {
		return Height;
	}
	
	public double getWeight() {
		return Weight;
	}
	
	public String getDesc() {
		return Desc;
	}
	
	public Pokemon(
			Integer id, 
			String name, 
			String species, 
			List<String> pokemonType, 
			double height, 
			double weight,
			String desc) 
	{
		super();
		Id = id;
		Name = name;
		Species = species;
		PokemonType = pokemonType;
		Height = height;
		Weight = weight;
		Desc = desc;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(Desc, Height, Id, Name, PokemonType, Species, Weight);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return Objects.equals(Desc, other.Desc) && Objects.equals(Height, other.Height) && Objects.equals(Id, other.Id)
				&& Objects.equals(Name, other.Name) && Objects.equals(PokemonType, other.PokemonType)
				&& Objects.equals(Species, other.Species)
				&& Double.doubleToLongBits(Weight) == Double.doubleToLongBits(other.Weight);
	}
	@Override
	public String toString() {
		return "Pokemon [Id=" + Id + ", Name=" + Name + ", Species=" + Species + ", PokemonType=" + PokemonType
				+ ", Height=" + Height + ", Weight=" + Weight + ", Desc=" + Desc + "]";
	}
	
	public String listToString() {
		String types = String.join(",", PokemonType);
		return types;
	}
}
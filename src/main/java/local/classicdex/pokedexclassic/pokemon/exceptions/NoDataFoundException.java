package local.classicdex.pokedexclassic.pokemon.exceptions;

@SuppressWarnings("serial")
public class NoDataFoundException extends RuntimeException{
	public NoDataFoundException() {
		super("No Data Found...");
	}
}
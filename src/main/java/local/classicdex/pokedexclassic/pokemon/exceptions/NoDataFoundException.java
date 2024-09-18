package local.classicdex.pokedexclassic.pokemon.exceptions;

@SuppressWarnings("serial")
public class NoDataFoundException extends RuntimeException{
	public NoDataFoundException(String msg) {
		super(msg + " - No Data Found...");
	}
}
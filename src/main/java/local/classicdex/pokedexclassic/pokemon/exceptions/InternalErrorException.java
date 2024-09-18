package local.classicdex.pokedexclassic.pokemon.exceptions;

@SuppressWarnings("serial")
public class InternalErrorException extends RuntimeException{
	public InternalErrorException(String msg) {
		super("INTERNAL SERVER ERROR: " + msg);
	}
}
package local.classicdex.pokedexclassic.pokemon.exceptions;

@SuppressWarnings("serial")
public class InternalErrorException extends RuntimeException{
	public InternalErrorException() {
		super("INTERNAL SERVER ERROR");
	}
}
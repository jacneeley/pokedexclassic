package local.classicdex.pokedexclassic.pokemon;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class PokemonRepo implements IPokemonRepo{
	
	private final JdbcClient _jdbcClient;
	
	public PokemonRepo(JdbcClient jdbcClient) {//db auto configured
		this._jdbcClient = jdbcClient;
	}

	@Override
	public List<Pokemon> GetAllPokemon() {
		  
		return _jdbcClient.sql("SELECT * FROM POKEMON")
				.query(Pokemon.class).list();
	}

	@Override
	public List<Pokemon> FindAllByType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Pokemon> FindById(Integer Id) {
		return _jdbcClient.sql("SELECT id, name, species, pokemon_type, height, weight, desc FROM POKEMON WHERE id = :Id")
				.param("Id", Id)
				.query(Pokemon.class)
				.optional();
	}

	@Override
	public void CreatePokemon(Pokemon pokemon) {
		var created = _jdbcClient.sql("INSERT INTO POKEMON("
				+ "id,"
				+ "name,"
				+ "species,"
				+ "pokemon_type,"
				+ "height,"
				+ "weight,"
				+ "desc"
				+ ") "
				+ "values(?,?,?,?,?,?,?)")
				.params(List.of(pokemon.getId(), pokemon.getName(), pokemon.getSpecies(), pokemon.listToString(), pokemon.getHeight(), pokemon.getWeight(), pokemon.getDesc()))
				.update();
		Assert.state(created == 1, "Failed to create: " + pokemon.toString());
	}

	@Override
	public void UpdatePokemon(Pokemon pokemon, Integer Id) {
		var updated = _jdbcClient.sql("UPDATE POKEMON SET "
				+ "name=?,"
				+ "species=?,"
				+ "pokemon_type=?,"
				+ "height=?,"
				+ "weight=?,"
				+ "desc=? where id=?")
				.params(List.of(pokemon.getName(), pokemon.getSpecies(), pokemon.listToString(),pokemon.getHeight(), pokemon.getWeight(), pokemon.getDesc(), Id))
				.update();
		Assert.state(updated == 1, "Failed to update: " + pokemon.toString());
		
	}

	@Override
	public void DeletePokemon(Integer Id) {
		var deleted = _jdbcClient.sql("delete from POKEMON where id =:Id")
				.param("Id", Id)
				.update();
		Assert.state(deleted == 1, "Failed to delete @ Pokemon Id= " + Integer.toString(Id));
	}
}

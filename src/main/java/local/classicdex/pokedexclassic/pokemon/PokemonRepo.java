//package local.classicdex.pokedexclassic.pokemon;
//
//import java.util.List;
//
//import org.springframework.jdbc.core.simple.JdbcClient;
//
//public class PokemonRepo implements IPokemonRepo{
//	
//	private final JdbcClient _jdbcClient;
//	
//	public PokemonRepo(JdbcClient jdbcClient) {//db auto configured
//		this._jdbcClient = jdbcClient;
//	}
//
//	@Override
//	public List<Pokemon> GetAllPokemon() {
//		return  _jdbcClient.sql("select * from pokemon")
//				.query(Pokemon.class).list();
//	}
//
//	@Override
//	public List<Pokemon> FindAllByType() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Pokemon FindById(Integer Id) {
//		return _jdbcClient.sql("SELECT id, name, species, types, height, weight, desc FROM pokemon WHERE id = :Id")
//				.param("Id", Id)
//				.query(Pokemon.class);
//	}
//
//	@Override
//	public void CreatePokemon(Pokemon pokemon) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void UpsertPokemon(Pokemon pokemon) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void DeletePokemon(Integer Id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean PokemonExists(Integer Id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}

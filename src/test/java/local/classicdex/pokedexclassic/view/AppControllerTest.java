package local.classicdex.pokedexclassic.view;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import local.classicdex.pokedexclassic.app.AppConstants;
import local.classicdex.pokedexclassic.app.AppController;
import local.classicdex.pokedexclassic.app.AppKey;
import local.classicdex.pokedexclassic.pokemon.PokemonController;
import local.classicdex.pokedexclassic.pokemon.PokemonRepo;
import local.classicdex.pokedexclassic.pokemon.PokemonResponse;
import local.classicdex.pokedexclassic.pokemon.PokemonService;

@WebMvcTest(AppController.class)
public class AppControllerTest {
	@Autowired
	MockMvc mvc;
	
	@MockBean
	AppConstants constants;
	
	@MockBean
	AppKey key;
	
	@MockBean
	PokemonController pokemonCtlr;
	
	@MockBean
	PokemonService pokemonSrv;
	
	@MockBean
	PokemonRepo pokemonRepo;
	
	private final List<PokemonResponse> poke = new ArrayList<>();
	
	@BeforeEach
	void setUp() {
		PokemonResponse pr = new PokemonResponse(
				999,
				"unknown",
				"unknown",
				new String[] {"psychic"},
				.5,
				5.0,
				"Their shapes look like hieroglyphs on ancient tablets. It is said that the two are somehow related."
			);
		PokemonResponse pr2 = new PokemonResponse(
				1,
				"bulbasaur",
				"bulbasaur",
				new String[] {"grass","poison"},
				7,
				69,
				"Red/Blue: A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokémon.\n\nYellow: It can go for days without eating a single morsel. In the bulb on its back, it stores energy.\n"
			);
		poke.add(pr);
		poke.add(pr2);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void shouldFindAllPokemon() throws Exception{
		List<PokemonResponse> body = poke;
		ResponseEntity<List<PokemonResponse>> response = new ResponseEntity<List<PokemonResponse>>(body, HttpStatus.OK);
		when(pokemonCtlr.findAll()).thenReturn(response);
		MvcResult result = mvc.perform(get("/"))
				.andExpect(status().isOk())
				.andReturn();
		String pageResponse = result.getResponse().getContentAsString();
		List<PokemonResponse> pageObjs = (List<PokemonResponse>) result.getModelAndView().getModel().get("allPokemon");
		PokemonResponse pokeObj = pageObjs.get(0);
		Assertions.assertEquals(response.getBody().get(0).Id(), pokeObj.Id());
		Assertions.assertTrue(pageResponse.contains(String.format("<p>%s</p>", pokeObj.Id())));
		Assertions.assertTrue(pageResponse.contains(String.format("<p style=\"display:inline;\">%s</p>", pokeObj.Name().toUpperCase())));
	}
	
	@Test
	void shouldFindPokemonById() throws Exception{
		PokemonResponse body = poke.get(1);
		ResponseEntity<PokemonResponse> response = new ResponseEntity<PokemonResponse>(body, HttpStatus.OK);
		when(pokemonCtlr.getPokemon(body.Id())).thenReturn(response);
		MvcResult result = mvc.perform(get("/" + body.Id()))
				.andExpect(status().isOk())
				.andReturn();
		String pageResponse = result.getResponse().getContentAsString();
		System.out.println(pageResponse);
		System.out.println(result.getModelAndView().getModel());
		PokemonResponse pokeObj = (PokemonResponse) result.getModelAndView().getModel().get("pokemon");
		Assertions.assertEquals(response.getBody().Id(), pokeObj.Id());
		Assertions.assertTrue(pageResponse.contains(String.format("<img src=\"https://raw.githubusercontent.com/jacneeley/gen1spriterepo/refs/heads/main/pokemon/" + "%d" + ".png\" \n"
				+ "			alt=\"%s\"/>", pokeObj.Id(),pokeObj.Name().toUpperCase())));
		Assertions.assertTrue(pageResponse.contains(String.format("<p>%s</p>", pokeObj.formatId())));
		Assertions.assertTrue(pageResponse.contains(String.format("<p>%s</p>", pokeObj.Name().toUpperCase())));
		Assertions.assertTrue(pageResponse.contains(String.format("<p>%s</p>", pokeObj.Species().toUpperCase())));
		Assertions.assertTrue(pageResponse.contains(String.format("<p>HT %s" + "m</p>\n"
				+ "			<p>WT %s" + "kg</p>", pokeObj.getHeightMetric(), pokeObj.getWeightMetric())));
		Assertions.assertTrue(pageResponse.contains(String.format("<p>%s</p>", pokeObj.parseDesc()[0])));
		Assertions.assertTrue(pageResponse.contains(String.format("<p>%s</p>", pokeObj.parseDesc()[1])));
		Assertions.assertTrue(pageResponse.contains(String.format("<a href=\"/%d\"><< Prev</a>\n"
				+ "			<a href=\"/\">Home</a>\n"
				+ "			\n"
				+ "			<a href=\"/%d\">Next >></a>", pokeObj.Id() - 1 , pokeObj.Id() + 1)));
	}
}

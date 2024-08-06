import requests
from bs4 import BeautifulSoup

#load some data for testing.
def getDesc(name:str) -> str:
	try:
		if "-m" in name:
			name.replace("-m", "♂")
		elif "-f" in name:
			name.replace("-f", "♀")
		
		page = requests.get("https://bulbapedia.bulbagarden.net/wiki/" + name + "_(Pok%C3%A9mon)")
		soup = BeautifulSoup(page.content, "html.parser")
		result = soup.find_all("td", attrs={'style':'vertical-align: middle; border: 1px solid #9DC1B7; padding-left:3px;'})
		return "Red/Blue: " + result[1].text + "\nYellow: " + result[2].text
	except:
		print("desc not found...")
		return ""

def getTypes(types: list)-> list:
	if len(types) > 1:
		ptypes = []
		for t in types:
			ptypes.append(t["type"]["name"])
		return ptypes
	return [types[0]["type"]["name"]]

for i in range(1,152):
	try:
		pokemon = requests.get("https://pokeapi.co/api/v2/pokemon/" + str(i) + "/").json()
		ptypes = getTypes(pokemon["types"])
		desc = getDesc(pokemon['name'])

		p = {
			"id" : pokemon["id"],
			"name": pokemon["name"],
			"species": pokemon["species"]["name"],
			"pokemonType": ptypes,
			"height": pokemon["height"],
			"weight": pokemon["weight"],
			"desc": desc
		}
		print(p)
		requests.post(url="http://localhost:8042/api/pokemon", json = p)
		break
	except:
		continue

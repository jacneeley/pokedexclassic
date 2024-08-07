## About:

POKeDEX Classic is a web app that aims to recreate the original POKeDEX from GEN 1 (Red/Blue/Green/Yellow) that runs in the browser.

### Philosophy:

A fast, simple, responsive web app with little to no JS and no annoying cookies, trackers, etc.

An almost 1:1 recreation - recreate the look and feel of the POKeDEX from the GEN 1 games with some creative liberaties taken for ease of development, accessibility, and or just because.

Make something fun and cool - These days there's a lot of junk on the internet trying to sell you stuff. We need more junk sites that are just fun to use.

### Disclaimers:

1. I am NOT a POKeMON fan. I have played Diamond/Pearl & Emerald. That is it. I just like the aesthetics of the GEN 1 and the gameboy color.

2. This is NOT a comprehensive database of POKeDEX. This is just a toy app for GEN 1. If you want a comprehensive DB check out [POKeAPI](https://pokeapi.co/).

## API

### GET
findall: ```localhost:8042/api/pokemon```

Returns all Pokemon as a list of JSON Objects.
```
{
    "Id": 1,
    "Name": "bulbasaur",
    "Species": "bulbasaur",
    "PokemonType": [
      "grass",
      "poison"
    ],
    "Height": 7,
    "Weight": 69,
    "Desc": "Red/Blue: A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokémon.\n\nYellow: It can go for days without eating a single morsel. In the bulb on its back, it stores energy.\n",
    "img": "assets/pokesprites/1.png",
    "weightLbs": 15,
    "heightFt": 2
  },
  {
    "Id": 2,
    "Name": "ivysaur",
    "Species": "ivysaur",
    "PokemonType": [
      "grass",
      "poison"
    ],
    "Height": 10,
    "Weight": 130,
    "Desc": "Red/Blue: When the bulb on its back grows large, it appears to lose the ability to stand on its hind legs.\n\nYellow: The bulb on its back grows by drawing energy. It gives off an aroma when it is ready to bloom.\n",
    "img": "assets/pokesprites/2.png",
    "weightLbs": 29,
    "heightFt": 3
  },
  {
    "Id": 3,
    "Name": "venusaur",
    "Species": "venusaur",
    "PokemonType": [
      "grass",
      "poison"
    ],
    "Height": 20,
    "Weight": 1000,
    "Desc": "Red/Blue: The plant blooms when it is absorbing solar energy. It stays on the move to seek sunlight.\n\nYellow: The flower on its back catches the sun's rays. The sunlight is then absorbed and used for energy.\n",
    "img": "assets/pokesprites/3.png",
    "weightLbs": 220,
    "heightFt": 7
  }
```

findallbyid: ```localhost:8042/api/pokemon/7```

Returns a singular Pokemon as a JSON Object.
```
{
  "Id": 1,
  "Name": "bulbasaur",
  "Species": "bulbasaur",
  "PokemonType": [
    "grass",
    "poison"
  ],
  "Height": 7,
  "Weight": 69,
  "Desc": "Red/Blue: A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokémon.\n\nYellow: It can go for days without eating a single morsel. In the bulb on its back, it stores energy.\n"
}
```
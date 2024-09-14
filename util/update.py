from requests import put

def update():
    nidoran_f = {
        "id":"029",
        "name":"nidoran-f",
        "species":"nidoran-f",
        "pokemonType":["poison"],
        "height":4,
        "weight":70,
        "desc":"Red/Blue: Although small, its venomous barbs render this Pokémon dangerous. The female has smaller horns.\n\nYellow: A mild-mannered Pokémon that does not like to fight. Beware, its small horns secrete venom.\n"
    }
    nidoran_m = {
        "id":"032",
        "name":"nidoran-m",
        "species":"nidoran-m",
        "pokemonType":["poison"],
        "height":5,
        "weight":90,
        "desc":"Red/Blue: Stiffens its ears to sense danger. The larger its horns, the more powerful its secreted venom.\n\nYellow: Its large ears are always kept upright. If it senses danger, it will attack with a poisonous sting.\n"
    }

    mr_mime = {
        "id":"122",
        "name":"mr mime",
        "species":"mr mime",
        "pokemonType":["psychic","fairy"],
        "height":13,
        "weight":545,
        "desc":"Red/Blue: If interrupted while it is miming, it will slap around the offender with its broad hands.\n\nYellow: Always practices its pantomime act. It makes enemies believe something exists that really doesn't.\n"
    }
    updates = [nidoran_f, nidoran_m, mr_mime]
    for u in updates:
        put(url="http://localhost:8042/api/pokemon", json = u)

if __name__ == "__main__":
    update()
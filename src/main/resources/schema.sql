CREATE TABLE IF NOT EXISTS Pokemon (
	id INT NOT NULL,
	name varchar(150) NOT NULL,
	species varchar(150) NOT NULL,
	pokemon_type varchar(250) NOT NULL,
	height int NOT NULL,
	weight int NOT NULL,
	desc varchar(500),
	PRIMARY KEY(id)
);
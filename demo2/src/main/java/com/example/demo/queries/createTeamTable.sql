create table team (
	id SERIAL PRIMARY KEY,
	team_name VARCHAR(32) NOT NULL,
	division_id INTEGER REFERENCES division(id) NOT NULL,
	conference_id INTEGER REFERENCES conference(id) NOT NULL
);

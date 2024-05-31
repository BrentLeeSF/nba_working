create table division (
	id SERIAL PRIMARY KEY,
	conference_id INTEGER REFERENCES conference(id) NOT NULL,
	division_name VARCHAR(16) NOT NULL
);
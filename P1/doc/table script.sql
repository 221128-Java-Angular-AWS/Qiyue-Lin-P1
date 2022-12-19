DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS users;
CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	email VARCHAR(200) UNIQUE,
	"password" VARCHAR(200),
	"role" VARCHAR(200) DEFAULT 'employee'
);

CREATE TABLE tickets (
	ticket_id SERIAL PRIMARY KEY,
    amount INT,
	description VARCHAR(8000),
	status VARCHAR(200) DEFAULT 'pending',
	user_id INT,
	CONSTRAINT fk_ticket_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

INSERT INTO users (email, "password", "role") VALUES ('qiyue108@revature.net', 'password', 'employee');

INSERT INTO tickets(amount, description, user_id) VALUES(100, 'need new equipment', 1);

SELECT * FROM users

SELECT * FROM tickets
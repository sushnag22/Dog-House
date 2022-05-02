INSERT INTO users (username, password, email, first_name, last_name) VALUES ('anonymous', 'user1', 'anonymous@gmail.com', 'Anonymous', 'User');
INSERT INTO users (username, password, email, first_name, last_name) VALUES ('john', 'user2', 'johndoe@gmail.com', 'John', 'Doe');

INSERT INTO breed (name) VALUES ('Dalmatian');
INSERT INTO breed (name) VALUES ('German Shepherd');
INSERT INTO breed (name) VALUES ('Labrador Retriever');

INSERT INTO dog (breed_id, user_id, name, birth_date, gender, colour, description, location) VALUES (1, 2, 'Angel', '2012-11-13', 'Female', 'Brown', 'Sweet and gentle', 'Bengaluru');
INSERT INTO dog (breed_id, user_id, name, birth_date, gender, colour, description, location) VALUES (2, 1, 'Betty', '2006-03-31', 'Male', 'White', 'Hyperactive and playful', 'Delhi');
INSERT INTO dog (breed_id, user_id, name, birth_date, gender, colour, description, location) VALUES (3, 1, 'Coco', '2008-11-22', 'Male', 'Black', 'Smart and caring', 'Jaipur');

INSERT INTO adopter (user_id, name, birth_date, gender, email, phone, address) VALUES (2, 'Ajith', '2001-12-01', 'Male', 'ajith@gmail.com', '9182736455', 'Agra');
INSERT INTO adopter (user_id, name, birth_date, gender, email, phone, address) VALUES (1, 'Bhavish', '2002-01-14', 'Male', 'bhavish@gmail.com', '9988776655', 'Bengaluru');
INSERT INTO adopter (user_id, name, birth_date, gender, email, phone, address) VALUES (2, 'Charan', '1999-05-22', 'Male', 'charan@gmail.com', '9876512345', 'Delhi');

INSERT INTO adoption (adoption_on, adopter_id, dog_id) VALUES ('2020-01-05 10:50:12.000000', '1', '1');
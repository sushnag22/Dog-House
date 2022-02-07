INSERT INTO `user` (username, password, email, first_name, last_name) VALUES ('anonymous', 'user1', 'anonymous@gmail.com', 'Anonymous', 'User');
INSERT INTO `user` (username, password, email, first_name, last_name) VALUES ('sushruth', 'user2', 'sushruth@gmail.com', 'Sushruth', 'Nagaraj');
INSERT INTO `user` (username, password, email, first_name, last_name) VALUES ('suvanta', 'user3', 'suvanta@gmail.com', 'Suvanta', 'Kulkarni');

INSERT INTO `breed` (name) VALUES ('Dalmatian');
INSERT INTO `breed` (name) VALUES ('German Shepherd');
INSERT INTO `breed` (name) VALUES ('Labrador Retriever');

INSERT INTO `dog` (name, birth_date, gender, colour, description, location, breed_id, user_id) VALUES ('Angel', '2012-11-13', 'Female', 'Brown', 'Sweet and gentle', 'Bengaluru', '1', '1');
INSERT INTO `dog` (name, birth_date, gender, colour, description, location, breed_id, user_id) VALUES ('Betty', '2006-03-31', 'Male', 'White', 'Hyperactive and playful', 'Delhi', '2', '2');
INSERT INTO `dog` (name, birth_date, gender, colour, description, location, breed_id, user_id) VALUES ('Coco', '2008-11-22', 'Male', 'Black', 'Smart and caring', 'Jaipur', '3', '3');

INSERT INTO `adopter` (name, birth_date, gender, email, phone, address, user_id) VALUES ('Ajith', '2001-12-01', 'Male', 'ajith@gmail.com', '9182736455', 'Agra', '1');
INSERT INTO `adopter` (name, birth_date, gender, email, phone, address, user_id) VALUES ('Bhavish', '2002-01-14', 'Male', 'bhavish@gmail.com', '9988776655', 'Bengaluru', '2');
INSERT INTO `adopter` (name, birth_date, gender, email, phone, address, user_id) VALUES ('Charan', '1999-05-22', 'Male', 'charan@gmail.com', '9876512345', 'Delhi', '3');

INSERT INTO `adoption` (adoption_on, adopter_id, dog_id) VALUES ('2020-01-05 10:50:12.000000', '1', '1');
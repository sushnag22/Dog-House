CREATE DATABASE IF NOT EXISTS `dog-house`;

USE `dog-house`;

CREATE TABLE IF NOT EXISTS `user`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `first_name` VARCHAR(25) NOT NULL,
    `last_name` VARCHAR(25),
    CONSTRAINT `user_id_pk` PRIMARY KEY(`id`),
    CONSTRAINT `user_username_uk` UNIQUE KEY(`username`),
    CONSTRAINT `user_email_uk` UNIQUE KEY(`email`)
);

CREATE TABLE IF NOT EXISTS `breed`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(25) NOT NULL,
    CONSTRAINT `breed_id_pk` PRIMARY KEY (`id`),
    CONSTRAINT `breed_name_uk` UNIQUE KEY(`name`)
);

CREATE TABLE IF NOT EXISTS `dog`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(25) NOT NULL,
    `birth_date` DATE,
    `age` INT NOT NULL,
    `gender` VARCHAR(25) NOT NULL,
    `colour` VARCHAR(25),
    `description` VARCHAR(25) NOT NULL,
    `location` VARCHAR(25) NOT NULL,
    `breed_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    CONSTRAINT `dog_id_pk` PRIMARY KEY (`id`),
    CONSTRAINT `dog_breed_id_fk` FOREIGN KEY(`breed_id`) REFERENCES `breed`(`id`),
    CONSTRAINT `dog_user_id_fk` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
);

CREATE TABLE IF NOT EXISTS `image`(
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `path` VARCHAR(255) NOT NULL,
                                      `dog_id` INT NOT NULL,
                                      `breed_id` INT NOT NULL,
                                      CONSTRAINT `image_id_pk` PRIMARY KEY(`id`),
                                      CONSTRAINT `image_path_uk` UNIQUE KEY(`path`),
                                      CONSTRAINT `image_dog_id_fk` FOREIGN KEY(`dog_id`) REFERENCES `dog`(`id`),
                                      CONSTRAINT `image_breed_id_fk` FOREIGN KEY(`breed_id`) REFERENCES `breed`(`id`)
);

CREATE TABLE IF NOT EXISTS `adopter`(
                                        `id` INT NOT NULL AUTO_INCREMENT,
                                        `name` VARCHAR(50) NOT NULL,
                                        `birth_date` DATE,
                                        `gender` VARCHAR(25) NOT NULL,
                                        `address` VARCHAR(25),
                                        `phone` VARCHAR(25) NOT NULL,
                                        `user_id` INT NOT NULL,
                                        CONSTRAINT `adopter_id_pk` PRIMARY KEY(`id`),
                                        CONSTRAINT `adopter_user_id_fk` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
);

CREATE TABLE IF NOT EXISTS `adoption`(
                                         `id` INT NOT NULL AUTO_INCREMENT,
                                         `adoption_on` DATETIME,
                                         `dog_id` INT,
                                         `adopter_id` INT ,
                                         CONSTRAINT `adoption_id_pk` PRIMARY KEY(`id`),
                                         CONSTRAINT `adoption_dog_id_fk` FOREIGN KEY(`dog_id`) REFERENCES `dog`(`id`),
                                         CONSTRAINT `adoption_adopter_id_fk` FOREIGN KEY(`adopter_id`) REFERENCES `adopter`(`id`)
);
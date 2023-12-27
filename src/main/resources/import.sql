-- Insert user admin
INSERT INTO User (`id`, `name`, `cpf`, `username`, `password`, `role`) VALUES 
    ("56b9be2c-9953-11ee-b9d1-0242ac120002", "Admin", "763.944.780-16", "admin", "$2a$10$tkx6uK1lKQalnCkdnErVleCQrRcRoon9E5pj/79OWBxsZ7OxNyRSG", "ADMIN"),
    ("8de13afc-9a15-11ee-b9d1-0242ac120002", "Claus", "650.576.130-65", "claus", "$2a$10$tkx6uK1lKQalnCkdnErVleCQrRcRoon9E5pj/79OWBxsZ7OxNyRSG", "USER");

-- CREATE TABLE `User` (
--     `id` VARCHAR(300) NOT NULL,
--     `name` VARCHAR(50) NOT NULL,
--     `cpf` VARCHAR(14) NOT NULL,
--     `username` VARCHAR(50) NOT NULL,
--     `password` VARCHAR(100) NOT NULL,
--     `role` VARCHAR(55) NOT NULL,
--     PRIMARY KEY(`id`)) ENGINE=InnoDB;

-- CREATE TABLE `Orders` (
--     `id` VARCHAR(300) NOT NULL,
--     `price` DECIMAL(6,2) NOT NULL,
--     `type` VARCHAR(20) NOT NULL,
--     `date` DATETIME NOT NULL,
--     `status` VARCHAR(30) NOT NULL,
--     `user_id` VARCHAR(300) NOT NULL,
--     PRIMARY KEY (`id`),
--     FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)) ENGINE=InnoDB;
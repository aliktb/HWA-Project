DROP TABLE IF EXISTS `book` CASCADE;
CREATE TABLE `book`
(
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`author_last_name` VARCHAR(255) NOT NULL,
	`author_first_name` VARCHAR(255) NOT NULL,
	`book_title` VARCHAR(255) NOT NULL,
	`checked_out` BOOLEAN,
	`customer_id` BIGINT
	
);

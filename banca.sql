-- MySQL Script generated by MySQL Workbench
-- Thu Mar 30 04:12:57 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema apbancara
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema apbancara
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `apbancara` DEFAULT CHARACTER SET utf8 ;
USE `apbancara` ;

-- -----------------------------------------------------
-- Table `apbancara`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apbancara`.`client` (
  `idClient` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cardNumber` VARCHAR(45) NOT NULL,
  `CNP` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `idLogin` INT(11) NOT NULL,
  PRIMARY KEY (`idClient`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `apbancara`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apbancara`.`account` (
  `idAccount` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `money` INT(11) NOT NULL,
  `creationDate` DATE NULL DEFAULT NULL,
  `idClient` INT(11) NOT NULL,
  `currency` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAccount`),
  INDEX `idClient_idx` (`idClient` ASC),
  CONSTRAINT `idClient`
    FOREIGN KEY (`idClient`)
    REFERENCES `apbancara`.`client` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `apbancara`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apbancara`.`login` (
  `idlogin` INT(11) NOT NULL AUTO_INCREMENT,
  `role` INT(11) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idlogin`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

--am adaugat cativa clienti
--INSERT INTO apbancara.client (name, cardNumber,CNP,address,idLogin) 
--VALUES ('Rosiu Claudia', '983', '92838','Cugir','10'); 

--INSERT INTO apbancara.client (name, cardNumber,CNP,address,idLogin) 
--VALUES ('Malan Nicolae', '192', '09282','Oradea','18'); 

--INSERT INTO apbancara.client (name, cardNumber,CNP,address,idLogin) 
-- ('Pop Ovidiu', '299', '27171','Bucuresti','62');
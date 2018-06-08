-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ChatBot
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ChatBot` ;

-- -----------------------------------------------------
-- Schema ChatBot
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ChatBot` DEFAULT CHARACTER SET utf8 ;
USE `ChatBot` ;

-- -----------------------------------------------------
-- Table `ChatBot`.`Resposta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChatBot`.`Resposta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `resposta` VARCHAR(144) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `resposta_UNIQUE` (`resposta` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChatBot`.`PalavraChave`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChatBot`.`PalavraChave` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `palavraChave` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `palavraChave_UNIQUE` (`palavraChave` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChatBot`.`Pontuacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChatBot`.`Pontuacao` (
  `idResposta` INT NOT NULL,
  `idPalavraChave` INT NOT NULL,
  `pontuacao` INT NOT NULL,
  PRIMARY KEY (`idResposta`, `idPalavraChave`),
  INDEX `fk_Resposta_has_PalavraChave_PalavraChave1_idx` (`idPalavraChave` ASC),
  INDEX `fk_Resposta_has_PalavraChave_Resposta1_idx` (`idResposta` ASC),
  CONSTRAINT `fk_Resposta_has_PalavraChave_Resposta1`
    FOREIGN KEY (`idResposta`)
    REFERENCES `ChatBot`.`Resposta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Resposta_has_PalavraChave_PalavraChave1`
    FOREIGN KEY (`idPalavraChave`)
    REFERENCES `ChatBot`.`PalavraChave` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChatBot`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChatBot`.`Funcionario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `senha` VARCHAR(45) NOT NULL,
  `tipo` INT NOT NULL,
  `status` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChatBot`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChatBot`.`Cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(144) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChatBot`.`Atendimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChatBot`.`Atendimento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dtInicio` DATE NULL,
  `dtFim` DATE NULL,
  `interacoes` INT NOT NULL,
  `status` INT NOT NULL,
  `duvida` VARCHAR(144) NULL,
  `Funcionario_id` INT NOT NULL,
  `Cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Atendimento_Funcionario1_idx` (`Funcionario_id` ASC),
  INDEX `fk_Atendimento_Cliente1_idx` (`Cliente_id` ASC),
  CONSTRAINT `fk_Atendimento_Funcionario1`
    FOREIGN KEY (`Funcionario_id`)
    REFERENCES `ChatBot`.`Funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Atendimento_Cliente1`
    FOREIGN KEY (`Cliente_id`)
    REFERENCES `ChatBot`.`Cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChatBot`.`Mensagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChatBot`.`Mensagem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mensagem` VARCHAR(144) NOT NULL,
  `remetente` INT NOT NULL,
  `Atendimento_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Mensagem_Atendimento1_idx` (`Atendimento_id` ASC),
  CONSTRAINT `fk_Mensagem_Atendimento1`
    FOREIGN KEY (`Atendimento_id`)
    REFERENCES `ChatBot`.`Atendimento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema adrianob_bd72019
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema adrianob_bd72019
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `adrianob_bd72019` DEFAULT CHARACTER SET latin1 ;
USE `adrianob_bd72019` ;

-- -----------------------------------------------------
-- Table `adrianob_bd72019`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adrianob_bd72019`.`Funcionario` (
  `idFuncionario` INT(11) NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idFuncionario`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `adrianob_bd72019`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adrianob_bd72019`.`Pedido` (
  `idPedido` INT(11) NOT NULL AUTO_INCREMENT,
  `precoTotal` DOUBLE NOT NULL,
  `cartao` TINYINT(4) NOT NULL,
  `idFuncionario` INT(11) NOT NULL,
  `data` DATE NULL DEFAULT NULL,
  `hora` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`idPedido`),
  INDEX `fk_Pedido_Funcionario` (`idFuncionario` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Funcionario`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `adrianob_bd72019`.`Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `adrianob_bd72019`.`Prato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adrianob_bd72019`.`Prato` (
  `idPrato` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `preco` DOUBLE NOT NULL,
  PRIMARY KEY (`idPrato`))
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `adrianob_bd72019`.`Pedido_Prato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adrianob_bd72019`.`Pedido_Prato` (
  `idPedido` INT(11) NOT NULL,
  `idPrato` INT(11) NOT NULL,
  INDEX `fk_Pedido_has_Prato_Prato` (`idPedido` ASC) VISIBLE,
  INDEX `fk_Pedido_has_Prato_Pedido` (`idPrato` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_has_Prato_Pedido`
    FOREIGN KEY (`idPrato`)
    REFERENCES `adrianob_bd72019`.`Prato` (`idPrato`),
  CONSTRAINT `fk_Pedido_has_Prato_Prato`
    FOREIGN KEY (`idPedido`)
    REFERENCES `adrianob_bd72019`.`Pedido` (`idPedido`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

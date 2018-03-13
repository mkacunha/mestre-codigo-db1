CREATE TABLE `cidade` (
  `ibge` varchar(100) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `cep` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cidade_fk` bigint(20) NOT NULL,
  `cep` varchar(20) NOT NULL,
  `logradouro` varchar(100) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `numero` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cep_cidade_FK` (`cidade_fk`),
  CONSTRAINT `cep_cidade_FK` FOREIGN KEY (`cidade_fk`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `historicolog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cep` varchar(20) NOT NULL,
  `status` varchar(100) NOT NULL,
  `log` varchar(500) DEFAULT NULL,
  `historico_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `historicolog_historico_FK` (`historico_fk`),
  CONSTRAINT `historicolog_historico_FK` FOREIGN KEY (`historico_fk`) REFERENCES `historico` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=728 DEFAULT CHARSET=utf8;


CREATE TABLE `historicolog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cep` varchar(20) NOT NULL,
  `status` varchar(100) NOT NULL,
  `log` varchar(500) DEFAULT NULL,
  `historico_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `historicolog_historico_FK` (`historico_fk`),
  CONSTRAINT `historicolog_historico_FK` FOREIGN KEY (`historico_fk`) REFERENCES `historico` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=728 DEFAULT CHARSET=utf8;



# Connection: developer
# Host: localhost
# Saved: 2006-12-26 23:14:20
# 
CREATE TABLE usuario_usr (
      login VARCHAR(10) NOT NULL,
      senha VARCHAR(10) NOT NULL,
      nome VARCHAR(50) NOT NULL,
      email VARCHAR(50) NOT NULL,
      site VARCHAR(50) NOT NULL,
      area VARCHAR(50) NOT NULL,
      nivel VARCHAR(2) NOT NULL,
      acessos INT NOT NULL,
PRIMARY KEY(login));

CREATE TABLE disciplina_dis (
      codigo VARCHAR(5) NOT NULL,
      nome VARCHAR(60) NOT NULL,
      horario VARCHAR(40) NOT NULL,
      professor_login VARCHAR(10) NOT NULL,
      descricao TEXT NOT NULL,
PRIMARY KEY(codigo));

CREATE TABLE usuario_disciplina_uds (
      disciplina_codigo VARCHAR(5) NOT NULL,
      usuario_login VARCHAR(10) NOT NULL,
FOREIGN KEY (disciplina_codigo) REFERENCES disciplina_dis(codigo)
                     ON DELETE CASCADE,
FOREIGN KEY (usuario_login) REFERENCES usuario_usr(login)
                     ON DELETE CASCADE);

CREATE TABLE mensagem_msg (
      id BIGINT AUTO_INCREMENT NOT NULL,
      disciplina_codigo VARCHAR(5) NOT NULL,
      autor_login VARCHAR(10) NOT NULL,
      autor_nome VARCHAR(50) NOT NULL,
      conteudo VARCHAR(50) NOT NULL,
      data VARCHAR(20) NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (autor_login) REFERENCES usuario_usr(login)
                     ON DELETE CASCADE,
FOREIGN KEY (disciplina_codigo) REFERENCES disciplina_dis(codigo)
                     ON DELETE CASCADE);

CREATE TABLE aula_aul (
      codigo BIGINT AUTO_INCREMENT NOT NULL,
      disciplina_codigo VARCHAR(5) NOT NULL,
      assunto VARCHAR(30) NOT NULL,
      descricao VARCHAR(150) NOT NULL,
      tarefa VARCHAR(100) NOT NULL,
      data VARCHAR(20) NOT NULL,
PRIMARY KEY (codigo),
FOREIGN KEY (disciplina_codigo) REFERENCES disciplina_dis(codigo)
                     ON DELETE CASCADE);
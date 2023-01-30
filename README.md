# Desafio Java - Ada

## Sobre o projeto 

prof. William Cesar

CRUD

- Para o cadastro de país deve ser registrado um identificador, nome e uma sigla. Pais(id, nome, sigla).
- Para o cadastro de estado deve ser registrado um identificador, nome, sigla e também a sigla do pais a qual esse estado pertence. Estado(id, nome, sigla, pais).
- Para o cadastro de cidade deve ser registrado um identificador, nome e a sigla do estado a qual essa cidade pertence. Cidade(id, nome, estado).

## Tecnologias

![java sdk 17](https://img.icons8.com/color/96/null/java-coffee-cup-logo--v1.png)
![mysql](https://img.icons8.com/color/96/null/mysql-logo.png)
![docker](https://img.icons8.com/color/96/null/docker.png)
![ubuntu](https://img.icons8.com/color/96/null/ubuntu--v1.png)

## Faça comigo

### Pré requisitos

- Java Instalado versão 11 ou mais.
```bash
$ java --version
java 17.0.1 2021-10-19 LTS
Java(TM) SE Runtime Environment (build 17.0.1+12-LTS-39)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.1+12-LTS-39, mixed mode, sharing)
```

- Maven 
```bash
$ mvn --version
Apache Maven 3.8.1 (05c21c65bdfed0f71a2f2ada8b84da59348c4c5d)
```

- IDE Intellij ou Eclipse (Indicado)

**PARA PERSISTÊNCIA EM BANCO DE DADOS**

- Docker instalado
```bash
$ docker --version
Docker version 20.10.22, build 3a2c30b
```

- Imagem MySQL versão mais recente 
```bash
$ docker pull mysql:latest
$ docker images
REPOSITORY   TAG       IMAGE ID       CREATED        SIZE
mysql        latest    3842e9cdffd2   2 months ago   538MB
```

- Após iniciar o container
```bash
$ docker run -e MYSQL_ROOT_PASSWORD=[informar senha desejada] --name mysql -d -p 3306:3306 mysql 
```

- Acessar o banco e criar a base
```bash
$ mysql -u root -p --protocol=tcp
mysql> CREATE DATABASE db_informar_nome;
```
### Instalação

1. Clone o repositório
```bash
git clone https://github.com/viniciussineza/sistemaCEP
```

2. Abrindo o projeto em uma das IDEs indicadas, poderão ser carregadas as dependências mais facilmente

### Utilização

*Interface da aplicação* == Linha de comando

A Aplicação deve se iniciar de acordo com a definição da configuração no arquivo **application.properties** dentro do caminho **./src/resources**

```
cep.controller.tipo=ARQUIVO
pessoa.persistencia.tipo=XML
```

Sendo duas opções de base de dados
- Arquivo
- Banco de dados MySQL

e uma opção de tipo de arquivo
- XML

#### Tipo de persistencia **ARQUIVO** e tipo **XML**

arquivo application.properties
```
cep.controller.tipo=ARQUIVO
pessoa.persistencia.tipo=XML
```

A aplicação irá gerar XML para cada objeto desejado de acordo com o menu na linha de comando.



## To Do

- [X] Implementação de validação para não cadastrar duas entidades iguais de acordo com o parâmetro **nome**.
- [] Implementar persistencia com banco de dados.
- [] Validação de atualização de atributo apenas ou de toda a entidade.
- [] Expandir README.

Confira as [issues abertas](https://github.com/viniciussineza/sistemaCEP/issues) para uma lista completa.

## Contato

Vinicius Sineza [linkedin](https://www.linkedin.com/in/vinícius-sineza-1bba3b28/)
Link do projeto [SistemaCEP](https://github.com/viniciussineza/sistemaCEP)

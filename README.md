# Liferay Integration

As tecnologias usadas foram: Liferay 7.4.13 , Springboot, Blade, Gradle e Postgres.

## Pré-requisitos

Para uso dessa aplicação é necessario que o ambiente onde ela for ser executada tenha instalado Liferay, Java 8, Gradle e Postgres.

A aplicação depende de outra aplicação em Quarkus para funcionar, baixe ela no GitHub e execute em sua máquina seguindo o passo a passo no arquivo README.md https://github.com/andre-arao/social-quarkus 
(API Quarkus) port=8084, username=postgres e password=postgres no Postgres.

## Instruções de subida da aplicação

1- Após a outra aplicação estiver sendo executada, incie seu bundle com o comando init bundle na pasta raiz do projeto pelo terminal;

2- Configure o servidor Liferay em sua IDE de preferencia e de um Start ou se preferir no terminal execute blade server start:

3- Entre na pasta de modules e execute o comando blade gw deploy

## Coleção do Postman

Para facilitar as chamadas e passar dados validos temos o diretório "postman" no repositório com uma coleção das uri's já mapeadas da API.
[Coleção](https://github.com/andre-arao/integration-liferay/tree/master/postman)
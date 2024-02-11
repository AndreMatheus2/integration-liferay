# Liferay Integration

As tecnologias usadas foram: Liferay DXP 7.4.13 , Springboot, Blade, Gradle e Postgres.

## Pré-requisitos

Para uso dessa aplicação é necessario que o ambiente onde ela for ser executada tenha instalado Liferay (Pasta bundles que já existe no projeto), Java 8, Gradle, Blade e Postgres.

A aplicação depende de uma API para funcionar, baixe a API no GitHub e execute em sua máquina seguindo o passo a passo no arquivo README.md https://github.com/andre-arao/social-quarkus ela roda na porta 8084 e precisa do Postgres como banco de dados.

## Instruções de subida da aplicação

1- Após a outra aplicação estiver sendo executada, incie seu bundle com o comando init bundle na pasta raiz do projeto pelo terminal;

2- Configure o servidor Liferay em sua IDE de preferencia e de um Start ou se preferir no terminal execute blade server start:

3- Entre na pasta de modules e execute o comando blade gw deploy

## Coleção do Postman

Para facilitar as chamadas e passar dados validos temos o diretório "postman" no repositório com uma coleção das uri's já mapeadas da API.
[Coleção](https://github.com/andre-arao/integration-liferay/tree/master/postman)

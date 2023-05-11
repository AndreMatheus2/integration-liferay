# Liferay Integration

As tecnologias usadas foram: Liferay, Springboot e Postgres.

## Pré-requisitos

Para uso dessa aplicação é necessario que o host onde ela for ser executada tenha instalado Liferay, Java 1.8, Gradle e Postgres.

A aplicação depende de outra aplicação para funcionar, baixe ela do GitHub e execute em sua máquina seguindo o passo a passo no arquivo README.md https://github.com/AndreMatheus2/social-quarkus
essa aplicação usa a port=8084, username=postgres e password=admin no Postgres, caso deseje trocar para outro ususario é necessário alterar as informações de conexão que estão no arquivo. [application.properties](https://github.com/ThiagoTecRicardo/quarkus-api-social/blob/master/src/main/resources/application.properties)

## Instruções de subida da aplicação

1- Após a outra aplicação estiver sendo executada, incie seu bundle com o comando init bundle na pasta raiz do projeto pelo terminal;

2- Configure o servidor Liferay em sua IDE de preferencia e de um Start:

3- Entre na pasta de modules e execute o comando glade gw deploy

## Coleção do Postman

Para facilitar as chamadas e passar dados validos temos o diretório "postman" no repositório com uma coleção das uri's já mapeadas da API.
[Coleção](https://git.smanager.com.br/thiago.ricardo/smanager-site-liferay/-/tree/dev/andre-arao/postman)
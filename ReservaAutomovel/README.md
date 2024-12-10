# ReservaAutomovel

Projeto teste  

Como colocar a aplicação a funcionar

1- Clone o repositório
2- No repositorio existem 2 projetos o backend(ReservaAutomovel) e frontend(ReservaAutomovelFront)
2- Abra o projeto no IDE (os 2)
para o backend ReservaAutomovel
3- Procure o ficheiro ReservaAutomovelApplication no caminho ReservaAutomovel\ReservaAutomovel\src\main\java\com\Duarte\ReservaAutomovel
4- dê run ao ficheiro para iniciar o servidor
5- para ver a BD H2 prossiga para o link: http://localhost:8080/h2-console/
5.1- JDBC URL: jdbc:h2:mem:reservadb
user:
sa
password:(não tem)
5.2 connect e está connctado à bd
6- Abra o POSTMAN e importe a coleção presente na raiz do repositório "Sistema de Reserva de Automoveis.postman_collection.json"
7- Siga os passos mencionados na coleção

para o frontend ReservaAutomovelFront

8- Abra o terminal do ide dentro da pasta do projetofrontend 
9- digite : 1º para correr os testes
npm run test 
2º para correr a app
npm run dev

abra o seu browser e backend está disponivel em 
http://localhost:8080/

frontend disponivel em 
http://localhost:3000/

com os dois a funcionar podemos a utilizar a app pelo frontend 

# Desafio Api Star Wars

O objetivo do desafio é desenvolver uma API que contenha os dados dos planetas.


1. #### Requisitos:

    - A API deve ser REST

   - Para cada planeta, os seguintes dados devem ser obtidos do banco de dados da aplicação, sendo inserido manualmente:

     - Nome
     - Clima
     - Terreno

   - Para cada planeta também devemos ter a quantidade de aparições em filmes, que podem ser obtidas pela API pública do Star Wars: https://swapi.dev/about

   ##### 1.1 Funcionalidades desejadas:

    - Adicionar um planeta (com nome, clima e terreno)

    - Listar planetas

    - Buscar por nome

    - Buscar por ID

    - Remover planeta
 
 2. ##### Tecnologias utilizadas no projeto
 
    - Java 8 com a IDE Intellij
    - Spring Boot
    - MongoDB (Utilizando o serviço de banco de dados Atlas: MongoDB)
    - Testes com JUnit 5 e Postman
    
 3. #### Rotas
 
       - Cria um planeta /create
    
       - Lista todos os planetas /findAll
    
       - Busca um planeta por ID /findById/{id}
    
       - Busca um planeta pelo Nome /findByName/{name}
    
       - Remove um planeta /delete/{id}

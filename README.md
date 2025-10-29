# Projeto Ana Souza - API REST (Back-End)

Este repositório contém a API RESTful (Back-End) que alimenta o e-commerce "Ana Souza". Ele é construído em Java com o framework Spring Boot e serve como o "cérebro" da aplicação, lidando com a lógica de negócio, acesso ao banco de dados e autenticação de usuários.

Este Back-End foi projetado para ser consumido pelo Front-End do projeto, que lida com toda a parte visual e experiência do usuário.

➡️ **Link para o Repositório do Front-End:** [projeto-anasouza-frontend](https://github.com/Lucascsg/projeto-anasouza-backend.git)

---

## ✨ Funcionalidades da API

Este servidor expõe uma API RESTful completa para gerenciar todas as entidades do e-commerce:

* **Produtos:** `GET /api/produtos` (lista todos) e `GET /api/produtos/{id}` (busca um, incluindo suas variações de cor).
* **Blog:** `GET /api/blog` (lista todos os posts) e `GET /api/blog/{id}` (busca um post completo).
* **Autenticação:**
    * `POST /api/auth/registrar`: Cadastra um novo usuário, com validação de campos e verificação de e-mail duplicado.
    * `POST /api/auth/login`: Autentica um usuário (comparando e-mail e senha) e retorna seus dados.
* **Pedidos (Checkout):**
    * `POST /api/pedidos`: Recebe a lista de itens do carrinho do Front-End, calcula o total e salva o pedido completo no banco de dados, associado a um usuário.
* **Histórico de Pedidos:**
    * `GET /api/pedidos/meus-pedidos`: Retorna a lista de pedidos anteriores de um usuário (atualmente simulado com ID fixo, pronto para autenticação real).
* **Contato:**
    * `POST /api/contato`: Recebe e salva mensagens do formulário de contato no banco de dados.

---

## 🛠️ Tecnologias (Back-End)

* **Java 17**
* **Spring Boot 3** (Spring Web, Spring Data JPA, Spring Boot DevTools)
* **H2 Database:** Banco de dados relacional em memória, configurado para popular dados iniciais a partir do arquivo `data.sql`.
* **JPA (Jakarta Persistence API) / Hibernate:** Para mapeamento objeto-relacional (ORM) e gerenciamento das tabelas (`Produto`, `Variacao`, `Post`, `Usuario`, `Pedido`, `ItemPedido`).
* **Gradle:** Ferramenta de gerenciamento de dependências e build.

---

## 🚀 Como Rodar

1.  **Pré-requisitos:**
    * Java JDK 17 (ou superior) instalado e configurado no `PATH` e `JAVA_HOME`.
    * Eclipse IDE (ou outra IDE Java com suporte a Gradle).

2.  **Importar o Projeto:**
    * Clone este repositório.
    * No Eclipse, vá em `File > Import... > Gradle > Existing Gradle Project`.
    * Selecione a pasta do projeto clonado e importe. O Eclipse/Gradle baixará todas as dependências.

3.  **Executar a Aplicação:**
    * No "Project Explorer", encontre a classe principal:
        `src/main/java/com/anasouza/anasouzabackend/AnasouzaBackendApplication.java`
    * Clique com o botão direito nela e selecione `Run As > Java Application`.
    * O servidor iniciará na porta `8080`.

4.  **Verificar a API:**
    * Acesse `http://localhost:8080/api/produtos` no seu navegador. Você deve ver o JSON com a lista de 10 produtos.

5.  **Acessar o Banco (H2 Console):**
    * Com o servidor rodando, acesse `http://localhost:8080/h2-console`.
    * Use a JDBC URL: `jdbc:h2:mem:testdb`
    * User: `sa`
    * Password: (deixe em branco)
    * Clique em "Connect" para ver as tabelas (`PRODUTO`, `POST`, `USUARIOS`, etc.) e executar queries SQL.

---

## 🧑‍💻 Autor

**Lucas Casagrande Silva**
* GitHub: `Lucascsg`
* LinkedIn: `Lucas Casagrande Silva`

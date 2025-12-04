# 🎟️ Sistema de Bilheteria – Microserviços com Spring Boot

Este projeto implementa um **sistema completo de venda de ingressos**, dividido em microserviços independentes e integrados via **RabbitMQ**.  
Ele permite **gestão de eventos**, **compra de ingressos**, **envio automático de e-mails** e **registro dos tickets** para futura **validação na portaria**.

---

## 📌 Funcionalidades Principais

### ✔️ Gestão de eventos  
- Cadastro de eventos, datas e modalidades de ingresso  
- Definição de lotes, disponibilidade e preços  

### ✔️ Gestão de clientes  
- Cadastro de clientes  
- Associação de ingressos às compras  

### ✔️ Compra de ingressos  
- Geração de ingressos com controle de estoque  
- Envio de confirmação por e-mail via fila RabbitMQ  
- Registro do ticket em outro microserviço  

### ✔️ Envio automatizado de e-mail  
- Microserviço dedicado a ler mensagens da fila e enviar e-mails SMTP  

### ✔️ Registro de ingressos para validação  
- Microserviço que recebe tickets emitidos e armazena para uso na bilheteria  

---

## 🏛️ Arquitetura do Sistema

O sistema segue uma **arquitetura baseada em microserviços**, organizados da seguinte forma:

┌───────────────────┐ ┌───────────────────────┐  
│ tickets-system │ ---> │ RabbitMQ (mensageria)│  
│ (serviço central) │ └───────────────────────┘  
│ - API REST │ ↑ ↑  
│ - Regras de negócio│ │ │  
└───────────────────┘ │ │  
│ envia emailDTO │ │ ingressoDTO  
▼ ▼ ▼  
┌───────────────────┐ ┌────────────────────────┐  
│ email-service │ │ ticket-office │  
│ (envio de e-mail) │ │ (registro de tickets) │  
└───────────────────┘ └────────────────────────┘  

Cada serviço possui seu próprio banco **PostgreSQL** e pode ser executado de forma isolada.

---

## 🧰 Tecnologias Utilizadas

**Back-end**
- Java 17 / 21  
- Spring Boot 3  
- Spring Web  
- Spring Data JPA  
- Spring Validation  
- Spring Mail  
- Spring AMQP (RabbitMQ)  

**Infraestrutura**
- PostgreSQL  
- Docker e Docker Compose  
- RabbitMQ  

**Outros**
- Flyway (migração de banco)  
- Jackson / Gson (serialização)  
- Lombok  

---

## 📁 Estrutura dos Microserviços

/tickets-system  
├── controllers  
├── dtos  
├── models  
├── producers  
├── services  
└── docker-compose.yml

/email-service  
├── consumer  
├── dto  
├── services  
├── configuration (SMTP)  
└── build.gradle  

/ticket-office  
├── consumer  
├── model  
├── service  
└── docker-compose.yml  

---

## 🚀 Como Executar o Projeto

### 1. Clone o repositório
```
git clone <url-do-seu-repo>
cd <nome-do-repo>
```
▶️ Rodando o tickets-system
Suba os serviços necessários:
```
docker compose up -d
```

Isso inicia:

- PostgreSQL
- pgAdmin
- RabbitMQ

Execute o microserviço:
```
mvn spring-boot:run
```

▶️ Rodando o email-service
Configure o application.properties:
```
properties

spring.mail.host=smtp.gmail.com
spring.mail.username=SEU_EMAIL
spring.mail.password=SUA_SENHA_DE_APLICATIVO
```
Depois execute:
```
./gradlew bootRun
```
▶️ Rodando o ticket-office
```
docker compose up -d
mvn spring-boot:run
```

### 📨 Fluxo da Compra de Ingresso
1. A API tickets-system recebe a compra
2. Valida disponibilidade e cria ingressos
3. Salva no banco
4. Publica 2 mensagens no RabbitMQ:  
   &nbsp;&nbsp;&nbsp;- emailDTO → email-service  
   &nbsp;&nbsp;&nbsp;- ingressoDTO → ticket-office
5. O email-service envia o e-mail
6. O ticket-office registra o ticket para validação

### 📡 Rotas Principais (tickets-system)

**Eventos**
```
POST /eventos
GET  /eventos
PUT  /eventos/{id}
DELETE /eventos/{id}
```
**Clientes**
```
POST /clientes
GET  /clientes
```
**Compra de ingressos**
```
POST /compras
POST /ingressos
```
#### Documentação da API
A documentação da API (Swagger/OpenAPI) fica disponível em:
```
http://localhost:8080/swagger-ui.html
```
### 📦 Próximas Evoluções Planejadas
- Sistema de validação de ingressos via QR Code
- Autenticação JWT
- Dashboard web para visualização de vendas
- Notificações (SMS, WhatsApp)
- Front-end mobile para compradores

### 🤝 Contribuições
Contribuições, sugestões e melhorias são bem-vindas!
Sinta-se à vontade para abrir issues ou enviar PRs.

### 📄 Licença
Este projeto está sob a licença MIT.

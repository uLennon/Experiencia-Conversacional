# Experiência Conversacional

### Challenge #1 –  Experiência Conversacional

**Descrição**

Este projeto é um bot para Discord desenvolvido como parte do **teste técnico da FURIA**.
O bot realiza integração com uma LLM (Large Language Model) de texto da **Cohere**, especializada em perguntas e respostas textuais, proporcionando uma experiência conversacional fluida e baseada em inteligência artificial generativa.

---

## Tecnologias Utilizadas

- **Java 17** – Linguagem principal do projeto.
- **Spring Boot** – Framework para construção da aplicação.
- **Docker** – Containerização da aplicação.
- **Cohere API** – Integração com LLM de texto para respostas automatizadas.
- **Render** – Plataforma utilizada para o deploy da aplicação.

---

## Deploy

O bot está em produção e hospedado na plataforma **[Render](https://render.com/)**, sendo executado diretamente em ambiente cloud sem necessidade de servidores locais.

---

## Como Rodar Localmente

Para executar o projeto localmente, siga os passos abaixo:

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/ulennon/experiencia-conversacional.git
   cd experiencia-conversacional

2. **Crie o arquivo application.properties na pasta src/main/resources/ com as seguintes chaves**:

   key_bot=SEU_TOKEN_DO_DISCORD
   
   key_ia=SUA_API_KEY_DA_COHERE

4. **Execute o projeto com o Spring Boot**:

   No terminal digite
   ./mvnw spring-boot:run
   
---
## Como Usar

    Adicione o bot ao seu servidor Discord usando o link de OAuth2 gerado no portal de desenvolvedores do Discord.

    Após iniciado, envie mensagens com o prefixo **!ask** no servidor e o bot responderá utilizando a IA com base na sua pergunta.
---

# 🏥 API — Sistema de Gestão Médica Híbrida

Esta API foi desenvolvida para oferecer um sistema completo de **gestão médica híbrida**, permitindo o **cadastro de pacientes, agendamento de consultas, acesso a relatórios médicos** e outras funcionalidades voltadas ao acompanhamento remoto e presencial.  
O objetivo principal é **reduzir o absenteísmo** em consultas médicas híbridas, oferecendo uma plataforma estável e fácil de usar.

---

## 🌐 Link da API Hospedada
> 🔗 **URL:** _[https://api-sistema-hc.onrender.com](https://api-sistema-hc.onrender.com)]_

---

## ⚙️ Tecnologias Utilizadas

- **Java 17+**  
- **Jakarta EE / JAX-RS** (para endpoints REST)  
- **Maven** (gerenciamento de dependências)   
- **JDBC** (conexão com o banco)  
- **JSON (Jackson / GSON)** (formato de resposta)  

---

## 🧩 Estrutura do Projeto

```
src/
├── beans/
│ ├── Agendamento.java
│ ├── ContaPaciente.java
│ ├── ConvenioMedico.java
│ ├── Endereco.java
│ ├── Instituicao.java
│ ├── Medico.java
│ ├── Paciente.java
│ └── RelatorioMedico.java
│
├── dao/
│ ├── AgendamentoDAO.java
│ ├── ContaPacienteDAO.java
│ ├── ConvenioMedicoDAO.java
│ ├── EnderecoDAO.java
│ ├── InstituicaoDAO.java
│ ├── MedicoDAO.java
│ ├── PacienteDAO.java
│ └── RelatorioMedicoDAO.java
│
├── bo/
│ ├── AgendamentoBO.java
│ ├── ContaPacienteBO.java
│ ├── ConveniOMedicoBO.java
│ ├── InstituicaoBO.java
│ ├── MedicoBO.java
│ ├── PacienteBO.java
│ └── RelatorioMedicoBO.java
│
├── AgendamentoResource.java
├── ContaPacienteResource.java
├── ConvenioMedicoResource.java
├── CorsFilter.java
├── InstituicaoResource.java
├── MedicoResource.java
├── PacienteResource.java
└── RelatoiroMedicoResource.java
│
├── conexoes/
│ └── ConexaoFactory.java
│
└── excecoes/
└── RequestsExcecoes.java
```
---

## 🚀 Funcionalidades Principais

### 👤 Pacientes
- Cadastro, atualização e exclusão de pacientes  
- Consulta de dados completos  
- Integração com relatórios e agendamentos  

### 📅 Agendamentos
- Criação de novos agendamentos  
- Consulta por paciente ou médico  
- Cancelamento e atualização de horários  

### 📋 Relatórios Médicos
- Registro de novos relatórios por paciente  
- Consulta de histórico médico completo  
- Reenvio automático em caso de falha de conexão (erro SQL 17008 tratado)  

---

## 🔒 Tratamento de Erros e Conexão

A API possui um sistema inteligente de tratamento de erros.
Exemplo no fluxo de relatórios médicos (`RelatorioMedicoBO`):

```java
catch (Exception e) {
    if (e instanceof SQLException sqlExcecao) {
        if (sqlExcecao.getErrorCode() == 17008) {
            this.conexao.close();
            this.conexao = new ConexaoFactory().conexao();
            return relatorioMedicoDAO.selecionarRelatoriosPorPaciente(id, conexao);
        }
    }
    throw e; // Propaga o erro para o Resource tratar
}
```
Assim, apenas o erro de conexão fechada (17008) é tratado diretamente.
Qualquer outro erro é lançado novamente (throw e) para ser capturado e tratado no nível do Resource, mantendo a arquitetura limpa e previsível.

---

🧠 Arquitetura

A API segue uma arquitetura em camadas (MVC expandido):

DAO (Data Access Object): acesso direto ao banco de dados
BO (Business Object): regras de negócio e tratamento de exceções
Resource (Controller REST): camada de exposição via HTTP

### 🧭 Endpoints — Resumo

| Método | Endpoint | Descrição |
|:--------:|:-----------------------------|:---------------------------------------------|
| **GET** | `/instituicao` | Retorna todas as instituições cadastradas. |
| **GET** | `/instituicao/{id}` | Retorna uma instituição específica pelo ID. |
| **POST** | `/instituicao` | Cadastra uma nova instituição. |
| **PUT** | `/instituicao` | Atualiza as informações de uma instituição existente. |
| **DELETE** | `/instituicao/{id}` | Remove uma instituição do sistema. |
| **GET** | `/paciente` | Retorna todos os pacientes cadastrados. |
| **GET** | `/paciente/{id}` | Retorna um paciente específico pelo ID. |
| **POST** | `/paciente` | Cadastra um novo paciente. |
| **PUT** | `/paciente` | Atualiza os dados de um paciente existente. |
| **DELETE** | `/paciente/{id}` | Remove um paciente do sistema. |
| **GET** | `/relatorio_medico` | Retorna todos os relatórios médicos registrados. |
| **GET** | `/relatorio_medico/paciente/{id}` | Retorna todos os relatórios de um paciente específico. |
| **POST** | `/relatorio_medico` | Cadastra um novo relatório médico. |
| **PUT** | `/relatorio_medico` | Atualiza um relatório médico existente. |
| **DELETE** | `/relatorio_medico/{id}` | Remove um relatório médico. |
| **GET** | `/conta_paciente` | Retorna todas as contas cadastradas. |
| **POST** | `/conta_paciente` | Cria uma nova conta de acesso. |
| **PUT** | `/conta_paciente` | Atualiza as informações de uma conta. |
| **DELETE** | `/conta_paciente/{id}` | Remove uma conta do sistema. |

---

🧪 Exemplos de Requisição
POST /paciente

Body (JSON)
```JSON
{
  "nomePaciente": "Ana Silva",
  "idade": 29,
  "altura": 1.65,
  "peso": 60.0,
  "rg": "1234567",
  "cpf": "111.222.333-44",
  "telefone": "11987654321",
  "endereco": {
    "logradouro": "Rua Azul",
    "numero": 21,
    "bairro": "Centro",
    "cidade": "Rio de Janeiro",
    "cep": "20031-170"
  },
  "sexo": "Feminino"
}
```
POST /agendamento

Body (JSON)
```JSON
{
  "data": "2026-05-20",
  "horario": "14:30:00",
  "paciente": { "id": 1 },
  "local": { "id": 2 },
  "medicoResponsavel": { "id": 3 }
}
```
POST /relatorio_medico
Body (JSON)
```JSON
{
  "descricaoRelatorio": "Relatório de rotina",
  "dataRelatorio": "2026-05-20",
  "paciente": { "id": 1 },
  "medicoRelator": { "id": 3 }
}
```

---

🧰 Como Executar Localmente

**1.** Clonar o repositório
```
git clone https://github.com/seu-usuario/api-gestao-medica.git
```

**2.** Importar no IntelliJ / Eclipse

**3.** Importe como projeto Maven.

**4.** Configurar o banco de dados

**5.** Crie o banco e ajuste as credenciais no ConexaoFactory.java.

**6.** Executar o servidor

**7.** Faça o deploy no Tomcat, Payara, Render ou GlassFish.

**8.** Testar os endpoints

**9.** Utilize Postman ou Insomnia para fazer requisições.

---

📈 Objetivo do Projeto

Este projeto foi desenvolvido como parte de um desafio, feito pelo Hospital das Clínicas, sobre redução do absenteísmo em consultas híbridas, abordando desafios de usabilidade tecnológica por parte dos pacientes.
A API centraliza e simplifica o acesso a informações médicas, permitindo um acompanhamento eficiente por pacientes e profissionais.

---

👥 Autores

Pedro Miranda — **RM:** 562682
André Rosa Colombo — **RM:** 563112
José Diogo da Silva Neves — **RM:** 562341

---

📎 Licença

_Este projeto é de uso acadêmico e não possui fins comerciais.
Sinta-se à vontade para estudar, adaptar e contribuir._

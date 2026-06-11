<div align="center">
  <img src="img/logo.png" width="50%">

  <p>
    <img src="https://github.com/rafastephanou/Bookly/actions/workflows/build.yml/badge.svg" alt="Build e Testes">
    <img src="https://img.shields.io/badge/Java-17-orange" alt="Java 17">
    <img src="https://img.shields.io/badge/UI-Swing-blue" alt="Swing">
    <img src="https://img.shields.io/badge/Tests-JUnit%205-green" alt="JUnit 5">
  </p>
</div>

## Definição

Bookly é um programa desenvolvido em Java que permite a criação de clubes do livro digitais, onde os usuários podem criar votações e definir encontros de forma simples e eficaz.

## Preview

<div align="center">
  <img src="src/design/mockup/allScreens.excalidraw.png" width="90%">
</div>

## Funcionalidades

- **Clubes do livro:** qualquer usuário poderá criar o seu próprio clube do livro ou participar de um já existente;
- **Livros:** os administradores de clubes do livro podem incluir novos títulos no banco de dados para que outros administradores possam utilizá-los;
- **Votações:** os administradores podem criar votações para definir o livro e a data-limite para a leitura;
- **Encontros:** os administradores podem definir encontros para discussão entre os participantes.

## Arquitetura

O código segue uma separação em camadas:

| Camada | Pasta | Responsabilidade |
|---|---|---|
| **Modelo** | [`src/com/model`](src/com/model) | Entidades de domínio (`User`, `Book`, `BookClub`, `Poll`, `Meeting`) |
| **Repositório** | [`src/com/repository`](src/com/repository) | Persistência baseada em arquivos (CSV) |
| **Serviço** | [`src/com/service`](src/com/service) | Regras de negócio |
| **Controle** | [`src/com`](src/com) | Controladores que ligam a interface à lógica |
| **Visão** | [`src/design/view`](src/design/view) | Interface gráfica em Swing |

## Tecnologias

- **Java 17**
- **Swing** — interface gráfica
- **[tinylog](https://tinylog.org/) 2.7.0** — sistema de _logging_
- **JCalendar 1.4** — seletor de datas
- **JUnit 5** — testes unitários
- **GitHub Actions** — integração contínua (compilação + testes)

## Como executar

### Pré-requisitos
- **JDK 17+** (para compilar) ou apenas o **JRE** (se as classes já estiverem compiladas)

### Pela linha de comando

A partir da **raiz do projeto** (os caminhos de dados são relativos a ela):

```bash
# Compilar
mkdir -p bin
find src -name "*.java" > sources.txt
javac -cp "lib/*" -d bin @sources.txt

# Executar
java -cp "bin:src/resources:lib/*" com.Main
```

### Pelo Eclipse
Importe o projeto (**File → Import → Existing Projects into Workspace**) e execute [`src/com/Main.java`](src/com/Main.java) como _Java Application_.

## Testes

A suíte usa JUnit 5 e cobre as entidades de domínio:

```bash
# Baixe o JUnit Platform Console Standalone em lib/ e rode, da raiz do projeto:
java -jar lib/junit-platform-console-standalone.jar \
  --class-path "bin:src/resources:lib/tinylog-api-2.7.0.jar:lib/tinylog-impl-2.7.0.jar:lib/jcalendar-1.4.jar" \
  --scan-class-path
```

Os testes também rodam automaticamente a cada _push_ via GitHub Actions.

## Princípios de reuso
- **Biblioteca:** _tinylog_ 2.7.0 — [documentação](https://tinylog.org/getting-started/)
- **Reuso no código:**
  - **Bibliotecas:** bibliotecas conhecidas e amplamente utilizadas do ecossistema Java;
  - **Métodos:** métodos de superclasses reaproveitados pelas subclasses;
  - **Sistema de _logging_:** os métodos que escrevem no arquivo de _log_ são reutilizados de forma extensiva no código.
- **Motivação:** o sistema de _logging_ auxilia substancialmente na localização e correção de erros, organizando informações, alertas e erros em um único arquivo gerado durante a execução.

## Autoria

Projeto desenvolvido em grupo para a disciplina **INF01120 — Técnicas de Construção de Programas** (UFRGS).

# Tutorial sobre Maven

<!-- TOC -->
* [Tutorial sobre Maven](#tutorial-sobre-maven)
  * [O que é Maven?](#o-que-é-maven)
  * [Estrutura de Diretórios do Maven](#estrutura-de-diretórios-do-maven)
    * [O ficheiro `pom.xml`](#o-ficheiro-pomxml)
  * [Criar um Projeto com Módulos](#criar-um-projeto-com-módulos)
    * [Estrutura do Projeto Multi-Módulo](#estrutura-do-projeto-multi-módulo)
    * [Estrutura de pastas de um projeto Multi-modulo](#estrutura-de-pastas-de-um-projeto-multi-modulo)
    * [Ficheiro `pom.xml` do Projeto Pai](#ficheiro-pomxml-do-projeto-pai)
    * [Ficheiro `pom.xml` do Módulo `core`](#ficheiro-pomxml-do-módulo-core)
    * [Ficheiro `pom.xml` do Módulo `ui`](#ficheiro-pomxml-do-módulo-ui)
  * [Definir Dependências em Maven](#definir-dependências-em-maven)
    * [Estrutura Básica de uma Dependência](#estrutura-básica-de-uma-dependência)
    * [Definir Dependências Comuns](#definir-dependências-comuns)
      * [JUnit (para testes)](#junit-para-testes)
      * [Spring Framework](#spring-framework)
      * [MySQL Connector (para conexão com base de dados MySQL)](#mysql-connector-para-conexão-com-base-de-dados-mysql)
    * [Definir Dependências para Projetos Multi-Módulo](#definir-dependências-para-projetos-multi-módulo)
      * [Ficheiro `pom.xml` do Projeto Pai](#ficheiro-pomxml-do-projeto-pai-1)
      * [Ficheiro `pom.xml` do Módulo `core`](#ficheiro-pomxml-do-módulo-core-1)
      * [Ficheiro `pom.xml` do Módulo `ui`](#ficheiro-pomxml-do-módulo-ui-1)
    * [Definir o Alcance (Scope) das Dependências](#definir-o-alcance-scope-das-dependências)
<!-- TOC -->

Maven é uma ferramenta de automação e gestão de projetos, especialmente utilizada em projetos Java. 
O Maven simplifica o processo de construção (build), dependências, relatórios e documentação.

## O que é Maven?

Maven é um software de automação de construção e gestão de dependências.
Ele utiliza um modelo de projeto unificado chamado Project Object Model (POM) e fornece uma maneira padrão de criar e gerir projetos Java.

## Estrutura de Diretórios do Maven

Para um projeto Maven básico, a estrutura de pastas segue uma convenção padrão que ajuda a manter o código organizado.
A estrutura padrão das pastas de um projeto Maven é a seguinte:

````text
my-application
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── App.java
    │   ├── resources
    │   │   └── application.properties
    │   └── webapp
    │       ├── WEB-INF
    │       │   └── web.xml
    │       └── index.jsp
    └── test
        ├── java
        │   └── com
        │       └── example
        │           └── AppTest.java
        └── resources

````
Descrição das pastas e ficheiros:
- `pom.xml`: O ficheiro de configuração principal do Maven.
- `src/main/java`: Contém o código-fonte da aplicação (pasta raiz). No caso acima, o ficheiro App.java encontra-se dentro do package `com.example`
- `src/main/resources`: Contém recursos não Java, como ficheiros de configuração, imagens, etc.
- `src/main/webapp`: Contém ficheiros web para aplicações web, incluindo JSPs, HTML, CSS, JavaScript, etc.
- `src/test/java`: Contém o código-fonte dos testes.
- `src/test/resources`: Contém os recursos necessários para os testes, como ficheiros de configuração específicos para testes.


### O ficheiro `pom.xml`

O ficheiro `pom.xml` (Project Object Model) é o coração de um projeto Maven. Este ficheiro define todas as configurações do projeto, incluindo as dependências, plugins e outras informações de construção.

**Exemplo de `pom.xml` simples:**

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-application</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

## Criar um Projeto com Módulos

Quando se trata de projetos mais complexos, pode ser útil dividi-los em múltiplos módulos. Vamos criar um projeto multi-módulo com dois módulos: `core` e `ui`.

### Estrutura do Projeto Multi-Módulo

```
my-application
├── pom.xml
├── core
│   └── pom.xml
└── ui
    └── pom.xml
```

### Estrutura de pastas de um projeto Multi-modulo

````text
my-application
├── pom.xml
├── core
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── core
│       │   │               └── CoreClass.java
│       │   └── resources
│       │       └── core-config.properties
│       └── test
│           ├── java
│           │   └── com
│           │       └── example
│           │           └── core
│           │               └── CoreClassTest.java
│           └── resources
└── ui
    ├── pom.xml
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── example
        │   │           └── ui
        │   │               └── UIClass.java
        │   ├── resources
        │   │   └── ui-config.properties
        │   └── webapp
        │       ├── WEB-INF
        │       │   └── web.xml
        │       └── index.jsp
        └── test
            ├── java
            │   └── com
            │       └── example
            │           └── ui
            │               └── UIClassTest.java
            └── resources

````


### Ficheiro `pom.xml` do Projeto Pai

O `pom.xml` do projeto pai define a estrutura dos módulos e configurações comuns. Repare que no exemplo abaixo está definido como `<packaging>pom</packaging>`:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-application</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>core</module>
        <module>ui</module>
    </modules>

    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <dependencyManagement>
        <dependencies>
            <!-- Defina dependências comuns aqui -->
        </dependencies>
    </dependencyManagement>
</project>
```

### Ficheiro `pom.xml` do Módulo `core`

O `pom.xml` do módulo `core` define as dependências e configurações específicas para este módulo:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.example</groupId>
        <artifactId>my-application</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>core</artifactId>

    <dependencies>
        <!-- Dependências específicas do módulo core -->
    </dependencies>
</project>
```

### Ficheiro `pom.xml` do Módulo `ui`

O `pom.xml` do módulo `ui` define as dependências e configurações específicas para este módulo:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.example</groupId>
        <artifactId>my-application</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>ui</artifactId>

    <dependencies>
        <!-- Dependências específicas do módulo ui -->
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>core</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
</project>
```

## Definir Dependências em Maven

Definir dependências no ficheiro `pom.xml` em Maven é uma das principais funcionalidades que tornam o Maven tão poderoso para o gerenciamento de projetos Java. As dependências são bibliotecas externas que seu projeto precisa para compilar, executar testes e funcionar corretamente. Aqui está um guia detalhado sobre como definir dependências no ficheiro `pom.xml`.

### Estrutura Básica de uma Dependência

Cada dependência é definida dentro do elemento `<dependencies>` no ficheiro `pom.xml`. A estrutura básica de uma dependência inclui:

- **`groupId`**: O identificador único do grupo (geralmente a organização ou o projeto ao qual a biblioteca pertence).
- **`artifactId`**: O identificador único do artefacto (a biblioteca ou o módulo específico em sí).
- **`version`**: A versão específica do artefacto.

Aqui está um exemplo básico:

```xml
<dependencies>
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>example-library</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

> Dentro da tag `<dependencies>` podem ser definidas mais que uma dependência:

```xml
<dependencies>
    <dependency>
        <!-- uma dependência -->
    </dependency>
    <dependency>
        <!-- outra dependência -->
    </dependency>
    <dependency>
        <!-- etc -->
    </dependency>
</dependencies>
```

### Definir Dependências Comuns

Vamos ver alguns exemplos de como definir dependências comuns. :

#### JUnit (para testes)

JUnit é uma biblioteca amplamente utilizada para testes unitários em Java.

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

- **`scope`**: Especifica o alcance da dependência. O valor `test` indica que esta dependência só é necessária para a compilação e execução dos testes.

#### Spring Framework

Spring é um framework popular para aplicações Java.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.3.8</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.3.8</version>
    </dependency>
</dependencies>
```

#### MySQL Connector (para conexão com base de dados MySQL)

```xml
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.25</version>
    </dependency>
</dependencies>
```

### Definir Dependências para Projetos Multi-Módulo

Num projeto multimódulo, as dependências podem ser partilhadas entre os módulos. Veja um exemplo de como definir dependências neste caso:

#### Ficheiro `pom.xml` do Projeto Pai

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-application</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>core</module>
        <module>ui</module>
    </modules>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
                <version>5.3.8</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```

#### Ficheiro `pom.xml` do Módulo `core`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.example</groupId>
        <artifactId>my-application</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>core</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
        </dependency>
    </dependencies>
</project>
```

#### Ficheiro `pom.xml` do Módulo `ui`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.example</groupId>
        <artifactId>my-application</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>ui</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>core</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
        </dependency>
    </dependencies>
</project>
```

### Definir o Alcance (Scope) das Dependências

O elemento `<scope>` define em que momento a dependência é necessária:

- **`compile`**: (padrão) A dependência está disponível em todas as fases.
- **`provided`**: A dependência é fornecida pelo ambiente de tempo de execução.
- **`runtime`**: A dependência é necessária apenas em tempo de execução.
- **`test`**: A dependência é necessária apenas para a compilação e execução dos testes.
- **`system`**: Similar a `provided`, mas a dependência deve estar presente no sistema local.

**Exemplo com diferentes alcances:**

```xml
<dependencies>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.5</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.1</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>30.1.1-jre</version>
    </dependency>
</dependencies>
```


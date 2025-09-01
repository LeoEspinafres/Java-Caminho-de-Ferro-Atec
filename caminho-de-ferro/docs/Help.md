# Ficheiro de Ajuda

Este ficheiro pretende apresentar/relembrar alguns conceitos uteis para o projeto.

<!-- TOC -->
* [Ficheiro de Ajuda](#ficheiro-de-ajuda)
  * [**Guia sobre Atributos e Métodos Estáticos em Java**](#guia-sobre-atributos-e-métodos-estáticos-em-java)
    * [**1. O que são Atributos Estáticos?**](#1-o-que-são-atributos-estáticos)
    * [**2. O que são Métodos Estáticos?**](#2-o-que-são-métodos-estáticos)
    * [**3. Quando Usar Atributos Estáticos?**](#3-quando-usar-atributos-estáticos)
    * [**4. Quando Usar Métodos Estáticos?**](#4-quando-usar-métodos-estáticos)
    * [**5. Classes e Interfaces Utilitárias**](#5-classes-e-interfaces-utilitárias)
    * [**6. Boas Práticas**](#6-boas-práticas)
    * [**7. Alguns Exemplos**](#7-alguns-exemplos)
  * [**Guia de Utilização do `LocalDate` em Java**](#guia-de-utilização-do-localdate-em-java)
    * [**1. O que é o `LocalDate`?**](#1-o-que-é-o-localdate)
    * [**2. Criar uma instância de `LocalDate`**](#2-criar-uma-instância-de-localdate)
    * [**3. Operações com datas**](#3-operações-com-datas)
      * [**3.1. Adicionar dias, meses ou anos**](#31-adicionar-dias-meses-ou-anos)
      * [**3.2. Subtrair dias, meses ou anos**](#32-subtrair-dias-meses-ou-anos)
    * [**4. Calcular diferenças entre datas**](#4-calcular-diferenças-entre-datas)
    * [**5. Comparar datas**](#5-comparar-datas)
    * [**6. Atributo estático `TODAY`**](#6-atributo-estático-today)
      * [**6.1. Exemplo de campo estático**](#61-exemplo-de-campo-estático)
    * [**7. Dicas importantes**](#7-dicas-importantes)
<!-- TOC -->

---

## **Guia sobre Atributos e Métodos Estáticos em Java**

### **1. O que são Atributos Estáticos?**

* Um **atributo estático** (palavra-chave `static`) **pertence à classe** e não a uma instância.
* Existe **apenas uma cópia do atributo para toda a classe**, independentemente do número de objetos criados.
* Pode ser acedido diretamente pelo nome da classe, sem precisar de criar um objeto:

  ```java
  Classe.atributoEstatico;
  ```

**Exemplo:**

```java
public class Configuracao {
    public static String VERSAO = "1.0";
}

// Uso
System.out.println(Configuracao.VERSAO);
```

---

### **2. O que são Métodos Estáticos?**

* Um **método estático** pode ser chamado **sem criar uma instância da classe**.
* Normalmente usado para **operações utilitárias** ou para trabalhar com atributos estáticos.
* Não pode aceder a atributos ou métodos de instância diretamente (porque não existe um objeto em contexto).

**Exemplo:**

```java
public class Matematica {
    public static int soma(int a, int b) {
        return a + b;
    }
}

// Uso
int resultado = Matematica.soma(5, 3);
```

---

### **3. Quando Usar Atributos Estáticos?**

* Quando o valor é **partilhado por todos os objetos** (ex: contador de instâncias).
* Quando se pretende uma **configuração global** (ex: data de referência).
* Para **constantes**, que normalmente são `public static final`.

---

### **4. Quando Usar Métodos Estáticos?**

* Para **funções utilitárias** ou operações matemáticas.
* Para **aceder/alterar valores globais** ou atributos estáticos.
* Para **criar “helpers”** (classes de apoio com métodos comuns).

---

### **5. Classes e Interfaces Utilitárias**

* Uma **classe utilitária** contém apenas métodos estáticos e, normalmente, um construtor privado para evitar instâncias:

  ```java
  public class DataUtils {
      private DataUtils() {}  // impede instâncias

      public static boolean isAnoBissexto(int ano) {
          return java.time.Year.isLeap(ano);
      }
  }
  // Uso
  boolean bissexto = DataUtils.isAnoBissexto(2024);
  ```

* Uma **interface utilitária** pode agrupar **constantes** ou métodos `default`/`static`:

  ```java
  public interface Mensagens {
      String ERRO = "Erro desconhecido";
      static void imprimirErro() {
          System.out.println(ERRO);
      }
  }
  Mensagens.imprimirErro();  // Acede sem criar instância
  ```

---

### **6. Boas Práticas**

* **Não abusar** de `static` para dados que deviam ser específicos de cada objeto.
* **Organizar helpers** (por ex. `StringUtils`, `DataUtils`, `MathUtils`) para métodos comuns.
* Usar `public static final` para constantes globais (ex: `PI`, `MAX_VALUE`).
* Métodos estáticos são mais rápidos, pois não precisam de instância, mas têm **menos flexibilidade** (não podem ser sobrescritos em subclasses).

---

### **7. Alguns Exemplos**

**1. Contador de Objetos Criados**

Este exemplo mostra como usar um atributo estático para contar quantos objetos de uma classe foram criados.

```java
public class Bilhete {
    private static int contador = 0; // Contador partilhado por todos os objetos
    private int id;

    public Bilhete() {
        contador++;      // Incrementa sempre que um novo bilhete é criado
        this.id = contador;
    }

    public int getId() {
        return id;
    }

    public static int getTotalBilhetesCriados() {
        return contador; // Acede ao contador sem precisar de instância
    }
}

// Utilização
Bilhete b1 = new Bilhete();
Bilhete b2 = new Bilhete();
System.out.println(Bilhete.getTotalBilhetesCriados()); // 2
```

---

**2. Configuração Global (Data de Referência)**

Este exemplo mostra como usar um atributo estático para manter um estado global (como uma data fixa para testes).

```java
import java.time.LocalDate;

public class Bilheteira {
    public static LocalDate TODAY = LocalDate.now(); // Data global

    public static void alterarDataHoje(LocalDate novaData) {
        TODAY = novaData; // Atualiza para todos os objetos
    }
}

// Utilização
System.out.println(Bilheteira.TODAY); // Data atual
Bilheteira.alterarDataHoje(LocalDate.of(2025, 12, 31));
System.out.println(Bilheteira.TODAY); // 2025-12-31
```

---

**3. Métodos Utilitários (Classe ou interface Helper)**

Este exemplo mostra uma classe apenas com métodos estáticos, úteis para várias partes da aplicação, sem precisar de instanciá-la.

```java
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DataUtils {

    // Calcula a diferença em dias entre duas datas
    public static long diferencaEmDias(LocalDate inicio, LocalDate fim) {
        return ChronoUnit.DAYS.between(inicio, fim);
    }

    // Verifica se uma data é futura
    public static boolean isFutura(LocalDate data) {
        return data.isAfter(LocalDate.now());
    }
}

// Utilização
long dias = DataUtils.diferencaEmDias(LocalDate.of(2020, 1, 1), LocalDate.now());
System.out.println("Dias desde 2020: " + dias);
```

Este exemplo mostra uma interface apenas com atributos e métodos estáticos, úteis para várias partes da aplicação, sem precisar de instanciá-la.

```java
public interface Labels {
    String MENU_PASSAGEIROS = "MENU PASSAGEIROS";
    String REGISTAR_PASSAGEIRO = "Registar passageiro";
    String VER_PASSAGEIRO = "Ver passageiro";
    String LISTAR_PASSAGEIROS = "Listar passageiros (por nome)";
    String LISTAR_PASSAGEIROS_POR_VIAGENS = "Listar Passagiros (por valor viagens)";
    String LISTAR_PASSAGEIROS_IDADE = "Listar Passageiros (por idade)";
    String ALTERAR_NOME_PASSAGEIRO = "Alterar passageiro (nome)";
    String PESQUISAR_PASSAGEIROS = "Pesquisar passageiros";

    //Solicitar Dados
    String ASK_NOME_PASSAGEIRO = "Insira o nome do Passageiro: ";
    String ASK_ID_PASSAGEIRO = "Insira o id do Passageiro: ";
    String ASK_DATA_NASCIMENTO = "Insira a data de nascimento do Passageiro: ";
    String ASK_TERMO_PESQUISA = "Insira o termo de pesquisa: ";


    //Mensagens de retorno
    static String passageiroCriado(int id) {
        return String.format("Passageiro ID: %d criado com Sucesso.", id);
    }

    static String passageiroAlterado(int id, String nome) {
        return String.format("Passageiro ID: %d alterado com sucesso [%s].", id, nome);
    }

    static String listaPassageirosHeader(int numPassageiros) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("------- Lista de Passageiros [%d] | Data: %s -------", numPassageiros, Bilheteira.TODAY.format(formato));
    }

    static String listaPassageirosItem(int id, String nome, String dataNascimento, int idadeAnos, String categoria, int numItenerarios, double valorItenerarios) {
        return String.format("-> [%d] %s - %s (%d) - %s - %d itenerário(s) (%.2f €)", id, nome, dataNascimento, idadeAnos, categoria, numItenerarios, valorItenerarios);
    }
}
```

```java
//Utilização:
System.out.println(Labels.passageiroCriado(3));

```

---

## **Guia de Utilização do `LocalDate` em Java**

### **1. O que é o `LocalDate`?**

A classe `LocalDate` (do pacote `java.time`) representa uma **data sem fuso horário**, contendo apenas ano, mês e dia.
É amplamente utilizada para trabalhar com datas de forma moderna e segura, substituindo a antiga `java.util.Date`.

---

### **2. Criar uma instância de `LocalDate`**

* **Data atual**:

  ```java
  LocalDate hoje = LocalDate.now();
  ```

* **Data específica** (ano, mês, dia):

  ```java
  LocalDate data = LocalDate.of(1990, 12, 25);  // 25 de Dezembro de 1990
  ```

* **A partir de texto (formato ISO yyyy-MM-dd)**:

  ```java
  LocalDate data = LocalDate.parse("2023-07-15");
  ```

---

### **3. Operações com datas**

O `LocalDate` oferece vários métodos para adicionar ou subtrair unidades de tempo.

#### **3.1. Adicionar dias, meses ou anos**

```java
LocalDate hoje = LocalDate.now();
LocalDate daquiA5Dias = hoje.plusDays(5);
LocalDate daquiA2Meses = hoje.plusMonths(2);
LocalDate daquiA1Ano = hoje.plusYears(1);
```

#### **3.2. Subtrair dias, meses ou anos**

```java
LocalDate semanaPassada = hoje.minusDays(7);
LocalDate mesPassado = hoje.minusMonths(1);
```

---

### **4. Calcular diferenças entre datas**

Para calcular diferenças, utilizamos a classe `ChronoUnit`:

**Exemplo – Idade em dias:**

```java
public long getIdadeEmDias() {
    return ChronoUnit.DAYS.between(this.dataNascimento, Bilheteira.TODAY);
}
```

* `ChronoUnit.DAYS.between(A, B)` devolve o número de dias entre as datas `A` e `B`.

**Exemplo – Idade em anos:**

```java
long idadeEmAnos = ChronoUnit.YEARS.between(dataNascimento, LocalDate.now());
```

---

### **5. Comparar datas**

* Verificar se uma data é anterior a outra:

  ```java
  if (dataNascimento.isBefore(LocalDate.now())) {
      System.out.println("Data válida.");
  }
  ```

* Verificar se uma data é posterior a outra:

  ```java
  if (dataEvento.isAfter(LocalDate.now())) {
      System.out.println("Evento futuro.");
  }
  ```

---

### **6. Atributo estático `TODAY`**

Por vezes, pretende-se ter uma data "fixa" para simulações ou testes, em vez de usar sempre `LocalDate.now()`. Por essa razão no projeto utilizamos um atributo estático na classe `Bilheteira`

#### **6.1. Exemplo de campo estático**

```java
public class Bilheteira {
    private LocalDate today;           // instância privada
    public static LocalDate TODAY;     // acesso estático global
}
```

Se quisermos de forma global numa aplicação aceder à mesma "data do sistema" de forma estática:

```java
Bilheteira.TODAY = LocalDate.now();  // define a data atual (pode ser no construtor ou método init)
```

Depois, podem aceder diretamente:

```java
LocalDate data = Bilheteira.TODAY;
```

---

### **7. Dicas importantes**

1. **Nunca usar `new LocalDate()`** – esta classe é **imutável**, e não possui construtor público.
2. **Utilizar sempre os métodos `plusX` e `minusX`** para manipular datas, pois eles devolvem **uma nova instância**, não alteram a original.
3. **Usar `ChronoUnit` para diferenças** em dias, meses ou anos, como:

   ```java
   ChronoUnit.DAYS.between(data1, data2);
   ChronoUnit.MONTHS.between(data1, data2);
   ChronoUnit.YEARS.between(data1, data2);
   ```
4. **Formatar a data** para exibição:

   ```java
   DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
   String texto = Bilheteira.TODAY.format(fmt);
   ```

---

# Enunciado de Projeto - Caminhos de Ferro

O objetivo do projeto é criar uma aplicação am JAVA que gere os
itinerários de passageiros que usam os caminhos de ferro (em Portugal). Para o efeito, elabarora-se uma descrição dos
requisitos,
refletido no seguinte índice:

<!-- TOC -->
* [Enunciado de Projeto - Caminhos de Ferro](#enunciado-de-projeto---caminhos-de-ferro)
  * [1. Principais Conceitos](#1-principais-conceitos)
    * [Serviço, Estações e Segmentos](#serviço-estações-e-segmentos)
    * [Passageiro](#passageiro)
    * [Itenerário, ou viagem](#itenerário-ou-viagem)
  * [Funcionalidades da Aplicação](#funcionalidades-da-aplicação)
    * [Menu Principal](#menu-principal)
      * [1. Abrir](#1-abrir)
      * [2. Guardar](#2-guardar)
      * [3. Incrementar data (dias)](#3-incrementar-data-dias)
      * [4. Gestão de Passageiros](#4-gestão-de-passageiros)
      * [4. Consulta de Serviços, Estações e Segmentos](#4-consulta-de-serviços-estações-e-segmentos)
      * [5. Aquisição de Itinerários (Bilhetes de viagem)](#5-aquisição-de-itinerários-bilhetes-de-viagem)
    * [Menu Passageiros](#menu-passageiros)
      * [1. Registar Passageiro](#1-registar-passageiro)
      * [2. Ver Passageiro](#2-ver-passageiro)
      * [3. Listar Passageiros (por nome, alfabeticamente)](#3-listar-passageiros-por-nome-alfabeticamente)
      * [4. Listar Passageiros (por idade)](#4-listar-passageiros-por-idade)
      * [5. Listar Passageiros (por viagens efetuadas)](#5-listar-passageiros-por-viagens-efetuadas)
      * [6. Alterar Passageiro (nome)](#6-alterar-passageiro-nome)
      * [7. Pesquisar Passageiros (por nome)](#7-pesquisar-passageiros-por-nome)
    * [Menu de Consulta de Serviços e Estações](#menu-de-consulta-de-serviços-e-estações)
      * [1. Listar todas as Estações](#1-listar-todas-as-estações)
      * [2. Listar todos os Serviços](#2-listar-todos-os-serviços)
      * [3. Imprimir detalhe de um Serviço](#3-imprimir-detalhe-de-um-serviço)
      * [4. Lista de Serviços com início numa Estação](#4-lista-de-serviços-com-início-numa-estação)
      * [5. Lista de Serviços com fim numa Estação](#5-lista-de-serviços-com-fim-numa-estação)
      * [6. Lista de Serviços que servem uma Estação](#6-lista-de-serviços-que-servem-uma-estação)
      * [7. Lista de Segmentos de um Serviço](#7-lista-de-segmentos-de-um-serviço)
    * [Menu de Itenerários](#menu-de-itenerários)
      * [1. Registar Itinerario](#1-registar-itinerario)
      * [2. Mostrar todos Itinerarios](#2-mostrar-todos-itinerarios)
      * [3. Mostrar Itinerarios de Cliente](#3-mostrar-itinerarios-de-cliente)
  * [Ficheiros de Dados e Importação](#ficheiros-de-dados-e-importação)
    * [Função `importFile`](#função-importfile)
    * [Estrutura dos Ficheiros `.import`](#estrutura-dos-ficheiros-import)
    * [Exemplo de Ficheiros](#exemplo-de-ficheiros)
  * [Regras Globais para o desenvolvimento](#regras-globais-para-o-desenvolvimento)
  * [Avaliação](#avaliação)
  * [Testes à aplicação](#testes-à-aplicação)
<!-- TOC -->

## 1. Principais Conceitos

Existem três conceitos básicos na aplicação a desenvolver: serviços, passageiros e Viagem.

### Serviço, Estações e Segmentos

**Entende-se por serviço** um percurso realizado por um comboio ao longo de várias estações.
A cada estação de um serviço está associado o momento de partida/chegada do comboio (por simplificação assume-se que o
combaio chega e parte de uma estação no mesmo minuto).

Ainda, para efeitos de simplificação, assume-se que todos os serviços se realizam em todos os dias.

Além das informações anteriores, o serviço tem ainda um identificador numérico único (definido na criação do serviço),
bem como um preço associado (relativo ao usufruto do serviço completo, ou seja, percorrer todas as estações do mesmo).

Exemplo da definição genérica de um serviço: `Serviço #559 [€10.55] Coimbra (12:16) >> Guarda (15:20)`

Exemplo da apresentação detalhada de um serviço (com identificação das respetivas estações):

```text
Serviço #559 @ €10.55
12:16 Coimbra
13:10 Soito
13:20 Mortágua
13:40 Santa Comba Dão
14:45 Mangualde
15:20 Guarda
```

**Entende-se por segmento** o percurso entre duas estações, pelo que se pode dizer que um serviço é
composto por um ou vários segmentos (percursos entre estações).

Cada segmento de um serviço tem um custo base, que depende da distância entre estações.
A distância entre estações corresponde ao tempo gasto na viagem entre essas estações.
Desse modo, considerando que a totalidade do custo do serviço `#559` (no exemplo acima) é de €10.55 (para 184 minutos,
ou seja, 3 Horas e 34 minutos - 3H34),
o custo do segmento `Coimbra >> Soito` (com duração de 54 minutos) é de `10.55 * 54 / 184 = 3,0961... => 3.10 €` (note
que o valor é sempre arredondado ao cêntimo do Euro).

O preço pago pelo passageiro para viajar um conjunto de segmentos de um serviço depende ainda da sua categoria, que pode
ser normal ou outra (a adição de novas categorias deve ter impacto reduzido no código da aplicação).

> **IMPORTANTE:**
> - Os valores monetários são sempre apresentados arredondados ao cêntimo;
> - O cálculo do tempo de cada segmento é sempre efetuado e apresentado ao minuto;

### Passageiro

Entende-se por Passageiro uma Pessoa que possui um registo na aplicação e que pode adquirir itenerários (viagens).

Um passageiro possui:

- um identificador único. O identificador único é atribuído automaticamente e de forma sequencial no momento do registo
  do passageiro, sendo atribuído o identificador 1 (um) ao primeiro passageiro.
- um nome (cadeia de caracteres - string).
- Data de Nascimento (yyyy-mm-dd) (instância da classe `LocalDate`)

Todos os passageiro pertencem a uma Categoria. Ou seja, os passageiros são categorizados como:

- normais,
- frequentes, ou
- especiais.

Um passageiro é considerado frequente enquanto o valor dos últimos 10 itinerários for superior a €200. Volta a ser
normal se esta condição deixar de se verificar. Um cliente é considerado especial enquanto tiver gasto mais de €1500 nos
últimos 10 itinerários. Deixa de ser especial se esta condição deixar de se verificar. Se se verificarem simultâneamente
as condições para ser um cliente frequente e especial, o cliente é considerado especial.

Aplicam-se as seguintes condições para atribuição de descontos em itinerários:

- passageiros normais, 0%;
- passageiros frequentes, 15%;
- passageiros especiais, 35%.

Cumulativamente, são ainda aplicados descontos relativamente à idade do passageiro:

- Passageiros com idade inferior ou igual a 18 anos: 15%;
- Passageiros com idade superior ou igual a 65 anos: 10%;

> **Importante:**
> - Na aplicação de descontos, caso o passageiro se encontre na situação de receber um desconto relativo à sua categoria
    e idade, a regra de aplicação é:
    >

- Primeiro é aplicado o desconto referente à categoria sobre o valor total a pagar pelo itenerário;

> - Só, posteriormente, é aplicado o desconto relativo à idade;
    >

- O arredondamento ao cêntimo de euro é só efetuado no final da aplicação de todos os descontos. Veja o exemplo
  abaixo.

```text
Exemplo de cálculo:
Valor total do Itenerário: 20,35 € (Somatório do valor dos segmentos)
Passageiro Frequente (15% de desconto) com 68 anos (10% de desconto)
Valor final a pagar pelo Passageiro: (20,00  * (1-0,15)) * (1-0,10) = 15,56775 => 15,57 €
```

### Itenerário, ou viagem

Um itinerário corresponde a uma viagem realizada num dado dia, por um passageiro,
com base num conjunto de segmentos lógicos, correspondentes a partes de serviços, i.e., partidas (ou chegadas) agendadas
para cada estação.
É possível saber, através do serviço associado, qual o percurso a realizar nesse segmento.

O custo base do itinerário é a soma dos custos parciais de cada serviço que o constitui (segmentos), correspondentes aos
segmentos realizados nesses serviços.
Dependendo da categoria do passageiro, podem ser realizados descontos.

Os itinerários são identificados por um número de ordem (com início em 1) no contexto de cada passageiro (cada
passageiro possui a sua numeração, isto é, dois passageiros distintos podem possuir o mesmo identificador para
determinada viagem).
Não é possível remover itinerários já registados. Ou seja assume-se sempre que no momento da aquisição do itenerário (
viagem) ela realiza-se.

---

## Funcionalidades da Aplicação

A aplicação permite manter informação sobre os serviços. Permite ainda registar e gerir passageiros, bem como criar
novos itinerários (bilhetes de viagens) para estes.
Possui ainda a capacidade de preservar o seu estado (não é possível manter várias versões do estado da aplicação em
simultâneo). Manter o seu estado significa guardar (através de serialização) o seu estado num ficheiro, de modo a poder
carregar esse mesmo estado, quando se pretender.

A base de dados de serviços (ficheiro textual com informação) é carregada no início da aplicação. Por simplificação, não
é possível adicionar ou remover serviços durante a execução da aplicação.

Todos os cálculos são realizados e os resultados armazenados sem arredondamento. Apenas na apresentação de resultados (
e.g., preços) se apresentam valores com um número limitado de casas decimais (onde indicado).

De modo ao utilizador poder executar as funcionalidades da aplicação, existem um conjunto de Menus, sendo que ao iniciar
a aplicação abre o menu Principal, efetuando-se, a partir desse menu, o acesso Às funcionalidade da aplicação e aos
submenus.

Porque há matéria que ainda será lecionada, pretende-se nesta primeira fase desenvolver as funcionalidades do **menu de
Passageiros e de Serviços**.

---

### Menu Principal

Menu de acesso a menus secundários e a funcionalidades como abrir e guardar o estado da aplicação através da
serialização.

#### 1. Abrir

**A fornecer posteriormente***

---

#### 2. Guardar

**A fornecer posteriormente***

---

#### 3. Incrementar data (dias)

**Já implementado**

A aplicação, por defeito, é sempre iniciada com a data de **28 de julho de 2025**, que é a data do lançamento do
projeto. Pode ver essa inicialização no construtor da classe Bilheteira `this.today = LocalDate.of(2025, 7, 28);`
Ao invocar-se esta funcionalidade a data avança um número inteiro de dias, a fornecer pelo utilizador da aplicação

> Não se utiliza a Data e Hora do computador de modo a ser possível simular e executar testes à Aplicação. Desse é
> possível simular um conjunto de funcionalidades sem ter que esperar que o tempo passe....

---

#### 4. Gestão de Passageiros

Abre o menu de Passageiros

É possível efetuar várias operações sobre passageiros, tais como criar um passageiro, ver os dados de um passageiro e
efetuar várias listagens sobre passageiros.

---

#### 4. Consulta de Serviços, Estações e Segmentos

Abre o menu de Serviços.

É possível fazer várias consultas sobre serviços (ver detalhe abaixo, na explicação do menu Serviços).
É possível ver todos os serviços, inspecionar um serviço particular e inspecionar serviços com base na especificação de
estações.
Podem ser definidas, no futuro, outras formas de pesquisa e de apresentação de serviços, pelo que a arquitetura da
aplicação tem que ser flexivel

---

#### 5. Aquisição de Itinerários (Bilhetes de viagem)

Abre o menu de Itenerários onde é possível um cliente adquirir um itenerário (percurso entre duas estações). De notar
que esse itenerário (percurso entre estações) pode ser efetuado entre estações que pertencem a serviços distintos.

---

### Menu Passageiros

Este menu permite efetuar operações sobre um passageiro.

As etiquetas das opções deste menu, bem como todos os métodos correspondentes às mensagens de diálogo e retorno para
este menu estão definidas na interface `atec.pooo.cferro.app.passageiros.Labels`.

No Menu de Passageiros (package `atec.poo.cferro.app.passageiros`) estão parcialmente implementadas todas as classes,
nomeadamente as classes que derivam da classe `atec.poo.ui.Comando`, que os formandos devem completar

> O desenvolvimento das funcionalidades deste menu exige que os alunos saibam manipular coleções de objetos (por
> exemplo, Listas ou Mapas), utilizando iterações e ordenadores (Comparators) para diferentes critérios, como nome,
> idade
> ou valor total das viagens. Também terão de aplicar LocalDate para calcular a idade dos passageiros, além de
> saber lidar com pesquisas (por exemplo, encontrar um passageiro pelo seu ID ou por parte do nome). Será igualmente
> necessário compreender
> tratamento de exceções personalizadas e trabalhar com formatação de strings para
> apresentar a informação no formato correto.

#### 1. Registar Passageiro

Permite registar um passageiro no sistema.

É Solicitado ao utilizador, por esta ordem, o nome do passageiro (`ASK_NOME_PASSAGEIRO`) e a sua data de Nascimento (
`ASK_ID_PASSAGEIRO`)
Ao ser criado, um novo passageiro é categorizado com a categoria `NORMAL`. Considera-se, ainda, que um passageiro
acabado de criar não possui itenerários efetuados.

Esta função possui como mensagem de retorno:

- Mensagem de criação com sucesso do passageiro, através da função `String passageiroCriado(int id)`

**Exemplo para um primeiro passageiro a ser criado**

```shell
Passageiro ID: 1 criado com Sucesso!
```

> Como para efeito das funcionalidades da aplicação, um passageiro será sempre identificado pelo seu `ID`, considera-se,
> para efeitos de simplificação, que não há passageiros iguais. Ou seja, é possivel registar dois passageiros com o
> mesmo
> nome, aos quais será atribuído IDs distintos.
---

#### 2. Ver Passageiro

Permite mostrar toda a informação de um passageiro.

É solicitado identificador (ID) de um passageiro (`ASK_ID_PASSAGEIRO`), devendo mostrar um dos seguintes outputs:

- Mensagem de Erro: Quando o Passageiro com o identificador fornecido não existir. Para o efeito deve ser lançada a
  exceção `AppNoSuchPassengerException`, já fornecida.
- Apresentação da Ficha do Passageiro: Através do método `Labels.showPassageiro(int id, String nome, String dataNascimento,
            int idadeAnos,
            String categoria,
            int numItenerarios,
            double valorItenerarios
    )`

**Exemplos:**

```text
MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 2
Insira o id do Passageiro: 3
#### Ficha de Passageiro [003] - 2025-07-28 ####
Nome: Beatriz Sousa
Data de Nascimento: 2006-03-20 (19 anos)
Categoria: Normal
Nº de Itinerários: 0
Valor Total Itinerários: 0,00 €
################################################

```

```text
MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 2
Insira o id do Passageiro: 78
Ver passageiro: Operação inválida: Passageiro com o id 78 não existe.

```

---

#### 3. Listar Passageiros (por nome, alfabeticamente)

Permite listar todos os passageiros, ordenados alfabeticamente pelo seu nome (A->Z). Caso existam dois passageiros com o
mesmo nome,
devem ser apresentados em primeiro lugar os passageiros com o menor ID.

O resultado desta operação deve gerar:

- Caso não existam passageiros registados: É lançada a exceção `AppNoPassengersRegistered`
- Caso existam passageiros registados:
    - Apresentar como Cabeçalho da lista a função a String retornada de  `listaPassageirosHeader(int numPassageiros)`
    - Por cada Passageiro existente, apresentar o retorno da função
      `String listaPassageirosItem(int id, String nome, String dataNascimento, String categoria, int numItenerarios)`

> A data de nascimento deve ser apresentada no formato `yyyy-MM-dd` e a categoria como: `Normal`, `Frequente` ou
`Especial`

**Exemplo**

```text
Listar passageiros (por nome): Operação inválida: Não existem passageiros registados na aplicação
```

```text
---- Lista de Passageiros [75] ----
-> [20] Abigail Martin - 1959-10-20 - Normal: 0 itenerário(s) (0,00 €)
-> [66] Amanda Cruz - 1978-12-29 - Normal: 0 itenerário(s) (0,00 €)
-> [14] Amelia Thomas - 1959-04-20 - Normal: 0 itenerário(s) (0,00 €)
```

---

#### 4. Listar Passageiros (por idade)

Permite listar todos os passageiros, ordenados pela sua idade, do **mais novo para o mais velho**.
Caso existam dois passageiros com a mesma idade (mesma data de nascimento), devem ser apresentados em primeiro lugar os
passageiros com o menor ID.

O resultado desta operação deve gerar:

* Caso não existam passageiros registados: É lançada a exceção `AppNoPassengersRegistered`.
* Caso existam passageiros registados:

    * Apresentar como Cabeçalho da lista a String retornada de `listaPassageirosHeader(int numPassageiros)`.
    * Por cada Passageiro existente, apresentar o retorno da função
      `String listaPassageirosItem(int id, String nome, String dataNascimento, String categoria, int numItenerarios)`.

> A data de nascimento deve ser apresentada no formato `yyyy-MM-dd` e a categoria como: `Normal`, `Frequente` ou
`Especial`.

```text

MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 4
------- Lista de Passageiros [75] | Data: 2025-07-28 -------
-> [9] Rafael Gomes - 2006-09-19 (18) - Normal - 0 itenerário(s) (0,00 €)
-> [8] Charlotte Martinez - 2006-08-14 (18) - Normal - 0 itenerário(s) (0,00 €)
-> [7] Tiago Almeida - 2006-07-09 (19) - Normal - 0 itenerário(s) (0,00 €)
-> [6] Emily Wilson - 2006-06-04 (19) - Normal - 0 itenerário(s) (0,00 €)

```

```text
MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 4
Listar Passageiros (por idade): Operação inválida: Não existem passageiros registados na aplicação

```

---

#### 5. Listar Passageiros (por viagens efetuadas)

Permite listar todos os passageiros, ordenados pelo **valor total das viagens realizadas**, do **menor para o maior**.
Caso existam dois passageiros com o mesmo valor total de viagens, devem ser apresentados em primeiro lugar os
passageiros com o menor ID.

O resultado desta operação deve gerar:

* Caso não existam passageiros registados: É lançada a exceção `AppNoPassengersRegistered`.
* Caso existam passageiros registados:

    * Apresentar como Cabeçalho da lista a String retornada de `listaPassageirosHeader(int numPassageiros)`.
    * Por cada Passageiro existente, apresentar o retorno da função
      `String listaPassageirosItem(int id, String nome, String dataNascimento, String categoria, int numItenerarios)`.

> A data de nascimento deve ser apresentada no formato `yyyy-MM-dd` e a categoria como: `Normal`, `Frequente` ou
`Especial`.

**Listagem quando não existem viagens (Itenerários) realizados por passageiros**

```text
MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 5
------- Lista de Passageiros [75] | Data: 2025-07-28 -------
-> [1] Julia Ramos - 2001-01-10 (24) - Normal - 0 itenerário(s) (0,00 €)
-> [2] James Williams - 2006-02-15 (19) - Normal - 0 itenerário(s) (0,00 €)
-> [3] Beatriz Sousa - 2006-03-20 (19) - Normal - 0 itenerário(s) (0,00 €)
-> [4] Robert Miller - 2006-04-25 (19) - Normal - 0 itenerário(s) (0,00 €)
-> [5] Marcos Pereira - 2006-05-30 (19) - Normal - 0 itenerário(s) (0,00 €)
...
```

```text
MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 5
Listar Passagiros (por valor viagens): Operação inválida: Não existem passageiros registados na aplicação

```

---

#### 6. Alterar Passageiro (nome)

Com esta funcionalidade pretende-se dar a possibilidade de alterar o nome de um passageiro.
Para o efeito deve se solicitado o ID do passageiro (`Labels.ASK_ID_PASSAGEIRO`) e:

- Caso o passageiro exista:
    - Deve ser solicitado o novo nome do passageiro através da `Labels.ASK_NOME_PASSAGEIRO`;
    - Deve ser alterado o nome do passageiro e posteriormente deve ser escrito no ecrã a mensagem
      `Labels.passageiroAlterado(int id, String nome)`, onde nome se refere ao novo nome do passageiro.
- Caso o passageiro não exista: Deve ser lançada a exceção `exceptions.AppNoSuchPassengerException`

> **Importante:** Só é solicitado o novo nome do passageiro caso o passageiro a alterar (ID) exista.

Exemplos:

```text
MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 6
Insira o id do Passageiro: 5
Insira o nome do Passageiro: Vitor Manuel
Passageiro ID: 5 alterado com sucesso [Vitor Manuel].
```

```text
MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 6
Insira o id do Passageiro: 99
Alterar passageiro (nome): Operação inválida: Passageiro com o id 99 não existe.
```

---

#### 7. Pesquisar Passageiros (por nome)

Permite pesquisar todos os passageiros cujo **nome contenha uma determinada sequência de caracteres** (
case-insensitive), ordenados pelo **ID** em ordem crescente.

O resultado desta operação deve gerar:

* Caso não existam passageiros registados: É lançada a exceção `AppNoPassengersRegistered`.
* Caso não sejam encontrados passageiros que correspondam ao critério de pesquisa:

    * É apresentada a mensagem:
      `Pesquisar passageiros (por nome): Nenhum passageiro encontrado com o critério "<texto_pesquisa>"`
* Caso sejam encontrados passageiros:

    * Apresentar como Cabeçalho da lista a String retornada de `listaPassageirosHeader(int numPassageiros)`.
    * Por cada Passageiro encontrado, apresentar o retorno da função
      `String listaPassageirosItem(int id, String nome, String dataNascimento, String categoria, int numItenerarios)`.

> A data de nascimento deve ser apresentada no formato `yyyy-MM-dd` e a categoria como: `Normal`, `Frequente` ou
`Especial`.

**Exemplos:**

```text
MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 7
Insira o termo de pesquisa: ricardo
------- Lista de Passageiros [1] | Data: 2025-07-28 -------
-> [21] Ricardo Carvalho - 1985-05-15 (40) - Normal - 0 itenerário(s) (0,00 €)
```

```text
MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 7
Insira o termo de pesquisa: mia
------- Lista de Passageiros [5] | Data: 2025-07-28 -------
-> [12] Mia Taylor - 1959-02-10 (66) - Normal - 0 itenerário(s) (0,00 €)
-> [54] Mia Brown - 1992-01-18 (33) - Normal - 0 itenerário(s) (0,00 €)
-> [65] Mia Costa - 1980-08-24 (44) - Normal - 0 itenerário(s) (0,00 €)
-> [70] Mia Silva - 1983-10-27 (41) - Normal - 0 itenerário(s) (0,00 €)
-> [75] Mia Mendes - 1992-02-29 (33) - Normal - 0 itenerário(s) (0,00 €)
```

```text
MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 7
Insira o termo de pesquisa: xpto
Pesquisar passageiros: Operação inválida: Pesquisar passageiros (por nome): Nenhum passageiro encontrado com o critério "xpto"
```

```text
MENU PASSAGEIROS
1 - Registar passageiro
2 - Ver passageiro
3 - Listar passageiros (por nome)
4 - Listar Passageiros (por idade)
5 - Listar Passagiros (por valor viagens)
6 - Alterar passageiro (nome)
7 - Pesquisar passageiros
0 - Sair
Escolha uma opção: 7
Insira o termo de pesquisa: teste
Pesquisar passageiros: Operação inválida: Não existem passageiros registados na aplicação
```

---

### Menu de Consulta de Serviços e Estações

O Menu de serviços agrega as funcionalidades de impressão de informação relacionada com os Serviços.

Para efeitos da simplificação da aplicação, os serviços são carregados através de ficheiros de dados (fornecidos na
pasta `imports`).
Deste modo não existem entradas de menu para criar novos serviços. Consoante o ficheiro de import utilizado, esses são
os serviços que podem ser consumidos pelos
passageiros.

Todas as listagens (Com exceção de imprimir o detalhe de um serviço) deste menu devem imprimir um header no topo da
Listagem e depois uma linha para cada serviço ou estação.

- No caso das listagens de **Serviços** utiliza-se como header a função `Labels.listaServicosHeader(int num)` e como
  item
  `Labels.listaServicosItem(int id,double preco,String c_partida,String h_partida,String c_chegada,String h_chegada)`
- No caso das listagens de **Estações** utiliza-se como header a função `Labels. listaEstaçoesHeader(int num)` e como
  item `Labels.listaEstacoeItem(String nome)`

Nas listagens onde é solicitado o nome da estação (origem, destino, ou servida) e caso essa estação não exista, deve ser
lançada a exceção `app.exceptions.AppNoSuchEstacaoException`.

> **Importante:**
>
> Para efeitos da simplificação da aplicação, os serviços (com as respetivas Estações e Segmentos) são carregados
> através de ficheiros de dados (fornecidos na pasta `imports`).
> Deste modo não existem entradas de menu para que sejam carregados novos serviços. Consoante o ficheiro de import
> utilizado, esses são os serviços que podem ser consumidos pelos
> passageiros. Saiba mais sobre o formato mais abaixo no Capitulo dedicado para o efeito.

> Para este menu, será essencial trabalhar com estruturas de dados como Map ou Set para garantir um registo único das
> estações e uma gestão eficiente dos serviços. As funcionalidades implicam saber ordenar serviços por ID, bem como
> filtrar serviços com base em estações de origem, destino ou passagem. Os alunos terão de compreender a iteração sobre
> coleções complexas, como listas de segmentos associadas a um serviço, e aplicar algoritmos de cálculo, como determinar
> a
> duração total de um serviço e calcular preços de segmentos com base na proporção de tempo. A implementação correta de
> toString() em classes como Servico e Segmento facilitará a impressão organizada de dados.

#### 1. Listar todas as Estações

Esta opção lista todas as Estações Existentes. Não é solicitado nenhum parâmetro ao utilizador sendo impressa
a lista de estações, as quais irão possuir uma ordenação alfabética.

Exemplo de Output esperado:

```text
---- Existem 8 Estações na Lista ----
-> Afife
-> Albergaria-a-Velha
-> Albufeira
-> Alfarelos
-> Aljustrel
-> Alverca
-> Alvito
-> Amadora
```

> Ao carregar os serviços, através do import, terá que efetuar um registo (numa collection) de todas as estações. A
> estação é só identificada pelo seu nome.

#### 2. Listar todos os Serviços

Esta opção de Menu permite mostrar todos os serviços existentes na Base de Dados. Não é solicitado nenhum parametro ao
utilizador.
A listagem de serviços é efetuada por ordem crescente do ID do serviço.

O formato da apresentação de cada serviço é
`Serviço #<id> [€<preco>] <origem> (<hora_origem>) >> <destino> (<hora_destino>)`. Este formato é escrito recorrendo à
função
`Labels.listaServicosItem(int id,double preco,String c_partida,String h_partida,String c_chegada,String h_chegada)`

Exemplo do output esperado:

```textmate
---- Existem 6 serviços na Lista ----
-> Serviço #101 [€6,50] Porto - Campanhã (06:20) >> Espinho (09:00)
-> Serviço #104 [€14,60] Lisboa - Santa Apolónia (07:30) >> Vilar Formoso (11:15)
-> Serviço #128 [€7,60] Porto - Campanhã (14:00) >> Lisboa - Oriente (17:30)
-> Serviço #159 [€12,00] Lisboa - Oriente (16:00) >> Coimbra-B (19:20)
-> Serviço #179 [€13,50] Porto - São Bento (11:30) >> Lisboa - Santa Apolónia (15:45)
-> Serviço #193 [€11,90] Aveiro (08:25) >> Lisboa - Oriente (12:00)
```

#### 3. Imprimir detalhe de um Serviço

Para se imprimir o detalhe de um serviço deve ser solicitado ao utilizador o ID de um serviço. O output desta
funcionalidade deve imprimir no ecrã:

- **Mensagem de Erro** caso não exista o serviço, devendo para tal ser lançada a exceção ``

```text
Insira o ID do serviço: 3
Imprimir detalhe de um Serviço: Operação inválida: Serviço 3 não existe.
```

- **O detalhe do Serviço** caso este exista. Para isso deve ser utilizada a função
  `Labels.ServiceDetailsHeader(int id, double preco)`
  para os detalhes (preço e ID) do serviço e por cada Estação que esse serviço possui deve ser utilizada a função ``

```text
Escolha uma opção: 3
Insira o ID do serviço: 193
Serviço #193 @ €11,90
08:25 Aveiro
09:10 Coimbra-B
09:50 Alfarelos
10:30 Pombal
11:15 Leiria
12:00 Lisboa - Oriente
```

#### 4. Lista de Serviços com início numa Estação

Imprime a Lista de todos os serviços com início numa determinada estação.
Para tal é solicitado o nome da Estação ao Utilizador, através da Label `RequestNomeEstacao`
A impressão da Lista segue as regras descritas wm Listar todos os Serviços (Labels a utilizar e ordenação)

#### 5. Lista de Serviços com fim numa Estação

Imprime a Lista de todos os serviços com chegada numa determinada estação.
Para tal é solicitado o nome da Estação ao Utilizador, através da Label `RequestNomeEstacao`
A impressão da Lista segue as regras descritas wm Listar todos os Serviços (Labels a utilizar e ordenação)

#### 6. Lista de Serviços que servem uma Estação

Imprime a Lista de todos os serviços que passam (servem) numa determinada estação.
Para tal é solicitado o nome da Estação ao Utilizador, através da Label `RequestNomeEstacao`
A impressão da Lista segue as regras descritas wm Listar todos os Serviços (Labels a utilizar e ordenação)

> Importante: A Lista deve conter os serviços que iniciam, terminam e passam em determinada estação (solicitada ao
> utilizador)

#### 7. Lista de Segmentos de um Serviço

Para imprimir a lista de segmentos de um serviço deve ser solicitado ao utilizador o ID de um serviço. O output desta
funcionalidade deve imprimir no ecrã:

- **Mensagem de Erro** caso não exista o serviço, devendo para tal ser lançada a exceção ``

```text
Insira o ID do serviço: 3
Imprimir detalhe de um Serviço: Operação inválida: Serviço 3 não existe.
```

- **O detalhe dos Segmentos** caso este exista. Para isso deve ser utilizada a função
  `Labels.SegmentsDetailsHeader(int id, String from, String to, double preco, int duracao)`
  para os detalhes do serviço e por cada segmento que esse serviço possui deve ser utilizada a função
  `Labels.SegmentoDetailItem(String from, String from_h, String to, String to_h, int minutos, double preco)`

```text
Insira o ID do serviço: 193
Serviço #193 Aveiro >> Lisboa - Oriente @ €11,90 | 215 minutos
-> Aveiro (08:25) >> Coimbra-B (09:10) @ € 2,49 | 45 minutos 
-> Coimbra-B (09:10) >> Alfarelos (09:50) @ € 2,21 | 40 minutos 
-> Alfarelos (09:50) >> Pombal (10:30) @ € 2,21 | 40 minutos 
-> Pombal (10:30) >> Leiria (11:15) @ € 2,49 | 45 minutos 
-> Leiria (11:15) >> Lisboa - Oriente (12:00) @ € 2,49 | 45 minutos 
```

> > Notar que a ordem dos segmentos é a ordem do itenerário do comboio (cronológica).

### Menu de Itenerários

> O menu de itinerários é o mais complexo de se implementar. Combina conceitos de coleções compostas (por exemplo,
> listas de viagens associadas a cada
> passageiro) com cálculos de valores monetários e aplicação de descontos baseados na categoria do passageiro e na sua
> idade. Será importante dominar a lógica de acumulação e cálculo, utilizando métodos como sum() em Streams ou ciclos
> tradicionais para agregar valores de segmentos. Além disso, os alunos terão de lidar com IDs sequenciais por
> passageiro,
> garantindo que cada viagem é registada com o número correto. Aqui, a gestão de objetos interligados (Passageiro,
> Itinerario e Servico) vai reforçar os conceitos de associação entre classes e encapsulamento. Por último terão que ser
> implementados algoritmos que permitam encontrar os itenerários, entre diferentes serviços, que correspondam à
> necessidade dos passageiros.

#### 1. Registar Itinerario

A fornecer posteriormenete

#### 2. Mostrar todos Itinerarios

A fornecer Posteriormente

#### 3. Mostrar Itinerarios de Cliente

A fornecer posteriormente

## Ficheiros de Dados e Importação

A aplicação carrega a informação inicial de **passageiros** e **serviços ferroviários** a partir de ficheiros de dados
no formato `.import`. Esses ficheiros permitem popular rapidamente o sistema com entradas reais, essenciais para testar
as funcionalidades do projeto.

Para o efeito já vem configurado, nas configurações de arranque do projeto (Intellij) a forma de iniciares a tua
aplicação sem imports, ou com imports relacionados com o carregamento de passageiros e/ou serviços

### Função `importFile`

Na classe **`CaminhoFerro`** está definido o método:

```java
public void importFile(String dataFile) throws IOException {
    Scanner ler = new Scanner(new File(dataFile));
    while (ler.hasNextLine()) {
        String linha = ler.nextLine();
        //Ignorar linha de comentários (#) no ficheiro
        if (linha.startsWith("#")) continue;
        String[] elementos = linha.split("\\|");
        switch (elementos[0]) {
            case "PASSENGER":
                //this.registPassageiro(elementos[1], LocalDate.parse(elementos[2]));
                break;

            case "SERVICE":
                /*
                if(!this.criarServico(elementos[1],elementos[2]))
                    break;
                int id=Integer.parseInt(elementos[1]);
                for (int i = 3; i < elementos.length-2; i+=2) {
                    this.adicionarSegmento(id,elementos[i+1],elementos[i], elementos[i+3],elementos[i+2] );
                }
                */
                break;
        }
    }
}
```

**O que já está feito:**

* Leitura linha a linha do ficheiro.
* Ignora linhas de comentários (linhas que começam com `#`).
* Divide cada linha em elementos, utilizando o delimitador `|`.
* Identifica se a linha corresponde a um **passageiro** (`PASSENGER`) ou um **serviço** (`SERVICE`).

**O que tens de implementar:**

* Ativar as linhas comentadas no `switch`.
* Criar os métodos que implementam a funcionalidade. No código documentado encontra-se invocado um conjunto de métodos,
  como se mostra abaixo, mas nada te impede de criares outros diferentes:

    1. `registPassageiro(String nome, LocalDate dataNascimento)`
    2. `criarServico(String id, String descricao)`
    3. `adicionarSegmento(int id, String horaFim, String estacaoFim, String horaInicio, String estacaoInicio)`

---

### Estrutura dos Ficheiros `.import`

Em todos os ficheiros existem dois tipos de registos: `PASSENGER` e `SERVICE`. As linhas iniciadas com `#` são
comentários e são ignorados

---

**PASSENGER**

Cada passageiro tem a seguinte estrutura:

```
PASSENGER|<nome>|<data_nascimento>
```

Exemplo:

```
PASSENGER|Ana Silva|2000-05-14
```

Ao ler esta linha, deverás invocar a seguinte função, ou outra que pretendas criar. No fundo estamos a automatizar a
criação de passageiros a partir da importação de dados. repara que cada linha possui todos os dados necessário à criação
de um passageiro:

```java
this.registPassageiro(elementos[1], LocalDate.parse(elementos[2]));
```

---

**SERVICE**

Um serviço é definido pelo seu identificador, descrição e um conjunto de segmentos com estações e horários.
Formato:

```
SERVICE|<id>|<descricao>|<estacao_inicio>|<hora_inicio>|<estacao_fim>|<hora_fim>|...
```

Exemplo:

```
SERVICE|1|Alfa Pendular|Lisboa|08:00|Coimbra|09:30|Porto|11:00
```

Para cada serviço:

* Cria-se o serviço com `criarServico`, ou outra função que entendas criar.
* Adicionam-se os segmentos (pares de estação e hora) com `adicionarSegmento`.

---

**Dica: Coleção de Estações**

Dado que a aplicação possui funcionalidades como **"Listar todas as estações"**, **"Listar serviços que servem uma
estação"** e outras consultas que dependem das estações, será **boa prática manter uma coleção global de estações**.
Deves sempre analisar as funcionalidades globais de uma aplicação antes de te decidires pela tua estrutura de classes.

**Sugestão de implementação:**

* Sempre que adicionares um segmento (na leitura de um `SERVICE`), verifica se as estações já estão registadas.
  Caso não estejam, adiciona-as a uma coleção (por exemplo, `Map<String> estacoes` para evitar duplicados).
* Esta coleção permitirá responder rapidamente às consultas do menu **Consulta de Serviços e Estações**.

---

### Exemplo de Ficheiros

Exemplo de `passengers10.import`:

```
# Lista de passageiros
PASSENGER|Ana Silva|2000-05-14
PASSENGER|João Costa|1999-11-30
```

Exemplo de `services10.import`:

```
# Serviços ferroviários
SERVICE|1|Alfa Pendular|Lisboa|08:00|Coimbra|09:30|Porto|11:00
```

---

## Regras Globais para o desenvolvimento

O projeto é distribuído com um conjunto de código efetuado, impondo-se as seguintes regras:

- Package `core`
    - Não é permitido alterar o nome das classes `CaminhoFerro` e `Bilheteira`contontudo as mesmas podem e devem ser
      completadas e podem ser criadas todas as outras Classes, Exceções, Enum e/ou interfaces que se considerem
      necessárias;
    - Todos os atributos e métodos já implementados nas classes referidas acima não podem mudar de nome. Contudo pode
      alterar o código relativo à importanção de dados;
    - O package `core` não pode invocar métodos da `ui` ou da `app`. Contudo na `app` podemos declarar Classes do `core`
      para se poderem receber objetos (por exemplo um objeto Passageiro)
- Package `app`
    - Não se podem alterar as Interfaces `Labels` e as Classes `Menu`
    - Não podem ser criadas ou alteradas as Exceções
    - Na `app` ou alunos só devem completar as Classes `Comando` que possuem o nome como `DOxxxxxxxxx`.
    - Não é necessário criar qualque classe no package `app`
- Package `ui`
    - Não são autorizadas quaisquer alterações. O Vosso programa irá ser testado com a ui do docente, pelo que qualquer
      alteração que façam pode implicar a não passagem dos testes.

> Algum erro, ou alteração necessária a fazer no package `app` e/ou `ui` será efetuada pelo formador e comunicado a
> todos os grupos. Qualquer dúvida não hesitem e perguntem

## Avaliação

O projeto será avaliado com uma nota de 0 a 20, correspondendo a 60% da avaliação finão da UC

O projeto final será avaliado com a seguinte distribuição:

- **Através da execução de testes: Até 16 valores**
    - Funcionalidades Menu Passageiros (ainda sem a criação de itenerários): até 4 valores;
    - Funcionalidades Menu Serviços: até 7 valores;
    - Funcionalidades do Menu Itinerários: até 4 valores
    - Funcionalidades do Menu Principal: até 1 valor

- **Análise do código: Até 4 valores**
    - Comentários
    - Abstração
    - Organização
    - Opções tomadas na definição das classes e utilização dos conceitos de POO (Exceções, Interfaces, Heranla,
      Poliformismo, etc)

## Testes à aplicação

No presente enunciado já possuis muitos testes à aplicação. É sempre responsabilidade do programador testar a aplicação
o mais exaustivamente possível.

Contudo, brevemente serão disponibilizados testes mais intensivos à aplicação.
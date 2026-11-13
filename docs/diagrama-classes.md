# Diagrama de Classes — A Torre do Rei Esquecido

Diagramas em Mermaid (renderizados automaticamente pelo GitHub). Para facilitar a leitura, o sistema foi dividido em dois diagramas:

1. **Modelo**: criaturas (personagens e inimigos) e itens.
2. **Fluxo**: jogo, combate e interface de console.

Também há versões em imagem: [`img/diagrama-modelo.png`](img/diagrama-modelo.png) e [`img/diagrama-fluxo.png`](img/diagrama-fluxo.png).

Legenda: seta com triângulo e linha cheia = herança; triângulo com linha tracejada = implementa interface; seta simples = associação (o objeto guarda uma referência); seta tracejada = dependência (usa ou cria); losango vazio = agregação.
Getters triviais foram omitidos para não poluir o desenho. A classe `Main` (ponto de entrada, que monta e injeta os objetos) também foi omitida.

## 1. Modelo: criaturas e itens

```mermaid
classDiagram
    direction TB

    class IAtacante {
        <<interface>>
        +getNome() String
        +getAtaque() int
    }
    class IAlvo {
        <<interface>>
        +getNome() String
        +getVida() int
        +estaVivo() boolean
        +receberDano(int) int
        +receberDanoIgnorandoDefesa(int) int
    }
    class Criatura {
        <<abstract>>
        -String nome
        -int vidaMaxima
        -int vida
        -int ataque
        -int defesa
        -int bonusAtaqueTemporario
        +receberDano(int) int
        +receberDanoIgnorandoDefesa(int) int
        +curar(int) int
        +aumentarAtaque(int)
        +aumentarDefesa(int)
        +reduzirDefesa(int)
        +aplicarBonusAtaqueTemporario(int)
        +removerBonusTemporarios()
        -perderVida(int) int
    }

    class IHabilidadeEspecial {
        <<interface>>
        +getNomeHabilidade() String
        +usarHabilidade(IAlvo) String
    }
    class Personagem {
        <<abstract>>
    }
    class Guerreiro {
        -int CUSTO_VIDA$
        +usarHabilidade(IAlvo) String
    }
    class Mago {
        +usarHabilidade(IAlvo) String
    }
    class Arqueiro {
        +usarHabilidade(IAlvo) String
    }
    class TipoPersonagem {
        <<enumeration>>
        GUERREIRO
        MAGO
        ARQUEIRO
        +getRotulo() String
        +getDescricao() String
        +criar(String) Personagem
    }

    class Inimigo {
        <<abstract>>
        +agir(Personagem) String
    }
    class Esqueleto
    class Golem {
        -boolean carregando
        +agir(Personagem) String
    }
    class ReiEsquecido {
        -int turno
        +agir(Personagem) String
    }

    class IItem {
        <<interface>>
        +getNome() String
        +getDescricao() String
        +usar(Personagem) String
    }
    class PocaoCura {
        +usar(Personagem) String
    }
    class ElixirForca {
        +usar(Personagem) String
    }
    class Equipamento {
        -String nome
        -int bonusAtaque
        -int bonusDefesa
        +usar(Personagem) String
    }
    class Inventario {
        -List~IItem~ itens
        +adicionar(IItem)
        +retirar(int) IItem
        +estaVazio() boolean
        +getItens() List~IItem~
    }

    IAtacante <|.. Criatura
    IAlvo <|.. Criatura
    Criatura <|-- Personagem
    Criatura <|-- Inimigo
    IHabilidadeEspecial <|.. Personagem
    Personagem <|-- Guerreiro
    Personagem <|-- Mago
    Personagem <|-- Arqueiro
    Inimigo <|-- Esqueleto
    Inimigo <|-- Golem
    Inimigo <|-- ReiEsquecido

    IItem <|.. PocaoCura
    IItem <|.. ElixirForca
    IItem <|.. Equipamento
    Inventario o-- IItem : guarda

    IItem ..> Personagem : usar(alvo)
    Inimigo ..> Personagem : agir(alvo)
    IHabilidadeEspecial ..> IAlvo : usarHabilidade(alvo)
    TipoPersonagem ..> Personagem : cria
```

## 2. Fluxo: jogo, combate e interface de console

```mermaid
classDiagram
    direction TB

    class Jogo {
        -List~Andar~ andares
        -Inventario inventario
        -IEntradaHeroi entradaHeroi
        -IEntradaJogador entradaJogador
        -ISaida saida
        +iniciar()
        -mostrarAbertura()
        -criarHeroi() Personagem
        -prepararHeroi(Personagem)
    }
    class Andar {
        -Inimigo inimigo
        -IItem recompensa
        +getInimigo() Inimigo
        +getRecompensa() IItem
        +temRecompensa() boolean
    }
    class Combate {
        -Personagem heroi
        -Inimigo inimigo
        -Inventario inventario
        -IEntradaJogador entrada
        -ISaida saida
        +executar() ResultadoCombate
        -turnoDoHeroi() ResultadoCombate
        -turnoDoInimigo() ResultadoCombate
        -usarItem() boolean
        -atacar(IAtacante, IAlvo) String
        -status() String
    }

    class ISaida {
        <<interface>>
        +mostrar(String)
    }
    class IEntradaJogador {
        <<interface>>
        +escolherAcao() AcaoCombate
        +escolherItem(List~IItem~) int
    }
    class IEntradaHeroi {
        <<interface>>
        +escolherTipo() TipoPersonagem
        +lerNome() String
    }
    class ConsoleUI {
        -Scanner scanner
        +mostrar(String)
        +escolherAcao() AcaoCombate
        +escolherItem(List~IItem~) int
        +escolherTipo() TipoPersonagem
        +lerNome() String
        -lerOpcao(int, int) int
    }

    class AcaoCombate {
        <<enumeration>>
        ATACAR
        HABILIDADE
        USAR_ITEM
        FUGIR
    }
    class ResultadoCombate {
        <<enumeration>>
        VITORIA
        DERROTA
        FUGA
    }

    ISaida <|.. ConsoleUI
    IEntradaJogador <|.. ConsoleUI
    IEntradaHeroi <|.. ConsoleUI

    Jogo "1" --> "1..*" Andar : percorre
    Jogo --> Inventario
    Jogo --> IEntradaHeroi
    Jogo --> IEntradaJogador
    Jogo --> ISaida
    Jogo ..> Combate : cria um por andar

    Combate --> Personagem : heroi
    Combate --> Inimigo
    Combate --> Inventario
    Combate --> IEntradaJogador
    Combate --> ISaida
    Combate ..> ResultadoCombate : devolve
    Combate ..> IAtacante : atacar()
    Combate ..> IAlvo : atacar()

    Andar --> Inimigo
    Andar --> IItem
    IEntradaJogador ..> AcaoCombate
    IEntradaJogador ..> IItem
    IEntradaHeroi ..> TipoPersonagem
```

As classes `Personagem`, `Inimigo`, `Inventario`, `IItem`, `IAtacante`, `IAlvo` e `TipoPersonagem` aparecem sem detalhes neste segundo diagrama porque já foram detalhadas no primeiro.

package jogo;

import personagens.TipoPersonagem;

// Responsabilidade: obter do jogador a classe e o nome do herói. (I) (D)
// Interface separada de IEntradaJogador: o Combate não precisa conhecer a criação do herói.
public interface IEntradaHeroi {
    TipoPersonagem escolherTipo();
    String lerNome();
}

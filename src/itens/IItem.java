package itens;

import personagens.Personagem;

// Responsabilidade: contrato de qualquer item usável pelo herói.
// (D - Inversão de Dependência) O Inventario e o Combate vão depender desta abstração,
// nunca de PocaoCura, ElixirForca etc.
// (O - Aberto/Fechado) Novo item = nova classe que implementa IItem.
public interface IItem {
    String getNome();
    String getDescricao();
    String usar(Personagem alvo); // aplica o efeito e devolve o texto do que aconteceu
}

package itens;

import personagens.Personagem;

// Consumível: bônus de ataque que dura até o fim do combate atual (ou do próximo, se usado entre andares).
public class ElixirForca implements IItem {
    private static final int BONUS = 10;

    @Override public String getNome()      { return "Elixir de Força"; }
    @Override public String getDescricao() { return "+" + BONUS + " de ataque até o fim do combate"; }

    @Override
    public String usar(Personagem alvo) {
        alvo.aplicarBonusAtaqueTemporario(BONUS);
        return alvo.getNome() + " bebe o Elixir de Força e ganha +" + BONUS + " de ataque!";
    }
}

package itens;

import personagens.Personagem;

// Equipamento (ex.: Espada de Ferro, Escudo de Madeira): bônus permanente de ataque e/ou defesa.
// Uma única classe configurável por parâmetros, porque o comportamento é o mesmo; só os números mudam.
public class Equipamento implements IItem {
    private final String nome;
    private final int bonusAtaque;
    private final int bonusDefesa;

    public Equipamento(String nome, int bonusAtaque, int bonusDefesa) {
        this.nome = nome;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
    }

    @Override public String getNome() { return nome; }

    @Override
    public String getDescricao() {
        return "+" + bonusAtaque + " ataque, +" + bonusDefesa + " defesa (permanente)";
    }

    @Override
    public String usar(Personagem alvo) {
        alvo.aumentarAtaque(bonusAtaque);
        alvo.aumentarDefesa(bonusDefesa);
        return alvo.getNome() + " equipa " + nome + " (" + getDescricao() + ").";
    }
}

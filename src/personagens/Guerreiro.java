package personagens;

import combate.IAlvo;

public class Guerreiro extends Personagem {
    private static final int CUSTO_VIDA = 10;

    public Guerreiro(String nome) { super(nome, 120, 20, 10); }

    @Override public String getNomeHabilidade() { return "Golpe Poderoso"; }

    @Override
    public String usarHabilidade(IAlvo alvo) {
        // paga o custo sem se matar (sempre sobra 1 de vida)
        receberDanoIgnorandoDefesa(Math.min(CUSTO_VIDA, getVida() - 1));
        int dano = alvo.receberDano(getAtaque() * 2);
        return getNome() + " usa Golpe Poderoso e causa " + dano + " de dano!";
    }
}

package personagens;

import combate.IAlvo;

public class Mago extends Personagem {
    public Mago(String nome) { super(nome, 70, 30, 3); }

    @Override public String getNomeHabilidade() { return "Bola de Fogo"; }

    @Override
    public String usarHabilidade(IAlvo alvo) {
        int dano = alvo.receberDanoIgnorandoDefesa(getAtaque());
        return getNome() + " lança Bola de Fogo e causa " + dano + " de dano!";
    }
}

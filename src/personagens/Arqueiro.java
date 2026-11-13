package personagens;

import combate.IAlvo;

public class Arqueiro extends Personagem {
    public Arqueiro(String nome) { super(nome, 90, 22, 6); }

    @Override public String getNomeHabilidade() { return "Tiro Duplo"; }

    @Override
    public String usarHabilidade(IAlvo alvo) {
        int dano = alvo.receberDano(getAtaque()) + alvo.receberDano(getAtaque());
        return getNome() + " usa Tiro Duplo e causa " + dano + " de dano!";
    }
}

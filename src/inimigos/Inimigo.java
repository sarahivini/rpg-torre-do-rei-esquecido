package inimigos;

import combate.Criatura;
import personagens.Personagem;

// Responsabilidade: criatura controlada pelo jogo; decide o que fazer no próprio turno.
// O comportamento padrão é um ataque simples; subclasses sobrescrevem agir() para variar.
public abstract class Inimigo extends Criatura {
    protected Inimigo(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    // Executa a ação do turno e devolve o texto do que aconteceu.
    public String agir(Personagem alvo) {
        int dano = alvo.receberDano(getAtaque());
        return getNome() + " ataca " + alvo.getNome() + " e causa " + dano + " de dano.";
    }
}

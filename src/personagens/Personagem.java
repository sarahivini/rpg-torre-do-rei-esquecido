package personagens;

import combate.Criatura;

// Responsabilidade: criatura controlada pelo jogador, com habilidade especial. (L - Liskov)
public abstract class Personagem extends Criatura implements IHabilidadeEspecial {
    protected Personagem(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }
}

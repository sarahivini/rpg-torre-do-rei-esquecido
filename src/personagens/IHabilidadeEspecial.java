package personagens;

import combate.IAlvo;

// Responsabilidade: habilidade especial. Só Personagem implementa;
// Esqueleto e Golem não são obrigados a ter métodos que não usam. (I)
public interface IHabilidadeEspecial {
    String getNomeHabilidade();
    String usarHabilidade(IAlvo alvo); // devolve o texto do que aconteceu
}

package inimigos;

import personagens.Personagem;

// Muita defesa e ataque lento: carrega num turno e ataca forte no seguinte.
public class Golem extends Inimigo {
    private boolean carregando = false;

    public Golem() { super("Golem de Pedra", 80, 24, 8); }

    @Override
    public String agir(Personagem alvo) {
        if (!carregando) {
            carregando = true;
            return getNome() + " ergue os punhos de pedra lentamente...";
        }
        carregando = false;
        return super.agir(alvo);
    }
}

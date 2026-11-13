package inimigos;

import personagens.Personagem;

// Chefe: ataque normal e, a cada 3 turnos, lança a Maldição, que reduz a defesa do herói.
// (O) Esse comportamento novo não exigiu nenhuma alteração no Combate.
public class ReiEsquecido extends Inimigo {
    private static final int REDUCAO_MALDICAO = 3;
    private int turno = 0;

    public ReiEsquecido() { super("Rei Esquecido", 120, 20, 6); }

    @Override
    public String agir(Personagem alvo) {
        turno++;
        if (turno % 3 == 0) {
            alvo.reduzirDefesa(REDUCAO_MALDICAO);
            return getNome() + " lança a Maldição! A defesa de " + alvo.getNome()
                    + " cai " + REDUCAO_MALDICAO + " pontos.";
        }
        return super.agir(alvo);
    }
}

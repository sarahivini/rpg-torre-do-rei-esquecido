package itens;

import personagens.Personagem;

// Consumível: recupera vida.
public class PocaoCura implements IItem {
    private static final int CURA = 40;

    @Override public String getNome()      { return "Poção de Cura"; }
    @Override public String getDescricao() { return "Recupera " + CURA + " de vida"; }

    @Override
    public String usar(Personagem alvo) {
        int curado = alvo.curar(CURA);
        return alvo.getNome() + " bebe a Poção de Cura e recupera " + curado + " de vida.";
    }
}

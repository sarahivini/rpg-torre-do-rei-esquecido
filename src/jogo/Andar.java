package jogo;

import inimigos.Inimigo;
import itens.IItem;

// Responsabilidade: descrever UM andar da torre (qual inimigo mora ali e qual item o herói ganha ao vencê-lo).
// Só guarda dados; não tem lógica de jogo. O chefe final não dá recompensa (recompensa = null).
public class Andar {
    private final Inimigo inimigo;
    private final IItem recompensa;

    public Andar(Inimigo inimigo, IItem recompensa) {
        this.inimigo = inimigo;
        this.recompensa = recompensa;
    }

    // Andar sem recompensa (o do chefe final).
    public Andar(Inimigo inimigo) {
        this(inimigo, null);
    }

    public Inimigo getInimigo()    { return inimigo; }
    public IItem getRecompensa()   { return recompensa; }
    public boolean temRecompensa() { return recompensa != null; }
}

package combate;

// Responsabilidade: guardar atributos de combate e controlar suas mudanças (dano, cura, bônus).
// (S - Responsabilidade Única) Não imprime nada e não decide turnos.
// Encapsulamento: atributos privados; só mudam por métodos que aplicam as regras
// (a vida nunca passa do máximo, a defesa nunca fica negativa).
public abstract class Criatura implements IAtacante, IAlvo {
    private final String nome;
    private final int vidaMaxima;
    private int vida;
    private int ataque;
    private int defesa;
    private int bonusAtaqueTemporario;

    protected Criatura(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vidaMaxima = vida;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    @Override public String getNome()   { return nome; }
    @Override public int getVida()      { return vida; }
    public int getVidaMaxima()          { return vidaMaxima; }
    @Override public int getAtaque()    { return ataque + bonusAtaqueTemporario; }
    public int getDefesa()              { return defesa; }
    @Override public boolean estaVivo() { return vida > 0; }

    // ---- dano ----
    @Override
    public int receberDano(int dano) {
        int danoReal = Math.max(1, dano - defesa); // sempre causa pelo menos 1
        return perderVida(danoReal);
    }

    @Override
    public int receberDanoIgnorandoDefesa(int dano) {
        return perderVida(dano);
    }

    // ---- cura e bônus (usados pelos itens) ----
    public int curar(int quantidade) {
        int antes = vida;
        vida = Math.min(vidaMaxima, vida + quantidade);
        return vida - antes; // cura efetivamente aplicada
    }

    public void aumentarAtaque(int valor)  { ataque += valor; }   // permanente (equipamento)
    public void aumentarDefesa(int valor)  { defesa += valor; }   // permanente (equipamento)
    public void reduzirDefesa(int valor)   { defesa = Math.max(0, defesa - valor); } // Maldição

    public void aplicarBonusAtaqueTemporario(int valor) { bonusAtaqueTemporario += valor; }
    public void removerBonusTemporarios()               { bonusAtaqueTemporario = 0; }

    // private: só a própria classe mexe na vida
    private int perderVida(int quantidade) {
        int antes = vida;
        vida = Math.max(0, vida - quantidade);
        return antes - vida; // dano efetivamente causado
    }
}

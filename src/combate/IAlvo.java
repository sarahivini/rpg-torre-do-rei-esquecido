package combate;

// Responsabilidade: quem pode ser atacado. (I - Segregação de Interfaces)
public interface IAlvo {
    String getNome();
    int getVida();
    boolean estaVivo();
    int receberDano(int dano);                  // aplica a defesa; retorna o dano real
    int receberDanoIgnorandoDefesa(int dano);   // usado pela Bola de Fogo
}

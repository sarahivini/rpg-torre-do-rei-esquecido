package combate;

// Responsabilidade: mostrar uma mensagem ao jogador. (I - interface minúscula, um método só)
// (D) O Combate depende desta abstração, não do System.out. Quem implementa (ConsoleUI) fica na camada ui.
public interface ISaida {
    void mostrar(String mensagem);
}

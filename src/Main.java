import inimigos.Esqueleto;
import inimigos.Golem;
import inimigos.ReiEsquecido;
import itens.ElixirForca;
import itens.Equipamento;
import itens.Inventario;
import itens.PocaoCura;
import jogo.Andar;
import jogo.Jogo;
import ui.ConsoleUI;
import java.util.List;

// Ponto de entrada. É o ÚNICO lugar que conhece as classes concretas e "monta" o jogo (injeção de dependência).
public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();

        // Os 3 andares: inimigo + item ganho ao vencê-lo (o chefe não dá recompensa).
        List<Andar> andares = List.of(
                new Andar(new Esqueleto(), new PocaoCura()),
                new Andar(new Golem(), new ElixirForca()),
                new Andar(new ReiEsquecido()));

        // Inventário inicial do herói.
        Inventario inventario = new Inventario();
        inventario.adicionar(new Equipamento("Espada de Ferro", 5, 0));

        // O mesmo objeto ui cumpre três papéis: escolher o herói, receber ações e mostrar texto.
        Jogo jogo = new Jogo(andares, inventario, ui, ui, ui);
        jogo.iniciar();
    }
}

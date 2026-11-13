package jogo;

import combate.Combate;
import combate.IEntradaJogador;
import combate.ISaida;
import combate.ResultadoCombate;
import itens.IItem;
import itens.Inventario;
import personagens.Personagem;
import java.util.List;

// Responsabilidade: o FLUXO da partida: criar o herói, subir andar por andar, dar recompensas
// e decidir como o jogo termina. (S) Quem luta é o Combate; quem lê/escreve é a UI.
// Tudo chega pelo construtor (D): a lista de andares, o inventário e as interfaces de entrada/saída.
// (O) Para ter um quarto andar, basta acrescentar um Andar na lista (em Main): o Jogo não muda.
public class Jogo {
    private final List<Andar> andares;
    private final Inventario inventario;
    private final IEntradaHeroi entradaHeroi;
    private final IEntradaJogador entradaJogador;
    private final ISaida saida;

    public Jogo(List<Andar> andares, Inventario inventario, IEntradaHeroi entradaHeroi,
                IEntradaJogador entradaJogador, ISaida saida) {
        this.andares = andares;
        this.inventario = inventario;
        this.entradaHeroi = entradaHeroi;
        this.entradaJogador = entradaJogador;
        this.saida = saida;
    }

    public void iniciar() {
        mostrarAbertura();
        Personagem heroi = criarHeroi();

        for (int i = 0; i < andares.size(); i++) {
            Andar andar = andares.get(i);
            boolean ultimoAndar = (i == andares.size() - 1);

            saida.mostrar("");
            saida.mostrar("========== ANDAR " + (i + 1) + " de " + andares.size() + " ==========");
            prepararHeroi(heroi);

            Combate combate = new Combate(heroi, andar.getInimigo(), inventario, entradaJogador, saida);
            ResultadoCombate resultado = combate.executar();

            if (resultado == ResultadoCombate.DERROTA) {
                saida.mostrar(heroi.getNome() + " tomba na torre e a névoa engole Eldoria. GAME OVER.");
                return;
            }
            if (resultado == ResultadoCombate.FUGA) {
                saida.mostrar(heroi.getNome() + " abandona a torre. A névoa continua sobre Eldoria. FIM DE JOGO.");
                return;
            }

            saida.mostrar(andar.getInimigo().getNome() + " foi derrotado!");
            if (!ultimoAndar && andar.temRecompensa()) {
                inventario.adicionar(andar.getRecompensa());
                saida.mostrar("Você encontrou: " + andar.getRecompensa().getNome() + "!");
            }
        }

        saida.mostrar("");
        saida.mostrar("O Rei Esquecido cai e a névoa sobre Eldoria se dissipa. A maldição foi quebrada!");
        saida.mostrar("*** VITÓRIA! ***");
    }

    private void mostrarAbertura() {
        saida.mostrar("===== A TORRE DO REI ESQUECIDO =====");
        saida.mostrar("O reino de Eldoria está coberto por uma névoa sombria que vem da Torre do Rei Esquecido.");
        saida.mostrar("Seu herói vai subir a torre, andar por andar, até enfrentar o Rei e quebrar a maldição.");
        saida.mostrar("");
    }

    private Personagem criarHeroi() {
        // A UI devolve a escolha; quem cria o objeto é o TipoPersonagem (a UI não instancia classes do domínio).
        var tipo = entradaHeroi.escolherTipo();
        String nome = entradaHeroi.lerNome();
        return tipo.criar(nome);
    }

    // Entre um andar e outro (e antes do primeiro), o jogador pode usar itens. Escolher "0" segue em frente.
    private void prepararHeroi(Personagem heroi) {
        while (!inventario.estaVazio()) {
            saida.mostrar("Você pode usar um item antes de enfrentar o inimigo (0 para seguir em frente):");
            int indice = entradaJogador.escolherItem(inventario.getItens());
            if (indice < 0) {
                return;
            }
            IItem item = inventario.retirar(indice);
            saida.mostrar(item.usar(heroi));
        }
    }
}

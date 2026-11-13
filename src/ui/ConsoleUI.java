package ui;

import combate.AcaoCombate;
import combate.IEntradaJogador;
import combate.ISaida;
import itens.IItem;
import jogo.IEntradaHeroi;
import personagens.TipoPersonagem;
import java.util.List;
import java.util.Scanner;

// Responsabilidade: TODA a entrada e saída de texto do jogo (teclado e console). (S)
// Não tem regra de jogo: não calcula dano, não decide quem vence.
// Implementa três interfaces pequenas; cada cliente enxerga só a que precisa (I).
public class ConsoleUI implements IEntradaJogador, IEntradaHeroi, ISaida {
    private final Scanner scanner = new Scanner(System.in);

    // ---------- ISaida ----------
    @Override
    public void mostrar(String mensagem) {
        System.out.println(mensagem);
    }

    // ---------- IEntradaHeroi ----------
    @Override
    public TipoPersonagem escolherTipo() {
        TipoPersonagem[] tipos = TipoPersonagem.values();
        System.out.println("Escolha a classe do seu herói:");
        for (int i = 0; i < tipos.length; i++) {
            System.out.println(" " + (i + 1) + ") " + tipos[i].getRotulo() + " - " + tipos[i].getDescricao());
        }
        int opcao = lerOpcao(1, tipos.length);
        return tipos[opcao - 1];
    }

    @Override
    public String lerNome() {
        System.out.print("Qual é o nome do herói? ");
        String nome = scanner.nextLine().trim();
        return nome.isEmpty() ? "Herói" : nome;
    }

    // ---------- IEntradaJogador ----------
    @Override
    public AcaoCombate escolherAcao() {
        System.out.println("Escolha sua ação:");
        System.out.println(" 1) Atacar");
        System.out.println(" 2) Habilidade");
        System.out.println(" 3) Usar item");
        System.out.println(" 4) Fugir");
        int opcao = lerOpcao(1, 4);
        return AcaoCombate.values()[opcao - 1]; // a ordem do enum é a mesma do menu
    }

    @Override
    public int escolherItem(List<IItem> itens) {
        System.out.println("Escolha um item:");
        for (int i = 0; i < itens.size(); i++) {
            System.out.println(" " + (i + 1) + ") " + itens.get(i).getNome()
                    + " - " + itens.get(i).getDescricao());
        }
        System.out.println(" 0) Voltar");
        int opcao = lerOpcao(0, itens.size());
        return opcao - 1; // 0 (Voltar) vira -1, como o Combate espera
    }

    // Pede um número até o jogador digitar algo válido (a UI se protege de entrada ruim).
    private int lerOpcao(int minimo, int maximo) {
        while (true) {
            System.out.print("> ");
            String linha = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(linha);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // não era número: cai na mensagem abaixo
            }
            System.out.println("Opção inválida. Digite um número de " + minimo + " a " + maximo + ".");
        }
    }
}

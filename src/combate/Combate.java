package combate;

import inimigos.Inimigo;
import itens.IItem;
import itens.Inventario;
import personagens.Personagem;

// Responsabilidade: controlar o loop de turnos de UMA luta (herói x inimigo). (S)
// Não lê teclado, não usa System.out e não sabe qual inimigo é qual:
//  - entrada e saída chegam por interfaces, injetadas no construtor (D);
//  - o inimigo decide a própria ação em agir(), então novos inimigos não mexem aqui (O, L).
public class Combate {
    private final Personagem heroi;
    private final Inimigo inimigo;
    private final Inventario inventario;
    private final IEntradaJogador entrada;
    private final ISaida saida;

    public Combate(Personagem heroi, Inimigo inimigo, Inventario inventario,
                   IEntradaJogador entrada, ISaida saida) {
        this.heroi = heroi;
        this.inimigo = inimigo;
        this.inventario = inventario;
        this.entrada = entrada;
        this.saida = saida;
    }

    public ResultadoCombate executar() {
        saida.mostrar("--- " + inimigo.getNome() + " surge diante de " + heroi.getNome() + "! ---");

        ResultadoCombate resultado = null;
        while (resultado == null) {
            saida.mostrar(status());
            resultado = turnoDoHeroi();
            if (resultado == null) {
                resultado = turnoDoInimigo();
            }
        }

        heroi.removerBonusTemporarios(); // o Elixir de Força acaba junto com a luta
        return resultado;
    }

    // Devolve null se a luta continua; senão, o resultado final.
    private ResultadoCombate turnoDoHeroi() {
        while (true) { // repete enquanto o jogador não fizer uma ação válida
            switch (entrada.escolherAcao()) {
                case ATACAR -> {
                    saida.mostrar(atacar(heroi, inimigo));
                    return inimigo.estaVivo() ? null : ResultadoCombate.VITORIA;
                }
                case HABILIDADE -> {
                    saida.mostrar(heroi.usarHabilidade(inimigo)); // polimorfismo: cada classe tem a sua
                    return inimigo.estaVivo() ? null : ResultadoCombate.VITORIA;
                }
                case USAR_ITEM -> {
                    if (usarItem()) {
                        return null; // usar item gasta o turno
                    }
                }
                case FUGIR -> {
                    saida.mostrar(heroi.getNome() + " foge da batalha!");
                    return ResultadoCombate.FUGA;
                }
            }
        }
    }

    private ResultadoCombate turnoDoInimigo() {
        saida.mostrar(inimigo.agir(heroi)); // polimorfismo: Esqueleto, Golem e Rei agem diferente
        return heroi.estaVivo() ? null : ResultadoCombate.DERROTA;
    }

    // true se um item foi usado; false se não havia item ou o jogador desistiu (o turno não é gasto).
    private boolean usarItem() {
        if (inventario.estaVazio()) {
            saida.mostrar("O inventário está vazio.");
            return false;
        }
        int indice = entrada.escolherItem(inventario.getItens());
        if (indice < 0) {
            return false;
        }
        IItem item = inventario.retirar(indice);
        saida.mostrar(item.usar(heroi));
        return true;
    }

    // Ataque básico escrito só com as interfaces (L): serve para qualquer atacante e qualquer alvo.
    private String atacar(IAtacante atacante, IAlvo alvo) {
        int dano = alvo.receberDano(atacante.getAtaque());
        return atacante.getNome() + " ataca " + alvo.getNome() + " e causa " + dano + " de dano.";
    }

    private String status() {
        return "[" + heroi.getNome() + " " + heroi.getVida() + "/" + heroi.getVidaMaxima() + "]  x  ["
                + inimigo.getNome() + " " + inimigo.getVida() + "/" + inimigo.getVidaMaxima() + "]";
    }
}

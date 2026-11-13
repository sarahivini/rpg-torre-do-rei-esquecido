package combate;

import itens.IItem;
import java.util.List;

// Responsabilidade: obter as decisões do jogador durante o combate. (I) (D)
public interface IEntradaJogador {
    AcaoCombate escolherAcao();

    // Devolve a posição do item escolhido na lista, ou -1 se o jogador desistir.
    int escolherItem(List<IItem> itens);
}

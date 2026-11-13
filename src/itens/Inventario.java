package itens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Responsabilidade: guardar e organizar os itens. NÃO usa os itens (quem usa é o Combate/Jogo).
// (S - Responsabilidade Única) (D) Só conhece a abstração IItem.
public class Inventario {
    private final List<IItem> itens = new ArrayList<>(); // private: ninguém mexe na lista direto

    public void adicionar(IItem item) {
        itens.add(item);
    }

    // Remove e devolve o item da posição indicada (o item sai do inventário ao ser usado).
    public IItem retirar(int indice) {
        return itens.remove(indice);
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    // Visão somente leitura: quem recebe não consegue adicionar nem remover.
    public List<IItem> getItens() {
        return Collections.unmodifiableList(itens);
    }
}

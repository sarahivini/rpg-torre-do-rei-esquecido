package personagens;

// Responsabilidade: catálogo das classes jogáveis e fábrica de heróis.
// (O) Para ter uma classe nova (ex.: Clérigo), cria-se a classe e UMA constante aqui.
//     O menu do ConsoleUI percorre values(), então ele aparece sozinho, sem mexer na UI.
public enum TipoPersonagem {
    GUERREIRO("Guerreiro", "vida alta, defesa alta | Golpe Poderoso (dano dobrado, custa vida)") {
        @Override public Personagem criar(String nome) { return new Guerreiro(nome); }
    },
    MAGO("Mago", "vida baixa, ataque alto | Bola de Fogo (ignora a defesa)") {
        @Override public Personagem criar(String nome) { return new Mago(nome); }
    },
    ARQUEIRO("Arqueiro", "atributos equilibrados | Tiro Duplo (dois ataques seguidos)") {
        @Override public Personagem criar(String nome) { return new Arqueiro(nome); }
    };

    private final String rotulo;
    private final String descricao;

    TipoPersonagem(String rotulo, String descricao) {
        this.rotulo = rotulo;
        this.descricao = descricao;
    }

    public String getRotulo()    { return rotulo; }
    public String getDescricao() { return descricao; }

    public abstract Personagem criar(String nome);
}

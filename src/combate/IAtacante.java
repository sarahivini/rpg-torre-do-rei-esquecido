package combate;

// Responsabilidade: quem pode atacar. Só expõe o que o Combate precisa para atacar. (I - Segregação de Interfaces)
public interface IAtacante {
    String getNome();
    int getAtaque();
}

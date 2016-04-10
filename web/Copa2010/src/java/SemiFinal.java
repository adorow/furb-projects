
public class SemiFinal {

    private static final Jogo[] jogos = new Jogo[]{
        new Jogo(QuartasDeFinal.getVencedorJogo58(), QuartasDeFinal.getVencedorJogo57()),
        new Jogo(QuartasDeFinal.getVencedorJogo59(), QuartasDeFinal.getVencedorJogo60())
    };

    public Jogo[] getJogos() {
        return jogos;
    }

    public static Selecao getVencedorJogo61(){
        return jogos[0].getVencedor();
    }

    public static Selecao getVencedorJogo62(){
        return jogos[1].getVencedor();
    }

    public static Selecao getPerdedorJogo61(){
        return getVencedorJogo61() == jogos[0].getSelecao01() ? jogos[0].getSelecao02() : jogos[0].getSelecao01();
    }

    public static Selecao getPerdedorJogo62(){
        return getVencedorJogo62() == jogos[1].getSelecao01() ? jogos[1].getSelecao02() : jogos[1].getSelecao01();
    }
}

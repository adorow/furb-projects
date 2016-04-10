public class QuartasDeFinal {
    
    private static final Jogo[] jogos = new Jogo[]{
        new Jogo(OitavaDeFinal.getVencedorJogo49(), OitavaDeFinal.getVencedorJogo50()),
        new Jogo(OitavaDeFinal.getVencedorJogo53(), OitavaDeFinal.getVencedorJogo54()),
        new Jogo(OitavaDeFinal.getVencedorJogo52(), OitavaDeFinal.getVencedorJogo51()),
        new Jogo(OitavaDeFinal.getVencedorJogo55(), OitavaDeFinal.getVencedorJogo56())
    };

    public Jogo[] getJogos() {
        return jogos;
    }

    public static Selecao getVencedorJogo58(){
        return jogos[0].getVencedor();
    }

    public static Selecao getVencedorJogo57(){
        return jogos[1].getVencedor();
    }

    public static Selecao getVencedorJogo59(){
        return jogos[2].getVencedor();
    }

    public static Selecao getVencedorJogo60(){
        return jogos[3].getVencedor();
    }    
}

public final class Final {

    private static final Jogo[] jogos = new Jogo[]{
        new Jogo(SemiFinal.getVencedorJogo61(), SemiFinal.getVencedorJogo62()),
        new Jogo(SemiFinal.getPerdedorJogo61(), SemiFinal.getPerdedorJogo62())
    };

    public Jogo[] getFinal() {
        return new Jogo[] { jogos[0] };
    }

    public Jogo[] getDisputaDeTerceiro() {
        return new Jogo[] { jogos[1] };
    }

    public Jogo[] getJogos() {
        return jogos;
    }
    
    public Selecao getCampeao(){
        return jogos[0].getVencedor();
    }
}

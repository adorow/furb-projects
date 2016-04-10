
public final class OitavaDeFinal {

    private static final Jogo[] jogos = new Jogo[]{
            new Jogo(Grupos.GrupoA.getPrimeiroDoGrupo(), Grupos.GrupoB.getSegundoDoGrupo()),
            new Jogo(Grupos.GrupoC.getPrimeiroDoGrupo(), Grupos.GrupoD.getSegundoDoGrupo()),
            new Jogo(Grupos.GrupoE.getPrimeiroDoGrupo(), Grupos.GrupoF.getSegundoDoGrupo()),
            new Jogo(Grupos.GrupoG.getPrimeiroDoGrupo(), Grupos.GrupoH.getSegundoDoGrupo()),
            new Jogo(Grupos.GrupoB.getPrimeiroDoGrupo(), Grupos.GrupoA.getSegundoDoGrupo()),
            new Jogo(Grupos.GrupoD.getPrimeiroDoGrupo(), Grupos.GrupoC.getSegundoDoGrupo()),
            new Jogo(Grupos.GrupoF.getPrimeiroDoGrupo(), Grupos.GrupoE.getSegundoDoGrupo()),
            new Jogo(Grupos.GrupoH.getPrimeiroDoGrupo(), Grupos.GrupoG.getSegundoDoGrupo())
    };

    public Jogo[] getJogos() {
        return jogos;
    }

    public static Selecao getVencedorJogo49(){
        return jogos[0].getVencedor();
    }

    public static Selecao getVencedorJogo50(){
        return jogos[1].getVencedor();
    }

    public static Selecao getVencedorJogo53(){
        return jogos[2].getVencedor();
    }

    public static Selecao getVencedorJogo54(){
        return jogos[3].getVencedor();
    }

    public static Selecao getVencedorJogo52(){
        return jogos[4].getVencedor();
    }

    public static Selecao getVencedorJogo51(){
        return jogos[5].getVencedor();
    }

    public static Selecao getVencedorJogo55(){
        return jogos[6].getVencedor();
    }

    public static Selecao getVencedorJogo56(){
        return jogos[7].getVencedor();
    }
    
}

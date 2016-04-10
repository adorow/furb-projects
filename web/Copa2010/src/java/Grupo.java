
import java.util.Comparator;
import java.util.Arrays;

public class Grupo {
    private String nomeGrupo;
    private Selecao[] selecoes;
    private Jogo[] jogos;

    public Grupo(String nomeGrupo, Selecao[] selecoes, Jogo[] jogos) {
        this.nomeGrupo = nomeGrupo;
        this.selecoes = selecoes;
        this.jogos = jogos;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public Selecao[] getSelecoes() {
        Arrays.sort(selecoes, new Comparator<Selecao>() {

            public int compare(Selecao s1, Selecao s2) {
                // criterio: pontos, saldo de gols, gols pro
                if (s2.getPontosGanhos() != s1.getPontosGanhos()) {
                    return s2.getPontosGanhos() - s1.getPontosGanhos();
                }
                if (s2.getSaldoGols() != s2.getSaldoGols()) {
                    return s2.getSaldoGols() - s1.getSaldoGols();
                }
                return s2.getGolsPro() - s1.getGolsPro();
            }

        });
        return selecoes;
    }

    public Jogo[] getJogos() {
        return jogos;
    }

    /* Único critério levado em consideração é a pontuação. Não há critérios de desempate. */
    public Selecao getPrimeiroDoGrupo(){
        //int maiorPontos = Integer.MIN_VALUE;
        //Selecao result = null;
        //for (Selecao selecao : selecoes){
        //    if (selecao.getPontosGanhos() > maiorPontos){
        //        maiorPontos = selecao.getPontosGanhos();
        //        result = selecao;
        //    }
        //}
        //return result;
        return getSelecoes()[0];// getSelecoes ja estah ordenando
    }

    /* Único critério levado em consideração é a pontuação. Não há critérios de desempate. */
    public Selecao getSegundoDoGrupo(){
        //Selecao result = getPrimeiroDoGrupo();
        //int maiorPontos = (result != null) ? result.getPontosGanhos() : Integer.MIN_VALUE;
        //result = null;
        //for (Selecao selecao : selecoes){
        //    if (selecao.getPontosGanhos() > maiorPontos){
        //        maiorPontos = selecao.getPontosGanhos();
        //        result = selecao;
        //    }
        //}
        //return result;
        return getSelecoes()[1];// getSelecoes ja estah ordenando
    }

    public void recalcularResultados() {
        // limpa dados das selecoes
        for (Selecao selecao : selecoes) {
            selecao.setDerrotas((byte) 0);
            selecao.setEmpates((byte) 0);
            selecao.setVitorias((byte) 0);
            selecao.setGolsContra((byte) 0);
            selecao.setGolsPro((byte) 0);
            selecao.setPontosGanhos((byte) 0);
        }
        // recalcula jogos
        for (Jogo jogo : jogos) {
            jogo.fimDoJogo();
        }
    }

}

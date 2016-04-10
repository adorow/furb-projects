
public class Jogo {

    private Selecao selecao01;
    private Selecao selecao02;
    private byte placarSelecao01;
    private byte placarSelecao02;

    public Jogo(Selecao selecao01, Selecao selecao02) {
        this.selecao01 = selecao01;
        this.selecao02 = selecao02;
    }    
    
    public Jogo(byte placarSelecao01, byte placarSelecao02) {
        this.placarSelecao01 = placarSelecao01;
        this.placarSelecao02 = placarSelecao02;
    }

    public byte getPlacarSelecao01() {
        return placarSelecao01;
    }

    public void setPlacarSelecao01(byte placarSelecao01) {
        this.placarSelecao01 = placarSelecao01;
    }

    public byte getPlacarSelecao02() {
        return placarSelecao02;
    }

    public void setPlacarSelecao02(byte placarSelecao02) {
        this.placarSelecao02 = placarSelecao02;
    }

    public void fimDoJogo() {
        Selecao selecaoVencedora = null;
        Selecao selecaoPerdedora = null;

        selecao01.setGolsPro((byte) (selecao01.getGolsPro() + placarSelecao01));
        selecao01.setGolsContra((byte) (selecao01.getGolsContra() + placarSelecao02));
        selecao02.setGolsPro((byte) (selecao02.getGolsPro() + placarSelecao02));
        selecao02.setGolsContra((byte) (selecao02.getGolsContra() + placarSelecao01));

        if (placarSelecao01 > placarSelecao02) {
            selecaoVencedora = selecao01;
            selecaoPerdedora = selecao02;
        } else if (placarSelecao01 < placarSelecao02) {
            selecaoVencedora = selecao02;
            selecaoPerdedora = selecao01;
        } else {
            selecao01.setEmpates((byte)(selecao01.getEmpates() + 1));
            selecao02.setEmpates((byte)(selecao02.getEmpates() + 1));
            selecao01.setPontosGanhos((byte)(selecao01.getPontosGanhos() + 1));
            selecao02.setPontosGanhos((byte)(selecao02.getPontosGanhos() + 1));
            return;
        }

        selecaoVencedora.setVitorias((byte) (selecaoVencedora.getVitorias() + 1));
        selecaoVencedora.setPontosGanhos((byte) (selecaoVencedora.getPontosGanhos() + 3));
        selecaoPerdedora.setDerrotas((byte) (selecaoPerdedora.getDerrotas() + 1));
    }

    public Selecao getVencedor(){
        if (placarSelecao01 > placarSelecao02)
            return selecao01;
        else if (placarSelecao01 < placarSelecao02)
            return selecao02;
        else return null;


    }

    public Selecao getSelecao01() {
        return selecao01;
    }

    public Selecao getSelecao02() {
        return selecao02;
    }
}

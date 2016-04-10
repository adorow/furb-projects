public class Selecao {
    private byte pontosGanhos;   
    private byte vitorias;
    private byte empates;
    private byte derrotas;
    private byte golsPro;
    private byte golsContra;
    private String pais;
    private String bandeira;

    public Selecao(String pais, String bandeira) {
        this.pais = pais;
        this.bandeira = bandeira;
    }    

    public String getPais() {
        return pais;
    }

    public String getBandeira() {
        return "/resources/images/" + bandeira;
    }

    public byte getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(byte derrotas) {
        this.derrotas = derrotas;
    }

    public byte getEmpates() {
        return empates;
    }

    public void setEmpates(byte empates) {
        this.empates = empates;
    }

    public byte getGolsContra() {
        return golsContra;
    }

    public void setGolsContra(byte golsContra) {
        this.golsContra = golsContra;
    }

    public byte getGolsPro() {
        return golsPro;
    }

    public void setGolsPro(byte golsPro) {
        this.golsPro = golsPro;
    }    

    public byte getPontosGanhos() {
        return pontosGanhos;
    }

    public void setPontosGanhos(byte pontosGanhos) {
        this.pontosGanhos = pontosGanhos;
    }

    public byte getVitorias() {
        return vitorias;
    }

    public void setVitorias(byte vitorias) {
        this.vitorias = vitorias;
    }

    public int getSaldoGols(){
        return golsPro - golsContra;
    }

}

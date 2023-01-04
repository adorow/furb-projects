package math;

/**
 * Classe para efetuar calculos de NDC.
 */
public class NDC {

    /**
     * "Traduz" um valor utilizando NDC.
     * 
     * @param oIni o valor de inicio do dispositivo de origem.
     * @param oEnd o valor de fim do dispositivo de origem.
     * @param dIni o valor de inicio do dispositivo de destino.
     * @param dEnd o valor de fim do dispositivo de destino.
     * @param coordinate o ponto de origem a ser "traduzido".
     * @return o ponto informado, na posicao correspondente ao destino.
     */
    public static double translate(double oIni, double oEnd, double dIni, double dEnd, double coordinate) {
        double deltaO = (oEnd - oIni);
        double deltaD = (dEnd - dIni);

        double e = ((coordinate - oIni) * (deltaD / deltaO)) + dIni;
        return e;
    }

    public static double getDiff(double oIni, double oEnd, double dIni, double dEnd) {
        double deltaO = (oEnd - oIni);
        double deltaD = (dEnd - dIni);
        return deltaD / deltaO;
    }

}

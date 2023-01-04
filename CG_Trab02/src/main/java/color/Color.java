package color;

/**
 * Representa uma cor a ser utilizada nos desenhos.
 */
public interface Color {

    /**
     * @return o fator da cor vermelha.
     */
    double red();

    /**
     * @return o fator da cor verde.
     */
    double green();

    /**
     * @return o fator da cor azul.
     */
    double blue();

    /**
     * @return o fator de transparência da cor.
     */
    double alpha();

}

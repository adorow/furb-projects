package view;

/**
 * Define os tipos poss�veis de a��es que podem ser utilizadas no desenho.
 */
public enum DrawAction {

    /** Cria��o de Spline */
    SPLINE,
    /** Cria��o de pol�gono aberto */
    OPEN_POLYGON,
    /** Cria��o de pol�gono fechado */
    CLOSED_POLYGON,
    /** Cria��o de c�rculo */
    CIRCLE,
    /** Sele��o de uma forma */
    SELECTION,
    /** Pan */
    PAN;

}

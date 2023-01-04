package view;

/**
 * Define os tipos possíveis de ações que podem ser utilizadas no desenho.
 */
public enum DrawAction {

    /** Criação de Spline */
    SPLINE,
    /** Criação de polígono aberto */
    OPEN_POLYGON,
    /** Criação de polígono fechado */
    CLOSED_POLYGON,
    /** Criação de círculo */
    CIRCLE,
    /** Seleção de uma forma */
    SELECTION,
    /** Pan */
    PAN;

}

package solarsystem.model;

/**
 * Representa um objeto que pode ser orbitado, ou seja, possui astros orbitando ele.
 */
public interface Orbitable<T extends Star> {

    void addStar(T star);
    void removeStar(T star);
    Star[] orbitingObjects();
    
}

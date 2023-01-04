package solarsystem.model;

/**
 * Representa um "astro".
 */
public interface Star extends SolarSystemObject, OrbitingObject {
    
    double size();
    
    double distanceToCenterOfOrbit();
    
}

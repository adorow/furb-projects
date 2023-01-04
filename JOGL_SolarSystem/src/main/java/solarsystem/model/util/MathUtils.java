package solarsystem.model.util;

public class MathUtils {

	/**
	 * Calcula o proximo angulo a partir do angulo atual e do periodo orbital.
	 */
	public static double calculateNextAngle(double currentAngle, long orbitalPeriod){
		return (currentAngle + (360.0 / orbitalPeriod)) % 360;
	}
	
}

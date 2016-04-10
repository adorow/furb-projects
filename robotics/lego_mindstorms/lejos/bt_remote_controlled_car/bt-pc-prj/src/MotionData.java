
public class MotionData {

	public static final byte GEAR_DELTA = 1;
	public static final short POWER_DELTA = 10;
	public static final short POWER_DELTA_HIGH = 100;
	public static final byte ANGLE_DELTA = 5;
	public static final byte ANGLE_DELTA_HIGH = 45;
	
	private byte gear;
	private short power;
	private byte angle;
	
	public byte getGear() {
		return gear;
	}
	public void setGear(byte gear) {
		byte min = -1;
		byte max = 3;
		
		if (gear < min) gear = min;
		if (gear > max) gear = max;
		
		this.gear = gear;
	}
	public short getPower() {
		return power;
	}
	public void setPower(short power) {
		short min = 0;
		short max = 900;
		
		if (power < min) power = min;
		if (power > max) power = max;
		
		this.power = power;
	}
	public byte getAngle() {
		return angle;
	}
	public void setAngle(byte angle) {
		byte min = -90;
		byte max = 90;
		
		if (angle < min) angle = min;
		if (angle > max) angle = max;
		
		this.angle = angle;
	}
	
	
	
}

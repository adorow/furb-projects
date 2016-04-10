				/*
				 * formato do pacote:
				 * 
				 * ----------------bytes----------------
				 * | byte 0 | byte 1 | byte 2 | byte 3 |
				 * ----------------bits-----------------
				 * |01234567|01234567|01234567|01234567|
				 * --------------legendas---------------
				 * |t..ggggg|pppppppp|pppppppp|aaaaaaaa|
				 * -------------------------------------
				 * 
				 * legenda:
				 * t = tipo de pacote
				 * g = gear
				 * p = power
				 * a = angle
				 * 
				 * - 't' representa o pacote do tipo MOTION_PACKET;
				 * - 'g' representa a marcha, o primeiro bit deste indica se � positivo ou negativo (negativo indica r�), zero significa 'parado';
				 * - 'p' representa a for�a, o quanto se est� pisando no acelerador, por exemplo;
				 * - 'a' representa o angulo para onde se est� virando, onde 0 representa o ponto central, ou seja, o ponto onde n�o h� nenhuma rota��o;
				 */
public class MotionData {

	private byte gear;
	private short power;
	private byte angle;
	
	public byte getGear() {
		return gear;
	}
	public short getPower() {
		return power;
	}
	public byte getAngle() {
		return angle;
	}

	public void setData(int input) {
		gear = (byte) ((input >> 24) & 0x7F);
		power = (short) ((input >> 8) & 0xFFFF);
		angle = (byte) (input & 0xFF);
	}
	
	
}

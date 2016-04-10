
public class MotionPacket implements Packet {

	private byte _gear;
	private short _power;
	private byte _angle;

	public MotionPacket(byte gear, short power, byte angle) {
		_gear = gear;
		_power = power;
		_angle = angle;
	}
	
	@Override
	public byte[] data() {
		byte v0, v1, v2, v3;
		
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
		v0 = (byte) (MOTION_PACKET | (_gear & 0x1F));
		v1 = (byte) ((_power & 0xFF00) >> 8);
		v2 = (byte) (_power & 0x00FF);
		v3 = _angle;
		
		return new byte[] { v0,v1,v2,v3 };
	}


}

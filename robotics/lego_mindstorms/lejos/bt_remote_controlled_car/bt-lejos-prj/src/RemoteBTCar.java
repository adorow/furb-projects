import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class RemoteBTCar {

	private static final byte VISIBLE = 1; // visibility
	private static final String NXT_NAME = "NXT_KIDO";// nome do dispositivo

	// NXT

	public static void main(String[] args) {
		Bluetooth.setFriendlyName(NXT_NAME);

		// deixa o dispositivo 'visivel'
		if (Bluetooth.getVisibility() != VISIBLE) {
			Bluetooth.setVisibility(VISIBLE);
		}

		showInfos();

		BTConnection btConn = waitForConnection();

		int data = 0;

		final Motor motorTraction = Motor.A;
		final Motor motorTurn = Motor.C;
		final Motor motorGear = Motor.B;
		
		MotionData motionData = new MotionData();
		byte gear = 1;
		short power = 0;
		byte angle = 0;

		try {
			LCD.drawString("CONNECTED!!", 0, 7);

			DataInputStream input = btConn.openDataInputStream();
			DataOutputStream output = btConn.openDataOutputStream();
			try {
				// enquanto há algo para ler...
				while (data > -1) {
					sleep(25);

					data = input.readInt();
					LCD.clearDisplay();

					LCD.drawString("Gear:", 0, 1);
					LCD.drawInt(gear, 0, 2);
					LCD.drawString("Power:", 0, 3);
					LCD.drawInt(power, 0, 4);
					LCD.drawString("Angle", 0, 5);
					LCD.drawInt(angle, 0, 6);

					// configura o dado recebido
					motionData.setData(data);
					if (gear != motionData.getGear()) {
						gear = motionData.getGear();// update gear data
						
						// prepare to change the gear
						motorTraction.setPower(0);// para o motor
						
						// FIXME muda a marcha fisicamente;
						
						if (power > 0 && gear != 0) {
							if (gear > 0) {
								forward(motorTraction);
							} else /*(gear < 0)*/ {
								backward(motorTraction);
							}
						} else {
							stop(motorTraction);
						}

						// retake the state before the gear change
						motorTraction.setPower(power);
					}
					if (power != motionData.getPower()) {
						boolean wasNull = (power == 0);
						
						power = motionData.getPower();// update power data

						if (wasNull && power > 0) {
							if (gear > 0) forward(motorTraction);
							if (gear < 0) backward(motorTraction);
						} else if (power == 0) {
							stop(motorTraction);
						} 
						motorTraction.setPower(power);
					}
					if (angle != motionData.getAngle()) {
						angle = motionData.getAngle();// update angle data
						
						// simply change the angle
						rotateTo(motorTurn, angle);
					}

					notifyDataReceived(output);
				}
				LCD.drawString("received -1", 0, 4);
			} catch (IOException e) {
				LCD.clearDisplay();
				LCD.drawString("I/O ERROR", 0, 5);
			} finally {
				try {
					input.close();
					output.close();
				} catch (IOException e) {}
			}


		} finally {
			btConn.close();
		}

		Sound.beepSequenceUp();

		// para finalizar o programa, aperte ESCAPE!
		Button.ESCAPE.waitForPressAndRelease();
	}

	private static void rotateTo(Motor motorTurn, byte angle) {
		motorTurn.rotateTo(angle);
	}

	private static void forward(Motor motorTraction) {
		motorTraction.forward();
	}
	
	private static void backward(Motor motorTraction) {
		motorTraction.backward();
	}

	private static void stop(Motor motorTraction) {
		motorTraction.flt();// ou stop()?
	}

	private static void notifyDataReceived(DataOutputStream output) throws IOException {
		// manda algo pra dizer ao controle que ele recebeu a instrução
		output.write(0);
		output.flush();
	}

	private static void sleep(int amount) {
		try {
			Thread.sleep(amount);
		} catch (InterruptedException e) {
		}
	}

	private static BTConnection waitForConnection() {
		BTConnection conn = Bluetooth.waitForConnection();

		// log
		LCD.clearDisplay();
		LCD.drawString("Connection", 0, 0);
		LCD.drawString("established!", 3, 1);

		return conn;
	}

	private static void showInfos() {
		LCD.drawString("BT name:", 0, 0);
		LCD.drawString(Bluetooth.getFriendlyName(), 0, 1);
		LCD.drawString("BT address:", 0, 2);
		LCD.drawString(Bluetooth.getLocalAddress(), 0, 3);
		LCD.drawString("BT PIN:", 0, 4);
		LCD.drawString(pinToString(Bluetooth.getPin()), 0, 5);
		// Bluetooth.getKnownDevicesList();
		LCD.drawString("Waiting conn...", 0, 7);
	}

	private static String pinToString(byte[] pin) {
		return new String(pin);
	}

}

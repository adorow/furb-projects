import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Communicator implements Runnable {

	private NXTDeviceConnection nxtConn;
	private MotionData data;

	public Communicator(NXTDeviceConnection conn, MotionData data) {
		this.nxtConn = conn;
		this.data = data;
	}

	@Override
	public void run() {
		System.out.println("INICIANDO ENVIO DE PACOTES DE DADOS");
		PacketTransmitter sender = new PacketTransmitter();

		DataInputStream input = new DataInputStream(nxtConn.getInputStream());
		DataOutputStream output = new DataOutputStream(nxtConn.getOutputStream());

		try {
			for (;;) {
				System.out.println("sending...");
				// envia o status atual de movimentação
				MotionPacket motionPacket = new MotionPacket(data.getGear(), data.getPower(), data.getAngle());
				sender.send(output, motionPacket);

				System.out.println("receiving...");
				// recebe um byte para saber que o nxt recebeu a mensagem.
				input.read();
			}
		} catch (IOException e) {
			System.err.println("I/O error: " + e);
			e.printStackTrace();
			System.exit(1);
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

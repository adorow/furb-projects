import java.io.DataOutputStream;
import java.io.IOException;


public class PacketTransmitter {

	public void send(DataOutputStream stream, Packet pack) throws IOException {
		stream.write(pack.data());
		stream.flush();
	}
	
}

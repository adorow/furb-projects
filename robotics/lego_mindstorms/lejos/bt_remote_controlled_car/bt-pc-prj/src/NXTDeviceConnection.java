import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Arrays;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;


public class NXTDeviceConnection {

	private NXTComm comm;
	private NXTInfo info;

	private NXTDeviceConnection(NXTComm comm, NXTInfo info) {
		this.comm = comm;
		this.info = info;
	}
	
	public DataInputStream getInputStream() {
		return new DataInputStream(comm.getInputStream());
	}

	public DataOutputStream getOutputStream() {
		return new DataOutputStream(comm.getOutputStream());
	}
	
	public NXTInfo getInfo() {
		return info;
	}
	
	/**
	 * Tenta conectar-se ao dispositivo <tt>friendlyName</tt>.
	 * 
	 * @param friendlyName o nome do dispositivo.
	 * @return a conex�o com o dispositivo.
	 * @throws NXTCommException caso ocorra algum erro tentando estabelecer comunica��o com o dispositivo, como por exemplo n�o encontrar o dispositivo, ou ocorr�ncia de falha durante a autentica��o.
	 */
	public static NXTDeviceConnection connect(String friendlyName) throws NXTCommException {
		NXTComm comm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		NXTInfo info = null;
		
		log("Searching device " + friendlyName + "...");

		NXTInfo[] infos = comm.search(friendlyName, NXTCommFactory.BLUETOOTH);
		if (infos == null || infos.length != 1) {
			throw new NXTCommException("NXTInfo n�o identificada: " + Arrays.toString(infos));
		}
		info = infos[0];
		logInfo(info);
		
		log("Tentando estabelecer conex�o com " + friendlyName);
		if (comm.open(info)) {
			log("Conex�o estabelecida!");
		} else {
			throw new NXTCommException("N�o foi poss�vel estabelecer a conex�o com " + friendlyName);
		}
		
		return new NXTDeviceConnection(comm, info);
	}
	
	private static void log(String str) {
		System.out.println(str);
	}
	
	private static void logInfo(NXTInfo nxtInfo) {
		StringBuilder log = new StringBuilder(1024);
		log.append("NXT infos:\r\n");
		log.append("Address:" + nxtInfo.deviceAddress + "\r\n");
		log.append("Friendly name:" + nxtInfo.name + "\r\n");
		log.append("Protocol:" + (NXTCommFactory.BLUETOOTH == nxtInfo.protocol ? "Bluetooth" : "unknown: " + nxtInfo.protocol)  + "\r\n");
		log.append("Connection state:" + nxtInfo.connectionState + "\r\n");
		System.out.println(log.toString());
	}

}

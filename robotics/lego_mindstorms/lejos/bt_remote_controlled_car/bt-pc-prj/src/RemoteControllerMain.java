import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lejos.pc.comm.NXTCommException;


public class RemoteControllerMain {

	public static void main(String[] args) {
		boolean TEST_WITHOUT_NXT = false;
		JFrame frame;
		if (TEST_WITHOUT_NXT) {
			frame = new MovementFrame(new MotionData());
		} else {
			frame = new ConnectorFrame();
		}
		frame.setVisible(true);
	}
	
	static class ConnectorFrame extends JFrame {
		
		private static final long serialVersionUID = -8256592577486231089L;

		public ConnectorFrame() {
			super("Remote Controller via Bluetooth - Connection");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			final JTextField txtName = new JTextField("NXT_KIDO");
			getContentPane().add(new JLabel("Device name: "), BorderLayout.WEST);
			getContentPane().add(txtName, BorderLayout.EAST);
			
			final JButton btnConnect = new JButton("Conectar");
			getContentPane().add(btnConnect, BorderLayout.SOUTH);
			btnConnect.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("action = " + e);
					try {
						final NXTDeviceConnection nxtConn = NXTDeviceConnection.connect(txtName.getText());
						final MotionData data = new MotionData();
						
						final Communicator comm = new Communicator(nxtConn, data);
						
						new Thread(comm, "NXT-Communicator").start();// roda em outra Thread
						
						ConnectorFrame.this.dispose();

						JOptionPane.showMessageDialog(ConnectorFrame.this, "Conexão efetuada com sucesso!", "Conexão", JOptionPane.INFORMATION_MESSAGE);

						new MovementFrame(data).setVisible(true);
						
					} catch (NXTCommException commEx) {
						JOptionPane.showMessageDialog(ConnectorFrame.this, "Não foi possível conectar: " + commEx.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			});
			this.setPreferredSize(new Dimension(200, 75));
			this.pack();
		}
		
	}
	
	static class MovementFrame extends JFrame {
		
		private static final long serialVersionUID = -2167482133142005327L;

		public MovementFrame(final MotionData data) {
			super("Remote Controller via Bluetooth - Movement");
			setLayout(new GridLayout(3, 2));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			final JLabel lblGear = new JLabel("0");
			final JLabel lblPower = new JLabel("0");
			final JLabel lblAngle = new JLabel("0");
			
			getContentPane().add(new JLabel("Gear: "));
			getContentPane().add(lblGear);
			getContentPane().add(new JLabel("Power: "));
			getContentPane().add(lblPower);
			getContentPane().add(new JLabel("Angle: "));
			getContentPane().add(lblAngle);
			
			KeyListener keyListener = new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					//System.out.println("pressed: " + e);
					act(e);
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					//System.out.println("released: " + e);
				}
				
				@Override
				public void keyTyped(KeyEvent e) {
					//System.out.println("typed: " + e);
				}
				
				private void act(KeyEvent e) {

					System.out.println("typed: " + e);
					
					switch (e.getKeyCode()) {
					// aceleração
					case KeyEvent.VK_UP:
						// acelera
						data.setPower((short) (data.getPower() + MotionData.POWER_DELTA));
						break;
					case KeyEvent.VK_DOWN:
						// reduz
						data.setPower((short) (data.getPower() - MotionData.POWER_DELTA));
						break;
					case KeyEvent.VK_PAGE_UP:
						// acelera bastante
						data.setPower((short) (data.getPower() + MotionData.POWER_DELTA_HIGH));
						break;
					case KeyEvent.VK_PAGE_DOWN:
						// reduz bastante
						data.setPower((short) (data.getPower() - MotionData.POWER_DELTA_HIGH));
						break;
						
					// marcha
					case KeyEvent.VK_SHIFT:
						// aumenta
						data.setGear((byte) (data.getGear() + MotionData.GEAR_DELTA));
						break;
					case KeyEvent.VK_CONTROL:
						// reduz
						data.setGear((byte) (data.getGear() - MotionData.GEAR_DELTA));
						break;
						
						// curva
					case KeyEvent.VK_LEFT:
						// esquerda
						data.setAngle((byte) (data.getAngle() - MotionData.ANGLE_DELTA));
						break;
					case KeyEvent.VK_RIGHT:
						// direita
						data.setAngle((byte) (data.getAngle() + MotionData.ANGLE_DELTA));
						break;
					case KeyEvent.VK_X:
						// vira bastante pra esquerda
						data.setAngle((byte) (data.getAngle() - MotionData.ANGLE_DELTA_HIGH));
						break;
					case KeyEvent.VK_C:
						// centraliza
						data.setAngle((byte) 0);
						break;
					case KeyEvent.VK_V:
						// vira bastante pra direita
						data.setAngle((byte) (data.getAngle() + MotionData.ANGLE_DELTA_HIGH));
						break;
						
						// especial
					case KeyEvent.VK_SPACE:
						// para totalmente
						data.setGear((byte) 0);
						data.setPower((short) 0);
						data.setAngle((byte) 0);
						break;

					}
					
					lblGear.setText(String.valueOf(data.getGear()));
					lblPower.setText(String.valueOf(data.getPower()));
					lblAngle.setText(String.valueOf(data.getAngle()));
				
				}
				
			};
			getContentPane().addKeyListener(keyListener);
			this.addKeyListener(keyListener);
			
			pack();
		}
		
	}
	
}

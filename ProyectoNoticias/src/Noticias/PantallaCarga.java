package Noticias;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

public class PantallaCarga {

	private JFrame frame;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaCarga window = new PantallaCarga();
					window.frame.setVisible(true);
					window.IniciarCarga();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaCarga() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(21, 222, 403, 14);
		progressBar.setStringPainted(true);
		frame.getContentPane().add(progressBar);
	}
	public void IniciarCarga(){
		
		Thread  hilo = new Thread(new Runnable() {
				public void run() {
					try {
						for(int i = 0; i<= 100; i++) {
							Thread.sleep(40);
							progressBar.setValue(i);
							
						}
						
						 frame.dispose();
						 
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
					}
				});
			
		hilo.start();
	

		
		
	}

}

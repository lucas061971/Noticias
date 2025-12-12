package Noticias;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inicio de sesión:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(136, 27, 215, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(54, 93, 99, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(54, 131, 112, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(175, 96, 86, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField(); 
		passwordField.setBounds(175, 125, 86, 26);
		passwordField.setEchoChar('•'); 
		frame.getContentPane().add(passwordField);
		
		btnNewButton = new JButton("Siguiente");
		btnNewButton.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Mostrar Contraseña");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxNewCheckBox.setBounds(266, 127, 158, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		
		final char defaultEchoChar = passwordField.getEchoChar();
		
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					
					passwordField.setEchoChar((char)0);
				} else {
					
					passwordField.setEchoChar(defaultEchoChar);
				}
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String usuario = textField.getText();
		        String contraseña = new String(passwordField.getPassword());

		        String tipo = validarUsuario(usuario, contraseña);

		        if (tipo == null) {
		            JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos");
		        } else if (tipo.equals("usuario")) {
		            new PanelUsuario(usuario); 
		            frame.dispose();
		        } else if (tipo.equals("admin")) {
		            new PanelAdmin(); 
		            frame.dispose();
		        }
		    }
		});

	}
	
	public String validarUsuario(String user, String pass) {
	    try (BufferedReader br = new BufferedReader(new FileReader("Usuarios.txt"))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {

	            String[] datos = linea.split(";");
	            if (datos.length < 4) continue;

	            String nombre = datos[0].trim();
	            String contraseña = datos[1].trim();
	            String tipo = datos[3].trim().toLowerCase();

	            if (nombre.equals(user) && contraseña.equals(pass)) {
	                return tipo; 
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
}//kfva elns vsyb vqjp

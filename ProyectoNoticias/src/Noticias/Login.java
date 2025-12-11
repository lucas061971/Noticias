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
		passwordField.setEchoChar('•');  // <-- Contraseña con puntos
		frame.getContentPane().add(passwordField);
		
		btnNewButton = new JButton("Siguiente");
		btnNewButton.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Mostrar Contraseña");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxNewCheckBox.setBounds(266, 127, 158, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		// Guardamos el caracter original para ocultar la contraseña
		final char defaultEchoChar = passwordField.getEchoChar();
		
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					// Mostrar la contraseña
					passwordField.setEchoChar((char)0);
				} else {
					// Ocultar la contraseña con puntos
					passwordField.setEchoChar(defaultEchoChar);
				}
			}
		});
		
		// Listener del botón para validar login
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textField.getText();
				String contraseña = new String(passwordField.getPassword());

				String tipo = validarUsuario(usuario, contraseña);

				if (tipo == null) {
					// Usuario o contraseña incorrectos
					JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos");
				} else if (tipo.equals("usuario")) {
					// Abrir clase para usuario
					PanelUsuario usuarioWindow = new PanelUsuario();
					usuarioWindow.setVisible(true);
					frame.dispose(); // cerrar login
				} else if (tipo.equals("admin")) {
					// Abrir clase para admin
					PanelAdmin adminWindow = new PanelAdmin();
					adminWindow.setVisible(true);
					frame.dispose(); // cerrar login
				}
			}
		});
	}
	
	/**
	 * Método que valida el usuario en el archivo usuarios.txt
	 * @param user nombre de usuario
	 * @param pass contraseña
	 * @return "usuario", "admin" o null si no existe
	 */
	public String validarUsuario(String user, String pass) {
	    try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] datos = linea.split(",");
	            if (datos.length == 3) {
	                String nombre = datos[0];
	                String contraseña = datos[1];
	                String tipo = datos[2];
	                
	                if (nombre.equals(user) && contraseña.equals(pass)) {
	                    return tipo;  // devuelve "usuario" o "admin"
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null; // No encontró usuario
	}
	
	public void inciarSesion() {
		// Puedes implementar lógica adicional si quieres
	}
}


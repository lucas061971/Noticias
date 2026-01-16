package Noticias;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Login Sistema");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        
        JLabel lblTitulo = new JLabel("Inicio de sesión:");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(136, 27, 215, 26);
        frame.getContentPane().add(lblTitulo);
        
        JLabel lblUser = new JLabel("Usuario :");
        lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUser.setBounds(54, 93, 99, 26);
        frame.getContentPane().add(lblUser);
        
        JLabel lblPass = new JLabel("Contraseña :");
        lblPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPass.setBounds(54, 131, 112, 14);
        frame.getContentPane().add(lblPass);
        
        textField = new JTextField();
        textField.setBounds(175, 96, 120, 26);
        frame.getContentPane().add(textField);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(175, 125, 120, 26);
        passwordField.setEchoChar('•');
        frame.getContentPane().add(passwordField);
        
        JCheckBox chckbxMostrar = new JCheckBox("Mostrar Contraseña");
        chckbxMostrar.setBounds(300, 127, 140, 23);
        frame.getContentPane().add(chckbxMostrar);
        
        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBounds(335, 227, 89, 23);
        frame.getContentPane().add(btnSiguiente);
        
        // Lógica Mostrar Contraseña
        final char defaultChar = passwordField.getEchoChar();
        chckbxMostrar.addActionListener(e -> {
            if (chckbxMostrar.isSelected()) passwordField.setEchoChar((char)0);
            else passwordField.setEchoChar(defaultChar);
        });
        
        // Lógica del botón Siguiente (DIFERENCIANDO ADMIN Y USUARIO)
        btnSiguiente.addActionListener(e -> {
            String user = textField.getText();
            String pass = new String(passwordField.getPassword());
            String tipo = validarUsuario(user, pass);

            if (tipo == null) {
                JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos");
            } else if (tipo.equals("usuario")) {
                new PanelUsuario(user); // Abre Panel Usuario
                frame.dispose();
            } else if (tipo.equals("admin")) {
                new PanelAdmin(); // AHORA SÍ: Abre Panel Admin
                frame.dispose();
            }
        });
    }
    
    public String validarUsuario(String user, String pass) {
        try (BufferedReader br = new BufferedReader(new FileReader("Usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length < 4) continue;
                if (datos[0].trim().equals(user) && datos[1].trim().equals(pass)) {
                    return datos[3].trim().toLowerCase(); 
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }
}//kfva elns vsyb vqjp

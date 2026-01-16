package Noticias;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PanelUsuario extends JFrame {

    private String usuario;

    public PanelUsuario(String usuario) {
        this.usuario = usuario;

        // Configuración de la ventana
        this.setTitle("Menú de Usuario - " + usuario);
        this.setSize(450, 350); // Aumentamos un poco el alto para el botón extra
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lbl = new JLabel("SESIÓN: " + usuario.toUpperCase());
        lbl.setFont(new Font("Tahoma", Font.BOLD, 18));
        lbl.setBounds(10, 30, 414, 30);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(lbl);

        // Botón Configurar
        JButton btnConfig = new JButton("Configurar Preferencias");
        btnConfig.setBounds(25, 110, 183, 45);
        if (comprobarPreferencias(usuario)) {
            btnConfig.setEnabled(false);
            btnConfig.setText("Configurado");
        }
        this.getContentPane().add(btnConfig);

        // Botón Ver Noticias
        JButton btnNoticias = new JButton("Ver Noticias");
        btnNoticias.setBounds(218, 110, 190, 45);
        this.getContentPane().add(btnNoticias);

        // --- BOTÓN ATRÁS (NUEVO) ---
        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(10, 270, 100, 25);
        this.getContentPane().add(btnAtras);

        // --- ACCIONES ---

        btnConfig.addActionListener(e -> {
            new PrefenciasUsuario(usuario).setVisible(true);
            this.dispose();
        });

        btnNoticias.addActionListener(e -> {
            new PanelNoticias(usuario); 
            this.dispose();
        });

        // Acción del botón Atrás: vuelve al Login
        btnAtras.addActionListener(e -> {
            new Login(); // Esto abrirá la ventana de Login
            this.dispose(); // Cierra el panel de usuario
        });

        this.setVisible(true); 
    }

    private boolean comprobarPreferencias(String user) {
        File f = new File("Usuarios.txt");
        if (!f.exists()) return false;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes[0].equalsIgnoreCase(user) && partes.length >= 5) {
                    return true; 
                }
            }
        } catch (Exception e) { return false; }
        return false;
    }
}
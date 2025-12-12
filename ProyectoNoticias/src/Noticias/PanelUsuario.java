package Noticias;

import javax.swing.*;
import java.awt.*;

public class PanelUsuario extends JFrame {

    private String usuario;

    public PanelUsuario(String usuario) {
        this.usuario = usuario;

        setTitle("Panel Usuario");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("USUARIO: ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(130, 40, 250, 27);
        getContentPane().add(lblNewLabel);

        JButton btnConfig = new JButton("Configurar Preferencias");
        btnConfig.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnConfig.setBounds(25, 140, 183, 45);
        getContentPane().add(btnConfig);

        JButton btnNoticias = new JButton("Ver noticias");
        btnNoticias.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNoticias.setBounds(218, 140, 190, 45);
        getContentPane().add(btnNoticias);

        JButton btnAtras = new JButton("ATRÁS");
        btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAtras.setBounds(10, 227, 89, 23);
        getContentPane().add(btnAtras);

        // -----------------------------------------------------------------
        //        AQUÍ ES DONDE SE ABRE PrefenciasUsuario AL PULSAR
        // -----------------------------------------------------------------
        btnConfig.addActionListener(e -> new PrefenciasUsuario(usuario));

        setVisible(true);
    }

    public static void main(String[] args) {
        new PanelUsuario("UsuarioDemo");
    }
}

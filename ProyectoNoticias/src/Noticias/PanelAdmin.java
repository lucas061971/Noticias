package Noticias;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelAdmin extends JFrame {

    public PanelAdmin() {
        setTitle("Panel Administrador");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("ADMINISTRADOR");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(130, 33, 200, 25);
        add(lblNewLabel);

        JButton btnGestion = new JButton("Gestión usuarios");
        btnGestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnGestion.setBounds(27, 96, 176, 46);
        add(btnGestion);

        JButton btnNoticias = new JButton("Ver Noticias");
        btnNoticias.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNoticias.setBounds(238, 97, 142, 45);
        add(btnNoticias);

        JButton btnEmail = new JButton("Email");
        btnEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnEmail.setBounds(238, 153, 142, 46);
        add(btnEmail);

        JButton btnAtras = new JButton("ATRÁS");
        btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAtras.setBounds(10, 227, 89, 23);
        add(btnAtras);

        setVisible(true);
    }

    public static void main(String[] args) {
        new PanelAdmin();
    }
}

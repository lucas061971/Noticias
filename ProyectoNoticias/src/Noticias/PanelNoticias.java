package Noticias;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class PanelNoticias extends JFrame {

    private JTextArea areaNoticias;
    private String usuario;
    private String urlsDirectas;

    // Constructor para ADMIN
    public PanelNoticias(String usuario, String urls) {
        this.usuario = usuario;
        this.urlsDirectas = urls;
        iniciarVentana();
        cargarNoticias(true);
    }

    // Constructor para USUARIO
    public PanelNoticias(String usuario) {
        this.usuario = usuario;
        this.urlsDirectas = null;
        iniciarVentana();
        cargarNoticias(false);
    }

    private void iniciarVentana() {
        setTitle("Visor de Noticias - " + usuario);
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        areaNoticias = new JTextArea();
        areaNoticias.setEditable(false);
        areaNoticias.setFont(new Font("Tahoma", Font.PLAIN, 13));
        areaNoticias.setBackground(Color.WHITE);
        areaNoticias.setForeground(Color.BLACK);
        
        add(new JScrollPane(areaNoticias));
        setVisible(true);
    }

    private void cargarNoticias(boolean esAdmin) {
        areaNoticias.setText("Conectando con los servidores de noticias...\n\n");
        new Thread(() -> {
            if (esAdmin) {
                procesarFuentes(urlsDirectas.split(","));
            } else {
                leerPreferenciasDelArchivo();
            }
        }).start();
    }

    private void leerPreferenciasDelArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("Usuarios.txt"))) {
            String l;
            boolean encontrado = false;
            while ((l = br.readLine()) != null) {
                String[] p = l.split(";");
                if (p[0].equalsIgnoreCase(usuario) && p.length >= 5) {
                    procesarFuentes(p[4].split(","));
                    encontrado = true;
                    break;
                }
            }
            if(!encontrado) areaNoticias.append("Por favor, configura tus periódicos en Preferencias.");
        } catch (Exception e) {
            areaNoticias.append("Error al acceder a tus preferencias.");
        }
    }

    private void procesarFuentes(String[] urls) {
        for (String url : urls) {
            String urlLimpia = url.trim();
            if(urlLimpia.isEmpty()) continue;

            areaNoticias.append(">>> " + urlLimpia.toUpperCase() + "\n");
            
            // Aquí llamamos al Gestor que tiene el Jsoup
            String contenido = GestorNoticias.leerTitulares(urlLimpia);
            
            areaNoticias.append(contenido + "\n");
            areaNoticias.append("----------------------------------------------------------\n\n");
            areaNoticias.setCaretPosition(areaNoticias.getDocument().getLength());
        }
    }
}
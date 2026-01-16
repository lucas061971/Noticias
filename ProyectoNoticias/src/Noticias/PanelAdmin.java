package Noticias;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PanelAdmin extends JFrame {

    public PanelAdmin() {
        // Configuración de la ventana
        setTitle("Panel Administrador - Control Total");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setResizable(false); 
        
        // Al cerrar con la X, preguntar
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salirSeguro();
            }
        });

        setLayout(null);

        // Título visual
        JLabel lblTitulo = new JLabel("ADMINISTRADOR");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(130, 20, 200, 25);
        add(lblTitulo);

        // --- BOTÓN 1: GESTIÓN DE USUARIOS (NUEVO CAMBIO) ---
        JButton btnGestion = new JButton("Gestión usuarios");
        btnGestion.setBounds(27, 80, 176, 46);
        btnGestion.addActionListener(e -> {
            // Abre la nueva ventana para añadir o borrar usuarios
            new GestionUsuarios();
        });
        add(btnGestion);

        // --- BOTÓN 2: VER NOTICIAS (CON LAS 18 FUENTES QUE FUNCIONAN) ---
        JButton btnNoticias = new JButton("Ver Noticias");
        btnNoticias.setBounds(238, 80, 142, 45);
        btnNoticias.addActionListener(e -> {
            String urls18 = "https://www.eleconomista.es,https://cincodias.elpais.com,https://www.expansion.com," +
                            "https://www.marca.com,https://www.mundodeportivo.com,https://www.sport.es," +
                            "https://elpais.com,https://www.abc.es,https://www.lavanguardia.com," +
                            "https://www.nytimes.com,https://www.bbc.com/news,https://www.elconfidencial.com," +
                            "https://www.3djuegos.com,https://www.hobbyconsolas.com,https://vandal.elespanol.com," +
                            "https://www.fotogramas.es,https://www.sensacine.com,https://www.espinof.com";
            
            // Abrimos el PanelNoticias en modo Admin (recibiendo el String largo)
            new PanelNoticias("ADMIN", urls18);
        });
        add(btnNoticias);

        // --- BOTÓN 3: EMAIL TEST ---
        JButton btnEmail = new JButton("Email Test");
        btnEmail.setBounds(238, 140, 142, 46);
        btnEmail.addActionListener(e -> ejecutarModoTest(btnEmail));
        add(btnEmail);

        // --- BOTÓN 4: ATRÁS ---
        JButton btnAtras = new JButton("ATRÁS");
        btnAtras.setBounds(10, 300, 89, 23);
        btnAtras.addActionListener(e -> {
            new Login(); 
            dispose();
        });
        add(btnAtras);

        setVisible(true);
    }

    // Método para simular la extracción de noticias para el Email
    private void ejecutarModoTest(JButton boton) {
        new Thread(() -> {
            boton.setEnabled(false);
            boton.setText("Procesando...");
            
            // Usamos las fuentes que sabemos que funcionan bien
            String[][] fuentes = {
                {"INTERNACIONAL", "https://www.nytimes.com", "https://www.bbc.com/news", "https://www.elconfidencial.com"},
                {"DEPORTES", "https://www.marca.com", "https://www.mundodeportivo.com", "https://www.sport.es"}
            };

            for (String[] fila : fuentes) {
                System.out.println("Categoría: " + fila[0]);
                for (int i = 1; i < fila.length; i++) {
                    System.out.println("Prueba conexión: " + fila[i]);
                    extraerTitularReal(fila[i]);
                }
            }
            
            boton.setEnabled(true);
            boton.setText("Email Test");
            JOptionPane.showMessageDialog(this, "Prueba de conexión a fuentes completada.");
        }).start();
    }

    // Método auxiliar de Jsoup con UserAgent para evitar bloqueos
    private String extraerTitularReal(String url) {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0")
                    .timeout(8000)
                    .get();
            return doc.select("h1, h2").first().text();
        } catch (Exception e) {
            return "Fuente no accesible en este momento.";
        }
    }

    private void salirSeguro() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea salir de la administración?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) System.exit(0);
    }
}
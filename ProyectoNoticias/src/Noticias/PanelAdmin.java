package Noticias;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PanelAdmin extends JFrame {

    public PanelAdmin() {
        // Configuración de ventana (Puntos 84 y 86 del PDF)
        setTitle("Panel Administrador");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setResizable(false); 
        
        // REQUISITO: Confirmar salida siempre (Punto 81)
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salirSeguro();
            }
        });

        setLayout(null);

        // REQUISITO: Barra de Menú con "Acerca de" (Punto 85)
        JMenuBar menuBar = new JMenuBar();
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcerca = new JMenuItem("Acerca de");
        itemAcerca.addActionListener(e -> JOptionPane.showMessageDialog(this, 
            "Proyecto DAM 25/26\nDesarrollador: Tu Nombre\nVersión 1.0", 
            "Acerca de", JOptionPane.INFORMATION_MESSAGE));
        menuAyuda.add(itemAcerca);
        menuBar.add(menuAyuda);
        setJMenuBar(menuBar);

        JLabel lblTitulo = new JLabel("ADMINISTRADOR");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(130, 20, 200, 25);
        add(lblTitulo);

        // Botones principales
        JButton btnGestion = new JButton("Gestión usuarios");
        btnGestion.setBounds(27, 80, 176, 46);
        add(btnGestion);

        JButton btnNoticias = new JButton("Ver Noticias");
        btnNoticias.setBounds(238, 80, 142, 45);
        add(btnNoticias);

        // --- BOTÓN EMAIL (MODO TEST DE LAS 18 NOTICIAS) ---
        JButton btnEmail = new JButton("Email Test");
        btnEmail.setBounds(238, 140, 142, 46);
        btnEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarModoTest(btnEmail);
            }
        });
        add(btnEmail);

        JButton btnAtras = new JButton("ATRÁS");
        btnAtras.setBounds(10, 300, 89, 23);
        btnAtras.addActionListener(e -> dispose());
        add(btnAtras);

        setVisible(true);
    }

    private void ejecutarModoTest(JButton boton) {
        // Hilo secundario para que la app no se bloquee mientras conecta a 18 webs
        new Thread(() -> {
            boton.setEnabled(false);
            boton.setText("Procesando...");
            
            StringBuilder sb = new StringBuilder();
            
            // Las 18 fuentes exactas de tu lista
            String[][] fuentes = {
                {"ECONOMÍA", "https://www.eleconomista.es", "https://cincodias.elpais.com", "https://www.expansion.com"},
                {"DEPORTES", "https://www.marca.com", "https://as.com", "https://www.sport.es"},
                {"NACIONAL", "https://elpais.com", "https://www.abc.es", "https://www.lavanguardia.com"},
                {"INTERNACIONAL", "https://www.nytimes.com", "https://www.bbc.com/news", "https://www.reuters.com"},
                {"VIDEOJUEGOS", "https://www.3djuegos.com", "https://www.hobbyconsolas.com", "https://vandal.elespanol.com"},
                {"SERIES", "https://www.fotogramas.es", "https://www.sensacine.com", "https://www.imdb.com/news"}
            };

            for (String[] fila : fuentes) {
                sb.append("• CATEGORÍA: ").append(fila[0]).append("\n");
                for (int i = 1; i < fila.length; i++) {
                    String titular = extraerTitularReal(fila[i]);
                    // Formato exacto del PDF: ☑ ✓ TITULAR
                    sb.append("  ☑ ✓ ").append(titular).append("\n");
                }
                sb.append("\n");
            }

            GestorEmail gestor = new GestorEmail();
            gestor.enviarEmailNoticias("lucasmediaroble@gmail.com", sb.toString());
            
            boton.setEnabled(true);
            boton.setText("Email Test");
        }).start();
        
        JOptionPane.showMessageDialog(this, "Iniciando test de las 18 noticias. El correo se enviará en breve.");
    }

    private String extraerTitularReal(String url) {
        try {
            // Usamos Jsoup para conectar a la web
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(6000)
                    .get();
            // Retorna el primer h1 o h2 encontrado
            return doc.select("h1, h2").first().text();
        } catch (Exception e) {
            return "Titular no disponible de: " + url;
        }
    }

    private void salirSeguro() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea salir?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) System.exit(0);
    }

    public static void main(String[] args) {
        new PanelAdmin();
    }
}
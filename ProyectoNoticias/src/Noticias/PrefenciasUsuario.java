package Noticias;

import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrefenciasUsuario extends JFrame {

    private String usuario;
    private final String ARCHIVO = "Usuarios.txt";

    private JButton btnGuardar;
    private JButton btnAtras; // Botón para volver al PanelUsuario
    private JComboBox<String> cbDeportes, cbEconomia, cbInternacional, cbNacional, cbVideojuegos, cbSeries;
    private JLabel lblDeportes, lblEconomia, lblInternacional, lblNacional, lblVideojuegos, lblSeries, lblTitulo;

    public PrefenciasUsuario(String usuario) {
        this.usuario = usuario;
        initComponents();
        
        setResizable(false); 
        setLocationRelativeTo(null);
        setTitle("Preferencias de " + usuario);
        
        cargarModelos();
        cargarPreferenciasUsuario();
    }

    private void cargarModelos() {
        cbEconomia.setModel(new DefaultComboBoxModel<>(new String[]{"El Economista", "Cinco Días", "Expansión"}));
        cbDeportes.setModel(new DefaultComboBoxModel<>(new String[]{"Marca", "AS", "Sport"}));
        cbNacional.setModel(new DefaultComboBoxModel<>(new String[]{"El País", "ABC", "La Vanguardia"}));
        cbInternacional.setModel(new DefaultComboBoxModel<>(new String[]{"BBC News", "NY Times", "The Reuters"}));
        cbVideojuegos.setModel(new DefaultComboBoxModel<>(new String[]{"3DJuegos", "HobbyConsolas", "Vandal"}));
        cbSeries.setModel(new DefaultComboBoxModel<>(new String[]{"Fotogramas", "Sensacine", "IMDb News"}));
    }

    private String obtenerURL(String medio) {
        switch (medio) {
            case "El Economista": return "https://www.eleconomista.es";
            case "Cinco Días": return "https://cincodias.elpais.com";
            case "Expansión": return "https://www.expansion.com";
            case "Marca": return "https://www.marca.com";
            case "AS": return "https://as.com";
            case "Sport": return "https://www.sport.es";
            case "El País": return "https://elpais.com";
            case "ABC": return "https://www.abc.es";
            case "La Vanguardia": return "https://www.lavanguardia.com";
            case "NY Times": return "https://www.nytimes.com";
            case "BBC News": return "https://www.bbc.com/news";
            case "The Reuters": return "https://www.reuters.com";
            case "3DJuegos": return "https://www.3djuegos.com";
            case "HobbyConsolas": return "https://www.hobbyconsolas.com";
            case "Vandal": return "https://vandal.elespanol.com";
            case "Fotogramas": return "https://www.fotogramas.es";
            case "Sensacine": return "https://www.sensacine.com";
            case "IMDb News": return "https://www.imdb.com/news";
            default: return "";
        }
    }

    private void guardarPreferencias() {
        String linea = usuario + ";" +
                obtenerURL(cbEconomia.getSelectedItem().toString()) + "," +
                obtenerURL(cbDeportes.getSelectedItem().toString()) + "," +
                obtenerURL(cbNacional.getSelectedItem().toString()) + "," +
                obtenerURL(cbInternacional.getSelectedItem().toString()) + "," +
                obtenerURL(cbVideojuegos.getSelectedItem().toString()) + "," +
                obtenerURL(cbSeries.getSelectedItem().toString());

        try {
            ArrayList<String> lineas = new ArrayList<>();
            File f = new File(ARCHIVO);
            if (f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String l;
                while ((l = br.readLine()) != null) {
                    if (!l.startsWith(usuario + ";")) lineas.add(l);
                }
                br.close();
            }
            lineas.add(linea);
            FileWriter fw = new FileWriter(ARCHIVO);
            for (String s : lineas) fw.write(s + "\n");
            fw.close();
            JOptionPane.showMessageDialog(this, "Preferencias guardadas correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarPreferenciasUsuario() {
        File f = new File(ARCHIVO);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(usuario + ";")) { /* Carga opcional */ }
            }
        } catch (Exception ignored) {}
    }

    private void initComponents() {
        lblTitulo = new JLabel("SELECCIONA TUS FUENTES");
        lblEconomia = new JLabel("Economía:");
        lblDeportes = new JLabel("Deportes:");
        lblNacional = new JLabel("Nacional:");
        lblInternacional = new JLabel("Internacional:");
        lblVideojuegos = new JLabel("Videojuegos:");
        lblSeries = new JLabel("Cine y Series:");

        cbEconomia = new JComboBox<>();
        cbDeportes = new JComboBox<>();
        cbNacional = new JComboBox<>();
        cbInternacional = new JComboBox<>();
        cbVideojuegos = new JComboBox<>();
        cbSeries = new JComboBox<>();

        btnGuardar = new JButton("Guardar Preferencias");
        btnAtras = new JButton("Atrás"); 

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
        getContentPane().setLayout(null);

        // Título y etiquetas
        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 22));
        lblTitulo.setBounds(60, 20, 320, 30);
        getContentPane().add(lblTitulo);

        // Ubicaciones de los combos
        cbEconomia.setBounds(85, 97, 200, 25);
        getContentPane().add(cbEconomia);
        cbDeportes.setBounds(85, 152, 200, 25);
        getContentPane().add(cbDeportes);
        cbNacional.setBounds(85, 207, 200, 25);
        getContentPane().add(cbNacional);
        cbInternacional.setBounds(85, 257, 200, 25);
        getContentPane().add(cbInternacional);
        cbVideojuegos.setBounds(85, 313, 200, 25);
        getContentPane().add(cbVideojuegos);
        cbSeries.setBounds(85, 359, 200, 25);
        getContentPane().add(cbSeries);

        // Botón Guardar
        btnGuardar.setBounds(105, 395, 180, 35);
        btnGuardar.addActionListener(evt -> guardarPreferencias());
        getContentPane().add(btnGuardar);

        // BOTÓN ATRÁS: Vuelve a PanelUsuario
        btnAtras.setBounds(10, 400, 80, 25);
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Al pulsar atrás, abrimos de nuevo el PanelUsuario pasándole el nombre
                new PanelUsuario(usuario); 
                dispose(); // Cierra esta ventana de preferencias
            }
        });
        getContentPane().add(btnAtras);

        setSize(420, 480);
    }
}
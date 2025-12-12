package Noticias;

import java.io.*;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PrefenciasUsuario extends javax.swing.JFrame {

    private String usuario;
    private final String ARCHIVO = "preferencias.txt";

    public PrefenciasUsuario(String usuario) {
        initComponents();
        this.usuario = usuario;
        setLocationRelativeTo(null);
        setTitle("Preferencias de " + usuario);

        cargarModelos();
        cargarPreferenciasUsuario();
    }

    // ------------------------
    //   RELLENAR COMBOS
    // ------------------------
    private void cargarModelos() {

        cbEconomia.setModel(new DefaultComboBoxModel<>(new String[]{
            "El Economista", "Cinco Días", "Expansión"
        }));

        cbDeportes.setModel(new DefaultComboBoxModel<>(new String[]{
            "Marca", "AS", "Sport"
        }));

        cbNacional.setModel(new DefaultComboBoxModel<>(new String[]{
            "El País", "ABC", "La Vanguardia"
        }));

        cbInternacional.setModel(new DefaultComboBoxModel<>(new String[]{
            "The Guardian", "NY Times", "BBC News"
        }));

        cbVideojuegos.setModel(new DefaultComboBoxModel<>(new String[]{
            "3DJuegos", "HobbyConsolas", "Vandal"
        }));

        cbSeries.setModel(new DefaultComboBoxModel<>(new String[]{
            "Fotogramas", "Sensacine", "IMDb News"
        }));
    }

    // ------------------------
    //   GUARDAR PREFERENCIAS
    // ------------------------
    private void guardarPreferencias() {

        String linea = usuario + ";" +
                cbEconomia.getSelectedItem() + "," +
                cbDeportes.getSelectedItem() + "," +
                cbNacional.getSelectedItem() + "," +
                cbInternacional.getSelectedItem() + "," +
                cbVideojuegos.getSelectedItem() + "," +
                cbSeries.getSelectedItem();

        try {
            ArrayList<String> lineas = new ArrayList<>();
            File f = new File(ARCHIVO);

            if (f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String l;
                while ((l = br.readLine()) != null) {
                    if (!l.startsWith(usuario + ";")) {
                        lineas.add(l);
                    }
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

    // ------------------------
    //   CARGAR PREFERENCIAS
    // ------------------------
    private void cargarPreferenciasUsuario() {
        File f = new File(ARCHIVO);
        if (!f.exists()) return;

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.startsWith(usuario + ";")) {
                    String[] partes = linea.split(";")[1].split(",");

                    cbEconomia.setSelectedItem(partes[0]);
                    cbDeportes.setSelectedItem(partes[1]);
                    cbNacional.setSelectedItem(partes[2]);
                    cbInternacional.setSelectedItem(partes[3]);
                    cbVideojuegos.setSelectedItem(partes[4]);
                    cbSeries.setSelectedItem(partes[5]);
                }
            }

            br.close();
        } catch (Exception ignored) {}
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblEconomia = new javax.swing.JLabel();
        lblDeportes = new javax.swing.JLabel();
        lblNacional = new javax.swing.JLabel();
        lblInternacional = new javax.swing.JLabel();
        lblVideojuegos = new javax.swing.JLabel();
        lblSeries = new javax.swing.JLabel();

        cbEconomia = new javax.swing.JComboBox<>();
        cbDeportes = new javax.swing.JComboBox<>();
        cbNacional = new javax.swing.JComboBox<>();
        cbInternacional = new javax.swing.JComboBox<>();
        cbVideojuegos = new javax.swing.JComboBox<>();
        cbSeries = new javax.swing.JComboBox<>();

        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 22));
        lblTitulo.setText("SELECCIONA TUS FUENTES");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(60, 20, 320, 30);

        lblEconomia.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEconomia.setText("Economía:");
        getContentPane().add(lblEconomia);
        lblEconomia.setBounds(139, 61, 100, 25);

        lblDeportes.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDeportes.setText("Deportes:");
        getContentPane().add(lblDeportes);
        lblDeportes.setBounds(149, 128, 100, 25);

        lblNacional.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNacional.setText("Nacional:");
        getContentPane().add(lblNacional);
        lblNacional.setBounds(139, 181, 100, 25);

        lblInternacional.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblInternacional.setText("Internacional:");
        getContentPane().add(lblInternacional);
        lblInternacional.setBounds(129, 228, 110, 25);

        lblVideojuegos.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblVideojuegos.setText("Videojuegos:");
        getContentPane().add(lblVideojuegos);
        lblVideojuegos.setBounds(139, 293, 110, 25);

        lblSeries.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblSeries.setText("Cine y Series:");
        getContentPane().add(lblSeries);
        lblSeries.setBounds(120, 338, 110, 25);

        getContentPane().add(cbEconomia);
        cbEconomia.setBounds(85, 97, 200, 25);

        getContentPane().add(cbDeportes);
        cbDeportes.setBounds(85, 152, 200, 25);

        getContentPane().add(cbNacional);
        cbNacional.setBounds(85, 207, 200, 25);

        getContentPane().add(cbInternacional);
        cbInternacional.setBounds(85, 257, 200, 25);

        getContentPane().add(cbVideojuegos);
        cbVideojuegos.setBounds(85, 313, 200, 25);

        getContentPane().add(cbSeries);
        cbSeries.setBounds(85, 359, 200, 25);

        btnGuardar.setText("Guardar Preferencias");
        btnGuardar.addActionListener(evt -> guardarPreferencias());
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(260, 395, 180, 35);

        setSize(420, 480);
        setLocationRelativeTo(null);
    }

    // ============================
    //   VARIABLES DEL FORMULARIO
    // ============================

    private JButton btnGuardar;
    private JComboBox<String> cbDeportes;
    private JComboBox<String> cbEconomia;
    private JComboBox<String> cbInternacional;
    private JComboBox<String> cbNacional;
    private JComboBox<String> cbVideojuegos;
    private JComboBox<String> cbSeries;

    private JLabel lblDeportes;
    private JLabel lblEconomia;
    private JLabel lblInternacional;
    private JLabel lblNacional;
    private JLabel lblVideojuegos;
    private JLabel lblSeries;
    private JLabel lblTitulo;
}

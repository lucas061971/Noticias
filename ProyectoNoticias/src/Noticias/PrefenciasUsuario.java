package Noticias;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class PrefenciasUsuario extends JFrame {

    private JComboBox<String> cbEconomia, cbDeportes, cbNacional, cbInternacional, cbVideojuegos, cbCineSeries;
    private String usuario;

    private final String ARCHIVO = "preferencias.txt";

    public PrefenciasUsuario(String usuario) {
        this.usuario = usuario;

        setTitle("Preferencias de " + usuario);
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel("SELECCIONA TUS FUENTES");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        titulo.setBounds(100, 20, 300, 30);
        add(titulo);

        // --- Etiquetas ---
        addLabel("Economía:", 50, 80);
        addLabel("Deportes:", 50, 120);
        addLabel("Nacional:", 50, 160);
        addLabel("Internacional:", 50, 200);
        addLabel("Videojuegos:", 50, 240);   // inventada
        addLabel("Cine y Series:", 50, 280); // inventada

        // --- Opciones de cada categoría ---
        String[] economia = {"El Economista", "Cinco Días", "Expansión"};
        String[] deportes = {"Marca", "AS", "Sport"};
        String[] nacional = {"El País", "ABC", "La Vanguardia"};
        String[] internacional = {"The Guardian", "NY Times", "BBC News"};

        // Inventadas
        String[] videojuegos = {"3DJuegos", "HobbyConsolas", "Vandal"};
        String[] cineSeries = {"Fotogramas", "Sensacine", "IMDb News"};

        // --- JComboBox ---
        cbEconomia = addCombo(economia, 200, 80);
        cbDeportes = addCombo(deportes, 200, 120);
        cbNacional = addCombo(nacional, 200, 160);
        cbInternacional = addCombo(internacional, 200, 200);
        cbVideojuegos = addCombo(videojuegos, 200, 240);
        cbCineSeries = addCombo(cineSeries, 200, 280);

        // --- Botón guardar ---
        JButton guardar = new JButton("GUARDAR PREFERENCIAS");
        guardar.setBounds(120, 340, 220, 40);
        guardar.setBackground(new Color(0, 150, 0));
        guardar.setForeground(Color.WHITE);
        guardar.setFont(new Font("Arial", Font.BOLD, 14));
        add(guardar);

        guardar.addActionListener(e -> guardarPreferencias());

        // Cargar si existen
        cargarPreferenciasUsuario();

        setVisible(true);
    }

    private void addLabel(String texto, int x, int y) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbl.setBounds(x, y, 150, 25);
        add(lbl);
    }

    private JComboBox<String> addCombo(String[] opciones, int x, int y) {
        JComboBox<String> combo = new JComboBox<>(opciones);
        combo.setBounds(x, y, 180, 25);
        add(combo);
        return combo;
    }

    private void guardarPreferencias() {
        String linea = usuario + ";" +
                cbEconomia.getSelectedItem() + "," +
                cbDeportes.getSelectedItem() + "," +
                cbNacional.getSelectedItem() + "," +
                cbInternacional.getSelectedItem() + "," +
                cbVideojuegos.getSelectedItem() + "," +
                cbCineSeries.getSelectedItem();

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

            JOptionPane.showMessageDialog(this, "Preferencias guardadas.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
                    cbCineSeries.setSelectedItem(partes[5]);
                }
            }

            br.close();
        } catch (Exception ignored) {}
    }
}

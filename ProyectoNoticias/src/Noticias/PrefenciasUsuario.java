package Noticias;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class PrefenciasUsuario extends JFrame {

    private String usuario;
    private final String ARCHIVO = "Usuarios.txt";
    private JComboBox<String> cbDeportes, cbEconomia, cbInternacional, cbNacional, cbVideojuegos, cbSeries;

    public PrefenciasUsuario(String usuario) {
        this.usuario = usuario;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Configuración - " + usuario);
        cargarModelos();
        this.setVisible(true);
    }

    private void cargarModelos() {
        cbEconomia.setModel(new DefaultComboBoxModel<>(new String[]{"El Economista", "Cinco Días", "Expansión"}));
        cbDeportes.setModel(new DefaultComboBoxModel<>(new String[]{"Marca", "Mundo Deportivo", "Sport"}));
        cbNacional.setModel(new DefaultComboBoxModel<>(new String[]{"El País", "ABC", "La Vanguardia"}));
        cbInternacional.setModel(new DefaultComboBoxModel<>(new String[]{"BBC News", "NY Times", "El Confidencial"}));
        cbVideojuegos.setModel(new DefaultComboBoxModel<>(new String[]{"3DJuegos", "HobbyConsolas", "Vandal"}));
        cbSeries.setModel(new DefaultComboBoxModel<>(new String[]{"Fotogramas", "Sensacine", "Espinof"}));
    }

    private String obtenerURL(String medio) {
        switch (medio) {
            case "El Economista": return "https://www.eleconomista.es";
            case "Cinco Días": return "https://cincodias.elpais.com";
            case "Expansión": return "https://www.expansion.com";
            case "Marca": return "https://www.marca.com";
            case "Mundo Deportivo": return "https://www.mundodeportivo.com";
            case "Sport": return "https://www.sport.es";
            case "El País": return "https://elpais.com";
            case "ABC": return "https://www.abc.es";
            case "La Vanguardia": return "https://www.lavanguardia.com";
            case "NY Times": return "https://www.nytimes.com";
            case "BBC News": return "https://www.bbc.com/news";
            case "El Confidencial": return "https://www.elconfidencial.com";
            case "3DJuegos": return "https://www.3djuegos.com";
            case "HobbyConsolas": return "https://www.hobbyconsolas.com";
            case "Vandal": return "https://vandal.elespanol.com";
            case "Fotogramas": return "https://www.fotogramas.es";
            case "Sensacine": return "https://www.sensacine.com";
            case "Espinof": return "https://www.espinof.com";
            default: return "";
        }
    }

    private void guardarPreferencias() {
        ArrayList<String> todasLasLineas = new ArrayList<>();
        String pass = "1234", correo = "user@mail.com", rol = "USUARIO";
        File f = new File(ARCHIVO);

        try {
            if (f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String l;
                while ((l = br.readLine()) != null) {
                    if (l.trim().isEmpty()) continue;
                    if (l.startsWith(usuario + ";")) {
                        String[] partes = l.split(";");
                        if (partes.length >= 4) {
                            pass = partes[1]; correo = partes[2]; rol = partes[3];
                        }
                    } else {
                        todasLasLineas.add(l);
                    }
                }
                br.close();
            }

            String urls = obtenerURL(cbEconomia.getSelectedItem().toString()) + "," +
                          obtenerURL(cbDeportes.getSelectedItem().toString()) + "," +
                          obtenerURL(cbNacional.getSelectedItem().toString()) + "," +
                          obtenerURL(cbInternacional.getSelectedItem().toString()) + "," +
                          obtenerURL(cbVideojuegos.getSelectedItem().toString()) + "," +
                          obtenerURL(cbSeries.getSelectedItem().toString());

            todasLasLineas.add(usuario + ";" + pass + ";" + correo + ";" + rol + ";" + urls);

            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for (String s : todasLasLineas) {
                bw.write(s); bw.newLine();
            }
            bw.close();

            JOptionPane.showMessageDialog(this, "Preferencias guardadas correctamente.");
            new PanelUsuario(usuario); 
            dispose();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void initComponents() {
        getContentPane().setLayout(null);
        cbEconomia = new JComboBox<>(); cbEconomia.setBounds(100, 60, 200, 25); getContentPane().add(cbEconomia);
        cbDeportes = new JComboBox<>(); cbDeportes.setBounds(100, 100, 200, 25); getContentPane().add(cbDeportes);
        cbNacional = new JComboBox<>(); cbNacional.setBounds(100, 140, 200, 25); getContentPane().add(cbNacional);
        cbInternacional = new JComboBox<>(); cbInternacional.setBounds(100, 180, 200, 25); getContentPane().add(cbInternacional);
        cbVideojuegos = new JComboBox<>(); cbVideojuegos.setBounds(100, 220, 200, 25); getContentPane().add(cbVideojuegos);
        cbSeries = new JComboBox<>(); cbSeries.setBounds(100, 260, 200, 25); getContentPane().add(cbSeries);

        JButton btnG = new JButton("GUARDAR");
        btnG.setBounds(100, 310, 200, 35);
        btnG.addActionListener(e -> guardarPreferencias());
        getContentPane().add(btnG);
        setSize(400, 420);
    }
}
package Noticias;

import Noticias.GestorNoticias;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class PanelNoticias extends JFrame {

    private JTextArea areaNoticias;
    private String usuario;

    public PanelNoticias(String usuario) {
        this.usuario = usuario;

        setTitle("Noticias en vivo");
        setSize(650, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        areaNoticias = new JTextArea();
        areaNoticias.setEditable(false);
        areaNoticias.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(areaNoticias), BorderLayout.CENTER);

        cargarNoticias();

        setVisible(true);
    }

    private void cargarNoticias() {

        areaNoticias.setText("Cargando noticias en vivo...\n\n");

        try (BufferedReader br = new BufferedReader(new FileReader("Usuarios.txt"))) {

            String linea;
            boolean encontrado = false;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(";");
                if (partes.length != 2) continue;

                if (partes[0].trim().equalsIgnoreCase(usuario.trim())) {

                    encontrado = true;
                    String[] urls = partes[1].split(",");

                    for (String url : urls) {
                        areaNoticias.append("Fuente: " + url + "\n\n");
                        areaNoticias.append(GestorNoticias.leerTitulares(url));
                    }
                }
            }

            if (!encontrado) {
                areaNoticias.append("No hay preferencias configuradas.\n");
            }

        } catch (Exception e) {
            areaNoticias.append("ERROR al leer Preferencias.txt\n");
        }
    }
}

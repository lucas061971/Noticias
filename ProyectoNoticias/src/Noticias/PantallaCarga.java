package Noticias;

import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class PantallaCarga {

    private JFrame frame;
    private JProgressBar progressBar;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PantallaCarga window = new PantallaCarga();
                window.frame.setVisible(true);
                window.IniciarCarga();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PantallaCarga() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setIconImage(
            Toolkit.getDefaultToolkit().getImage(
                PantallaCarga.class.getResource("/Noticias/icono.jpg")
            )
        );

        ImageIcon fondoImg = new ImageIcon(
            PantallaCarga.class.getResource("/Noticias/foto.jpg")
        );

        JLabel fondo = new JLabel(fondoImg);
        fondo.setBounds(0, 0, 450, 300);
        frame.getContentPane().add(fondo);


        progressBar = new JProgressBar();
        progressBar.setBounds(21, 222, 403, 14);
        progressBar.setStringPainted(true);

        frame.getContentPane().add(progressBar);
    }

    public void IniciarCarga() {
        Thread hilo = new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(40);
                    progressBar.setValue(i);
                }

                frame.dispose();
                new Login();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        hilo.start();
    }
}

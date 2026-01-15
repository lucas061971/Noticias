package Noticias;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import java.util.Date;

public class GestorEmail {
    
    // Datos configurados por el ADMIN (puedes leerlos del TXT luego)
    private String remitente = "lucasmediavilla.dosa@gmail.com";
    private String claveApp = "kfvaelnsvsybvqjp"; 

    public void enviarEmailNoticias(String destinatario, String todasLasNoticias) {
        
        // 1. Propiedades del servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // 2. Sesión con autenticación
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, claveApp);
            }
        });

        try {
            // 3. Creación del mensaje siguiendo los requisitos del PDF
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            
            // Requisito: Asunto exacto
            message.setSubject("NOTICIAS DAM");
            
            // Requisito: Cuerpo con Fecha/Hora y los 18 titulares
            String cuerpoFinal = "• FECHA/HORA: " + new Date().toString() + "\n\n" + todasLasNoticias;
            message.setText(cuerpoFinal);

            // 4. Envío
            Transport.send(message);
            
            JOptionPane.showMessageDialog(null, "El proceso de envío de noticias ha finalizado correctamente.");

        } catch (MessagingException e) {
            // Requisito: No ocultar errores
            JOptionPane.showMessageDialog(null, "Error crítico de envío: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
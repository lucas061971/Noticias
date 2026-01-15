package Noticias;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GestorNoticias {

    public static String leerTitulares(String url) {

        StringBuilder sb = new StringBuilder();

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(7000)
                    .get();

            Elements titulares = doc.select("h1, h2");

            int contador = 0;
            for (Element e : titulares) {
                String texto = e.text().trim();

                if (texto.length() > 20) {
                    sb.append("• ").append(texto).append("\n\n");
                    contador++;
                }
                if (contador == 3) break;
            }

        } catch (Exception e) {
            sb.append("No se pudieron cargar titulares de esta fuente.\n\n");
        }

        return sb.toString();
    }
}

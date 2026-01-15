package Noticias;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AnalizadorWeb {
    public static String extraerTitular(String url) {
        try {
            // Conectamos simulando un navegador real para obtener la noticia de portada
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .timeout(10000)
                    .get();
            
            // Intentamos buscar el primer enlace dentro de un h1, h2 o h3, 
            // que suele ser la noticia principal de portada
            Element titular = doc.select("h1 a, h2 a, h3 a, h1, h2").first();

            if (titular != null) {
                return titular.text().trim();
            }
            return "Titular no disponible";
        } catch (Exception e) {
            return "Error al conectar con la fuente";
        }
    }
}
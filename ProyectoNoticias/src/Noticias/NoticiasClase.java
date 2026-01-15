package Noticias;

public class NoticiasClase {

    private String categoria;
    private String fuente;
    private String titular;
    private String fechaHora;

    public NoticiasClase(String categoria, String fuente, String titular, String fechaHora) {
        this.categoria = categoria;
        this.fuente = fuente;
        this.titular = titular;
        this.fechaHora = fechaHora;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFuente() {
        return fuente;
    }

    public String getTitular() {
        return titular;
    }

    public String getFechaHora() {
        return fechaHora;
    }
}

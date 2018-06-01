package articulos.Error;

public enum Mensajes {
    ARTICULO_EXISTE("El articulo ya existe."),
    ARTICULO_INEXISTENTE("El articulo no existe."),
    ERROR("Se produjo un error.");

    final private String mensaje;

    Mensajes(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}


package ec.ups.edu.Heladeria.entidades.peticiones.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IniciarSesion {

    @JsonProperty
    String correo;
    @JsonProperty
    String contrasenia;

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }
}

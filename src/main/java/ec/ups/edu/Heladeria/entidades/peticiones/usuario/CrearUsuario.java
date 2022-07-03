package ec.ups.edu.Heladeria.entidades.peticiones.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CrearUsuario {

    @JsonProperty
    private String cedula;
    @JsonProperty
    private String correo;
    @JsonProperty
    private String contrasenia;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}

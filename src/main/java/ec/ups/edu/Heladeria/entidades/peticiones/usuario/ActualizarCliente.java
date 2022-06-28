package ec.ups.edu.Heladeria.entidades.peticiones.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ActualizarCliente {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String cedula;
    @JsonProperty
    private String nombre;
    @JsonProperty
    private String apellido;
    @JsonProperty
    private String correo;
    @JsonProperty
    private String contrasenia;

    @JsonProperty
    private String telefono;
    @JsonProperty
    private String direccion;

    public Long getId() {
        return id;
    }

    public void setId(Long codigo) {
        this.id = codigo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActualizarCliente that = (ActualizarCliente) o;
        return id.equals(that.id) && cedula.equals(that.cedula) && nombre.equals(that.nombre) && apellido.equals(that.apellido) && correo.equals(that.correo) && contrasenia.equals(that.contrasenia) && telefono.equals(that.telefono) && direccion.equals(that.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cedula, nombre, apellido, correo, contrasenia, telefono, direccion);
    }

    @Override
    public String toString() {
        return "ActualizarCliente{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +

                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}

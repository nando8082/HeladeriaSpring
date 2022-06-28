package ec.ups.edu.Heladeria.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;
    private String tipoUS;
    private String telefono;
    private String direccion;

    public Usuario() {
    }

    public Usuario(long id, String cedula, String nombre, String apellido, String correo, String contrasenia, String tipoUS, String telefono, String direccion) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.tipoUS = tipoUS;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public long getCodigo() {
        return id;
    }

    public void setCodigo(long codigo) {
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

    public String getTipoUS() {
        return tipoUS;
    }

    public void setTipoUS(String tipoUS) {
        this.tipoUS = tipoUS;
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
        Usuario usuario = (Usuario) o;
        return id == usuario.id && cedula.equals(usuario.cedula) && nombre.equals(usuario.nombre) && apellido.equals(usuario.apellido) && correo.equals(usuario.correo) && contrasenia.equals(usuario.contrasenia) && tipoUS.equals(usuario.tipoUS) && telefono.equals(usuario.telefono) && direccion.equals(usuario.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cedula, nombre, apellido, correo, contrasenia, tipoUS, telefono, direccion);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "codigo=" + id +
                ", cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", tipoUS='" + tipoUS + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}

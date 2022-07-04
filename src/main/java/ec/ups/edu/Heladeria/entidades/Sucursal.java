package ec.ups.edu.Heladeria.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String correo;
    private double latitud;
    private double longitud;
    @ManyToOne
    @JoinColumn(nullable = true, name = "entidad_principal_id")
    private EntidadPrincipal entidadPrincipal;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "sucursal")
    private Set<Producto> productos = new HashSet<Producto>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal")
    private Set<Categorias> categorias = new HashSet<Categorias>();
    public Sucursal() {


    }

    public Sucursal(Long id, String nombre, String telefono, String direccion, String ciudad, String correo, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.correo = correo;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public EntidadPrincipal getEntidadPrincipal() {
        return entidadPrincipal;
    }

    public void setEntidadPrincipal(EntidadPrincipal entidadPrincipal) {
        this.entidadPrincipal = entidadPrincipal;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public Set<Categorias> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categorias> categorias) {
        this.categorias = categorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sucursal sucursal = (Sucursal) o;
        return Double.compare(sucursal.latitud, latitud) == 0 && Double.compare(sucursal.longitud, longitud) == 0 && Objects.equals(id, sucursal.id) && Objects.equals(nombre, sucursal.nombre) && Objects.equals(telefono, sucursal.telefono) && Objects.equals(direccion, sucursal.direccion) && Objects.equals(ciudad, sucursal.ciudad) && Objects.equals(correo, sucursal.correo) && Objects.equals(entidadPrincipal, sucursal.entidadPrincipal) && Objects.equals(productos, sucursal.productos) && Objects.equals(categorias, sucursal.categorias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", correo='" + correo + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}

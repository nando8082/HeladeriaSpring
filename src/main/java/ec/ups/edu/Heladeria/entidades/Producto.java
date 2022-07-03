package ec.ups.edu.Heladeria.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String descripcion;
    private String marca;
    private int stock;
    private String estado;
    private Double precio;
    @Transient
    private int cantidad;


    @ManyToOne
    @JoinColumn(nullable = true)
    @JsonIgnore
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(nullable = true)
    @JsonIgnore
    private Categorias categorias;

    public Producto() {

    }

    public Producto(long id, String nombre, String descripcion, String marca, int stock, String estado, Double precio, int cantidad, Sucursal sucursal, Categorias categorias) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.stock = stock;
        this.estado = estado;
        this.precio = precio;
        this.cantidad = cantidad;

        this.sucursal = sucursal;

        this.categorias = categorias;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", marca=" + marca + ", stock=" + stock + ", estado=" + estado + ", precio=" + precio +   ", sucursal=" + sucursal +", categorias=" + categorias + '}';
    }
}

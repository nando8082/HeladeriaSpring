package ec.ups.edu.Heladeria.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Categorias implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    @ManyToOne
    @JoinColumn(nullable = true)
    private Sucursal sucursal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorias")
    private Set<Producto> productos = new HashSet<Producto>();

    public Categorias() {

    }
    public Categorias(long id, String nombre, Sucursal sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.sucursal = sucursal;
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

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Categorias{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", sucursal=" + sucursal +
                '}';
    }
}

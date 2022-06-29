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
    private String telefono;
    private String direccion;
    private String ciudad;
    private String correo;
    @ManyToOne
    @JoinColumn(nullable = true, name = "entidad_principal_id")
    @JsonIgnore
    private EntidadPrincipal entidadPrincipal;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "sucursal")
    private Set<Producto> productos = new HashSet<Producto>();

    public Sucursal() {
        super();
    }

    public Sucursal(long id, String telefono, String direccion, String ciudad, String correo) {
        super();
        this.setId(id);
        this.setTelefono(telefono);
        this.setDireccion(direccion);
        this.setCiudad(ciudad);
        this.setCorreo(correo);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sucursal categoria = (Sucursal) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public String toString() {
        return "Sucursal{" + "id=" + id + ", telefono=" + telefono + ", direccion=" + direccion + ", ciudad=" + ciudad + ", correo=" + correo + '}';
    }


}
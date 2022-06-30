package ec.ups.edu.Heladeria.entidades.peticiones.detalle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ec.ups.edu.Heladeria.entidades.Pedido;
import ec.ups.edu.Heladeria.entidades.Producto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class CrearDetalle {
    @JsonProperty
    private long idproducto;
    @JsonProperty
    private int cantidad;
    @JsonProperty
    private double precio;
    @JsonProperty
    private double subtotal;
    @JsonProperty
    private long idpedido;


    public long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(long idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(long idpedido) {
        this.idpedido = idpedido;
    }



}

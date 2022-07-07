package ec.ups.edu.Heladeria.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double total;
    private Date fecha;
    private double subtotal;
    private double iva;
    @OneToOne
    private Pedido pedido;
    @OneToOne
    private Cliente cliente;



    public Factura() {
    }

    public Factura(int id, double total, Date fecha, double subtotal, double iva, Pedido pedido) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.iva = iva;
        this.pedido = pedido;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }


    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", total=" + total + ", fecha=" + fecha + ", subtotal=" + subtotal + ", iva=" + iva + '}';
    }
}

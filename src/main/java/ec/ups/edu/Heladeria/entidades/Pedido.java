package ec.ups.edu.Heladeria.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private double latitud;
    private double longitud;
    private String estado;
    @Column(name = "costo_envio")
    private double costoEnvio;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Detalle> detalles;
    @OneToOne
    private Tarjeta tarjeta;

    public Pedido() {
    }

    public Pedido(long id, Cliente cliente, double latitud, double longitud, String estado, double costoEnvio, List<Detalle> detalles, Tarjeta tarjeta) {
        this.id = id;
        this.cliente = cliente;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.costoEnvio = costoEnvio;
        this.detalles = detalles;
        this.tarjeta = tarjeta;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
}

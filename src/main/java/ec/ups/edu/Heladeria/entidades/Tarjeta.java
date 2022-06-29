package ec.ups.edu.Heladeria.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "nombre_Titular")
    private String nombreTitular;
    @Column(name = "num_Tarjeta")
    private int numTarjeta;
    @Column(name = "fecha_Cducidad")
    private Date fechaCducidad;
    @Column(name = "codigo_Cvv")
    private int codigoCvv;
    @Column(name = "tipo")
    private String tipo;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Cliente cliente;

    public Tarjeta() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public int getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(int numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public Date getFechaCducidad() {
        return fechaCducidad;
    }

    public void setFechaCducidad(Date fechaCducidad) {
        this.fechaCducidad = fechaCducidad;
    }

    public int getCodigoCvv() {
        return codigoCvv;
    }

    public void setCodigoCvv(int codigoCvv) {
        this.codigoCvv = codigoCvv;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarjeta tarjeta = (Tarjeta) o;
        return id == tarjeta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "id=" + id +
                ", nombreTitular='" + nombreTitular + '\'' +
                ", numTarjeta=" + numTarjeta +
                ", fechaCducidad=" + fechaCducidad +
                ", codigoCvv=" + codigoCvv +
                ", tipo='" + tipo + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}

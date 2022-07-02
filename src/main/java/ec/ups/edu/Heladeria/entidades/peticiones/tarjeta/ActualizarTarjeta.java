package ec.ups.edu.Heladeria.entidades.peticiones.tarjeta;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ActualizarTarjeta {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String nombreTitular;
    @JsonProperty
    private int numTarjeta;
    @JsonProperty
    private Date fechaCducidad;
    @JsonProperty
    private int codigoCvv;
    @JsonProperty
    private String tipo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}

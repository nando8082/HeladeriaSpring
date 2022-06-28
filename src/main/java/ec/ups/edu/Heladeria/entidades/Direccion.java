package ec.ups.edu.Heladeria.entidades;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Direccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;

    @Column(name = "calle_principal")
    private String callePrincipal;
    @Column(name = "calle_secundaria")
    private String calleSecundaria;
    private String numeracion;

    @ManyToOne
    @JoinColumn
    private Usuario usuario;


}

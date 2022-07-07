package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Factura;
import ec.ups.edu.Heladeria.entidades.Pedido;
import ec.ups.edu.Heladeria.repositorios.FacturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaServicio {
    @Autowired
    private FacturaRepositorio facturaRepositorio;


    public void save(Factura factura){
        facturaRepositorio.save(factura);
    }
}

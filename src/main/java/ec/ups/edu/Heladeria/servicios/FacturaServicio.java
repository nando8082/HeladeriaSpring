package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Cliente;
import ec.ups.edu.Heladeria.entidades.Factura;
import ec.ups.edu.Heladeria.entidades.Pedido;
import ec.ups.edu.Heladeria.entidades.Tarjeta;
import ec.ups.edu.Heladeria.repositorios.FacturaRepositorio;
import ec.ups.edu.Heladeria.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServicio {
    @Autowired
    private FacturaRepositorio facturaRepositorio;
    private UsuarioRepositorio usuarioRepositorio;


    public void save(Factura factura){
        facturaRepositorio.save(factura);
    }

    public List<Factura> retrieveIdCliente(long idC){
        return facturaRepositorio.IdCliente(idC);
    }

    public Cliente retrieveUsuarioById(Long id) {
        return (Cliente) usuarioRepositorio.findUsuarioById(id);
    }
}

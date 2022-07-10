package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Pedido;
import ec.ups.edu.Heladeria.entidades.Producto;
import ec.ups.edu.Heladeria.entidades.Tarjeta;
import ec.ups.edu.Heladeria.repositorios.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PedidoServicio {
    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    public List<Pedido> findAll(){
        return (List<Pedido>) pedidoRepositorio.findAll();
    }


    public Optional<Pedido> findById(long id){
        return (Optional<Pedido>) pedidoRepositorio.findById(id);
    }

    public void save(Pedido pedido){
        pedidoRepositorio.save(pedido);
    }


    public void delete(long id){
        pedidoRepositorio.deleteById(id);
    }


    public Pedido retrieveAllProducto(Long id){
        return (Pedido) pedidoRepositorio.findPedidobyId(id);
    }

    public Pedido retrieveIdClienteEstado(long idCliente, String estado){
        return pedidoRepositorio.IdClienteEstado(idCliente, estado);
    }

    public List<Pedido> retrieveIdCliente(long idC){
        return pedidoRepositorio.IdCliente(idC);
    }


}
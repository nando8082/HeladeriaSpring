package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Detalle;
import ec.ups.edu.Heladeria.entidades.Pedido;
import ec.ups.edu.Heladeria.repositorios.DetalleRepositorio;
import ec.ups.edu.Heladeria.repositorios.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleServicio {
    @Autowired
    private DetalleRepositorio detalleRepositorio;

    public List<Detalle> findAll(){
        return (List<Detalle>) detalleRepositorio.findAll();
    }

    public Optional findId(long codigo){
        return (Optional)detalleRepositorio.findById(codigo);
    }

    public void Crear(Detalle detalle){
        detalleRepositorio.save(detalle);
    }


    public void Eliminar(long codigo) {
        detalleRepositorio.deleteById(codigo);
    }





}
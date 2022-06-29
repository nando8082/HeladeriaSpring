package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Producto;
import ec.ups.edu.Heladeria.repositorios.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicios {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    public List<Producto> findAll(){
        return (List<Producto>) productoRepositorio.findAll();
    }
}

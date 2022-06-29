package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepositorio extends CrudRepository<Producto, Long> {


}
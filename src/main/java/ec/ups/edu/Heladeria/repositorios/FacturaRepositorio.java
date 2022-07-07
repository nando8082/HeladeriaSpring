package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Detalle;
import ec.ups.edu.Heladeria.entidades.Factura;
import org.springframework.data.repository.CrudRepository;

public interface FacturaRepositorio extends CrudRepository<Factura, Long> {
}

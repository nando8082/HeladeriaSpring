package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Detalle;
import ec.ups.edu.Heladeria.entidades.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FacturaRepositorio extends CrudRepository<Factura, Long> {

    @Query("select f  from Factura f, Cliente c where f.cliente.id =:idC and c.id=:idC")
    List<Factura> IdCliente(long idC);
}

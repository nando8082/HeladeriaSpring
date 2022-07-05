package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Detalle;
import ec.ups.edu.Heladeria.entidades.Tarjeta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DetalleRepositorio extends CrudRepository<Detalle, Long> {
    @Query("select t from Detalle t where t.producto.id = :idProd")
    Detalle findIdProd(long idProd);

}
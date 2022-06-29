package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Tarjeta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TarjetaRepositorio extends CrudRepository<Tarjeta, Long> {

    @Query("select t from Tarjeta t where t.numTarjeta = :numTarjeta")
    Tarjeta findTarjetaBynumTarjeta(int numTarjeta);
}

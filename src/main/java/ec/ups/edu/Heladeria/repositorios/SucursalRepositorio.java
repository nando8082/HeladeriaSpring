package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Sucursal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SucursalRepositorio extends CrudRepository<Sucursal, Long> {

    @Query("select t from Sucursal t where t.nombre = :nombre")
    Sucursal findSucursalNombre(String nombre);
}

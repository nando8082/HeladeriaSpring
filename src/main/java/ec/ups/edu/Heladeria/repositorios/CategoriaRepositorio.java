package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Categorias;
import ec.ups.edu.Heladeria.entidades.Producto;
import ec.ups.edu.Heladeria.entidades.Sucursal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepositorio extends CrudRepository<Categorias, Long> {

    @Query("select t from Categorias t where t.nombre = :nombre")
    Categorias findCatNombre(String nombre);
}

package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoRepositorio extends CrudRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p,Sucursal s where  p.sucursal.id =:id and s.id=:id")
    List<Producto> findProductosSucursal(Long id);

    @Query("SELECT p FROM Producto p  where  p.categorias.id =:id ")
    List<Producto> findProductosCat(Long id);

    @Query("select p from Producto p where p.id = :id")
    Producto findAllProducto(Long id);

    @Query("select p.nombre from Producto p ")
    List<Producto> findAllNombre();

    @Query("select p.nombre ,p.precio from Producto p where p.precio <= :precio ")
    List<String> findPorPrecio(double precio);


}
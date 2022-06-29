package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoRepositorio extends CrudRepository<Producto, Long> {

    @Query("SELECT p.id, p.nombre, p.descripcion , p.stock, p.precio " +
            "FROM Sucursal s, Producto p where p.sucursal = :id")
    List<String> findProductosSucursal(Long id);

    @Query("select p from Producto p where p.id = :id")
    Producto findAllProducto(Long id);

    @Query("select p.nombre from Producto p ")
    List<String> findAllNombre();

    @Query("select p.nombre ,p.precio from Producto p where p.precio <= :precio ")
    List<String> findPorPrecio(double precio);


}
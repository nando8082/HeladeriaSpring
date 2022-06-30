package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface PedidoRepositorio extends CrudRepository<Pedido,Long> {


    @Query("select p  from Pedido p where p.id = :id")
    Pedido  findPedidobyId(long id);

}

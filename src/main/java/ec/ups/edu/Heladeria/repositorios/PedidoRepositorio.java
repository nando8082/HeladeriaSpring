package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface PedidoRepositorio extends CrudRepository<Pedido,Long> {


    @Query("select p  from Pedido p where p.id = :id")
    Pedido  findPedidobyId(long id);

    @Query("select p  from Pedido p where p.cliente.id = :id2 and p.estado = :estado ")
    Pedido  IdClienteEstado(long id2, String estado);

}

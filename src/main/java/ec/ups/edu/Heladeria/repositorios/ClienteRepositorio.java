package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepositorio extends CrudRepository<Cliente, Long> {

    @Query("select u.id, u.nombre from Cliente u where u.id = :id")
    String findUsuarioNombreById(Long id);

    @Query("select u.nombre from Cliente u")
    List<String> findAllNombre();

    @Query("select u from Cliente u where u.cedula = :cedula")
    Cliente findUsuarioByCedula(String cedula);

    @Query("select u from Cliente u where u.correo =:correo and u.contrasenia = :contrasenia")
    Cliente getUsuarioEncontrado(String correo, String contrasenia);

}

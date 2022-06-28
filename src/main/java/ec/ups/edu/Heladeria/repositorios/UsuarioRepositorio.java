package ec.ups.edu.Heladeria.repositorios;

import ec.ups.edu.Heladeria.entidades.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

    @Query("select u.id, u.nombre from Usuario u where u.id = :id")
    String findUsuarioNombreById(Long id);

    @Query("select u.nombre from Usuario u")
    List<String> findAllNombre();

    @Query("select u from Usuario u where u.cedula = :cedula")
    Usuario findUsuarioByCedula(String cedula);

    @Query("select u from Usuario u where u.correo =:correo and u.contrasenia = :contrasenia")
    Usuario getUsuarioEncontrado(String correo,String contrasenia);

}

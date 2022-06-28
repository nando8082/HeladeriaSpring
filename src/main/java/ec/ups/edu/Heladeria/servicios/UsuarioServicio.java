package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Usuario;
import ec.ups.edu.Heladeria.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> findAll(){
        List<Usuario> usuario = (List<Usuario>) usuarioRepositorio.findAll();
        return usuario;
    }

    public String retrieveUsuarioNombreByCodigo(Long codigo) {
        return usuarioRepositorio.findUsuarioNombreByCodigo(codigo);
    }

    public List<String> retrieveAllNombres(){
        return (List<String>) usuarioRepositorio.findAllNombre();
    }

    public Usuario retrieveUsuarioByCedula(String cedula){
        return (Usuario) usuarioRepositorio.findUsuarioByCedula(cedula);
    }

    public Optional<Usuario> findByCodigo(Long codigo){
        return (Optional<Usuario>) usuarioRepositorio.findById(codigo);
    }

    public void save(Usuario usuario){

        usuarioRepositorio.save(usuario);
    }

    public void delete(Long codigo){

        usuarioRepositorio.deleteById(codigo);
    }

}

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

    public String retrieveUsuarioNombreById(Long id) {
        return usuarioRepositorio.findUsuarioNombreById(id);
    }

    public List<String> retrieveAllNombres(){
        return (List<String>) usuarioRepositorio.findAllNombre();
    }

    public Usuario retrieveUsuarioByCedula(String cedula){
        return (Usuario) usuarioRepositorio.findUsuarioByCedula(cedula);
    }

    public Optional<Usuario> findById(Long id){
        return (Optional<Usuario>) usuarioRepositorio.findById(id);
    }

    public void save(Usuario usuario){

        usuarioRepositorio.save(usuario);
    }

    public void delete(Long id){
        usuarioRepositorio.deleteById(id);
    }

    public Usuario iniciarsesion(String correo, String contrasenia){
        return (Usuario) usuarioRepositorio.getUsuarioEncontrado(correo,contrasenia);
    }

}

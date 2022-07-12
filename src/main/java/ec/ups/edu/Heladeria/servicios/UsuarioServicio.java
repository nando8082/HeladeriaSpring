package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Cliente;
import ec.ups.edu.Heladeria.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Cliente> findAll(){
        List<Cliente> cliente = (List<Cliente>) usuarioRepositorio.findAll();
        return cliente;
    }

    public String retrieveUsuarioNombreById(Long id) {
        return usuarioRepositorio.findUsuarioNombreById(id);
    }

    public List<String> retrieveAllNombres(){
        return (List<String>) usuarioRepositorio.findAllNombre();
    }

    public Cliente retrieveUsuarioByCedula(String cedula){
        return (Cliente) usuarioRepositorio.findUsuarioByCedula(cedula);
    }

    public Optional<Cliente> findById(Long id){
        return (Optional<Cliente>) usuarioRepositorio.findById(id);
    }

    public void save(Cliente cliente){

        usuarioRepositorio.save(cliente);
    }

    public void delete(Long id){
        usuarioRepositorio.deleteById(id);
    }

    public Cliente iniciarsesion(String correo, String contrasenia){
        return (Cliente) usuarioRepositorio.getUsuarioEncontrado(correo,contrasenia);
    }

    public Cliente retrieveUsuarioById(Long id) {
        return (Cliente) usuarioRepositorio.findUsuarioById(id);
    }


}

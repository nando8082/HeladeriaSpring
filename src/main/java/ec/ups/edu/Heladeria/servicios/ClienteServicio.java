package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Cliente;
import ec.ups.edu.Heladeria.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> findAll(){
        List<Cliente> cliente = (List<Cliente>) clienteRepositorio.findAll();
        return cliente;
    }

    public String retrieveUsuarioNombreById(Long id) {
        return clienteRepositorio.findUsuarioNombreById(id);
    }

    public List<String> retrieveAllNombres(){
        return (List<String>) clienteRepositorio.findAllNombre();
    }

    public Cliente retrieveUsuarioByCedula(String cedula){
        return (Cliente) clienteRepositorio.findUsuarioByCedula(cedula);
    }

    public Optional<Cliente> findById(Long id){
        return (Optional<Cliente>) clienteRepositorio.findById(id);
    }

    public void save(Cliente cliente){

        clienteRepositorio.save(cliente);
    }

    public void delete(Long id){

        clienteRepositorio.deleteById(id);
    }
    public Cliente iniciarsesion(String correo, String contrasenia){
        return (Cliente) clienteRepositorio.getUsuarioEncontrado(correo,contrasenia);
    }

}

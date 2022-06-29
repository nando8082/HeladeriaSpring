package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Cliente;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.ActualizarCliente;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.CrearCliente;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.IniciarSesion;
import ec.ups.edu.Heladeria.servicios.ClienteNoEncontradoException;
import ec.ups.edu.Heladeria.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteControlador {

    private ClienteServicio clienteServicio;

    @Autowired
    public void setUsuarioServicio(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllUsuarios(){
        List<Cliente> listaClientes = clienteServicio.findAll();

        return new ResponseEntity<List<Cliente>>(listaClientes, HttpStatus.OK);
    }

    @GetMapping("/cliente/{codigo}")
    public ResponseEntity<String> getNombresByCodigo(@PathVariable Long id){
        String nombres = clienteServicio.retrieveUsuarioNombreById(id);
        return new ResponseEntity<String>(nombres, HttpStatus.OK);
    }

    @GetMapping("/cliente/nombres")
    public ResponseEntity<List<String>> getAllNombres(){
        List<String> listaNombres = clienteServicio.retrieveAllNombres();
        return new ResponseEntity<List<String>>(listaNombres, HttpStatus.OK);
    }

    @GetMapping("/cliente/{cedula}")
    public ResponseEntity<Cliente> getUsuarioByCedula(@PathVariable String cedula){
        Optional<Cliente> usuarioOptional = Optional.ofNullable(clienteServicio.retrieveUsuarioByCedula(cedula));
        Cliente cliente = usuarioOptional.orElseThrow(ClienteNoEncontradoException::new);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @GetMapping("/iniciarSesion")
    public ResponseEntity<Cliente> getUsuarioIiciado(@RequestBody  IniciarSesion iniciarS ){
        Optional<Cliente> usuarioOptional = Optional.ofNullable(clienteServicio.iniciarsesion(iniciarS.getCorreo(),iniciarS.getContrasenia()));
        Cliente cliente = usuarioOptional.orElseThrow(ClienteNoEncontradoException::new);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping("/cliente/create")
    public ResponseEntity<Cliente> createUsuario(@RequestBody CrearCliente crearCliente){
        Cliente cliente = new Cliente();
        cliente.setCedula(crearCliente.getCedula());
        cliente.setNombre(crearCliente.getNombre());
        cliente.setApellido(crearCliente.getApellido());
        cliente.setCorreo(crearCliente.getCorreo());
        cliente.setContrasenia(crearCliente.getContrasenia());

        cliente.setTelefono(crearCliente.getTelefono());
        cliente.setDireccion(crearCliente.getDireccion());

        clienteServicio.save(cliente);

        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/cliente/update")
    public ResponseEntity<Cliente> updateUsuario(@RequestBody ActualizarCliente actualizarUsuario){
        Optional<Cliente> usuarioOptional = clienteServicio.findById(actualizarUsuario.getId());
        if(usuarioOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Cliente clienteEncontrado = usuarioOptional.get();
        clienteEncontrado.setCedula(actualizarUsuario.getCedula());
        clienteEncontrado.setNombre(actualizarUsuario.getNombre());
        clienteEncontrado.setApellido(actualizarUsuario.getApellido());
        clienteEncontrado.setCorreo(actualizarUsuario.getCorreo());
        clienteEncontrado.setContrasenia(actualizarUsuario.getContrasenia());

        clienteEncontrado.setTelefono(actualizarUsuario.getTelefono());
        clienteEncontrado.setDireccion(actualizarUsuario.getDireccion());

        clienteServicio.save(clienteEncontrado);

        return ResponseEntity.ok(clienteEncontrado);
    }


    @DeleteMapping("/cliente/delete/{codigo}")
    public ResponseEntity<String> deletePersona(@PathVariable Long id){
        clienteServicio.delete(id);

        return ResponseEntity.ok("Persona Eliminada Correctamente");
    }
}

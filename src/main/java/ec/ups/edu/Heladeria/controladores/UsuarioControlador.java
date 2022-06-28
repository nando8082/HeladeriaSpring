package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Usuario;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.ActualizarUsuario;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.CrearUsuario;
import ec.ups.edu.Heladeria.servicios.UsuarioNoEncontradoException;
import ec.ups.edu.Heladeria.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioControlador {

    private UsuarioServicio usuarioServicio;

    @Autowired
    public void setUsuarioServicio(UsuarioServicio usuarioServicio){
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> listaUsuarios = usuarioServicio.findAll();

        return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
    }

    @GetMapping("/usuarios/{codigo}")
    public ResponseEntity<String> getNombresByCodigo(@PathVariable Long codigo){
        String nombres = usuarioServicio.retrieveUsuarioNombreById(codigo);
        return new ResponseEntity<String>(nombres, HttpStatus.OK);
    }

    @GetMapping("/usuarios/nombres")
    public ResponseEntity<List<String>> getAllNombres(){
        List<String> listaNombres = usuarioServicio.retrieveAllNombres();
        return new ResponseEntity<List<String>>(listaNombres, HttpStatus.OK);
    }

    @GetMapping("/usuario/{cedula}")
    public ResponseEntity<Usuario> getUsuarioByCedula(@PathVariable String cedula){
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioServicio.retrieveUsuarioByCedula(cedula));
        Usuario usuario = usuarioOptional.orElseThrow(UsuarioNoEncontradoException::new);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PostMapping("/usuario/create")
    public ResponseEntity<Usuario> createUsuario(@RequestBody CrearUsuario crearUsuario){
        Usuario usuario = new Usuario();
        usuario.setCedula(crearUsuario.getCedula());
        usuario.setNombre(crearUsuario.getNombre());
        usuario.setApellido(crearUsuario.getApellido());
        usuario.setCorreo(crearUsuario.getCorreo());
        usuario.setContrasenia(crearUsuario.getContrasenia());
        usuario.setTipoUS(crearUsuario.getTipo());
        usuario.setTelefono(crearUsuario.getTelefono());
        usuario.setDireccion(crearUsuario.getDireccion());

        usuarioServicio.save(usuario);

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/usuario/update")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody ActualizarUsuario actualizarUsuario){
        Optional<Usuario> usuarioOptional = usuarioServicio.findById(actualizarUsuario.getId());
        if(usuarioOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Usuario usuarioEncontrado = usuarioOptional.get();
        usuarioEncontrado.setCedula(actualizarUsuario.getCedula());
        usuarioEncontrado.setNombre(actualizarUsuario.getNombre());
        usuarioEncontrado.setApellido(actualizarUsuario.getApellido());
        usuarioEncontrado.setCorreo(actualizarUsuario.getCorreo());
        usuarioEncontrado.setContrasenia(actualizarUsuario.getContrasenia());
        usuarioEncontrado.setTipoUS(actualizarUsuario.getTipo());
        usuarioEncontrado.setTelefono(actualizarUsuario.getTelefono());
        usuarioEncontrado.setDireccion(actualizarUsuario.getDireccion());

        usuarioServicio.save(usuarioEncontrado);

        return ResponseEntity.ok(usuarioEncontrado);
    }


    @DeleteMapping("/usuario/delete/{codigo}")
    public ResponseEntity<String> deletePersona(@PathVariable Long codigo){
        usuarioServicio.delete(codigo);

        return ResponseEntity.ok("Persona Eliminada Correctamente");
    }
}

package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Usuario;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.ActualizarUsuario;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.CrearUsuario;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.IniciarSesion;
import ec.ups.edu.Heladeria.servicios.UsuarioNoEncontradoException;
import ec.ups.edu.Heladeria.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
public class UsuarioControlador {

    private UsuarioServicio usuarioServicio;

    @Autowired
    public void setUsuarioServicio(UsuarioServicio usuarioServicio){
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> listaUsuarios = usuarioServicio.findAll();

        return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<String> getNombresByCodigo(@PathVariable Long id){
        String nombres = usuarioServicio.retrieveUsuarioNombreById(id);
        return new ResponseEntity<String>(nombres, HttpStatus.OK);
    }

    @GetMapping("/nombres")
    public ResponseEntity<List<String>> getAllNombres(){
        List<String> listaNombres = usuarioServicio.retrieveAllNombres();
        return new ResponseEntity<List<String>>(listaNombres, HttpStatus.OK);
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<Usuario> getUsuarioByCedula(@PathVariable String cedula){
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioServicio.retrieveUsuarioByCedula(cedula));
        Usuario usuario = usuarioOptional.orElseThrow(UsuarioNoEncontradoException::new);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @GetMapping("/iniciarSesion")
    public ResponseEntity<Usuario> getUsuarioIiciado(@RequestBody  IniciarSesion iniciarS ){
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioServicio.iniciarsesion(iniciarS.getCorreo(),iniciarS.getContrasenia()));
        Usuario usuario = usuarioOptional.orElseThrow(UsuarioNoEncontradoException::new);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> createUsuario(@RequestBody CrearUsuario crearUsuario){
        Usuario usuario = new Usuario();
        usuario.setCedula(crearUsuario.getCedula());
        usuario.setNombre(crearUsuario.getNombre());
        usuario.setApellido(crearUsuario.getApellido());
        usuario.setCorreo(crearUsuario.getCorreo());
        usuario.setContrasenia(crearUsuario.getContrasenia());

        usuario.setTelefono(crearUsuario.getTelefono());
        usuario.setDireccion(crearUsuario.getDireccion());

        usuarioServicio.save(usuario);

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/update")
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
        usuarioEncontrado.setTelefono(actualizarUsuario.getTelefono());
        usuarioEncontrado.setDireccion(actualizarUsuario.getDireccion());

        usuarioServicio.save(usuarioEncontrado);

        return ResponseEntity.ok(usuarioEncontrado);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id){
        usuarioServicio.delete(id);

        return ResponseEntity.ok("Usuario Eliminada Correctamente");
    }
}

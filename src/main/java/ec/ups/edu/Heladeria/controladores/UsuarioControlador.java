package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Cliente;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.ActualizarUsuario;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.CrearUsuario;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.IniciarSesion;
import ec.ups.edu.Heladeria.servicios.UsuarioNoEncontradoException;
import ec.ups.edu.Heladeria.servicios.UsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("usuario")
public class UsuarioControlador {

    private UsuarioServicio usuarioServicio;

    @Autowired
    public void setUsuarioServicio(UsuarioServicio usuarioServicio){
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/myuser")
    public ResponseEntity<Optional<Cliente>> getAllUsuarios(HttpSession httpSession, HttpServletRequest request){
        httpSession = request.getSession();
        System.out.println(httpSession.getAttribute("idCliente"));
       long id = (long) httpSession.getAttribute("idCliente");
        Optional<Cliente> listaClientes = usuarioServicio.findById(id);

        return new ResponseEntity<Optional<Cliente>>(listaClientes, HttpStatus.OK);
    }



    @PostMapping("/iniciarSesion")
    public ResponseEntity<Cliente> getUsuarioIiciado(@RequestBody  IniciarSesion iniciarS, HttpSession httpSession, HttpServletRequest request){
        httpSession = request.getSession(true);

        System.out.println("lllllllllllllll"+httpSession.getId());

        Optional<Cliente> usuarioOptional = Optional.ofNullable(usuarioServicio.iniciarsesion(iniciarS.getCorreo(),iniciarS.getContrasenia()));


        if(usuarioOptional.isEmpty()){
            System.out.println("Usuario no enconrado");
            httpSession.setAttribute("Verificador", "false");
        }else{
            httpSession.setAttribute("Verificador", "true");
            httpSession.setAttribute("idCliente",usuarioOptional.get().getId());
            System.out.println("CorrectoLogin");
            System.out.println(httpSession.getAttribute("idCliente"));
        }

        Cliente cliente = usuarioOptional.orElseThrow(UsuarioNoEncontradoException::new);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<Cliente> createUsuario(@RequestBody CrearUsuario crearUsuario){
        Optional<Cliente> usuarioOptional = Optional.ofNullable(usuarioServicio.retrieveUsuarioByCedula(crearUsuario.getCedula()));
        if(usuarioOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Cliente clienteEcontrado = usuarioOptional.get();
        clienteEcontrado.setCorreo(crearUsuario.getCorreo());
        clienteEcontrado.setContrasenia(crearUsuario.getContrasenia());
        usuarioServicio.save(clienteEcontrado);

        return ResponseEntity.ok(clienteEcontrado);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUsuario(@RequestBody ActualizarUsuario actualizarUsuario, HttpSession httpSession){
        String v = (String) httpSession.getAttribute("Verificador");

        if(v== "true"){
            Optional<Cliente> usuarioOptional = usuarioServicio.findById(actualizarUsuario.getId());
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

            usuarioServicio.save(clienteEncontrado);

            return ResponseEntity.ok(clienteEncontrado);
        }

        return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id){
        usuarioServicio.delete(id);

        return ResponseEntity.ok("Usuario Eliminada Correctamente");
    }
    @GetMapping("/logout")
    public ResponseEntity<?> cerrarS(HttpSession httpSession){
        String v = (String) httpSession.getAttribute("Verificador");

        if(v== "true"){
            httpSession.setAttribute("idCliente",null);
            System.out.println("Sesion cerrada id: "+httpSession.getAttribute("id"));
            return ResponseEntity.ok("Cerrar Sesion correcto");
        }

        return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");
    }


    @GetMapping
    public ResponseEntity<List<Cliente>> getAllPersonas(){
        List<Cliente> listaPersonas = usuarioServicio.findAll();

        return new ResponseEntity<List<Cliente>>(listaPersonas, HttpStatus.OK);
    }

}

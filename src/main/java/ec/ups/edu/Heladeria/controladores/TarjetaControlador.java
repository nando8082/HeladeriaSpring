package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Cliente;
import ec.ups.edu.Heladeria.entidades.Tarjeta;
import ec.ups.edu.Heladeria.entidades.peticiones.tarjeta.ActualizarTarjeta;
import ec.ups.edu.Heladeria.entidades.peticiones.tarjeta.CrearTarjeta;
import ec.ups.edu.Heladeria.servicios.SesionC;
import ec.ups.edu.Heladeria.servicios.UsuarioServicio;
import ec.ups.edu.Heladeria.servicios.TarjetaNoEncontradaException;
import ec.ups.edu.Heladeria.servicios.TarjetaServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("tarjeta")
public class TarjetaControlador {

    private TarjetaServicio tarjetaServicio;
    private UsuarioServicio usuarioServicio;

    @Autowired
    public void setTarjetaServicio(TarjetaServicio tarjetaServicio){
        this.tarjetaServicio = tarjetaServicio;
    }

    @Autowired
    public void setClienteServicio(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }


    //Crear Tarjeta
    @PostMapping("/create")
    public ResponseEntity<?> createTarjeta(@RequestBody CrearTarjeta crearTarjeta, HttpSession httpSession){
        String v = (String) httpSession.getAttribute("Verificador");
        System.out.println(v);


        if(v== "true"){

        long id = (long) httpSession.getAttribute("idCliente");
        Optional<Cliente> usuario = usuarioServicio.findById(id);


        if (usuario.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNombreTitular(crearTarjeta.getNombreTitular());
        tarjeta.setNumTarjeta(crearTarjeta.getNumTarjeta());
        tarjeta.setFechaCducidad(crearTarjeta.getFechaCducidad());
        tarjeta.setCodigoCvv(crearTarjeta.getCodigoCvv());
        tarjeta.setTipo(crearTarjeta.getTipo());
        tarjeta.setUsuario(usuario.get());

        tarjetaServicio.save(tarjeta);

       return   ResponseEntity.ok(tarjeta);

        }

        return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");

    }

    //Buscar por número de tarjeta

    @GetMapping("/{numTarjeta}")
    public ResponseEntity<Tarjeta> getTarjetaBynumTarjeta(@PathVariable int numTarjeta){
        Optional<Tarjeta> tarjetaOptional = Optional.ofNullable(tarjetaServicio.retrieveTarjetaBynumTarjeta(numTarjeta));
        Tarjeta tarjeta = tarjetaOptional.orElseThrow(TarjetaNoEncontradaException::new);
        return new ResponseEntity<Tarjeta>(tarjeta, HttpStatus.OK);
    }

    //Listar todas las tarjetas



    @GetMapping("/myCards")
    public ResponseEntity<List<Tarjeta>> getTarjetasUser(HttpSession httpSession){
        long id = (long) httpSession.getAttribute("idCliente");
        System.out.println(id);
        List<Tarjeta> listadoTarjetas = tarjetaServicio.retrieveTarjetas(id);

        return new ResponseEntity<List<Tarjeta>>(listadoTarjetas, HttpStatus.OK);
    }


    //Eliminar Tarjeta
    @DeleteMapping("/delete/{numTarjeta}")
    public ResponseEntity<String> deleteTarjeta(@PathVariable int numTarjeta){
        tarjetaServicio.delete(numTarjeta);

        return ResponseEntity.ok("Tarjeta Eliminada Correctamente");
    }

    //Actualizar Tarjeta

    @PutMapping("/update")
    public ResponseEntity<Tarjeta> updateTarjeta(@RequestBody ActualizarTarjeta actualizarTarjeta){
        Optional<Tarjeta> tarjetaOptional = tarjetaServicio.findById(actualizarTarjeta.getId());
        if(tarjetaOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Tarjeta tarjetaEncontrada = tarjetaOptional.get();
        tarjetaEncontrada.setNombreTitular(actualizarTarjeta.getNombreTitular());
        tarjetaEncontrada.setNumTarjeta(actualizarTarjeta.getNumTarjeta());
        tarjetaEncontrada.setFechaCducidad(actualizarTarjeta.getFechaCducidad());
        tarjetaEncontrada.setCodigoCvv(actualizarTarjeta.getCodigoCvv());
        tarjetaEncontrada.setTipo(actualizarTarjeta.getTipo());

        tarjetaServicio.save(tarjetaEncontrada);

        return ResponseEntity.ok(tarjetaEncontrada);
    }


}

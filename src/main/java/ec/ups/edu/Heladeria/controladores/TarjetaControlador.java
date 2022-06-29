package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Cliente;
import ec.ups.edu.Heladeria.entidades.Tarjeta;
import ec.ups.edu.Heladeria.entidades.peticiones.tarjeta.CrearTarjeta;
import ec.ups.edu.Heladeria.entidades.peticiones.usuario.ActualizarCliente;
import ec.ups.edu.Heladeria.servicios.ClienteNoEncontradoException;
import ec.ups.edu.Heladeria.servicios.ClienteServicio;
import ec.ups.edu.Heladeria.servicios.TarjetaNoEncontradaException;
import ec.ups.edu.Heladeria.servicios.TarjetaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TarjetaControlador {

    private TarjetaServicio tarjetaServicio;
    private ClienteServicio clienteServicio;

    @Autowired
    public void setTarjetaServicio(TarjetaServicio tarjetaServicio){
        this.tarjetaServicio = tarjetaServicio;
    }

    @Autowired
    public void setClienteServicio(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @PostMapping("/tarjeta/create")
    public ResponseEntity<Tarjeta> createTarjeta(@RequestBody CrearTarjeta crearTarjeta){

        Optional<Cliente> cliente = clienteServicio.findById(crearTarjeta.getIdCliente());

        if (cliente.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNombreTitular(crearTarjeta.getNombreTitular());
        tarjeta.setNumTarjeta(crearTarjeta.getNumTarjeta());
        tarjeta.setFechaCducidad(crearTarjeta.getFechaCducidad());
        tarjeta.setCodigoCvv(crearTarjeta.getCodigoCvv());
        tarjeta.setTipo(crearTarjeta.getTipo());
        tarjeta.setCliente(cliente.get());

        tarjetaServicio.save(tarjeta);
        return ResponseEntity.ok(tarjeta);
    }

    @GetMapping("/tarjeta/{numTarjeta}")
    public ResponseEntity<Tarjeta> getTarjetaBynumTarjeta(@PathVariable int numTarjeta){
        Optional<Tarjeta> tarjetaOptional = Optional.ofNullable(tarjetaServicio.retrieveTarjetaBynumTarjeta(numTarjeta));
        Tarjeta tarjeta = tarjetaOptional.orElseThrow(TarjetaNoEncontradaException::new);
        return new ResponseEntity<Tarjeta>(tarjeta, HttpStatus.OK);
    }

    @GetMapping("/tarjetas")
    public ResponseEntity<List<Tarjeta>> getAllUsuarios(){
        List<Tarjeta> listaTarjetas = tarjetaServicio.findAll();

        return new ResponseEntity<List<Tarjeta>>(listaTarjetas, HttpStatus.OK);
    }


}

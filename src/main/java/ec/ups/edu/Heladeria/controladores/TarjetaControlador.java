package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Cliente;
import ec.ups.edu.Heladeria.entidades.Tarjeta;
import ec.ups.edu.Heladeria.entidades.peticiones.tarjeta.CrearTarjeta;
import ec.ups.edu.Heladeria.servicios.ClienteServicio;
import ec.ups.edu.Heladeria.servicios.TarjetaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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




}

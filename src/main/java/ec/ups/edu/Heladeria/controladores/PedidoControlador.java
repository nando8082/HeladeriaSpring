package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.*;
import ec.ups.edu.Heladeria.entidades.peticiones.pedido.ActualizarPedido;
import ec.ups.edu.Heladeria.entidades.peticiones.pedido.CrearPedido;
import ec.ups.edu.Heladeria.servicios.UsuarioServicio;
import ec.ups.edu.Heladeria.servicios.PedidoNoEncontradoException;
import ec.ups.edu.Heladeria.servicios.PedidoServicio;
import ec.ups.edu.Heladeria.servicios.TarjetaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //notacion de spring
public class PedidoControlador {

    private PedidoServicio pedidoServicio;
    private UsuarioServicio usuarioServicio;
    private TarjetaServicio tarjetaServicio;

    @Autowired //inyeccion de dependencia
    public void setPedidoServicio(PedidoServicio pedidoServicio) {
        this.pedidoServicio = pedidoServicio;
    }
    @Autowired
    public void setClienteServicio(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }
    @Autowired
    public void setTarjetaServicio(TarjetaServicio tarjetaServicio) {
        this.tarjetaServicio = tarjetaServicio;
    }






    @GetMapping("/pedidos") //obtener el listado de Pedidos
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> listaPedidos = pedidoServicio.findAll();
        return new ResponseEntity<List<Pedido>>(listaPedidos, HttpStatus.OK);
    }

    @PostMapping("/pedido/create") //crear Pedido
    public ResponseEntity<Pedido> createPedido(@RequestBody CrearPedido crearPedido) {
        Optional<Cliente> clineteOptional = usuarioServicio.findById(crearPedido.getIdCliente());
        if(clineteOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Optional<Tarjeta> optionalTarjeta = tarjetaServicio.findById(crearPedido.getIdTarjeta());
        if(optionalTarjeta.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Pedido pedido = new Pedido();

        pedido.setCliente(clineteOptional.get());
        pedido.setLatitud(crearPedido.getLatitud());
        pedido.setLongitud(crearPedido.getLongitud());
        pedido.setEstado(crearPedido.getEstado());
        pedido.setCostoEnvio(crearPedido.getCostoEnvio());
        pedido.setDetalles(crearPedido.getDetalles());
        pedido.setTarjeta(optionalTarjeta.get());
        pedidoServicio.save(pedido);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/pedido/update")
    public ResponseEntity<Pedido> updatePedido(@RequestBody ActualizarPedido actualizarPedido) {
        Optional<Pedido> pedidoOptional = pedidoServicio.findById(actualizarPedido.getIdPedido());
        if (pedidoOptional.isEmpty()) {
            Pedido pedido =pedidoOptional.orElseThrow(PedidoNoEncontradoException::new);
            return new ResponseEntity <Pedido>(pedido,HttpStatus.OK);
        }
        Optional<Cliente> clineteOptional = usuarioServicio.findById(actualizarPedido.getIdCliente());
        if(clineteOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Optional<Tarjeta> optionalTarjeta = tarjetaServicio.findById(actualizarPedido.getIdTarjeta());
        if(optionalTarjeta.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Pedido pedidoEncontrado = pedidoOptional.get();
        pedidoEncontrado.setCliente(clineteOptional.get());
        pedidoEncontrado.setLatitud(actualizarPedido.getLatitud());
        pedidoEncontrado.setLongitud(actualizarPedido.getLongitud());
        pedidoEncontrado.setEstado(actualizarPedido.getEstado());
        pedidoEncontrado.setCostoEnvio(actualizarPedido.getCostoEnvio());
        pedidoEncontrado.setDetalles(actualizarPedido.getDetalles());
        pedidoEncontrado.setTarjeta(optionalTarjeta.get());
        pedidoServicio.save(pedidoEncontrado);
        return ResponseEntity.ok(pedidoEncontrado);
    }

    @DeleteMapping("/pedido/delete/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable long id) {
        pedidoServicio.delete(id);
        return ResponseEntity.ok("Pedido eliminado correctamente");
    }



}

package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.*;
import ec.ups.edu.Heladeria.entidades.peticiones.pedido.ActualizarPedido;
import ec.ups.edu.Heladeria.entidades.peticiones.pedido.CrearPedido;
import ec.ups.edu.Heladeria.servicios.*;
import jakarta.servlet.http.HttpSession;
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

    private SucursalServicio sucursalServicio;
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
    @Autowired
    public void setSucursalServicio(SucursalServicio sucursalServicio) {
        this.sucursalServicio = sucursalServicio;
    }








    @GetMapping("/pedidos") //obtener el listado de Pedidos
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> listaPedidos = pedidoServicio.findAll();
        return new ResponseEntity<List<Pedido>>(listaPedidos, HttpStatus.OK);
    }

    @PostMapping("/pedido/create") //crear Pedido
    public ResponseEntity<Pedido> createPedido(@RequestBody CrearPedido crearPedido, HttpSession httpSession) {
        long idCl = (long) httpSession.getAttribute("idCliente");
        Optional<Cliente> clineteOptional = usuarioServicio.findById(idCl);
        if(clineteOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Optional<Tarjeta> optionalTarjeta = Optional.ofNullable(tarjetaServicio.retrieveTarjetaBynumTarjeta(crearPedido.getNumTarjeta()));
        if(optionalTarjeta.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Optional<Sucursal> optionalSucursal = Optional.ofNullable(sucursalServicio.retrieveSucursalName(crearPedido.getNombreS()));
        if(optionalSucursal.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

      double lat1 = optionalSucursal.get().getLatitud();
        double lng1 = optionalSucursal.get().getLongitud();
       double lat2 = crearPedido.getLatitud();
       double lng2 = crearPedido.getLongitud();

        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;
        double distanciaT = Math.round(distancia*100.0)/100.0;

        System.out.println("Distancia es: "+distanciaT+" km");
        double cEnvio = distancia*0.75;
        Pedido pedido = new Pedido();
        pedido.setCliente(clineteOptional.get());
        pedido.setLatitud(crearPedido.getLatitud());
        pedido.setLongitud(crearPedido.getLongitud());
        pedido.setEstado("En Cola");
        pedido.setCostoEnvio(cEnvio);
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

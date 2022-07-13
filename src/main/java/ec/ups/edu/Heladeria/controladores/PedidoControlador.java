package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.*;
import ec.ups.edu.Heladeria.entidades.peticiones.Factura.CreaFactura;
import ec.ups.edu.Heladeria.entidades.peticiones.detalle.CrearDetalle;
import ec.ups.edu.Heladeria.entidades.peticiones.pedido.CrearPedido;
import ec.ups.edu.Heladeria.servicios.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController//notacion de spring
public class PedidoControlador{

    private PedidoServicio pedidoServicio;
    private UsuarioServicio usuarioServicio;
    private TarjetaServicio tarjetaServicio;

    private SucursalServicio sucursalServicio;

    private DetalleServicio detalleServicio;

    private ProductoServicios productoServicios;
    private FacturaServicio facturaServicio;


    @Autowired
    public void setFacturaServicio(FacturaServicio facturaServicio) {
        this.facturaServicio = facturaServicio;
    }


    @Autowired
    public void setDetalleServicioñ(DetalleServicio detalleServicioñ) {
        this.detalleServicio = detalleServicioñ;
    }

    @Autowired
    public void setProductoServicios(ProductoServicios productoServicios) {
        this.productoServicios = productoServicios;
    }

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


    List<Detalle> detalles = new ArrayList<Detalle>();


    private double sumaTotal = 0;


  
    @GetMapping("/pedidos") //obtener el listado de Pedidos
    public ResponseEntity<List<Pedido>> getAllPedidos(HttpSession httpSession) {
        //String v = (String) httpSession.getAttribute("Verificador");

        //if(v== "true"){
            List<Pedido> listaPedidos = pedidoServicio.findAll();
            return new ResponseEntity<List<Pedido>>(listaPedidos, HttpStatus.OK);
        //}

        //return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");
    }

    @GetMapping("/pedido/cliente/{id}") //obtener el listado de Pedidos
    public ResponseEntity<List<Pedido>> getAllPedidosCliente(@PathVariable Long id, HttpSession httpSession) {
        //String v = (String) httpSession.getAttribute("Verificador");

        //if(v== "true"){
            Optional<Cliente> clienteOptional = Optional.ofNullable(usuarioServicio.retrieveUsuarioById(id));
            if(clienteOptional.isEmpty()){
                return ResponseEntity.badRequest().build();
            }
            long idC = clienteOptional.get().getId();
            List<Pedido> pedidoList = pedidoServicio.retrieveIdCliente(idC);
            System.out.println(pedidoList);
            return new ResponseEntity<List<Pedido>>(pedidoList, HttpStatus.OK);
        //}

        //return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");
    }

    @GetMapping("/pedido/cliente") //obtener el listado de Pedidos
    public ResponseEntity<List<Pedido>> getAllPedidosClienteDirecto(HttpSession httpSession) {
        //String v = (String) httpSession.getAttribute("Verificador");

        //if(v== "true"){
            long idCl = (long) httpSession.getAttribute("idCliente");
            Optional<Cliente> clienteOptional = Optional.ofNullable(usuarioServicio.retrieveUsuarioById(idCl));
            if(clienteOptional.isEmpty()){
                return ResponseEntity.badRequest().build();
            }

            long idC = clienteOptional.get().getId();
            List<Pedido> pedidoList = pedidoServicio.retrieveIdCliente(idC);
            System.out.println(pedidoList);
            return new ResponseEntity<List<Pedido>>(pedidoList, HttpStatus.OK);
        //}

        //return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");
    }



    @PostMapping("/pedido/terminar")
    //crear Pedido
    public ResponseEntity<?> createPedido(@RequestBody CrearPedido crearPedido, HttpSession httpSession) {

        String v = (String) httpSession.getAttribute("Verificador");

        if(v== "true"){
            long idCl = (long) httpSession.getAttribute("idCliente");
            Optional<Cliente> clineteOptional = usuarioServicio.findById(idCl);
            if (clineteOptional.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Optional<Tarjeta> optionalTarjeta = Optional.ofNullable(tarjetaServicio.retrieveTarjetaBynumTarjeta(crearPedido.getNumTarjeta()));
            if (optionalTarjeta.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Optional<Sucursal> optionalSucursal = Optional.ofNullable(sucursalServicio.retrieveSucursalName(crearPedido.getNombreS()));
            if (optionalSucursal.isEmpty()) {
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
            double distanciaT = Math.round(distancia * 100.0) / 100.0;

            System.out.println("Distancia es: " + distanciaT + " km");
            double cEnvio = distancia * 0.75;
            Pedido pedido = new Pedido();
            pedido.setCliente(clineteOptional.get());
            pedido.setLatitud(crearPedido.getLatitud());
            pedido.setLongitud(crearPedido.getLongitud());
            pedido.setEstado("Procesando");
            pedido.setCostoEnvio(cEnvio);
            pedido.setTotal((sumaTotal = detalles.stream().mapToDouble(dt -> dt.getSubtotal()).sum()) + cEnvio);
            pedido.setTarjeta(optionalTarjeta.get());
            pedidoServicio.save(pedido);


            for (int x = 0; x < detalles.size(); x++) {

                Detalle d = detalles.get(x);
                d.setPedido(pedido);
                detalleServicio.Crear(d);
            }
            return ResponseEntity.ok(pedido);

        }

        return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");


    }


    @DeleteMapping("/pedido/delete/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable long id, HttpSession httpSession) {

        String v = (String) httpSession.getAttribute("Verificador");

        if(v== "true"){

            pedidoServicio.delete(id);
            return ResponseEntity.ok("Pedido eliminado correctamente");

        }

        return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");
    }

    @PutMapping("/pedido/confirmarEntrega")
    public ResponseEntity<?> createFactura(@RequestBody CreaFactura creaFactura, HttpSession httpSession) {
        String v = (String) httpSession.getAttribute("Verificador");

        if(v== "true"){

            Optional<Pedido> pedido = pedidoServicio.findById(creaFactura.getPedido());


            if (pedido.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Pedido pedidoEncontrado = pedido.get();
            Factura factura = new Factura();
            System.out.println(pedidoEncontrado.getEstado());
            if(pedidoEncontrado.getEstado().equals("Finalizado")){
                System.out.println("Pedido ya fdacturado");
            } else if (!pedidoEncontrado.getEstado().equals("Finalizado"))
            {


                pedidoEncontrado.setEstado("Finalizado");
                pedidoServicio.save(pedidoEncontrado);

                factura.setFecha(new Date());
                factura.setPedido(pedido.get());
                factura.setSubtotal(pedido.get().getTotal());
                factura.setIva(0.12);
                factura.setTotal((pedido.get().getTotal() * 0.12) + pedido.get().getTotal());
                facturaServicio.save(factura);

            }
            return ResponseEntity.ok(factura);

        }

        return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");

    }

    @PostMapping("/detalle1/create") //crear Pedido
    public ResponseEntity<?> createDetalle(@RequestBody CrearDetalle crearDetalle, HttpSession httpSession) {
        String v = (String) httpSession.getAttribute("Verificador");

        if(v== "true"){
            long id = (long) httpSession.getAttribute("idCliente");
            Optional<Producto> producto = productoServicios.findById(crearDetalle.getIdproducto());
            String estado = "procesando";
            Optional<Pedido> optionalPedido = Optional.ofNullable(pedidoServicio.retrieveIdClienteEstado(id, estado));

            System.out.println("optional " + optionalPedido);


            if (producto.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Detalle detalle1;
            boolean prod = false;
            Producto producto1 = producto.get();

            for (int x = 0; x < detalles.size(); x++) {

                Detalle d = detalles.get(x);
                if (crearDetalle.getIdproducto() == d.getProducto().getId()) {
                    System.out.println("Prodiuccto");
                    prod = true;
                    sumaTotal = 0;
                    // Producto producto1 = d.getProducto();
                    d.setCantidad(crearDetalle.getCantidad() + d.getCantidad());
                    System.out.println(crearDetalle.getCantidad() + d.getCantidad());
                    d.setSubtotal(d.getProducto().getPrecio() * (d.getCantidad()));
                    break; // Terminar ciclo, pues ya lo encontramos
                }
            }

            if (prod == false) {
                Detalle detalle = new Detalle();
                detalle.setCantidad(crearDetalle.getCantidad());
                detalle.setPrecio(producto1.getPrecio());
                detalle.setProducto(producto1);
                detalle.setSubtotal(producto1.getPrecio() * crearDetalle.getCantidad());
                //  detalle.setPedido(pedidoOptional.get());
                //validar que le producto no se añada 2 veces


                detalles.add(detalle);
            }
            sumaTotal = detalles.stream().mapToDouble(dt -> dt.getSubtotal()).sum();

            return new ResponseEntity<List<Detalle>>(detalles, HttpStatus.OK);

        }

        return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");
    }



}

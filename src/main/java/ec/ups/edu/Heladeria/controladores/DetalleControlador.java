package ec.ups.edu.Heladeria.controladores;


import ec.ups.edu.Heladeria.entidades.Detalle;
import ec.ups.edu.Heladeria.entidades.Pedido;
import ec.ups.edu.Heladeria.entidades.Producto;
import ec.ups.edu.Heladeria.entidades.peticiones.detalle.CrearDetalle;

import ec.ups.edu.Heladeria.repositorios.DetalleRepositorio;
import ec.ups.edu.Heladeria.servicios.DetalleServicio;
import ec.ups.edu.Heladeria.servicios.PedidoNoEncontradoException;
import ec.ups.edu.Heladeria.servicios.PedidoServicio;
import ec.ups.edu.Heladeria.servicios.ProductoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
public class DetalleControlador {

    private DetalleServicio detalleServicio;
    private ProductoServicios productoServicio;
    private PedidoServicio pedidoServicio;

    @Autowired
    public void setDetalleServicio(DetalleServicio detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

    @Autowired
    public void setProductoServicio(ProductoServicios productoServicio) {
        this.productoServicio = productoServicio;
    }

    @Autowired
    public void setPedidoServicio(PedidoServicio pedidoServicio) {
        this.pedidoServicio = pedidoServicio;
    }

    @PostMapping("/detalle/create") //crear Pedido
    public ResponseEntity<Detalle> createDetalle(@RequestBody CrearDetalle crearDetalle) {

        Optional<Producto> producto = productoServicio.findById(crearDetalle.getIdproducto());
        System.out.printf(String.valueOf(producto.get()));
        if (producto.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Pedido> pedidoOptional = pedidoServicio.findById(crearDetalle.getIdpedido());
        System.out.printf(String.valueOf(pedidoOptional.get()));
        if (pedidoOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Producto producto1 = producto.get();
        Detalle detalle = new Detalle();
        detalle.setCantidad(crearDetalle.getCantidad());
        detalle.setPrecio(producto1.getPrecio());
        detalle.setProducto(producto1);
        detalle.setSubtotal(producto1.getPrecio()*crearDetalle.getCantidad());
        detalle.setPedido(pedidoOptional.get());
        detalleServicio.Crear(detalle);
        return ResponseEntity.ok(detalle);
    }
}


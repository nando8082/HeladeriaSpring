package ec.ups.edu.Heladeria.controladores;


import ec.ups.edu.Heladeria.entidades.*;
import ec.ups.edu.Heladeria.entidades.peticiones.detalle.CrearDetalle;

import ec.ups.edu.Heladeria.servicios.DetalleServicio;
import ec.ups.edu.Heladeria.servicios.PedidoNoEncontradoException;
import ec.ups.edu.Heladeria.servicios.PedidoServicio;
import ec.ups.edu.Heladeria.servicios.ProductoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/detalle/agregarACarritto") //crear Pedido
    public ResponseEntity<Detalle> createDetalle(@RequestBody CrearDetalle crearDetalle) {

        Optional<Producto> producto = productoServicio.findById(crearDetalle.getIdproducto());
        System.out.printf(String.valueOf(producto.get()));
        if (producto.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }





        Detalle detalle = new Detalle();




        detalle.setCantidad(crearDetalle.getCantidad());
        detalle.setPrecio(producto.get().getPrecio());
        detalle.setProducto(producto.get());
        detalle.setSubtotal(producto.get().getPrecio()*crearDetalle.getCantidad());

        detalleServicio.Crear(detalle);

        return ResponseEntity.ok(detalle);




    }
}


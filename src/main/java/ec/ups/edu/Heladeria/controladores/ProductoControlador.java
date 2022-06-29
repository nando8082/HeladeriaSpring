package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Producto;
import ec.ups.edu.Heladeria.servicios.ProductoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoControlador {

    private ProductoServicios productoServicios;

    @Autowired
    public ProductoControlador(ProductoServicios productoServicios) {
        this.productoServicios = productoServicios;
    }
    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> listaProductos = productoServicios.findAll();
        return new ResponseEntity<List<Producto>>(listaProductos, HttpStatus.OK);
    }
}

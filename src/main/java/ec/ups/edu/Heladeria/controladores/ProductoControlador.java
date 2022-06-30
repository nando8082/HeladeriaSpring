package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Producto;
import ec.ups.edu.Heladeria.entidades.Sucursal;
import ec.ups.edu.Heladeria.entidades.peticiones.producto.CrearProducto;
import ec.ups.edu.Heladeria.servicios.ProductoNoEncontradoException;
import ec.ups.edu.Heladeria.servicios.ProductoServicios;
import ec.ups.edu.Heladeria.servicios.SucursalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductoControlador {

    private ProductoServicios productoServicios;
    private SucursalServicio sucursalServicio;

    @Autowired
    public ProductoControlador(ProductoServicios productoServicios) {
        this.productoServicios = productoServicios;
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> listaProductos = productoServicios.findAll();
        return new ResponseEntity<List<Producto>>(listaProductos, HttpStatus.OK);
    }

    @GetMapping("/productos/sucursal/{id}")
    public ResponseEntity<List<Producto>> getProductosSucursal(@PathVariable Long id){
        List<Producto> listadoSucursal = productoServicios.retrieveProductosSucursal(id);

        return new ResponseEntity<List<Producto>>(listadoSucursal, HttpStatus.OK);
    }

    @GetMapping("/producto/nombre")
    public ResponseEntity<List<String>> getAllNombre(){
        List<String> listaNombres = productoServicios.retrieveAllNombre();

        return new ResponseEntity<List<String>>(listaNombres, HttpStatus.OK);
    }

    @GetMapping("/producto/id/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long id){
        Optional<Producto> productoOptional = Optional.ofNullable(productoServicios.retrieveAllProducto(id));
        Producto producto = productoOptional.orElseThrow(ProductoNoEncontradoException::new);
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }

    @GetMapping("/productos/precio/{precio}")
    public ResponseEntity<List<String>> getPorPrecio(@PathVariable double precio){
        List<String> listaNombres = productoServicios.retrievePorPrecio(precio);

        return new ResponseEntity<List<String>>(listaNombres, HttpStatus.OK);
    }

    @PostMapping("/producto/create")
    public ResponseEntity<Producto> createProducto(@RequestBody CrearProducto crearProducto){
        Optional<Sucursal> sucursal = sucursalServicio.findByCodigo(crearProducto.getIdSucursal());

        if(sucursal.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Producto producto = new Producto();
        producto.setNombre(crearProducto.getNombre());
        producto.setDescripcion(crearProducto.getDescripcion());
        producto.setMarca(crearProducto.getMarca());
        producto.setStock(crearProducto.getStock());
        producto.setEstado(crearProducto.getEstado());
        producto.setPrecio(crearProducto.getPrecio());
        producto.setCantidad(crearProducto.getCantidad());
        producto.setSucursal(sucursal.get());

        productoServicios.save(producto);
        return  ResponseEntity.ok(producto);
    }
}

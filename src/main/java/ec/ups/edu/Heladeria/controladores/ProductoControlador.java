package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Categorias;
import ec.ups.edu.Heladeria.entidades.Producto;
import ec.ups.edu.Heladeria.entidades.Sucursal;
import ec.ups.edu.Heladeria.servicios.CategoriaServicio;
import ec.ups.edu.Heladeria.servicios.ProductoNoEncontradoException;
import ec.ups.edu.Heladeria.servicios.ProductoServicios;
import ec.ups.edu.Heladeria.servicios.SucursalServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ProductoControlador {

    private ProductoServicios productoServicios;
    private SucursalServicio sucursalServicio;
    private CategoriaServicio categoriaServicio;
    @Autowired
    public void setCategoriaServicio(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    @Autowired
    public ProductoControlador(ProductoServicios productoServicios) {
        this.productoServicios = productoServicios;
    }
    @Autowired
    public void setSucursalServicio(SucursalServicio sucursalServicio) {
        this.sucursalServicio = sucursalServicio;
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getAllProductos() {
        ///String v = (String) httpSession.getAttribute("Verificador");

        ///if(v== "true"){
            List<Producto> listaProductos = productoServicios.findAll();
            return new ResponseEntity<List<Producto>>(listaProductos, HttpStatus.OK);
        ///}

        ///return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");
    }

    @GetMapping("/productos/sucursal/{nombreS}")
    public ResponseEntity<List<Producto>> getProductosSucursal(@PathVariable String nombreS){

        Optional<Sucursal> sucursalOptional = Optional.ofNullable(sucursalServicio.retrieveSucursalName(nombreS));
        if(sucursalOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        long id = sucursalOptional.get().getId();
        List<Producto> listadoSucursal = productoServicios.retrieveProductosSucursal(id);
        System.out.println(listadoSucursal);
        return new ResponseEntity<List<Producto>>(listadoSucursal, HttpStatus.OK);
    }

    @GetMapping("/productos/categoria/{nombreC}")
    public ResponseEntity<List<Producto>> getProductosCat(@PathVariable String nombreC){
        Optional<Categorias> categoriasOptional = Optional.ofNullable(categoriaServicio.retrieveCateName(nombreC));
        if(categoriasOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        long id = categoriasOptional.get().getId();
        List<Producto> lstCat = productoServicios.retrieveProductosCat(id);
        System.out.println(lstCat);
        return new ResponseEntity<List<Producto>>(lstCat, HttpStatus.OK);
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


}

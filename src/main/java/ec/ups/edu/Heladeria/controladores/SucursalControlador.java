package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Sucursal;
import ec.ups.edu.Heladeria.entidades.Tarjeta;
import ec.ups.edu.Heladeria.servicios.SucursalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("sucursal")
public class SucursalControlador {

    private SucursalServicio sucursalServicio;

    @Autowired
    public void setSucursalServicio(SucursalServicio sucursalServicio) {
        this.sucursalServicio = sucursalServicio;
    }

    @GetMapping
    public ResponseEntity<List<Sucursal>> getAllTarjetas(){
        List<Sucursal> listaSucursales = sucursalServicio.findAll();

        return new ResponseEntity<List<Sucursal>>(listaSucursales, HttpStatus.OK);
    }
}

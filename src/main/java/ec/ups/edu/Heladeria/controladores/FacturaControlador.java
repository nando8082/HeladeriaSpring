package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Cliente;
import ec.ups.edu.Heladeria.entidades.Factura;
import ec.ups.edu.Heladeria.servicios.FacturaServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("factura")
public class FacturaControlador {

    private FacturaServicio facturaServicio;

    @Autowired
    public void setFacturaServicio(FacturaServicio facturaServicio) {
        this.facturaServicio = facturaServicio;
    }

    @GetMapping
    public ResponseEntity<?> getAllFacturas(HttpSession httpSession) {

        String v = (String) httpSession.getAttribute("Verificador");

        if(v== "true"){
            long id = (long) httpSession.getAttribute("idCliente");

            List<Factura> listaFacturas = facturaServicio.findAll();

            return new ResponseEntity<List<Factura>>(listaFacturas,HttpStatus.OK);
        }

        return  ResponseEntity.badRequest().body("NO HA INICIADO SESION");
    }


}

package ec.ups.edu.Heladeria.controladores;

import ec.ups.edu.Heladeria.entidades.Cliente;
import ec.ups.edu.Heladeria.entidades.Factura;
import ec.ups.edu.Heladeria.repositorios.UsuarioRepositorio;
import ec.ups.edu.Heladeria.servicios.FacturaServicio;
import ec.ups.edu.Heladeria.servicios.UsuarioServicio;
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
    private UsuarioServicio usuarioServicio;

    @Autowired
    public void setFacturaServicio(FacturaServicio facturaServicio) {
        this.facturaServicio = facturaServicio;
    }

    @Autowired
    public void setUsuarioServicio(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/cliente") //obtener el listado de Facturas
    public ResponseEntity<List<Factura>> getAllFacturasCliente(HttpSession httpSession) {
        long idCl = (long) httpSession.getAttribute("idCliente");
        System.out.println(idCl);
        Optional<Cliente> clienteOptional = Optional.ofNullable(usuarioServicio.retrieveUsuarioById(idCl));
        if(clienteOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        long idC = clienteOptional.get().getId();
        System.out.println(idC);
        List<Factura> facturaList = facturaServicio.retrieveIdCliente(idC);
        //System.out.println(facturaList);
        return new ResponseEntity<List<Factura>>(facturaList, HttpStatus.OK);
    }


}

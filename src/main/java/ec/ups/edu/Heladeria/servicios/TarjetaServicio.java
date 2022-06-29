package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Cliente;
import ec.ups.edu.Heladeria.entidades.Tarjeta;
import ec.ups.edu.Heladeria.repositorios.TarjetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaServicio {

    @Autowired
    TarjetaRepositorio tarjetaRepositorio;

    public void save(Tarjeta tarjeta){

        tarjetaRepositorio.save(tarjeta);
    }

    public Tarjeta retrieveTarjetaBynumTarjeta(int numTarjeta){
        return tarjetaRepositorio.findTarjetaBynumTarjeta(numTarjeta);
    }

    public List<Tarjeta> findAll(){
        List<Tarjeta> tarjeta = (List<Tarjeta>) tarjetaRepositorio.findAll();
        return tarjeta;
    }

}
package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Tarjeta;
import ec.ups.edu.Heladeria.repositorios.TarjetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarjetaServicio {

    @Autowired
    TarjetaRepositorio tarjetaRepositorio;

    public void save(Tarjeta tarjeta){

        tarjetaRepositorio.save(tarjeta);
    }

}

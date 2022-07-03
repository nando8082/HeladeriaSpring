package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Producto;
import ec.ups.edu.Heladeria.entidades.Tarjeta;
import ec.ups.edu.Heladeria.repositorios.TarjetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaServicio {

    @Autowired
    TarjetaRepositorio tarjetaRepositorio;

    public void save(Tarjeta tarjeta){

        tarjetaRepositorio.save(tarjeta);
    }
    public Optional<Tarjeta> findById(long id){
        return (Optional<Tarjeta>) tarjetaRepositorio.findById(id);
    }

    public Tarjeta retrieveTarjetaBynumTarjeta(int numTarjeta){
        return tarjetaRepositorio.findTarjetaBynumTarjeta(numTarjeta);
    }

    public List<Tarjeta> findAll(){
        List<Tarjeta> tarjeta = (List<Tarjeta>) tarjetaRepositorio.findAll();
        return tarjeta;
    }

    public List<Tarjeta> retrieveTarjetas(Long id){
        return (List<Tarjeta>) tarjetaRepositorio.findTarjetaID(id);
    }



    public void delete(int numTarjeta){
        Tarjeta tarjeta = tarjetaRepositorio.findTarjetaBynumTarjeta(numTarjeta);
        long id = tarjeta.getId();
        System.out.println(id);

        tarjetaRepositorio.deleteById(id);
    }

}

package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Producto;
import ec.ups.edu.Heladeria.entidades.Sucursal;
import ec.ups.edu.Heladeria.entidades.Tarjeta;
import ec.ups.edu.Heladeria.repositorios.SucursalRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalServicio {

    @Autowired
    SucursalRepositorio sucursalRepositorio;

    public void save(Sucursal sucursal){
        sucursalRepositorio.save(sucursal);}

    public Optional<Sucursal> findByCodigo(long codigo){
        return (Optional<Sucursal>) sucursalRepositorio.findById(codigo);
    }

    public Sucursal retrieveSucursalName(String nombre){

        return sucursalRepositorio.findSucursalNombre(nombre);
    }

    public List<Sucursal> findAll(){
        List<Sucursal> sucursal = (List<Sucursal>) sucursalRepositorio.findAll();
        return sucursal;
    }
}

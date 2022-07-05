package ec.ups.edu.Heladeria.servicios;

import ec.ups.edu.Heladeria.entidades.Categorias;
import ec.ups.edu.Heladeria.entidades.Sucursal;
import ec.ups.edu.Heladeria.repositorios.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServicio {
    @Autowired
    CategoriaRepositorio categoriaRepositorio;

    public Categorias retrieveCateName(String nombre){

        return categoriaRepositorio.findCatNombre(nombre);
    }
}

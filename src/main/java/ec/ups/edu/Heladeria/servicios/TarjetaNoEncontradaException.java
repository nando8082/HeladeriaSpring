package ec.ups.edu.Heladeria.servicios;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Tarjeta no encontrada!")
public class TarjetaNoEncontradaException extends RuntimeException{

        public TarjetaNoEncontradaException() {
        }

        public TarjetaNoEncontradaException(String message) {
            super(message);
        }



}

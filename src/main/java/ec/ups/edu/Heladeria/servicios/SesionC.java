package ec.ups.edu.Heladeria.servicios;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "noooooooooooooooooooooooooo")
    public class SesionC extends RuntimeException {
        public SesionC(){

        }
        public SesionC(String message){
            super(message);
        }

    }


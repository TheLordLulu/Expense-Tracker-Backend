package lkenneth.expensetracker.domain.core.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceCreationException extends RuntimeException{
    public ResourceCreationException(String message){
        super(message);
    }
}

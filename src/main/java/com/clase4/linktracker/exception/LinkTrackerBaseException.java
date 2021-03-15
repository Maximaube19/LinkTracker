package com.clase4.linktracker.exception;

import com.clase4.linktracker.model.ErrorDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Maxi Maubecin
 */
@Getter @Setter
public class LinkTrackerBaseException extends Exception{
    private ErrorDTO errorDTO;
    private HttpStatus status;

    public LinkTrackerBaseException(String message, HttpStatus status) {
        super(message);
        this.errorDTO = new ErrorDTO();
        errorDTO.setName(this.getClass().getSimpleName());
        errorDTO.setDescription(message);
        this.status = status;
    }
}

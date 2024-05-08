package br.com.gustavo.gestao_vagas.modules.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.gustavo.gestao_vagas.modules.exceptions.DTO.MessageErrorDTO;

@ControllerAdvice
public class ExceptionHandlerController {
    
    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<MessageErrorDTO>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<MessageErrorDTO> dto = new ArrayList<>();
        
        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = this.messageSource.getMessage(err, LocaleContextHolder.getLocale());
            MessageErrorDTO error = new MessageErrorDTO(message, err.getField());
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }   
}

package br.com.zupacademy.alissonprado.mercadolivre.validacao;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
    private MessageSource messageSource;

    public ErroDeValidacaoHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioResponse> handle(MethodArgumentNotValidException exception){

        List<ErroDeFormularioResponse> responseList = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(fieldErrors, responseList);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<ErroDeFormularioResponse> handle(BindException exception){

        List<ErroDeFormularioResponse> responseList = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(fieldErrors, responseList);
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDeFormularioResponse handleInvalidFormatException(InvalidFormatException exception) {

        String nomeCampo = "campo";

        if (exception.getPath() != null && !exception.getPath().isEmpty()) {
            JsonMappingException.Reference path = exception.getPath().get(exception.getPath().size() - 1);
            if (path != null) {
                nomeCampo = path.getFieldName();
            }
        }

        String mensagem = "O valor informado (" + exception.getValue().toString() + ") não é do tipo requerido ou está em formato inválido.";

        return new ErroDeFormularioResponse(exception.getValue().toString(), mensagem);
    }

    private List<ErroDeFormularioResponse> buildValidationErrors(List<FieldError> fieldErrors, List<ErroDeFormularioResponse> responseList) {
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeFormularioResponse erro = new ErroDeFormularioResponse(e.getField(), mensagem);
            responseList.add(erro);
        });

        return responseList;
    }
}

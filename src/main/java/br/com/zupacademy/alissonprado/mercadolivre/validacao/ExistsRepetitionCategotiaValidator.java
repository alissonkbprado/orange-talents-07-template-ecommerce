package br.com.zupacademy.alissonprado.mercadolivre.validacao;

import br.com.zupacademy.alissonprado.mercadolivre.cadastroProduto.request.CaracteristicaRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExistsRepetitionCategotiaValidator implements ConstraintValidator<ExistsRepetitionCategoria, Object> {

    private boolean optional;

    @Override
    public void initialize(ExistsRepetitionCategoria params) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        List<CaracteristicaRequest> caracteristicaRequestList = (List<CaracteristicaRequest>) value;

        return existeCaracteristicaComMesmoNome(caracteristicaRequestList);

    }

    private boolean existeCaracteristicaComMesmoNome(List<CaracteristicaRequest> caracteristicaRequestList) {
        Set<String> nomes = new HashSet<>();

        caracteristicaRequestList.forEach( c -> {
            nomes.add(c.getNome() + c.getIdGrupoCaracteristica());
        });

        return (caracteristicaRequestList.size() == nomes.size());
    }
}

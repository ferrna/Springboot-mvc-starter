package com.expensestracker.expensestracker.service.validation;

import com.expensestracker.expensestracker.exception.InvalidExampleException;
import com.expensestracker.expensestracker.model.Example;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("updateExampleValidationService")
public class UpdateExampleValidationService extends BaseExampleValidationService {
    @Override
    public void validateExample(Example example) throws InvalidExampleException {
        validateNameLength(example);
        validatePriceNonNegative(example);
    }

}

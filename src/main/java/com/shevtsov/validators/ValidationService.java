package com.shevtsov.validators;

import com.shevtsov.exceptions.BusinessException;

public interface ValidationService {

    void validateAge(int age) throws BusinessException;
}

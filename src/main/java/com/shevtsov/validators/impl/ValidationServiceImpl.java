package com.shevtsov.validators.impl;

import com.shevtsov.exceptions.BusinessException;
import com.shevtsov.validators.ValidationService;

import javax.xml.bind.ValidationException;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validateAge(int age) throws BusinessException {
        if (age < 0 || age > 200){
            throw new ValidationException();
        }
    }
}

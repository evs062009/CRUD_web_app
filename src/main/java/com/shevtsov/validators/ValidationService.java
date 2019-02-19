package com.shevtsov.validators;

import com.shevtsov.exceptions.BusinessException;

public interface ValidationService {

    void validateAge(int age) throws BusinessException;

    void validatePhoneFormat(String phone) throws BusinessException;

    void validateEmail(String email) throws BusinessException;

    void validatePhoneUniq(String phone) throws BusinessException;
}

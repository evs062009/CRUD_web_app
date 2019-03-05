package com.shevtsov.validators;

import com.shevtsov.exceptions.BusinessException;

public interface ValidationService {

    /**
     * Validates age-parameter for client-object creating/modifying.
     * Valid age has to be in interval 0-200.
     * @param age value, that is checked.
     * @throws BusinessException exception, which is thrown, if age is not valid.
     */
    void validateAge(int age) throws BusinessException;

    /**
     * Validates phone-parameter for client-object creating/modifying.
     * Valid phone has to have 10-chars length, and first 3 chars have to be "050", "067" or "097"
     * @param phone value, that is checked.
     * @throws BusinessException exception, which is thrown, if phone is not valid.
     */
    void validatePhoneFormat(String phone) throws BusinessException;

    /**
     * Validates phone-parameter for client-object creating (but not modifying).
     * If there is the same phone number in the storage, phone is not valid.
     * @param phone value, that is checked.
     * @throws BusinessException exception, which is thrown, if phone is not valid.
     */
    void validatePhoneUniq(String phone) throws BusinessException;

    /**
     * Validates email-parameter for client-object creating/modifying.
     * Valid email has to have "@" in the middle and ".com" at the end.
     * @param email value, that is checked.
     * @throws BusinessException exception, which is thrown, if phone is not valid.
     */
    void validateEmail(String email) throws BusinessException;
}

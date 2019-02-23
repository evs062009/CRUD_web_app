package com.shevtsov.validators.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.exceptions.BusinessException;
import com.shevtsov.validators.ValidationService;

import java.util.Arrays;

public class ValidationServiceImpl implements ValidationService {
    private final ClientDao clientDao = ClientDaoImpl.getInstance();
    private static final String[] OPERATOR_CODES = {"067", "097", "050"};

    @Override
    public void validateAge(int age) throws BusinessException {
        if (age < 0 || age > 200) {
            throw new BusinessException("Invalid age!!!");
        }
    }

    @Override
    public void validatePhoneFormat(String phone) throws BusinessException {
        if (phone != null && phone.length() == 10) {
            String code = phone.substring(0, 3);
            if (Arrays.asList(OPERATOR_CODES).contains(code)) {
                return;
            }
        }
        throw new BusinessException("Invalid phone format!!!");
    }

    @Override
    public void validatePhoneUniq(String phone) throws BusinessException {
        if (clientDao.findByPhone(phone) != -1) {
            throw new BusinessException("This phone number already exist!!!");
        }
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        if (email != null) {
            if (!(email.equals("") || email.matches(".+@.+\\.com"))) {
                throw new BusinessException("Invalid email format!!!");
            }
        }
    }
}
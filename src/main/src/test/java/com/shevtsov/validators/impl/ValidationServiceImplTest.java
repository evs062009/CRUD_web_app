package com.shevtsov.validators.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.exceptions.BusinessException;
import com.shevtsov.validators.ValidationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class ValidationServiceImplTest {
    private ValidationService validationService;

    private ClientDao clientDao = mock(ClientDao.class);

    @Before
    public void setUp() {
        validationService = new ValidationServiceImpl(clientDao);
    }

    @Test
    public void validateAgeGetValidValue() throws BusinessException {
        //GIVEN
        int age = 50;
        //WHEN
        validationService.validateAge(age);
    }

    @Test(expected = BusinessException.class)
    public void validateAgeGetMoreThanMaximum() throws BusinessException {
        //GIVEN
        int ageMoreMaximum = 201;
        //WHEN
        validationService.validateAge(ageMoreMaximum);
    }

    @Test(expected = BusinessException.class)
    public void validateAgeGetLessThenMinimum() throws BusinessException {
        //GIVEN
        int ageLessMinimum = -1;
        //WHEN
        validationService.validateAge(ageLessMinimum);
    }

    @Test
    public void validatePhoneFormatGetValidValue() throws BusinessException {
        //GIVEN
        String phone = "0501111111";
        //WHEN
        validationService.validatePhoneFormat(phone);
    }

    @Test(expected = BusinessException.class)
    public void validatePhoneFormatGetNULL() throws BusinessException {
        //WHEN
        validationService.validatePhoneFormat(null);
    }

    @Test(expected = BusinessException.class)
    public void validatePhoneFormatGetLessThenTenChars() throws BusinessException {
        //GIVEN
        String phoneLessYenChars = "123456789";
        //WHEN
        validationService.validatePhoneFormat(phoneLessYenChars);
    }

    @Test(expected = BusinessException.class)
    public void validatePhoneFormatGetWrongCode() throws BusinessException {
        //GIVEN
        String wrongCode = "111";
        //WHEN
        validationService.validatePhoneFormat(wrongCode);
    }

    @Test
    public void validatePhoneUniqUniqueNumber() throws BusinessException {
        //GIVEN
        String uniqueNumber = "someUniqueNumber";
        Mockito.when(clientDao.findByPhone(uniqueNumber)).thenReturn(-1L);
        //WHEN
        validationService.validatePhoneUniq(uniqueNumber);
    }

    @Test (expected = BusinessException.class)
    public void validatePhoneUniqNotUniqueNumber() throws BusinessException {
        //GIVEN
        String notUniqueNumber = "someNonUniqueNumber";
        Mockito.when(clientDao.findByPhone(notUniqueNumber)).thenReturn(0L);
        //WHEN
        validationService.validatePhoneUniq(notUniqueNumber);
    }

    @Test
    public void validateEmailGetValidValue() throws BusinessException {
        //GIVEN
        String email = "111@test.com";
        //WHEN
        validationService.validateEmail(email);
    }

    @Test(expected = BusinessException.class)
    public void validateEmailGetWrongFormat() throws BusinessException {
        //GIVEN
        String wrongEmail = "123";
        //WHEN
        validationService.validateEmail(wrongEmail);
    }

    @After
    public void tearDown() {
        validationService = null;
    }
}
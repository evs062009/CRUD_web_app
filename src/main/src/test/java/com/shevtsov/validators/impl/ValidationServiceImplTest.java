package com.shevtsov.validators.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.exceptions.BusinessException;
import com.shevtsov.validators.ValidationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Matchers.anyString;

@RunWith(JUnit4.class)
public class ValidationServiceImplTest {
    private ValidationService validationService;

    @Mock
    private ClientDao clientDao;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        validationService = new ValidationServiceImpl(clientDao);
    }

    @Test
    public void validateAgeWithValidParameters() throws BusinessException {
        //GIVEN
        int age = 50;
        //WHEN
        validationService.validateAge(age);
    }

    @Test(expected = BusinessException.class)
    public void validateAgeWithMoreThanValid() throws BusinessException {
        //GIVEN
        int ageMoreMaximum = 201;
        //WHEN
        validationService.validateAge(ageMoreMaximum);
    }

    @Test(expected = BusinessException.class)
    public void validateAgeWithLessThenValid() throws BusinessException {
        //GIVEN
        int ageLessMinimum = -1;
        //WHEN
        validationService.validateAge(ageLessMinimum);
    }

    @Test
    public void validatePhoneFormatWithValidParameters() throws BusinessException {
        //GIVEN
        String phone = "0501111111";
        //WHEN
        validationService.validatePhoneFormat(phone);
    }

    @Test(expected = BusinessException.class)
    public void validatePhoneFormatWithNULL() throws BusinessException {
        //WHEN
        validationService.validatePhoneFormat(null);
    }

    @Test(expected = BusinessException.class)
    public void validatePhoneFormatWithLessCharsThenValid() throws BusinessException {
        //GIVEN
        String phoneLessYenChars = "123456789";
        //WHEN
        validationService.validatePhoneFormat(phoneLessYenChars);
    }

    @Test(expected = BusinessException.class)
    public void validatePhoneFormatWithInvalidCode() throws BusinessException {
        //GIVEN
        String wrongCode = "111";
        //WHEN
        validationService.validatePhoneFormat(wrongCode);
    }

    @Test
    public void validatePhoneUniqWithUniqueNumber() throws BusinessException {
        //GIVEN
        String uniqueNumber = anyString();
        Mockito.when(clientDao.findByPhone(uniqueNumber)).thenReturn(-1L);
        //WHEN
        validationService.validatePhoneUniq(uniqueNumber);
        //THEN
        Mockito.verify(clientDao, Mockito.times(1)).findByPhone(uniqueNumber);
        Mockito.verifyNoMoreInteractions(clientDao);
    }

    @Test(expected = BusinessException.class)
    public void validatePhoneUniqWithNonUniqueNumber() throws BusinessException {
        //GIVEN
        String notUniqueNumber = anyString();
        Mockito.when(clientDao.findByPhone(notUniqueNumber)).thenReturn(0L);
        //WHEN
        validationService.validatePhoneUniq(notUniqueNumber);
    }

    @Test
    public void validateEmailWithValidParameters() throws BusinessException {
        //GIVEN
        String email = "111@test.com";
        //WHEN
        validationService.validateEmail(email);
    }

    @Test(expected = BusinessException.class)
    public void validateEmailWithInvalidFormat() throws BusinessException {
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
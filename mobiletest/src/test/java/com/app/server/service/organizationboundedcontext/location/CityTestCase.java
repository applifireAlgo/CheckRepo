package com.app.server.service.organizationboundedcontext.location;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.City;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.athena.framework.server.test.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.Before;
import org.junit.After;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo(1, "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
    }

    private City createCity(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Country country = new Country();
        country.setCountryCode1("zyz");
        country.setIsoNumeric(6);
        country.setCurrencySymbol("48rGPungvwziQhEdD0iNAeY2IOgg4fF3");
        country.setCurrencyCode("c47");
        country.setCountryName("wuuE9ECtuL6cltojiCFlybRXUzn2bR2J9BFesG2s2knJ5LSHiV");
        country.setCountryFlag("jcdBfhtjcXOJb7kWZw4QYGsjj3CIbnsbTZzXbtt3854iaBHfgj");
        country.setCapitalLatitude(2);
        country.setCountryCode2("2py");
        country.setCurrencyName("nhPPar18WE7MLA0kzXthn8l9Wyb5b6OmDfN1SK5aWe6rQoToLf");
        country.setCapital("zB38bYg6iSmu83FUkexDTEHD4xBeNCPC");
        country.setCapitalLongitude(6);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        State state = new State();
        state.setStateCapital("7AhjFgfjZwBpHH7xlX8OQu6hxmgd9ISGdP2Ex3w1CKpXa2TYCT");
        state.setStateName("ce7LiGXxOUaibDE0fjsL0avJm4lGwz3xh9vV3TqNsj7g8IPQzk");
        state.setStateCodeChar2("Ki3YUVTetXqzKlLfDUvqGz49tGmz20af");
        state.setStateFlag("OiH01ZB5ochm2KGpInwRLuVlThfKQgSSuhBb40W9pX6y2Ly2Vu");
        state.setStateCode(1);
        state.setStateCapitalLatitude(8);
        state.setStateCapitalLongitude(7);
        state.setStateCodeChar3("ZjNeR5ebnxmLqA1sgfgDaQLGwwSB3NPC");
        state.setStateDescription("3IoWg6oEfksvz2AB2wP7AuRRGupd68RKq8H89ZIqDUVHe1UOXK");
        state.setStateCapital("K4pViWbMdUEGPyeTRElgQfe0h3J1UDRK9Ltq9zV0eC1sSgucma");
        state.setStateName("NKbQ4QuBQlXg1cAkTGtx2R4q366XItf63qtLZwGI70OUKKUD59");
        state.setStateCodeChar2("NmGznh1JUfAki5N1XOQKFkraWZ8Rp6e4");
        state.setStateFlag("EIVSoHCkoeN6aj1pbVgYlfysWf1nsjnrNnq3xAnIvoybbjDBqg");
        state.setStateCode(2);
        state.setStateCapitalLatitude(5);
        state.setStateCapitalLongitude(4);
        state.setStateCodeChar3("Zjegi3I8BC2PgvJlT8RxInAF5N44ls0C");
        state.setStateDescription("n1MUNYTmNQpsJjCH19sM5RUtsbVysAOnBYT76dJpCufJ7kDNkZ");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
        }
        map.put("StatePrimaryKey", state._getPrimarykey());
        City city = new City();
        city.setCityCode(3);
        city.setCityName("ceLuQITBA1IiOqdIQcvNS4AWHVaJPiIGQN9jEh46hWCqWGhUpj");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setStateId((java.lang.String) StateTest._getPrimarykey());
        city.setCityFlag("xArtSGYRWEQMfiZftJJu7ykcALtAyr2aoSxDlFbtNGCeEqoCE6");
        city.setCityCodeChar2("D51hka2JFFqxOKAiFnqg06WsLTeyCYk1");
        city.setCityLatitude(3);
        city.setCityDescription("knCSkVLfgZZtoYd5XfahcWVmvEVenLTDpGY927PSSheszoBZEI");
        city.setCityLongitude(4);
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityCode(3);
            city.setCityName("K5bNAasYVXI7zEhWueRQ4SirC8PIsSzj1UzG7dl87D2R7me6WY");
            city.setVersionId(1);
            city.setCityFlag("55kHB7SRQsRZPfYHCj9X1e7T38d0FT5lnKQnpKcZbFGnoUJa5K");
            city.setCityCodeChar2("QD7arEZ7iyKsGZV7rpmkiKXmIS43xzQY");
            city.setCityLatitude(8);
            city.setCityDescription("Xix9kkZvNWzyMQNnfNfZNT4iK1AHGC9msjbtqMn7YM7l2I0CTB");
            city.setCityLongitude(7);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "BjwKG9yNLYLM4zPnqiulbcSRnPha9yv6MvKtOLLlk5oZTiX4FEyxQIMheVwseWu95uGIyYQEV3Sym0It6gjCH3e5ci6ur4gm3rOBdNdVWOoxMwnxdNtwUwauxXSU7Os308SW4rmcbh3mjbzBaS9xVdqHbtiyCnmPowfnyjnyLr8q51QGR188RJ6GiAvDBRMuGaz3kQsuJPyrxOiqS73Z6UQwcxzFkISu1gfadbWxyryfzKjYCnHOuUc3q9dWd8w0J"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "XZYA1wWxkfYZJRLqhhhZuJbm4NqfGqUby"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "s0C5Q7AfUQ0Ou2ACobHQ0AFRel6OSLEBgDc6rRvwqxJkQu43ewx03Kaulp1AaXodcr0OOJGGSdYg1apGhLoCJGr8b3bjajfnf3LjH8CDVRhe792gUv1m4mjMkb8zgS89w"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "1pmrvwKYlJQf7CmnLnhJpwmuNyCzkoGcMCFMdJS3jjKA8pCEWYASooGsnJvlEXdyanNUY3MXlflMEZTwYIFKsVu1pgCXNii9YhMNhKpgnduogZSlUAUjiro1wcZLerKYP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 20));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = city.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                }
            } catch (SpartanIncorrectDataException e) {
                e.printStackTrace();
            } catch (SpartanConstraintViolationException e) {
                e.printStackTrace();
            } catch (SpartanPersistenceException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}

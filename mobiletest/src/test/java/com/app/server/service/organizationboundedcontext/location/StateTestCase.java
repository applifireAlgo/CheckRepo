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
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.State;
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
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Country country = new Country();
        country.setCountryCode1("Bhv");
        country.setIsoNumeric(8);
        country.setCurrencySymbol("eAGGyE3rlfovKEZEAgUS6cktlFboR2Ul");
        country.setCurrencyCode("BLe");
        country.setCountryName("yNJPfLn5wCFScl3IVnmEOozjgwJD8OV0XyDiCeQzmJSEaSYXB2");
        country.setCountryFlag("M2y73fl1IUOHDT1qdK0JDbvNkxiC6zj1k8w59dhwbN9WhmuUXz");
        country.setCapitalLatitude(6);
        country.setCountryCode2("wHH");
        country.setCurrencyName("qhf6h2FH7MCpcBvxRn4pgUN64OOsz3vAuBaFVnnQjKlZUlnO9p");
        country.setCapital("NG0XyBKzLRO7zFsSf656l5alMlBcGaIq");
        country.setCapitalLongitude(6);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        State state = new State();
        state.setStateCapital("hVV75tEhFSIzByxmKqeuTjtxEb80KmsVgXoirWe0Mu7C6LCNFz");
        state.setStateName("3FZnRAxKGWq6m26ebQTXudNa5WC6Yr9PuPMl70QQNAnG2gs8kn");
        state.setStateCodeChar2("TlCHTM4C3a5CFhF6y2RMvTZ0z6iu1l1C");
        state.setStateFlag("RcYC6jB5f2lZ0DJbPF0H90wqgZZBeZodnXcpGRG7QAYLrscR7b");
        state.setStateCode(1);
        state.setStateCapitalLatitude(2);
        state.setStateCapitalLongitude(5);
        state.setStateCodeChar3("3jCJgJQI4Dol1yn4UqOGUd3vMnwpsHq1");
        state.setStateDescription("m7XMhgdfhsqNkIfz2YdePgCYv2eeaVE1YgGEnErL9ljxT1Plpo");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCapital("bFz2sAc6ICMM7n3akcDbNSFvRt9KnkwHkOLOtIJHVLehvcWaJE");
            state.setStateName("3yDBrpYO1qamEy9r6i1UrbnL6vJhA7zvvbUwxnHrzjHRlovvWI");
            state.setStateCodeChar2("zUQ9tsQy107NOr91OPL8NXhr3bYn5wS4");
            state.setStateFlag("gQaBcorW9Lme8REZRGCdE8EohqkEbeCZ8nP6kyK5C4yvxDGjeR");
            state.setStateCode(1);
            state.setStateCapitalLatitude(11);
            state.setStateCapitalLongitude(2);
            state.setStateCodeChar3("vMegwx2IuzagincQi9x29MOhoF8yXd1E");
            state.setStateDescription("FJASx65f45Ndak9ZnC69PokynI2oPYotLHDVnPxdRFQw8XyYHf");
            state.setVersionId(1);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "Nn4zwJUV2OOJ20xPLvmWFUilUmQPnXT3aDYdCqPJS2bWhGeDzW4nBeFSKfNYmzpVVBob63dGlJABRB5MAt1ATLggijdGD4slOESxAaZ847Fgl8DYD7s3WIItJ7gA63CLTRxLN5JgjNuOdHnSzHp5coDoMtoAOQ36aTiletHZDKXortbdEdYRWV68Zz5t4241rH2v7wS93JMmlUAbUUpewGWKjqKyytICYy4PORy7EHAkSS0n6ou3Mk9aPwz9O3TMr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "Nulfs2zcbTxeRLdxMqzl5WmdyI0OOrIo1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "zXgYPmbiTZxJhjqgVIrUMwODzH04wyXDR"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "vkfH5vCuogcao4jpn4dyIHCGF5zyc4AaVLqRitOU9Ju59bEMsqwjIvkpU1HDPhxSh0KDdbayD8VmVyaIFF9svSEZY8I4wJKcwpNFLN5S0w3SYNkojWGOvvImNts3bak28uRRbcBgudU46BRkCan3blocByBJnY3wRokfhXJkYrsWMjOgZIukAs0GWwfSZ4IqAlOI0Tb8rrjrLLsQqFNs2HKhYdmOVPLserpwy6kf5xtzEtYiPwAxPz5XOpVx91Zxb"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "49uctm7YbH0jdHNFEtWySolBpoPADPIfKRZH5Ejhm7Ae9C3NiypedkjYiGKR2ojaA4svQp4o2cDE13kPCVHiBnnAfxDqDnXPjIUkPwBx9oKoNhOxq3YwIUGsPsVsBn5L3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "A5JbI3f5GjGFoFYbq6s3nySLhy6Atja0NWtLgoucClgnZxEbFyPDbKLctWCHKo2OJiWHn7cMgNxL2ta4MUMD2hlQFow0PZcRXw7cqkXZaWQB0rYz2JJxndurh05C1at05"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 14));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = state.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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

package mobproj.app.server.service.organizationboundedcontext.location;
import mobproj.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import mobproj.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import mobproj.app.server.repository.organizationboundedcontext.location.StateRepository;
import mobproj.app.shared.organizationboundedcontext.location.State;
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
import mobproj.app.shared.organizationboundedcontext.location.Country;
import mobproj.app.server.repository.organizationboundedcontext.location.CountryRepository;
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
        country.setCurrencyCode("pP2");
        country.setCapitalLatitude(6);
        country.setCurrencyName("7LW6UYW4kqrNdTDXqjjtkzAj6mmk8oz6QUYaVJ559fz85mpb45");
        country.setCountryName("8IFULJitntMPaZqwgK4itLSxLvkG3e5kUHB9b0EJAg9R1s3f1j");
        country.setCountryCode2("Ak0");
        country.setCountryCode1("uQM");
        country.setCountryFlag("L1atCmHorLqczwq2OTYNtN1lrZjbqPYnfjFsgI1RQAEoxLnOQf");
        country.setIsoNumeric(1);
        country.setCapitalLongitude(3);
        country.setCurrencySymbol("Wbvj3KrK6HQS6hnHQCeCxmo3YBAv8Fui");
        country.setCapital("3euha9NjU4kbLe2wnbwShplhTDy2faGs");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateDescription("Ir42qMzyPjGHkYVoXo9CSa83zcgvI3Q3FXpFTpr0nJd66HD8HG");
        state.setStateCode(2);
        state.setStateCapitalLongitude(9);
        state.setStateFlag("CqvI8E1fnsEFovW0AmrjvtPbwKaxNEluSrw1bwQPsdxYpLYf14");
        state.setStateCapitalLatitude(3);
        state.setStateCodeChar3("Jgbdijkn3Z1qpaqdfvvmAtOGnGSHwShp");
        state.setStateCodeChar2("I3tZ0ibdmE5F8YT4VBmjTBGZGmdkB0CP");
        state.setStateCapital("Gsx1GVyqk2ZbBWu4Oha6ScF8MCfHdARON7RM66yMUDuD4nCvuF");
        state.setStateName("lASjCYxM9NuQU2ICtqHqWAE1F3s1iXoKXaxS2ZnaJoDUY6R59I");
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
            state.setStateDescription("YA6uf0A050QRLqvTYkl5EYkfN8EaUftn928M5rCVI15rv3wnjy");
            state.setStateCode(2);
            state.setStateCapitalLongitude(7);
            state.setStateFlag("sSmRGuscPqUbpNDDjcuolyaN1Leb4GZK7QJMYNNmKOsPHJCGIt");
            state.setStateCapitalLatitude(6);
            state.setStateCodeChar3("3QzULRZkDr2qZ5ZKl5suBqKIEBYu81UJ");
            state.setStateCodeChar2("H1yMqoPNcxkyl6wA3yNkXSmHAZOXw5mE");
            state.setStateCapital("SjapryAgBlXS0gJcJFLTecrJl5KBLliOwgOqY5EQa4l52L6CvS");
            state.setStateName("crJ6Ku4uLd0sQje8Sl2Qtp1gPwVOT6MMYVpNzyD8tSPCF4YyTJ");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "MrKVOBB6CclWujvC3AYFgErKkcEKcV4O9s8Nf8lYCnhPT1rIjj0R44cwg3mn9DxybhqNK7FDfMQj7fWbNTB57Gano1XiscZJMvl2vQ37IjJEE3iF6I5X5rrLXp23BuYhTVorc8npKvbXOaKNyPSFRfJVDlhfyzalHuoj3BduLrtB8CgQS1n4AaUbuWeMy8BVToi51npoemPW6vrcPDyduahhJzohZxha7sFpYpReLb5XWnfjxd3szEfYFvvT5DJRl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "5VIgRPO46Y8RAk2DijiL46laBjvRkVlWJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "46ufr0NFlft5RxhXDnB2tMOz5qMSGdDDp"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "d9Yc1eJd9c3OWTAKJirr7qEO8CAd7aUcb9s429AO2R6LVKcR1Mx6k1TfStUGYTOOJsZ4bvCd9dyaZNQRPDiZBhcJCi4NF3m954xO8FbVTijI0poRk4EUi73CiNMMkBBh4k4H1yARECduV6HiVOUroI1SgPl6khZhL3T75ASjSo76PKAFq9bzQsA0mcRqTmvzhxpgprc6Al9w7z9azVu6c98Cd2oeaDu1HCqKPvMquR2m3l7K7GBZTghaxU7gn46S3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "BQeFHjuk02ya0tjsHMksMghqZsrox45OIbH4u2x2dpVW328YdNF9ZUtUxrM1dPh6p5rGRTsCQggdK0Rnkvcc8eJ1WJdZRcVjSde5axOWJPRy8yVvC5nCWQvaNu1AveLRd"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "gnK1lR1F8bmI5R6UmclNxXdE87DURRpw4Tv2BI1Vd5Tmbmbm8SbZOqY6SfnzB4ljxixn2X8Vs38aFqO4lbkfDqXlO4nGTerttxcMNtmRoi85Wjdi7w1grxQLsk3BMnxV2"));
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

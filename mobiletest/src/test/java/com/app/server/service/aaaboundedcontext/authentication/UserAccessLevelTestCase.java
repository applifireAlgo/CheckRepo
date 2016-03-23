package com.app.server.service.aaaboundedcontext.authentication;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
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
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("AlDdNiUswAIM0BG8fpYzE9PuU5fCBbn8bQP8hKLArWSkIve5sg");
        useraccesslevel.setLevelDescription("zMbB4KfTdR0Tnu9m1kAox1se0kC2ZtVk7dzfBCaHiDsXxXpya9");
        useraccesslevel.setLevelHelp("S8bjQLVDgbHWCWojFiW3xkjpHGdJN1Fr7gpbxFneXMrAk2d2td");
        useraccesslevel.setLevelName("ahqmbvEAkIUj83WHjtCmWe31zNJAXD2DQEaXOVHhw7z2Imlhln");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelIcon("bjuikCBlWYegzZJOAQZyS0Zg6Bkt7jNXucaR767kTLLUatLb0k");
            useraccesslevel.setLevelDescription("VGcxsK2j7jt40D4q8yVqAFFo1XbvEP9RCyvltly3NQiRwJE6kk");
            useraccesslevel.setLevelHelp("YkS0VYFahVQOwUBjgvgRtT7wkfqrmunhBwz8dqv7wtcnwSdOEt");
            useraccesslevel.setLevelName("Tlodouz3gcnPOVy58D6GUBonyFkrgWPrljYywrAlgkS5qZFaDW");
            useraccesslevel.setUserAccessLevel(79824);
            useraccesslevel.setVersionId(1);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 2, "userAccessLevel", ""));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "userAccessLevel", 103494));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "levelName", "zcDTFLCaRQzklRXU6vuWaDTWu56hHfitxGagf4VRPHPRVOM7QMnR0bDMuACtO0QhXVqhGOd1f204LCiK7Lnf9fIDzEVgBJrdfQQlGJW2Qp76lhBo8FniBVe1ffIOZJLDQ0hXaaF7TEtUNpZ6gZaexBaZrGfC0pjTHRbA8BCuH1bfhyPsuFoYNyWcIu6xWL77XtWBi63RQzhCzg4Swc0kJ8E0XdQ0oH75tF5cfVhIddSsO85IpjFqaNbWbm3cM9zNo"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelDescription", "SDY0Is3vVVZSWVYQ8kJgnJTIG7OYIaTT79D3v4CXx0MdkNvcfJmTPutH9UDeNwlm2SU1dprmNIb9XkYK7SAdWjsiLiUqkpn0ZQrwuTc6LiBEFVBe3VKAOE6jBFeM6qeGlGdCYB9eBggHoN37ZbUOaJhKiz2VbTcu9PzqlQG4gjfeSgTyEIFJ8nOHSW3VnnSRZ94Wu2L4MCFJlU5OvAVMu4iH2oVmBw0dZ1geELS6OdLy9KUue6vLv2pIK1bgdYz2e"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "levelIcon", "kcSOVtZVpaMT6ASLtQrXc9Iam9iJiYXx0r6R0mTVpZO68xHq44edrFNcfodB62KrH4C5Z7VZOHvWATpjUx1dil06EFihqLGSOQS7ay64TABrXos7ANjpKkWOG0Pbb34wCLMGatadwsUZtsytuUcfDaRXlpL2FeAKs1SMd6d17GvU8JtJpkNzSSrKwoomQAcj3M6PCmupn9WYoM0SInMlLDVeQWsHMtPpKWAQMLQfLfddDvpVufhkMRSfqDE4zcyLF"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
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

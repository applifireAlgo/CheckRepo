package mobproj.app.server.service.aaaboundedcontext.authentication;
import mobproj.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import mobproj.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import mobproj.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import mobproj.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
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
        useraccesslevel.setLevelName("3l5FkE4CZIltrojHSNDaHiVmWzyCdxeAdqMAI4cJzgzLEWJZTm");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("YbT8a7YXZ6WZjIUMqV8T8xHqnaemT9aXsy44we1B6uqOvGUXtI");
        useraccesslevel.setLevelHelp("ftwUgYAR4285NggycflaC7fZIz2uPTcWaKCvokwktbETwvJbBB");
        useraccesslevel.setLevelDescription("twUtHdHxsFzCuCNoegj655VnXIKszGXpIlRsgF0B5bBQQqrhyx");
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
            useraccesslevel.setLevelName("epbV71Xv4JXSqWvyZvthBitdOD3nVsRtH6MemvHu9W4PKXy5KM");
            useraccesslevel.setUserAccessLevel(16839);
            useraccesslevel.setLevelIcon("6a4HkjBODttVLfAhfJ0GhxLTdf3ev32eTKflj7lIlK2sapoxm1");
            useraccesslevel.setLevelHelp("F3YVEGpNFtMIuZwXV1uWrFQTho4tDOsAcDzO2FymeZd6oN2TQb");
            useraccesslevel.setLevelDescription("VUaUF5cFDIfIGBJZj5VU1KmUwidDBZ8AyCg2LTo8W6p57CsetA");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "userAccessLevel", 175528));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "levelName", "iwQy9wUlrNCxaQl7YVf3aftl9m2qUDL5Ujd6mzFF40uv42RllEAppz66N3ROW5kR6aCcjlPoeimjauweXViG5sNQUPrIsfESW8voCnin3ZQyKPIOMSuxU1YBoypgrXZpw3j6IPGaqnbZlAvgueB1kVnFXcmdhm56YawqiDQDo4h5qUtdoXv0MfbFILZCHc31BmnuugAz3giqgo1z2S3aEIiJB8w9EexcolX6xiIxNn0SbJlckfRogvyWwHknY6zTg"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelDescription", "BssQIMP44TiEe5s7uh5Fl0WXjo1wDZDzS5LcOKTW72PGEDQRrFlYhR9uQzphJvbZC0raV4E8HFKN8xj8FLWsiLUsKU1wz6mUQ6v6rLaiaptbqOzOgA92hPBiNTEyBlxVzRSy3rFfaHSlSyyEZiHwNRfQUH3X64QC1ViLGkIHYW4fvfxRC2cGgV1m1DkFJ4rd517WF5S2gbW9dz16BDjtVIjSng89Ve55cZGiXnXxbgQvsw0hAQtcaSrGFTWGPG6MO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "levelIcon", "g2TgtXIIvJuvtnIoJvvu0zMQxGdXYz5RzikGRorwVZuCpMX0RtsZzOZziPqx64pr8DFWZP8uGdWHpv9vzRTKzb2k0hrVbv5oOuJT5xfJ6FG2zOCxcC8cX0lWzbtphc5mNn3ucGSFRLjrGrNInHQvcpk1glGnCBncDZe5LSN3p2SLFnXEcwT4LOu3eS1KeDP9HLoJ28mjRbK8Wu4YJsAznc0DgzlIqK7WENwChY3VgCm4fqGIJqAgNpU9K5AR5pfQa"));
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

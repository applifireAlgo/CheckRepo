package com.app.server.service.defaultcontext.defaultdomain;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.defaultcontext.defaultdomain.PersonalInfoRepository;
import com.app.shared.defaultcontext.defaultdomain.PersonalInfo;
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
public class PersonalInfoTestCase extends EntityTestCriteria {

    @Autowired
    private PersonalInfoRepository<PersonalInfo> personalinfoRepository;

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

    private PersonalInfo createPersonalInfo(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        PersonalInfo personalinfo = new PersonalInfo();
        personalinfo.setfName("ubbIeTDVN9CKjpSPMH6sysoV08oi47lXNgeJe4b0ASE4QUZo3w");
        personalinfo.setAge(2147483647);
        personalinfo.setDateOfBirth(new java.sql.Timestamp(1458652217583l));
        personalinfo.setEntityValidator(entityValidator);
        return personalinfo;
    }

    @Test
    public void test1Save() {
        try {
            PersonalInfo personalinfo = createPersonalInfo(true);
            personalinfo.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            personalinfo.isValid();
            personalinfoRepository.save(personalinfo);
            map.put("PersonalInfoPrimaryKey", personalinfo._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("PersonalInfoPrimaryKey"));
            PersonalInfo personalinfo = personalinfoRepository.findById((java.lang.Integer) map.get("PersonalInfoPrimaryKey"));
            personalinfo.setfName("j2eq10BWy1j0PRtFT5TuiEI1kFtUkO8ogFpqUgTGWiHj5P1CoS");
            personalinfo.setAge(2147483647);
            personalinfo.setVersionId(1);
            personalinfo.setDateOfBirth(new java.sql.Timestamp(1458652217595l));
            personalinfo.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            personalinfoRepository.update(personalinfo);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("PersonalInfoPrimaryKey"));
            personalinfoRepository.findById((java.lang.Integer) map.get("PersonalInfoPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("PersonalInfoPrimaryKey"));
            personalinfoRepository.delete((java.lang.Integer) map.get("PersonalInfoPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validatePersonalInfo(EntityTestCriteria contraints, PersonalInfo personalinfo) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            personalinfo.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            personalinfo.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            personalinfo.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            personalinfoRepository.save(personalinfo);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "fName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "fName", "BAS2cpg57fp0G6OCBu4cr69oCLpYRq0qsPxdbfqBQRhRxF0xJvH7uZQp0VIZe7Z5DCVF7t16MME5Q1R58FyKRG3ONdr1Wzx4x5kvbu0QsTbhGvqDOojEI1E55FAH0XnZgapn2lG9cB6f1SUdxMS3xlUJ2xEip6gKaN99YglmR9wVWc4vzZvQu7UwJ3F0JntaEhEznSCjRqrfjSVolyJRBU5R5TBIzPaEinjVewHFcwtcxd8vsmkWeK2rfGeXXmwga"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "age", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "dateOfBirth", null));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                PersonalInfo personalinfo = createPersonalInfo(false);
                java.lang.reflect.Field field = personalinfo.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(personalinfo, null);
                        validatePersonalInfo(contraints, personalinfo);
                        failureCount++;
                        break;
                    case 2:
                        personalinfo.setfName(contraints.getNegativeValue().toString());
                        validatePersonalInfo(contraints, personalinfo);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(personalinfo, null);
                        validatePersonalInfo(contraints, personalinfo);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(personalinfo, null);
                        validatePersonalInfo(contraints, personalinfo);
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

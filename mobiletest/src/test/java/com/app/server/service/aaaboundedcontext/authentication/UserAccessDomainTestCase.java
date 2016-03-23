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
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("NKbD5VGWyHubxLPIY0BzpA76826N9mVnrbBVdEDUtIlzwfubLI");
        useraccessdomain.setDomainHelp("grInaCs8HU7Ncz8W8ac5r3oFMsSprjo2bXabXVgBlZ7vLvj6YF");
        useraccessdomain.setDomainDescription("QBq5okqpbwdD77i28LKJE0GHTUBO5x6THyu6DQyED6Wnjzosam");
        useraccessdomain.setDomainName("Wevp1wUwQmsHu93woWFcda4XwznfWStQlCIw7GKo2N7nzSuVoT");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setUserAccessDomain(75603);
            useraccessdomain.setDomainIcon("jGBUiynG6wodHBtLiykwSiPjFYUUZbeDJWTDTu1GbihkFW3tI4");
            useraccessdomain.setDomainHelp("V9i8ELBoOuYQ9G4z608vXEgXaocPVyAs985DtuAVHon2xAlA0C");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainDescription("XTouGI4Aa9NSHJnT4U8mhyTLOvYhelLsrD6KGz2LdihbwszbRL");
            useraccessdomain.setDomainName("ObJECjfnx2oOmDD3RU1jOIWm0DEF070E5ZCi33SyQ93q3gWf9N");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 2, "userAccessDomain", ""));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "userAccessDomain", 121149));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "domainName", "CxCsHuoyQ8xse8q216iWkubBNsymmg0DWl7m8jhfoEcK7FVtHwh9DsCMvRNWpR5B3EgbRbMubsIhTC89vXbJJGyFUdCY7UKpKCUReQBGcU0s0ZtGk48ZfAVAsCr5QHVdm2SbFVyskncawSsnePwEIjPg4BuPC1DlSZyjUyy2uqvGZ4ak0CzVUa3C1fJD3e06hDtGa33tZhC5Hqd8nNa3eJfkoaeJrdleDArCHvPa5GFZjeAVkwYHeaGtOP1WrU8w7"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainDescription", "5t2xPJhlQDeFk9KMXS03hekG5snnP71KJyPNJ7KsEt99VeZrvAxvfb1qfrNF9oN30OSaVj5OuUbSFyG8RVdjjhOVevJsrHMJn18cmH0XdGpPXdtY7OmIPes0H52UFha4x5GWQoP0nLTErJyeBjNWhBQe97LhRN4xeoT0JiOJcaQAPgZrxxMbQFSkbwD3K9zhIRuL6xcXwbOLU5o1zRkJF8ksLEBIqsGLCni9msOHDk8zFAP73RVgogGbz0e4G3uo7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "domainIcon", "53CzdljUDOEmj6OIK8HgFvcF5u7QrpyPSEsoMO5wKFNgKfT31leE5610RcbLcRevzEKyWN9ynw35EXW8MIywPEiBJGGEiCrfdnUriL4Xjr87LcPMWqjc51dH1QLlFL5qa6hzaN5LZylo6bd2EDBOo2Z96MEjYFQEfntNrBfZShYntP7O1EkD2zHn2RPNLOnm2mYb1MooJDZ1FDcn6AGMDbd4PZ3YZRrMWNTn46kq7aNxEwMg7jt4ntaNs6IaCKM7d"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
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

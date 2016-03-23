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
import mobproj.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import mobproj.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
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
        useraccessdomain.setDomainHelp("DZNw384pTY5eioixvwMNzH4fkCmno44YhJmyz3Qrh0kHzhtVPd");
        useraccessdomain.setDomainDescription("H4gzEBV3SjxmG6QASUscOt7CSM8C1nUWmUQUO3ecMSJnwrwTU3");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("ohIcdfNAyK13WtgEF1OEnfcEYJvFT2urgFa6htTpqZcR1E2288");
        useraccessdomain.setDomainIcon("g2fWyv6ddRp77M1PJLcfH06DYFzdNStYEQ4zHufJhrc3uNuOnY");
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
            useraccessdomain.setDomainHelp("0oZHTD445wRTmV1guQcalayBDVwxZBs4bagvOU4LY3zUdUN6ld");
            useraccessdomain.setDomainDescription("bBqeVa2V0OpfSd0NErD5ZAtvxrRRKnw8GAy43uQnQGXh9bdO6f");
            useraccessdomain.setUserAccessDomain(53988);
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainName("NLt4GL7LrJShfrKsPKSp5DZZXjnW0qPonYvPp3fzq9OB4UxftO");
            useraccessdomain.setDomainIcon("hp1IOfq5qFeHfwnnOZgySPIiuy0JmHQFuSzsqeKCR1t5NdEKKj");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "userAccessDomain", 136198));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "domainName", "cCHE0CZ6zIU52pZ8LweDI2ei9aIujqrfb2lmJx2hf5r47WAZjYmCDrFuklb4OkukHi9FFqAlZF8oN90ArvRWg4gSdzLu0p8SnBinP4MPX2gcen5eCVWiqBXwSSyewFBVnVJOEMmO1bcyUsVOqYc2F7J8j7PUsx00xWKc0bBIQ61e2y0nNGqzblUlvaSzj6t8XwsqM1DP8eW4FESuadfUPO6G2xVgcDdREpEdPUSllPO0B8APHt1bdafdBwQ6CJuDP"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainDescription", "JhaRt4jvhnyX8pJNeDelNDM27PRuV79X4kL95qk7y2iTb5wUY0yx45W1s916wn7uYFsWHMaR6SAKTHSBfjG6Cjy2tAN4vWKaFRALl4gNBXABTHyXNVGhUDWliToPX8Wdrfx7AeM0ti3thgmP6QE9DfOpl3sgPuhmDu5iFSUiGJxnCIb7TllajTTGInPiuSUy5FuWt1jKACjTYghl2VExqi991aEPZyae0orKkmpMzbYnc1yq8LeB9okEgQKczETwu"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "domainIcon", "b0djZcAEMAapNV7Y4SVzqMAcEOc2uKLKIoIZCl6uNCWhJcC7oTV0yYBsZCdEIsjeksyqm7u9eF5MSj5WEH5rzXQqvuNFYAOpUZ0sT8UmsvaX4gnm42cL5pdQo7378tlVO6XMHCX0hZj8cbJ4tRn2utLsTxfi3wSVKEKE1d10KcAfyyBjGTLtv1IAMbhyikhtB7OyHKP9gJsMTNfAnG2a3FFtZeU9nj2k6AWsnU3h9galBdPpdJeTSbnyMJo6Utdt3"));
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

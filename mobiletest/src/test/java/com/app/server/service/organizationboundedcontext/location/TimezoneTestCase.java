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
import com.app.server.repository.organizationboundedcontext.location.TimezoneRepository;
import com.app.shared.organizationboundedcontext.location.Timezone;
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
public class TimezoneTestCase extends EntityTestCriteria {

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

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

    private Timezone createTimezone(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("rgveMHuARZhT7K91mOGZgYMtxoxD6VFh49kyP49qBcEYQn3Qhr");
        timezone.setUtcdifference(7);
        timezone.setCities("uZPak5CTwdIgIs5cyuCOeTi7x7LTVqhHwvVEAoCgfVEAeeb32M");
        timezone.setGmtLabel("Ouwj0ua5Rw3hKo6Kg6u0F1JCTm9aagksYtCR0r9yyYboWyT1Yt");
        timezone.setCountry("oNeT6gRwuHoZznUZvhfhqrKrU118zVDRNUgimy2jzZMpBUZDna");
        timezone.setEntityValidator(entityValidator);
        return timezone;
    }

    @Test
    public void test1Save() {
        try {
            Timezone timezone = createTimezone(true);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            timezone.isValid();
            timezoneRepository.save(timezone);
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            Timezone timezone = timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
            timezone.setTimeZoneLabel("puL5yhkw1WN9w9HzrkTVF4TiainETm8HAKU8hMR7Ya7flY8C1b");
            timezone.setUtcdifference(2);
            timezone.setCities("qE5Xhp3Oj6S4TPiii3Zae316S8RdHR10MAVX8r3WIJKUF9MGfJ");
            timezone.setGmtLabel("tJUCnhSTzxo8tZ1ld3CskWQoLTBhBuk1pNr8XpdRjYyobs867f");
            timezone.setCountry("eNLwbBvPeqjjlWTHXBWM3JqqM7gYTHcEBMduhpzDySurBnBYVz");
            timezone.setVersionId(1);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            timezoneRepository.update(timezone);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateTimezone(EntityTestCriteria contraints, Timezone timezone) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            timezone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            timezoneRepository.save(timezone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "utcdifference", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "utcdifference", 20));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "gmtLabel", "HfG4fCnNvzi6VvQOaLcVGIrTwk8bFqdfcEe28OvSQct1Xd87Kky45UcT82agPeTyGkVEBySwHlYo25qyCwTfW26tHMF7706e1jx5LK7otWX0q7deChF2ZjAzL4n1rHiG1k0yBfJKzbk5mdU3PjAMnAM3R0ouRiy4v6iqrmTOxcV0zsgV1ks0IoHjDj2JuMaBPsl3lk0C9GeyVFkj2JkDkjARbHLmi0jzwoc68N6IGlVAKZX7rPmK1T4ymFtXYkrlO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "timeZoneLabel", "WTIjI516USrxkkbRXJxiDR3iz4IF4BcnDduTQaBxdVdntgtE1j3Tm5AmtqfRzccdtkpEfGKa2ChG7NTojlXwpdS2BizIYV1yIXigkK8tItaHMwZOj3wlCWYSxnQ1OZ6R92tqsiJyh3jp3ZFmjGh7490xq1jNUpj7wmZJDtrzruTe59WE6RR9cr9Ml9J0IgetPInf4XXYkZAFH8C1tajCMwc5O3Xnxth2EUqu6gzld0RoFjvoX37mrSreqfwzerOWb"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "country", "O7u7fvdaeVXgPGRIyQNEKSMmBISPofV3qqh8svMPv12NjWoW1Lz8j7NpCcSm3zkrQ0RcvB8fNgjENnGVuQSPG6ufs5y0MYx3U4NGnQQseGiCz0pWr8a7DQF42w1LSo0nO524P84eEmUPEXn8lYERdjWsGOxNPFbbZQDW83iRln9ghZqgRBgtIRUoA6joEHxTABGOrXzmVsImcgsAjsRRtyuCMycQTXlMTpJnkQQM2Mg17rPIEQMdm48Wvrkv1NlHf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cities", "hGXhDcb83TxnNByDKj50Qy95iTZkRWlUwMadoMtGU8JTkerMY4r22FTtNYOLj2AJxET4G0gdswZaOGBCDutV2HR3iWCFdRy4QRF2AoqNcHPKoVAZsud6UsbDsj9XVcDcuZR0Ra4Brdeo5JHzjRhyErG6hUB48gup4ys2C4SmAvVsvfwqZFZQzdXkalJVqnMD7wIBBnMGSuKpPdfJYORTVM7cmQ3LPNiSQHb4frFu3umrrFCathDMmC4tBZBQbrK0u"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Timezone timezone = createTimezone(false);
                java.lang.reflect.Field field = timezone.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(timezone, null);
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 2:
                        timezone.setUtcdifference(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 3:
                        timezone.setGmtLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 4:
                        timezone.setTimeZoneLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 5:
                        timezone.setCountry(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 6:
                        timezone.setCities(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
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

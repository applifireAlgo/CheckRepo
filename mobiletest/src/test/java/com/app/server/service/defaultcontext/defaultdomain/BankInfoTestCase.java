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
import com.app.server.repository.defaultcontext.defaultdomain.BankInfoRepository;
import com.app.shared.defaultcontext.defaultdomain.BankInfo;
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
public class BankInfoTestCase extends EntityTestCriteria {

    @Autowired
    private BankInfoRepository<BankInfo> bankinfoRepository;

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

    private BankInfo createBankInfo(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        BankInfo bankinfo = new BankInfo();
        bankinfo.setUploadPic("AZqBSsxL0HtVq7z9eXpPkCcFwd52zNo0zoPBhriYe0jRymq20G");
        bankinfo.setBirthCertificate("qPOQqHItOSITXqwBNn7ZjzqGn2NQQxT7YMA9lGLGbeM2hE1qxy");
        bankinfo.setLongitude(7760.0d);
        bankinfo.setLatitude(7200.0d);
        bankinfo.setEntityValidator(entityValidator);
        return bankinfo;
    }

    @Test
    public void test1Save() {
        try {
            BankInfo bankinfo = createBankInfo(true);
            bankinfo.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            bankinfo.isValid();
            bankinfoRepository.save(bankinfo);
            map.put("BankInfoPrimaryKey", bankinfo._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("BankInfoPrimaryKey"));
            BankInfo bankinfo = bankinfoRepository.findById((java.lang.Integer) map.get("BankInfoPrimaryKey"));
            bankinfo.setUploadPic("N4v6M3ZjV9t2M2zwxHDdsl2p7H1ytNFUWppLhfps2kGDkpbtVM");
            bankinfo.setVersionId(1);
            bankinfo.setBirthCertificate("pRkStAmXm8SGfELbCXKCt2IQXncxM6H1yOLgaLgQB54QLLZzWO");
            bankinfo.setLongitude(9800.0d);
            bankinfo.setLatitude(7400.0d);
            bankinfo.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            bankinfoRepository.update(bankinfo);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("BankInfoPrimaryKey"));
            bankinfoRepository.findById((java.lang.Integer) map.get("BankInfoPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("BankInfoPrimaryKey"));
            bankinfoRepository.delete((java.lang.Integer) map.get("BankInfoPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateBankInfo(EntityTestCriteria contraints, BankInfo bankinfo) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            bankinfo.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            bankinfo.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            bankinfo.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            bankinfoRepository.save(bankinfo);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "uploadPic", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "uploadPic", "lqhsUkF3TKFjN7jJ91hlMxuc6DRSujoGcUXX1qH5Me1Xm1l4xBMQ0pGJd0fa4aC7aB8ktxaS0G0iNfnwVZjwEmlkntb9l91nibpB8gZezkWJag4jDOH1vo9982z2OCHT8ZulCnySqICNuPaqWDrvc2htZ7R6oixLEkq3VFgCfPh0b4zShHxhzwJGbWTG4koZU18epmBpITpblQmluZQBoEjFsiHrqyXbTAkh1Tco66bHB6VvP4MqwSAWAunW4ptJg"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "latitude", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "latitude", 1.4322184588947671E19d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "longitude", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "longitude", 1.1528508958950228E19d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 7, "birthCertificate", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "birthCertificate", "R1PS9a1Wemdk7aHqvpNoynTVUSW2UpzTIXkePeMu2eIjzUQ8LOB7g7NwOMLyRuwkuMr3oSGCOwDQKzEIXZXlGFgMEqw4tXu4swzXWfD3I8YngmyUq5bCuMTxgq8bnLB5vp4KFYvppvCNLcQMRAKxpfkfBRl1apTZddaaS8Sejz4Joalfta1NDqLU53yndcSx78IKvFGl4v9Wegp0OfpwmmUPPC8IUOBEmYK6s71nddo4rutryvpWv9ibXi0WbECQ0"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                BankInfo bankinfo = createBankInfo(false);
                java.lang.reflect.Field field = bankinfo.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(bankinfo, null);
                        validateBankInfo(contraints, bankinfo);
                        failureCount++;
                        break;
                    case 2:
                        bankinfo.setUploadPic(contraints.getNegativeValue().toString());
                        validateBankInfo(contraints, bankinfo);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(bankinfo, null);
                        validateBankInfo(contraints, bankinfo);
                        failureCount++;
                        break;
                    case 4:
                        bankinfo.setLatitude(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateBankInfo(contraints, bankinfo);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(bankinfo, null);
                        validateBankInfo(contraints, bankinfo);
                        failureCount++;
                        break;
                    case 6:
                        bankinfo.setLongitude(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateBankInfo(contraints, bankinfo);
                        failureCount++;
                        break;
                    case 7:
                        field.setAccessible(true);
                        field.set(bankinfo, null);
                        validateBankInfo(contraints, bankinfo);
                        failureCount++;
                        break;
                    case 8:
                        bankinfo.setBirthCertificate(contraints.getNegativeValue().toString());
                        validateBankInfo(contraints, bankinfo);
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

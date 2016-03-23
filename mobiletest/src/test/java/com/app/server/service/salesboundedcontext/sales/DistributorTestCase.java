package com.app.server.service.salesboundedcontext.sales;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.salesboundedcontext.sales.DistributorRepository;
import com.app.shared.salesboundedcontext.sales.Distributor;
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
import com.app.shared.salesboundedcontext.sales.SalesRegion;
import com.app.server.repository.salesboundedcontext.sales.SalesRegionRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class DistributorTestCase extends EntityTestCriteria {

    @Autowired
    private DistributorRepository<Distributor> distributorRepository;

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

    private Distributor createDistributor(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        SalesRegion salesregion = new SalesRegion();
        salesregion.setRegionname("RtRbiuKm3JBNy0rNQLwkeK13m9QsTWKwdhiW3ocCWw6E5OdV1m");
        SalesRegion SalesRegionTest = new SalesRegion();
        if (isSave) {
            SalesRegionTest = salesregionRepository.save(salesregion);
        }
        map.put("SalesRegionPrimaryKey", salesregion._getPrimarykey());
        Distributor distributor = new Distributor();
        distributor.setLongitude(4700.0d);
        distributor.setRegioncode((java.lang.String) SalesRegionTest._getPrimarykey());
        distributor.setLattitude(1700.0d);
        distributor.setDistributorname("GtOx76585h59Y8RdIdedd1qyP10rkhdJwSaNQvKTP7pghBqtn8");
        distributor.setEntityValidator(entityValidator);
        return distributor;
    }

    @Test
    public void test1Save() {
        try {
            Distributor distributor = createDistributor(true);
            distributor.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            distributor.isValid();
            distributorRepository.save(distributor);
            map.put("DistributorPrimaryKey", distributor._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private SalesRegionRepository<SalesRegion> salesregionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("DistributorPrimaryKey"));
            Distributor distributor = distributorRepository.findById((java.lang.String) map.get("DistributorPrimaryKey"));
            distributor.setLongitude(2800.0d);
            distributor.setLattitude(2000.0d);
            distributor.setVersionId(1);
            distributor.setDistributorname("82542DgcNwzfuc25KhXunaqTvUlfDB5iRrrP10OSe1XkBtcNoC");
            distributor.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            distributorRepository.update(distributor);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByregioncode() {
        try {
            java.util.List<Distributor> listofregioncode = distributorRepository.findByRegioncode((java.lang.String) map.get("SalesRegionPrimaryKey"));
            if (listofregioncode.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("DistributorPrimaryKey"));
            distributorRepository.findById((java.lang.String) map.get("DistributorPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("DistributorPrimaryKey"));
            distributorRepository.delete((java.lang.String) map.get("DistributorPrimaryKey")); /* Deleting refrenced data */
            salesregionRepository.delete((java.lang.String) map.get("SalesRegionPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateDistributor(EntityTestCriteria contraints, Distributor distributor) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            distributor.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            distributor.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            distributor.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            distributorRepository.save(distributor);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "distributorname", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "distributorname", "B8M81GQGte7EdzhqXr43FzRDdfsNEiEto9qhPELvyXhy8BKQ1q0uL3VjdH0Ekxc9A"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "longitude", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "longitude", 1.7868292797966004E19d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "lattitude", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "lattitude", 9.321410092103662E18d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Distributor distributor = createDistributor(false);
                java.lang.reflect.Field field = distributor.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(distributor, null);
                        validateDistributor(contraints, distributor);
                        failureCount++;
                        break;
                    case 2:
                        distributor.setDistributorname(contraints.getNegativeValue().toString());
                        validateDistributor(contraints, distributor);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(distributor, null);
                        validateDistributor(contraints, distributor);
                        failureCount++;
                        break;
                    case 4:
                        distributor.setLongitude(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateDistributor(contraints, distributor);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(distributor, null);
                        validateDistributor(contraints, distributor);
                        failureCount++;
                        break;
                    case 6:
                        distributor.setLattitude(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateDistributor(contraints, distributor);
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

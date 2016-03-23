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
import com.app.server.repository.salesboundedcontext.sales.SalesDataRepository;
import com.app.shared.salesboundedcontext.sales.SalesData;
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
import com.app.shared.salesboundedcontext.sales.Category;
import com.app.server.repository.salesboundedcontext.sales.CategoryRepository;
import com.app.shared.salesboundedcontext.sales.Retailer;
import com.app.server.repository.salesboundedcontext.sales.RetailerRepository;
import com.app.shared.salesboundedcontext.sales.Distributor;
import com.app.server.repository.salesboundedcontext.sales.DistributorRepository;
import com.app.shared.salesboundedcontext.sales.SalesRegion;
import com.app.server.repository.salesboundedcontext.sales.SalesRegionRepository;
import com.app.shared.salesboundedcontext.sales.Material;
import com.app.server.repository.salesboundedcontext.sales.MaterialRepository;
import com.app.shared.salesboundedcontext.sales.Brand;
import com.app.server.repository.salesboundedcontext.sales.BrandRepository;
import com.app.shared.salesboundedcontext.sales.Channel;
import com.app.server.repository.salesboundedcontext.sales.ChannelRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class SalesDataTestCase extends EntityTestCriteria {

    @Autowired
    private SalesDataRepository<SalesData> salesdataRepository;

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

    private SalesData createSalesData(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Category category = new Category();
        category.setCategory("fHAmDW3ulJGpUeO8yiY91RTm2315oQyuH9Whik2B0cLmNHgLtu");
        Category CategoryTest = new Category();
        if (isSave) {
            CategoryTest = categoryRepository.save(category);
        }
        map.put("CategoryPrimaryKey", category._getPrimarykey());
        Retailer retailer = new Retailer();
        retailer.setRetailername("NlwTzenlEjmz5OrHyNlqdPis0vfpvGISsX93oAveV5Uys2AJ3y");
        Distributor distributor = new Distributor();
        distributor.setLongitude(9800.0d);
        SalesRegion salesregion = new SalesRegion();
        salesregion.setRegionname("SV0Htqh1OF78JDJ8IKjhhqqYFeZPPGUTu7AkKN3jBq4meGxOcC");
        SalesRegion SalesRegionTest = new SalesRegion();
        if (isSave) {
            SalesRegionTest = salesregionRepository.save(salesregion);
        }
        map.put("SalesRegionPrimaryKey", salesregion._getPrimarykey());
        distributor.setLongitude(7230.0d);
        distributor.setRegioncode((java.lang.String) SalesRegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        distributor.setLattitude(1870.0d);
        distributor.setDistributorname("YROrANQn6I8VmcROZzZTol2XRPK1W3BKDIVwKsNti9plriH21U");
        Distributor DistributorTest = new Distributor();
        if (isSave) {
            DistributorTest = distributorRepository.save(distributor);
        }
        map.put("DistributorPrimaryKey", distributor._getPrimarykey());
        retailer.setRetailername("3f5Owk7pRvyCZ3HzVzkbCAUnXN6m29tz4ZYmCa0jVoBeyuOMZQ");
        retailer.setDistributorcode((java.lang.String) DistributorTest._getPrimarykey()); /* ******Adding refrenced table data */
        Retailer RetailerTest = new Retailer();
        if (isSave) {
            RetailerTest = retailerRepository.save(retailer);
        }
        map.put("RetailerPrimaryKey", retailer._getPrimarykey());
        Material material = new Material();
        material.setMaterialdesc("wo0u9k3OmnLCwZnCHEbG9zrkKsEZsLLGNMu516LLDYRDEzjP1v");
        Brand brand = new Brand();
        brand.setCategoryId((java.lang.String) CategoryTest._getPrimarykey()); /* ******Adding refrenced table data */
        brand.setBranddesc("5iEsrvnJUNXVf7FS1PEPCb3DlmyJ9cZJBwfPEOCRFhoGlOqlkl");
        Brand BrandTest = new Brand();
        if (isSave) {
            BrandTest = brandRepository.save(brand);
        }
        map.put("BrandPrimaryKey", brand._getPrimarykey());
        material.setMaterialdesc("FzSROxE3lN69OuF49FoNa8hFTvyQoaAyA37o91D4Zl054nosAG");
        material.setBrandcode((java.lang.String) BrandTest._getPrimarykey()); /* ******Adding refrenced table data */
        Material MaterialTest = new Material();
        if (isSave) {
            MaterialTest = materialRepository.save(material);
        }
        map.put("MaterialPrimaryKey", material._getPrimarykey());
        Channel channel = new Channel();
        channel.setChannel("k0JulNrovm5JRO48RIptWiT6tRPnosN1P9txcmllBOiGdZKc94");
        Channel ChannelTest = new Channel();
        if (isSave) {
            ChannelTest = channelRepository.save(channel);
        }
        map.put("ChannelPrimaryKey", channel._getPrimarykey());
        SalesData salesdata = new SalesData();
        salesdata.setCategory((java.lang.String) CategoryTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setReatilercode((java.lang.String) RetailerTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setBranddesc("J3SOKf0p9JuwAmKNfqHpIIRXM9f3tvierha6miyTRnI2IpdylY");
        salesdata.setMaterialdesc("tMaWjD7f5WXcv9Hi8sTVWWw2RqlZ3thqsnl5XqAsE7qLgt6zR3");
        salesdata.setSalesyear(2147483647);
        salesdata.setMaterialcode((java.lang.String) MaterialTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setBrandcode((java.lang.String) BrandTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setSalesinvoicenbr("Z5ENfyZe6h2XG4ksc90bZJxCmfPQFLvFWQY0ms6VIoO5SZfNE2");
        salesdata.setRetailername("SeYRSjWCqBliwUsvXwn5ihO9Vytn8vpJb7PFa7QXiHpiGfFYpY");
        salesdata.setGrosssalesamt(6700.0d);
        salesdata.setSalesmonth(2147483647);
        salesdata.setSalesqty(3900.0d);
        salesdata.setNetsalesamt(1600.0d);
        salesdata.setChannelId((java.lang.String) ChannelTest._getPrimarykey());
        salesdata.setSalesdate(new java.sql.Timestamp(1458641455729l));
        salesdata.setEntityValidator(entityValidator);
        return salesdata;
    }

    @Test
    public void test1Save() {
        try {
            SalesData salesdata = createSalesData(true);
            salesdata.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            salesdata.isValid();
            salesdataRepository.save(salesdata);
            map.put("SalesDataPrimaryKey", salesdata._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CategoryRepository<Category> categoryRepository;

    @Autowired
    private RetailerRepository<Retailer> retailerRepository;

    @Autowired
    private DistributorRepository<Distributor> distributorRepository;

    @Autowired
    private SalesRegionRepository<SalesRegion> salesregionRepository;

    @Autowired
    private MaterialRepository<Material> materialRepository;

    @Autowired
    private BrandRepository<Brand> brandRepository;

    @Autowired
    private ChannelRepository<Channel> channelRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("SalesDataPrimaryKey"));
            SalesData salesdata = salesdataRepository.findById((java.lang.Integer) map.get("SalesDataPrimaryKey"));
            salesdata.setBranddesc("3uHbiG7HzygkvVyqO6acZxiqZaOdOL1hkZaZ6ysloXF12buMcs");
            salesdata.setMaterialdesc("58j5smPEZheBHcYEDwioIBz3WPiDgZXQtiABh4ufin241PNqDk");
            salesdata.setSalesyear(2147483647);
            salesdata.setVersionId(1);
            salesdata.setSalesinvoicenbr("w5VNzIJrY7g7DMjW17ox1Q1GYMWFVy1UX6e6fHIgSZa9QNq6IB");
            salesdata.setRetailername("BP1eXbAafGf9NvWg0WSw7I7KURr5b2nFIca8JdxxoSlQ5FCSdX");
            salesdata.setGrosssalesamt(3200.0d);
            salesdata.setSalesmonth(2147483647);
            salesdata.setSalesqty(1700.0d);
            salesdata.setNetsalesamt(4600.0d);
            salesdata.setSalesdate(new java.sql.Timestamp(1458641455742l));
            salesdata.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            salesdataRepository.update(salesdata);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycategory() {
        try {
            java.util.List<SalesData> listofcategory = salesdataRepository.findByCategory((java.lang.String) map.get("CategoryPrimaryKey"));
            if (listofcategory.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByreatilercode() {
        try {
            java.util.List<SalesData> listofreatilercode = salesdataRepository.findByReatilercode((java.lang.String) map.get("RetailerPrimaryKey"));
            if (listofreatilercode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBymaterialcode() {
        try {
            java.util.List<SalesData> listofmaterialcode = salesdataRepository.findByMaterialcode((java.lang.String) map.get("MaterialPrimaryKey"));
            if (listofmaterialcode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBybrandcode() {
        try {
            java.util.List<SalesData> listofbrandcode = salesdataRepository.findByBrandcode((java.lang.String) map.get("BrandPrimaryKey"));
            if (listofbrandcode.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("SalesDataPrimaryKey"));
            salesdataRepository.findById((java.lang.Integer) map.get("SalesDataPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBychannelId() {
        try {
            java.util.List<SalesData> listofchannelId = salesdataRepository.findByChannelId((java.lang.String) map.get("ChannelPrimaryKey"));
            if (listofchannelId.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("SalesDataPrimaryKey"));
            salesdataRepository.delete((java.lang.Integer) map.get("SalesDataPrimaryKey")); /* Deleting refrenced data */
            channelRepository.delete((java.lang.String) map.get("ChannelPrimaryKey")); /* Deleting refrenced data */
            materialRepository.delete((java.lang.String) map.get("MaterialPrimaryKey")); /* Deleting refrenced data */
            brandRepository.delete((java.lang.String) map.get("BrandPrimaryKey")); /* Deleting refrenced data */
            retailerRepository.delete((java.lang.String) map.get("RetailerPrimaryKey")); /* Deleting refrenced data */
            distributorRepository.delete((java.lang.String) map.get("DistributorPrimaryKey")); /* Deleting refrenced data */
            salesregionRepository.delete((java.lang.String) map.get("SalesRegionPrimaryKey")); /* Deleting refrenced data */
            categoryRepository.delete((java.lang.String) map.get("CategoryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateSalesData(EntityTestCriteria contraints, SalesData salesdata) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            salesdata.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            salesdata.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            salesdata.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            salesdataRepository.save(salesdata);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "retailername", "Qv8wda9H0iC0V0mMNByHa3dbgKYjjsDwFPVjBd2VkIJ2as6qS16d2ANju1p89ZQVb"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 2, "salesdate", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "salesmonth", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "salesyear", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "salesinvoicenbr", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "salesinvoicenbr", "XfluFVeH7tPK5IbLzJelAHhys0lpdF4t59XXeog06vWU9cMCErC8kAywAmlMKNIgt"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "materialdesc", "8gVZERlZBMcovHCLzW7EHmRNMMrJ4Zc7oehk4Q5Z99jE9M7RxLNhRZ6RYYpmEZgov"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "branddesc", "mi7i7XboK3RtXW2ZBi5GjFTKCJwq16zxXfCQt7KZ6DaRbfTdPXyhQ2rX6UIznkkJ0"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 9, "salesqty", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "salesqty", 9.616697674708615E18d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "netsalesamt", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "netsalesamt", 1.6961655585416854E19d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "grosssalesamt", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "grosssalesamt", 1.3880256783342875E19d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                SalesData salesdata = createSalesData(false);
                java.lang.reflect.Field field = salesdata.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        salesdata.setRetailername(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 2:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 6:
                        salesdata.setSalesinvoicenbr(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 7:
                        salesdata.setMaterialdesc(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 8:
                        salesdata.setBranddesc(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 9:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 10:
                        salesdata.setSalesqty(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 12:
                        salesdata.setNetsalesamt(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 14:
                        salesdata.setGrosssalesamt(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalesData(contraints, salesdata);
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

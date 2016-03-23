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
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
import com.app.shared.organizationboundedcontext.location.Address;
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
import com.app.shared.organizationboundedcontext.location.City;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.AddressType;
import com.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        City city = new City();
        city.setCityCode(1);
        city.setCityName("yemuBq54BWHBfEJx7BS0NanKPrZB9VML0djWyRxEWux5Ocy1si");
        Country country = new Country();
        country.setCountryCode1("Tqi");
        country.setIsoNumeric(2);
        country.setCurrencySymbol("8pcKTKQB7dv9iPmFcfswhgqV7wcncbUi");
        country.setCurrencyCode("YJZ");
        country.setCountryName("lPWSoZ04PVFcngUs0NezcuIYYDnP754eLQeypbD4wGb39AMlNT");
        country.setCountryFlag("4lH3U19OGWuOAkVS2EpncmeIvR2l1FobZ0U4Gk4xqf0v4sh26o");
        country.setCapitalLatitude(3);
        country.setCountryCode2("BzQ");
        country.setCurrencyName("jVN8oT8ElX2H8EgCbV07dVuzNjIR2E2AOSZGt97KTY1AbZHDRC");
        country.setCapital("N2seKNIgW5GH79iZJpUny5q896LuIE8M");
        country.setCapitalLongitude(4);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        State state = new State();
        state.setStateCapital("9yUBurrMCxwPxe3om3U16cfL9COmE0VBJJOOMU5i2bKWMDA3Wm");
        state.setStateName("1MoO7dvQBEvEhz7eYjHQ227j4ho2p0BDAfg81eDq1HdqBFVcFn");
        state.setStateCodeChar2("QabEyAcdMqb9ORnpp9myE1qBjP8Okgo2");
        state.setStateFlag("pFwc4u37JUyaHa7jyTJJ21Jtkvf8ZKtwBFyv3Fx0lfdyZ0JW1A");
        state.setStateCode(2);
        state.setStateCapitalLatitude(9);
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar3("vNngc06bTokqdlmB1vS972QGQRetjGuZ");
        state.setStateDescription("FFyCk1iCrCHSFz8Ui58bi8V7MN29Nks80dFUDJd4mIwL7pz65p");
        state.setStateCapital("6FGRtFtNwJrhWZdJILOCXq4bIR11xEjB3HiCAfi0C1VMqi6eXW");
        state.setStateName("1j07GVCsJWknPUm2l6RJkgBiF8y8ONgzmZ1QKuslL5ZnWueMPH");
        state.setStateCodeChar2("ToQWGMRnX036rcrfzHP0CM1PlH3pyTmK");
        state.setStateFlag("oTR6AjNo8xqW43I68djdvHTZ6cXB9bAOGqcFqs54MxxDARfMBR");
        state.setStateCode(2);
        state.setStateCapitalLatitude(5);
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar3("ZNkVkFQ5JQx9ViUeCxLhQuZTXpi7YIfL");
        state.setStateDescription("BQTWqaFZwepYl1dGVz12dFbawFju57Scu3CmiAD6jNdq4IBUji");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
        }
        map.put("StatePrimaryKey", state._getPrimarykey());
        city.setCityCode(1);
        city.setCityName("Ojq16bPygZmV9Tt4psEHAx5A2lgwNrEiNF4vRmF8s1hgO9JXiE");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("svQSF205MkMAoVG8BOS5u8aEtjRnC5JwI0yzOiEsIhGswUCz7o");
        city.setCityCodeChar2("Z3NW0fiTRxFMPThOrj7vwtxTdIXsLS1o");
        city.setCityLatitude(1);
        city.setCityDescription("Pczr98PGidxff6xi5TIrKhq880LcI0LmnL0sMhcJhnPrc2GSQf");
        city.setCityLongitude(5);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
        }
        map.put("CityPrimaryKey", city._getPrimarykey());
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("DyHHIIhLMyCqqacmFwKySaMO3AYI0G5julsbjIDStbjIq6FUuC");
        addresstype.setAddressType("7InKv8nQL31yWBqHgg08rl49HDLIRdQh0b90KShgTchNBoyUYg");
        addresstype.setAddressTypeDesc("ztl5t6zRzcCplVctuv1e3XqZUMmrYmv0qjoomPnO0Hvtj1FNDE");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
        }
        map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        Address address = new Address();
        address.setLongitude("yOdja65dczrM3uLdiXdOjhNBNXuiv3oru5ugO1cRnzJjrTLYDs");
        address.setAddressLabel("BRhDdvzZrZg");
        address.setAddress3("KarzekvWq4gvBA2k5ViXf0PpL89zIhWDJmHQxypkIVRX7dSQ1w");
        address.setAddress2("opyqd146u5W4LVt27MnGxLhowiyevMd1v1aibE3mHO4ks0fnTC");
        address.setZipcode("9cDf0l");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("P5X1LRZLdP4Xf7buWSbsvculrhup1QDYlIaVcJgIHjYx2HmnCj");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setAddress1("X6OpgVeYIvo69WnneiyRb1zDYEXTpPL05dBDjIFGsExYOC4nGJ");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setLongitude("OM3SaDIpNbc3KAZK0DdgQtu542Afr53yNs0gCZNewXrieZoEEI");
            address.setAddressLabel("sYbkAtbYMzw");
            address.setAddress3("2rlKi1bLhUnlhICbVMXPQQWBrrEcc1g75MeBM4JBSoClmLZDxi");
            address.setAddress2("qBSNO0hqRgNZcGUPESaVaWMHsKzMmRgpiXJWe89uVZfqOP1dqS");
            address.setZipcode("uLsBM6");
            address.setLatitude("pvClJCSBB8m91iLjGJiNdIiJL0IOEiwXRqYYgY42sTKOu0kXrR");
            address.setVersionId(1);
            address.setAddress1("IfSq79uHcIjYH6NmcOgiATyT708L25nZpq7BQRr5AyBYyqQaMn");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "vILRepQgLmFE"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "sgJqyosGLPJ7umt2RWZvlBlQWWDmmty51GDL77Lx7Ip04qR9H4oPb36XO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "VuYisOepAHOltGY3vUYvUx901RpX13x7FXoHld16mpEDOKhxKVXkgmsv3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "UNfBye1XoBzSuA3PTBQSoKo751dsx60mzjKROwsIycUN4CPy9nQ1hjiTo"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "At09DyN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "EpEvVuoKHfGoc0QaWn1oIyzt70QKuB4G65flUdoM9sMjbrbeqYVElwneeikK1bEed"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "w5fmdpwM4zOc2Jxn6kb38W7qkkHY7tHf0PfNtydWeZOqNB4Pn9jn645HkIklUWgUx"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = address.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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

package mobproj.app.server.service.organizationboundedcontext.location;
import mobproj.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import mobproj.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import mobproj.app.server.repository.organizationboundedcontext.location.AddressRepository;
import mobproj.app.shared.organizationboundedcontext.location.Address;
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
import mobproj.app.shared.organizationboundedcontext.location.City;
import mobproj.app.server.repository.organizationboundedcontext.location.CityRepository;
import mobproj.app.shared.organizationboundedcontext.location.State;
import mobproj.app.server.repository.organizationboundedcontext.location.StateRepository;
import mobproj.app.shared.organizationboundedcontext.location.Country;
import mobproj.app.server.repository.organizationboundedcontext.location.CountryRepository;
import mobproj.app.shared.organizationboundedcontext.location.AddressType;
import mobproj.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
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
        city.setCityName("9yrIkoevkxMsbmtqu0vLbUV3NfKESKvPqMQ3B4NXR1yG0wu9Mm");
        city.setCityFlag("Oh5nEL8dchPBIulvInvmAJBesF3KD4Id7uKha8cVFkqiJphFbm");
        State state = new State();
        Country country = new Country();
        country.setCurrencyCode("CB4");
        country.setCapitalLatitude(6);
        country.setCurrencyName("7l6PJTDAuG4PSvxFahPrNYVwBEBb1q9EeeQ47r1QF6IvTnXgzA");
        country.setCountryName("IrNSKNvezX26HTWFHlYA5aU9K7lzzOSWaZn8EvcDXuvwVvJq7R");
        country.setCountryCode2("bOo");
        country.setCountryCode1("uuI");
        country.setCountryFlag("F3TL2Zra9KKU2rQeuxFPKyhNdBmcNMPUcpSsGXOlgoSgDSHnW8");
        country.setIsoNumeric(4);
        country.setCapitalLongitude(10);
        country.setCurrencySymbol("HYCfVLKK0leY9wTjZURn7HCG5GiIoRT7");
        country.setCapital("kzESi7UXBzXndfj5OBab2Tx5u6WsRETy");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("PtPbkljK2eq7PoHJcVSD6UE6XcGN76kSrowjSGaV8HMOSJTDPM");
        state.setStateCode(1);
        state.setStateCapitalLongitude(1);
        state.setStateFlag("vqjvWlWW4zNfiN318sA9yFiE7HJPEdAKV0UpbM9Db9TVKymaQs");
        state.setStateCapitalLatitude(8);
        state.setStateCodeChar3("1bZtrl5YQFmh3Cy0zgFKzVXKRvSVBk7D");
        state.setStateCodeChar2("rp6KPQI4padrLyDW1nNOYekr1tZjBGsK");
        state.setStateCapital("X6aHEVopWbRvF8pC8ZAbGRRIJSqfp9E2p4PATubPZQ0piKOiCP");
        state.setStateName("aKk7mlTFRJVCvhpUJikCQscKO8UO4cN6qXZIKpJa9UEtuVpURE");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
        }
        map.put("StatePrimaryKey", state._getPrimarykey());
        city.setCityName("pnFK6jYzHAnwdUQqbNF5ftJ9oYYFwCTSc1aVsUCC82Ds3YhPu0");
        city.setCityFlag("iNHP7Y6zQ1NcwvzkQWsrvDJzM5NbEDAYK5FZlBsR6MhCBQ6ot6");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("Zjf8JEwfRp6dMC3R61GMdSDps7XQh26mnNG88tDc0TnqswBlqU");
        city.setCityLatitude(8);
        city.setCityCodeChar2("E6RCRbAS8ApGUd22S3tmFiqk2DBZ4NwI");
        city.setCityLongitude(2);
        city.setCityCode(1);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
        }
        map.put("CityPrimaryKey", city._getPrimarykey());
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("Nn9Qt3uT2hMpl3LZm0VQ8r146hexRnXksfQKssIrR2vwdZqNz8");
        addresstype.setAddressType("yoSZBRGxbw72iLEHQdsZQ37UwdqJEqv0e3eD8BDaqSpXWrUQLz");
        addresstype.setAddressTypeDesc("nCO7kJBQZw5sCFNeHhcXVk8Y4jb3xIUajtnKOpQfFyVUG3i7xk");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
        }
        map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        Address address = new Address();
        address.setLongitude("4gMJQmmKSnC79VorSCAClnrn1kuyePJNMSVLYsNOIHNiVAASuD");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("yrGFp2");
        address.setAddress2("swuzNcSyJkZX19o9b2O8LwTEF5ch3G8HfjGYNwxpXwl753MQzb");
        address.setAddress1("4D94dDIWjnEa3nB1UIztd5PokSlbLk5n06i5Ws6AAuMuKWqrWD");
        address.setAddressLabel("VKEtxyaT8yf");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("icd7cGCl6pVX0uMXVdXX3dxj7bcF06pdTjrfj4vFs0bKBmlrDy");
        address.setLatitude("LPodYl2gOEr5QgjYzS8TSEo33znjGDENNnmUAeJv6JUQ8KqOEY");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
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
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setLongitude("rdFNk8Az3jQuqHO7nv1QpRWuWsrYVYWLiuo70GweF1cIXXPWbi");
            address.setZipcode("tyjpNf");
            address.setAddress2("oqai02U9Va2K3rgT7OUkotgsS7RLYAZqMc3rEAewW3FSWOXDa6");
            address.setAddress1("tKp7VSIONhDydfI2WjkHm9PCJCPRE7dOSam7pZRs9uopdTmvae");
            address.setAddressLabel("EjYY8pW4vWt");
            address.setVersionId(1);
            address.setAddress3("0dAJiOKVlMuSKSiDRB4Zmn2xyqx35FsYHvOKaEvIkVSG6Wddo4");
            address.setLatitude("IHRSpaxL3gNg8jFpq5cMe1RoMtkER4gb4v9UzGYMOI2PHSdCwS");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "CNJSSTXc9RzQ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "3xOh0WIqSU7ZJnFaB2Xr7AobdiZ93obDOkydlrQKY6jjQHEQShm8NGzFN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "LSyXCoMjcnsFRQwLdw94fHt4JW2J6EDkMaELSJEC29CSzVlkp83gANWof"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "1NpjI4RovXPOEeZEkNpoLFMnDLypRV4RcxuJKtKBTdb9t34vVGUlnEibt"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "DYjPKVm"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "ora9BANBtZ6sVjqYQpbzqkTek1iLBPs9slmkXUfHPrzeJzFTvQOgAYp882GvpQunF"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "Nx1c6sOlJFa7nZAOKgf7RvUKHg4kblHQC9hoq6GljHd0uBgVht3q6JRUC83loBVSC"));
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

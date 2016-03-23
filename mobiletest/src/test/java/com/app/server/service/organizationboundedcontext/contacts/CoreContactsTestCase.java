package com.app.server.service.organizationboundedcontext.contacts;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
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
import com.app.shared.organizationboundedcontext.location.Language;
import com.app.server.repository.organizationboundedcontext.location.LanguageRepository;
import com.app.shared.organizationboundedcontext.location.Timezone;
import com.app.server.repository.organizationboundedcontext.location.TimezoneRepository;
import com.app.shared.organizationboundedcontext.contacts.Gender;
import com.app.server.repository.organizationboundedcontext.contacts.GenderRepository;
import com.app.shared.organizationboundedcontext.contacts.Title;
import com.app.server.repository.organizationboundedcontext.contacts.TitleRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationData;
import com.app.shared.organizationboundedcontext.contacts.CommunicationType;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationTypeRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationGroup;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationGroupRepository;
import com.app.shared.organizationboundedcontext.location.Address;
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Language language = new Language();
        language.setAlpha3("8Ly");
        language.setLanguageIcon("rrtL2ipi9ZvtCI90Jm8LKaAi5DKHsNhd34PeyaWQ7WG3oX9VAG");
        language.setLanguageDescription("8BzdaVgj9Mkmp1jmK0vvSmOqnQrN42M3CsIlWwEetZw1F45kjw");
        language.setAlpha4("qZMI");
        language.setLanguage("IM2pbntD0LdjOfPtKRoZEZe7ulQzh5NsCKXTxjpP6DtI8DJsA7");
        language.setAlpha2("HD");
        language.setLanguageType("C32QPtCWTEsKxINnQwRgVkGmUcP2GbGs");
        language.setAlpha4parentid(7);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
        }
        map.put("LanguagePrimaryKey", language._getPrimarykey());
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("eHONE4tp7C0PVNI2APsAKrSqAXbZONOSWMRATyhb7OQR2p9rpL");
        timezone.setUtcdifference(7);
        timezone.setCities("MPtiX2DxQPksjwUh01LJ0pAphS9IB1S8LDyVnjou7SqbehQ2Fo");
        timezone.setGmtLabel("h3AY8wsEsuIe4tKRfqqgo0xsqqtZJQoEzGLXPNpaLYShNd9Vhe");
        timezone.setCountry("u8Xmb8ur6Np8gNBx1eTijglftJDKgTHT3EfyoASAFMlTwxPSS6");
        Gender gender = new Gender();
        gender.setGender("sri9mg7rAn8OGXhAzTWZcOhHfVSGmeVqw9NRMjoEHTs9IIcANf");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
        }
        map.put("GenderPrimaryKey", gender._getPrimarykey());
        Title title = new Title();
        title.setTitles("ys6UdwJhzWsC8bpxn3Kk1ju7Nw93Akuuiksov2xretfhkzdhDo");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
        }
        map.put("TitlePrimaryKey", title._getPrimarykey());
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setLastName("laa3ujc6Pdd1ApKeoP9ONSvGQ9LELXLnfQsdL7e7AvI8bLx72a");
        corecontacts.setNativeTitle("4XQMs0ECRkBvEiJ3lnyz9c89Vx1vmpeHvrqiSKXoeSHUmL0aYA");
        corecontacts.setNativeLastName("RFHRfHgC2K7RbA97xYjv2DvNplJK5b73ZT0Ez20KmqMzlGUWXX");
        corecontacts.setMiddleName("enaTUgtk5IHk3eCwbjHjWKJFBHKAal0h1kOhfwnD06Xaz2s906");
        corecontacts.setEmailId("BvqQeYHdXSUBus6LLW5Ku18syesOwQ1Fm8nxnBJeczl4bhyjNe");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        corecontacts.setPhoneNumber("aZCSXCbu9RU5DfjVucFo");
        corecontacts.setAge(120);
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1458641438041l));
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("2sMcamPPL9pzJKZ00Niwb6uX0knmSX0T73YMcAOJ4l4m27j9vc");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1458641438063l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("R5a8pV7H8EVhMiCNoC1SDRRckV8BgrMxkeWGe8PWOmHth1EOln");
        corecontacts.setNativeMiddleName("PBzs5G89OPIjvrSXnRXs7IRxCW8Gy3bxH5QJ7pYldaCDZIEd7k");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("O4pfKhKgVctXRffJN6hTVGGJ0QVHbyDfZYAm9LfzRBsWOY6YK4");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("ijIpb2A5CP4euXX5Mr8srZ6hMYIe3xQ6c9FgTEHWnsvddmKhk8");
        communicationgroup.setCommGroupDescription("z7jMR1vlTJ5XCi655qEJOoJB4bKeCC5ZqbvmIp9rGzmHWMdSrB");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
        }
        map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        communicationtype.setCommTypeDescription("x7e7peTzzvGD3DdMnAhvGaqifRHTFvzPMYq60p1evyQX4OFvg1");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("MphtoQEEZGhBZv1KxGTTzmC3bXAC0QrHNxz1atawj8gTlQiWlU");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
        }
        map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLongitude("JatKt6OarTQZTBBGBd74sJV74QxwCeHwRpqgzaBuMiQhd7wLTi");
        address.setAddressLabel("3tdf72r7OnG");
        address.setAddress3("RZ5kG2p9CuaHhH6pfonFhxxtsa5LKUwCed32Sy95VMYDLPjbkV");
        address.setAddress2("JZanSkSd1nYz7s3LTlIFTNU9AVM6MvuFvDiDWb8qC48uJXrJ4S");
        address.setZipcode("SyDg92");
        City city = new City();
        city.setCityCode(2);
        city.setCityName("GmditDecGkFr2BGmhiH0m2ukpEaQN4UUrr1UTtEc8pgnJLgiN2");
        Country country = new Country();
        country.setCountryCode1("lOI");
        country.setIsoNumeric(1);
        country.setCurrencySymbol("XC3aVenw6g7SvlCorLXZbh4JZjfLa52Z");
        country.setCurrencyCode("B7n");
        country.setCountryName("m3NKRm9eRhFXMs5yQHdaRgNAldfvL7vMjQ8aYhDYuN6BGQA2Lt");
        country.setCountryFlag("FwBGlYTZwE6fyWgDcEFnUock00tX8WgUAcxNhX1fHYmeMnPnYd");
        country.setCapitalLatitude(2);
        country.setCountryCode2("sSW");
        country.setCurrencyName("OfMqVoccqOuBk9npZNoHtsuFhK5pwQstAYuvRgPOToqz6ITQX1");
        country.setCapital("nhcjVmmINjjVz6KWZqWSnR26zdx13S9Y");
        country.setCapitalLongitude(4);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        State state = new State();
        state.setStateCapital("QaT9WFQEODdGaWsVZnOAmJk1hf1AfqvE4qOAtrWkLlCy0V5Qne");
        state.setStateName("z8EJZKn6ovo4oqXwW1wfv72YawzCnnAGrb47qjwoKLJqfbU23S");
        state.setStateCodeChar2("WpZU4HgRReluJoxs2MVN0uIYdJEJMR85");
        state.setStateFlag("9WgVGK4YmjcJWEd3mTomGeULxDqaZRiBj5R8nBPoYGPcvch2iO");
        state.setStateCode(1);
        state.setStateCapitalLatitude(2);
        state.setStateCapitalLongitude(1);
        state.setStateCodeChar3("dONzgosXoLS7cNBKkkzfWOcl75Xp13Ep");
        state.setStateDescription("Nl7wgxnOcNG255jfcUaO8vak4360vrd8Kx9g2drmQ68hrV7GQ6");
        state.setStateCapital("XS89kPbPgMU5AnXuQ1wJiMiT0fvOGhuef8KHD4vlVlGL5kSvZ7");
        state.setStateName("K9YfE5JtLuEy99RMLgldtrHe1fsrx1y7l7N5uK4M5NL5xeuFJh");
        state.setStateCodeChar2("lAcbSn5OBjtUpSH2Du9wLxphVRcUcXy0");
        state.setStateFlag("lT9vmRMZAHEWkqq8fi0QDxfsGVq5cruLBSuB40LyQtvdqjz8Wr");
        state.setStateCode(2);
        state.setStateCapitalLatitude(7);
        state.setStateCapitalLongitude(5);
        state.setStateCodeChar3("1lLN2SHx7ZjFROipIU9PYFMGwFh44yNQ");
        state.setStateDescription("mRb2Qh5mGJXAMBnUmsC1zu9iCWzoYEmGiLglzN8aHyrx5nvHUo");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
        }
        map.put("StatePrimaryKey", state._getPrimarykey());
        city.setCityCode(1);
        city.setCityName("lhRDTUQyAHhLmHrTBEeOXnDd0CtK82EVoSRnFxgjuCiIuENFbF");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("qz0ULOypZ8uv1NPxhtUUyAfh2jOOjCKn7SLSeqiZMUqFUUincE");
        city.setCityCodeChar2("yc3mniObzZ4G1rnpQOQPVsFxkNysAvo1");
        city.setCityLatitude(7);
        city.setCityDescription("HdDJo5kgOA9tN3uJqguz8QhtjY8fbVOgCy5Hfv51oJ6U7eaFFf");
        city.setCityLongitude(2);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
        }
        map.put("CityPrimaryKey", city._getPrimarykey());
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("WhblF8fVUpBxGy9xk00U2Pwm21IEHYQlgh4qo9Xic2NT6SYhPZ");
        addresstype.setAddressType("MRV9EvVHmiTNxJJQy5AzlgiobIUdBsz6YnbMD7UysWUnoqUT2e");
        addresstype.setAddressTypeDesc("XjM9uZbvcRKQQiObuKwIGcqVIWbm54bEYQNOyt0KEgiTqQ1O9r");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
        }
        map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        address.setLongitude("ebnLUqpBWhyyHCFAM2ejxTPwGKhWoF0BXpPk85pYPeRYp6wtQg");
        address.setAddressLabel("4CoQZ4mTg0Q");
        address.setAddress3("8zTRqf2dlXq4uPKZV2vb4KKHBBI5OiVopnETN8DvLtMLOyo1tx");
        address.setAddress2("azDc8YX3Ucw3GN5hsAquQHSviHd0Z9JzWGGEY8YR5KZpKhrAhx");
        address.setZipcode("CWXH26");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("dyc8CflA3u2piXdh82WaFVDr5x2Qee7VSuMHYRWj7TSBRnksiC");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setAddress1("c3VEYy30eTx8eZBx5WSVxe4sop1ICD6BNiWDRWtDV3JmLwKUuJ");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setLastName("JcxTWvCqATQ3hb5m6PecNiFbio1Zs3OdBe5aRtGTjLx5naEX6V");
            corecontacts.setNativeTitle("u5IrQpzsz9gq77hW3JbWUOFiQUkuq6wJh90Ky2qbltkHCiW3ve");
            corecontacts.setVersionId(1);
            corecontacts.setNativeLastName("c3w25yeFxT4JJZkdAgkWS6o2PC1Mf7aY0e3LSJzGKs2U1XSj9R");
            corecontacts.setMiddleName("uGSpBzvXkUNxvBh7pqN9iQRYsp8xDmrzwyHlONi9cf4zhRSRgX");
            corecontacts.setEmailId("kBwBiPxO4BSarFfnqa0B8CHJV7xCt9nbEwn8M5tOGMKuEsl6Jc");
            corecontacts.setPhoneNumber("uq4ox9BJb8K9yDg16tBs");
            corecontacts.setAge(10);
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1458641438371l));
            corecontacts.setFirstName("PTStMOuSKZRZ0504WtRX42Rn0b0ewpIDTWluAlF91A4oTvaGVx");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1458641438382l));
            corecontacts.setNativeFirstName("sTDeKhVDdzDbphRWJ3zJTGwrqwyX4kts82eIpuVvfdmK6sVhcQ");
            corecontacts.setNativeMiddleName("Id8OXCPikzEkoCE3plAL2VFEkG8O9FlNLwZTwvvr5sp6NuDWv4");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "QI43tzCTd0wYu4WLYNMMRjlGna7EI3EUrbyl15CsFr1gmW0nHF0jPnP2yHP4Q8aNxzSaKcAK6pS5jMrxKl3AWhDgcpms9D3Q1T9JO1Szuq3TeEjocl5yhSXV9QH8CulclNZOAofrHSeE20mHBwZIkvjNzgNAAqAaUroMoKbZNWbgRSGHC5qCmgnwbGCdmd6iwrJv3XI49nBv2cb2gA45ZCXtTfIHZcba5OqQEll3jnIjAyYrglvVWvJTPTXb2kHDf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "fDbxYZy3eLvQ5Qyjy3EfJLJjdjZAtYk1CIztVJiNB4dR5101282AYf0jbH5DoWeHxkshGu9V0pIObuRxmzS2oZnILc0Q6ciqJ9K2LUCsZdMDaBaHkDC7ndtQ0iUQzkIu8f5REbZmjnuHeZtZsopPeVM1o5wnJSIPuHvMEoLtnCi16PaJLHnrm5qpGNVWvXtySVqOiIDVP9Q8YNGQSt0wWFoKjytAUUMXBggJ1GGWjB8HTKSK5LWIcQvwZNyMkf4an"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "eb9ZJbZSzAowDlL2NsvEMA4AicjFzNHI3liEPbkOa2sanArj9gFAx9gklHGMheGlDMeFn1HQVeCgBee9NV5N61OKNl0kMb7NOQuZqIEZG6xOn8x1CHCkitS6EsP9xEs2dt27WJjlv6c8fqv9s4uEnMyw4GywcEiGAAn7u7PQeG86CmYz7AH6Uu1kCz6alB3uGxOFLrSSmCTDht5SSWEnSVPWUOstL9h0VFKGb3ggYMohnq515cTk0YeIqehUdrUE1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "ji4liLITMUPxCksgp8UhU85pnnjaUqtZRAWsGvYPZiknfjVqyHRbsJTn4zA9vmJno"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "LZgykE4vhIWehGRD9SwV6Cg3oBApIeThivu2JWcnlDIe8SZgI4lXLLf158q2SMfGRXZdssKixyI8iMZzVlaaEse699IMOt9ZBF1MLvMMG2DODgLcNbJZFP1h6pIi35akyInUWVu0l9FXh4v7tLylgIlQtrJiPpnb9xQ21wDz9IPZZ0VQfOgXoJojRfcinPCEUO4Ft3xX84O9ljQhCXsFfKGsdrl9aB3v93e4UsY0XBxOdghOSeDqRcTRAV8D9gwnl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "pP2ibtur7e27lbwpjzqQPuMYVgNlS3BiNiAhbqplts7eHkOXNDWin3Riv9T2xZZ9JA9jjPz06yeUeDAuFAuNrAKjk5BdLFuUw5aZLNeKuuzIg77TDkzFVsDeKM2sUc7SzLqFlk3cY72TwGGyP2IVBliguoOvSgegmTI4cyPGWWK6KIZqsxCfIpS3r6V6gH2y3Ak5XHWeQ2rmpzJu9xOFlf34zcMMGbvpv35tWQ1maXofhnoZah38Ctwlh2C18fhs3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "w3kxDaGzB1aYbOyU42Mt5zo6NL0KyyeV2J97ek9OSDf64zpu42mtsR3m8pix1Ko62VkUgfFkSIaZgnFWmq5jokM1hPwem1GK6kQKV8ZCW5GooY3nb8a1FHiKnJHJPFXdN5E8wDm6pLxQFCIXNbexpFBMbLBVwkjXiP2hdisPjzu8ij7kXpVgPOuFGsFIfWC0qJ5TyxKl9wWKzXwWmFzMB2gVi4dO5nYcxCYqXGuV7p510GsVAhfFHbsBOmZ2NlTtE"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 164));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "jnxG10muTbjXiUduSgeyp1KV6W8BwkdGFasCUH2VNhsjTvnnm9OsAPCNXGzJ8DKFX3jpdwWfeVDMk6KwWCls6danPAOT41Zyoi5QQjsJFLVwTXGakeTGaub4DxQ3cPUodCDP3x8TRlaW7HJtDFIU7Y1cMZMshEGHOtt2gLcdq5QIZ5pxqbtsVMvORCTuivYieNV6SgCaLNSwT08h4e1MnSb77jnLfyAWvwErT5UcsDKn6LE0Ie6v5vHvjGy036cTW"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "VS7MjXPuOZnNfg3x944OP"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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

package mobproj.app.server.service.organizationboundedcontext.contacts;
import mobproj.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import mobproj.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import mobproj.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
import mobproj.app.shared.organizationboundedcontext.contacts.CoreContacts;
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
import mobproj.app.shared.organizationboundedcontext.contacts.Gender;
import mobproj.app.server.repository.organizationboundedcontext.contacts.GenderRepository;
import mobproj.app.shared.organizationboundedcontext.location.Timezone;
import mobproj.app.server.repository.organizationboundedcontext.location.TimezoneRepository;
import mobproj.app.shared.organizationboundedcontext.location.Language;
import mobproj.app.server.repository.organizationboundedcontext.location.LanguageRepository;
import mobproj.app.shared.organizationboundedcontext.contacts.Title;
import mobproj.app.server.repository.organizationboundedcontext.contacts.TitleRepository;
import mobproj.app.shared.organizationboundedcontext.contacts.CommunicationData;
import mobproj.app.shared.organizationboundedcontext.contacts.CommunicationGroup;
import mobproj.app.server.repository.organizationboundedcontext.contacts.CommunicationGroupRepository;
import mobproj.app.shared.organizationboundedcontext.contacts.CommunicationType;
import mobproj.app.server.repository.organizationboundedcontext.contacts.CommunicationTypeRepository;
import mobproj.app.shared.organizationboundedcontext.location.Address;
import mobproj.app.server.repository.organizationboundedcontext.location.AddressRepository;
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
        Gender gender = new Gender();
        gender.setGender("1Kf4xJniSCQbBTG0RQJ31bb0HS4kHbeZE9pKcYX6mmhMyaFCNE");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
        }
        map.put("GenderPrimaryKey", gender._getPrimarykey());
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(1);
        timezone.setCountry("T0b75HgBPENbxYnUNLJ95XMIojwGwZIEM5LszA4oHhyMOh6duE");
        timezone.setTimeZoneLabel("Dt1j3pn1qO09Pi2ZErHbxTmsfdhZ9A9qTw5h2LaRUiGkrvLQu0");
        timezone.setCities("YhS60iqB9ZnEW5X1S5gMPbEf4TnhgIVmqNMlu1iMt1MhLlJVQ2");
        timezone.setGmtLabel("j6RDfUDBOcgZ3NfvAMghOcaHR3lr5iibuVbMuBWBh8dkIkkcrB");
        Language language = new Language();
        language.setLanguageIcon("bJxIfBVV3LZgQqegKPsexStWBdYqm1Xe2iNzrHOpHmmEtKbA5X");
        language.setAlpha3("GNT");
        language.setLanguage("wm8TKl8XHWbVrjEIDKCnA8G0bq7PMY7iSG7oxR7tvriaXdu4D0");
        language.setAlpha2("w6");
        language.setAlpha4("uHMx");
        language.setAlpha4parentid(7);
        language.setLanguageDescription("hZjGvEofAPA0xs8nqMp0y6seWyZ6P9t3tpGQzMLTGMLW8AL4GN");
        language.setLanguageType("dlvdYaDwAMcx6KFt1ZjLH1Z02cN4hEPB");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
        }
        map.put("LanguagePrimaryKey", language._getPrimarykey());
        Title title = new Title();
        title.setTitles("FAUx853jSdXE55g4zb2yzJxbKSRyvghxlTi7phtQ9IPka5tB3t");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
        }
        map.put("TitlePrimaryKey", title._getPrimarykey());
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(8);
        corecontacts.setEmailId("6VOrB8kHEpHs8Cd75uZmeH4isyCHeuSDzfrPzzWk3gxOw9yg9E");
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1458729704070l));
        corecontacts.setFirstName("eJkVSVaYaLPZvU3phIpB5j7DSwYM8FudH13dPhD2hN9dbimFDj");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("sPtOBt6HwwhKX13lc09LB0hdW9dPLLL4Y4QZC5gbhOrq3ud6cP");
        corecontacts.setPhoneNumber("efVJk9LZSg1C8i0KITrz");
        corecontacts.setNativeTitle("XCn3FRHCbGtPtoz1kazrnZw17SINqrb7z16POCOfhTJ2rKxjXv");
        corecontacts.setMiddleName("5WX8TufTI3YOFqLNp3B4Z00eN1bcnNTLVIz6Rmmp9WBokHFw4V");
        corecontacts.setNativeLastName("m4kgidN2h28MZifXI4PhPoLEcRUdaktd4Ch2VbrsedrOPTPhtZ");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("Qqmv5dUWAzdUMOoDHcAbCQZVFSUR8igOniq4DyJLjZZTLbJemG");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1458729704196l));
        corecontacts.setNativeFirstName("frQylQNDlr49IxYCJBfE0CEaQ69bhPWCxcEYKMkggUTXMO136O");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("QEiOY3by3EX36ECuDLoNOJaemLNXigB25Cw2zOnrEoDn5QaSpm");
        communicationgroup.setCommGroupName("quFiiyCwNPsPcftEBJHTDkSUtIzFegBP1VVmUYezn6pKIKR1f8");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
        }
        map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("FZoU2hqPPQnt4Yvn1LBjD1sNMBPVF2GYP95LH9iHdpsStudkXO");
        communicationtype.setCommTypeName("mbTHL46fn44g7qeRLQIRHzBCQFpcG1m1xLJawmvf9v2zx9vxJ5");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("Sa7yvFUEpmIUBi8MUWsXHzZoXZlSUk3erYhov3rnjde49emuvb");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
        }
        map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLongitude("E2b5Pd9kIpZgRhklYYAHu2k4pcB6cwZjSKVIV6syuTtIiko4tu");
        City city = new City();
        city.setCityName("cG0Q9zAeSAxdIR9MHAteA8e7EoRnxYXAJUgplS7FgTXcCPM0qA");
        city.setCityFlag("3oz65z2YhdEsBQoKgbJGJCeiTEaRJzKyo6yHoYu5GfqNmzTWHH");
        State state = new State();
        Country country = new Country();
        country.setCurrencyCode("oQq");
        country.setCapitalLatitude(10);
        country.setCurrencyName("AvnFqZxssFGvmfgSWIDkeB1hr7zqjRdp6tE8IiokOzfH0TIQay");
        country.setCountryName("25ddSb7xSlrOPIX5mCMcoMAJcuet1AWSmcd7wUoOl1GfQlQgsD");
        country.setCountryCode2("chJ");
        country.setCountryCode1("5mB");
        country.setCountryFlag("AqcQK1c4M9XlFKImWwsqtMsxZZXahjVd7tuG3hVdHCAK5VCeWa");
        country.setIsoNumeric(7);
        country.setCapitalLongitude(11);
        country.setCurrencySymbol("p0hxe1jCmPEUcHZ1eb2xuOmpP0aqvtFX");
        country.setCapital("yzKhsg3SVGeprqnKWHPMKbv5XlCoIDqf");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("1AzsOQPOpP3UqTH8AKfE3PK83ip5hhQ8eLnT9Z688ujicpnjs1");
        state.setStateCode(2);
        state.setStateCapitalLongitude(1);
        state.setStateFlag("usVHoFNlPjMOCTOaepY4Dc6iSC844C4bvu9wyz0m0wv0QwWgWv");
        state.setStateCapitalLatitude(3);
        state.setStateCodeChar3("Yh8YESYEqfxtGkAeMbGNdXy9PeXXQjN4");
        state.setStateCodeChar2("nQqY0TL9AviscWNxioGwzbs7HlwkSbJs");
        state.setStateCapital("fyhTthWLNcRGN3z0zlXsEiQm02yllEj0BYxJmstiy4jMHvILwl");
        state.setStateName("Ey8K72kGfPtiGlq3RivAz3dUeaI5H9ajoqDSvsTnjmTY6nCwMU");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
        }
        map.put("StatePrimaryKey", state._getPrimarykey());
        city.setCityName("MBShkTuH7QcZXXJHisvPKocqzp4nTLJJCbrWmQpz2g1wDHjIc9");
        city.setCityFlag("njxeapdn19o9XmfDU0Zdp9xg2ofntHIhJW91mslV9lmnLm2dxp");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("AZ4NliWdTPUTHSLw9cn85FnAIbuft17ZthzSPCUrX592EzaRJS");
        city.setCityLatitude(2);
        city.setCityCodeChar2("xzGDXxptzBqdD0F0ONtQe76Ocj4u1ZpB");
        city.setCityLongitude(2);
        city.setCityCode(1);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
        }
        map.put("CityPrimaryKey", city._getPrimarykey());
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("uzcBSVYpY1MBCEFIEqJsFRMqF5xnTFLmI7mWbldapdQbpv8QSi");
        addresstype.setAddressType("aotjEFO1RmLphxceMxkRV1ZPDQVZ3AQuV2BWZcbsK3r5n1oVzS");
        addresstype.setAddressTypeDesc("zrvp9MkBK52CvRav26B71MNX2hxOyx0dv6F5chSOMXcUlPS187");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
        }
        map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        address.setLongitude("6Vx9Fvb3rqX92raOL2YjnjCygoydSQ2XNF2qXgfmsumJrSfXDD");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("CQAKzp");
        address.setAddress2("zWPsswDWynu5qatHQC8fdWpLQZ3Dip9zHA9M7RijgKevybPQnP");
        address.setAddress1("dEh5Cgu2i9xzLRIt5REhVLTvaSxXDzaUwPHu3XXJ3VrosRVQNO");
        address.setAddressLabel("iQjsDDG3TEh");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("Ai6O7ec4bwoCsKU67bWG7rNzHG8ao4JaN18Dvu13WvxPLXUrzs");
        address.setLatitude("MUptvs4PGeGKf1ThelkEDNjl8OiX2k0DYCUzR2UismKoyl653Y");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
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
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setAge(115);
            corecontacts.setEmailId("2PJ3n9E3cW0EJHQQwzQ0PM37bPVYQSS1L3ubdtaKcZf1xDa82a");
            corecontacts.setVersionId(1);
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1458729704893l));
            corecontacts.setFirstName("UtoCkWLpYTqzSsCv3v6o6uHTnP1SfXLITrQHXi4Psnpt1yhmJg");
            corecontacts.setLastName("iamhh5lo0kp0HLQPDOsd4lwOEn1SOonY3aWdLhu4cEvYc3idsq");
            corecontacts.setPhoneNumber("guuVBKkmrpolTlk0uNQk");
            corecontacts.setNativeTitle("IwDibOxBAmoa9KBcwsJVQgbVpEqHpunseHJ5lounoLjJ91eMcN");
            corecontacts.setMiddleName("VHA4BS9UOd9Ky6PtAtjIEINQaEIpnoeuSJX8FS8khqmqqS2Ezf");
            corecontacts.setNativeLastName("hjNImEiFO1kjP5l2WWxTSiL9Gc8SKJX8o2NHIzlSxRVxtWaSZp");
            corecontacts.setNativeMiddleName("hbS63SZ0aS8tIvkxnBI7qUfxpWnU9SS3YArFgZOZYe9dnjbq7x");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1458729705010l));
            corecontacts.setNativeFirstName("UeqZFCBws85Wx0gwKeYpdZMmhdyPX3Lj1welFnGDkvd12h1TYv");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
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
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey"));
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "1fM9Wi9zAsaDpCEjfIro0HKr87vcubpBajBv5gyOStH1cgWSBDDbj8QWk5w2Ny44BhuV9IKJjPbzU5cmwmDvvuoHKSyTCtnOusQGrOmlkBKd3j8Pn9AeFN15ZZjf7401rMPvdU0mBg7YODLRZRqP5w6MNbl8lmLfgzhgv8vYcKnMPpOdbPH1Ls90FNG89m7w3wotawrFMyO1MGaEvKB2ql8gCYKyYeW3i52NqLmWfqV9YvMpsdmYgW5pXZS1yFizr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "pfQ3F5QHj92L45qfZVbjjxlExG078BNk2u7p96UMRC6Z7V1upIganVZxJK5x75bdAfiLS3nJZCdvWTJK4EppQ1uxoUjZGNpEUK1R27PVkrT1jevoNyq2bHgaT930XP8EmB1TylupxfIJ01zvk5wSwnvaX3et2SaNq0TYlxwXTVsZvF07CLw13q2t23TSxtchTCheX1GijBVY4W4hTDLngRYMHG9H07vFWzPJkANvq1woLp41shIo3MH4QaZ8kt1yK"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "Pewma2YJTFzJHFSrnTC2voYK3wzC9HAmGqjJXIa3S4CBr77fjaqZdSE7shnQcPyUiqQTfBidCwnHaWorW3RqCbF8VIQPgjXA1SKaN5wCMERQfDj0M8fZnbKeqyiozg27oIRndqp4vcABMRsqkOEMxlw6vpGNZxixhjrLePuPMY6GiynzmTwye4jla7YfYJPY414wz5hYSFBE0V8qWCGX9Ap4hTvoDpHDa9hFfnF7NZJt8yfQlDGuthsiDITWwW3og"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "W0saX39SSXdXki0MU45DEomugO1LvOUeg5KxpJlVBvaPUdnLwXJ0Q75qnZZKblC5L"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "Td6iwpCL6i1kQTTbVWJeBagqCKIsVdVPokHLhSZWkjVcBqViAbw2vgjLPELRSHcxcPoN5KTgl9zlqFDmGno6VhN0gHW7RmdeCY9qUQsGAawc0vWOEqRLPYt2tV8UnBIquNTynREEpmg5pXupsW7kYFLqVW8oQnyB8AAMbyxHslWFnJweopKOnZjOZqXi0rEtzBbriWlXBNqftKezbPhTVwXqEbt9ekhdZi7NJMvC3OqhgjQotmJzT3AlgBpAqyeym"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "X10F7mbGlFBTvZ3mqRbns4lbFZnsnFK7cy5CVoTWMXaaSTMH4kmDZhk9NVEAetncxOhv7GEo8N3fEKzct1qYV3LXe3WUAQCxo80F9BmSfNCAGaIf9vvPJ4B8XCnCu24p2L5GkxI4qAsuppOpLqYr0hGpMQIigD14nIb9EHQ7LlAZuV5I0PipHo8KuFGarGpYWiwVEiSH6E6QS4Vo8353hhKf0VPqzHyV3zGtSs5qLc25frdcUd0i9QII80mQY71h5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "su7TXyL3EvLvUT24iW83tvqaV1El7H9uuhk91tWjHQ9JxXCG03c1aR5GopSu6OMCYnBzMshQWVV6JXTaWVfO3ktuyr5VPEaL5JEKVe2LkIeHvhxivZ5iPN7Q4Lqtmc9wtM1BYPeovGD0N1c6QgkY2jbEr9bzGRBOh2V7vT9PGURuwCgE86C6Y0HkWQryjb8lQtKnSbZm0bWRuuUAkqTB8IqCKnejLKfLhE4KiGPa2V38yBgmcTVpuYGDjyxmzClhr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 192));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "gHe3CrsLsVpyLwKTnEbjiN1u4DxXobi23TJi9g6DVmXTFniEeIC1JK74CD5mvBMX86c6qi1DPxzioLwfHfr4tJt10UcXLR11sW3kLTR8dtCVycc4vpGQM0WdKmyKEt1XXnsXLD6TxCfCuLHY4gzWJPkZf3a97QBq5cQdvwvxR5D5kwpnyAQYZLBhykMxgBxtr3JnP5sfUNpKIz1CYgZjE470BMN8bNhT4M6FVuoBuSQijNlIj2xX8CQ9BGceha02w"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "aKpZ07OsdXxp6oxGGVS46"));
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

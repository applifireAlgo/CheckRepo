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
import mobproj.app.server.repository.aaaboundedcontext.authentication.LoginRepository;
import mobproj.app.shared.aaaboundedcontext.authentication.Login;
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
import mobproj.app.shared.organizationboundedcontext.contacts.CoreContacts;
import mobproj.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
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
import mobproj.app.shared.aaaboundedcontext.authentication.User;
import mobproj.app.server.repository.aaaboundedcontext.authentication.UserRepository;
import mobproj.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
import mobproj.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import mobproj.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
import mobproj.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import mobproj.app.shared.aaaboundedcontext.authentication.PassRecovery;
import mobproj.app.shared.aaaboundedcontext.authentication.Question;
import mobproj.app.server.repository.aaaboundedcontext.authentication.QuestionRepository;
import mobproj.app.shared.aaaboundedcontext.authentication.UserData;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        CoreContacts corecontacts = new CoreContacts();
        Gender gender = new Gender();
        gender.setGender("ikOakOvELFSFmb4UV8EMgizU9JLh2CufaPaqJ25U0gaDWcTeX7");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
        }
        map.put("GenderPrimaryKey", gender._getPrimarykey());
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(4);
        timezone.setCountry("sMUlrH6DoywTnzOqB0Jax3vjdngpql2ICiY1owgNwWmJozIjiZ");
        timezone.setTimeZoneLabel("ODTxieT1iMtjBs0LvKHSqdX7dXAlOu0lGODwAOBCzoxDenZptu");
        timezone.setCities("7904cVtvi2U7Mv9pJHuFcUrWX9hy896eJgvjPYGX7TAPSJkNAS");
        timezone.setGmtLabel("yQcNU8Ng301CbComrnZ25LQCedG5e2W5fvNIfOqpJQww2gKDxC");
        Language language = new Language();
        language.setLanguageIcon("uYC4MzUZ7uLxQ6PJJJBos53onAf0uA0JTfYfbVsBQc0W3yQ67R");
        language.setAlpha3("MeN");
        language.setLanguage("dbnnmjOmv95LshN73PEZmK0bOeSVfLZ9Wy5XXS2FoEDdHRK4xL");
        language.setAlpha2("U0");
        language.setAlpha4("cDAo");
        language.setAlpha4parentid(1);
        language.setLanguageDescription("4LR37AY072S7TzU1rNJdPu8jegdL0w7Ahzzawyzf3fp189koyB");
        language.setLanguageType("YX1MtVe9YRqONdpZYCq0bp7aGYE2T0M8");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
        }
        map.put("LanguagePrimaryKey", language._getPrimarykey());
        Title title = new Title();
        title.setTitles("M5MXUlADbn34Ce4lulO8LQAZ9d1V9oUKmB0zdHkKIpv07fIcaK");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
        }
        map.put("TitlePrimaryKey", title._getPrimarykey());
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(87);
        corecontacts.setEmailId("CFWYvF2xsBGBJnRGlbSrHJVEtKDDTTo1uabWtwe9o2mVUkyt0o");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1458729713321l));
        corecontacts.setFirstName("oDvTbiIycOyfv0RYSF4IxYRgTPdeIZowfYfD1Uruqr8XKVXIHp");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("1hfyr2xQAsWOOf0xUDaJXZS1loenO3wO6xyMAJiQM88lDSVhRU");
        corecontacts.setPhoneNumber("qyNBvxTPXZktLHNKArCv");
        corecontacts.setNativeTitle("iqZTKcfcxliLjvBCcZkj9fFI7xGr0cgWoeK1666V3wpnxDitcU");
        corecontacts.setMiddleName("P13qpLN5Yu4gixPiddxfPTI0cYq2Twxd7mdgjaVFUbbYP1gfCU");
        corecontacts.setNativeLastName("ZiObrVfSSuuciCuDsQgPWbeWJkUs2pKtrUeNRbbKz3mCJUUyMe");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("S3HYwdyndKIiw3APHA287w2iPLY4uowtI1WO0DCmUhUiWIewU4");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1458729713382l));
        corecontacts.setNativeFirstName("Zmf0kbu2IYBYfiE46cpIFdLxBMA3OLEueikCKHTr1ULbYumv3P");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("ZPPYrNts8TeZKZF6SBvzg17pY21qn3cU0AHHnxEuYnEzJK1Hwk");
        communicationgroup.setCommGroupName("mrafx3o4l1zUDc6w4kwHFrKIvDA0nhqm9nQya2zjsy9JdrxLQI");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
        }
        map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("nBZ3sh8vGj34Nyw5TnOYicTJux35h7e0dF8NkPiuoAtlnKyafm");
        communicationtype.setCommTypeName("p2OETM1C06jlSPlGKXsGxZVBubtZ8MIaUwDkEQJuJHlGZlxUwF");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("eBlvPynnJ8Q9X3fE254o7bBrQAM9sdbab7p3NmT4wObwxZCPUh");
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
        address.setLongitude("cfCV4PXGNaA7tY3FB1d77NxwiszbSzpJHIVhaJ0bLgIZwlPCqB");
        City city = new City();
        city.setCityName("hXMsMnDiuru6GY8kekG1wdlZyIaleLK1y00ancgZHpQdQnpb8o");
        city.setCityFlag("aOepGavDA8ShdNm8gKZJYXtPXyvDmrQ8MmaPoxXVP77hDG4pdf");
        State state = new State();
        Country country = new Country();
        country.setCurrencyCode("04L");
        country.setCapitalLatitude(6);
        country.setCurrencyName("otqYbdJ0vkJZLKDjSirGeT4xo0Ww3uLFod8iZRIPzzEvVcvus6");
        country.setCountryName("GjzoHfppqNyb84TKSKg8ltFPrTiC2N3R2yYrFrQDJsd6cQrcGz");
        country.setCountryCode2("iSC");
        country.setCountryCode1("n49");
        country.setCountryFlag("zcvn27wL3qTRndOq2w3gtRntLL5yLXqeFLYlf8qLbOMrHcyjJE");
        country.setIsoNumeric(9);
        country.setCapitalLongitude(2);
        country.setCurrencySymbol("J5No928NtyXkkH4np0dFNGebdEH25jvG");
        country.setCapital("ThgmvOLTGXFmaWcngaFKw2p4Q8XbRcdD");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("J5FTxaJIZtvnOBiUN6lDReL2WzSp1YDzwNUc7ofkck3xf29fbJ");
        state.setStateCode(1);
        state.setStateCapitalLongitude(7);
        state.setStateFlag("trayIOpZ4Qy4SXanlNaAKApJbAZodJCtuEfZvXXMnRYnvBbOnJ");
        state.setStateCapitalLatitude(6);
        state.setStateCodeChar3("ECK8SrX6w9LpkuwhUCSeAxIEzYhK3py6");
        state.setStateCodeChar2("IOYHfQsnzH6NFKtj0VgiKOFsKviFc8wn");
        state.setStateCapital("JAZZpeaHjenAz0X8FGMtAWO9fi67irKylpFxcsP0c38Qg7FAm6");
        state.setStateName("xgaJCS3KMDFvahfZX87iGEUsxqHfdZKMlJ4ztrfKIGGsu2Xvxt");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
        }
        map.put("StatePrimaryKey", state._getPrimarykey());
        city.setCityName("QQ6Pa4rUGB0vGFE3CKUxM6g5iqcug0q2BhFRLykRnEuvnrqClH");
        city.setCityFlag("oQjq71uoJlI3xKdZCU1RoclVfDIibNEQj8RxcefV0JrcS2cmgg");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("vDLBTe8ESbTZCiqJaZPTxCm5lBZf9CITJBPH07IpbBs6qpUmoj");
        city.setCityLatitude(2);
        city.setCityCodeChar2("Fa0WMLqXri1y7gNg8q6Eb7gfwmzlu7JI");
        city.setCityLongitude(2);
        city.setCityCode(3);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
        }
        map.put("CityPrimaryKey", city._getPrimarykey());
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("uvxuv8LkUIn6FEmfgd4TredhbIxKHr7zH0clFrphBBSehnxVjG");
        addresstype.setAddressType("VxqNqAbEbtVzOH758ORAyQSGf19mHvcaQ4IFbhPzQQjtID5mjV");
        addresstype.setAddressTypeDesc("l7LIx5Kse5Aaq0Dh66YoH8aODPf4ru1XUExGoNQ4RZEwBXKgZn");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
        }
        map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        address.setLongitude("cGuVmaNuSHAE6cRPBhJDDagSixi3EQP2QWOI0Pqfc4yPtwfpJz");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("XMU7pZ");
        address.setAddress2("6KqzV37c2fAItilfFMz97jqZdExJJCOEv6pOYIpi4Bwghl11b4");
        address.setAddress1("mVMqcxenh0qWu7INuU8mryMK5I56KQAqVLwwPR6ki7IjdyrC5T");
        address.setAddressLabel("asDOli8FPHL");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("Ve6080NXJlMNBFXKySB9O70kLLCAqfGvxnwQggKCw6RzxG3U35");
        address.setLatitude("GfTawOH7cAhhKI9jBB4kOCh1PSRE8DezlguQbPlQyL1IKcYxIT");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        User user = new User();
        user.setAllowMultipleLogin(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1458729713726l));
        user.setIsDeleted(1);
        user.setPasswordAlgo("ReP4mrEBj5Ei0rZCj1WRI5gC2ypY6SS7i4jlmxKlJFIpVX9o7i");
        user.setGenTempOneTimePassword(1);
        user.setMultiFactorAuthEnabled(1);
        user.setSessionTimeout(1927);
        user.setIsLocked(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("WsMn5i3OHCBJJKJzjYDlavG3y85jeZ0dYkmM5mAayKaCuZDQ8A");
        useraccessdomain.setDomainDescription("43yab5poeZjxo3XSX2xPrm70Ih8QVbjao1i8tVe2UssutbBIXt");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("YxxdDjlepAs9gAOPpJ6X1bpPl1bLZ0oEG7qaBjCGf78dYgekvL");
        useraccessdomain.setDomainIcon("a6XBncvVSZsiFj6QpxIT8ZAK3QP8aJfPaJiX9JA3wJov1Q4FqJ");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
        }
        map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("Jg5nHgP6NBLVDnZ8htFeSXfPDjoa5nS5fQpnshe6v7hy3qxap5");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("nPzwWEYu6RHFO9jPHWcpOqtgIehHlUnDaZtJImIa0xDWawukZV");
        useraccesslevel.setLevelHelp("X7yrJkHtUZhRtQey6DZfILOK86OXCZtNFzuiU9hfSZ5b1iFWoX");
        useraccesslevel.setLevelDescription("tI2BCTu0R5TMYJpghlRoSJgIPXEhhO1XGCxzNLb9cM83haBFKj");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
        }
        map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        user.setAllowMultipleLogin(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1458729713743l));
        user.setIsDeleted(1);
        user.setPasswordAlgo("pIzNJrtxl39QYz5dMWJCrLHGLMzKdFkICpjoG3dDNGhP8hiZ9h");
        user.setGenTempOneTimePassword(1);
        user.setMultiFactorAuthEnabled(1);
        user.setSessionTimeout(3249);
        user.setIsLocked(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordExpiryDate(new java.sql.Timestamp(1458729713771l));
        user.setUserAccessCode(7939);
        user.setChangePasswordNextLogin(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("JXououNubJmqp0c7VTyFHDnXTiS9K617FiUAiqnGQcqne5HiN3");
        Question question = new Question();
        question.setLevelid(3);
        question.setQuestionIcon("iRnoxFaaQrsi4Jw9z9XxhuKKNlBso4ZejvNphck8K31kVW9KEw");
        question.setQuestion("x5Tn6NhzX7B5e1flmfQ5IPjErxJam9yzIMAZSCtWj5lHR6Tj8u");
        question.setQuestionDetails("KpT2wvWWqd");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
        }
        map.put("QuestionPrimaryKey", question._getPrimarykey());
        passrecovery.setAnswer("dve3nFNHTBs6tU6hQFkzCWPRmHP7mgVxOgqDcc3exkdHqWdY8v");
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("5CBeqZIwDQCCgNaMqFNUSUWISSPzEPGV");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1458729713952l));
        userdata.setOneTimePassword("w9a0KL7C9q9QEe2SojNjDiypPZ7PZRzY");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1458729713969l));
        userdata.setUser(user);
        userdata.setLast5Passwords("ADs6hMeq2sc9Beu3Ti6sTvdNXlpIYHbLSR3jPDu8rcgKN4w4OF");
        userdata.setOneTimePasswordExpiry(7);
        userdata.setPassword("qfacv2gK7IKcFsItUOzB3AwK22dRLhtcq6lvXmkwhC3QEJkkPy");
        user.setUserData(userdata);
        Login login = new Login();
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setFailedLoginAttempts(2);
        login.setLoginId("6Gn3pX2oAy9p5voMjntlIu7IeVZ1NcuEx7NxTkQQLVz16Fgfya");
        login.setServerAuthText("hobg5MA3mBnusBd7");
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthImage("IBgwqNq0lVo0aXxWvWj3oQtjv9ZYrLqg");
        login.setIsAuthenticated(true);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setFailedLoginAttempts(3);
            login.setLoginId("oLBnbtcoujCXomLukCTD34n7l8F5BrHMlAFBdgCZJWAiQdb6td");
            login.setServerAuthText("SLBpXIa8nn1OWRCL");
            login.setVersionId(1);
            login.setServerAuthImage("BHnx2EwN7AQz8i9v9fs1FxpoLIZKF6sk");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
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

    private void validateLogin(EntityTestCriteria contraints, Login login) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "cUEGJGHY4nutaX68zBZG4aMLyajB6xFOyTSFOAx7KtrtplaoPDShfwJD2lAo7AEiM39jXGdCWqRD1ysXdojKKujv3zfsmqoG1m4MFFfcb0mzqb5Fzarr4JvsoQu6XJnHQBviN2IvSIXvD4piqsXeQPlOZIXUw3Dg349CrY3h5W1s1q4qHRBzObNgo4h31un9I3oyoe47i"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "tqcQt1vdyJdzDKx6sgCQxMZUQCympt0Qy"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "6qx4F3oWlXzpdlAmC"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = login.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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

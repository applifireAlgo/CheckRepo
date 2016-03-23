package com.app.server.service.aaaboundedcontext.authentication;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authentication.LoginRepository;
import com.app.shared.aaaboundedcontext.authentication.Login;
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
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
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
import com.app.shared.aaaboundedcontext.authentication.User;
import com.app.server.repository.aaaboundedcontext.authentication.UserRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.PassRecovery;
import com.app.shared.aaaboundedcontext.authentication.Question;
import com.app.server.repository.aaaboundedcontext.authentication.QuestionRepository;
import com.app.shared.aaaboundedcontext.authentication.UserData;
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
        corecontacts.setLastName("zfZE9WncIZUm3jV9FIJgMlIsJmVnavKhkwSdkqlc1uKlOPUQbi");
        corecontacts.setNativeTitle("kMPBlkYD47jWT2u9FsqV3dxRRk3U2yxFOh0Ikgihs4UlaNdd4L");
        corecontacts.setNativeLastName("aMeQZgaCe7QiAMjdGrHvAB84IASeJZlF27oxbEf1b3hT5a1Uj4");
        corecontacts.setMiddleName("nZxegnYKRd36QBy3lEsXmnOy25j5YwJEB4pzwTpFIzDDh7ykoQ");
        corecontacts.setEmailId("h0RlO3eggcHjH3WgbpqNSg8V51vnRADcSNGDMpK72oEQV9hHU9");
        Language language = new Language();
        language.setAlpha3("Bj1");
        language.setLanguageIcon("aajAONtEy2536QBneAfUdZMsUOF6ymYmETwUo9aHbSOmw4vuvh");
        language.setLanguageDescription("m4iCfaAsqynm9JDa0FRQYekSFSpWRWaWqty6uPg4KWmkK2HGXF");
        language.setAlpha4("J52W");
        language.setLanguage("FXi9ejRo5ePr7IzR8QEwQ06oU9ZxJGH6vrwz9boibtvYO8UpUU");
        language.setAlpha2("2O");
        language.setLanguageType("RoN0ePJgr3nyXr6a6EcjLkL4diAa6Db9");
        language.setAlpha4parentid(6);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
        }
        map.put("LanguagePrimaryKey", language._getPrimarykey());
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("HygHBNQvDBrIXGl1eBahRKZFS3XbodH7MmT6P8PZqUiktwuzek");
        timezone.setUtcdifference(1);
        timezone.setCities("WXG9cxTjstjS5vUBmRlFlUhUAzbIOqqZ4BEUK3qdPgSGVCB27Z");
        timezone.setGmtLabel("eNOz0RaEHp6X8cGVDs1ZbjfFQ2Gzj85VKGYeBuiRdgS7mxiMfF");
        timezone.setCountry("UBLxELJXihujvSHYE3dSg2sduyTzMOoy5NkXZHheGYMJjhksjg");
        Gender gender = new Gender();
        gender.setGender("qTTj3S3rWqI3Dq09CX8zLrGAmLI8L2MGlnyYjJcB8QLhgiqdp1");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
        }
        map.put("GenderPrimaryKey", gender._getPrimarykey());
        Title title = new Title();
        title.setTitles("vNS6QLymt1KhjPkAYLCf2iM7Hdn81qP3rLENvCjxEkAE4aeFkQ");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
        }
        map.put("TitlePrimaryKey", title._getPrimarykey());
        corecontacts.setLastName("CevR48s3egfRVGc90XVOkXhcvHL4mja0jMkdz03IOWsTDx2Gz0");
        corecontacts.setNativeTitle("FdR8PEqcFSK1K2jFe00DOjhxDuPlU6jvoyR0pNp66zECF5ERsH");
        corecontacts.setNativeLastName("EqslFByocFpOCc09USQfTWVHeGXgvfQVxMKWPUi7Po6cBd9SCn");
        corecontacts.setMiddleName("YbcabOVTlQg2BEGbNXvoHVHoCMEFI5WCMSb6uk3xyKUgm68xpQ");
        corecontacts.setEmailId("rJ30Vy11mcKm9pa3dCeBrSOCEGapv3vOlj6CAUQYkTRbcyRFPt");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        corecontacts.setPhoneNumber("68ejn5PxcrkLwm8Na5Xk");
        corecontacts.setAge(60);
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1458641444906l));
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("0sgBe0hmUCOJoQ13KdBztTGqtiF8ORSqZ2SzId7ET7wsnTPbWE");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1458641444927l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("VrOaTOuQEU79uraxm6IUk2XoqSIocdiDdXNnAJ9BC1h2AGPBIs");
        corecontacts.setNativeMiddleName("Xb0Ttxq7SsCj217xFECYEDU0VuB04iVtKoA7mZsz8UEL181yhf");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("rzIUhv3Ge1JEPxuAL5yG8N8Jt0mM0IUXnYaNrwsQPZtjD5WBKu");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("ZK8MOTGrQKlacaSZ7XCp69AGpjwqsFFsoK1LhjAuk2qHra9RfB");
        communicationgroup.setCommGroupDescription("FNUDnEqoiDrDD3E9ppFT5RLnpWIfzuGrTXOA3ACR6M9OwOiaB5");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
        }
        map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        communicationtype.setCommTypeDescription("lUnjlE6mrf2KwnLveC5vvJhJgEbiCqGlieL86ZUsGsbcrBxxmL");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("JjhDbgNibBL6CrHg7NNwXXMQoJx9omgaRC4PWgcYZYxHTKinIg");
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
        address.setLongitude("Mo59qi6SRtqrsibMz8YaiIU7pit7i1QcW8sUzrqqgNYA542Q04");
        address.setAddressLabel("HCPKpE65rhD");
        address.setAddress3("RjynDEnwPRgY3oPuJPH0D7BxcW7XEg3GFmlIwJXnlgs6XWw6D7");
        address.setAddress2("af1facZIGIIyX0re4ulzPMQ4CWM8WVTzhdn7MtV9ZpFDWQSUPj");
        address.setZipcode("WWPKWr");
        City city = new City();
        city.setCityCode(3);
        city.setCityName("ulLF8ik0pOJ83iO4fR3B5Gpg8kHo5HbanBaZbbRr14PB0dJVh7");
        Country country = new Country();
        country.setCountryCode1("j3G");
        country.setIsoNumeric(4);
        country.setCurrencySymbol("QUKxLw4djghGyXsfTLM4i8CpSDHSqjb2");
        country.setCurrencyCode("2he");
        country.setCountryName("SGc3LjYxy9LNTTfsAyTl2xrjI5hbYRyeomH67cOIan4lGeVf01");
        country.setCountryFlag("Hd7CELxXPfx9MmGf06l1uI0iDXLawWuToHH8k9LJnmvZpBIV7W");
        country.setCapitalLatitude(10);
        country.setCountryCode2("f0P");
        country.setCurrencyName("JMpk65M33iIIjUnHF4McqA4kZYgerxUP33CVaLjd5nHts2r7hh");
        country.setCapital("ZzDKkFEDtvTNeneAGEr7nbeRM7f6cGIB");
        country.setCapitalLongitude(2);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        State state = new State();
        state.setStateCapital("mo3vXfGau2zRwkPDrAI1hL5CRSsLK7KYZ06zUvd5xPlAplYgZS");
        state.setStateName("vv0HkTr6brBEMqgmnWbYWeFJBr4qNhj4QcFosvYBJFjZzAMeiY");
        state.setStateCodeChar2("c8vQ6MBk3lQLpDOPl20KwyokQDY2DLAV");
        state.setStateFlag("tJ8jE9FnZ3DbbhBxw9WGFajcA2w3FbMYCwPVmkarFjoJHeSmom");
        state.setStateCode(2);
        state.setStateCapitalLatitude(7);
        state.setStateCapitalLongitude(4);
        state.setStateCodeChar3("uwgtddmx7cFuTT32i7KDQR1KA7wwBOoL");
        state.setStateDescription("xM5UmsUMAsH4WxUnmGkFgguhxbqJ9jF5h6KDxK86b8hZvdbPzg");
        state.setStateCapital("raFqnBVoX2DLYlOmMXLBdPO93199V5XZHjVNhXFZOJlwMwG8to");
        state.setStateName("dhKQNukIhnwgm0kRLw4No4p54hhExSc1On5y1QTaQ4YTONoInK");
        state.setStateCodeChar2("I1Cmmn2oSpL4FQZIZe86NR7vYo54uIZT");
        state.setStateFlag("PCKByRToKCXmz33lT9Om4XUiBouioNp1I2WfTWzM34Jg3cW4Yy");
        state.setStateCode(1);
        state.setStateCapitalLatitude(11);
        state.setStateCapitalLongitude(7);
        state.setStateCodeChar3("1VGUGMZlLqc3VLRsk9xWWWtI1qAlbdlY");
        state.setStateDescription("PGNMiaFqcSn2ekiISxFvxbTPf8QKnJGYh4g7ZmSJ8YDiyJa9HG");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
        }
        map.put("StatePrimaryKey", state._getPrimarykey());
        city.setCityCode(2);
        city.setCityName("IVm7Vkvw8YY5iZVqE7JuGIbFjGl0SJmNRNpoDZNmbw231olMAA");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("pzAU9nbDD18nPPM0a7ymR14Rnny4ItR3D6YlNiEZIWTzhUm14H");
        city.setCityCodeChar2("1F8jqrF19ZPNoUWt6sflUgzIKfhCqGa5");
        city.setCityLatitude(2);
        city.setCityDescription("N6gKH1jV4rUawj0YHKlFHDYHgDwnTCEmJLC0xQLWYx1TTOXMr3");
        city.setCityLongitude(7);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
        }
        map.put("CityPrimaryKey", city._getPrimarykey());
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("7l6yWFcRDFBKzotKWOTWN05F2DjoCD28ert6aPDgYxcylVXVLr");
        addresstype.setAddressType("CGF7nsoHYSkbQV0CQXunZ5zAvchenhKofzqlQlzkgGryFNGN0V");
        addresstype.setAddressTypeDesc("tPU74mEBALnEXoEwmku1jLtWZ5v3yBixv8Tmt1wGmRYZo7viJQ");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
        }
        map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        address.setLongitude("acZysNiVhPQWaeeWtdrEvOZsFjBhG6vx4zq6eV8nApHqR4vPXi");
        address.setAddressLabel("zW183fsDwmR");
        address.setAddress3("56Pl0WKdmUkLUGw18zdhvJzf13F8NqyYZBKZpsboofbrjbWDEH");
        address.setAddress2("naQT6swNXvbxXgCW7mkPJuoVVsQInkH8Q0NceAzncZ4BUrlzmg");
        address.setZipcode("QTNyJH");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("F9jRgSBTf4DgyB0LMbefTENXSraAISBeJpGa5r9xipdOSAHvNK");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("nVmSZi0gtuGWrNb57TS0Yim3264fbheVyCxAIcoHoBDt1JOEbo");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        User user = new User();
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1458641445209l));
        user.setChangePasswordNextLogin(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1458641445209l));
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("b2ErbK05UL1kcdNa8Arw0BCJhqoegdMIbrfMifYtQraVTAlZ4I");
        useraccesslevel.setLevelDescription("V5BKcvg3R5z8aSt862TbRv81BYObZTfhfm6j1JQrjysz5NT5TT");
        useraccesslevel.setLevelHelp("7TNNMd70ExUww0HWKJVVhMUWZfyShfBewn5XjLlcGyOKENbgQA");
        useraccesslevel.setLevelName("N6xkdC1HMbGwO8JNNiPfGIwV9z3z5Ehjb40mb09aemc40ORYHM");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
        }
        map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("6IP2RtzJzYuivxflkULxvkzpjCgYgdNbdySRkThtS88xatXlCD");
        useraccessdomain.setDomainHelp("MiOcND2OLKZfD4W6oKdwVilnK8JLT3al0RtWLJXBp95xu8fUc4");
        useraccessdomain.setDomainDescription("BDa1zefVpmkIUxkir6xdMF0j0giXTtR33lkVhxsc3L2yTRqg0T");
        useraccessdomain.setDomainName("LRXou5HFj7GpWpaeBHDFB8e3pqLT2Gz1pk50hAVuxDYFRVQzgr");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
        }
        map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1458641445222l));
        user.setChangePasswordNextLogin(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1458641445222l));
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setMultiFactorAuthEnabled(1);
        user.setIsDeleted(1);
        user.setIsLocked(1);
        user.setUserAccessCode(14741);
        user.setSessionTimeout(2203);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setGenTempOneTimePassword(1);
        user.setAllowMultipleLogin(1);
        user.setPasswordAlgo("PgZymPlY6SajX2Mx91Hiak4FqEFU06t2lUmLSuZwVEIB85gacJ");
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setLevelid(2);
        question.setQuestion("BZchz2xnw3PytqSxnbG94lvi36C6ZbJA2HHLdUjHwQmIHwiNln");
        question.setQuestionIcon("GYQmPIxVZtmpFhfo4FgSekwJFARIhBwWg7ocm60tg3LKLls1RL");
        question.setQuestionDetails("qo5SJVUu6L");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
        }
        map.put("QuestionPrimaryKey", question._getPrimarykey());
        passrecovery.setUser(user);
        passrecovery.setAnswer("bY9RedoUrubIuwLeYvpsD3GwnQnTdJ6OJqd2K16X45Dt7toUtq");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setPassword("nM9Nsf4n9GXi25Iq4vJhlclTDOQmF0rdCwg633toTkHJ1abyzl");
        userdata.setPassword("kPW7kRWJxRmdcC6wc05Xi0BLbPDBZsV27oKcoXDFcyr3DZc2z8");
        userdata.setUser(user);
        userdata.setLast5Passwords("kN2Dw87PpFsbL3Wpdns1txI88cakJwOnS6Ke8TE0eTo8HCrQj5");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1458641445399l));
        userdata.setOneTimePasswordExpiry(11);
        userdata.setOneTimePassword("sjRkqP1KrEvv9Yrs5KYUSncaSzkJS882");
        user.setUserData(userdata);
        Login login = new Login();
        login.setServerAuthImage("4X9D0ROpzFlI6pjxlpQ3hxKwnxt8la3D");
        login.setServerAuthText("fChXhAWszcsNyQyZ");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setLoginId("cvA0S75eV9qxPO8LYy77qj9i34A5pbTY5cQAax8ehLiuHSEXn0");
        login.setFailedLoginAttempts(2);
        user.setUserId(null);
        login.setUser(user);
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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthImage("MQ863M7V3gSSIUyDODZZ6tM1yVhJTA9N");
            login.setServerAuthText("sGk7I5ApdOFWjGh8");
            login.setLoginId("sali7ZSLkjGXANCbnY2CbBEIsC2jH8Oh4335BGEAlnmXrlx9A3");
            login.setFailedLoginAttempts(1);
            login.setVersionId(1);
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
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "DsvYmhySYDJtbc681vsAXEpPXY2oQDg1orNDpHna0OmxihsDs7OF3Iu7o5kDtBhvQCNlxRf2IA0z2AX4mjvKVQPJpmgudhnG1UehVmVWLhrqlZTleVozUcacszyzgvuc4ZTaRPSlhomJ30XOBLQmBzr9KXv68G6x01S3fSDIOP7mIarxZJmPZVHMvXnjneCJuzkRkhIBS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "dIgcYlsTFcpc0kAoBp97EeWBCoXIT9b7b"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "JQ5HZ3UUbo9teUvco"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 13));
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

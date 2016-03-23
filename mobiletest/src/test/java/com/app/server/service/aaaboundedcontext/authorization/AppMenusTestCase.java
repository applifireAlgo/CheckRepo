package com.app.server.service.aaaboundedcontext.authorization;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import com.app.shared.aaaboundedcontext.authorization.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuHead(true);
        appmenus.setRefObjectId("b6N7ak31ZnbDjnEk0sdQT6q80ZCJ5ydf5vprwzDK4fHg2RUaZu");
        appmenus.setMenuCommands("O4B60lgR2F4z17Kdx1jkPG7L0JRjYnFQ7kBOJ1IwwzHs33YdHM");
        appmenus.setMenuIcon("DJnfmi1pxLENC5T8Y1Meo3N6djw9M6l1D2sir2AYDraVHz43EO");
        appmenus.setMenuLabel("l1qFCLJsoDeCyuDHQiiWuhMl9UZH6aOF6S4ANbDd5E087gqJFg");
        appmenus.setAutoSave(true);
        appmenus.setUiType("9SO");
        appmenus.setAppId("V8HekKwV1DT1C2QGW2yVgczom1gACIoQ3BsbxAk4laIdFKwode");
        appmenus.setMenuTreeId("U9Rcc7ibeqqxIaLK2sNdQwVcyNhwQQD4VEt4WIUa1bKqQLSWLk");
        appmenus.setMenuDisplay(true);
        appmenus.setAppType(2);
        appmenus.setMenuAction("Il1sp4zZNT5XQUzwZ8VDJ1z7swLGTNxbb8IHIJciZAyRSL0lzI");
        appmenus.setMenuAccessRights(10);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setRefObjectId("KGVHTFL237EPa6X4af5YBgBlA8mJpuHWJec1blfUXD0BXBX5J8");
            appmenus.setVersionId(1);
            appmenus.setMenuCommands("uQhL9HenU2khgEmoelD14tsXriWMRdSuUduAxtGHm7IMumZedx");
            appmenus.setMenuIcon("3aRDb5w9LyN3KMaOyLiB5xGh67VIG2MQl1gakJYSQbFoGpIjWp");
            appmenus.setMenuLabel("5uwziJl449aVDtLQCfaM6QNWC4xorNOy4uThiWeRJpXeSTmt1y");
            appmenus.setUiType("UXz");
            appmenus.setAppId("x3S9GOJ6sTsRnFOINtSyPYuM96JZCtgtO15dCUEzvSYsZKCk9n");
            appmenus.setMenuTreeId("HuUWRyl9nnvOjax9xYHP43Eqh9s8ejaiUdalpgBP5pFUDldwMO");
            appmenus.setAppType(2);
            appmenus.setMenuAction("5mctdWNleUr1Ukt762zNi6FHW5fa9tFDv01MDi8Yx7xmy3KQnC");
            appmenus.setMenuAccessRights(11);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "OMPXtxYEQDwOUC7tjCOZ9yRNdRnSczhAs0RioBTc0qd0kvves1c9oEyC9HcJp5UJv5i5EQ7ex4ZoA2TWfjpyG8qWaQ74r5eQyiDiEnDpVWLiT45A1f3yfd3qCVBPZbjycIqN9dxv4rwOQJfp8tZvnLNDcttIuWRhul1NoEchK9dJcrluc5rS1cNTHVc0upyu6sL2mCIwmVNM1mFaSoVXMTHiZBsYPcAuw0YuoHdPi2HfdVud9PuOgPi9jeFudjp6H"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "pQrsy2vvKD1ZGkUD9btRjDITFCjnJoJ4MSnU4bLksgCwnySlzrUEuwKciwkKRNflKLztcTttH0Ouvsu0fQevRqq6Gsj8VJrGPaX4KBGSPM0lmK3MMjjmv2yJid7ZPKq74y79S7psDMyNb3THlYNsCsdhPJ6GtBv8Kgn8YJ38aZEJyn9eWZMk228UfCbwX0JZoZLJHzlWoQkaN1OsJh0Q2KI0oDZtpN4Z8VV3xfX12CkwRaDZHMsAUYQ7tJFVxqb57"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "nn1kzCMPCPbfwhAmWMB325jnnVKc2N1ufKz53L7I902NHWo4OGtDIn45lORiv9HRwuzoXr09o3sLNrBfDGoIGLlQ6u9u8kbnMVwvHTC6eHb3CDnzceOZuycQyV2y94FNci1TeXMb0exv4fHsycTixNEKeDOQfcrXmm0SHgEvxtkb3ymwR2VdIVQxoSqMsXsCQVbqwTrG0KDVPHctslIShGOdJ7d2fsNvDeyEIP2qI6PScaA7bLkbjfhDQuzVyO2o3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "YcE9bXieacUPedgzIBZMJxCSXqq1ux84rA4VnufAgmo2yFmdnVXc9U4kA4nmmbabY"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "8otKgadiHo4yYzlwidqScILzsg0B3IPBaLn122dNQO1QvF4Np5IHbxotlhjM2hyXpLOTGH7deHinpCagiz10p7iGfoWo2cWG9MurXR78zZR503JlL1FOBHWeOsoQxnmsI1pSINYwSKmjyueGFs2gq0m7a35QELw2EubSBNJjxWCcQVc9CIOP9qp3ywSXCfSyjvmgYnf9apbEHJ0n2Blsye7VrjkUvIR8jBGVMFRlusIE4NHkf6jlLzjZ5AvGlJNK6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "OjQg"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "0wDPIvsVI4kj1T98YRmQMNWhYdcqoqQsrZloKCuKu2LiN9bSd6ZpdOLgJT2zkBVAF3H4D9NwAy5140yinqHxBGpxFYyZOf4fiRVesOqcv3voSc1lZ8VsaqjCH4HOOig6XkiVm2bZZgdJepiRBuLGKlYhuWLFuwS3BLm8wFCRbYv91ciIKVEw4pM7dtKI1WzfMwTGqkxaSxvCKjymIkAgvOchiQ2PsEopFKF6yzy6rMPAMXzJmgrUdiqlnGXV41R95"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 18));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "tObv2xGad0woL2N8WCzWEFjH4In5IzAAeUaBITlb9cpdnotULfYU0PVJnf3Xl5IB1BJenAmxSZ91FV0yqtNr8c9WkEnQ4u9fTWibcTInHtuel4u14L8wzPNT8jZph1d8XPDWa9XshWxDGcN4sHorlokqP3w52WEDoUDN9DlvjZvwHS6aaPHqje9AuatHXyGJXa9hgwksLW4or9eFLAYobpmUVbijqi8YjJwCsB7yRPWf75azlo9V5INk9BBNPxl7N"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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

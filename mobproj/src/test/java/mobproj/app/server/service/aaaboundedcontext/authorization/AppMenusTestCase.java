package mobproj.app.server.service.aaaboundedcontext.authorization;
import mobproj.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import mobproj.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import mobproj.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import mobproj.app.shared.aaaboundedcontext.authorization.AppMenus;
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
        appmenus.setAppId("04NZevWe1IECccAAGGy1w0I0VFSTkJNqelCm3RvpymzIk6PHG4");
        appmenus.setMenuTreeId("zeufNLEwOcJ9LjJUC8A3SppjoKB6LDVtxTDSPhiNEyTgqEh6rJ");
        appmenus.setUiType("sdy");
        appmenus.setMenuAction("mjILcTaUHDr2aVHFn1LtwM3Rlk4Q3flDUBDncco9ALPMbh4y2O");
        appmenus.setMenuLabel("JrzSXIx25JlRZ42FROxo0nIi9PfwTFDiS0dfcOVBETVLOXmOow");
        appmenus.setMenuCommands("tM4KyB2JxGrzC85G2Ok6PLQwlRFcTvLjCK6BVySbjRDqTEBNSh");
        appmenus.setMenuHead(true);
        appmenus.setMenuIcon("aoJG47CzO503sA2MGL3GONnhTv073CwkTm6mA3lZUtzIIzetAV");
        appmenus.setMenuAccessRights(10);
        appmenus.setAppType(2);
        appmenus.setMenuDisplay(true);
        appmenus.setRefObjectId("e8dDVYmL2OHDtKH5JvUGk1mP1nw55toqzHKl0jo8itqd1V55F5");
        appmenus.setAutoSave(true);
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
            appmenus.setAppId("VSV2KUs1pxTs0w7d0PNwTq3NQP8SsLiADWsfjVQ6wlFNDUyQS7");
            appmenus.setMenuTreeId("qkWWjjqGDPSkOkRPIQ1nJdCvTe98B2ngdTNY8bmOUmkeuzThyL");
            appmenus.setUiType("OFP");
            appmenus.setMenuAction("NmYZFTw8lW4JF5X60hGdMIKXl4r0giZAAvEIHS6gpovcgMOzlf");
            appmenus.setMenuLabel("s1nxXXZaUDP5SZZ8VFekEAgMjW8uJns2LD3xIJVgotXvD5Dm3W");
            appmenus.setMenuCommands("QuzmXFaKMVrvGP4N87irx3gg6Yn6VKYxhJBPojjPnl9XEivpuP");
            appmenus.setMenuIcon("ITik6z0urbBqfNibHvJ58LLW0aff3q4X1PXcHScnT73nSeZeIq");
            appmenus.setMenuAccessRights(10);
            appmenus.setVersionId(1);
            appmenus.setAppType(2);
            appmenus.setRefObjectId("xjBieaDn13pLBJtlzG9a6TsrppaU4yELPCqtocl8VlVoBTblAx");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "GffeXQ2JiKOlmnMM17pANqYNrJQfIlJOJx7uhJ0tBpVLWWJMp4SZZiYVPNYnYpPGC12E3Lp2aJi4jJQDkqMVWOCqxU1PYBqMDRgInieNd8zo9xjqCWF2BksyS62lS6tBOIfvuwmHri61mQLbF86J0DRdM4JSpUDTTdYvtX4zqUn6ZM7kbCG4c7au7CY8PKFFCOoNdNH0GvqQu4CqnTaSHB45HgSkyVhPU1DBZGTDyvutBnDSsYmufygzjsDYLPt8Z"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "CH7SZm5cgGqHcEkgZW8lQSMV06vYOy6wg6HtRjppheVtPAABiiAOK1dp071MTJYkQOlm8HGyHl8neRNRvrmEsXOXTstf7lDkAqnWaJvlQoP7Aas9B9snPhqvpboVIf2SikNoUq24smRRmMC7R4q7sSepIIqbdFaD3p1qPe4ojnOusrdMdLM5fcZFF0dprOQD5R7PMcv3AFL9szM4XeACE4kCc6TA05JcCybgTZS1uyuIb2h7ufquuJw07AkSKA6gT"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "X1AGr9QR1BnWK7WBn3bUAur4NOi0KYadsosqBpsDcP76ZWUXAUvOz9mlepB9CsimhDteP8HdxoWjhh9TUPzDEi7SQv7l4FPc2UIGg6OthHhnaTM44pemxJ5zxW9JlsHq7G9oHefjeMHjYNnIcGwtPLvqZvItaGmmRjBJslwu8pGiJ07AcERa4cJ33ln7prhzrjDWV6TZIaXQlhmCgIkqohDaJAXbloggRZZ1M2ocIS7oa9PQLkvgguuD71613YSa1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "58VRyCfa1RXV5BkJtDMi8i5nidLewagd0AZgXbxjKWZ06stI5TIWEj4bpAl8Ydrdq"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "ATudHLLs1UDVSqREzLWD5IQXnLNRsoS7AQ1KAf27PpxBgbo46oJtggZifb37LEUtI83FULtgiH7gEXNrxqak0pij707xX8PkfVbvdiAudq35pKD3RV6ddX3G7A5eeYQ8Np8BiEtj9iMIawUCtBLDo8WT2adL2DB89sGbpNubQUl4ui0o8k8zZx5OzBcpRmfOvwaQoaZCHlHRGKsaP2vCC2RbHJjBXwUr5M1Bk0YO01ToGfIWOD38yhT25eNe5xUPg"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "1aLu"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "z3zZH3XHmING4jLLPLI2ZBqNPRECdFqUVlZQiXGxnM5mb3cUlCtODvITAFNMiZinUlVZ0IQymVVcRTK7FV64PrIdOBgUVtKDn4UvzfsSTbrJPbqkdzbznWDN6YM9R9zgLRqegFRv9gyrxHGToU0YH1j4icWsZ2Aggzu7mfoIYT5kz7FfU00KQprufdOJnBm8qHI7Y38NKK2XGF9W8p9n7vzRxRtpjVRak2Sn0Ow614P95ECfuwkcQTGVo2Q9aMbEZ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "3nWsJ2rhz6UAztEoPASVqSmhbBYV6fWtUPmRoQpUdjmAyiNlA7eoHyhickz0YyCmp10CkX4RoKLam5vwaUdBqF4ceE56dr9eln7jZ7FuS72SJzc9TChi2Ql6hYl1yYr2uNICQV1PmM8IedeKBRpsWQK0jMn3c0K7hZSoSpYr1Rtv8UVyj7ocgGv2TyGu7joDkTgKzQGOf5PKp9713aT8JjcaUCL00Yr0RDkLT4ceKROjCsgQne96CENjj1aNGLTdK"));
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

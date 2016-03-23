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
import mobproj.app.server.repository.aaaboundedcontext.authorization.RolesRepository;
import mobproj.app.shared.aaaboundedcontext.authorization.Roles;
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
import mobproj.app.shared.aaaboundedcontext.authorization.RoleMenuBridge;
import mobproj.app.shared.aaaboundedcontext.authorization.AppMenus;
import mobproj.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Roles roles = new Roles();
        roles.setRoleDescription("gIxyp4coEgvrNkJBCLuLakQHiF4VVv8OVnNiVqq3ynxDPMX4Us");
        roles.setRoleIcon("R46GCE11AHunWZJUo995Sq1oRcJY0EpiXDsGDIln0dJGbPL2ue");
        roles.setRoleHelp("BB1gVSdmP408GxX258GQYeJBKOROJOoNC61Mho48u8LP74BFZS");
        roles.setRoleName("HBJoOyNR3u8i2bYy6nKhKpJQGc4m6JM3MPfIyrvX7cHtdSSKaq");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setAppId("OyFPcZhq2lmYrAzbNZnzTRuISm0GLPYGFRo0kEHNE4OCvYIw98");
        appmenus.setMenuTreeId("yt3ktnI3yVWJTDEOSb1Y3a8m492xoAH1JnZE6WYPQRC0T8WyAi");
        appmenus.setUiType("1VZ");
        appmenus.setMenuAction("eonFqajPoqCNy3RazebDUyEd4hDp9JCuWhEBlDMWShEEuGjit6");
        appmenus.setMenuLabel("rqlVPRKMlS8JEDl86nnMTPHqPJfNSGFn27mcA9nQEoXg3aYD3V");
        appmenus.setMenuCommands("jsa5pvNnw4JS26LfLkB6PjXdSOXDjJqQKVZ2pqCzWdCs3VTgLn");
        appmenus.setMenuHead(true);
        appmenus.setMenuIcon("aCH1bKyvFQx5ujPzTa6PrMDYAA6NWF1AICyTvZLMu3d16xgZKB");
        appmenus.setMenuAccessRights(2);
        appmenus.setAppType(1);
        appmenus.setMenuDisplay(true);
        appmenus.setRefObjectId("4eFjIwfiOCMjhFNCZOzxoS0jGbM3cEULuSZlZkBIPskJYOGwnY");
        appmenus.setAutoSave(true);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
        }
        map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setIsRead(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsWrite(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setVersionId(1);
            roles.setRoleDescription("wkel9Tdku0zhOKBGLhcaxNldi7aslEuwhdSbbR2BU1PTIaeAYo");
            roles.setRoleIcon("mD5Ixh0aMa8MffyeWxoYi0PlhHWz8qfTb1vjcsYbQuod6ZUDWp");
            roles.setRoleHelp("0Z9uN19LJtbroJhodTkuwfDmINH2stWej2TyC3OsHgW6Q26p7Y");
            roles.setRoleName("sPKveRntyP4kCgQS62DgfYgOAQW2A0Cllxy8NJkRJXZGCd1l8M");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "pEOF8uW5M4tG1o2V6rU3IvF2McmghV0BRsvnIqd3JCnRgMg5u3yda8ypV5j7CdySiu5jcV8lkLFrLt6sS3oj6KRyd9dPw56RCXeOO7uSmqFjMhOJkMWWgWX8j40lLakXBXQq1JhQrVlErMzkyPR0d4IIAJA3lkPs0PX43vYqZvYKTeIFc3hAezFww4rT2vbNgNHEvvSyC2OJKdElsPZMXRGg1rWDKflHk0EsBlHYJHuyWFKVz7ds5Ef71HT8uq6CZ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "dcZZnkQIXljWMqdrAU0VACS1ccZvJlzv7ALZBYsz3V7E6XCMw1g2Zr7bjBg3IdenxSDhf0wn6eSrVMZH0UpIjZ97wiNeKcJuG87S7JvZFhgZiqkr0pidO8g4sVYDvlFyDJXgxSwY1wayHMNcH7IFAb9rZtobjQrsWLb2p6wxcX54N4nJk46F51bfSG0UVNtlExzYndNA1ShdaxTtGgChwreJescnPOMhbl4QbUjzgNWOzAfVcxqTqOgvqAE7QOpTC"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "75l3WRj8Y12imSkXr9wt4kyzH5zlrUyFTdn6gchDnS3ekpkcAPiCmHOGbKhYLak1o"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "oqahSoQNA925YbMgYBv5z4itPFawHigizmoBD9joRcehbYqQvxHEbRaPWVEoPjzaDSxjucbo9OG5fnZqk9SE8HvJt0KuQtWa7Cp8iAQA7ugUFgGONenxib9Mx8YMuVmGMi75tY5ZHcoZZZTxSHSqlCnonFSV1QoX3reZYQBJOdS3JP5g7qvoWl1VHQ2QoSdrHfkwoz9NhLKu8f8TUxSywmmPJ3AoiQKqReRtzs2z74Md12xFpufP74MywQIm3DmYQ"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = roles.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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

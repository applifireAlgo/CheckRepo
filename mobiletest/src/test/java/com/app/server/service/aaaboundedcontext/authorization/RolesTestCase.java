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
import com.app.server.repository.aaaboundedcontext.authorization.RolesRepository;
import com.app.shared.aaaboundedcontext.authorization.Roles;
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
import com.app.shared.aaaboundedcontext.authorization.RoleMenuBridge;
import com.app.shared.aaaboundedcontext.authorization.AppMenus;
import com.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
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
        roles.setRoleHelp("HNtfUGwhzBcifdST0yUJ67ywSCBoPYPUkjeC3RYpVutWvgvHzl");
        roles.setRoleDescription("zqr0M8Vc2qLF5nFoYP9d8qGTJlcT6NPgiRplHegrPJjQTJ7CH9");
        roles.setRoleName("nNvKLsPcX9avcwLw2hUhRX5L8tljWwTBHkiPn3Rj9FByStVTEo");
        roles.setRoleIcon("VkHwD1vBxFUUSRlftXW8lqkijc1pD3YhMN0C12906bMrFRXbmp");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuHead(true);
        appmenus.setRefObjectId("OJ9slpp9EiMf185wurKn4L8oYlUkL7Y6LSTa8ora6qbKNimHpt");
        appmenus.setMenuCommands("4iT5UOIAm9NoIGnVQCIFjUkGOcfU6uCWnBfDdpTXSI5TcZxoaL");
        appmenus.setMenuIcon("ZXbjIEkZKJlXf0zbCW1sJa7CwQRIXjSq9jrlSLYZnYDs4YLJB1");
        appmenus.setMenuLabel("0482Ue5oLCZgpG8spzqmBqtCGwu7zNVnTnLQxiRT6N4AIshETH");
        appmenus.setAutoSave(true);
        appmenus.setUiType("89C");
        appmenus.setAppId("waS6T1LbHdREj28rf9ihjMDoLtEMv3ZjsQn4trPaj34xv3Zh5T");
        appmenus.setMenuTreeId("PgG6IbIpjAerHl7YKwbwnrnZ34JiOh4dBjdcvKCF8hn6F0BMuz");
        appmenus.setMenuDisplay(true);
        appmenus.setAppType(1);
        appmenus.setMenuAction("TxJ0xIxHYpnOSfw0fQ3uFlPOwRrsQaEMv3pxoARwXRGstpUDAm");
        appmenus.setMenuAccessRights(1);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
        }
        map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsRead(true);
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
            roles.setRoleHelp("OKbK5AVzDv34qQ1XMnxVUuFx1aPrEjSaKlaGc5lmHVVDCPJ9gQ");
            roles.setRoleDescription("glIb3xqqNRNXS9XfBp142ZJd5eZwfXSIRea7gF4c602nVKVRZx");
            roles.setRoleName("7ZytfTp2lxg50uYMkuTjU70gaqjdkY7scLCDognxFMKWEALrNI");
            roles.setRoleIcon("1dclHtMCghmnwssuFn3NQeUFzNUtmgFejGxpz2NQChhlPTyNRH");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "IGTC0rtFZ9SiBWDGMRugJy8wtN3Lo1IzWIuTmofGXTsIE0idfUgoeVZkzcDcoyvCH7POyRY2f0QLRYimPZks7aXF4eREtgtqoOr8HMuvLeQ1Ro5bO4XX6rSZIjQNVk4VZ3LRWlel54vE3sajyITYWKDvMlfzVFiYx2FyyLkvGfacGTCUqQCzZEcAlReldZ2VQKw0kxOsCQAbrzVIOxjpA7jvpH3c6pk0G1HlyRzv4iRiTsletgl3rVViV8BqkVofN"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "zsACo9iq4R0TANSBDnYhaRKdLJG2qbPj1MgNFPf7p3v4X16s9VAdaF0XzYMBg9tqWzv1OsCBZA2MCvyUa7BcFhOf3ePacaUGMViKeW6wxELWLsM1SewZ2cmj41SWp0KbXW23fjYsm3N2cf9w5N5h7vYtsqIMLJYhBk3WwB3taA533sgNjOsrQccJOYZrcJ7g9zGSP3TTqYChwFQH9mxSRBztj3Qw7PTdOyqlOhiSCkPVKVCmDNTxgpsJRniianuWE"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "wmRSSQ6WUWwNgTkoFR9csDPv7TkddteTxJvJ9i3FUj1AomIDGYLdQ7V1e0r6zqJ47"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "b99PKIq3DqcEGaFYEjfktRf4Ozb41yxZyrqxM8Ja1P2DldtRwy2GWO2mIbmnmdE2nCAiFBdzsF4DjSNu96c66ORviBpa9xlT39Va3OdAZP1Y9zsO7QbCfcoTIIr8bFlMJwFVYxk3CG7PVZnQbqHGjW1aFiKj0L2hMLdi5JbwuVRWv7ceOxXoZZcpb2Gd4NN1WBXWMINZaAeGup4QPyLP3jY7sbmfqNBtJELRPvezwhK9ghJoC61335G6OcN2dtNPs"));
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

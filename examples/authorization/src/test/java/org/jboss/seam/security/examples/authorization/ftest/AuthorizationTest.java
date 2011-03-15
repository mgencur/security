package org.jboss.seam.security.examples.authorization.ftest;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.jboss.test.selenium.AbstractTestCase;
import org.jboss.test.selenium.locator.IdLocator;
import org.jboss.test.selenium.locator.XpathLocator;
import static org.jboss.test.selenium.locator.LocatorFactory.*;
import static org.jboss.test.selenium.guard.request.RequestTypeGuardFactory.*;

/**
 * A functional test for the Authorization example
 * 
 * @author Martin Gencur
 * 
 */
public class AuthorizationTest extends AbstractTestCase
{
   protected IdLocator USERNAME_INPUT = id("loginForm:name");
   protected IdLocator LOGIN = id("loginForm:login");
   protected XpathLocator LOGOUT = xp("//a[contains(text(),'Log out')]");
   protected XpathLocator DO_SOMETHING_RESTRICTED = xp("//input[contains(@value,'doSomethingRestricted')]");
   protected XpathLocator DO_FOO_ABC = xp("//input[contains(@value,'doFooAbc')]");
   protected XpathLocator DO_FOO_DEF = xp("//input[contains(@value,'doFooDef')]");
   protected XpathLocator DO_LOGGED_IN = xp("//input[contains(@value,'doLoggedIn')]");
   protected XpathLocator DO_USER_ACTION = xp("//input[contains(@value,'doUserAction')]");
   private final String WARNING = "You do not have the necessary permissions to perform that operation";
   private final String WARNING_INFO = "You do not have the necessary permissions to perform that operation";
   private final String GENERAL_USER = "martin";
   private final String ADMIN_USER = "demo";
   private final String MEMBER_USER = "user";

   @BeforeMethod
   public void openStartUrl() throws MalformedURLException
   {
      selenium.setSpeed(400);
      selenium.open(new URL(contextPath.toString()));
   }

   @Test
   public void testSomethingRestricted()
   {
      //not logged in
      waitHttp(selenium).click(DO_SOMETHING_RESTRICTED);
      assertTrue(selenium.isTextPresent(WARNING), WARNING_INFO);
      //general user
      selenium.type(USERNAME_INPUT, GENERAL_USER);
      waitHttp(selenium).click(LOGIN);
      waitHttp(selenium).click(DO_SOMETHING_RESTRICTED);
      assertTrue(selenium.isTextPresent(WARNING), WARNING_INFO);
      waitHttp(selenium).click(LOGOUT);
      //member user
      selenium.type(USERNAME_INPUT, MEMBER_USER);
      waitHttp(selenium).click(LOGIN);
      waitHttp(selenium).click(DO_SOMETHING_RESTRICTED);
      assertTrue(selenium.isTextPresent(WARNING), WARNING_INFO);
      waitHttp(selenium).click(LOGOUT);
      //admin user
      selenium.type(USERNAME_INPUT, ADMIN_USER);
      waitHttp(selenium).click(LOGIN);
      waitHttp(selenium).click(DO_SOMETHING_RESTRICTED);
      assertTrue(selenium.isTextPresent("doSomethingRestricted() invoked"), "doSomethingRestricted method should be invoked");
      waitHttp(selenium).click(LOGOUT);
   }
   
   @Test
   public void testFooAbc()
   {
      waitHttp(selenium).click(DO_FOO_ABC);
      assertTrue(selenium.isTextPresent("doFooAbc() invoked"), "doFooAbc method should be invoked");
   }
   
   @Test
   public void testFooDef()
   {
      //not logged in
      waitHttp(selenium).click(DO_FOO_DEF);
      assertTrue(selenium.isTextPresent(WARNING), WARNING_INFO);
      //general user
      selenium.type(USERNAME_INPUT, GENERAL_USER);
      waitHttp(selenium).click(LOGIN);
      waitHttp(selenium).click(DO_FOO_DEF);
      assertTrue(selenium.isTextPresent(WARNING), WARNING_INFO);
      waitHttp(selenium).click(LOGOUT);
      //member user
      selenium.type(USERNAME_INPUT, MEMBER_USER);
      waitHttp(selenium).click(LOGIN);
      waitHttp(selenium).click(DO_FOO_DEF);
      assertTrue(selenium.isTextPresent(WARNING), WARNING_INFO);
      waitHttp(selenium).click(LOGOUT);
      //admin user
      selenium.type(USERNAME_INPUT, ADMIN_USER);
      waitHttp(selenium).click(LOGIN);
      waitHttp(selenium).click(DO_FOO_DEF);
      assertTrue(selenium.isTextPresent(WARNING), WARNING_INFO);
      waitHttp(selenium).click(LOGOUT);
   }
   
   @Test
   public void testLoggedIn()
   {
      //not logged in
      waitHttp(selenium).click(DO_LOGGED_IN);
      assertTrue(selenium.isTextPresent(WARNING), WARNING_INFO);
      //general user
      selenium.type(USERNAME_INPUT, MEMBER_USER);
      waitHttp(selenium).click(LOGIN);
      waitHttp(selenium).click(DO_LOGGED_IN);
      assertTrue(selenium.isTextPresent("doLoggedIn() invoked"), "doLoggedIn method should be invoked");
      waitHttp(selenium).click(LOGOUT);
      //member user
      selenium.type(USERNAME_INPUT, MEMBER_USER);
      waitHttp(selenium).click(LOGIN);
      waitHttp(selenium).click(DO_LOGGED_IN);
      assertTrue(selenium.isTextPresent("doLoggedIn() invoked"), "doLoggedIn method should be invoked");
      waitHttp(selenium).click(LOGOUT);
      //admin user
      selenium.type(USERNAME_INPUT, ADMIN_USER);
      waitHttp(selenium).click(LOGIN);
      waitHttp(selenium).click(DO_LOGGED_IN);
      assertTrue(selenium.isTextPresent("doLoggedIn() invoked"), "doLoggedIn method should be invoked");
      waitHttp(selenium).click(LOGOUT);
   }
   
   @Test
   public void testUserAction()
   {
      //not logged in
      waitHttp(selenium).click(DO_USER_ACTION);
      assertTrue(selenium.isTextPresent(WARNING), WARNING_INFO);
      //general user
      waitHttp(selenium).click(DO_USER_ACTION);
      assertTrue(selenium.isTextPresent(WARNING), WARNING_INFO);
      //member user
      selenium.type(USERNAME_INPUT, MEMBER_USER);
      waitHttp(selenium).click(LOGIN);
      waitHttp(selenium).click(DO_USER_ACTION);
      assertTrue(selenium.isTextPresent("doUserAction() invoked"), "doUserAction method should be invoked");
      waitHttp(selenium).click(LOGOUT);
      //admin user
      selenium.type(USERNAME_INPUT, ADMIN_USER);
      waitHttp(selenium).click(LOGIN);
      waitHttp(selenium).click(DO_USER_ACTION);
      assertTrue(selenium.isTextPresent("doUserAction() invoked"), "doUserAction method should be invoked");
      waitHttp(selenium).click(LOGOUT);
   }
}

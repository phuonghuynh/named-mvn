package kimrac;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AAppTest extends TestCase {
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public AAppTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(AAppTest.class);
  }

  /**
   * Rigourous Test :-)
   */
  public void testApp() {
    AApp.sayHello();
  }
}

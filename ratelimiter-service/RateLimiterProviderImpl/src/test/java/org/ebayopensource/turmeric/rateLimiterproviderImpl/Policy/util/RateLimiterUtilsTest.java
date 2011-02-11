/**
 * 
 */
package org.ebayopensource.turmeric.rateLimiterproviderImpl.Policy.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author gbaal
 * 
 */
public class RateLimiterUtilsTest {
	private RateLimiterUtils limiterUtils;

	@Before
	public void init() {
		limiterUtils = new RateLimiterUtils("test");
	}

	@After
	public void tearDown() {
		limiterUtils = null;
	}

	@Test
	public void testsimpleExp() {
		Boolean flag;
		try {
			flag = limiterUtils.getFinalresult("1>2");
			assertEquals("\"1>2\" should  be false",false, flag);
			flag = limiterUtils.getFinalresult(" 1   >  3  ");
			assertEquals("\" 1   >  3  \" should  be false",false, flag);
			flag = limiterUtils.getFinalresult("4 > 2");
			assertEquals("\"4 > 2\" should  be true",true, flag);
			flag = limiterUtils.getFinalresult("1>1");
			assertEquals("\"1>1 \" should  be false",false, flag);
			
		} catch (Exception e) {
		}

	}
	@Test
	public void testOrExp() {
		Boolean flag;
		try {
			flag = limiterUtils.getFinalresult("1>2||3>6");
			assertEquals("\"1>2||3>6\" should  be false",false, flag);
			flag = limiterUtils.getFinalresult(" 1   >  3  || 4 > 6 || 5 >7 ");
			assertEquals("\" 1   >  3  || 4 > 6 || 5 >7  \" should  be false",false, flag);
			flag = limiterUtils.getFinalresult("1   >  3  || 4 > 6  || 4 > 2");
			assertEquals("\"1   >  3  || 4 > 6  || 4 > 2\" should  be true",true, flag);
			flag = limiterUtils.getFinalresult("1>1 || 2>10 || 3>5");
			assertEquals("\"1>1 || 2>10 || 3>5 \" should  be false",false, flag);
			
		} catch (Exception e) {
		}

	}
	@Test
	public void testAndExp() {
		Boolean flag;
		try {
			flag = limiterUtils.getFinalresult("1>2&&3>6");
			assertEquals("\"1>2||3>6\" should  be false",false, flag);
			flag = limiterUtils.getFinalresult(" 1   >  3  && 4 > 6 && 5 >7 ");
			assertEquals("\" 1   >  3  || 4 > 6 || 5 >7  \" should  be false",false, flag);
			flag = limiterUtils.getFinalresult("1   <  3  && 4 < 6  && 4 > 2");
			assertEquals("\"1   <  3  && 4 < 6  && 4 > 2\" should  be true",true, flag);

			
		} catch (Exception e) {
		}

	}
	@Test
	public void testInitProvider() {
		assertNotNull("limiterUtils is null", limiterUtils);
	}
	
	@Test
	public void testIvalidExpression(){
		Boolean flag;
		try {
			flag = limiterUtils.getFinalresult("1>>2");
			fail("invalid expression should fail");
		} catch (Exception e) {
		}
		try {
			flag = limiterUtils.getFinalresult("1>>2 2");
			fail("invalid expression should fail");
		} catch (Exception e) {
		}
		
		try {
			flag = limiterUtils.getFinalresult("1>>2 && 2 >1");
			fail("invalid expression should fail");
		} catch (Exception e) {
		}

	}
}
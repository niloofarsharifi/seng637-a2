package org.jfree.data.test;
import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;
import org.jfree.data.*;



public class DataUtilitiesCalculateRowTotalTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}
	
	private static final double DELTA = 0.0000000001;

	@Test
	public void calculateRowTotalMaxValueBoundary() {
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);

	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(3)); // Suppose we have 3 columns
	            one(values).getValue(0, 0);
	            will(returnValue(Double.MAX_VALUE));
	            one(values).getValue(0, 1);
	            will(returnValue(Double.MAX_VALUE));
	            one(values).getValue(0, 2);
	            will(returnValue(Double.MAX_VALUE));
	        }
	    });

	    double resultm = DataUtilities.calculateRowTotal(values, 0);
	   // System.out.println("Actual Result: " + resultm);
	    assertEquals("The row total is adding up to 2e10", 3 * Double.MAX_VALUE, resultm, DELTA);
	}

	@Test
	public void calculateRowTotalMinValBoundary() {
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);

	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(3)); // Suppose we have 1 row
	            one(values).getValue(0, 0);
	            will(returnValue(-Double.MAX_VALUE));
	            one(values).getValue(0, 1);
	            will(returnValue(-Double.MAX_VALUE));
	            one(values).getValue(0, 2);
	            will(returnValue(Double.MAX_VALUE));
	        }
	    });

	    double resultm = DataUtilities.calculateRowTotal(values, 0);
	    double expectedres = -2 * Double.MAX_VALUE + Double.MAX_VALUE;
	    assertEquals("The row total is adding up to Double.MIN_VALUE", expectedres, resultm, DELTA);
	}

	    @Test
	    public void testCalculateRowTotalWithNullData() {
	        Mockery mockery = new Mockery();
	        final Values2D values = null; // Null data object

	        try {
	            // Attempt to calculate row total with null data object
	            double rowTotal = DataUtilities.calculateRowTotal(values, 0);

	            // If we reach here, the test should fail
	            // Fail if no exception is thrown
	            assertEquals("Row total should be 0.0 for null data object", 0.0, rowTotal, 0.0001);
	        } catch (NullPointerException e) {
	            // Expected NullPointerException if data object is null
	            // Test passes if NullPointerException is caught
	        }
	    }
	    
	    @Test
	    public void calculateRowTotalInvalidIndex() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(2)); 

	                // Expecting an InvalidParameterException when accessing negative row index
	              one(values).getValue(with(any(int.class)), with(equal(0)));
	               will(throwException(new InvalidParameterException()));
	            }
	        });

	        try {
	            double result = DataUtilities.calculateRowTotal(values, -10);
	            fail("Expected InvalidParameterException");
	        } catch (InvalidParameterException e) {
	            // Test passed if InvalidParameterException is thrown
	        }
	    }


	    
	    @Test
	    public void calculateRowTotalValidRow() {
	    	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(7.5));
	                one(values).getValue(0, 1);
	                will(returnValue(2.0));
	                one(values).getValue(0, 2);
	                will(returnValue(-19.0));
	            }
	        });
	        double result = DataUtilities.calculateRowTotal(values, 0);
	 	  // System.out.println("Actual Result: " + result);
	        assertEquals(9.5, result, DELTA);
	    }

	    @After
		public void tearDown() throws Exception {
			
		}

		@AfterClass
		public static void tearDownAfterClass() throws Exception {
			
		}
}

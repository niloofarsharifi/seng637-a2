package org.jfree.data.test;

import static org.junit.Assert.*; import org.junit.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.DefaultKeyedValues;
import org.jmock.*;

/*public class DataUtilitiesTest {
    private Mockery mockingContext;
    private Values2D values;
    private KeyedValues kv;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.mockingContext = new Mockery();
        this.values = this.mockingContext.mock(Values2D.class);
        this.kv = mockingContext.mock(KeyedValues.class);
    }
  */
public class getCumulativePercentages {
	private Mockery mockingContext;
    private Values2D values;
    private KeyedValues kv;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.mockingContext = new Mockery();
        this.values = this.mockingContext.mock(Values2D.class);
        this.kv = mockingContext.mock(KeyedValues.class);
    }
	

	@Test
    public void getCumulativePercentagesWithNoKeyedValues() {
        KeyedValues kv = new DefaultKeyedValues();

        KeyedValues cumPercentage = DataUtilities.getCumulativePercentages(kv);
        KeyedValues expected = new DefaultKeyedValues();
        assertEquals(
                "Calling getCumulativePercentage on an empty KeyedValues, should return an empty KeyedValues object",
                expected, cumPercentage);
    }

    @Test
    public void getCumulativePercentagesWithOneRowKeyedValue() {
        mockingContext.checking(new Expectations() {
            {
                allowing(kv).getItemCount();
                will(returnValue(1));
                allowing(kv).getKey(0);
                will(returnValue(0));
                allowing(kv).getValue(0);
                will(returnValue(2));
            }
        });

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals("The Cumulative Percentage of 2 in data should be 1.0", 1.0, result.getValue(0));
    }

    @Test
    public void getCumulativePercentagesWithOneKeyedValueHavingZeroValue() {
        mockingContext.checking(new Expectations() {
            {
                allowing(kv).getItemCount();
                will(returnValue(1));
                allowing(kv).getKey(0);
                will(returnValue(0));
                allowing(kv).getValue(0);
                will(returnValue(0));
            }
        });

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals("The cumulative percentage of 0 in data should be the result of 0.0/0.0", 0.0 / 0.0,
                result.getValue(0));
    }

    @Test
    public void test_getCumulativePercentages_oneKeyedValue_nullValue() {
        mockingContext.checking(new Expectations() {
            {
                allowing(kv).getItemCount();
                will(returnValue(1));
                allowing(kv).getKey(0);
                will(returnValue(0));
                allowing(kv).getValue(0);
                will(returnValue(null));
            }
        });

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals("The cumulative percentage of null in data should be NaN", Double.NaN, result.getValue(0));
    }

    @Test
    public void getCumulativePercentagesWithKeyedValuesHavingNullAndMixValues() {
        mockingContext.checking(new Expectations() {
            {
                allowing(kv).getItemCount();
                will(returnValue(5));
                allowing(kv).getKey(0);
                will(returnValue(0));
                allowing(kv).getKey(1);
                will(returnValue(1));
                allowing(kv).getKey(2);
                will(returnValue(2));
                allowing(kv).getKey(3);
                will(returnValue(3));
                allowing(kv).getKey(4);
                will(returnValue(4));
                allowing(kv).getValue(0);
                will(returnValue(-4.5));
                allowing(kv).getValue(1);
                will(returnValue(2));
                allowing(kv).getValue(2);
                will(returnValue(null));
                allowing(kv).getValue(3);
                will(returnValue(12.5));
                allowing(kv).getValue(4);
                will(returnValue(0));
            }
        });

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);

        assertEquals("The cumulative percentage of -4.5 in data should be -0.45", -0.45,
                result.getValue(0).doubleValue(), 0.000000001d);
        assertEquals("The cumulative percentage of 2 in data should be -0.25", -0.25, result.getValue(1).doubleValue(),
                0.000000001d);
        assertEquals("The cumulative percentage of null in data should be -0.25", -0.25,
                result.getValue(2).doubleValue(), 0.000000001d);
        assertEquals("The cumulative percentage of 12.5 in data should be 1.0", 1.0, result.getValue(3).doubleValue(),
                0.000000001d);
        assertEquals("The cumulative percentage of 0 in data should be 1.0", 1.0, result.getValue(4).doubleValue(),
                0.000000001d);
    }
}

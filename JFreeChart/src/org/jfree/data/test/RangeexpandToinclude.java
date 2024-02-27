package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeexpandToinclude {
    private Range exampleRange;
  
    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRange.getCentralValue(), .000000001d);
    }
    @Test
    public void expandToIncludeWithInputBLB() {
        assertEquals("Testing epanding range to include value BLB", new Range(-10.00001, 10),
                Range.expandToInclude(this.exampleRange, -10.00001));
    }

    @Test
    public void expandToIncludeWithInputLB() {
        assertEquals("Testing epanding range to include value at LB (range shouldn't change)", new Range(-10, 10),
                Range.expandToInclude(this.exampleRange, -10));
    }

    @Test
    public void expandToIncludeWithInputALB() {
        assertEquals("Testing epanding range to include value ALB (range shouldn't change)", new Range(-10, 10),
                Range.expandToInclude(this.exampleRange, -9.99999));
    }

    @Test
    public void expandToIncludeWithInputBUB() {
        assertEquals("Testing epanding range to include value BUB (range shouldn't change)", new Range(-10, 10),
                Range.expandToInclude(this.exampleRange, 9.99999));
    }

    @Test
    public void expandToIncludeWithInputUB() {
        assertEquals("Testing epanding range to include value at UB (range shouldn't change)", new Range(-10, 10),
                Range.expandToInclude(this.exampleRange, 10));
    }

    @Test
    public void expandToIncludeWithInputAUB() {
        assertEquals("Testing epanding range to include value AUB ", new Range(-10, 10.00001),
                Range.expandToInclude(this.exampleRange, 10.00001));
    }

    @Test
    public void expandToIncludeWithInputPositive() {
        assertEquals("Testing epanding range to include value at LB (range shouldn't change)", new Range(-10, 25),
                Range.expandToInclude(this.exampleRange, 25));
    }

    @Test
    public void expandToIncludeWithInputNegative() {
        assertEquals("Testing epanding range to include value at LB (range shouldn't change)", new Range(-25, 10),
                Range.expandToInclude(this.exampleRange, -25));
    }

    @Test
    public void expandToIncludeWithInputDoubleMax() {
        assertEquals("Testing epanding range to include max double", new Range(-10, Double.MAX_VALUE),
                Range.expandToInclude(this.exampleRange, Double.MAX_VALUE));
    }
    @Test
    public void expandToIncludeWithInputWithinRange() {
        assertEquals("Testing expanding range to include a value within the range", new Range(-1, 1),
                Range.expandToInclude(this.exampleRange, 0.5));
    }
    @Test
    public void expandToIncludeWithInputNegativeDoubleMax() {
        assertEquals("Testing epanding range to include negative max double", new Range(-Double.MAX_VALUE, 10),
                Range.expandToInclude(this.exampleRange, -Double.MAX_VALUE));
    }
    
}

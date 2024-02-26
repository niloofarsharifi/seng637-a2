package org.jfree.data.test;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import org.jfree.data.DataUtilities;
import org.junit.*;


public class DataUtilitiesCreateNumberArrayTest {
	
	private final Number[] expected = {1.0, 2.5, 3.7, 4.2}; // Expected output
	
	@Test
    public void testCreateNumberArrayWithBottomLimitBoundary() {
        double[] testData = {Double.MIN_VALUE}; // Smallest positive nonzero value
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCreateNumberArrayWithLowerBoundary() {
        double[] testData = {-1.0, 0.0, 1.0}; // Example low values
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCreateNumberArrayWithAdjustedLowerBoundary() {
        double[] testData = {-10.5, -5.0, -1.0}; // Example negative values
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }
    @Test
    public void testCreateNumberArrayWithSameValues() {
        // Input array containing repeated values
        double[] testData = {1.0, 1.0, 1.0, 1.0};
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }


    @Test
    public void testCreateNumberArrayWithUpperBoundary() {
        double[] testData = {Double.MAX_VALUE - 1.0, Double.MAX_VALUE}; // Example high values
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCreateNumberArrayWithUpperLimitBoundary() {
        double[] testData = {Double.MAX_VALUE}; // Largest positive finite value
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCreateNumberArrayWithAdjustedUpperBoundary() {
        double[] testData = {1.0e100, 1.0e200}; // Example very large values
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }
	
    @Test
    public void testCreateNumberArrayValidInput() {
        double[] testData = {1.0, 2.5, 3.7, 4.2}; // similar input data
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }
    @Test
    public void testCreateNumberArrayWithNominalValues() {
        double[] testData = {10.0, 20.5, 30.7, 40.2}; // Typical values
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void testCreateNumberArrayEmptyInput() { //passed
        double[] testData = {}; // Empty array
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertEquals(0, result.length);
    }
    @Test
    public void testCreateNumberArrayWithMixedValues() {
        double[] testData = {-1.0, 2.5, 0.0, 4.2}; // Mixed positive and negative values
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCreateNumberArrayWithLargeInput() {
        // Large input array with repeated values
        double[] testData = new double[1000];
        Arrays.fill(testData, 1.0);
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(new Number[1000], result);
    }

    @Test
    public void testCreateNumberArrayWithZeroValues() {
        // Input array containing only zeros
        double[] testData = {0.0, 0.0, 0.0, 0.0};
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }
   
    public void testCreateNumberArrayWithSingleElement() {
        // Input array with a single element
        double[] testData = {10.0};
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }
    @Test
    public void testCreateNumberArrayWithNaN() {
        // Input array containing NaN (Not-a-Number)
        double[] testData = {Double.NaN, 2.5, 3.7, 4.2};
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }
    

}
    


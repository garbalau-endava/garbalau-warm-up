package com.endava.internship.warmup.domain.service;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;

public class ArrayProcessorWithForLoops implements ArrayProcessor {

    /**
     * Return true if there are no numbers that divide by 10
     * @param input non-null immutable array of ints
     */
    @Override
    public boolean noneMatch (final int[] input) {
        for (int number : input) {
            if (number % 10 == 0) {
                return false;
            };
        }
        return true;
    }

    /**
     * Return true if at least one value in input matches the predicate
     * @param input non-null immutable array of ints
     * @param predicate invoke the predicate.test(int value) on each input element
     */
    @Override
    public boolean someMatch (final int[] input, IntPredicate predicate) {
        for (int number : input) {
            if (predicate.test(number)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if all values processed by function, matches the predicate
     * @param input non-null immutable array of Strings. No element is null
     * @param function invoke function.applyAsInt(String value) to transform all the input elements into an int value
     * @param predicate invoke predicate.test(int value) to test the int value obtained from the function
     */
    @Override
    public boolean allMatch (final String[] input, ToIntFunction<String> function, IntPredicate predicate) {
        for (String s : input) {
            int value = function.applyAsInt(s);
            if (!(predicate.test(value))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Copy values into a separate array from specific index to stopindex
     * @param input non-null array of ints
     * @param startInclusive the first index of the element from input to be included in the new array
     * @param endExclusive the last index prior to which the elements are to be included in the new array
     * @throws IllegalArgumentException when parameters are outside of input index bounds
     */
    @Override
    public int[] copyValues (int[] input, int startInclusive, int endExclusive) throws IllegalArgumentException {
        if (startInclusive > endExclusive || startInclusive < 0 || endExclusive > input.length - 1) {
            throw new IllegalArgumentException();
        }

        int[] copyArray = new int[endExclusive - startInclusive];

        for (int i = 0; i < endExclusive - startInclusive; i++) {
            copyArray[i] = input[i + startInclusive];
        }

        return copyArray;
    }

    /**
     * Replace even index values with their doubles and odd indexed elements with their negative
     * @param input non-null immutable array of ints
     * @return new array with changed elements
     */
    @Override
    public int[] replace (final int[] input) {
        int[] resultArray = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            if (i % 2 == 0) {
                resultArray[i] = input[i] * 2;
            } else {
                resultArray[i] = input[i] * -1;
            }
        }
        return resultArray;
    }

    /**
     * Find the second max value in the array
     * @param input non-null immutable array of ints
     */
    @Override
    public int findSecondMax (final int[] input) {
        int secondMaximumValue = 0;

        Arrays.sort(input);

        for (int i = input.length - 2; i >= 0; i--) {
            if (input[i] != input[input.length - 1]) {
                secondMaximumValue = input[i];
                break;
            }
        }
        return secondMaximumValue;
    }

    /**
     * Return in reverse first negative numbers, then positive numbers from array
     * @param input non-null immutable array of ints.
     * @return example: input {3, -5, 4, -7, 2 , 9}
     *                  result: {-7, -5, 9, 2, 4, 3}
     */
    @Override
    public int[] rearrange (final int[] input) {

        for (int i = 0; i < input.length / 2; i++) {
            int temp = input[i];
            input[i] = input[input.length - 1 - i];
            input[input.length - 1 - i] = temp;
        }

        for (int i = 0; i < input.length; i++) {
            for (int j = input.length - 1; j > 0; j--) {
                if (!(input[j - 1] < 0) && input[j] < 0) {
                    int swap = input[j - 1];
                    input[j - 1] = input[j];
                    input[j] = swap;
                }
            }
        }

        return input;
    }

    /**
     * Remove (filter) all values which are smaller than (input max element - 10)
     * @param input non-null immutable array of ints
     * @return The result array should not contain empty cells!
     */
    @Override
    public int[] filter (final int[] input) {
        int indexes = 0;

        for (int i = 0; i < input.length; i++) {
            if (input[i] > -10 && input[i] < 0) {
                indexes++;
            }
        }

        int[] result = new int[input.length - indexes];

        for (int i = 0, k = 0; i < input.length; i++) {
            if (!(input[i] > -10 && input[i] < 0)) {
                result[k] = input[i];
                k++;
            }
        }
        return result;
    }

    /**
     * Insert values into input array at a specific index.
     * @param input non-null immutable array of ints.
     * @param startInclusive the index of input at which the first element from values array should be inserted
     * @param values the values to be inserted from startInclusive index
     * @return new array containing the combined elements of input and values
     * @throws IllegalArgumentException when startInclusive is out of bounds for input
     */
    @Override
    public int[] insertValues (final int[] input, int startInclusive, int[] values) throws IllegalArgumentException {
        if (startInclusive < 0) {
            throw new IllegalArgumentException();
        }

        int[] result = new int[input.length + values.length];

        for (int i = 0; i < startInclusive; i++) {
            result[i] = input[i];
        }

        for (int i = 0; i < values.length; i++) {
            result[i + startInclusive] = values[i];
        }

        for (int i = startInclusive + values.length, k = 0; i < result.length; i++) {
            result[i] = input[values.length - 1 + k];
            k++;
        }

        return result;
    }

    /**
     * Merge two sorted input and input2 arrays so that the return values are also sorted
     * @param input first non-null array
     * @param input2 second non-null array
     * @return new array containing all elements sorted from input and input2
     * @throws IllegalArgumentException if either input or input are not sorted ascending
     */
    @Override
    public int[] mergeSortedArrays (int[] input, int[] input2) throws IllegalArgumentException {
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] > input[i + 1]) throw new IllegalArgumentException();
        }

        for (int i = 0; i < input2.length - 1; i++) {
            if (input2[i] > input2[i + 1]) throw new IllegalArgumentException();
        }

        int[] result = new int[input.length + input2.length];

        int i, i1, i2;
        i = i1 = i2 = 0;

        for (; i1 < input.length && i2 < input2.length; i++) {
            if (input[i1] < input2[i2]) {
                result[i] = input[i1++];
            } else {
                result[i] = input2[i2++];
            }
        }

        for (; i1 < input.length; i++) {
            result[i] = input[i1++];
        }

        for (; i2 < input2.length; i++) {
            result[i] = input2[i2++];
        }

        return result;
    }

    /**
     * In order to execute a matrix multiplication, in this method, please validate the input data throwing exceptions for invalid input. If the the
     * input params are satisfactory, do not throw any exception.
     *
     * Please review the matrix multiplication https://www.mathsisfun.com/algebra/matrix-multiplying.html
     * @param leftMatrix the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @throws NullPointerException when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */

    @Override
    public void validateForMatrixMultiplication (int[][] leftMatrix, int[][] rightMatrix) throws NullPointerException, IllegalArgumentException {
        validateMatrixHelper(leftMatrix);
        validateMatrixHelper(rightMatrix);
        if (leftMatrix.length == 0 || rightMatrix.length == 0) {
            throw new IllegalArgumentException("Matrices cannot be empty");
        }
        if (leftMatrix[0].length != rightMatrix.length) {
            throw new IllegalArgumentException("Invalid matrix dimensions for multiplication");
        }
    }

    /**
     * Perform the matrix multiplication as described in previous example Please review the matrix multiplication
     * https://www.mathsisfun.com/algebra/matrix-multiplying.html
     * @param leftMatrix the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @throws NullPointerException when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */
    @Override
    public int[][] matrixMultiplication (final int[][] leftMatrix, final int[][] rightMatrix) throws NullPointerException, IllegalArgumentException {
        int rowsA = leftMatrix.length;
        int colsA = leftMatrix[0].length;
        int colsB = rightMatrix[0].length;
        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += leftMatrix[i][k] * rightMatrix[k][j];
                }
            }
        }

        return result;
    }

    /**
     * Return only distinct values in an array.
     * @param input non-null immutable array of ints.
     */
    @Override
    public int[] distinct (final int[] input) {
        int[] uniqueArray = new int[input.length];
        int uniqueCount = 0;

        for (int i = 0; i < input.length; i++) {
            boolean isUnique = true;
            for (int j = 0; j < i; j++) {
                if (input[i] == input[j]) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                uniqueArray[uniqueCount++] = input[i];
            }
        }

        uniqueArray = Arrays.copyOf(uniqueArray, uniqueCount);
        return uniqueArray;
    }

    // Helper for validateForMatrixMultiplication
    public void validateMatrixHelper (int[][] matrix) {
        for (int[] row : matrix) {
            if (row == null) {
                throw new NullPointerException("Matrix rows cannot be null");
            }
            if (row.length != matrix[0].length) {
                throw new IllegalArgumentException("Matrix rows must have equal length");
            }
            for (int val : row) {
                if (val == 0 && row.length > 1) {
                    throw new IllegalArgumentException("Matrix cannot contain null values");
                }
            }
        }
    }
}
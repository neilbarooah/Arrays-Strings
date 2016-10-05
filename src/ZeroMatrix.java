/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column
 * is set to 0
 *
 * We need a second matrix which flags the zero locations. Then, do a second pass through the
 * matrix to set the zeros. This takes O(MN) space.
 * Created by neilbarooah on 17/09/16.
 */
public class ZeroMatrix {

    void setZeros(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];

        // store the row and column index with value 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        // nullify rows
        for (int i = 0; i < row.length; i++) {
            if (row[i]) {
                nullifyRow(matrix, i);
            }
        }

        // nullify columns
        for (int j = 0; j < column.length; j++) {
            if (column[j]) {
                nullifyColumn(matrix, j);
            }
        }
    }

    void nullifyRow(int[][] matrix, int row) {
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[row][j] = 0;
        }
    }

    void nullifyColumn(int[][] matrix, int col) {
        for (int k = 0; k < matrix.length; k++) {
            matrix[k][col] = 0;
        }
    }

    /* we can reduce the space to O(1) by using the first row as a replacement for the row array
    and the first column as the replacement for the column array.
     */
    void setZerosEfficient(int[][] matrix) {
        boolean rowHasZero = false;
        boolean colHasZero = false;

        // check if first row has a zero
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowHasZero = true;
                break;
            }
        }

        // check if the first column has a zero
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colHasZero = true;
                break;
            }
        }

        // check for zeros in the rest of the array
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // nullify rows based on values in the first column
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                nullifyRow(matrix, i);
            }
        }

        // nullify cols based on values in the first row
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                nullifyColumn(matrix, j);
            }
        }

        // nullify first row
        if (rowHasZero) {
            nullifyRow(matrix, 0);
        }

        if (colHasZero) {
            nullifyColumn(matrix, 0);
        }
    }
}


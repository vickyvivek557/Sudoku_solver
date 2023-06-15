import java.util.Scanner;

public class Sudoku {    
    public static boolean solveSudoku(int[][] matrix) {
        int row = -1;
        int col = -1;
        boolean isEmpty = false;
        
        // Find the first empty cell
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrix[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = true;
                    break;
                }
            }
            if (isEmpty) {
                break;
            }
        }
        
        // If no empty cells, the Sudoku is solved
        if (!isEmpty) {
            return true;
        }
        
        // Try different numbers for the empty cell
        for (int num = 1; num <= 9; num++) {
            if (isSafe(matrix, row, col, num)) {
                matrix[row][col] = num;
                
                // Recursively solve the Sudoku
                if (solveSudoku(matrix)) {
                    return true;
                }
                
                // If the solution is not valid, then make it zero (BACKTRACK);
                matrix[row][col] = 0;
            }
        }
        
        return false;
    }
    
    private static boolean isSafe(int[][] matrix, int row, int col, int num) {
        // Check if the number exists in the same row
        for (int j = 0; j < 9; j++) {
            if (matrix[row][j] == num) {
                return false;
            }
        }
        
        // Check if the number exists in the same column
        for (int i = 0; i < 9; i++) {
            if (matrix[i][col] == num) {
                return false;
            }
        }
        
        // Check if the number exists in the same 3x3 box
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[boxStartRow + i][boxStartCol + j] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = new int[9][9];

        //Taking sudoku question as input in 2D array
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        //calling function to solve sudoku
        boolean ans = solveSudoku(matrix);

        //printing answer
        System.out.println("Answer");
        if(!ans){
            System.out.println("This is not a valid Sudoku");
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
            

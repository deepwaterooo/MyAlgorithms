import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.BitSet;

public class solveSudoku {
    public static class Solution {
        public void solveSudoku(char[][] board) {
            
        }
    }

    public static void main(String[] args){
        Solution result = new Solution();
        char [][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        result.solveSudoku(board);

        System.out.println("");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) 
                System.out.print(board[i][j] + ", ");
            System.out.println("");
        }
    }
}

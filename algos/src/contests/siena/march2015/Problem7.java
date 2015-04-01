package contests.siena.march2015;

import java.util.*;

// Remember to make sure the name of the file & class match!
public class Problem7
{
    static HashSet<Integer> known;
    static boolean alreadySolved = false;
    static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        sc.nextLine();
        for (int trialCount = 0; trialCount < K; trialCount++)
        {
            alreadySolved = false;
            known = new HashSet<Integer>();
            int[][] board = new int[9][9];
            int startR = -1;
            int startC = -1;
            
            for (int i = 0; i < board.length; i++) {
                String[] parts = sc.nextLine().split(" ");
                for (int j = 0; j < board.length; j++) {
                	// Read in the input and put it into a 2D array.
                    board[i][j] = Integer.parseInt(parts[j]);
                    
                    // If the square is not 0, then it's a number we should pay attention to.
                    // Add that number to a list of known, unmovable values in the known HashSet.
                    if (board[i][j] != 0) {
                        known.add(board[i][j]);
                    }
                }
            }
            
            // If we know we have a 1, then we know where to start.
            if (known.contains(1)) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] == 1) {
                        	// Find the location of the 1, located at i, j.
                        	// Run the solving algorithm from i, j, starting at count 1.
                            solve(i, j, board, 1);
                            printBoard(board);
                            System.out.println("Done.");
                        }
                    }
                }
            }
            else {
            	// Otherwise, we have no idea where to start.  So start everywhere.
                known.add(1);
                outer: for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (board[i][j] == 0) {
                            board[i][j] = 1;
                            startR = i;
                            startC = j;
                            
                            //Put a 1 down in every single empty spot and run the solving algorithm
                            solve(startR, startC, board, 1);
                            if (alreadySolved) {
                            	// This solution happens to have worked!  We're done!
                                printBoard(board);
                                System.out.println("Done.");
                                break outer;
                            }
                            
                            // If it didn't work, then reset the board position to 0 and continue.
                            board[i][j] = 0;
                        }
                    }
                }
            }
        }
        sc.close(  );
    }

    public static void solve (int r, int c, int[][] board, int count) {
        if (r < 0 || c < 0 || r >= board.length || c >= board.length) {
        	// If we're out of bounds then we clearly can't step this way.
            return;
        }

        if (known.contains(count)) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == count) {
                    	// If we know that the step we're on exists already on the board, if we see
                    	// that our current place on the board doesn't match up with where the existing
                    	// number should be, then this is bad!  Invalid!
                        if (r != i || c != j) return;
                    }
                }
            }
        }
        if (board[r][c] != 0 && !known.contains(count)) {
            // If this position we're looking at already has a number in it, then
        	// we can't cross over ourselves.  Invalid!
            return;
        }
        
        if (count == 81) {	
        	// Got to 81, which means that this is a solution!
            board[r][c] = 81;
            alreadySolved = true;
            return;
        }
        else {
            for (int[] dir : dirs) {
            	// Set this position to be count, showing that we've stepped here.
                board[r][c] = count;
                
                // Try moving to every single direction.
                solve(r + dir[0], c + dir[1], board, count + 1);
                if (alreadySolved) {
                	// It worked!?  Let's get out of here!
                    return;
                }
                
                if (!known.contains(count)) {
                    // If it didn't work and it's not a known value, then set it to 0.
                	// If it was a known value, then we can't set it to 0 because it has to be where it is.
                	board[r][c] = 0;
                }
            }
        }

    }

    // Prettily prints the board with no [] or ,.
    public static void printBoard (int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {

                if (board[i][j] < 10) {
                    System.out.print("0" + board[i][j]+((j!=10)?" ":""));
                } else {

                    System.out.print(board[i][j] + ((j!=10)?" ":""));
                }
            }
            System.out.println();
        } 
    }

}

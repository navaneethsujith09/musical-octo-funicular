import java.util.Scanner;
import java.io.File;
import java.io.IOException;

class ThreadTheMazeFerrante {

	private final static int MAXROW = 12;
	private final static int MAXCOL = 12;
	private final static char BLANK = ' ';
	Scanner keyboard = new Scanner(System.in);  // Used to pause the output
	
	public static void main(String[] args) {

		ThreadTheMazeFerrante mazeWalker = new ThreadTheMazeFerrante();

		char[][] maze = new char[MAXROW + 1][MAXCOL + 1];

		mazeWalker.loadMaze(maze);
		mazeWalker.traceMazeStepByStep(maze, 6, 6, 1, "Centered...");
		// mazeWalker.traceMaze(maze, 6, 6);
	}

	public void loadMaze(char[][] maze) {

		Scanner inFile = null;

		try {
			inFile = new Scanner(new File("mazeData.txt"));
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	    for (int row = 1; row <= MAXROW; row++) {

			String line = inFile.nextLine();

			for (int col = 1; col <= MAXCOL; col++)
				maze[row][col] = line.charAt(col-1);

		}
	}

	public void printMaze(char[][] maze) {

		for (int row = 1; row <= MAXROW; row++) {

			for (int col = 1; col <= MAXCOL; col++)
		        System.out.print("" + maze[row][col]);
			System.out.println();
		}
		System.out.println();
	}

	/*
	*  Will attempt to find a path out of the maze.  A path will
	*  be marked with the ! marker.  The method makes a copy of
	*  the array each time so that only the path out will be
	*  marked, otherwise extra ! markers will appear in the
	*  answer. The function is recursive.
	*
	*  char[][] maze is an array representing the maze
	*  row, col is the current position of the player in the maze
	*  level is the depth of recursion
	*  dir is the UP, RIGHT, DOWN, LEFT direction being tried
	*/
	public void traceMazeStepByStep(char[][] maze, int row, int col, int level, String dir) {

		System.out.print("Level " + level + " call to traceMaze going " + dir);
		
		keyboard.nextLine();

		// make a local copy of maze[][] to work with
		char[][] mazeCopy = new char[maze.length][maze[0].length];
		for (int r = 0; r < mazeCopy.length; r++)
			for (int c = 0; c < mazeCopy[0].length; c++)
				mazeCopy[r][c] = maze[r][c];

		if (mazeCopy[row][col] == BLANK) {
			
			mazeCopy[row][col] = '!';
			System.out.println(" --> Move is successful!\n");
			printMaze(mazeCopy);
			
			if (row == 1 || row == MAXROW) {
				System.out.println("Maze is solved!\n");
				printMaze(mazeCopy); // base case: Maze Solved!
			}
			else {
				traceMazeStepByStep(mazeCopy, row - 1, col, level + 1, "UP...");
				traceMazeStepByStep(mazeCopy, row, col + 1, level + 1, "RIGHT...");
				traceMazeStepByStep(mazeCopy, row + 1, col, level + 1, "DOWN...");
				traceMazeStepByStep(mazeCopy, row, col - 1, level + 1, "LEFT...");
			}
		}
		else { // base case: Can't go this way.  Do nothing.
			System.out.println(" --> Can't go this way!\n");
			printMaze(mazeCopy);
		}
	}

  // Same method without printing at each step
	public void traceMaze(char[][] maze, int row, int col) {

		// make a local copy of maze[][] to work with
		char[][] mazeCopy = new char[maze.length][maze[0].length];
		for (int r = 0; r < mazeCopy.length; r++)
			for (int c = 0; c < mazeCopy[0].length; c++)
				mazeCopy[r][c] = maze[r][c];

		if (mazeCopy[row][col] == BLANK) {			
			mazeCopy[row][col] = '!';
			
			if (row == 1 || row == MAXROW)
				printMaze(mazeCopy); // base case: Maze Solved!
			else {
				traceMaze(mazeCopy, row - 1, col);
				traceMaze(mazeCopy, row, col + 1);
				traceMaze(mazeCopy, row + 1, col);
				traceMaze(mazeCopy, row, col - 1);
			}
		}
	}

	public static boolean isSafe(int[]board,int col, int row )
    public static boolean nQueens(int[][] board, int col){
		for(int i = 0; i < 6 i++){
			if(isSafe(board, col, i)){
				board[col][i] = 1;
				if(nQueens(board, col+1)) {
					return true;
				}
				board[col][i] = 0;

			}
		}
		return false;



	}

}

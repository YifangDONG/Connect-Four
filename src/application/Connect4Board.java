package application;

public class Connect4Board {
	
	
	final int NUMBER_OF_COLUMNS = 7;
	final int NUMBER_OF_ROWS = 6;
	final char EMPTY_SPACE=' ';
	private char[][] board;
	
	public Connect4Board() {
		board = new char[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		for(int i = 0; i < NUMBER_OF_ROWS; i++) {
			for(int j = 0; j < NUMBER_OF_COLUMNS; j++) {
				board[i][j] = EMPTY_SPACE;
			}
		}
	}
	public int play(int column, char piece) {
		if(column < 0 || column >= NUMBER_OF_COLUMNS) {
			System.out.println("Illegal input of column");
			return -1;
		}
		else if (!free_space_in_column(column)) {
			System.out.println("the column is full");
			return -1;
		}
		else {
			int i = 0;
			while(get_char_at_location(i,column)!= EMPTY_SPACE) {
				i++;
			}
			board[i][column] = piece;
			return i;
		}
	}
	
	public boolean free_space_in_column(int column) {
		if(get_char_at_location(NUMBER_OF_ROWS-1,column) == EMPTY_SPACE) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean free_space_in_board() {
		for(int i = 0; i < NUMBER_OF_COLUMNS; i++) {
			if (free_space_in_column(i))
				return true;
		}
		return false;	
	}
	
	public char get_char_at_location(int row, int column) {
		return board[row][column];
	}
	
	public boolean isWin(char piece) {
		//4 x in one column
		int l;
		for(int i = 0; i < NUMBER_OF_COLUMNS; i++) {
			l = 0;
			for (int j = 0; j < NUMBER_OF_ROWS; j++) {
				if (board[j][i] == piece)
					l++;
				else
					l = 0;
				if (l == 4)
					return true;
			}
		}
		//4 x in one row
		for(int i = 0; i < NUMBER_OF_ROWS; i++) {
			l = 0;
			for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
				if (board[i][j] == piece)
					l++;
				else
					l = 0;
				if (l == 4)
					return true;
			}
		}
		//from left-low to right-high
		for (int k = -2; k < 4; k++) {
			l = 0;
			for (int i = k,j = 0; i < NUMBER_OF_COLUMNS && j < NUMBER_OF_ROWS; i++,j++) {
				if ( i < 0)
					continue;
				if (board[j][i] == piece)
					l++;
				else
					l = 0;
				if (l == 4)
					return true;
			}
		}
		//from left-high to right-low
		for (int k = -2; k < 4; k++) {
			l = 0;
			for (int i = k,j = NUMBER_OF_ROWS-1; i < NUMBER_OF_COLUMNS && j >=0; i++,j--) {
				if ( i < 0)
					continue;
				if (board[j][i] == piece)
					l++;
				else
					l = 0;
				if (l == 4)
					return true;
			}
		}
		return false;
	}
}

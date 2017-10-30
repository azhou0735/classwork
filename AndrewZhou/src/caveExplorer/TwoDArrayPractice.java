package caveExplorer;

public class TwoDArrayPractice {

	public static void main(String[] args) {
		String[][] pic = new String[6][10];
		for(int row = 0; row < pic.length; row++) {
			for(int col = 0; col < pic[row].length; col++){
				pic[row][col] = " ";
			}
		}
		drawBox(pic,2,9);
		print(pic);
	}

	private static void drawBox(String[][] pic, int row, int col) {
		drawSlot(pic,row,col);
		drawSlot(pic,row+1,col);
		for(int j = col-1 ; j <= col+1; j++) {
			drawIfInBounds("_",pic,row-1,j);
		}
		drawIfInBounds("_",pic,row+1,col);
	}
	
	private static void drawIfInBounds(String string, String[][] pic, int row, int col) {
		if(row < pic.length && row > -1) {
			if(col < pic[row].length && col > -1) {
				pic[row][col] = string;
			}
		}
		
	}

	private static void drawSlot(String[][] pic, int row, int col) {
		if(row > -1 && row < pic.length) {
			if(col+1 < pic[row].length) {
				pic[row][col+1] = "|";
			}
			if(col-1 > -1) {
				pic[row][col-1] = "|";
			}
		}
	}

	private static void drawVerticalLine(String[][] pic, int col) {
		for(int row = 0; row < pic.length; row++) {
			/*if(pic[row][col] == "-")
				pic[row][col] = "+";
			else*/
				pic[row][col] = "|";
		}
		
	}

	private static void drawHorizontalLine(String[][] pic, int row) {
		for(int col = 0; col < pic[row].length; col++) {
			pic[row][col] = "-";
		}
		
	}

	private static void print(String[][] pic) {
		String returnString = "";
		for(int row = 0; row < pic.length; row++) {
			for(int col = 0; col < pic[row].length; col++){
				returnString += pic[row][col];
			}
			//System.out.println(returnString);
			//returnString = "";
			returnString += "\n";
		}
		System.out.print(returnString);
	}

}

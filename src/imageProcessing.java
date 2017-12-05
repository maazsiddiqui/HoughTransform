import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class imageProcessing {
	
	private int numRows, numCols, minVal, maxVal;
	private int[][] imgAry; // dynamic array
	private String inFile; // pointer to argv[1]

	public imageProcessing(String inFile) throws FileNotFoundException {  // Constructor
		
		this.inFile = inFile;
			
		Scanner dataFile = new Scanner(new File(inFile));
			
		numRows = dataFile.nextInt();
		numCols = dataFile.nextInt();
		minVal = dataFile.nextInt();
		maxVal = dataFile.nextInt();

		imgAry = new int[numRows][numCols];
  		
		for(int i = 0; i < numRows; i++) {
  			for(int j = 0; j < numCols; j++)
	    		imgAry[i][j] = dataFile.nextInt();
		}
			
			dataFile.close(); 	// input file closed
		}
		
		int getNumRows() {
			return numRows;
		}

		int getNumCols() {
			return numCols;
		}
		
		int[][] getImgAry() {
			return imgAry;
		}

}

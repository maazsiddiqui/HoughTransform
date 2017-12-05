import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class houghTransform {

	public class xyCoord { int x, y; }
	private xyCoord point;
	private String outFile; // pointer to argv[2]
	private String outFile2; // pointer to argv[3]
	private	double pi;
	private	int angleInDegree;
	private	double angleInRadians;
	private	int houghRows; 	// = 180 // rows are used for angles in degree from 0 to 179
	private	int houghCols; 	// cols are used for distances 
					 	// cols = sqrt [(numRows)^2 + (numCols)^2] (convert to int)
					 
	private	int houghMinVal;
	private	int houghMaxVal;
	int[][] houghAry;
		
	public houghTransform(int rows, int cols, int[][] imgAry, String outFile, String outFile2) {
			
		this.outFile = outFile;
		this.outFile2 = outFile2;
		int dist = 0;			
		pi = Math.PI;
		point = new xyCoord();
		houghRows = 180;
		houghCols = (int) Math.sqrt((rows*rows) + (cols*cols));
		houghCols *= 2;
			
		houghAry = new int[houghRows][houghCols];
 			
		for(int i = 0; i < rows; i++) {
  			for(int j = 0; j < cols; j++) {
				
				if(imgAry[i][j] > 0) {
					point.x = i;
					point.y = j;
					angleInDegree = 0;
					
					while (angleInDegree < 180) {
						angleInRadians = angleInDegree*pi / (180.0);
						dist = computeDistance(point, angleInRadians);							
						houghAry[angleInDegree ][dist + houghCols/2]++; // size doubled for easy view
						angleInDegree++;
					}
				}
			}

		}
			
		findMin();
		findMax();

	}
		
		int computeDistance(xyCoord pt, double angleInRad) {
			double t = angleInRad - Math.atan((double)point.y/(double)point.x) - (pi/2);
			double distance = Math.sqrt((point.x*point.x) + (point.y*point.y)) * Math.cos(t);
			return (int) Math.abs(distance);	
		}
		
		void houghPrint() throws IOException {
		
			PrintWriter out = new PrintWriter(new FileWriter(outFile));
			
			out.println(houghRows + " " + houghCols + " " + houghMinVal + " " + houghMaxVal);

			for(int i = 0; i < houghRows; i++) {
	  			for(int j = 0; j < houghCols; j++) {
					out.print(houghAry[i][j] + " ");
				}
				out.println();
			}
			
			out.close();

		}
		
		void houghPrettyPrint() throws IOException {
		
			PrintWriter out = new PrintWriter(new FileWriter(outFile2));

			for(int i = 0; i < houghRows; i++) {
	  			for(int j = 0; j < houghCols; j++) {
	  				if(houghAry[i][j] > 0) out.print(". ");
	  				else out.print("  ");
				}
				out.println();
			}
			
			out.close();

		}
		
		void findMin() {
			houghMinVal = 0;	
			for(int i = 0; i < houghRows; i++) {
	  			for(int j = 0; j < houghCols; j++) {
	  				if(houghMinVal > houghAry[i][j]) houghMinVal = houghAry[i][j];
				}
			}
		}
		
		void findMax() {
			houghMaxVal = 0;	
			for(int i = 0; i < houghRows; i++) {
	  			for(int j = 0; j < houghCols; j++) {
	  				if(houghAry[i][j] > houghMaxVal) houghMaxVal = houghAry[i][j];
				}
			}
		}

}

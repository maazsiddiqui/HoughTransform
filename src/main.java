import java.io.FileNotFoundException;
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		
		imageProcessing imgProcess = new imageProcessing(args[0]);

		houghTransform hTrans = new houghTransform (imgProcess.getNumRows(),
													imgProcess.getNumCols(),
													imgProcess.getImgAry(),
													args[1],
													args[2]);

		hTrans.houghPrint();
		hTrans.houghPrettyPrint();

	}

}

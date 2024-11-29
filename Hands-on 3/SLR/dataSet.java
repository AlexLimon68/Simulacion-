public class dataSet{

	public static double[] dataX = new double[9];
	public static double[] dataY = new double[9];

	public dataSet(){
		
		dataX[0] = 23;
		dataX[1] = 26;
		dataX[2] = 30;
		dataX[3] = 34;
		dataX[4] = 43;
		dataX[5] = 48;
		dataX[6] = 52;
		dataX[7] = 57;
		dataX[8] = 58;
		
		dataY[0] = 651;
		dataY[1] = 762;
		dataY[2] = 856;
		dataY[3] = 1063;
		dataY[4] = 1190;
		dataY[5] = 1298;
		dataY[6] = 1421;
		dataY[7] = 1440;
		dataY[8] = 1518;
	}
			
	public static double[] setX(){
		return dataX;
	}

	public static double[] setY(){
		return dataY;
	}
}
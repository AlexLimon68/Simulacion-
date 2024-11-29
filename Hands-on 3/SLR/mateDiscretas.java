public class mateDiscretas{

	public static dataSet ds = new dataSet();

	public mateDiscretas(){}

	public static double sumatoriaX(){
		
		double sumaX = 0;

		double[] dataX = new double[9];
		dataX = ds.setX();
		
		for(int i = 0; i < dataX.length; i++){
			sumaX += dataX[i];
		}

		return sumaX;		
	}

	public static double sumatoriaY(){

		double sumaY = 0;

		double[] dataY = new double[9];
		dataY = ds.setY();

		for(int i = 0; i < dataY.length; i ++){
			sumaY += dataY[i];
		}
		
		return sumaY;
	} 


	public static double sumatoriaXY(){
		
		double suma = 0;

		double[] dataX = new double [9];
		dataX = ds.setX();

		double[] dataY = new double [9]; 
		dataY = ds.setY();

		for(int i = 0; i < dataX.length; i++){
			suma += (dataX[i] * dataY[i]);
		}

		return suma;
	}

	public static double sumatoriaX2(){

		double suma = 0;
		
		double[] dataX = new double[9];
		dataX = ds.setX();

		for(int i = 0; i < dataX.length; i++){
			suma += (dataX[i] * dataX[i]);
		}

		return suma;
	}
}
public class algebra{

	public static dataSet dataSet = new dataSet();
	public static mateDiscretas md = new mateDiscretas();

	public algebra(){}

	public static double promedioX(){

		double prom = 0;

		double[] dataX = new double[9];
		dataX = dataSet.setX();

		for(int i = 0; i < dataX.length; i++){
			prom += dataX[i];
		}
		return (prom / dataX.length);
	}

	public static double promedioY(){

		double prom = 0;

		double[] dataY = new double[9];
		dataY = dataSet.setY();

		for(int i = 0; i < dataY.length; i++){
			prom += dataY[i];
		}
		return (prom / dataY.length);
	}
	
	public static void ecuacionRegresion(double data){

		double b0 = 0, b1 = 0;

		b1 = ( (9 * md.sumatoriaXY()) - (md.sumatoriaX() * md.sumatoriaY()) ) / ( (9 * md.sumatoriaX2()) - (md.sumatoriaX() * md.sumatoriaX()) ); 
		b0 = promedioY() - ( b1 * promedioX() );

		System.out.println("Ecuacion de regresion: " + b0 + " + " + b1 +" x");

		System.out.println("Prediccion: " + (b0 + (b1 * data) ));
					
	}

}
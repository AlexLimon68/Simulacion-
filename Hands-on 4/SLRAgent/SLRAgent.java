package examples.SLRAgent;

import jade.core.Agent;

public class SLRAgent extends Agent {









	public static double[] setX(){

		double[] dataX = new double[9];
		
		dataX[0] = 23;
		dataX[1] = 26;
		dataX[2] = 30;
		dataX[3] = 34;
		dataX[4] = 43;
		dataX[5] = 48;
		dataX[6] = 52;
		dataX[7] = 57;
		dataX[8] = 58;
		
		return dataX;
	}

	public static double[] setY(){

		double[] dataY = new double[9];

		dataY[0] = 651;
		dataY[1] = 762;
		dataY[2] = 856;
		dataY[3] = 1063;
		dataY[4] = 1190;
		dataY[5] = 1298;
		dataY[6] = 1421;
		dataY[7] = 1440;
		dataY[8] = 1518;

		return dataY;
	}

	public static double[] predicciones(){

		double[] datos = new double [5];
		
		datos[0] = 63;
		datos[1] = 67;
		datos[2] = 70;
		datos[3] = 72;
		datos[4] = 77;		
		
		return datos;
	}







	public static double sumatoriaX(){
		
		double sumaX = 0;

		double[] dataX = new double[9];
		dataX = setX();
		
		for(int i = 0; i < dataX.length; i++){
			sumaX += dataX[i];
		}

		return sumaX;		
	}

	public static double sumatoriaY(){

		double sumaY = 0;

		double[] dataY = new double[9];
		dataY = setY();

		for(int i = 0; i < dataY.length; i ++){
			sumaY += dataY[i];
		}
		
		return sumaY;
	} 


	public static double sumatoriaXY(){
		
		double suma = 0;

		double[] dataX = new double [9];
		dataX = setX();

		double[] dataY = new double [9]; 
		dataY = setY();

		for(int i = 0; i < dataX.length; i++){
			suma += (dataX[i] * dataY[i]);
		}

		return suma;
	}

	public static double sumatoriaX2(){

		double suma = 0;
		
		double[] dataX = new double[9];
		dataX = setX();

		for(int i = 0; i < dataX.length; i++){
			suma += (dataX[i] * dataX[i]);
		}

		return suma;
	}










	public static double promedioX(){

		double prom = 0;

		double[] dataX = new double[9];
		dataX = setX();

		for(int i = 0; i < dataX.length; i++){
			prom += dataX[i];
		}
		return (prom / dataX.length);
	}

	public static double promedioY(){

		double prom = 0;

		double[] dataY = new double[9];
		dataY = setY();

		for(int i = 0; i < dataY.length; i++){
			prom += dataY[i];
		}
		return (prom / dataY.length);
	}


	


	public void setup () {

		double b0 = 0, b1 = 0;
		double[] x = new double [5];

		x = predicciones();

		b1 = ( (9 * sumatoriaXY()) - (sumatoriaX() * sumatoriaY()) ) / ( (9 * sumatoriaX2()) - (sumatoriaX() * sumatoriaX()) ); 
		b0 = promedioY() - ( b1 * promedioX() );

		System.out.println("Ecuacion de regresion: " + b0 + " + " + b1 +" x");

		for(int i = 0; i < 5; i++) System.out.println("Prediccion x = " + x[i] + ": " + (b0 + (b1 * x[i]) ));
		
		doDelete();
	}


}
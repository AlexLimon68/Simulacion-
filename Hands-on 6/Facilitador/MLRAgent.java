package examples.Facilitador;

import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class MLRAgent extends Agent{

	public void setup (){
	
		ACLMessage recibido = this.blockingReceive();
		ACLMessage respuesta;
	
		if(recibido != null){

			String m = recibido.getContent();
			double [][] x = dataSet(m);
			String n = resultados(x);

			System.out.println("Mensaje recibido, enviando Multiple Linear Regression: ");
			respuesta = recibido.createReply();
			respuesta.setContent("mensaje -->" + n);
			this.send(respuesta);
			
		}
		else System.out.println("No se recibió el mensaje.");

		doDelete();
		
	}

	public static String resultados(double [][] x){
		double [] respuesta = new double[5];
		double [][] aux = x;

		aux = xX(x);
		aux = inversa3(aux);

		double [] bet = multi(x);

		bet = beta(aux, bet);

		double [] p1 = dataP1();
		double [] p2 = dataP2();
		

		for(int i = 0; i < 5; i++) respuesta[i] = prediccion(bet, p1[i], p2[i]);
		
		return crearMensaje(respuesta);
	}

	public static double[][] dataSet(String mensaje){
		int ren = 0;
		char c;
		String aux = "";
		String m = "";
		int count = 0;
	
		for(int i = 0; i < mensaje.length(); i++){
			c = mensaje.charAt(i);
			if(Character.isDigit(c)){
				m += c;
				count ++;
			}
			else{
				aux = m;
				m = "";
				i = mensaje.length() - 1;
			}
		}

		ren = Integer.parseInt(aux);
		double [][] x = new double[ren][4];
		count ++;
		int o = 0;
		int a = 0;

		for(int i = count; i < mensaje.length(); i++){
			
			if(o == ren){
				o = 0;
				a++;
			}

			c = mensaje.charAt(i);
			if(Character.isDigit(c) || c == '.'){
				m += c;
			}
			else{
				x[o][a] = Double.parseDouble(m);
				o++;
				m = "";
			}
		}

		return x;
	}


	public static String crearMensaje (double [] x){
		String mensaje = "";

		for(int i = 0; i < x.length; i ++){
			mensaje += String.valueOf(x[i]) + " "; 
		}

		return mensaje;
	}


	public static double[][] xX (double[][] x){

        	double[][] suma = new double[3][3];
        	double aux = 0;
	        double l = 0;

        	for (int i = 0; i < (x[0].length - 1); i++){
            		for(int j = 0; j < (x[0].length - 1); j++){
                		aux = 0;
                		for(int k = 0; k < x.length; k++){
                    			aux += x[k][i] * x[k][j];
                    			suma[i][j] = aux;
                		}
            		}
		}
        	return suma;
	}



	public static double[][] inversa3 (double [][] x){

        	double [][] respuesta = new double[3][3];
        	respuesta [0][0] = (x[1][1] * x[2][2]) - (x[2][1] * x[1][2]);
        	respuesta [0][1] = ((x[0][1] * x[2][2]) - (x[0][2] * x[2][1])) * (-1);
       		respuesta [0][2] = (x[0][1] * x[1][2]) - (x[0][2] * x[1][1]);

        	respuesta [1][0] = ((x[1][0] * x[2][2]) - (x[1][2] * x[2][0])) * (-1);
        	respuesta [1][1] = (x[0][0] * x[2][2]) - (x[0][2] * x[2][0]);
        	respuesta [1][2] = ((x[0][0] * x[1][2]) - (x[0][2] * x[1][0])) * (-1);

        	respuesta [2][0] = (x[1][0] * x[2][1]) - (x[1][1] * x[2][0]);
        	respuesta [2][1] = ((x[0][0] * x[2][1]) - (x[0][1] * x[2][0])) * (-1);
        	respuesta [2][2] = (x[0][0] * x[1][1]) - (x[0][1] * x[1][0]);

        	double det = (x[0][0]*((x[1][1] * x[2][2]) - (x[1][2] * x[2][1]))) - (x[0][1]*((x[1][0] * x[2][2]) - (x[1][2] * x[2][0]))) + (x[0][2]*((x[1][0] * x[2][1]) - (x[1][1] * x[2][0])));
        	double[][] y = respuesta;

        	for(int i = 0; i < 3; i ++){
            		for(int j = 0; j < 3; j++) y[i][j] = 1/det * respuesta[i][j];
        	}

        	return y;
    	}



	public static double [] multi (double[][] x){

        	double [] a = new double[x[0].length - 1];

        	for(int i = 0; i < (x[0].length - 1); i++){
            		for(int j = 0; j < x.length; j++) a[i] += x[j][i] * x[j][(x[0].length - 1)];
        	}

        	return a;
    	}



	public static double [] beta (double [][]x, double [] y){

        	double [] bet = new double[3];
        	double aux = 0;

        	for(int i = 0; i < 3; i++){
            		for(int j = 0; j < 3; j++) {
                		aux += x[i][j] * y[j];
            		}
            		bet[i] = aux;
            		aux = 0;
        	}

        	return bet;
    	}



	public static double prediccion(double [] b, double d1, double d2){
		
		return (b[0] + (b[1] * d1) + (b[2] * d2));
	}



	public static double [][] dataX (){

		double[][] x = new double [17][3];

		x[0][0] = 1;
		x[1][0] = 1;
		x[2][0] = 1;
		x[3][0] = 1;
		x[4][0] = 1;
		x[5][0] = 1;
		x[6][0] = 1;
		x[7][0] = 1;
		x[8][0] = 1;
		x[9][0] = 1;
		x[10][0] = 1;
		x[11][0] = 1;
		x[12][0] = 1;
		x[13][0] = 1;
		x[14][0] = 1;
		x[15][0] = 1;
		x[16][0] = 1;

		x[0][1] = 41.9;
		x[1][1] = 43.4;
		x[2][1] = 43.9;
		x[3][1] = 44.5;
		x[4][1] = 47.3;
		x[5][1] = 47.5;
		x[6][1] = 47.9;
		x[7][1] = 50.2;
		x[8][1] = 52.8;
		x[9][1] = 53.2;
		x[10][1] = 56.7;
		x[11][1] = 57;
		x[12][1] = 63.5;
		x[13][1] = 65.3;
		x[14][1] = 71.1;
		x[15][1] = 77;
		x[16][1] = 77.8;

		x[0][2] = 29.1;
		x[1][2] = 29.3;
		x[2][2] = 29.5;
		x[3][2] = 29.7;
		x[4][2] = 29.9;
		x[5][2] = 30.3;
		x[6][2] = 30.5;
		x[7][2] = 30.7;
		x[8][2] = 30.8;
		x[9][2] = 30.9;
		x[10][2] = 31.5;
		x[11][2] = 31.7;
		x[12][2] = 31.9;
		x[13][2] = 32;
		x[14][2] = 32.1;
		x[15][2] = 32.5;
		x[16][2] = 32.9;

		return x;
	}



	public static double [] dataY (){

		double[] y = new double [17];

		y[0] = 251.3;
		y[1] = 251.3;
		y[2] = 148.3;
		y[3] = 267.5;
		y[4] = 273.0;
		y[5] = 276.5;
		y[6] = 270.3;
		y[7] = 274.9;
		y[8] = 285;
		y[9] = 290;
		y[10] = 297;
		y[11] = 302.5;
		y[12] = 304.5;
		y[13] = 309.3;
		y[14] = 321.7;
		y[15] = 330.7;
		y[16] = 349;

		return y;		
	}


	public static double [] dataP1 (){
		double [] predic = new double [5];

		predic[0] = 78;
		predic[1] = 78.4;
		predic[2] = 78.9;
		predic[3] = 79.2;
		predic[4] = 79.6;

		return predic;
	}

	public static double [] dataP2 (){
		double [] predic = new double [5];

		predic[0] = 33.1;
		predic[1] = 33.7;
		predic[2] = 34;
		predic[3] = 34.6;
		predic[4] = 34.9;

		return predic;
	}

}
package examples.LSRAgent;

import jade.core.Agent;

public class LSRAgent extends Agent {

	public void setup() {

		System.out.println("Calculo de grado 1: ");
		grado1();

		System.out.println();

		System.out.println("Calculo de grado 2: ");		
		grado2();

		System.out.println();

		System.out.println("Calculo de grado 3: ");		
		grado3();			

		doDelete();
	}


	public static void grado3 (){
		double [][]x = dataX();
		double [][]aux = dataX();
		double []y = dataY();
		double []aux2 = new double [4];
		double []bet = new double [4];
		
		aux = xX(x);
		aux = inversa4(aux);

		aux2 = multi(x,y);

		bet = beta(aux, aux2);

		System.out.print("Betas:");
		for(int i = 0; i < 4; i ++) System.out.print("| " + bet[i] + "| ");
		System.out.println();

		double [] p = dataP1();

		for(int i = 0; i < 4; i++) System.out.println("Prediccion " + (i + 1) + ": " + (bet[0] + (bet[1] * p[i]) + (bet[2] * p[i] * p[i]) + (bet[3] * p[i] * p[i] * p[i])));


	}

	public static double[][] xX (double[][] x){
		int t = x[0].length;

        	double[][] suma = new double[t][t];
        	double aux = 0;
        	double l = 0;

        	for (int i = 0; i < t; i++){
            		for(int j = 0; j < t; j++){
                		aux = 0;
                		for(int k = 0; k < 26; k++){
                    			aux += x[k][i] * x[k][j];
                    			suma[i][j] = aux;
                		}
            		}
        	}
        	return suma;
    	}

    public static double [][] inversa4(double [][] x){
        double [][] matriz = new double [3][3];
        double [][] matriz1 = new double [3][3];

        double[][] auxiliar = new double[4][4];
        double [][] inversa = new double [4][4];
        double [] d = new double [4];
        double determinante = 0;

        boolean renglon = false, columna = false;

        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j++){

                for(int k = 0; k < 4; k++){
                    if (k == i) renglon = true;

                    for(int o = 0; o < 4; o++){
                        if(o == j) columna = true;

                        if(k != i && o != j && renglon == true && columna == false) matriz[k - 1][o] = x[k][o];
                        if(k != i && o != j && columna == true && renglon == false) matriz[k][o - 1] = x[k][o];
                        if(k != i && o != j && renglon == true && columna == true) matriz[k - 1][o - 1] = x[k][o];
                        if(k != i && o != j && renglon == false && columna == false) matriz[k][o] = x[k][o];
                    }
                    columna = false;
                }
                renglon = false;
                if((i + j) %2 != 0) auxiliar[j][i] = (-1) * det(matriz);
                else auxiliar[j][i] = det(matriz);
            }
        }

        for(int i = 1; i < 4; i++){
            for(int j = 1; j < 4; j++){
                matriz1 [i - 1][j - 1] = x[i][j];
            }
        }

        d[0] = det(matriz1);

        for(int i = 1; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(j > 1) matriz1 [i - 1][j - 1] = x[i][j];
                if (j < 1) matriz1 [i - 1][j] = x[i][j];
            }
        }

        d[1] = det(matriz1);

        for(int i = 1; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(j > 2) matriz1 [i - 1][j - 1] = x[i][j];
                if(j < 2) matriz1 [i - 1][j] = x[i][j];
            }
        }

        d[2] = det(matriz1);

        for(int i = 1; i < 4; i++){
            for(int j = 0; j < 3; j++){
                matriz1 [i - 1][j] = x[i][j];
            }
        }

        d[3] = det(matriz1);

        determinante = (d[0] * x[0][0]) - (d[1] * x[0][1]) + (d[2] * x[0][2]) - (d[3] * x[0][3]);

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++){
                inversa[i][j] = (1 / determinante) * auxiliar[i][j];
            }
        }

        return inversa;
    }

    public static double det (double [][]x){
        double det = (x[0][0]*((x[1][1] * x[2][2]) - (x[1][2] * x[2][1]))) - (x[0][1]*((x[1][0] * x[2][2]) - (x[1][2] * x[2][0]))) + (x[0][2]*((x[1][0] * x[2][1]) - (x[1][1] * x[2][0])));

        return det;
    }

	public static double [] multi (double[][] x, double[] y){
		int t = x[0].length;

        	double [] a = new double[t];

        	for(int i = 0; i < t; i++){
            		for(int j = 0; j < 26; j++) a[i] += x[j][i] * y[j];
        	}

        	return a;
    	}

    	public static double [] beta (double [][]x, double []y){
		int t = x[0].length;

        	double [] bet = new double[t];
        	double aux = 0;

        	for(int i = 0; i < t; i++){
            		for(int j = 0; j < t; j++) {
                		aux += x[i][j] * y[j];
            		}
            		bet[i] = aux;
            		aux = 0;
        	}

        	return bet;
    	}





	public static void grado1 (){
		double [][]x = dataX();
        	double [][]aux = dataX();
        	double []y = dataY();
        	double []aux2 = new double [2];
        	double [] bet = new double[2];

		aux = xX(x);
		aux = inversa2(aux);

		aux2 = multi(x,y);

		bet = beta(aux,aux2);

		double [] p = dataP1();


		System.out.print("Betas:");
		for(int i = 0; i < 2; i ++) System.out.print("| " + bet[i] + "| ");
		System.out.println();

		for(int i = 0; i < 5; i++) System.out.println("Prediccion " + (i + 1) + ": " + (bet[0] + bet[1] * p[i]));
		
	}

    	public static double[][] inversa2 (double[][] x){
        	double y = 1;
        	double k = 1;
        	double [][] respuesta = new double[2][2];

        	respuesta[0][0] = x[1][1];
        	respuesta[0][1] = x[0][1] * (-1);
        	respuesta[1][0] = x[1][0] * (-1);
        	respuesta[1][1] = x[0][0];
        	int j = 1;

        	for(int i = 0; i < 2; i++) {
            		y *= x[i][i];
            		k *= x[j][i];
            		j--;
        	}

        	y -= k;

        	for(int i = 0; i < 2; i++){
            		for(int o = 0; o < 2; o++) {
                		respuesta[i][o] *= 1/y;
            		}
        	}
        	return respuesta;
    	}







	public static void grado2 (){
		double[][] x = dataX();
		double [][]aux = dataX();
        	double []y = dataY();
        	double []aux2 = new double [3];
        	double [] bet = new double[3];

		aux = xX(x);
		aux = inversa3(aux);
	
		aux2 = multi(x,y);
		bet = beta(aux,aux2);


		System.out.print("Betas:");
		for(int i = 0; i < 3; i ++) System.out.print("| " + bet[i] + "| ");
		System.out.println();

		double [] p = dataP1();

		for(int i = 0; i < 5; i++) System.out.println("Prediccion " + (i + 1) + ": " + (bet[0] + ((bet[1] * p[i]) + (bet[2] * p[i] * p[i]))));
		
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

    public static double [][] dataX (){
        double[][] setX = new double[26][4];

        setX[0][0] = 1;
        setX[1][0] = 1;
        setX[2][0] = 1;
        setX[3][0] = 1;
        setX[4][0] = 1;
        setX[5][0] = 1;
        setX[6][0] = 1;
        setX[7][0] = 1;
        setX[8][0] = 1;
        setX[9][0] = 1;
        setX[10][0] = 1;
        setX[11][0] = 1;
        setX[12][0] = 1;
        setX[13][0] = 1;
        setX[14][0] = 1;
        setX[15][0] = 1;
        setX[16][0] = 1;
        setX[17][0] = 1;
        setX[18][0] = 1;
        setX[19][0] = 1;
        setX[20][0] = 1;
        setX[21][0] = 1;
        setX[22][0] = 1;
        setX[23][0] = 1;
        setX[24][0] = 1;
        setX[25][0] = 1;

        setX[0][1] = 108;
        setX[1][1] = 115;
        setX[2][1] = 106;
        setX[3][1] = 97;
        setX[4][1] = 95;
        setX[5][1] = 91;
        setX[6][1] = 97;
        setX[7][1] = 83;
        setX[8][1] = 83;
        setX[9][1] = 78;
        setX[10][1] = 54;
        setX[11][1] = 67;
        setX[12][1] = 56;
        setX[13][1] = 53;
        setX[14][1] = 61;
        setX[15][1] = 115;
        setX[16][1] = 81;
        setX[17][1] = 78;
        setX[18][1] = 30;
        setX[19][1] = 45;
        setX[20][1] = 99;
        setX[21][1] = 32;
        setX[22][1] = 25;
        setX[23][1] = 28;
        setX[24][1] = 90;
        setX[25][1] = 89;

        setX[0][2] = 108 * 108;
        setX[1][2] = 115 * 115;
        setX[2][2] = 106 * 106;
        setX[3][2] = 97 * 97;
        setX[4][2] = 95 * 95;
        setX[5][2] = 91 * 91;
        setX[6][2] = 97 * 97;
        setX[7][2] = 83 * 83;
        setX[8][2] = 83 * 83;
        setX[9][2] = 78 * 78;
        setX[10][2] = 54 * 54;
        setX[11][2] = 67 * 67;
        setX[12][2] = 56 * 56;
        setX[13][2] = 53 * 53;
        setX[14][2] = 61 * 61;
        setX[15][2] = 115 * 115;
        setX[16][2] = 81 * 81;
        setX[17][2] = 78 * 78;
        setX[18][2] = 30 * 30;
        setX[19][2] = 45 * 45;
        setX[20][2] = 99 * 99;
        setX[21][2] = 32 * 32;
        setX[22][2] = 25 * 25;
        setX[23][2] = 28 * 28;
        setX[24][2] = 90 * 90;
        setX[25][2] = 89 * 89;

        setX[0][3] = 108 * 108 * 108;
        setX[1][3] = 115 * 115 * 115;
        setX[2][3] = 106 * 106 * 106;
        setX[3][3] = 97 * 97 * 97;
        setX[4][3] = 95 * 95 * 95;
        setX[5][3] = 91 * 91 * 91;
        setX[6][3] = 97 * 97 * 97;
        setX[7][3] = 83 * 83 * 83;
        setX[8][3] = 83 * 83 * 83;
        setX[9][3] = 78 * 78 * 78;
        setX[10][3] = 54 * 54 * 54;
        setX[11][3] = 67 * 67 * 67;
        setX[12][3] = 56 * 56 * 56;
        setX[13][3] = 53 * 53 * 53;
        setX[14][3] = 61 * 61 * 61;
        setX[15][3] = 115 * 115 * 115;
        setX[16][3] = 81 * 81 * 81;
        setX[17][3] = 78 * 78 * 78;
        setX[18][3] = 30 * 30 * 30;
        setX[19][3] = 45 * 45 * 45;
        setX[20][3] = 99 * 99 * 99;
        setX[21][3] = 32 * 32 * 32;
        setX[22][3] = 25 * 25 * 25;
        setX[23][3] = 28 * 28 * 28;
        setX[24][3] = 90 * 90 * 90;
        setX[25][3] = 89 * 89 * 89;

        return setX;
    }



    public static double [] dataY (){

        double[] sety = new double[26];

        sety[0] = 95;
        sety[1] = 96;
        sety[2] = 95;
        sety[3] = 97;
        sety[4] = 93;
        sety[5] = 94;
        sety[6] = 95;
        sety[7] = 93;
        sety[8] = 92;
        sety[9] = 86;
        sety[10] = 73;
        sety[11] = 80;
        sety[12] = 65;
        sety[13] = 69;
        sety[14] = 77;
        sety[15] = 96;
        sety[16] = 87;
        sety[17] = 89;
        sety[18] = 60;
        sety[19] = 63;
        sety[20] = 95;
        sety[21] = 61;
        sety[22] = 55;
        sety[23] = 56;
        sety[24] = 94;
        sety[25] = 93;

        return sety;
    }


    public static double [] dataP1 (){
        double [] predic = new double [5];

        predic[0] = 78;
        predic[1] = 89;
        predic[2] = 82;
        predic[3] = 90;
        predic[4] = 79;

        return predic;
    }


}
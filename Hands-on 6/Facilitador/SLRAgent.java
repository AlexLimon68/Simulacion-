package examples.Facilitador;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class SLRAgent extends Agent {


	


	public void setup () {

		String m = "";

		ACLMessage recibido = this.blockingReceive();
		ACLMessage respuesta;
	
		if(recibido != null){
			m = recibido.getContent();

			double[][] x = dataSet(m);
			String n = regresion(x);

			System.out.println("Mensaje recibido, enviando Simple Linear Regression: ");
			respuesta = recibido.createReply();
			respuesta.setContent("mensaje -->" + n);
			this.send(respuesta);
			
		}
		else System.out.println("No se recibió el mensaje.");
		

		
		
		doDelete();
	}


	public static String regresion (double [][] x){
		double [] datos = predicciones();

		double b1 = ( (x.length * sumatoriaXY(x)) - (sumatoriaX(x) * sumatoriaY(x)) ) / ( (x.length * sumatoriaX2(x)) - (sumatoriaX(x) * sumatoriaX(x)) );
		double b0 = promedioY(x) - ( b1 * promedioX(x) );

		System.out.println("Beta 0: " + b0 + " Beta 1: " + b1);		

		for(int i = 0; i < datos.length; i ++) datos[i] = b0 + (b1 * predicciones()[i]);

		return crearMensaje(datos);
	}




	public static double sumatoriaXY(double [][] x){
		
		double suma = 0;

		for(int i = 0; i < x.length; i++){
			suma += (x[i][0] * x[i][1]);
		}

		return suma;
	}

	public static double sumatoriaX(double [][] x){
		
		double sumaX = 0;
		
		for(int i = 0; i < x.length; i++){
			sumaX += x[i][0];
		}

		return sumaX;		
	}


	public static double sumatoriaY(double [][] x){

		double sumaY = 0;

		for(int i = 0; i < x.length; i ++){
			sumaY += x[i][1];
		}
		
		return sumaY;
	} 


	public static double sumatoriaX2(double [][] x){

		double suma = 0;
		
		for(int i = 0; i < x.length; i++){
			suma += (x[i][0] * x[i][0]);
		}

		return suma;
	}


	public static double promedioX(double [][] x){

		double prom = 0;

		for(int i = 0; i < x.length; i++){
			prom += x[i][0];
		}
		return (prom / x.length);
	}


	public static double promedioY(double [][] x){

		double prom = 0;

		for(int i = 0; i < x.length; i++){
			prom += x[i][1];
		}
		return (prom / x.length);
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
		double [][] x = new double[ren][2];
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

	public static double [] predicciones (){
		double[] datos = new double [5];
		
		datos[0] = 9;
		datos[1] = 10;
		datos[2] = 11;
		datos[3] = 12;
		datos[4] = 13;		
		
		return datos;
	}


}
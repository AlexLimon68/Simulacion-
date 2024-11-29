package examples.Facilitador;
 
import jade.core.Agent;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import java.util.Iterator;
import jade.lang.acl.ACLMessage;
 
public class BuscaServicio extends Agent {
   
     protected void setup() {
		String nombre = "";
		String tipo = "";

		double[][] x = data1();
		
		String mensaje = creaMC(x);
		ACLMessage enviado;
		ACLMessage respuesta;
		String recibido = "";

		enviado = new ACLMessage(ACLMessage.INFORM);
		enviado.addReceiver(new AID("Clas", AID.ISLOCALNAME));
		enviado.setContent(mensaje);
		this.send(enviado);
		respuesta = this.blockingReceive();

		if(respuesta != null){
			recibido = respuesta.getContent();
			System.out.println(recibido);
		}
		
		ServiceDescription servicio = new ServiceDescription();
		DFAgentDescription descripcion = new DFAgentDescription();
		tipo = recibido;
		mensaje = creaMensaje(x);
					
		
        	servicio.setType(tipo);
					
	        descripcion.addLanguages("Castellano");

		descripcion.addServices(servicio);

    		try {
        	
			DFAgentDescription[] resultados = DFService.search(this,descripcion);
 	
			if (resultados.length == 0) System.out.println("Ningun agente ofrece el servicio deseado");
 
			for (int i = 0; i < resultados.length; ++i) {
				Iterator servicios = resultados[i].getAllServices();
                		int j = 1;
			        while(servicios.hasNext()) {
                		    servicio = (ServiceDescription)servicios.next();
			            if(servicio.getType().equals(tipo)) nombre = servicio.getName();
						    
               				j++;
                		}
            		}
        	}
       		catch (Exception e) {
       			e.printStackTrace();
       		}

		
		enviado = new ACLMessage(ACLMessage.INFORM);
		enviado.addReceiver(new AID(nombre, AID.ISLOCALNAME));
		enviado.setContent(mensaje);
		this.send(enviado);
		System.out.println("Mensaje enviado a " + nombre + " agent.");
		respuesta = this.blockingReceive();

		if(respuesta != null){
			recibido = respuesta.getContent();
			imprime(recibido);
		}
			

    }



	public static void imprime(String m){
		String aux = "";
		int o = 0;
		char c;
		for(int i = 0; i < m.length(); i++){
			
			c = m.charAt(i);
			if(Character.isDigit(c) || c == '.') aux += c;
			else{
				System.out.println(aux);
				o++;
				aux = "";
			} 
		}
	}


	public static String creaMC(double [][] x){
		String mensaje = "";
		
		for(int i = 0; i < x[0].length; i++) mensaje += x[0][i] + " ";

		return mensaje;
	}


	public static String creaMensaje(double [][]x){
		String mensaje = String.valueOf(x.length) + " ";	
		
		for(int i = 0; i < x[0].length; i++){
			for(int j = 0; j < x.length; j++){
				mensaje += String.valueOf(x[j][i] + " ");	
			}
		}

		return mensaje;
	}


	public static double[][] data1 (){

		double[][] dataX = new double[9][2];
		
		dataX[0][0] = 1;
		dataX[1][0] = 2;
		dataX[2][0] = 3;
		dataX[3][0] = 4;
		dataX[4][0] = 5;
		dataX[5][0] = 6;
		dataX[6][0] = 7;
		dataX[7][0] = 8;
		dataX[8][0] = 9;

		dataX[0][1] = 3;
		dataX[1][1] = 6;
		dataX[2][1] = 9;
		dataX[3][1] = 12;
		dataX[4][1] = 15;
		dataX[5][1] = 18;
		dataX[6][1] = 21;
		dataX[7][1] = 24;
		dataX[8][1] = 27;

		return dataX;

	}

	public static double[][] data2 (){
		double[][] x = new double [17][4];

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

		x[0][3] = 251.3;
		x[1][3] = 251.3;
		x[2][3] = 148.3;
		x[3][3] = 267.5;
		x[4][3] = 273.0;
		x[5][3] = 276.5;
		x[6][3] = 270.3;
		x[7][3] = 274.9;
		x[8][3] = 285;
		x[9][3] = 290;
		x[10][3] = 297;
		x[11][3] = 302.5;
		x[12][3] = 304.5;
		x[13][3] = 309.3;
		x[14][3] = 321.7;
		x[15][3] = 330.7;
		x[16][3] = 349;

		return x;

	}


	public static double[][] data3(){
        	double[][] setX = new double[26][5];

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

	        setX[0][4] = 95;
        	setX[1][4] = 96;
	        setX[2][4] = 95;
        	setX[3][4] = 97;
	        setX[4][4] = 93;
        	setX[5][4] = 94;
	        setX[6][4] = 95;
        	setX[7][4] = 93;
	        setX[8][4] = 92;
        	setX[9][4] = 86;
	        setX[10][4] = 73;
        	setX[11][4] = 80;
	        setX[12][4] = 65;
        	setX[13][4] = 69;
        	setX[14][4] = 77;
	        setX[15][4] = 96;
        	setX[16][4] = 87;
	        setX[17][4] = 89;
        	setX[18][4] = 60;
	        setX[19][4] = 63;
        	setX[20][4] = 95;
	        setX[21][4] = 61;
        	setX[22][4] = 55;
	        setX[23][4] = 56;
        	setX[24][4] = 94;
	        setX[25][4] = 93;

	        return setX;
	}

}







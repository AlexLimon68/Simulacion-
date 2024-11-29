package examples.Facilitador;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class Clasificador extends Agent {

	public void setup() {
		String m = "";

		ACLMessage recibido = this.blockingReceive();
		ACLMessage respuesta;
	
		if(recibido != null){
			String n = recibido.getContent();
			m = tipo(n);

			System.out.println("Mensaje recibido, el mejor simsetma de regresion es: ");
			respuesta = recibido.createReply();
			respuesta.setContent(m);
			this.send(respuesta);
			
		}
		else System.out.println("No se recibió el mensaje.");		

		doDelete();
	}

	
	public String tipo (String datos){
		char c;
		int o = 0;
		

		for(int i = 0; i < datos.length(); i++){

			c = datos.charAt(i);
			if(Character.isDigit(c) || c == '.'){
			}
			else { 
				o++; 
			}
		}


		double [] x = new double [o];
		String m = "";
		o = 0;
		
		for(int i = 0; i < datos.length(); i++){

			c = datos.charAt(i);
			if(Character.isDigit(c) || c == '.') m += c;
			else{
				x[o] = Double.parseDouble(m);
				o++;
				m = "";
			}
		}

		String reg = "";

		if(x.length > 2){
			if(x[2] == (x[1] * x[1])) reg = "Regresion Polinomial";
			else reg = "Regresion Multiple";
		}
		else reg = "Regresion Lineal Simple";

		return reg;

	}

}

package com.ssbaez.ejercicio633;

import java.security.SecureRandom;
import java.util.Scanner;

public class CrapsV2 {
	
	private static Scanner input = new Scanner(System.in);
	private static final SecureRandom numAleatorio = new SecureRandom();
	
	private enum Estado{CONTINUA, GANO, PERDIO};
	
	//Constantes
	private static final int DOS_UNO 	= 2;
	private static final int TRES 		= 3;
	private static final int SIETE 		= 7;
	private static final int ONCE 		= 11;
	private static final int DOCE 		= 12;
	
	public static void main(String[] args) {
		
		int miPto = 0;
		Estado estadoJuego;
		double saldoBlanco = 1000;
		
		System.out.printf("%45s%n", "=========C R A P S=========");
		
		while(true) {
			
			double apuesta = apostar(saldoBlanco);
			
			int tiro = tirarDados();
			
			switch(tiro) {
			
			case SIETE:
			case ONCE:
				estadoJuego = Estado.GANO;
				break;
				
			case DOS_UNO:
			case TRES:
			case DOCE:
				estadoJuego = Estado.PERDIO;
				break;
				
			default:
				estadoJuego = Estado.CONTINUA;
				miPto = tiro;
				System.out.printf("El PUNTO es %d%n", miPto);
				break;
			}
			
			while(estadoJuego == Estado.CONTINUA) {
				
				tiro = tirarDados();
				
				if(tiro == miPto)
					estadoJuego = Estado.GANO;
					
				else if(tiro == SIETE)
					estadoJuego = Estado.PERDIO;
			}
			
			if(estadoJuego == Estado.GANO) {
				saldoBlanco += apuesta;
				System.out.printf("%n%s%,.2f%n%s%,.2f%n",
								  "El jugador GANA $ ", apuesta,
								  "El saldo es: $ ", saldoBlanco);
				
				burla(estadoJuego);
				
				if(volverApostar(saldoBlanco) == false)
					break;
			}
			else {
				saldoBlanco -= apuesta;
				System.out.printf("%n%s%,.2f%n%s%,.2f%n",
								  "El jugador PIERDE $ ", apuesta,
								  "El saldo es: $ ", saldoBlanco);
				
				burla(estadoJuego);
				
				if(volverApostar(saldoBlanco) == false)
					break;
			}
			
		}//Fin de while principal
		
	}//Fin de metodo main
	
	public static double apostar(double saldo) {
		
		double apuesta = 0;
		
		while(true) {
			
			System.out.printf("Ingrese su apuesta %n%s",
					  "----> ");
			apuesta = input.nextDouble();
			
			while(apuesta > saldo) {
				System.out.println("\n*** No puede apostar mas de lo que se tiene, no sea hijoeputa xD ***");
				System.out.printf("%nIngrese una apuesta valida, menor o igual a su saldo: %,.2f %n%s", saldo,
								  "----> ");
				apuesta = input.nextDouble();
			}
			break;
		}
		
		return apuesta;
	}//Fin de metodo apostar
	
	public static int tirarDados() {
		
		int dado1 = 1 + numAleatorio.nextInt(6);
		int dado2 = 1 + numAleatorio.nextInt(6);
		
		int suma = dado1 + dado2;
		
		System.out.printf("%nSe tiran un %d y un %d = %d%n", dado1, dado2, suma);
		
		return suma;
		
	}//Fin de metodo tirarDados
	
	public static boolean volverApostar(double saldo) {
		
		boolean selec = false;
		
		if(saldo > 0) {
			
			while(true) {
				System.out.printf("%nQuiere volver apostar? 1) Si  2) No %n%s",
						  "----> ");
				int sino = input.nextInt();
				
				if(sino == 1) {
					selec = true;
					break;
				}
				
				else if(sino == 2) {
					System.out.println("Te retiras con: $ " + saldo + "\n");
					break;
				}
				
				else
					System.out.println("\n***OPCION INCORRECTA***\n");
			}
			
		}
		else
			System.out.println("\nLo siento. ¡Se quedo sin fondos!");
		
		return selec;
	}//Fin de metodo volverApostar
	
	public static void burla(Estado estadoJuego) {
		
		String uno = "\n¡¡Estas arrasando!!";
		String dos = "\nO hay truco, o tienes mucha suerte!!";
		String tres = "\nAlguien no quiere regresar vivo a casa esta noche ¬¬";
		String cuatro = "\nYa es hora de recoger las ganancias!";
		
		String cinco = "\nAlguien se levanto con el pie izquierdo...";
		String seis = "\nHoy no te va a dejar entrar a casa xD";
		String siete = "\nTe estas yendo a la quiebra, verdad?";
		String ocho = "\nVamos! Doble o nada!";
		
		if(estadoJuego == Estado.GANO) {
			
			int selec = 1 + numAleatorio.nextInt(4);
			
			switch(selec) {
			
			case 1:
				System.out.println(uno);
				break;
				
			case 2:
				System.out.println(dos);
				break;
				
			case 3:
				System.out.println(tres);
				break;
				
			case 4:
				System.out.println(cuatro);
				break;
			}
			
		}
		
		else {
			
			int selec = 5 + numAleatorio.nextInt(4);
			
			switch(selec) {
			
			case 5:
				System.out.println(cinco);
				break;
				
			case 6:
				System.out.println(seis);
				break;
				
			case 7:
				System.out.println(siete);
				break;
				
			case 8:
				System.out.println(ocho);
				break;
			}
			
		}
		
	}//Fin de metodo burla

}//Fin declaracion de clase

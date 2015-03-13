/* -------------------------------------------------------------------
 * Proyecto01.java
 * versión 1.0
 * Copyright (C) 2015  José Ricardo Rodríguez Abreu.
 * Facultad de Ciencias,
 * Universidad Nacional Autónoma de México, Mexico.
 *
 * Este programa es software libre; se puede redistribuir
 * y/o modificar en los términos establecidos por la
 * Licencia Pública General de GNU tal como fue publicada
 * por la Free Software Foundation en la versión 2 o
 * superior.
 *
 * Este programa es distribuido con la esperanza de que
 * resulte de utilidad, pero SIN GARANTÍA ALGUNA; de hecho
 * sin la garantía implícita de COMERCIALIZACIÓN o
 * ADECUACIÓN PARA PROPÓSITOS PARTICULARES. Véase la
 * Licencia Pública General de GNU para mayores detalles.
 *
 * Con este programa se debe haber recibido una copia de la
 * Licencia Pública General de GNU, de no ser así, visite el
 * siguiente URL:
 * http://www.gnu.org/licenses/gpl.html
 * o escriba a la Free Software Foundation Inc.,
 * 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * -------------------------------------------------------------------
 */

import java.util.Scanner;
import java.io.IOException;

import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.LinkedList;
//import java.util.Scanner;
//import java.util.NoSuchElementException;

import mx.unam.ciencias.automatas.*;

/**
 * @author Jose Ricardo Rodriguez Abreu
 * @version 1.0
 * @since Mar 8 2015.
 * <p>
 * Clase principal del proyecto.</p>
 *
 * <p>
 * Desde esta clase podemos lanzamos el programa.</p>
 */
public class Proyecto01 {


    /**
     *
     *
     */
    public static void lectura(){
	int n;
	char[] letras;
	Character[] letrasC;
	Estados[] estados;
	Tabla tabla;
	String temp = "";
	Scanner sc = new Scanner(System.in);
	msj();
	while(true){
	    System.out.println("Favor de ingresar el alfabeto de la forma: \"a,b,c,...\"");
	    temp = sc.nextLine();
	    if(temp.equals(""))
		continue;
	    temp = temp.replace(" ","");
	    temp = temp.replace(",","");
	    letras = temp.toCharArray();
	    letrasC = new Character[letras.length];
	    System.out.println("¿Es correcto su alfabeto? s\\n");
	    temp = "";
	    for(int i = 0; i < letras.length; i++){
		temp += letras[i]+",";
	    }
	    System.out.println("Alfabeto: "+temp.substring(0,temp.length()-1));
	    temp = sc.nextLine();
	    if(temp.equalsIgnoreCase("s")){
		break;
	    }else{
		continue;
	    }
	}
	while(true){
	    System.out.println("¿Cuántos estados tiene el autómata?");
	    temp = sc.nextLine();
	    if(temp.equals(""))
		continue;
	    if(!EsNumero.esNumero(temp) || Integer.parseInt(temp) <= 0){
		System.out.println("Favor de dar un número válido y mayor a cero.\n");
		continue;
	    }
	    n = Integer.parseInt(temp);
	    String est = "";
	    for(int k = 0; k < n; k++){
		est += " q"+k+",";
	    }
	    System.out.println("¿El autómata consta de los estados"+est.substring(0,est.length()-1)+"? s\\n");
	    temp = sc.nextLine();
	    if(temp.equalsIgnoreCase("s")){
		break;
	    }else{
		continue;
	    }
	}
	estados = new Estados[n];
	for(int i = 0; i < estados.length; i++){
	    estados[i] = new Estados(i, false);
	}
	System.out.println("\nNOTA: Favor de responder con \"qn\" donde n es el número de estado de 0 a "+(n-1)+".");
	while(true){
	    for(int i = 0; i < estados.length; i++){
		for(int j = 0; j < letras.length; j++){
		    System.out.println("Si estoy en q"+i+" y veo un(a) \""+letras[j]+"\", llego al estado: ");
		    temp = sc.nextLine();
		    if(temp.equals("")){
			j--;
			continue;
		    }
		    if(!EsNumero.esNumero(temp.substring(1)) || 
		       Integer.parseInt(temp.substring(1)) >= estados.length ||
		       temp.charAt(0) != 'q'){
			System.out.println("Favor de dar un estado de la forma \"qn\" donde n es el número de estado de 0 a "+(n-1)+".");
			j--;    
		    }else{
			estados[i].setEstadosSiguientes(letras[j],estados[Integer.parseInt(temp.substring(1))]);
		    }
		    
		}
		System.out.println("¿Es q"+i+" estado final? s\\n");
		temp = sc.nextLine();
		if(temp.equalsIgnoreCase("s"))
		    estados[i].setFinal(true);
	    }
	    for(int i = 0; i < letras.length; i++)
		letrasC[i] = new Character(letras[i]);
	    System.out.println("¿Es este el autómata que deseabas formar? s\\n \n");
	    tabla = new Tabla(n,letrasC);
	    tabla.setEstados(estados);
	    tabla.imprime(letrasC);
	    temp = sc.nextLine();
	    if(temp.equalsIgnoreCase("s")){
		System.out.println("TU AUTÓMATA REDUCIDO ES:");
		tabla.sacaMinimo().imprime(letrasC);
		break;
	    }
	}
	System.exit(0);
    }   

    /**
     * Metodo que solo imprime el mensaje de bienvenida y lo basico.
     *
     */
    public static void msj(){
	System.out.println("***********************************");
        System.out.println("*         PROYECTO NUM 1          *");
	System.out.println("* Autómatas y lenguajes formales  *");
	System.out.println("* José Ricardo Rodríguez Abreu    *");
	System.out.println("***********************************\n");
	System.out.println("Instrucciones:");
	System.out.println("Para reducir un autómata dado un archivo con el formato indicado (ver \"info\") ");
	System.out.println("Favor de ejecutar el programa como \"java Proyecto01 <archivo de Entrada> o");
	System.out.println("\"java Proyecto01 <archivo de Entrada> <archivo de Salida>\"");
	System.out.println("Para más información acerca ingrese \"info\"");
	System.out.println("\nPara iniciar a leer desde consola ingrese \"/c\" sin comillas");
    }    

    /**
     * El método main.
     *
     * @param args los argumentos del programa.
     */
    public static void main(String[] args) {      
	if(args.length == 1){
	    if(args[0].equalsIgnoreCase("/c"))
		lectura();
	    try{
		Tabla tabla = new Tabla(new FileReader(args[0]), args[0]);
	    tabla.imprime();
	    System.out.println("TU AUTÓMATA REDUCIDO ES:");
	    tabla.sacaMinimo().imprime();
	    }catch(IOException e){
		System.err.println("Error al procesar el archivo. Ver formato y asegurese de que el arhivo existe (Proyecto)");
	    }
	}else{
	msj();
	}
    }
}// Fin de Proyecto1.java

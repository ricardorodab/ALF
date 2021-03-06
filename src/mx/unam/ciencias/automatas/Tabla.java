/* -------------------------------------------------------------------
 * Tabla.java
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
package mx.unam.ciencias.automatas;

import java.util.LinkedList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import mx.unam.ciencias.automatas.Estados;

/**
 * @author Jose Ricardo Rodriguez Abreu
 * @version 1.0
 * @since Mar 8 2015.
 * <p>
 * Clase que da el comportamiento a las tablas.</p>
 *
 * <p>
 * Desde esta clase podemos obtener el comportamiento deseado de las tablas.</p>
 */
public class Tabla {

    /**
     * Variable para saber la cantidad de estados que tenemos.
     */
    private int estados;
    /**
     * Variable que tiene nuestro alfabeto.
     */
    private Character[] alfabeto;
    /**
     * Arreglo de los estados del automata.
     */
    private Estados[] listEstados;

    /**
     * Metodo que ponemos por completud.
     */
    public Tabla() {
    }
    
    /**
     * Metodo constructor de una tabla dado un archivo.
     *
     * @param entrada - es el archivo de entrada donde viene la tabla.
     * @param entrada2 - es el nombre del archivo.
     */
    public Tabla(FileReader entrada, String entrada2){
	String linea,numLineaS;
	BufferedReader texto,numLineas = new BufferedReader(entrada);
	char[] letras;
	Estados[] estados;
	Character[] letrasC;
	String[] vecinos;
	LinkedList<Estados> lestados = new LinkedList<Estados>();
	int numEstados,estadoNum;
	numEstados = estadoNum = 0;
	try{
	    while((numLineaS = numLineas.readLine()) != null)
		if(!numLineaS.trim().isEmpty())
		    numEstados++;
	    numEstados--;
	    numLineas.close();
	    estados = new Estados[numEstados];
	    for(int i = 0; i < estados.length; i++){
		estados[i] = new Estados(i, false);
	    }
	    texto = new BufferedReader(new FileReader(entrada2));
	    linea = texto.readLine();
	    if(linea == null)
		throw new NullPointerException();
	    linea = linea.replace(" ","");
	    linea = linea.replace(",","");
	    letras = linea.toCharArray();
	    letrasC = new Character[letras.length];
	    vecinos = new String[letras.length];
	    for(int i = 0; i < letras.length; i++)
		letrasC[i] = new Character(letras[i]);
	    while((linea = texto.readLine()) != null){
		linea = linea.replace(" ","");
		linea = linea.replace(",","");
		int k,u = 1;
		Estados estadoTemp = estados[estadoNum];
		if(linea.charAt(linea.length()-1) == '1')
		    estados[estadoNum].setFinal(true);	  
		estadoNum++;
		while(linea.charAt(u) != 'q' && u < linea.length()-1)
		    u++;
		estadoTemp.setNombre(linea.substring(0,u-1));
		for(int j = 0; j < letras.length; j++){
		    k = u;
		    while(linea.charAt(++u) != 'q' && u+1 < linea.length());
		    estadoTemp.setEstadosSiguientes(letras[j],estados[Integer.parseInt(linea.substring(k+1,u))]);									  }
		
	    }
	    this.estados = numEstados;
	    this.alfabeto = letrasC;
	    this.listEstados = estados;
	}catch(IOException e){
	    System.err.println("Error al procesar el archivo. Ver formato y asegurese de que el arhivo existe");
	}
    }
    
    
    
    /**
     * Metodo constructor de la tabla.
     *
     * @param estados - es el número de estados que tiene mi tabla.
     * @param alfabeto - es el alfabeto del automata.
     */
    public Tabla(int estados, Character[] alfabeto) {
        this.estados = estados;
        this.alfabeto = alfabeto;
        this.listEstados = new Estados[this.estados];
    }
    
    /**
     * Metodo para colocar los estados de nuestra tabla.
     *
     * @param estados - Son los estados de la tabla.
     */
    public void setEstados(Estados[] estados) {
        this.listEstados = estados;
    }

    /**
     * Metodo para obtener el alfabeto.
     * @return el alfabeto de la tabla.
     */
    public Character[] getAlfabeto(){
        return this.alfabeto;
    }

    /**
     * Metodo para poder ver los estados y su transiciones.
     */
    public void imprime(){
	imprime(this.alfabeto);
    }
    
    /**
     * Metodo para poder ver los estados y su transiciones.
     *
     * @param alfabeto - es el alfabeto de la tabla.
     */
    public void imprime(Character[] alfabeto) {
	System.out.print("Alfabeto del autómata: ");
	    for (int i = 0; i < alfabeto.length; i++)
		if(i+1 < alfabeto.length)
		    System.out.print(alfabeto[i]+", ");
		else
		    System.out.print(alfabeto[i]);
	System.out.println("");
        for (Estados e : listEstados) {
            System.out.print("q"+e.id());
	    for(int j = 0; j < alfabeto.length; j++)
		System.out.print(" q" + e.dameEstadoSiguiente(alfabeto[j]).id());
	    if(e.esFinal())
		System.out.println(" 1");
	    else
		System.out.println(" 0");
        }
    }

    /**
     * Metodo que dado una tabla, regresa otra tabla pero mínima.
     *
     * @return La tabla del automata minimo del cual es llamado.
     */
    public Tabla sacaMinimo() {
        int[][] matriz, matriz2;
        int k, iteracion = 0;
        LinkedList<LinkedList<Estados>> equivalentes = new LinkedList<LinkedList<Estados>>();
        matriz = new int[estados - 1][estados - 1];
        matriz2 = new int[estados - 1][estados - 1];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                Estados estado1 = this.listEstados[i + 1];
                Estados estado2 = this.listEstados[j];
                if ((estado1.esFinal() || estado2.esFinal()) && (j <= i)
                        && !(estado1.esFinal() && estado2.esFinal())) {
                    matriz[i][j] = iteracion;
                } else {
                    matriz[i][j] = -1;
                }
            }
        }

        boolean cambio = true;
        while (cambio) {          
            iteracion++;
            cambio = false;
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j <= i; j++) {
                    Estados estado1 = this.listEstados[j];
                    Estados estado2 = this.listEstados[i + 1];              
                    k = 0;
                    while (k < alfabeto.length) {
                        int m = estado1.dameEstadoSiguiente(alfabeto[k]).id();
                        int n = estado2.dameEstadoSiguiente(alfabeto[k]).id();                       
                        if (n == m) {
                            k++;
                            continue;
                        }
                        int mayor, menor;
                        if (n > m) {
                            mayor = n;
                            menor = m;
                        } else {
                            mayor = m;
                            menor = n;
                        }
                        if (matriz[mayor-1][menor] != -1) {
                            if (matriz[i][j] == -1) {
                                matriz[i][j] = iteracion;
                                cambio = true;
                            }
                            break;
                        }
                        k++;
                    }
                }
            }
        }
	//NOTA:
	//SI SE DESEA VER LA MATRIZ TRIANGULAR DE REDUCCIÓN DESCOMENTAR EL SIGUIENTE FRAGMENTO DE CÓDIGO:
	/*
	System.out.println("\nMatriz de reducción: ");
        for (int ñ = 0; ñ < matriz.length; ñ++) {
         for (int m = 0; m <= ñ; m++) {
         System.out.print(matriz[ñ][m] + " ");
         }
         System.out.print("\n");
         }
	System.out.print("\n");	
	*/
        LinkedList<Integer> saltar = new LinkedList<Integer>();
        boolean ultimoAgreg = false;
        for (int i = 0; i < matriz.length; i++) {                        
            LinkedList<Estados> l = new LinkedList<Estados>();
            if (!saltar.isEmpty() && saltar.contains(i) && (i+1 != matriz.length)) {
                continue;
            }            
            for (int j = i; j < matriz.length; j++) {
                if (matriz[j][i] == -1) {
                    saltar.add(j+1); 
                    l.add(this.listEstados[j+1]);
                    if(this.listEstados.length-1 == j+1)
                        ultimoAgreg = true;
                }
            }
            l.add(this.listEstados[i]);
            saltar.add(i);                       
            equivalentes.add(l);
        }
        if(!ultimoAgreg){
            LinkedList<Estados> l = new LinkedList<Estados>();
            l.add(this.listEstados[this.listEstados.length-1]);
            equivalentes.add(l);
        }
        
        Estados[] nuevos = getNuevosEstados(equivalentes);
        Tabla t = new Tabla(nuevos.length, alfabeto);
	t.setEstados(quitaEstados(nuevos));
        //t.setEstados(nuevos);
        return t;
    }

    //Método auxiliar para quitar estados que no se pueden llegar.
    private Estados[] quitaEstados(Estados[] estados){
	LinkedList<Estados> lista = new LinkedList<Estados>();
	quitaEstadosRecursivo(estados[0], lista);
	Estados[] nuevosEstados = new Estados[lista.size()];
	int i = 0;
	for(Estados e : estados)
	    if(lista.contains(e))
		nuevosEstados[i++] = e;
	return nuevosEstados;
    }

    //Método auxiliar recursivo de quitaEstados.
    private void quitaEstadosRecursivo(Estados estado, LinkedList<Estados> l){
	if(l.contains(estado))
	    return;
	l.add(estado);
	for(Character c : alfabeto)
	    quitaEstadosRecursivo(estado.dameEstadoSiguiente(c), l);
    }

    //Método auxiliar para crear nuevos estados dado una lista de listas de estados.
    private Estados[] getNuevosEstados(LinkedList<LinkedList<Estados>> lista) {
        Estados[] nuevos = new Estados[lista.size()];
        for (int i = 0; i < nuevos.length; i++) {
            nuevos[i] = new Estados(i, lista.get(i).get(0).esFinal());
        }
        for (int i = 0; i < nuevos.length; i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                Estados estadoT = listEstados[lista.get(i).get(0).id()];
                Estados estadoS = estadoT.dameEstadoSiguiente(alfabeto[j]);
                for (LinkedList<Estados> l1 : lista) {
                    if (l1.contains(estadoS)) {
                        nuevos[i].setEstadosSiguientes(alfabeto[j], nuevos[lista.indexOf(l1)]);
                        break;
                    }
                }
            }

        }
        return nuevos;
    }
} //Fin de Tabla.java

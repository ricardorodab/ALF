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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
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
     * @param estados
     */
    public void setEstados(Estados[] estados) {
        this.listEstados = estados;
    }

    public void imprime() {
        for (Estados e : listEstados) {
            System.out.println(e.id() + " A: " + e.dameEstadoSiguiente('0').id() + " B: " + e.dameEstadoSiguiente('1').id() + " F: " + e.esFinal);
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
        //boolean distinguible;
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
                    //distinguible = false;
                    k = 0;
                    while (k < alfabeto.length) {// && !distinguible){
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
                                System.out.println("Llené de 1 porque: Or:"+j+"x"+i+" Da:"+mayor+"x"+menor);
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
        for (int ñ = 0; ñ < matriz.length; ñ++) {
         for (int m = 0; m <= ñ; m++) {
         System.out.print(matriz[ñ][m] + " ");
         }
         System.out.print("\n");
         }
        
        LinkedList saltar = new LinkedList();
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
        //ESTO MMMMMMM.... SUENA MAL
        if(!ultimoAgreg){
            LinkedList<Estados> l = new LinkedList<Estados>();
            l.add(this.listEstados[this.listEstados.length-1]);
            equivalentes.add(l);
        }
        
        Estados[] nuevos = getNuevosEstados(equivalentes);
        Tabla t = new Tabla(nuevos.length, alfabeto);
        t.setEstados(nuevos);
        return t;
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

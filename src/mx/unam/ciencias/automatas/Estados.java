/* -------------------------------------------------------------------
 * Estados.java
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

import java.util.Hashtable;

 /**
  * @author Jose Ricardo Rodriguez Abreu
  * @version 1.0
  * @since Mar 9 2015.
  * <p>
  * Clase para manejar estados de un automata.</p>
  *
  * <p>
  * Desde esta clase podemos instanciar estados.</p>
  */
public class Estados{
    
    /**
     * Es el identificador del estado
     */
    private int numEstado;
    /**
     * Sabemos si es o no estado final
     */
    private boolean esFinal;
    /**
     * Es el nombre de cada estado;
     */
    private String nombre;
    /**
     * Una tabla hash para almacenar a todos los posibles estados vecinos
     */
    private Hashtable<Character, Estados> vecinos;
    
    /**
     * Metodo constructor de un estado.
     *
     * @param numEstado - Es el identificador único del estado.
     * @param esFinale - True solo si es estado final.
     */
    public Estados(int numEstado, boolean esFinale) {
	this.numEstado = numEstado;
	this.esFinal = esFinale;
	this.vecinos = new Hashtable<Character, Estados>();
    }

    /**
     * Metodo constructor de un estado.
     *
     * @param numEstado - Es el identificador único del estado.
     * @param esFinale - True solo si es estado final.
     * @param nombre - es el nombre del estado.
     */
    public Estados(int numEstado, boolean esFinale, String nombre) {
	this.numEstado = numEstado;
	this.esFinal = esFinale;
	this.nombre = nombre;
	this.vecinos = new Hashtable<Character, Estados>();
    }
    
    /**
     * Metodo que nos regresa el numero de estado que es.
     *
     * @return un entero que es identificador unico.
     */
    public int id() {
	return this.numEstado;
    }

    /**
     * Metodo que nos regresa si tiene nombre.
     *
     * @return True solo si tiene nombre.
     */
    public boolean tieneNombre(){
	return nombre == null || nombre.equals("");
    }

    /**
     * Metodo para asignar nombre a los estados.
     *
     * @param nombre - Es el nombre del estado.
     */
    public void setNombre(String nombre){
	this.nombre = nombre;
    }
    
    /**
     * Metodo que nos regresa el nombre del estado.
     *
     * @return el nombre del estado.
     * @throws NullPointerException - si no tiene nombre.
     */
    public String dameNombre(){
	if(!this.tieneNombre())
	    throw new  NullPointerException();
	return this.nombre;
    }
    
    /**
     * Metodo para saber si es estado final.
     *
     * @return True si es estado final, False en otro caso.
     */
    public boolean esFinal() {
	return this.esFinal;
    }

    /**
     * Metodo para asignar el valor de final a un estado.
     *
     * @param esFinal - es el valor de si es final el estado.
     */
    public void setFinal(boolean esFinal){
	this.esFinal = esFinal;
    }
    
    /**
     * Metodo que nos regresa un vecino dado una llave.
     *
     * @param llave - es la llave del vecino.
     * @return Estado vecino dada una llave.
     */
    public Estados dameEstadoSiguiente(Character llave) {
	return this.vecinos.get(llave);
    }
    
    /**
     * Metodo para poner los estados siguientes en nuestra tabla hash de vecinos.
     *
     * @param llave - es la llave del vecino.
     * @param e - es el estado vecino.
     */
    public void setEstadosSiguientes(Character llave, Estados e) {
	vecinos.put(llave, e);
    }
} //Fin de Estados.java
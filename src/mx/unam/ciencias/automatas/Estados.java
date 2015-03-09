/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.unam.ciencias.automatas;

import java.util.Hashtable;

/**
     * Clase protegida para definir los estados
     */
    public class Estados{

        /**
         * Es el identificador del estado
         */
        private int numEstado;
        /**
         * Sabemos si es o no estado final
         */
        boolean esFinal;
        /**
         * Una tabla hash para almacenar a todos los posibles estados vecinos
         */
        private Hashtable<Character, Estados> vecinos;

        /**
         * Metodo constructor de un estado.
         *
         * @param numEstado - Es el identificador único del estado.
         * @param esFinal - True solo si es estado final.
         */
        public Estados(int numEstado, boolean esFinale) {
            this.numEstado = numEstado;
            this.esFinal = esFinale;
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
         * Metodo para saber si es estado final.
         *
         * @return True si es estado final, False en otro caso.
         */
        public boolean esFinal() {
            return this.esFinal;
        }

        /**
         * Metodo que nos regresa un vecino dado una llave.
         *
         * @return Estado vecino dada una llave.
         * @throws NullPointerException - si la llave es null.
         */
        public Estados dameEstadoSiguiente(Character llave) {
            return this.vecinos.get(llave);
        }

        public void setEstadosSiguientes(Character llave, Estados e) {
            vecinos.put(llave, e);
        }
    }
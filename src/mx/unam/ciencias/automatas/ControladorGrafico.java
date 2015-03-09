/* -------------------------------------------------------------------
 * ControladorGrafico.java
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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

/**
 * @author Jose Ricardo Rodriguez Abreu
 * @version 1.0
 * @since Mar 8 2015.
 * <p>
 * Clase principal de la intefaz grafica.</p>
 *
 * <p>
 * Clase principal que asigna comportamiento a la interfaz gráfica.</p>
 */
public class ControladorGrafico implements Initializable {

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
    
     /**
     * Método que despliega la licencia del programa y una pequeña ayuda.
     */
    public void msjAcerca(){
        String msj = " Programa de reducción de autómatas deterministas dada su tabla:\n" +
                " Versión 1.0\n" +
                " Copyright (C) 2014  José Ricardo Rodríguez Abreu.\n" +
                " Facultad de Ciencias,\n" +
                " Universidad Nacional Autónoma de México, Mexico.\n" +
                " \n" +
                " Este programa es software libre; se puede redistribuir\n" +
                " y/o modificar en los términos establecidos por la\n" +
                " Licencia Pública General de GNU tal como fue publicada\n" +
                " por la Free Software Foundation en la versión 2 o\n" +
                " superior.\n" +
                " \n" +
                " Este programa es distribuido con la esperanza de que\n" +
                " resulte de utilidad, pero SIN GARANTÍA ALGUNA; de hecho\n" +
                " sin la garantía implícita de COMERCIALIZACIÓN o\n" +
                " ADECUACIÓN PARA PROPÓSITOS PARTICULARES. Véase la\n" +
                " Licencia Pública General de GNU para mayores detalles.\n" +
                " \n" +
                " Con este programa se debe haber recibido una copia de la\n" +
                " Licencia Pública General de GNU, de no ser así, visite el\n" +
                " siguiente URL:\n" +
                " http://www.gnu.org/licenses/gpl.html\n" +
                " o escriba a la Free Software Foundation Inc.,\n" +
                " 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.\n\n"
                + "Proyecto 1 - Autómatas y lenguajes formales. \n"
                + "Alumno: José Ricardo Rodríguez Abreu. \n";
        
        MessageBox.show(new Stage(), msj, "Un poco sobre el programa...",
                MessageBox.OK);
        
    }
    
    
    
}

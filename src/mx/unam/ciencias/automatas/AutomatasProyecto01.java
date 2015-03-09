/* -------------------------------------------------------------------
 * AutomatasProyecto1.java
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

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mx.unam.ciencias.automatas.Estados;

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
public class AutomatasProyecto01 extends Application {
  /**
     * Método principal del proyecto.
     *
     * @param stage es la intefaz del proyecto.
     * @throws Exception - en caso de tener algún problema al cargar el
     * programa en threads.
     */
    @Override
    public void start(Stage stage) throws Exception {
        
        /*
        Character[] c = new Character[2];
        c[0] = '0';
        c[1] = '1';        
        Tabla t1 = new Tabla(8, c);
        Estados qa,qb,qc,qd,qe,qf,qg,qh;
        qa = new Estados(0, false);
        qb = new Estados(1, false);
        qc = new Estados(2, true);
        qd = new Estados(3, false);
        qe = new Estados(4, false);
        qf = new Estados(5, false);
        qg = new Estados(6, false);
        qh = new Estados(7, false);
        
        qa.setEstadosSiguientes('0', qb);
        qa.setEstadosSiguientes('1', qf);
        
        qb.setEstadosSiguientes('0', qg);
        qb.setEstadosSiguientes('1', qc);
        
        qc.setEstadosSiguientes('0', qa);
        qc.setEstadosSiguientes('1', qc);
        
        qd.setEstadosSiguientes('0', qc);
        qd.setEstadosSiguientes('1', qg);
        
        qe.setEstadosSiguientes('0', qh);
        qe.setEstadosSiguientes('1', qf);
        
        qf.setEstadosSiguientes('0', qc);
        qf.setEstadosSiguientes('1', qg);
        
        qg.setEstadosSiguientes('0', qg);
        qg.setEstadosSiguientes('1', qe);
        
        qh.setEstadosSiguientes('0', qg);
        qh.setEstadosSiguientes('1', qc);
        Estados[] x = {qa,qb,qc,qd,qe,qf,qg,qh};
        t1.setEstados(x);
        
        Tabla t2 = t1.sacaMinimo();
        t2.imprime();
        */
        
        Character[] c = new Character[2];
        c[0] = '0';
        c[1] = '1';        
        Tabla t1 = new Tabla(9, c);
        Estados qa,qb,qc,qd,qe,qf,qg,qh,qi;
        qa = new Estados(0, false);
        qb = new Estados(1, false);
        qc = new Estados(2, true);
        qd = new Estados(3, false);
        qe = new Estados(4, false);
        qf = new Estados(5, true);
        qg = new Estados(6, false);
        qh = new Estados(7, false);
        qi = new Estados(8,true);
        
        qa.setEstadosSiguientes('0', qb);
        qa.setEstadosSiguientes('1', qe);
        
        qb.setEstadosSiguientes('0', qa);
        qb.setEstadosSiguientes('1', qc);
        
        qc.setEstadosSiguientes('0', qd);
        qc.setEstadosSiguientes('1', qh);
        
        qd.setEstadosSiguientes('0', qe);
        qd.setEstadosSiguientes('1', qh);
        
        qe.setEstadosSiguientes('0', qf);
        qe.setEstadosSiguientes('1', qi);
        
        qf.setEstadosSiguientes('0', qg);
        qf.setEstadosSiguientes('1', qb);
        
        qg.setEstadosSiguientes('0', qh);
        qg.setEstadosSiguientes('1', qb);
        
        qh.setEstadosSiguientes('0', qi);
        qh.setEstadosSiguientes('1', qc);
        
        qi.setEstadosSiguientes('0', qa);
        qi.setEstadosSiguientes('1', qe);
        Estados[] x = {qa,qb,qc,qd,qe,qf,qg,qh,qi};
        t1.setEstados(x);
        
        Tabla t2 = t1.sacaMinimo();
        t2.imprime();
        
        
      /*  Character[] c = new Character[2];
        c[0] = 'a';
        c[1] = 'b';        
        Tabla t1 = new Tabla(7, c);
        Estados q0,q1,q2,q3,q4,qf,qe;
        q0 = new Estados(0, false);
        q1 = new Estados(1, false);
        q2 = new Estados(2, false);
        q3 = new Estados(3, false);
        q4 = new Estados(4, false);
        qf = new Estados(5, true);
        qe = new Estados(6, false);
        
        q0.setEstadosSiguientes('a', q1);
        q0.setEstadosSiguientes('b', qf);
        
        q1.setEstadosSiguientes('a', qe);
        q1.setEstadosSiguientes('b', q2);
        
        q2.setEstadosSiguientes('a', q3);
        q2.setEstadosSiguientes('b', qf);
        
        q3.setEstadosSiguientes('a', qe);
        q3.setEstadosSiguientes('b', q4);
        
        q4.setEstadosSiguientes('a', q1);
        q4.setEstadosSiguientes('b', qf);
        
        qf.setEstadosSiguientes('a', qe);
        qf.setEstadosSiguientes('b', qe);
        
        qe.setEstadosSiguientes('a', qe);
        qe.setEstadosSiguientes('b', qe);
        
        Estados[] x = {q0,q1,q2,q3,q4,qf,qe};
        t1.setEstados(x);
        
        Tabla t2 = t1.sacaMinimo();
        t2.imprime();*/
        
        Parent root = FXMLLoader.load(getClass().getResource("ControladorGrafico.fxml"));
        Scene scene = new Scene(root);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.exit(0);
            }
        });
        //Funcionamiento intermitente.
        stage.getIcons().add(new javafx.scene.image.Image("/resources/Facultad_Ciencias.png") {});
        stage.setTitle("Proyecto 2 - Base de datos");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * El método main (que es ignorado en el funcionamiento de javaFX).
     *
     * @param args los argumentos del programa.
     */
    public static void main(String[] args) {
        launch(args);
    }

}// Fin de Proyecto1.java
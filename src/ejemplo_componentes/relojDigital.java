/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_componentes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.EventObject;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author chern007
 */
public class relojDigital extends JPanel implements Serializable, ActionListener {   


    private javax.swing.JFormattedTextField txtReloj;
    private boolean tipohora24 = true;
    private int[] alarma = new int[2];
    private SimpleDateFormat format24 = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat format12 = new SimpleDateFormat("hh:mm:ss a");
    private ArrayList<alarmEventListener> listeners = new ArrayList<alarmEventListener>();

    public relojDigital() {

        super();//llamamos al constructor de la superclase (JPanel)
        setPreferredSize(new Dimension(197, 55));
        txtReloj = new javax.swing.JFormattedTextField();        
        txtReloj.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtReloj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm:ss"))));
        txtReloj.setEditable(false);
        
        alarma[0]= 10;
        alarma[1] = 7;

        javax.swing.GroupLayout pnlRelojLayout = new javax.swing.GroupLayout(this);
        this.setLayout(pnlRelojLayout);
        pnlRelojLayout.setHorizontalGroup(
                pnlRelojLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlRelojLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlRelojLayout.setVerticalGroup(
                pnlRelojLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlRelojLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ponHora();
        Timer t = new Timer(1000, this);
        t.start();

    }

    public int[] getAlarma() {
        return alarma;
    }

    public void setAlarma(int[] alarma) {
        this.alarma = alarma;
    }

    public boolean isTipohora24() {
        return tipohora24;
    }

    public void setTipohora24(boolean tipohora24) {
        this.tipohora24 = tipohora24;
    }

    private int[] getTime() {
        int[] ret = new int[3];

        Calendar now = Calendar.getInstance();
        ret[0] = now.get(Calendar.HOUR_OF_DAY);
        ret[1] = now.get(Calendar.MINUTE);
        ret[2] = now.get(Calendar.SECOND);

        return ret;
    }

    private void ponHora() {

        //int[] partesHora = getTime();
        //txtReloj.setText(partesHora[0] + ":" + partesHora[1] + ":" + partesHora[2]);
        if (tipohora24) {
            txtReloj.setText(format24.format(new Date()));
            txtReloj.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        } else {
            txtReloj.setText(format12.format(new Date()));
            txtReloj.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ponHora();
        comprobarAlarma();
    }

    public void comprobarAlarma() {

        int[] ahora = getTime();

        System.out.println(ahora[0] + ":" + String.format("%02d", ahora[1]));

        if (ahora[0] == alarma[0] && ahora[1] == alarma[1] && ahora[2] == 0) {
            //JOptionPane.showMessageDialog(txtReloj, "¡¡¡ALARMA!!!");//Para probar sin eventos
            
            //desencadenamos el evento de alarma
            triggerAlarmEvent();
        }
    }

//******************************************************************************
//******************************************************************************
//******************************************************************************
    
    
    
    
    //Se agregan manejadores de eventos
    public void addEventListener(alarmEventListener listener) {
        listeners.add(listener);
    }
    
    
    //metodo disparador del evento Alarm
    private void triggerAlarmEvent() {

        ListIterator li = listeners.listIterator();
        while (li.hasNext()) {
            alarmEventListener listener = (alarmEventListener) li.next();//     listener-->***(OBJETO OYENTE)***
            alarmEvent readerEvObj = new alarmEvent(this, this);//creamos el evento en la clase   readerEvObj-->***(OBJETO EVENTO)*** // this(relojDijital)-->***(OBJETO FUENTE)***
            (listener).alarm(readerEvObj);//decimos que recoja el evento el listener de tipo "alarm"           
        }
    }    
}//FIN DE LA CLASE "relojDigital"
    

/*se crea una interface que contenga los métodos abstractos que se dispararán cuando 
 suceda algún evento
 */
interface alarmEventListener extends EventListener {

    //metodo que se disparará cuando se cumpla la hora de la alarma
    public abstract void alarm(alarmEvent ev);

}

//se crea una clase que herede a EventObject
class alarmEvent extends EventObject {

    relojDigital relojito;

    public alarmEvent(Object source, relojDigital _relojito) {
        super(source);
        relojito = _relojito;
    }
}
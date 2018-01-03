/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_componentes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author chern007
 */
public class relojDigital extends JPanel implements Serializable, ActionListener {

    private javax.swing.JFormattedTextField txtReloj;
    private boolean tipohora24 = true;
    private SimpleDateFormat format24 = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat format12 = new SimpleDateFormat("hh:mm:ss a");

    public relojDigital() {

        super();//llamamos al constructor de la superclase (JPanel)
        setPreferredSize(new Dimension(197, 55));
        txtReloj = new javax.swing.JFormattedTextField();

        txtReloj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm:ss"))));

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

    public boolean isTipohora24() {
        return tipohora24;
    }

    public void setTipohora24(boolean tipohora24) {
        this.tipohora24 = tipohora24;
    }

    private int[] getTime() {
        int[] ret = new int[3];

        Calendar now = Calendar.getInstance();
        ret[0] = now.get(Calendar.HOUR);
        ret[1] = now.get(Calendar.MINUTE);
        ret[2] = now.get(Calendar.SECOND);

        return ret;
    }

    private void ponHora() {

        //int[] partesHora = getTime();
        //txtReloj.setText(partesHora[0] + ":" + partesHora[1] + ":" + partesHora[2]);
        
        txtReloj.setText(format24.format(new Date()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ponHora();
    }

}
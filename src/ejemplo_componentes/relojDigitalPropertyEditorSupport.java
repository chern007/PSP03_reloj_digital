/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_componentes;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author chern007
 */
public class relojDigitalPropertyEditorSupport extends PropertyEditorSupport {

    private relojDigitalPanel customPanel = new relojDigitalPanel();
    
    @Override
    public boolean supportsCustomEditor() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getCustomEditor() {
        return customPanel; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJavaInitializationString() {
        
    alarma miAlarma =  customPanel.getSelectedValue();        
        
        return "new ejemplo_componentes.alarma("+ miAlarma.getHora() +", "+miAlarma.getMinuto()+")"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue() {
        return customPanel.getSelectedValue(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

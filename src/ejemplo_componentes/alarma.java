/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_componentes;

import java.io.Serializable;

/**
 *
 * @author chern007
 */
public class alarma  implements Serializable{

    private int hora =0;
    private int minuto = 0;

    public alarma() {
    }  

    public alarma(int hora, int minuto) {
        
     this.hora = hora;
     this.minuto = minuto;
        
    }
    
    

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

}

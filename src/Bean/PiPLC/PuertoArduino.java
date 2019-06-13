package Bean.PiPLC;

import static Util.Constantes.OFF;
import static Util.Constantes.ON;
import cc.arduino.Arduino;
import lombok.Getter;
import lombok.Setter;

/**
 * @author CPZ
 */
public class PuertoArduino implements Comparable<PuertoArduino> {
    
    private @Getter @Setter int id;
    private @Getter @Setter int orden;
    private @Getter @Setter String modelo;
    private @Getter @Setter int usb;
    private @Getter @Setter String codigo, nombre, nombreCorto, descripcion;
    private @Getter @Setter String tipo;
    private @Getter @Setter int pin;
    private @Setter boolean dig;
    private @Setter @Getter boolean digPrev;
    private @Getter @Setter int ana, anaPrev, dAna;
    private @Getter @Setter int pwm, pwmPrev;
    private @Getter @Setter boolean override, logicaNegativa;
    private @Getter @Setter Arduino arduino;
    
    public PuertoArduino() {
        dAna = 5;
    }
    
    public void write(Object valor) {
        int valorOut;
        if (valor instanceof Boolean) {
            boolean b = (boolean) valor;
            if (b) {
                valorOut = 1;
            } else {
                valorOut = 0;
            }
        } else {
            valorOut = (int) valor;
        }
        switch(tipo) {
            case "digOut":
                digPrev = dig;
                if (logicaNegativa) {
                    if (valorOut == 0) {
                        valorOut = 1;
                    } else {
                        valorOut = 0;
                    }
                }
                dig = valorOut == 1;
                arduino.digitalWrite(pin, valorOut);
                break;
            case "pwmOut":
                pwmPrev = pwm;
                pwm = valorOut;
                arduino.analogWrite(pin, valorOut);
                break;
            default:
                break;
        }
    }
    
    public Object read() {
        switch(tipo) {
            case "digIn":
                digPrev = dig;
                dig = arduino.digitalRead(pin) == 1;
                return dig;
            case "anaIn":
                anaPrev = ana;
                ana = arduino.analogRead(pin);
                return ana;
            case "anaAsDigIn":
                digPrev = dig;
                dig = arduino.analogRead(pin) > 511;
                return dig;
            case "digOut":
                return arduino.digitalRead(pin) == 1;
        }
        return null;
    }

    
    public boolean cambio() {
        switch (tipo) {
            case "digIn":
                return dig != digPrev;
            case "anaAsDigIn":
                return dig != digPrev;
            case "anaIn":
                return Math.abs(ana - anaPrev) > dAna;
            case "digOut":
                return dig != digPrev;
            case "pwmOut":
                return pwm != pwmPrev;
        }
        return false;
    }
    
    public boolean flancoSubida() {
        switch (tipo) {
            case "digIn":
                if (logicaNegativa) {
                    return dig != digPrev && dig == OFF;
                }
                return dig != digPrev && dig == ON;
            case "anaAsDigIn":
                if (logicaNegativa) {
                    return dig != digPrev && dig == OFF;
                }
                return dig != digPrev && dig == ON;
            case "anaIn":
                return ana - anaPrev > dAna;
            case "digOut":
                if (logicaNegativa) {
                    return dig != digPrev && dig == OFF;
                }
                return dig != digPrev && dig == ON;
            case "pwmOut":
                return pwm > pwmPrev;
        }
        return false;
    }
    
    public boolean flancoBajada() {
        switch (tipo) {
            case "digIn":
                if (logicaNegativa) {
                    return dig != digPrev && dig == ON;
                }
                return dig != digPrev && dig == OFF;
            case "anaAsDigIn":
                if (logicaNegativa) {
                    return dig != digPrev && dig == ON;
                }
                return dig != digPrev && dig == OFF;
            case "anaIn":
                return anaPrev - ana > dAna;
            case "digOut":
                if (logicaNegativa) {
                    return dig != digPrev && dig == ON;
                }
                return dig != digPrev && dig == OFF;
            case "pwmOut":
                return pwmPrev > pwm;
        }
        return false;
    }
    
    public boolean isDig() {
        if (logicaNegativa) {
            return !dig;
        }
        return dig;
    }
    
    @Override
    public String toString() {
        StringBuilder datos = new StringBuilder();
        datos.append("id = ").append(id).append(" | ");
        datos.append("modelo = ").append(modelo).append(" | ");
        datos.append("usb = ").append(usb).append(" | ");
        datos.append("codigo = ").append(codigo).append(" | ");
        datos.append("nombre = ").append(nombre).append(" | ");
        datos.append("nombreCorto = ").append(nombreCorto).append(" | ");
        datos.append("descripción = ").append(descripcion).append(" | ");
        datos.append("tipo = ").append(tipo).append(" | ");
        datos.append("lógicaNegativa = ").append(logicaNegativa).append(" | ");
        datos.append("pin = ").append(String.format("%2d", pin)).append(" | ");
        datos.append("dig = ").append(dig).append(" | ");
        datos.append("ana = ").append(ana).append(" | ");
        datos.append("pwm = ").append(pwm);
        return datos.toString();
    }

    @Override
    public int compareTo(PuertoArduino o) {
        return Integer.compare(orden, o. orden);
    }
    
}

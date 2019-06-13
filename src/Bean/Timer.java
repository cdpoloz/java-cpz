package Bean;

import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

/**
 * @author CPZ
 */
public class Timer {

    private final PApplet sk;
    private @Getter long tiempoInicio, tiempoActual, tiempoOn;
    private @Setter int periodo;
    private @Setter float dutyCycle;
    private @Getter boolean running;
    private @Setter @Getter String nombre;

    public Timer(PApplet sk) {
        this.sk = sk;
    }

    public void iniciar() {
        tiempoInicio = sk.millis();
        running = true;
        if (nombre != null) {
            System.out.println("Iniciando timer: " + nombre);
        }
    }
    
    public void iniciar(int periodo) {
        this.periodo = periodo;
        tiempoInicio = sk.millis();
        running = true;
        if (nombre != null) {
            System.out.println("Iniciando timer: " + nombre);
        }
    }

    public void iniciar(int periodo, float dutyCycle) {
        this.periodo = periodo;
        this.dutyCycle = dutyCycle;
        tiempoInicio = sk.millis();
        running = true;
        if (nombre != null) {
            System.out.println("Iniciando timer: " + nombre);
        }
    }

    public boolean periodoPulso() {
        tiempoActual = sk.millis();
        if (tiempoActual - tiempoInicio > periodo) {
            tiempoInicio = tiempoActual;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean periodoFin() {
        tiempoActual = sk.millis();
        return tiempoActual - tiempoInicio > periodo;
    }

    public boolean periodoOnOff() {
        tiempoActual = sk.millis();
        if (tiempoActual - tiempoInicio > 2 * periodo) {
            tiempoInicio = tiempoActual;
        }
        return tiempoActual - tiempoInicio < periodo;
    }

    public boolean periodoDC(float dutyCycle) {
        this.dutyCycle = PApplet.constrain(dutyCycle, 0, 1);
        tiempoOn = (int) (periodo * this.dutyCycle);
        tiempoActual = sk.millis();
        if (tiempoActual - tiempoInicio > periodo) {
            tiempoInicio = tiempoActual;
        }
        return tiempoActual - tiempoInicio < tiempoOn;
    }

    public void apagar() {
        periodo = 0;
        dutyCycle = 0;
        tiempoInicio = 0;
        tiempoActual = 0;
        tiempoOn = 0;
        running = false;
        if (nombre != null) {
            System.out.println("Apagando: " + nombre);
        }
    }

}

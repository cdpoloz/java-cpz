package Bean.PiPLC;

import lombok.Getter;
import lombok.Setter;

/**
 * @author CPZ
 */
public class Plc {
    
    private @Getter @Setter int id;
    private @Getter @Setter String ip;
    private @Getter @Setter String nombre;
    private @Getter @Setter String funcion;
    private @Getter @Setter int cantUsb;
    private @Getter @Setter int cantDigIn;
    private @Getter @Setter int cantAnaIn;
    private @Getter @Setter int cantDigOut;
    private @Getter @Setter int cantPwmOut;
    
}

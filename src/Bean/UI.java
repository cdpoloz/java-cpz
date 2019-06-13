package Bean;

import Def.Pantalla;
import java.util.ArrayList;

/**
 * @author CPZ
 */
public class UI implements Pantalla {

    private final ArrayList<Boton> lstBotones;
    private final ArrayList<Guia> lstGuias;
    private final ArrayList<Reloj> lstRelojes;
    private final ArrayList<Texto> lstTextos;
    private final ArrayList<Imagen> lstImagenes;

    public UI() {
        lstBotones = new ArrayList();
        lstGuias = new ArrayList();
        lstRelojes = new ArrayList();
        lstTextos = new ArrayList();
        lstImagenes = new ArrayList();
    }

    @Override
    public void add(Object... o) {
        if (o[0] instanceof Boton) {
            lstBotones.add((Boton) o[0]);
        } else if (o[0] instanceof Reloj) {
            lstRelojes.add((Reloj) o[0]);
        } else if (o[0] instanceof Texto) {
            lstTextos.add((Texto) o[0]);
        } else if (o[0] instanceof Imagen) {
            lstImagenes.add((Imagen) o[0]);
        } else if (o[0] instanceof Guia) {
            lstGuias.add((Guia) o[0]);
        } else if (o[0] instanceof String && o.length > 1) {
            String clase = (String) o[0];
            switch (clase) {
                case "Boton":
                    for (int i = 1; i < o.length; i++) {
                        lstBotones.add((Boton) o[i]);
                    }
                    break;
                case "Guia":
                    for (int i = 1; i < o.length; i++) {
                        lstGuias.add((Guia) o[i]);
                    }
                    break;
                case "Reloj":
                    for (int i = 1; i < o.length; i++) {
                        lstRelojes.add((Reloj) o[i]);
                    }
                    break;
                case "Texto":
                    for (int i = 1; i < o.length; i++) {
                        lstTextos.add((Texto) o[i]);
                    }
                    break;
                case "Imagen":
                    for (int i = 1; i < o.length; i++) {
                        lstImagenes.add((Imagen) o[i]);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void display() {
        lstBotones.stream().filter(btn -> btn != null).forEach(btn -> btn.display());
        lstRelojes.stream().filter(rlj -> rlj != null).forEach(rlj -> rlj.display());
        lstTextos.stream().filter(txt -> txt != null).forEach(txt -> txt.display());
        lstImagenes.stream().filter(img -> img != null).forEach(img -> img.display());
        lstGuias.stream().filter(gui -> gui != null).forEach(gui -> gui.display());
    }

    @Override
    public void remove(String clase, int i) {
        switch (clase) {
            case "Boton":
                lstBotones.remove(i);
                break;
            case "Guia":
                lstGuias.remove(i);
                break;
            case "Reloj":
                lstRelojes.remove(i);
                break;
            case "Texto":
                lstTextos.remove(i);
                break;
            case "Imagen":
                lstImagenes.remove(i);
                break;
            default:
                break;
        }
    }

    @Override
    public void update(Object... o) {
        lstBotones.forEach(btn -> btn.update());
        lstRelojes.forEach(rlj -> rlj.update());
    }

    @Override
    public Object get(String clase, int i) {
        switch (clase) {
            case "Boton":
                return lstBotones.get(i);
            case "Guia":
                return lstGuias.get(i);
            case "Reloj":
                return lstRelojes.get(i);
            case "Texto":
                return lstTextos.get(i);
            case "Imagen":
                return lstImagenes.get(i);
            default:
                return null;
        }
    }

    @Override
    public void set(String clase, int i, Object o) {
        switch (clase) {
            case "Boton":
                lstBotones.set(i, (Boton) o);
                break;
            case "Guia":
                lstGuias.set(i, (Guia) o);
                break;
            case "Reloj":
                lstRelojes.set(i, (Reloj) o);
                break;
            case "Texto":
                lstTextos.set(i, (Texto) o);
                break;
            case "Imagen":
                lstImagenes.set(i, (Imagen) o);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased() {
    }

}

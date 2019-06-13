package Def;


/**
 * @author CPZ
 */
public interface Pantalla {
    
    public void add(Object... o);
    public void display();
    public void remove(String tipo, int i);
    public void update(Object... o);
    public Object get(String tipo, int i);  
    public void set(String tipo, int i, Object o);  
    public void mouseReleased();
    
}

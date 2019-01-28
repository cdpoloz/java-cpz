package Util;

import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @author CPZ
 */
public class JComp {

    public static void clear(Object o) {
        if (o == null) {
            return;
        }
        if (o instanceof JTable) {
            JTable tabla = (JTable) o;
            DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
        } else if (o instanceof JTextField) {
            JTextField tf = (JTextField) o;
            tf.setText("");
        } else if (o instanceof JTextArea) {
            JTextArea ta = (JTextArea) o;
            ta.setText("");
        } else if (o instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) o;
            check.setSelected(false);
        } else if (o instanceof JComboBox) {
            JComboBox cb = (JComboBox) o;
            if (cb.getMaximumRowCount() > 0) {
                cb.setSelectedIndex(0);
            }
        } else if (o instanceof JLabel) {
            JLabel lb = (JLabel) o;
            lb.setText("");
        } else if (o instanceof JDateChooser) {
            JDateChooser dc = (JDateChooser) o;
            dc.setDate(null);
        }
    }

    public static Object get(Object... o) {
        if (o[0] == null) {
            return null;
        }
        if (o[0] instanceof JTextField) {
            JTextField tf = (JTextField) o[0];
            return tf.getText();
        } else if (o[0] instanceof JTextArea) {
            JTextArea ta = (JTextArea) o[0];
            return ta.getText();
        } else if (o[0] instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) o[0];
            return check.isSelected();
        } else if (o[0] instanceof JLabel) {
            JLabel lb = (JLabel) o[0];
            return lb.getText();
        } else if (o[0] instanceof JComboBox) {
            JComboBox cb = (JComboBox) o[0];
            if (o.length == 1) {
                return cb.getSelectedItem();
            } else if (o.length == 2) {
                String mod = (String) o[1];
                if (mod.equals("indice")) {
                    return cb.getSelectedIndex();
                }
            }            
        } else if (o[0] instanceof JDateChooser) {
            JDateChooser dc = (JDateChooser) o[0];
            return dc.getDate();
        }
        return null;
    }

    public static void set(Object... o) {
        if (o[0] == null) {
            return;
        }
        if (o[0] instanceof JTextField) {
            JTextField tf = (JTextField) o[0];
            tf.setText((String) o[1]);
        } else if (o[0] instanceof JTextArea) {
            JTextArea ta = (JTextArea) o[0];
            ta.setText((String) o[1]);
        } else if (o[0] instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) o[0];
            check.setSelected((Boolean) o[1]);
        } else if (o[0] instanceof JLabel) {
            JLabel lb = (JLabel) o[0];
            lb.setText((String) o[1]);
        } else if (o[0] instanceof JComboBox) {
            JComboBox cb = (JComboBox) o[0];
            cb.setSelectedIndex((int) o[1]);
        } else if (o[0] instanceof JDateChooser) {
            JDateChooser dc = (JDateChooser) o[0];
            dc.setDate((Date) o[1]);
        }
    }

    public static void enable(Object o, boolean... b) {
        if (o == null) {
            return;
        }
        if (o instanceof JComponent) {
            JComponent c = (JComponent) o;
            c.setEnabled(b[0]);
        }
    }

    public static void visible(Object o, boolean b) {
        if (o == null) {
            return;
        }
        if (o instanceof JComponent) {
            JComponent c = (JComponent) o;
            c.setVisible(b);
        }
    }

}

/**
 *
 * @author Ivan
 */
package components;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import java.awt.event.*;
import java.awt.*;

/*
 * InternalFrameDemo.java requires:
 *   MyInternalFrame.java
 */
public class InternalFrameDemo extends JFrame
        implements ActionListener {

    JDesktopPane desktop;

    public InternalFrameDemo() {
        super("InternalFrameDemo");

        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);

        //Set up the GUI.
        desktop = new JDesktopPane(); //a specialized layered pane
        //Invocamos a los Metodos
        createFrame(); //create first "window" por Default
        setContentPane(desktop);
        setJMenuBar(createMenuBar());

        //metodo que al arrastrar se puede salir del Frame Principal
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //Set up the lone menu.
        JMenu menu = new JMenu("Document");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        //Set up the first menu item.
        JMenuItem menuItem = new JMenuItem("New");
        menuItem.setMnemonic(KeyEvent.VK_N);
        //Metodo que hace que las teclas Alt+N creen otro Frame
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        //Metodo que reemplaza el nombre de un componente a un String (Sirve para Eventos)
        menuItem.setActionCommand("new");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Set up the second menu item.
        menuItem = new JMenuItem("Quit");
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menuBar;
    }

    //React to menu selections.
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) { //new
            createFrame();
        } else { //quit
            quit();
        }
    }

    //Create a new internal frame.
    protected void createFrame() {
        MyInternalFrame frame = new MyInternalFrame();
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    //Quit the application.
    protected void quit() {
        System.exit(0);
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Decoramos las Ventanas Tipo Windows.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        InternalFrameDemo frame = new InternalFrameDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Metodo para Evitar interrupciones en el entorno de ventanas
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

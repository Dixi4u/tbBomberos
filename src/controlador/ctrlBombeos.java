package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Bomberos;
import vista.frmBomberos;


public class ctrlBombeos implements MouseListener, KeyListener{
    
    private Bomberos modelo;
    private frmBomberos vista;

    public ctrlBombeos(Bomberos modelo, frmBomberos vista){
        this.modelo = modelo;
        this.vista = vista;
    
        vista.btnAgregarB.addMouseListener(this);   
        modelo.Mostrar(vista.jtbBomberos);
        vista.btnEliminarB.addMouseListener(this);
        vista.jtbBomberos.addMouseListener(this);
        vista.btnActualizarB.addMouseListener(this);
        vista.btnLimpiarB.addMouseListener(this);
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.btnAgregarB){
            modelo.setNombre(vista.txtNombreB.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEdadB.getText()));
            modelo.setPeso(Double.parseDouble(vista.txtPesoB.getText()));
            modelo.setCorreo(vista.txtCorreoB.getText());
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbBomberos);
        }
        
        if(e.getSource() == vista.btnEliminarB){
            modelo.Eliminar(vista.jtbBomberos);
            modelo.Mostrar(vista.jtbBomberos);
        }
        
        if(e.getSource() == vista.jtbBomberos){
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnActualizarB){
            modelo.setNombre(vista.txtNombreB.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEdadB.getText()));
            modelo.setPeso(Double.parseDouble(vista.txtPesoB.getText()));
            modelo.setCorreo(vista.txtCorreoB.getText());
            
            modelo.Actualizar(vista.jtbBomberos);
            modelo.Mostrar(vista.jtbBomberos);
        }
        
        if(e.getSource() == vista.btnLimpiarB){
            modelo.limpiar(vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {      
    }
    
}

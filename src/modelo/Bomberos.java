package modelo;

import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.frmBomberos;

public class Bomberos {
    
    private String UUID_Bombero;
    private String Nombre_Bombero;
    private int Edad;
    private double Peso;
    private String Correo_Bombero;
    
    public String getUuid_paciente() {
        return UUID_Bombero;
    }

    public void setUuid_paciente(String UUID_Bombero) {
        this.UUID_Bombero = UUID_Bombero;
    }
    
    public String getNombre() {
        return Nombre_Bombero;
    }

    public void setNombre(String Nombre_Bombero) {
        this.Nombre_Bombero = Nombre_Bombero;
    }
    
    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }
    
    public double getPeso() {
        return Peso;
    }

    public void setPeso(double Peso) {
        this.Peso = Peso;
    }
    
    public String getCorreo() {
        return Correo_Bombero;
    }

    public void setCorreo(String Correo_Bombero) {
        this.Correo_Bombero = Correo_Bombero;
    }
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conexion.prepareStatement("INSERT INTO tbBomberos(UUID_Bombero, Nombre_Bombero, Edad, Peso, Correo_Bombero) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNombre());
            addProducto.setInt(3, getEdad());
            addProducto.setDouble(4, getPeso());
            addProducto.setString(5, getCorreo());
 
            //Ejecutar la consulta
            addProducto.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo: metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Bombero", "Nombre_Bombero", "Edad", "Peso", "Correo_Bombero"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbBomberos");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Bombero"), 
                    rs.getString("Nombre_Bombero"), 
                    rs.getInt("Edad"), 
                    rs.getDouble("Peso"),
                    rs.getString("Correo_Bombero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbBomberos where UUID_Bombero = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    public void cargarDatosTabla(frmBomberos vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbPacientes.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbPacientes.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbPacientes.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbPacientes.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.jtbPacientes.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.jtbPacientes.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombreB.setText(NombreDeTB);
            vista.txtEdadB.setText(EdadDeTb);
            vista.txtPesoB.setText(PesoDeTB);
            vista.txtCorreoB.setText(CorreoDeTB);
        }
    }
   
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbBomberos set Nombre_Bombero= ?, Edad = ?, Peso = ?, Correo_Bombero = ? where UUID_Bombero = ?");

                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setDouble(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void limpiar(frmBomberos vista) {
        vista.txtNombreB.setText("");
        vista.txtEdadB.setText("");
        vista.txtPesoB.setText("");
        vista.txtCorreoB.setText("");
    }
}

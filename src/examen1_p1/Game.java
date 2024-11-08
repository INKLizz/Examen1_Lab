/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1_p1;

/**
 *
 * @author Hp
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class Game extends RentItem implements MenuActions {

    protected Calendar FechaPublicacion;
    private ArrayList<String> especificaciones;
    protected int indiceActual;

    public Game(int codigo, String nombre) {
        super(codigo, nombre, 20);
        FechaPublicacion = Calendar.getInstance();
        especificaciones = new ArrayList<>();
    }

    @Override
    public String toString() {
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");  
        String formattedFecha = formatFecha.format(FechaPublicacion.getTime());
        return super.toString() + "\nFecha de Publicacion: " + formattedFecha + "\n -PS3 GAME";
    }

    public void setFechaPublicacion(int year, int mes, int dia) {
        FechaPublicacion.set(Calendar.YEAR, year);
        FechaPublicacion.set(Calendar.MONTH, mes - 1);
        FechaPublicacion.set(Calendar.DAY_OF_MONTH, dia);
        

        if (FechaPublicacion.get(Calendar.MONTH) != (mes - 1)) {
            JOptionPane.showMessageDialog(null, "Fecha invalida, dia corregido al ultimo dia del mes.");
        }
    }

    public Calendar getFechaPublicacion() {
        return FechaPublicacion;
    }

    public double pagoRenta(int dias) {
        return this.getPrecio() * dias;
    }

    public void listEspecificaciones() {
        if (indiceActual < especificaciones.size()) {
            JOptionPane.showMessageDialog(null, especificaciones.get(indiceActual));
            indiceActual++;
            listEspecificaciones();
        }
    }

    @Override
    public void submenu() {
        System.out.println("submenu() is being called");
        String opciones = "1) Actualizar Fecha de Publicacion\n2) Agregar Especificacion\n3) Ver Especificaciones";
        String opcionStr = JOptionPane.showInputDialog(null, opciones, "Submenu", JOptionPane.INFORMATION_MESSAGE);

        if (opcionStr != null && !opcionStr.isEmpty()) {
            try {
                int option = Integer.parseInt(opcionStr);
                ejecutarOpcion(option);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "DEBE INGRESAR UNA SERIE DE NUMEROS ENTEROS", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se selecciono ninguna opcion.", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        if (opcion == 1) {
            int año = 0;
            int mes = 0;
            int dia = 0;

            while (true) {
                String input = JOptionPane.showInputDialog("Ingrese el año:");
                try {
                    año = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un año valido.");
                }
            }
            while (true) {
                String input = JOptionPane.showInputDialog("Ingrese el mes (1-12):");
                try {
                    mes = Integer.parseInt(input);
                    if (mes >= 1 && mes <= 12) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor ingrese un mes valido entre 1 y 12.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un mes valido.");
                }
            }
            while (true) {
                String input = JOptionPane.showInputDialog("Ingrese el dia:");
                try {
                    dia = Integer.parseInt(input);
                    if (dia >= 1 && dia <= 31) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor ingrese un dia valido entre 1 y 31.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un dia valido.");
                }
            }
            this.setFechaPublicacion(año, mes, dia);
            JOptionPane.showMessageDialog(null, "Fecha de publicacion actualizada a: " + dia + "/" + mes + "/" + año);

        } else if (opcion == 2) {
            String especificacion = JOptionPane.showInputDialog(null, "Ingrese una nueva especificacion:");
            if (especificacion != null && !especificacion.trim().isEmpty()) {
                especificaciones.add(especificacion);
                JOptionPane.showMessageDialog(null, "Especificacion agregada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se ingreso ninguna especificacion.");
            }

        } else if (opcion == 3) {
            listEspecificaciones();

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de opcion valido.");
        }
    }
}
package examen1_p1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Dell
 */
import java.util.Calendar;

public class Movie extends RentItem {

    private Calendar fecha;
    private Calendar estreno;

    public Movie(int codigo, String nombre, double precio) {
        super(codigo, nombre, precio);

        this.fecha = Calendar.getInstance();
        this.estreno = Calendar.getInstance();

    }

    public Calendar getEstreno() {
        return estreno;
    }

    public void setEstreno(int year, int mes, int dia) {
        estreno.set(Calendar.YEAR, year);
        estreno.set(Calendar.MONTH, mes - 1);
        estreno.set(Calendar.DAY_OF_MONTH, dia);
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        Calendar fechaLimiteEstreno = (Calendar) estreno.clone();
        fechaLimiteEstreno.add(Calendar.MONTH, 3);

        if (fecha.before(fechaLimiteEstreno)) {
            return "ESTRENO";
        } else {
            return "NORMAL";
        }

    }

    @Override
    public String toString() {
        return super.toString() + "\n - " + getEstado() + " - Movie";
    }

    @Override
    public double pagoRenta(int dias) {
        double costo = getPrecio();
        String estado = getEstado();

        if ("ESTRENO".equals(estado) && dias > 2) {
            costo += (dias - 2) * 50;
        } else if ("NORMAL".equals(estado) && dias > 5) {
            costo += (dias - 5) * 30;
        }

        return costo;
    }
}

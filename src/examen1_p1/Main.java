/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1_p1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Laura Sabillon
 */
public class Main extends JFrame {

    private RentItem item;
    private ArrayList<RentItem> objetos = new ArrayList<>();

    Main() {

        setTitle("MENU: MOVIES Y GAMES");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 70));

        JButton agregar = new JButton("Agregar Item");
        JButton rentar = new JButton("Rentar Item");
        JButton submenu = new JButton("Ejecutar Submenu");
        JButton imprimir = new JButton("Mostrar Items");

        agregar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String opcion = JOptionPane.showInputDialog(Main.this, "ESCOGER AGREGAR MOVIE O GAME");

                if (opcion != null) {
                    if (opcion.equalsIgnoreCase("GAME") || opcion.equalsIgnoreCase("MOVIE")) {

                        String codStr = JOptionPane.showInputDialog(Main.this, "Agregar Código del Ítem");
                        int codigo;

                        try {
                            codigo = Integer.parseInt(codStr);

                            ///AKJDJSHDAKJHFDakjhdfkaHDKAshfd
                            boolean codigoExistente = objetos.stream().anyMatch(obj -> obj.getCodigo() == codigo);
                            if (codigoExistente) {
                                JOptionPane.showMessageDialog(Main.this, "Codigo ya existente. Intente con otro codigo.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(Main.this, "Codigo invalido. Debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String nombre = JOptionPane.showInputDialog(Main.this, "Agregar Nombre del Item");
                        if (nombre == null || nombre.isEmpty()) {
                            JOptionPane.showMessageDialog(Main.this, "Nombre invalido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String precioStr = JOptionPane.showInputDialog(Main.this, "Agregar Precio del Item");
                        double precio;

                        try {
                            precio = Double.parseDouble(precioStr);
                            if (precio <= 0) {
                                JOptionPane.showMessageDialog(Main.this, "Precio invalido. Debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(Main.this, "Precio invalido. Debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        if (opcion.equalsIgnoreCase("GAME")) {
                            Game game = new Game(codigo, nombre);
                            objetos.add(game);
                            JOptionPane.showMessageDialog(Main.this, "Juego agregado exitosamente.");

                        } else if (opcion.equalsIgnoreCase("MOVIE")) {
                            Movie movie = new Movie(codigo, nombre, precio);
                            objetos.add(movie);
                            JOptionPane.showMessageDialog(Main.this, "Película agregada exitosamente.");
                        }

                    } else {
                        JOptionPane.showMessageDialog(Main.this, "TIPO INCORRECTO.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        rentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = JOptionPane.showInputDialog(Main.this, "INGRESAR CODIGO DE ITEM:");

                if (codigo != null) {
                    try {
                        int code = Integer.parseInt(codigo);

                        RentItem itemEncontrado = null;

                        for (RentItem item : objetos) {
                            if (item.getCodigo() == code) {
                                itemEncontrado = item;
                                break;
                            }
                        }

                        if (itemEncontrado != null) {
                            String diasS = JOptionPane.showInputDialog(Main.this, "Ingresar cuantos días desea rentar:");

                            try {
                                int dias = Integer.parseInt(diasS);

                                if (dias <= 0) {
                                    JOptionPane.showMessageDialog(Main.this, "Dias invalidos. Debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                double montoTotal = itemEncontrado.pagoRenta(dias);
                                JOptionPane.showMessageDialog(Main.this, "Item encontrado: " + itemEncontrado.toString() + "\nRenta Total: " + montoTotal);

                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(Main.this, "Daas invalidos. Debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(Main.this, "Codigo no encontrado. Intente con otro codigo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Main.this, "Codigo invalido. Debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        submenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codigo = JOptionPane.showInputDialog(Main.this, "INGRESAR CODIGO DE ITEM:");
                if (codigo != null) {
                    try {
                        int code = Integer.parseInt(codigo);
                        boolean found = false;

                        for (RentItem game : objetos) {
                            if (game instanceof Game && game.getCodigo() == code) {
                                Game juego = (Game) game;
                                juego.submenu();
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            JOptionPane.showMessageDialog(null, "CODIGO NO ENCONTRADO");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "CODIGO INVALIDO");
                    }
                }
            }
        });
        imprimir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame mostrarImp = new JFrame("DATOS DE ITEMS");
                mostrarImp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mostrarImp.setSize(400, 500);
                mostrarImp.setLocationRelativeTo(null);

                JTextPane pane = new JTextPane();
                StringBuilder content = new StringBuilder();

                if (objetos != null && !objetos.isEmpty()) {
                    for (Object item : objetos) {
                        content.append(item).append("\n---------------------------------------------\n");
                    }
                } else {
                    content.append("NO HAY NINGUN ITEM...");
                }

                pane.setText(content.toString());
                pane.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(pane);

                JButton volver = new JButton("Volver");
                volver.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostrarImp.dispose();
                    }
                });

                mostrarImp.setLayout(new BorderLayout());
                mostrarImp.add(scrollPane, BorderLayout.CENTER);
                mostrarImp.add(volver, BorderLayout.SOUTH);

                mostrarImp.setVisible(true);
            }
        });

        panel.add(agregar);
        panel.add(rentar);
        panel.add(submenu);
        panel.add(imprimir);
        add(panel);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main ventana = new Main();
            ventana.setVisible(true);
        });
    }
}

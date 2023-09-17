package edu.formularios;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class frmPizza {
    private JPanel jpanelPrincipal;
    private JComboBox<Topping> comboBoxToppings;
    private JTextField txtNombrePizza;
    private JButton bntAddIngrediente;
    private JLabel lblTotal;
    private JList<String> lista1;
    private JButton btnPreparar;
    private JComboBox<Pizza> comboBoxPizza;
    private JRadioButton smallRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton largeRadioButton;
    private JList<String> lista2;

    private DefaultListModel<String> modeLista = new DefaultListModel<>();
    private DefaultListModel<String> listaPreparacion = new DefaultListModel<>();

    private double total = 0;

    private List<Topping> ingredientes = new ArrayList<>();
    private List<Pizza> pizzas = new ArrayList<>();
    private List<Topping> ingredientesSeleccionados = new ArrayList<>();
    private List<Topping> ingredientesYoLaArmo = new ArrayList<>();

    public JPanel getJpanelPrincipal() {
        return jpanelPrincipal;
    }

    public frmPizza() {
        cargarIngredientesPredeterminadosYoLaArmo();
        cargarPizzas();

        bntAddIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Topping ingrediente = (Topping) comboBoxToppings.getSelectedItem();

                if (ingrediente != null) {
                    modeLista.addElement(ingrediente.toString());
                    lista1.setModel(modeLista);
                    total += ingrediente.getPrecio();
                    lblTotal.setText(String.valueOf(total));
                }
            }
        });

        btnPreparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePizza = txtNombrePizza.getText();
                Pizza pizzaSeleccionada = (Pizza) comboBoxPizza.getSelectedItem();

                if (nombrePizza.isEmpty() || pizzaSeleccionada == null || (!smallRadioButton.isSelected() && !mediumRadioButton.isSelected() && !largeRadioButton.isSelected()) || modeLista.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe completar todos los campos y agregar al menos un ingrediente para preparar la pizza.");
                } else {
                    // Muestra la ventana de progreso
                    JFrame progresoFrame = new JFrame("Progreso");
                    progresoFrame.setSize(300, 200);
                    progresoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JList<String> progresoLista = new JList<>(listaPreparacion);
                    progresoFrame.add(new JScrollPane(progresoLista));
                    progresoFrame.setVisible(true);

                    // Limpia la lista de preparación
                    listaPreparacion.clear();

                    // Agrega el mensaje "Agregando ingredientes" a la lista de preparación
                    listaPreparacion.addElement("Agregando ingredientes");

                    // Agrega los ingredientes seleccionados uno por uno a la lista de preparación con pausas de 1 segundo
                    for (int i = 0; i < modeLista.getSize(); i++) {
                        String ingrediente = modeLista.get(i);
                        listaPreparacion.addElement(ingrediente);
                        try {
                            Thread.sleep(1000); // Pausa de 1 segundo entre ingredientes
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }

                    // Cierra la ventana de progreso después de 5 segundos
                    Timer timer = new Timer(5000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            progresoFrame.dispose();

                            // Muestra el mensaje de que la pizza está lista
                            String mensaje = "Pizza preparada y lista para servir.\nNombre de la pizza: " + nombrePizza;
                            if (pizzaSeleccionada != null) {
                                mensaje += "\nTotal a pagar: $" + (total);
                            }
                            JOptionPane.showMessageDialog(null, mensaje);

                            // Limpia todo después de preparar la pizza
                            txtNombrePizza.setText("");
                            modeLista.clear();
                            total = 0;
                            lblTotal.setText("0.0");
                            smallRadioButton.setSelected(false);
                            mediumRadioButton.setSelected(false);
                            largeRadioButton.setSelected(false);
                        }
                    });

                    timer.setRepeats(false);
                    timer.start();
                }
            }
        });



        // Agregar manejador de eventos de doble clic para quitar ingredientes de la lista1
        lista1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = lista1.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        String ingredienteSeleccionado = modeLista.get(selectedIndex);
                        String[] parts = ingredienteSeleccionado.split(" - "); // Separar el nombre del precio
                        String nombreIngrediente = parts[0]; // Obtener el nombre del ingrediente
                        double precioIngrediente = Double.parseDouble(parts[1]); // Obtener el precio del ingrediente

                        // Resta el precio del ingrediente seleccionado del total
                        total -= precioIngrediente;

                        // Actualiza el total
                        lblTotal.setText(String.valueOf(total));

                        // Elimina el ingrediente de la lista de ingredientes seleccionados
                        ingredientesSeleccionados.remove(nombreIngrediente);

                        // Elimina el elemento de la lista de la GUI
                        modeLista.remove(selectedIndex);
                    }
                }
            }
        });

        comboBoxPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pizza selectedPizza = (Pizza) comboBoxPizza.getSelectedItem();

                if (selectedPizza != null) {
                    if (selectedPizza.getName().equals("Yo la armo")) {
                        // Si se selecciona "Yo La Armo," cargar los ingredientes predeterminados
                        comboBoxToppings.setEnabled(true);
                        cargarIngredientesPredeterminadosYoLaArmo();
                        txtNombrePizza.setEnabled(true); // Habilitar el campo "Nombre de la pizza"
                    } else {
                        // Si se selecciona otra pizza, habilitar el ComboBox y cargar sus ingredientes
                        comboBoxToppings.setEnabled(true);
                        cargarIngredientes(selectedPizza.getToppings());
                        txtNombrePizza.setText(""); // Limpiar el nombre de la pizza
                    }
                }
            }
        });
    }

    // Cargar los ingredientes predeterminados de "Yo La Armo" en la lista
    private void cargarIngredientesPredeterminadosYoLaArmo() {
        ingredientesYoLaArmo.clear();
        ingredientesYoLaArmo.add(new Topping("Champiñones", 3));
        ingredientesYoLaArmo.add(new Topping("Tomate", 2));
        ingredientesYoLaArmo.add(new Topping("Cebolla", 1));
        ingredientesYoLaArmo.add(new Topping("Salchicha", 5));
        ingredientesYoLaArmo.add(new Topping("Calamares", 1));
        ingredientesYoLaArmo.add(new Topping("Chocho", 6));
        // Agrega más ingredientes predeterminados para "Yo La Armo" según sea necesario
        cargarIngredientes(ingredientesYoLaArmo);
    }

    // Cargar ingredientes en el ComboBox a partir de una lista de ingredientes
    private void cargarIngredientes(List<Topping> ingredientes) {
        DefaultComboBoxModel<Topping> model = new DefaultComboBoxModel<>(ingredientes.toArray(new Topping[0]));
        comboBoxToppings.setModel(model);
    }

    private void limpiarComboBoxToppings() {
        DefaultComboBoxModel<Topping> model = new DefaultComboBoxModel<>();
        comboBoxToppings.setModel(model);
    }

    private void cargarPizzas() {
        // Crear diferentes especialidades de pizza y agregarlas al comboBoxPizza
        Pizza pizzanada = new Pizza("");
        Pizza pizzaYoLaArmo = new Pizza("Yo la armo");
        Pizza pizzaChurrasco = new Pizza("Pizza Churrasco");
        Pizza pizzaDelux = new Pizza("Pizza Delux");
        Pizza pizzaItaliana = new Pizza("Pizza Italiana");
        Pizza pizzaQueso = new Pizza("Pizza Queso");

        // Agregar ingredientes predeterminados a las pizzas especiales
        pizzaChurrasco.addTopping(new Topping("Carne de res", 6));
        pizzaChurrasco.addTopping(new Topping("Cebolla Morada cortana en aros finos", 3));
        pizzaChurrasco.addTopping(new Topping("Tomate en rodajas", 2));
        pizzaChurrasco.addTopping(new Topping("Quezo Mozarella", 3));
        pizzaChurrasco.addTopping(new Topping("Aceitunas Negras", 5));
        pizzaChurrasco.addTopping(new Topping("Maíz Dulce", 4));
        pizzaChurrasco.addTopping(new Topping("Salsa de Chimichurri", 1));

        pizzaDelux.addTopping(new Topping("Peperoni", 3));
        pizzaDelux.addTopping(new Topping("Jamón", 4));
        pizzaDelux.addTopping(new Topping("Cebolla", 2));
        pizzaDelux.addTopping(new Topping("Aceitunas", 5));
        pizzaDelux.addTopping(new Topping("Salchicha Italiana", 8));
        pizzaDelux.addTopping(new Topping("Tomate", 2));

        pizzaItaliana.addTopping(new Topping("Salsa de tomate", 2));
        pizzaItaliana.addTopping(new Topping("Quezo Mozarella", 3));
        pizzaItaliana.addTopping(new Topping("Albahaca", 1));

        pizzaQueso.addTopping(new Topping("Salsa de Tomate", 2));
        pizzaQueso.addTopping(new Topping("Queso fiordilatte en rodajas", 5));
        pizzaQueso.addTopping(new Topping("Albahaca", 1));

        pizzas.add(pizzanada);
        pizzas.add(pizzaYoLaArmo);
        pizzas.add(pizzaChurrasco);
        pizzas.add(pizzaDelux);
        pizzas.add(pizzaItaliana);
        pizzas.add(pizzaQueso);

        DefaultComboBoxModel<Pizza> pizzaModel = new DefaultComboBoxModel<>(pizzas.toArray(new Pizza[0]));
        comboBoxPizza.setModel(pizzaModel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pizza Order Form");
        frame.setContentPane(new frmPizza().getJpanelPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

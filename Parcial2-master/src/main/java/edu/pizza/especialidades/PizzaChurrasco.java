package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaChurrasco extends Pizza {
    private String salsa;

    public  PizzaChurrasco(String name, double price, String salsa, Topping... toppings){
        super(name,

                toppings);
        this.salsa=salsa;

    }


    public String getSalsa() {
        return salsa;
    }

    public void setSalsa(String salsa) {
        this.salsa = salsa;
    }



}
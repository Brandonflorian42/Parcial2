# P2C6_2023
Ejercicio para repasar sobre poo, interfaces y Listas

## **Ejercicio 1:** Crear una clase Pizza
La primera clase que vamos a crear es "Pizza".

Esta clase debe tener las siguientes propiedades:

- nombre: un string que representa el nombre de la pizza.
- precio: un double que representa el precio de la pizza.
- ingredientes: una lista de strings que representan los ingredientes de la pizza (por ejemplo, queso, tomate, cebolla).
Además, la clase Pizza debe tener un método "preparar" que simule la preparación de la pizza. Este método debe imprimir un mensaje que indique que la pizza está listo para ser servida.

## Ejercicio 2: Heredar de la clase Pizza

Vamos a crear una nueva clase llamada "PizzaItaliana" que herede de la clase Pizza. 

La clase PizzaItaliana debe tener una propiedad adicional llamada"salsa" que sea un string que represente la salsa utilizada en la pizza. 

Además, la clase PizzaItaliana debe tener un método "preparar" que sobreescriba el método del mismo nombre de la clase Pizza para agregar la salsa a la pizza durante la preparación.

## Ejercicio 3: Crear una clase Topping

Vamos a crear una clase llamada "Topping" que represente un topping para la pizza (por ejemplo, queso, champiñones, etc.). La clase Topping debe tener una propiedad "nombre" que sea un string que represente el nombre del topping, y un método "agregar" que permita agregar el topping a una pizza.

## Ejercicio 4: Agregar toppings a una pizza

Creamos una instancia de la clase PizzaItaliana y agregamos varios toppings a ella utilizando el método "agregar" de la clase Topping. Luego, llamamos al método "preparar" de la clase PizzaItaliana para verificar que los toppings han sido agregados correctamente.

## Ejercicio 5: Inheritance with interfaces

Paquete nuevo Interfaces Crearemos una interfaz llamada "Preparable" que tenga un método "prepare". 

Luego, crearemos una clase "PizzaBase" que implemente esta interfaz y tenga una propiedad "ingredients". 

Finalmente, crearemos una clase "PizzaSpecial" que herede de la clase PizzaBase y tenga una propiedad adicional "salsa". 

La clase PizzaSpecial debe implementar el método "prepare" de la interfaz Preparable para agregar la salsa a la pizza durante la preparación.

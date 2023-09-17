package edu.listas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListSample {
    public void explicacion1(){
        List<String> taskList = new ArrayList<>();
        taskList.add("Hacer compras");
        taskList.add("Sacar al perro");
        taskList.add("Hacer la cama");
        taskList.add("Lavar los platos");

        //get the first element from taskList
        String firstTask = taskList.get(0);
        System.out.println("First task: indice 0:" + firstTask);

        //delete the first element from taskList
        taskList.remove(0);


        System.out.println("New first task: indice 0:" + taskList.get(0));
        System.out.println("List of the rest of the tasks:");
        //iterate over the list
        for (String task : taskList) {
            System.out.println(task);
        }

    }

    public void listaValores(){

        //obtener los numeros del teclado
        //obtener un valor del teclado en consola


        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese un numero");
        int numero = s.nextInt();


        List<Integer> numeros = new ArrayList<>();
        numeros.add(numero);


        numeros.add(2);
        numeros.add(3);
        numeros.add(4);
        numeros.add(5);

        int suma = 0;
        for (Integer numero1 : numeros) {
            suma = suma + numero1;
        }

    }

    public void EjemploListaTareas() {
        List<ClsTaskList> tareas = new ArrayList<>();
        ClsTaskList task = new ClsTaskList();
        task .setTaskName("Hacer compras");
        task .setTaskDescription("Comprar leche, huevos, pan, cereal, fruta");
        tareas.add(task);
        task = new ClsTaskList();

        task.setTaskName("Sacar al perro");
        task.setTaskDescription("Sacar al perro al parque");
        tareas.add(task);
        task = new ClsTaskList();

        task.setTaskName("Hacer la cama");
        task.setTaskDescription("Cambiar las sábanas y acomodar las almohadas");
        tareas.add(task);
        task = new ClsTaskList();

        task.setTaskName("Lavar los platos");
        task.setTaskDescription("Lavar los platos y guardarlos");
        tareas.add(task);
        task = new ClsTaskList();

//        System.out.println("Lista de tareas:");
//        for (ClsTaskList tarea : tareas) {
//            System.out.println("Tarea: " + tarea.getTaskName());
//            System.out.println("Descripción: " + tarea.getTaskDescription());
//        }

        //modificar el estado de la tarea 1
        tareas.get(1).setDone(true);
        //mostrar solo las tareas completadas
        System.out.println("Lista de tareas completadas:");
        for (ClsTaskList tarea : tareas) {
            if (tarea.isDone()) {
                System.out.println("Tarea: " + tarea.getTaskName());
                System.out.println("Descripción: " + tarea.getTaskDescription());
            }
        }

    }

}

import beans.Employee;
import beans.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        HrManager hrManager = new HrManager();

        main:
        while (true) {

            System.out.println("seleccione la opción: ");
            System.out.println("1. listar empleados");
            System.out.println("2. listar trabajos");
            System.out.println("3. Crear trabajo");
            System.out.println("4. Salir");
            System.out.println("5. Buscar Empleado");
            System.out.print("Opción: ");

            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> {
                    for (Employee e : hrManager.listarEmpleados())
                        e.imprimeDatos();
                }
                case "2" -> {
                    for (Job j : hrManager.listarTrabajos()) {
                        j.imprimeDatos();
                    }
                }
                case "3" -> {
                    boolean isTrabajoCreado = hrManager.crearTrabajo();
                    if (isTrabajoCreado) {
                        System.out.println("trabajo creado");
                    } else {
                        System.out.println("no se pudo crear el trabajo");
                    }
                }
                case "4" -> {
                    break main;
                }
                case "5" -> {
                    System.out.print("Ingrese el nombre o apellido del empleado a buscar: ");
                    String nombreApellido = scanner.nextLine();
                    ArrayList<Employee> employeeArrayList = hrManager.buscarEmpleado(nombreApellido);
                    System.out.println("Cantidad de empleados encontrados: " + employeeArrayList.size());
                    for (Employee e : employeeArrayList)
                        e.imprimeDatos();
                }
            }
        }
    }
}
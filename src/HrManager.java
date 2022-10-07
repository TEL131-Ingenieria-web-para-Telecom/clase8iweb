import beans.Employee;
import beans.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HrManager {

    public ArrayList<Employee> listarEmpleados() {

        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = "root";
        String password = "root";

        String url = "jdbc:mysql://127.0.0.1:3306/hr";

        String sql = "select * from employees";

        //try-with-resources
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt(1));
                employee.setJobId(rs.getString("job_id"));
                employee.setFirstName(rs.getString(2));
                employee.setLastName(rs.getString(3));
                employee.setManagerId(rs.getInt("manager_id"));
                listaEmpleados.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEmpleados;
    }

    public ArrayList<Job> listarTrabajos() {

        ArrayList<Job> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = "root";
        String password = "root";

        String url = "jdbc:mysql://127.0.0.1:3306/hr";

        String sql = "select * from jobs";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getString(1));
                job.setJobTitle(rs.getString(2));
                job.setMinSalary(rs.getInt(3));
                job.setMaxSalary(rs.getInt(4));
                lista.add(job);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public boolean crearTrabajo() {
        Scanner scanner = new Scanner(System.in);
        Job job = new Job();
        System.out.print("Ingrese el job id: ");
        job.setJobId(scanner.nextLine());
        System.out.print("Ingrese el jobTitle: ");
        job.setJobTitle(scanner.nextLine());
        System.out.print("Tiene salario mínimo? (S/N)");
        String hasMinSalary = scanner.nextLine();
        if (hasMinSalary.equalsIgnoreCase("s")) {
            System.out.print("Ingrese el minSalary: ");
            job.setMinSalary(Integer.parseInt(scanner.nextLine()));
        }
        System.out.print("Ingrese el maxSalary: ");
        job.setMaxSalary(Integer.parseInt(scanner.nextLine()));
        return guardarJobEnBd(job);
    }

    public boolean guardarJobEnBd(Job job) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = "root";
        String password = "root";

        String url = "jdbc:mysql://127.0.0.1:3306/hr";

        String sql = "insert into jobs (job_id, job_title, min_salary, max_salary) VALUES (?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, job.getJobId());
            pstmt.setString(2, job.getJobTitle());
            if (job.getMinSalary() == null) {
                pstmt.setNull(3, Types.INTEGER);
            }else{
                pstmt.setInt(3, job.getMinSalary());
            }
            pstmt.setInt(4, job.getMaxSalary());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<Employee> buscarEmpleado(String nombreApellido) {
        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String username = "root";
        String password = "root";

        String url = "jdbc:mysql://127.0.0.1:3306/hr";

        String sql = "SELECT * FROM employees where lower(first_name) like ? or lower(last_name) like ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, "%" + nombreApellido + "%");
            pstmt.setString(2, "%" + nombreApellido + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeId(rs.getInt(1));
                    employee.setJobId(rs.getString("job_id"));
                    employee.setFirstName(rs.getString(2));
                    employee.setLastName(rs.getString(3));
                    employee.setManagerId(rs.getInt("manager_id"));
                    listaEmpleados.add(employee);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEmpleados;
    }
}

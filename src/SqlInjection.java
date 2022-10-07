import java.sql.*;
import java.util.Scanner;

public class SqlInjection {
    /**
     * @base_de_datos: hr
     * @tabla: employees
     * @Credenciales:
     * @usuario: first_name
     * @password: last_name
     * @probar: usuario: cualquier cosa, password: ' or '1'='1
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de pruebas\n");
        System.out.println("-----------------------------");

        System.out.print("Ingrese su usuario: ");
        String usuario = sc.nextLine();

        System.out.print("Ingrese su password: ");
        String password = sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/hr";

        String sql = "SELECT * FROM employees WHERE first_name = ? AND last_name = ?";

        System.out.println(sql);
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, usuario);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Acceso concedido");
                } else {
                    System.out.println("Credenciales erroneas");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

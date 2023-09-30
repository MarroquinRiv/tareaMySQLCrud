package edu.ejercicios;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MySQLCRUD {
    private Connection conn;
    private Scanner scanner;

    public MySQLCRUD() {
        conn = null;
        scanner = new Scanner(System.in);
    }

    public void conexion() throws SQLException {
        // Establece la conexión a tu base de datos MySQL
        String jdbcUrl = "jdbc:mysql://localhost:3306/crud";
        String usuario = "root";
        String contraseña = "Astrid2005";

        conn = DriverManager.getConnection(jdbcUrl, usuario, contraseña);
    }

    public void consultar() throws SQLException {
        conexion();

        String query = "SELECT * FROM table1";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            System.out.println("Codigo: " + resultSet.getInt("Codigo") +
                    " Nombre: " + resultSet.getString("Nombre") +
                    " Apellido: " + resultSet.getString("Apellido") +
                    " Fecha_Registro: " + resultSet.getDate("Fecha_Registro") +
                    " Sueldo_Base: " + resultSet.getBigDecimal("Sueldo_Base") +
                    " Sexo: " + resultSet.getString("Sexo") +
                    " Edad: " + resultSet.getInt("Edad") +
                    " Longitud_Casa: " + resultSet.getFloat("Longitud_Casa") +
                    " Latitud_Casa: " + resultSet.getFloat("Latitud_Casa") +
                    " Comentarios: " + resultSet.getString("Comentarios"));
        }

        // Cierra las conexiones
        resultSet.close();
        ps.close();
        conn.close();
    }

    public void agregar() throws SQLException {
        conexion();

        System.out.println("Ingrese el Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el Nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el Apellido: ");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese la Fecha de Registro (AAAA-MM-DD): ");
        String fechaRegistro = scanner.nextLine();

        System.out.println("Ingrese el Sueldo Base: ");
        BigDecimal sueldoBase = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.println("Ingrese el Sexo: ");
        String sexo = scanner.nextLine();

        System.out.println("Ingrese la Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese la Longitud de la Casa: ");
        float longitudCasa = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Ingrese la Latitud de la Casa: ");
        float latitudCasa = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Ingrese los Comentarios (opcional): ");
        String comentarios = scanner.nextLine();

        String query = "INSERT INTO table1 (Codigo, Nombre, Apellido, Fecha_Registro, Sueldo_Base, Sexo, Edad, Longitud_Casa, Latitud_Casa, Comentarios) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, codigo);
        ps.setString(2, nombre);
        ps.setString(3, apellido);
        ps.setString(4, fechaRegistro);
        ps.setBigDecimal(5, sueldoBase);
        ps.setString(6, sexo);
        ps.setInt(7, edad);
        ps.setFloat(8, longitudCasa);
        ps.setFloat(9, latitudCasa);
        ps.setString(10, comentarios);

        ps.executeUpdate();

        // Cierra las conexiones
        ps.close();
        conn.close();
    }

    public void actualizar() throws SQLException {
        conexion();

        System.out.println("Ingrese el Código del registro que desea actualizar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nuevo Sueldo Base: ");
        BigDecimal nuevoSueldo = scanner.nextBigDecimal();
        scanner.nextLine();

        String query = "UPDATE table1 SET Sueldo_Base = ? WHERE Codigo = ?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setBigDecimal(1, nuevoSueldo);
        ps.setInt(2, codigo);

        ps.executeUpdate();

        // Cierra las conexiones
        ps.close();
        conn.close();
    }

    public void eliminar() throws SQLException {
        conexion();

        System.out.println("Ingrese el Código del registro que desea eliminar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        String query = "DELETE FROM table1 WHERE Codigo = ?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, codigo);

        ps.executeUpdate();

        // Cierra las conexiones
        ps.close();
        conn.close();
    }

    public static void main(String[] args) throws SQLException {
        MySQLCRUD mySQLCRUD = new MySQLCRUD();
    }
}

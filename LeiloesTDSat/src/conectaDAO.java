
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {

    /**
     *
     * @return
     * @throws SQLException
     */
    public Connection connectDB() throws SQLException{
        Connection conn = null;


    public Connection connectDB() {
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=");

        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/uc11", // linha de conexao
                    "Admin", // usuario do mysql
                    "Rps@32admin"// senha do mysql
            );
            return conn;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar" + erro.getMessage());
            return null;
        }
        return conn;
    }

}

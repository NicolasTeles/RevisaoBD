/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    private static String url = "jdbc:postgresql://200.18.128.54/aula";
    private static String usuario = "aula";
    private static String senha = "aula";

    private static Connection minhaConexao = null;

    public static Connection getConexao() {
        try {
            if (Conexao.minhaConexao == null) {
                Conexao.minhaConexao = DriverManager.getConnection(url, usuario, senha);
            }
            return minhaConexao;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ocorreu um erro ao se conectar ao banco de dados");
            System.out.println("Erro: " + ex.getMessage());
        }
        return null;
    }
}

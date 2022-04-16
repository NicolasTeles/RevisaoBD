/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.Conexao;
import Model.Bebida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicol
 */
public class BebidasDAO {
    public boolean inserirBebida(Bebida b){
       
        try {
            String SQL="INSERT INTO nicolas_teles_lanchonete.bebidas(nome_bebida, codigo_bebida, preco_bebida, descricao_bebida) VALUES (?,?,?,?)";
            Connection minhaConexao = Conexao.getConexao();
            PreparedStatement comando = minhaConexao.prepareStatement(SQL);
            comando.setString(1, b.getNome_bebida());
            comando.setInt(2, b.getCodigo_bebida());
            comando.setString(3, b.getPreco_bebida());
            comando.setString(4, b.getDesc_bebida());
            int retorno=comando.executeUpdate();
            if(retorno>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BebidasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<Bebida> listarBebidas(){
        
        try {
            String SQL ="SELECT * FROM nicolas_teles_lanchonete.bebidas";
            List<Bebida> listaDeBebidas = new ArrayList<Bebida>();
            Connection c = Conexao.getConexao();
            PreparedStatement ps = c.prepareStatement(SQL);
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Bebida atual = new Bebida();
                atual = this.pegaDados(resultado);
                listaDeBebidas.add(atual);
            }
            return listaDeBebidas;
        } catch (SQLException ex) {
            Logger.getLogger(BebidasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private Bebida pegaDados(ResultSet resultado){
        
        try {
            Bebida atual = new Bebida();
            atual.setCodigo_bebida(resultado.getInt("codigo_bebida"));
            atual.setDesc_bebida(resultado.getString("descricao_bebida"));
            atual.setNome_bebida(resultado.getString("nome_bebida"));
            atual.setPreco_bebida(resultado.getString("preco_bebida"));
            return atual;
        } catch (SQLException ex) {
            Logger.getLogger(BebidasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Bebida consulta(String codigo_bebida){
        
        try {
            String SQL="SELECT * FROM nicolas_teles_lanchonete.bebidas WHERE codigo_bebida = ?";
            Connection c = Conexao.getConexao();
            PreparedStatement ps = c.prepareStatement(SQL);
            ps.setInt(1, Integer.valueOf(codigo_bebida));
            ResultSet resultado = ps.executeQuery();
            if(resultado.next()){
                Bebida atual = new Bebida();
                atual = this.pegaDados(resultado);
                return atual;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BebidasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean atualizaDados(Bebida dados){
        
        try {
            String SQL = "UPDATE nicolas_teles_lanchonete.bebidas SET nome_bebida=?, descricao_bebida=?, preco_bebida=? WHERE codigo_bebida=?";
            Connection c = Conexao.getConexao();
            PreparedStatement comando = c.prepareStatement(SQL);
            comando.setString(1, dados.getNome_bebida());
            comando.setString(2, dados.getDesc_bebida());
            comando.setString(3, dados.getPreco_bebida());
            comando.setInt(4, dados.getCodigo_bebida());
            int retorno = comando.executeUpdate();
            if(retorno>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BebidasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Bebida consulta(Bebida dados){
        
        try {
            String SQL = "SELECT * FROM nicolas_teles_lanchonete.bebidas ";
            String filtro = "";
            Connection c = Conexao.getConexao();
            
            if(dados != null && dados.getCodigo_bebida()>0){
                filtro = "WHERE codigo_bebida = "+dados.getCodigo_bebida();
            }
            if(dados != null && dados.getDesc_bebida()!= null && !dados.getDesc_bebida().equalsIgnoreCase("")){
                if(!filtro.equalsIgnoreCase("")){
                    filtro += " AND descricao_bebida = "+dados.getDesc_bebida();
                }else{
                    filtro = "WHERE descricao_bebida ilike '%"+dados.getDesc_bebida()+"%'";
                }
            }
            if(dados != null && dados.getNome_bebida()!= null && !dados.getNome_bebida().equalsIgnoreCase("")){
                if(!filtro.equalsIgnoreCase("")){
                    filtro += " AND nome_bebida = "+dados.getNome_bebida();
                }else{
                    filtro = "WHERE nome_bebida ilike '%"+dados.getNome_bebida()+"%'";
                }
            }
            if(dados != null && dados.getPreco_bebida()!= null && !dados.getPreco_bebida().equalsIgnoreCase("")){
                if(!filtro.equalsIgnoreCase("")){
                    filtro += " AND preco_bebida = "+dados.getPreco_bebida();
                }else{
                    filtro = "WHERE preco_bebida ilike '%"+dados.getPreco_bebida()+"%'";
                }
            }
            
            PreparedStatement ps = c.prepareStatement(SQL + filtro);
            ResultSet resultado = ps.executeQuery();
            if(resultado.next()){
                Bebida atual = new Bebida();
                atual = this.pegaDados(resultado);
                return atual;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BebidasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

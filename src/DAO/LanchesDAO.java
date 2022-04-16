/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.Conexao;
import Model.Lanche;
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
public class LanchesDAO {
    public boolean inserirLanche(Lanche L){
        try {
            String SQL="INSERT INTO nicolas_teles_lanchonete.lanches(nome_lanche, codigo_lanche, preco_lanche, descricao_lanche) VALUES (?,?,?,?)";
            Connection minhaConexao = Conexao.getConexao();
            PreparedStatement comando = minhaConexao.prepareStatement(SQL);
            comando.setString(1, L.getNome_lanche());
            comando.setInt(2, L.getCodigo_lanche());
            comando.setString(3, L.getPreco_lanche());
            comando.setString(4, L.getDesc_lanche());
            int retorno=comando.executeUpdate();
            if(retorno>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LanchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<Lanche> listarLanches(){
        try {
            String SQL ="SELECT * FROM nicolas_teles_lanchonete.lanches";
            List<Lanche> listaDeLanches = new ArrayList<Lanche>();
            Connection c = Conexao.getConexao();
            PreparedStatement ps = c.prepareStatement(SQL);
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Lanche atual = new Lanche();
                atual = this.pegaDados(resultado);
                listaDeLanches.add(atual);
            }
            return listaDeLanches;
        } catch (SQLException ex) {
            Logger.getLogger(LanchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private Lanche pegaDados(ResultSet resultado){
        try {
            Lanche atual = new Lanche();
            atual.setCodigo_lanche(resultado.getInt("codigo_lanche"));
            atual.setDesc_lanche(resultado.getString("descricao_lanche"));
            atual.setNome_lanche(resultado.getString("nome_lanche"));
            atual.setPreco_lanche(resultado.getString("preco_lanche"));
            return atual;
        } catch (SQLException ex) {
            Logger.getLogger(LanchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Lanche consulta(String codigo_lanche){
        try {
            String SQL="SELECT * FROM nicolas_teles_lanchonete.lanches WHERE codigo_lanche = ?";
            Connection c = Conexao.getConexao();
            PreparedStatement ps = c.prepareStatement(SQL);
            ps.setInt(1, Integer.valueOf(codigo_lanche));
            ResultSet resultado = ps.executeQuery();
            if(resultado.next()){
                Lanche atual = new Lanche();
                atual = this.pegaDados(resultado);
                return atual;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LanchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean atualizaDados(Lanche dados){
        try {
            String SQL = "UPDATE nicolas_teles_lanchonete.lanches SET nome_lanche=?, descricao_lanche=?, preco_lanche=? WHERE codigo_lanche=?";
            Connection c = Conexao.getConexao();
            PreparedStatement comando = c.prepareStatement(SQL);
            comando.setString(1, dados.getNome_lanche());
            comando.setString(2, dados.getDesc_lanche());
            comando.setString(3, dados.getPreco_lanche());
            comando.setInt(4, dados.getCodigo_lanche());
            int retorno = comando.executeUpdate();
            if(retorno>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LanchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Lanche consulta(Lanche dados){
        try {
            String SQL = "SELECT * FROM nicolas_teles_lanchonete.lanches ";
            String filtro = "";
            Connection c = Conexao.getConexao();
            
            if(dados != null && dados.getCodigo_lanche()>0){
                filtro = "WHERE codigo_lanche = "+dados.getCodigo_lanche();
            }
            if(dados != null && dados.getDesc_lanche() != null && !dados.getDesc_lanche().equalsIgnoreCase("")){
                if(!filtro.equalsIgnoreCase("")){
                    filtro += " AND descricao_lanche = "+dados.getDesc_lanche();
                }else{
                    filtro = "WHERE descricao_lanche ilike '%"+dados.getDesc_lanche()+"%'";
                }
            }
            if(dados != null && dados.getNome_lanche()!= null && !dados.getNome_lanche().equalsIgnoreCase("")){
                if(!filtro.equalsIgnoreCase("")){
                    filtro += " AND nome_lanche = "+dados.getNome_lanche();
                }else{
                    filtro = "WHERE nome_lanche ilike '%"+dados.getNome_lanche()+"%'";
                }
            }
            if(dados != null && dados.getPreco_lanche()!= null && !dados.getPreco_lanche().equalsIgnoreCase("")){
                if(!filtro.equalsIgnoreCase("")){
                    filtro += " AND preco_lanche = "+dados.getPreco_lanche();
                }else{
                    filtro = "WHERE preco_lanche ilike '%"+dados.getPreco_lanche()+"%'";
                }
            }
            
            PreparedStatement ps = c.prepareStatement(SQL + filtro);
            ResultSet resultado = ps.executeQuery();
            if(resultado.next()){
                Lanche atual = new Lanche();
                atual = this.pegaDados(resultado);
                return atual;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LanchesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

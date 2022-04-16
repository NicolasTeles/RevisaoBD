/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.Conexao;
import Model.Doce;
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
public class DocesDAO {
    public boolean inserirDoce(Doce d){
        
        try {
            String SQL="INSERT INTO nicolas_teles_lanchonete.doces(nome_doce, codigo_doce, preco_doce, descricao_doce) VALUES (?,?,?,?)";
            Connection minhaConexao = Conexao.getConexao();
            PreparedStatement comando = minhaConexao.prepareStatement(SQL);
            comando.setString(1, d.getNome_doce());
            comando.setInt(2, d.getCodigo_doce());
            comando.setString(3, d.getPreco_doce());
            comando.setString(4, d.getDesc_doce());
            int retorno=comando.executeUpdate();
            if(retorno>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<Doce> listarDoces(){
        
        try {
            String SQL ="SELECT * FROM nicolas_teles_lanchonete.doces";
            List<Doce> listaDeDoces = new ArrayList<Doce>();
            Connection c = Conexao.getConexao();
            PreparedStatement ps = c.prepareStatement(SQL);
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Doce atual = new Doce();
                atual = this.pegaDados(resultado);
                listaDeDoces.add(atual);
            }
            return listaDeDoces;
        } catch (SQLException ex) {
            Logger.getLogger(DocesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private Doce pegaDados(ResultSet resultado){
        
        try {
            Doce atual = new Doce();
            atual.setCodigo_doce(resultado.getInt("codigo_doce"));
            atual.setDesc_doce(resultado.getString("descricao_doce"));
            atual.setNome_doce(resultado.getString("nome_doce"));
            atual.setPreco_doce(resultado.getString("preco_doce"));
            return atual;
        } catch (SQLException ex) {
            Logger.getLogger(DocesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Doce consulta(String codigo_doce){
        
        try {
            String SQL="SELECT * FROM nicolas_teles_lanchonete.doces WHERE codigo_doce = ?";
            Connection c = Conexao.getConexao();
            PreparedStatement ps = c.prepareStatement(SQL);
            ps.setInt(1, Integer.valueOf(codigo_doce));
            ResultSet resultado = ps.executeQuery();
            if(resultado.next()){
                Doce atual = new Doce();
                atual = this.pegaDados(resultado);
                return atual;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean atualizaDados(Doce dados){
        
        try {
            String SQL = "UPDATE nicolas_teles_lanchonete.doces SET nome_doce=?, descricao_doce=?, preco_doce=? WHERE codigo_doce=?";
            Connection c = Conexao.getConexao();
            PreparedStatement comando = c.prepareStatement(SQL);
            comando.setString(1, dados.getNome_doce());
            comando.setString(2, dados.getDesc_doce());
            comando.setString(3, dados.getPreco_doce());
            comando.setInt(4, dados.getCodigo_doce());
            int retorno = comando.executeUpdate();
            if(retorno>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Doce consulta(Doce dados){
        
        try {
            String SQL = "SELECT * FROM nicolas_teles_lanchonete.doces ";
            String filtro = "";
            Connection c = Conexao.getConexao();
            
            if(dados != null && dados.getCodigo_doce()>0){
                filtro = "WHERE codigo_doce = "+dados.getCodigo_doce();
            }
            if(dados != null && dados.getDesc_doce()!= null && !dados.getDesc_doce().equalsIgnoreCase("")){
                if(!filtro.equalsIgnoreCase("")){
                    filtro += " AND descricao_doce = "+dados.getDesc_doce();
                }else{
                    filtro = "WHERE descricao_doce ilike '%"+dados.getDesc_doce()+"%'";
                }
            }
            if(dados != null && dados.getNome_doce()!= null && !dados.getNome_doce().equalsIgnoreCase("")){
                if(!filtro.equalsIgnoreCase("")){
                    filtro += " AND nome_doce = "+dados.getNome_doce();
                }else{
                    filtro = "WHERE nome_doce ilike '%"+dados.getNome_doce()+"%'";
                }
            }
            if(dados != null && dados.getPreco_doce()!= null && !dados.getPreco_doce().equalsIgnoreCase("")){
                if(!filtro.equalsIgnoreCase("")){
                    filtro += " AND preco_doce = "+dados.getPreco_doce();
                }else{
                    filtro = "WHERE preco_doce ilike '%"+dados.getPreco_doce()+"%'";
                }
            }
            
            PreparedStatement ps = c.prepareStatement(SQL + filtro);
            ResultSet resultado = ps.executeQuery();
            if(resultado.next()){
                Doce atual = new Doce();
                atual = this.pegaDados(resultado);
                return atual;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author nicol
 */
public class Doce {
    private int codigo_doce;
    private String nome_doce;
    private String preco_doce;
    private String desc_doce;
    
    public Doce(){
        this.codigo_doce=0;
        this.desc_doce="";
        this.nome_doce="";
        this.preco_doce="";
    }

    public Doce(int codigo_doce, String nome_doce, String preco_doce, String desc_doce) {
        this.codigo_doce = codigo_doce;
        this.nome_doce = nome_doce;
        this.preco_doce = preco_doce;
        this.desc_doce = desc_doce;
    }

    public int getCodigo_doce() {
        return codigo_doce;
    }

    public void setCodigo_doce(int codigo_doce) {
        this.codigo_doce = codigo_doce;
    }

    public String getNome_doce() {
        return nome_doce;
    }

    public void setNome_doce(String nome_doce) {
        this.nome_doce = nome_doce;
    }

    public String getPreco_doce() {
        return preco_doce;
    }

    public void setPreco_doce(String preco_doce) {
        this.preco_doce = preco_doce;
    }

    public String getDesc_doce() {
        return desc_doce;
    }

    public void setDesc_doce(String desc_doce) {
        this.desc_doce = desc_doce;
    }

    @Override
    public String toString() {
        return codigo_doce + " | " + nome_doce + " | " + preco_doce + " | " + desc_doce;
    }    
}

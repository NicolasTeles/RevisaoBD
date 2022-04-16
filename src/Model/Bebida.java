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
public class Bebida {
    private int codigo_bebida;
    private String nome_bebida;
    private String preco_bebida;
    private String desc_bebida;

    public Bebida() {
        this.codigo_bebida=0;
        this.desc_bebida="";
        this.nome_bebida="";
        this.preco_bebida="";
    }

    public Bebida(int codigo_bebida, String nome_bebida, String preco_bebida, String desc_bebida) {
        this.codigo_bebida = codigo_bebida;
        this.nome_bebida = nome_bebida;
        this.preco_bebida = preco_bebida;
        this.desc_bebida = desc_bebida;
    }

    public int getCodigo_bebida() {
        return codigo_bebida;
    }

    public void setCodigo_bebida(int codigo_bebida) {
        this.codigo_bebida = codigo_bebida;
    }

    public String getNome_bebida() {
        return nome_bebida;
    }

    public void setNome_bebida(String nome_bebida) {
        this.nome_bebida = nome_bebida;
    }

    public String getPreco_bebida() {
        return preco_bebida;
    }

    public void setPreco_bebida(String preco_bebida) {
        this.preco_bebida = preco_bebida;
    }

    public String getDesc_bebida() {
        return desc_bebida;
    }

    public void setDesc_bebida(String desc_bebida) {
        this.desc_bebida = desc_bebida;
    }

    @Override
    public String toString() {
        return codigo_bebida + " | " + nome_bebida + " | " + preco_bebida + " | " + desc_bebida;
    }
}

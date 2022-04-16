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
public class Lanche {
    private int codigo_lanche;
    private String nome_lanche;
    private String preco_lanche;
    private String desc_lanche;

    public Lanche() {
        this.codigo_lanche=0;
        this.desc_lanche="";
        this.nome_lanche="";
        this.preco_lanche="";
    }

    public Lanche(int codigo_lanche, String nome_lanche, String preco_lanche, String desc_lanche) {
        this.codigo_lanche = codigo_lanche;
        this.nome_lanche = nome_lanche;
        this.preco_lanche = preco_lanche;
        this.desc_lanche = desc_lanche;
    }

    public int getCodigo_lanche() {
        return codigo_lanche;
    }

    public void setCodigo_lanche(int codigo_lanche) {
        this.codigo_lanche = codigo_lanche;
    }

    public String getNome_lanche() {
        return nome_lanche;
    }

    public void setNome_lanche(String nome_lanche) {
        this.nome_lanche = nome_lanche;
    }

    public String getPreco_lanche() {
        return preco_lanche;
    }

    public void setPreco_lanche(String preco_lanche) {
        this.preco_lanche = preco_lanche;
    }

    public String getDesc_lanche() {
        return desc_lanche;
    }

    public void setDesc_lanche(String desc_lanche) {
        this.desc_lanche = desc_lanche;
    }

    @Override
    public String toString() {
        return codigo_lanche + " | " + nome_lanche + " | " + preco_lanche + " | " + desc_lanche;
    }
}

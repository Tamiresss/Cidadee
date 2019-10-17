/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.DaoCidadee;
import javax.swing.JOptionPane;
import modelo.Cidadee;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import tela.manutencao.ManutencaoCidadee;

/**
 *
 * @author Administrador
 */
public class ControladorCidadee {

    public static void inserir(ManutencaoCidadee man){
        Cidadee objeto = new Cidadee();
        objeto.setNome(man.jtfNome.getText());
        objeto.setSigla(man.jtfSigla.getText());
        objeto.setNrhabitantes(Integer.parseInt(man.jtfNrhabitantes.getText()));
        objeto.setDataeman(LocalDate.parse(man.jtfDataeman.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setAreatotal(Double.parseDouble(man.jtfAreatotal.getText()));
        objeto.setDistancia(Double.parseDouble(man.jtfDistancia.getText()));
        
        boolean resultado = DaoCidadee.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
            
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

    public static void alterar(ManutencaoCidadee man){
        Cidadee objeto = new Cidadee();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setNome(man.jtfNome.getText());
        objeto.setSigla(man.jtfSigla.getText());
        objeto.setNrhabitantes(Integer.parseInt(man.jtfNrhabitantes.getText()));
        objeto.setDataeman(LocalDate.parse(man.jtfDataeman.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setAreatotal(Double.parseDouble(man.jtfAreatotal.getText()));
        objeto.setDistancia(Double.parseDouble(man.jtfDistancia.getText()));
        
        boolean resultado = DaoCidadee.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public static void excluir(ManutencaoCidadee man){
        Cidadee objeto = new Cidadee();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoCidadee.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("Sigla");
        modelo.addColumn("Número de habitantes");
        modelo.addColumn("Data de emancipação");
        modelo.addColumn("Área total");
        modelo.addColumn("Distância");
        List<Cidadee> resultados = DaoCidadee.consultar();
        for (Cidadee objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome());
            linha.add(objeto.getSigla());
            linha.add(objeto.getNrhabitantes());
            linha.add(objeto.getDataeman().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getAreatotal());
            linha.add(objeto.getDistancia());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
    public static void atualizaCampos(ManutencaoCidadee man, int pk){ 
        Cidadee objeto = DaoCidadee.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfNome.setText(objeto.getNome());
        man.jtfSigla.setText(objeto.getSigla());
        man.jtfNrhabitantes.setText(objeto.getNrhabitantes().toString());
        man.jtfDataeman.setText(objeto.getDataeman().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfAreatotal.setText(objeto.getAreatotal().toString());
        man.jtfDistancia.setText(objeto.getDistancia().toString());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}

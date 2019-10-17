/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cidadee;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class DaoCidadee {
    public static boolean inserir(Cidadee objeto) {
        String sql = "INSERT INTO cidadee (nome, sigla, nrhabitantes, dataeman, areatotal, distancia) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getSigla());
            ps.setInt(3, objeto.getNrhabitantes());
            ps.setDate(4, Date.valueOf(objeto.getDataeman()));
            ps.setDouble(5, objeto.getAreatotal());
            ps.setDouble(6, objeto.getDistancia());
           
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static void main(String[] args) {
        Cidadee objeto = new Cidadee();
        objeto.setNome("Ibirubá");
        objeto.setSigla("IB");
        objeto.setNrhabitantes(100);
        objeto.setDataeman(LocalDate.parse("11/01/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setAreatotal(1.50);
        objeto.setDistancia(1.50);
        
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    public static boolean alterar(Cidadee objeto) {
        String sql = "UPDATE cidadee SET nome = ?, sigla = ?, nrhabitantes = ?, dataeman = ?, areatotal = ?, distancia = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getSigla());
            ps.setInt(3, objeto.getNrhabitantes());
            ps.setDate(4, Date.valueOf(objeto.getDataeman()));
            ps.setDouble(5, objeto.getAreatotal());
            ps.setDouble(6, objeto.getDistancia());
            ps.setInt(7, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static boolean excluir(Cidadee objeto) {
        String sql = "DELETE FROM cidadee WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static List<Cidadee> consultar() {
        List<Cidadee> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome, sigla, nrhabitantes, dataeman, areatotal, distancia FROM cidadee";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cidadee objeto = new Cidadee();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome(rs.getString("nome"));
                objeto.setSigla(rs.getString("sigla"));
                objeto.setNrhabitantes(rs.getInt("nrhabitantes"));
                objeto.setDataeman(rs.getDate("dataeman").toLocalDate());
                objeto.setAreatotal(rs.getDouble("areatotal"));
                objeto.setDistancia(rs.getDouble("distancia"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
    public static Cidadee consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome, sigla, nrhabitantes, dataeman, areatotal, distancia FROM cidadee WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cidadee objeto = new Cidadee();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome(rs.getString("nome"));
                objeto.setSigla(rs.getString("sigla"));
                objeto.setNrhabitantes(rs.getInt("nrhabitantes"));
                objeto.setDataeman(rs.getDate("dataeman").toLocalDate());
                objeto.setAreatotal(rs.getDouble("areatotal"));
                objeto.setDistancia(rs.getDouble("distancia"));
                
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}

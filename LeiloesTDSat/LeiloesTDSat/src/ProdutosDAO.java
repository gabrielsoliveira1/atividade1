/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */


import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {
    private Connection conn;
    private PreparedStatement prep;
    private ResultSet resultset;
    private ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    private conectaDAO conexao; 

    public boolean conectar() {
        conexao = new conectaDAO();
        conn = conexao.getConexao();
        return conn != null;
    }

    public int cadastrarProduto(ProdutosDTO produto) {
        int status;
        try {
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, produto.getNome());
            st.setLong(2, produto.getValor());
            st.setString(3, produto.getStatus());
            status = st.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir o produto: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public int venderProduto(ProdutosDTO produto) {
        int resposta;
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setLong(1, produto.getId());
            resposta = st.executeUpdate();
            return resposta;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>(); // instância local para evitar adicionar duplicados
        String sql = "SELECT * FROM produtos"; // removi o "uc_11." do nome da tabela
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet resultset = st.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getLong("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getLong("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
            return listagem;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>(); // instância local para evitar adicionar duplicados
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'"; // removi o "uc_11." do nome da tabela
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet resultset = st.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getLong("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getLong("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
            return listagem;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
    }

    public void desconectar() {
        if (this.conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.print("Erro ao desconectar: " + ex.getMessage());
            }
        }
    }

    List<ProdutosDTO> listarProdutosVendidos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

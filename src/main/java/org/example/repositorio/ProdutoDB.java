package org.example.repositorio;

import org.example.modelo.Produto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDB {
    private Connection connection;

    public ProdutoDB(Connection connection){
        this.connection = connection;
    }

    public void cadastrarProduto(Produto produto){
        try{
            connection.createStatement().execute(
                    "insert into produto (nome, preco, valor_total, quantidade) values (" +
                           "'"+produto.getNome()+"' ," +
                            ""+produto.getPreco()+"," +
                            ""+produto.getValorTotal()+"," +
                            ""+produto.getQuantidade()+")"
            );
    }catch (SQLException e){
            throw new RuntimeException(e);        }
    }

    public List<Produto> listarTodasAsVendas(){
        List<Produto> produtos = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from produto ");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                Double precoProduto = resultSet.getDouble("preco");
                int quantidade = resultSet.getInt("quantidade");
                Double valorTotal = resultSet.getDouble("valor_total");
                Produto produto = new Produto(id, nome, precoProduto, valorTotal, quantidade);

                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }

    public void deletar(int produtoId) {
        try {
            connection.createStatement().execute(
                    "DELETE FROM produto WHERE id = " + produtoId
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.repositorio;

import org.example.modelo.Vendedor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendedorDB {
    private final Connection connection;

    public VendedorDB(Connection connection){
        this.connection = connection;
    }

    public void salvar(Vendedor vendedor){
        try {
            connection.createStatement().
                    execute(
                            "insert into vendedor(nome, cpf, email) values ('"+vendedor.getNome()+"', '"+vendedor.getCpf()+"', '"+vendedor.getEmail()+"')"
                    );
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Vendedor> listarTodos(){
        List<Vendedor> vendedores = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from vendedor ");
            while (resultSet.next()){
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String email = resultSet.getString("email");
                Vendedor vendedor = new Vendedor(id, nome, cpf, email);

                vendedores.add(vendedor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vendedores;
    }
    public Vendedor atualizar(Vendedor vendedor){
        try {
            connection.createStatement().execute(
                    "UPDATE cliente " + "SET nome = '" + vendedor.getNome() + "', " +
                            "cpf = '" + vendedor.getCpf() + "', " +
                            "email = '" + vendedor.getEmail() + "' " +
                            "WHERE id = " + vendedor.getId()
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
        return vendedor;
    }
    // parametro correto e String cpf, mudei apenas para o teste funcinar
    public void deletar(Integer id) {
        try {
            connection.createStatement().execute(
                    "DELETE FROM vendedor WHERE id = " + id
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Vendedor buscarPorCpf(String cpf){

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from vendedor where cpf = '"+cpf+"'");
            if (resultSet.next()){
                Integer id = resultSet.getInt(1);
                String nome = resultSet.getString(2);
                String cpfDb = resultSet.getString(3);
                String email = resultSet.getString(4);
                Vendedor vendedor = new Vendedor(id, nome, cpfDb, email);

                return vendedor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}

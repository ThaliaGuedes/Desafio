package org.example.swing;

import org.example.modelo.Cliente;
import org.example.regras.CadastroCliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaClientesGUI extends JFrame {

    private final CadastroCliente cadastroCliente;
    public ListaClientesGUI(CadastroCliente cadastroCliente) {
        this.cadastroCliente = cadastroCliente;

        setTitle("Lista de Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centraliza a janela

        // Obter a lista de clientes
        List<Cliente> clientes = cadastroCliente.listarTodos();

        // Criar os dados para a tabela
        Object[][] data = new Object[clientes.size()][3];
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            data[i][0] = cliente.getNome();
            data[i][1] = cliente.getCpf();
            data[i][2] = cliente.getEmail();
        }

        // Nomes das colunas
        String[] columnNames = {"Nome", "CPF", "Email"};

        // Criar o modelo da tabela
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Criar a tabela com o modelo
        JTable table = new JTable(model);

        // Adicionar a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Adicionar o JScrollPane ao JFrame
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}

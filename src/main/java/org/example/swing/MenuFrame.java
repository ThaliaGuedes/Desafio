package org.example.swing;

import org.example.regras.CadastroCliente;
import org.example.repositorio.ClienteDB;
import org.example.repositorio.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame implements ActionListener {

    CadastroCliente cadastroCliente = new CadastroCliente(new ClienteDB(DBConnection.getConnection()));
    public MenuFrame() {
        setTitle("Menu do Banco de Dados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);

        String[] options = {
                "Adicionar Cliente", "Listar Todos os Clientes", "Atualizar Cliente", "Deletar Cliente",
                "Buscar Cliente por CPF", "Adicionar Vendedor", "Listar Todos os Vendedores", "Atualizar Vendedor",
                "Deletar Vendedor", "Buscar Vendedor por CPF", "Adicionar Produto", "Listar todos os produtos",
                "Deletar produto por Id", "Sair"
        };

        int yPos = 20;
        for (int i = 0; i < options.length; i++) {
            JButton button = new JButton(options[i]);
            button.setBounds(50, yPos, 300, 25);
            button.addActionListener(this);
            add(button);
            yPos += 30;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String choice = source.getText();

        switch (choice) {
            case "Adicionar Cliente" -> new CadastroClienteGUI(cadastroCliente);
            case "Listar Todos os Clientes" -> new ListaClientesGUI(cadastroCliente);
            case "Atualizar Cliente" -> {
                AtualizarClienteGUI clienteGUI =
                new AtualizarClienteGUI(cadastroCliente);
                clienteGUI.setVisible(true);
            }
            case "Deletar Cliente" -> {
                RemoverClienteGUI removerClienteGUI = new RemoverClienteGUI(cadastroCliente);
                removerClienteGUI.setVisible(true);
            }
        }
        if (choice.equals("Sair")) {
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, "Opção selecionada: " + choice);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuFrame menuFrame = new MenuFrame();
            menuFrame.setVisible(true);
        });
    }
}


package org.example.swing;

import org.example.modelo.Cliente;
import org.example.regras.CadastroCliente;
import org.example.repositorio.ClienteDB;
import org.example.repositorio.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroClienteGUI extends JFrame implements ActionListener {
    final static CadastroCliente cadastroCliente =
            new CadastroCliente(new ClienteDB(DBConnection.getConnection()));
    private JTextField txtNome, txtCpf, txtEmail;

    public CadastroClienteGUI(CadastroCliente cadastroCliente) {
        // Configurações básicas do JFrame
        setTitle("Cadastro de Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Usando layout nulo para posicionar manualmente os componentes

        // Crie rótulos para descrever os campos
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 50, 80, 25);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(20, 80, 80, 25);

        // Adicione os rótulos ao JFrame
        add(lblNome);
        add(lblCpf);
        add(lblEmail);

        // Crie campos de texto para os dados
        txtNome = new JTextField();
        txtNome.setBounds(100, 20, 200, 25);

        txtCpf = new JTextField();
        txtCpf.setBounds(100, 50, 200, 25);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 80, 200, 25);

        // adiciona os campos de texto ao JFrame
        add(txtNome);
        add(txtCpf);
        add(txtEmail);

        //botão para salvar o cadastro
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(150, 120, 80, 25);
        btnSalvar.addActionListener(this);

        // Adicione o botão ao JFrame
        add(btnSalvar);
        btnSalvar.addActionListener(this);

        // Configure o tamanho e a visibilidade do JFrame
        setSize(350, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String nomeCliente = txtNome.getText();
            String cpfCliente = txtCpf.getText();
            String emailCliente = txtEmail.getText();

            Cliente cliente = new Cliente(nomeCliente, cpfCliente, emailCliente);
            cadastroCliente.adicionar(cliente);
            System.out.println("Cliente adicionado com sucesso: " + cliente);
        } catch (Exception ex) {
            ex.printStackTrace(); // Isso imprime o rastreamento da pilha da exceção
            JOptionPane.showMessageDialog(this, "Erro ao adicionar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}

package org.example.swing;

import org.example.modelo.Cliente;
import org.example.regras.CadastroCliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtualizarClienteGUI extends JFrame implements ActionListener {
    private static CadastroCliente cadastroCliente;
    private JTextField txtNome, txtCpf, txtEmail;

    public AtualizarClienteGUI(CadastroCliente cadastroCliente) {
        this.cadastroCliente = cadastroCliente;

        setTitle("Atualizar Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);

        JLabel lblNome = new JLabel("Novo Nome:");
        lblNome.setBounds(20, 20, 100, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(150, 20, 200, 25);
        add(txtNome);

        JLabel lblCpf = new JLabel("Novo CPF:");
        lblCpf.setBounds(20, 60, 100, 25);
        add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setBounds(150, 60, 200, 25);
        add(txtCpf);

        JLabel lblEmail = new JLabel("Novo E-mail:");
        lblEmail.setBounds(20, 100, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 100, 200, 25);
        add(txtEmail);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(150, 150, 100, 30);
        btnAtualizar.addActionListener(this);
        add(btnAtualizar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String novoNome = txtNome.getText();
        String novoCpf = txtCpf.getText();
        String novoEmail = txtEmail.getText();

        Cliente clienteNovo = new Cliente(novoNome, novoCpf, novoEmail);

        try {
            cadastroCliente.atualizar(clienteNovo);
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
            dispose(); // Fecha a janela após a atualização
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Crie uma instância do CadastroCliente
        // Suponha que você já tenha criado a instância de CadastroCliente e a tenha chamado de cadastroCliente
        CadastroClienteGUI cadastroClienteGUI = new CadastroClienteGUI(cadastroCliente);

        // Agora você tem uma janela para atualizar o cliente
        AtualizarClienteGUI atualizarClienteGUI = new AtualizarClienteGUI(cadastroCliente);
        atualizarClienteGUI.setVisible(true); // Exibe a janela para atualizar o cliente
    }
}

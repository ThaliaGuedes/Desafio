package org.example.swing;

import org.example.regras.CadastroCliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoverClienteGUI extends JFrame implements ActionListener {
    private static CadastroCliente cadastroCliente;
    private JTextField txtCpf;

    public RemoverClienteGUI(CadastroCliente cadastroCliente) {
        this.cadastroCliente = cadastroCliente;

        setTitle("Remover Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLayout(null);

        JLabel lblCpf = new JLabel("CPF para Remover:");
        lblCpf.setBounds(20, 20, 150, 25);
        add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setBounds(180, 20, 200, 25);
        add(txtCpf);

        JButton btnRemover = new JButton("Remover");
        btnRemover.setBounds(150, 70, 100, 30);
        btnRemover.addActionListener(this);
        add(btnRemover);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cpfRemover = txtCpf.getText();

        try {
            cadastroCliente.remover(cpfRemover);
            JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
            dispose(); // Fecha a janela após a remoção
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao remover cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        // Suponha que você já tenha criado a instância de CadastroCliente e a tenha chamado de cadastroCliente
        RemoverClienteGUI removerClienteGUI = new RemoverClienteGUI(cadastroCliente);
        removerClienteGUI.setVisible(true); // Exibe a janela para remover o cliente
    }
}

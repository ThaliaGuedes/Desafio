package regras;

import org.example.modelo.Cliente;
import org.example.regras.CadastroCliente;
import org.example.repositorio.ClienteDB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CadastroClienteTest {
    private ClienteDB clienteDB;
    private CadastroCliente cadastroCliente;
    @Before
    public void setup(){
        clienteDB = mock(ClienteDB.class);
        cadastroCliente = new CadastroCliente(clienteDB);
    }
    @Test
    public void validarClienteComEmailRepetido(){
        //criar cliente
        Cliente cliente1 = new Cliente("Joao", "Joao@.gmail", "1345");
        //comportamentos
        when(clienteDB.listarTodos()).thenReturn(List.of(cliente1));
        when(clienteDB.buscarPorCpf(Mockito.anyString())).thenReturn(null);
        //validacao
        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class,()-> cadastroCliente.adicionar(cliente1));

        assertEquals("Email ja cadastrado", illegalArgumentException.getMessage());
    }
    @Test
    public void validarClienteComCpfRepetido(){
        Cliente cliente1 = new Cliente("Ana", "ana@gmail.com", "12345");

        when(clienteDB.buscarPorCpf("12345")).thenReturn(cliente1);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> cadastroCliente.adicionar(cliente1));

        assertEquals("CPF ja existente", illegalArgumentException.getMessage());
    }

    @Test
    public void testarvalidarCPFIncorreto(){

        String cpfvalido = "12312";
        cadastroCliente.buscarPorcpf(cpfvalido);
        Mockito.verify(clienteDB, times(1)).buscarPorCpf(Mockito.anyString());
    }
    @Test
    public void testeBuscarPorCPFCorreto(){
        String cpf = "12343212345";

        ClienteDB clienteDB = mock(ClienteDB.class);
        Mockito.when(clienteDB.buscarPorCpf(cpf)). thenReturn(new Cliente("thalia", "thalia@.", cpf));
        CadastroCliente cadastroCliente1 = new CadastroCliente(clienteDB);
        Cliente cliente1 = cadastroCliente1.buscarPorcpf(cpf);

        Assert.assertEquals("thalia", cliente1.getNome());
        Assert.assertEquals(cpf, cliente1.getCpf());
        Assert.assertEquals("thalia@.", cliente1.getEmail());

    }
    @Test
    public void testeAdicionarCliente(){
        Cliente cliente = new Cliente("Thalia", "thalia@gmail.com", "12345678901");
        Mockito.when(clienteDB.buscarPorCpf(cliente.getCpf())).thenReturn(cliente);
        when(clienteDB.listarTodos()).thenReturn(new ArrayList<>());

    }

    @Test
    public void testeRemoverVendedoresExistente(){
        String cpf = "123456";
        Cliente clienteExistente = new Cliente("ana", "ana@gmail.com",cpf);
        when(clienteDB.buscarPorCpf(cpf)).thenReturn(clienteExistente);
        Cliente result = cadastroCliente.remover(cpf);

        verify(clienteDB).deletar(clienteExistente.getId());
        assertEquals(clienteExistente, result);
    }
    @Test
    public void testRemoverVendedorInexistente() {
        String cpf = "123456789";

        when(clienteDB.buscarPorCpf(cpf)).thenReturn(null);

        Cliente resultado = cadastroCliente.remover(cpf);

        verify(clienteDB, never()).deletar(anyInt());

        assertNull(resultado);
    }

    @Test
    public void testBuscarPorCpfVendedorExistente() {
        String cpf = "123456789";
        Cliente clienteExistente = new Cliente("Ana", "ana@gmail.com", cpf);

        when(clienteDB.buscarPorCpf(cpf)).thenReturn(clienteExistente);

        Cliente resultado = cadastroCliente.buscarPorcpf(cpf);

        verify(clienteDB, times(2)).buscarPorCpf(cpf);

        assertEquals(clienteExistente, resultado);
    }
    @Test
    public void testAtualizarVendedor() {
        //criando objeto cliente
        Cliente cliente = new Cliente("Ana", "ana@gmail.com", "123456789");

        //mock para atualizar
        when(clienteDB.atualizar(cliente)).thenReturn(cliente);

        //chamando o metodo da classe vendedor para atualizar
        Cliente resultado = cadastroCliente.atualizar(cliente);

        //verificando se o metodo foi chamado 1 vez
        verify(clienteDB, times(1)).atualizar(cliente);

        //validacao
        assertEquals(cliente, resultado);
    }


}
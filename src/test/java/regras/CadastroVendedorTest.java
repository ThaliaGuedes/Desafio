package regras;

import org.example.modelo.Vendedor;
import org.example.regras.CadastroVendedor;
import org.example.repositorio.VendedorDB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CadastroVendedorTest {

    private VendedorDB vendedorDB;
    private CadastroVendedor cadastroVendedor;
    @Before
    public void setup(){
        vendedorDB = mock(VendedorDB.class);
        cadastroVendedor = new CadastroVendedor(vendedorDB);
    }
    @Test
    public void validarVendedorComCpfRepetido(){
        Vendedor vendedor = new Vendedor("matheus", "matheus@.gmail", "2345");

        when(vendedorDB.listarTodos()).thenReturn(List.of(vendedor));
        when(vendedorDB.buscarPorCpf(Mockito.anyString())).thenReturn(null);
        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class,()-> cadastroVendedor.adicionar(vendedor));

        assertEquals("Email ja cadastrado", illegalArgumentException.getMessage());
    }
    @Test
    public void validarVendedorComCPFRepetido(){
        Vendedor vendedor = new Vendedor("Ana", "ana@gmail.com", "12345");

        when(vendedorDB.buscarPorCpf("12345")).thenReturn(vendedor);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> cadastroVendedor.adicionar(vendedor));

        assertEquals("CPF ja existente", illegalArgumentException.getMessage());
    }
    @Test
    public void testarvalidarCPFIncorreto(){
        String cpfvalido = "12312";
        cadastroVendedor.buscarPorcpf(cpfvalido);
        Mockito.verify(vendedorDB, times(1)).buscarPorCpf(Mockito.anyString());
    }
    @Test
    public void testeBuscarPorCPFCorreto(){
        String cpf = "12343212345";

        VendedorDB vendedordb = mock(VendedorDB.class);
        Mockito.when(vendedordb.buscarPorCpf(cpf)). thenReturn(new Vendedor("thalia", "thalia@.", cpf));
        CadastroVendedor cadastroVendedor = new CadastroVendedor(vendedordb);
        Vendedor vendedor = cadastroVendedor.buscarPorcpf(cpf);

        Assert.assertEquals("thalia", vendedor.getNome());
        Assert.assertEquals(cpf, vendedor.getCpf());
        Assert.assertEquals("thalia@.", vendedor.getEmail());

    }
    @Test
    public void testeAdicionarVendedor(){
        Vendedor vendedor = new Vendedor("Thalia", "thalia@gmail.com", "12345678901");
        Mockito.when(vendedorDB.buscarPorCpf(vendedor.getCpf())).thenReturn(vendedor);
        when(vendedorDB.listarTodos()).thenReturn(new ArrayList<>());

    }
    @Test
    public void testeRemoverVendedoresExistente(){
        String cpf = "123456";
        Vendedor vendedorExistente = new Vendedor("ana", "ana@gmail.com",cpf);
        when(vendedorDB.buscarPorCpf(cpf)).thenReturn(vendedorExistente);
        Vendedor result = cadastroVendedor.remover(cpf);

        verify(vendedorDB).deletar(vendedorExistente.getId());
        assertEquals(vendedorExistente, result);
    }
    @Test
    public void testRemoverVendedorInexistente() {
        String cpf = "123456789";

        when(vendedorDB.buscarPorCpf(cpf)).thenReturn(null);

        Vendedor resultado = cadastroVendedor.remover(cpf);

        verify(vendedorDB, never()).deletar(anyInt());

        assertNull(resultado);
    }

    @Test
    public void testBuscarPorCpfVendedorExistente() {
        String cpf = "123456789";
        Vendedor vendedorExistente = new Vendedor("Ana", "ana@gmail.com", cpf);

        when(vendedorDB.buscarPorCpf(cpf)).thenReturn(vendedorExistente);

        Vendedor resultado = cadastroVendedor.buscarPorcpf(cpf);

        verify(vendedorDB, times(2)).buscarPorCpf(cpf);

        assertEquals(vendedorExistente, resultado);
    }
    @Test
    public void testAtualizarVendedor() {
        //criando objeto vendedor
        Vendedor vendedor = new Vendedor("Ana", "ana@gmail.com", "123456789");

        //mock para atualizar
        when(vendedorDB.atualizar(vendedor)).thenReturn(vendedor);

        //chamando o metodo da classe vendedor para atualizar
        Vendedor resultado = cadastroVendedor.atualizar(vendedor);

        //verificando se o metodo foi chamado 1 vez
        verify(vendedorDB, times(1)).atualizar(vendedor);

        //validacao
        assertEquals(vendedor, resultado);
    }

}
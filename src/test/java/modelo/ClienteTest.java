package modelo;

import org.example.modelo.Cliente;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ClienteTest {
    @Test
    public void validarClienteComEmailCorreto(){
        Cliente cliente = new Cliente("Joao", "joao@.", "123");

        try {
            cliente.validarEmail();
        }catch (Exception e){
            Assert.fail("Essa funcao deve lançar uma exceçao");
        }

    }
    @Test
    public void validarClienteComEmailErrado(){
        Cliente cliente = new Cliente("joao", "joao","123" );

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () ->
                cliente.validarEmail());

        assertEquals("Email sem @.", illegalArgumentException.getMessage());
    }

}

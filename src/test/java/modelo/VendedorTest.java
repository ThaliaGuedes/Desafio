package modelo;

import org.example.modelo.Vendedor;
import org.example.repositorio.VendedorDB;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class VendedorTest {
    @Mock
    private VendedorDB vendedorDB;
    @Test
    public void setUp(){
        vendedorDB = mock(VendedorDB.class);
    }
    @Test
    public void validarVendedorComEmailCorreto() {
        Vendedor vendedor = new Vendedor("Joao", "joao@gmail.com", "123456");
        try {
            vendedor.validarEmail();
        } catch (Exception e) {
            Assert.fail("Essa funcao deve lançar uma exceçao");
        }

    }

    @Test
    public void validarVendedorComEmailErrado() {
        Vendedor vendedor = new Vendedor("Maira", "maria.", "123");

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () ->
                vendedor.validarEmail());

        assertEquals("Email sem @.", illegalArgumentException.getMessage());
    }

}


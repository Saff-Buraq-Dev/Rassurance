package dollar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DollarTest {


    Dollar dollar;
    Dollar dollarDeux;

    @BeforeEach
    void setUp() {
        dollar = new Dollar("155,55$");
        dollarDeux = new Dollar("200.20$");
    }

    @AfterEach
    void tearDown() {
        dollar = null;
        dollarDeux= null;
    }

    @Test
    void additionnerDollar() {
        assertEquals("170.70$",
                dollar.additionner(new Dollar("15.15$")).toString());
    }


    @Test
    void soustraireDollar() {
        assertEquals("140.40$",
                dollar.soustraire(new Dollar("15.15$")).toString());
    }

    @Test
    void comparerDollars() {
        assertEquals(1,dollarDeux.compareTo(dollar));
    }

    @Test
    void testToString() {
        assertEquals("155.55$",dollar.toString());
    }

    @Test
    void testEqualsFalse() {
        assertFalse(dollar.equals(dollarDeux));
    }

    @Test
    void testEqualsTrue() {
        assertTrue(dollar.equals(new Dollar("155.55$")));
    }
    @Test
    void pourcentageUn() {
        assertEquals("38.89$", dollar.calculerPourcentage(25).toString());
    }

    @Test
    void pourcentageDeux() {
        assertEquals("84.08$",dollarDeux.calculerPourcentage(42).toString());
    }	
}
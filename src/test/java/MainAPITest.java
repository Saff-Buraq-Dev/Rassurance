import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainAPITest {
    String[] tab1;
    String[] tab2;
    String[] tab3;
    String[] tabS;
    String[] tabSR;

    @BeforeEach
    void setUp() {
        tab1 = new String[]{"toto"};
        tab2 = new String[]{"toto", "tata"};
        tab3 = new String[]{"toto", "tata", "titi"};
        tabS = new String[]{"-S"};
        tabSR = new String[]{"-SR"};
    }


    @Test
    void validerArguments0() {
        MockMain.validerArguments(new String[0]);
        assertEquals(MockMain.getResultat(), "argument = 0 ou > 2" );
    }

    @Test
    void validerArguments1() {
        MockMain.validerArguments(tab1);
        assertEquals(MockMain.getResultat(), "argument = 1" );
    }

    @Test
    void validerArguments2() {
        MockMain.validerArguments(tab2);
        assertEquals(MockMain.getResultat(), "argument = 2" );
    }

    @Test
    void validerArguments3() {
        MockMain.validerArguments(tab3);
        assertEquals(MockMain.getResultat(), "argument = 0 ou > 2" );
    }

    @Test
    void validerArgumentUniqueS() {
        MockMain.validerArgumentUnique(tabS);
        assertEquals(MockMain.getResultat(), "argument = -S" );
    }

    @Test
    void validerArgumentUniqueSR() {
        MockMain.validerArgumentUnique(tabSR);
        assertEquals(MockMain.getResultat(), "argument = -SR" );
    }

    @Test
    void validerArgumentUniqueInvalide() {
        MockMain.validerArgumentUnique(tab1);
        assertEquals(MockMain.getResultat(), "argument est invalide" );
    }


}
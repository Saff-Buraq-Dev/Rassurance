package contrat;

import client.Client;
import client.Reclamation;
import dollar.Dollar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoinTest {
    @Test
    @DisplayName("Accepte tous les numéros de soins dentaires valides [300...399]")
    void validerSoinsDentaires() {
        int soin300 = Soin.validerSoinsDentaires(300);
        assertEquals(300, soin300);

        int soin350 = Soin.validerSoinsDentaires(350);
        assertEquals(300, soin350);

        int soin399 = Soin.validerSoinsDentaires(399);
        assertEquals(300, soin399);
    }
}

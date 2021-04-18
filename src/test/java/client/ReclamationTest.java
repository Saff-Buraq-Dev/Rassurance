package client;

import dollar.Dollar;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * On assume que les valeurs de cette classe ont été validées avant d'être utilisées.
 */

class ReclamationTest {
    private Reclamation reclamation;

    @Test
    void testConstructeur() {
        reclamation = new Reclamation(100, "2020-12-01", "12,12$");
        assertEquals(100, reclamation.getSoin());
        assertEquals("2020-12-01", reclamation.getDateString());
        assertEquals(new Dollar("12,12$"), reclamation.getMontantReclamation());
        assertEquals("12.12$", reclamation.getMontantReclamationString());
    }

    @Test
    void convertirReclamationToJSONObjectTest() {
        reclamation = new Reclamation(100, "2020-12-01", "12,12$");
        reclamation.setMontantRemboursement("22.22$");
        JSONObject objet = reclamation.convertirReclamationToJSONObject();
        assertEquals(100, objet.get("soin"));
        assertEquals("2020-12-01", objet.get("date"));
        assertEquals("22.22$", objet.get("montant"));
    }

    @Test
    void testToString() {
        reclamation = new Reclamation(100, "2020-12-01", "12,12$");
        assertEquals("Soin : 100 (2020-12-01) - Réclamation : 12.12$ - Remboursement : 0.00$",
                reclamation.toString());
    }

}

package client;

import dollar.Dollar;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import validation.DonneeInvalideException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * On assume que les valeurs de cette classe ont été validées avant d'être utilisées.
 */

class ClientTest {
    private static Client clientValide;
    private static JSONObject clientJsonValide;
    private static JSONObject clientJsonNull;
    private static JSONObject clientJsonReclamationSansSoin;

    @BeforeAll
    static void setUp() throws DonneeInvalideException {
        // listeReclamation
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("soin", 100);
        jsonObject1.put("date", "2020-12-01");
        jsonObject1.put("montant", "12,12$");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("soin", 200);
        jsonObject2.put("date", "2020-12-02");
        jsonObject2.put("montant", "25.00$");
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("soin", 100);
        jsonObject3.put("date", "2020-12-03");
        jsonObject3.put("montant", "154.47$");
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("soin", 400);
        jsonObject4.put("date", "2020-12-03");
        jsonObject4.put("montant", "55.55$");
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        jsonArray.add(jsonObject3);
        jsonArray.add(jsonObject4);

        // clientValide
        clientJsonValide = new JSONObject();
        clientJsonValide.put("dossier", "C987654");
        clientJsonValide.put("mois", "2020-12");
        clientJsonValide.put("reclamations", jsonArray);
        clientValide = new Client(clientJsonValide);

        clientValide.getListeReclamation().get(0).setMontantRemboursement("12.46$");
        clientValide.getListeReclamation().get(1).setMontantRemboursement("1.87$");
        clientValide.getListeReclamation().get(2).setMontantRemboursement("326.89$");

        // pour le test client null;
        clientJsonNull = new JSONObject();

        // pour le test pas de soin
        JSONObject obj = new JSONObject();
        JSONArray tab = new JSONArray();
        clientJsonReclamationSansSoin = new JSONObject();
        clientJsonReclamationSansSoin.put("dossier","C987654");
        clientJsonReclamationSansSoin.put("mois","2021-01");
        obj.put("date","2021-01-13");
        obj.put("montant","200.00$");
        tab.add(obj);
        clientJsonReclamationSansSoin.put("reclamations",tab);
    }

    @Test
    void testConstructeur() {
        assertEquals(clientValide.getDossier(), "C987654");
        assertEquals(clientValide.getNoClient(), "987654");
        assertEquals(clientValide.getContrat(), "C");
        assertEquals(clientValide.getMois(), "2020-12");
        assertEquals(clientValide.getListeReclamation().toString(),
                "[Soin : 100 (2020-12-01) - Réclamation : 12.12$ - Remboursement : 12.46$, " +
                "Soin : 200 (2020-12-02) - Réclamation : 25.00$ - Remboursement : 1.87$, " +
                "Soin : 100 (2020-12-03) - Réclamation : 154.47$ - Remboursement : 326.89$, " +
                "Soin : 400 (2020-12-03) - Réclamation : 55.55$ - Remboursement : 0.00$]");
    }

    @Test
    void calculerTotalRemboursement() {
        assertEquals(new Dollar("341.22$"), clientValide.calculerTotalRemboursement());
    }

    @Test
    void calculerTotalRemboursementPourUnSoin() {
        assertEquals(new Dollar("339.35$"), clientValide.calculerTotalRemboursementPourUnSoin(100));
    }

    @Test
    void calculerNombreReclamationPourUnJour() {
        assertEquals(2, clientValide.calculerNombreReclamationPourUnJour("2020-12-03"));
    }

    @Test
    void serializerClient(){
        assertEquals(clientJsonValide.getString("dossier"),
                clientValide.convertirArrayListReclamationToJSONArray().getString("dossier"));
        assertEquals(clientJsonValide.getString("mois"),
                clientValide.convertirArrayListReclamationToJSONArray().getString("mois"));
    }

    @Test
    void testClientSansDossier(){
        String message = "La clé: \"dossier\" est introuvable.";
        Exception exception = assertThrows(DonneeInvalideException.class, () -> new Client(clientJsonNull));
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testClientReclamationSansSoin(){
        String message = "La clé: \"soin\" est introuvable.";
        Exception exception = assertThrows(DonneeInvalideException.class, () -> new Client(clientJsonReclamationSansSoin));
        assertEquals(message, exception.getMessage());
    }

}

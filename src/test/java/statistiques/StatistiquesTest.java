package statistiques;

import client.Reclamation;
import json.JSONElement;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StatistiquesTest {

    Statistiques statsZero;
    Statistiques statsAleatoire;
    Statistiques statsReclamation;
    JSONObject statsJsonAleatoire;
    ArrayList<Reclamation> listeReclamation;
    private final String[] LISTE_SOINS = {"massotherapie", "osteopathie", "kinesitherapie", "medecin generaliste prive",
            "psychologie individuelle", "soins dentaires", "naturopathie / acupuncture",
            "chiropractie", "physiotherapie", "orthophonie / ergotherapie"};

    @BeforeEach
    void setUp() {
        // Pour le constructeur sans parametres
        statsZero = new Statistiques();

        // Json object pour le constructeur avec parametres
        statsJsonAleatoire = new JSONObject();
        JSONObject nbSoins = new JSONObject();
        statsJsonAleatoire.put(JSONElement.NB_RECLAMATIONS_VALIDES.toString(), 3);
        statsJsonAleatoire.put(JSONElement.NB_DEMANDES_REJETEES.toString(), 0);
        for (int i = 0; i < LISTE_SOINS.length ; i++) {
            if(i == 0 || i == 5 || i == 9){
                nbSoins.put(LISTE_SOINS[i], 1);
            } else {
                nbSoins.put(LISTE_SOINS[i], 0);
            }
        }
        statsJsonAleatoire.put(JSONElement.NB_DECLARATIONS_SOINS.toString(), nbSoins);
        statsAleatoire = new Statistiques(statsJsonAleatoire);

        //Liste de réclamations pour la méthode ajouterReclamationsAcceptees
        listeReclamation = new ArrayList<>();
        listeReclamation.add(new Reclamation(0, "2021-01-12", "100.00"));
        listeReclamation.add(new Reclamation(334, "2021-01-13", "100.00"));
        listeReclamation.add(new Reclamation(700, "2021-01-14", "100.00"));

        statsReclamation = new Statistiques();
        statsReclamation.ajouterReclamartionsAcceptees(listeReclamation);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testConstructeurSansParam() {
        assertEquals(0, statsZero.getNbReclamationsAcceptees());
        assertEquals(0, statsZero.getNbReclamationsRejetees());
        for (int i = 0; i < LISTE_SOINS.length; i++) {
            assertEquals(0, statsZero.getNbDeclarationsSoins()[i]);
        }
    }

    @Test
    void testConstructeurAvecParam() {
        assertEquals(3, statsAleatoire.getNbReclamationsAcceptees());
        assertEquals(0, statsAleatoire.getNbReclamationsRejetees());
        assertEquals(1, statsAleatoire.getNbDeclarationsSoins()[0]);
        assertEquals(0, statsAleatoire.getNbDeclarationsSoins()[1]);
        assertEquals(0, statsAleatoire.getNbDeclarationsSoins()[2]);
        assertEquals(0, statsAleatoire.getNbDeclarationsSoins()[3]);
        assertEquals(0, statsAleatoire.getNbDeclarationsSoins()[4]);
        assertEquals(1, statsAleatoire.getNbDeclarationsSoins()[5]);
        assertEquals(0, statsAleatoire.getNbDeclarationsSoins()[6]);
        assertEquals(0, statsAleatoire.getNbDeclarationsSoins()[7]);
        assertEquals(0, statsAleatoire.getNbDeclarationsSoins()[8]);
        assertEquals(1, statsAleatoire.getNbDeclarationsSoins()[9]);
    }

    @Test
    void ajouterReclamartionsAcceptees() {
        assertEquals(3 , statsReclamation.getNbReclamationsAcceptees());
        assertEquals(0, statsReclamation.getNbReclamationsRejetees());
        assertEquals(1, statsReclamation.getNbDeclarationsSoins()[0]);
        assertEquals(0, statsReclamation.getNbDeclarationsSoins()[1]);
        assertEquals(0, statsReclamation.getNbDeclarationsSoins()[2]);
        assertEquals(0, statsReclamation.getNbDeclarationsSoins()[3]);
        assertEquals(0, statsReclamation.getNbDeclarationsSoins()[4]);
        assertEquals(1, statsReclamation.getNbDeclarationsSoins()[5]);
        assertEquals(0, statsReclamation.getNbDeclarationsSoins()[6]);
        assertEquals(0, statsReclamation.getNbDeclarationsSoins()[7]);
        assertEquals(0, statsReclamation.getNbDeclarationsSoins()[8]);
        assertEquals(1, statsReclamation.getNbDeclarationsSoins()[9]);
    }

    @Test
    void ajouterDemandesRejetees(){
        assertEquals(statsAleatoire.getNbReclamationsRejetees() + 1,
                statsAleatoire.ajouterDemandesRejetees().getNbReclamationsRejetees());
    }

    @Test
    void testSerialization(){
        JSONObject objetJsonStatistiques = statsReclamation.serializerStatistiques();
        assertEquals(statsReclamation.getNbReclamationsAcceptees(),
                objetJsonStatistiques.getInt(JSONElement.NB_RECLAMATIONS_VALIDES.toString()));
        assertEquals(statsReclamation.getNbReclamationsRejetees(),
                objetJsonStatistiques.getInt(JSONElement.NB_DEMANDES_REJETEES.toString()));
        for (int i = 0; i < LISTE_SOINS.length; i++) {
            assertEquals(statsReclamation.getNbDeclarationsSoins()[i],
                    objetJsonStatistiques.getJSONObject(JSONElement.NB_DECLARATIONS_SOINS.toString()).getInt(LISTE_SOINS[i]));
        }
    }
}
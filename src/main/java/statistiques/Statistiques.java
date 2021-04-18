package statistiques;

import client.Reclamation;
import contrat.Soin;
import json.FileWriterWrapper;
import json.JSONElement;
import net.sf.json.JSONObject;
import validation.MessageErreur;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Statistiques {

    private JSONObject nbSoins;
    private int nbReclamationsAcceptees;
    private int nbReclamationsRejetees;
    private int[] nbDeclarationsSoins = new int [10];
    private final int[] LISTE_NOMBRE_SOINS = {0, 100, 150, 175, 200, 300, 400, 500, 600, 700};
    private final String[] LISTE_SOINS = {"massotherapie", "osteopathie", "kinesitherapie", "medecin generaliste prive",
            "psychologie individuelle", "soins dentaires", "naturopathie / acupuncture",
            "chiropractie", "physiotherapie", "orthophonie / ergotherapie"};

    public Statistiques(){
        nbReclamationsAcceptees = 0;
        nbReclamationsRejetees = 0;
        nbSoins = new JSONObject();
        for (int i = 0; i < nbDeclarationsSoins.length; i++) {
            nbDeclarationsSoins[i] = 0;
            nbSoins.put(LISTE_SOINS[i], nbDeclarationsSoins[i]);
        }
    }

    public Statistiques(JSONObject statistiques){
        nbReclamationsAcceptees = statistiques.getInt(JSONElement.NB_RECLAMATIONS_VALIDES.toString());
        nbReclamationsRejetees = statistiques.getInt(JSONElement.NB_DEMANDES_REJETEES.toString());
        nbSoins = statistiques.getJSONObject(JSONElement.NB_DECLARATIONS_SOINS.toString());
        for (int i = 0; i < LISTE_SOINS.length; i++) {
            nbDeclarationsSoins[i] = nbSoins.getInt(LISTE_SOINS[i]);
        }
    }

    public void afficher(){
        System.out.println(this.serializerStatistiques().toString(3));
    }

    public Statistiques ajouterReclamartionsAcceptees(ArrayList<Reclamation> listeReclamations){
        nbReclamationsAcceptees += listeReclamations.size();
        for (Reclamation reclamation : listeReclamations) {
            int soin = Soin.validerSoinsDentaires(reclamation.getSoin());
            for (int i = 0; i < LISTE_NOMBRE_SOINS.length; i++) {
                if (soin == LISTE_NOMBRE_SOINS[i]) {
                    nbDeclarationsSoins[i]++;
                }
            }
        }
        return this;
    }

    public Statistiques ajouterDemandesRejetees(){
        nbReclamationsRejetees++;
        return this;
    }

        public JSONObject serializerStatistiques() {
        JSONObject obj = new JSONObject();
        obj.put(JSONElement.NB_RECLAMATIONS_VALIDES.toString(), nbReclamationsAcceptees);
        obj.put(JSONElement.NB_DEMANDES_REJETEES.toString(), nbReclamationsRejetees);
        for (int i = 0; i < nbDeclarationsSoins.length; i++) {
            nbSoins.put(LISTE_SOINS[i], nbDeclarationsSoins[i]);
        }
        obj.put(JSONElement.NB_DECLARATIONS_SOINS, nbSoins);
        return obj;
    }

    public int getNbReclamationsAcceptees() {
        return nbReclamationsAcceptees;
    }

    public int getNbReclamationsRejetees() {
        return nbReclamationsRejetees;
    }

    public int[] getNbDeclarationsSoins() {
        return nbDeclarationsSoins;
    }

    public static void creerFichierStatistiques(){
        if (!Files.exists(Paths.get("statistiques.json"))) {
            try {
                FileWriterWrapper file = new FileWriterWrapper(new FileWriter("statistiques.json"));
                String statsZero = new Statistiques().serializerStatistiques().toString(3);
                file.write(statsZero);
            } catch (IOException e) {
                System.err.println(MessageErreur.ERREUR_IO.toString());
            }
        }
    }

}

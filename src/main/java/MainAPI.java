import client.Client;
import contrat.Remboursement;
import json.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import statistiques.Statistiques;
import validation.DonneeInvalideException;
import validation.MessageErreur;
import validation.Validation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * INF2050 - gr.20 - TP #3
 * <br>
 * Classe principale présentant la seule méthode devant être appelée pour traiter
 * les réclamations d'un client pour un mois donné.
 * <br>
 * Janah, Anass (JANA18089406)<br>
 * Boisvert-Bouchard, Catherine (BOIC92600105)<br>
 * Gharbi, Safouene (GHAS03089005)<br>
 * Paradis, Mireille (PARM07546209)
 */
public class MainAPI {
    private static final String FICHIER_STATISTIQUES = "statistiques.json";
    private static final String MSG_REINITIALISATION = "Les statistiques ont bien été réinitialisées !";
    private static FileReaderWrapper readerReclamation;
    private static FileReaderWrapper readerStatistiques;
    private static FileWriterWrapper writerReclamation;
    private static FileWriterWrapper writerStatistiques;

    /**
     * Méthode permettant de traiter les réclamations d'un client à partir
     * d'un fichier JSON fourni à l'entrée, et permettant de créer un fichier
     * JSON à la sortie avec les informations de remboursement.
     * S'il manque un ou plusieurs des paramètres, aucun traitement n'est fait.
     * Si le fichier d'entrée ne respecte pas la configuration imposée, un fichier
     * d'erreur JSON est généré.
     *
     * @param args &lt;chemin vers le fichier d'entrée> &lt;chemin vers le fichier de sortie><br>
     *             -S  pour afficher les statistiques<br>
     *             -SR pour réinitialiser les statistiques<br>
     *             -p &lt;chemin vers le fichier d'entrée> &lt;chemin vers le fichier de sortie> <br>
     *             pour réaliser le traitement d'un fichier en mode <i>prédiction</i>
     */
    public static void main(String[] args) {
        Statistiques.creerFichierStatistiques();
        validerArguments(args);
        String nomFichierEntree = args[0];
        String nomFichierSortie = args[1];
        preparerLectureEtEcriture(nomFichierEntree, nomFichierSortie);
        traiterFichierJson();
    }

    private static void validerArguments(String[] args) {
        if (args.length == 1) {
            validerArgumentUnique(args);
        } else if (args.length != 2) {
            System.err.println(MessageErreur.ARGUMENT_DOUBLE_INVALIDE);
            System.exit(-1);
        }
    }

    private static void validerArgumentUnique(String[] args) {
        preparerLectureEtEcriture(null, null);
        switch (args[0]) {
            case "-S":
                new Statistiques((JSONObject) JSONSerializer.toJSON(readerStatistiques.read())).afficher();
                break;
            case "-SR":
                writerStatistiques.write(new Statistiques().serializerStatistiques().toString(3));
                System.out.println(MSG_REINITIALISATION);
                break;
            default:
                System.err.println(MessageErreur.ARGUMENT_UNIQUE_INVALIDE);
                System.exit(-1);
                break;
        }
        System.exit(0);
    }

    private static void traiterFichierJson() {
        try {
            traitementFichierValide();
        } catch (DonneeInvalideException e) {
            traitementFichierInvalide(e.getMessage());
        } catch (JSONException e) {
            JSONObject erreur = new JSONObject();
            erreur.put(JSONElement.MESSAGE.toString(), MessageErreur.FICHIER_JSON_INVALIDE.toString());
            writerReclamation.write(erreur.toString(3).replace("{","{\n\t")
                    .replace("}", "\n}"));
            System.err.println(MessageErreur.FICHIER_JSON_INVALIDE.toString());
        }
    }

    private static void traitementFichierValide() throws DonneeInvalideException {
        Client client = new Client((JSONObject) JSONSerializer.toJSON(readerReclamation.read()));
        Validation.validerDonnees(client);
        new Remboursement().calculerRemboursementSelonContrat(client);
        JSONObject statistiquesJson = (JSONObject) JSONSerializer.toJSON(readerStatistiques.read());
        writerStatistiques.write(new Statistiques(statistiquesJson)
                .ajouterReclamartionsAcceptees(client.getListeReclamation())
                .serializerStatistiques().toString(3));
        writerReclamation.write(client.convertirArrayListReclamationToJSONArray().toString(3));
    }

    private static void traitementFichierInvalide(String message){
        writerStatistiques.write(new Statistiques().serializerStatistiques().toString(3));
        writerReclamation.write(((JSONObject)new JSONObject().put(JSONElement.MESSAGE.toString(), message)).toString(3));
    }

    private static void preparerLectureEtEcriture(String nomFichierEntree, String nomFichierSortie)  {
        try {
            readerReclamation = new FileReaderWrapper(new BufferedReader(new FileReader(nomFichierEntree)));
            writerReclamation = new FileWriterWrapper(new FileWriter(nomFichierSortie));
            readerStatistiques = new FileReaderWrapper(new BufferedReader(new FileReader(FICHIER_STATISTIQUES)));
            writerStatistiques = new FileWriterWrapper(new FileWriter(FICHIER_STATISTIQUES));
        } catch (IOException e) {
            System.err.println(MessageErreur.FICHIER_INEXISTANT.toString());
        }
    }



}

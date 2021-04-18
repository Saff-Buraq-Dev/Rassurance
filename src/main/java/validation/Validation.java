package validation;

import client.Client;
import client.Reclamation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Cette classe permet de vérifier toutes les données d'une réclamation.
 */
public class Validation {

    public static int compteurReclamation;

    public static void validerDonnees(Client client) throws DonneeInvalideException {
        compteurReclamation = 1;
        validerClient(client.getNoClient());
        validerContrat(client.getContrat());
        validerDateMois(client.getMois());
        for(Reclamation reclamation : client.getListeReclamation()){
            validerSoin(reclamation.getSoin());
            validerDateJour(reclamation.getDateString());
            comparerDate(reclamation.getDateString(), client.getMois());
            validerMontant(reclamation.getMontantReclamationString());
            compteurReclamation++;
        }
    }

    private static void validerContrat(String chaine) throws DonneeInvalideException {
        if(chaine == null || !chaine.matches("[A-E]")){
            throw new DonneeInvalideException(MessageErreur.CONTRAT_INVALIDE.toString());
        }
    }

    private static void validerClient(String chaine) throws DonneeInvalideException {
        if(chaine == null || !chaine.matches("[0-9]{6}")){
            throw new DonneeInvalideException(MessageErreur.CLIENT_INVALIDE.toString());
        }
    }

    private static void validerMontant(String chaine) throws DonneeInvalideException {
        if(chaine == null || !chaine.matches("^(([0-9][0-9]+)([.,]{1}[0-9]{2})\\$)$")){
            throw new DonneeInvalideException(MessageErreur.MONTANT_INVALIDE.toString());
        }
    }

    private static void validerSoin(int soin) throws DonneeInvalideException {
        if((soin != 0 && soin != 100 && soin != 150 && soin != 175 && soin != 200 && soin != 500
                && soin != 600 && soin != 700) && (soin < 300 || soin > 400)) {
            throw new DonneeInvalideException(MessageErreur.SOIN_INVALIDE.toString());
        }
    }

    /**
     * @param dateAVerifier : date sous forme de chaine caractère "yyyy-MM-dd" ou "yyyy-MM" à vérifier.
     * @throws DonneeInvalideException : Générée si la date n'est pas valide ou si la date est ultérieure à la date actuelle.
     */
    private static void validerDateMois(String dateAVerifier) throws DonneeInvalideException {
        SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM");
        try{
            sdfm.setLenient(false);
            sdfm.parse(dateAVerifier);
        } catch (Exception e) {
            throw new DonneeInvalideException(MessageErreur.MOIS_INVALIDE.toString());
        }
    }

    private static void validerDateJour(String dateAVerifier) throws DonneeInvalideException {
        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy-MM-dd");
        try{
            sdfy.setLenient(false);
            sdfy.parse(dateAVerifier);
        } catch (ParseException e) {
            throw new DonneeInvalideException(MessageErreur.DATE_INVALIDE.toString());
        }
    }


    /**
     * @param dateUn : date sous forme de chaine de caractère "yyyy-MM-dd"
     * @param dateDeux : date sous forme de chaine de caractère "yyyy-MM"
     * @throws DonneeInvalideException : Générée si les dates sont différentes.
     */
    private static void comparerDate(String dateUn, String dateDeux) throws DonneeInvalideException {
        if(!dateUn.substring(0,7).equals(dateDeux)){
            throw new DonneeInvalideException(MessageErreur.DATE_INVALIDE.toString());
        }
    }

}

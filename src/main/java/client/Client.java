package client;

import dollar.Dollar;
import java.util.ArrayList;
import json.JSONElement;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import validation.DonneeInvalideException;


/**
 * Classe représentant un client créé à partir d'un fichier JSON.
 */
public class Client {
    private String dossier;
    private String contrat;
    private String noClient;
    private String mois;
    private ArrayList<Reclamation> listeReclamation;


    public Client(String dossier, String mois, ArrayList<Reclamation> listeReclamation){
        setDossier(dossier);
        setMois(mois);
        setListeReclamation(listeReclamation);
    }

    public Client(JSONObject fichierReclamation) throws DonneeInvalideException {
        try {
            setDossier(fichierReclamation.getString(JSONElement.DOSSIER.toString()));
            setMois(fichierReclamation.getString(JSONElement.MOIS.toString()));
            setListeReclamation(fichierReclamation.getJSONArray(JSONElement.RECLAMATION.toString()));
        } catch (JSONException e) {
            throw new DonneeInvalideException(e.getMessage().replace("JSONObject[", "La clé: ").
                replace("] not found.", " est introuvable."));
        } catch (NumberFormatException e){
            throw new DonneeInvalideException("La clé: \"soin\" est introuvable.");
        }
    }

    public Dollar calculerTotalRemboursement() {
        Dollar resultat = new Dollar();
        for (Reclamation item : listeReclamation) {
            resultat = resultat.additionner(item.getMontantRemboursement());
        }
        return resultat;
    }

    public Dollar calculerTotalRemboursementPourUnSoin(int soin) {
        Dollar resultat = new Dollar();
        for (Reclamation item : listeReclamation) {
            if (soin == item.getSoin()) {
                resultat = resultat.additionner(item.getMontantRemboursement());
            }
        }
        return resultat;
    }

    public int calculerNombreReclamationPourUnJour(String jour) {
        int resultat = 0;
        for (Reclamation item : listeReclamation) {
            if (jour.equals(item.getDateString())) {
                resultat ++;
            }
        }
        return resultat;
    }

    public JSONObject convertirArrayListReclamationToJSONArray() {
        JSONObject objet = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Reclamation reclamation : listeReclamation){
            jsonArray.add(reclamation.convertirReclamationToJSONObject());
        }
        objet.put(JSONElement.DOSSIER.toString(), dossier);
        objet.put(JSONElement.MOIS.toString(), mois);
        objet.put(JSONElement.REMBOURSEMENTS.toString(), jsonArray);
        objet.put(JSONElement.TOTAL.toString(), calculerTotalRemboursement().toString());
        return objet;
    }


    // setters
    public void setDossier(String dossier) {
        this.dossier = dossier;
        if(dossier != null) {
            this.contrat = dossier.substring(0, 1);
            this.noClient = dossier.substring(1);
        }
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public void setListeReclamation(ArrayList<Reclamation> listeReclamation) {
        this.listeReclamation = listeReclamation;
    }

    public void setListeReclamation(JSONArray listeJSON) {
        this.listeReclamation = new ArrayList<>();
        for (Object item : listeJSON) {
            int soin = Integer.parseInt(((JSONObject) item).getString(JSONElement.SOIN.toString()));
            String date = ((JSONObject) item).getString(JSONElement.DATE.toString());
            String montant = ((JSONObject) item).getString(JSONElement.MONTANT.toString());
            listeReclamation.add(new Reclamation(soin, date, montant));
        }
    }


    // getters
    public String getDossier() {
        return dossier;
    }

    public String getNoClient() {
        return noClient;
    }

    public String getContrat() {
        return contrat;
    }

    public String getMois() {
        return mois;
    }

    public ArrayList<Reclamation> getListeReclamation() {
        return listeReclamation;
    }

}

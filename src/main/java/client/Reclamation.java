package client;

import dollar.Dollar;
import json.JSONElement;
import net.sf.json.JSONObject;

/**
 * Classe représentant une réclamation d'un client.
 */
public class Reclamation {
    private int soin;
    private String date;
    private Dollar montantReclamation;
    private Dollar montantRemboursement;


    public Reclamation(int soin, String date, String montantReclamation) {
        setSoin(soin);
        setDate(date);
        setMontantReclamation(montantReclamation);
        montantRemboursement = new Dollar();
    }

    public JSONObject convertirReclamationToJSONObject() {
        JSONObject objet = new JSONObject();
        objet.put(JSONElement.SOIN.toString(), soin);
        objet.put(JSONElement.DATE.toString(), date);
        objet.put(JSONElement.MONTANT.toString(), getMontantRemboursementString());
        return objet;
    }

    @Override
    public String toString() {
        return "Soin : " + soin + " (" + date + ") - Réclamation : " + montantReclamation +
                " - Remboursement : " + montantRemboursement;
    }


    // setters
    public void setSoin(int soin) {
        this.soin = soin;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMontantReclamation(String montant) {
        if (montant == null) {
            this.montantReclamation = new Dollar();
        } else {
            this.montantReclamation = new Dollar(montant);
        }
    }

    public void setMontantRemboursement(String montant) {
        if (montant == null) {
            this.montantRemboursement = new Dollar();
        } else {
            this.montantRemboursement = new Dollar(montant);
        }
    }


    // getters
    public int getSoin() {
        return soin;
    }

    public String getDateString() {
        return date;
    }

    public Dollar getMontantReclamation() {
        return montantReclamation;
    }

    public String getMontantReclamationString() {
        return montantReclamation.toString();
    }

    public Dollar getMontantRemboursement() {
        return montantRemboursement;
    }

    public String getMontantRemboursementString() {
        return montantRemboursement.toString();
    }

}
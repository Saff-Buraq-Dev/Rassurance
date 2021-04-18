package contrat;

import client.Client;
import client.Reclamation;
import dollar.Dollar;

import java.util.ArrayList;

/**
 * Classe permettant de calculer le montant à rembourser selon le contrat et le soin du client.
 */
public class Remboursement {
    private final ArrayList<Contrat> listeContrat;
    private Contrat contratChoisi;

    public Remboursement() {
        Contrat contratA = new ContratA();
        Contrat contratB = new ContratB();
        Contrat contratC = new ContratC();
        Contrat contratD = new ContratD();
        Contrat contratE = new ContratE();

        this.listeContrat = new ArrayList<>();
        listeContrat.add(contratA);
        listeContrat.add(contratB);
        listeContrat.add(contratC);
        listeContrat.add(contratD);
        listeContrat.add(contratE);
    }

    private void choisirContrat(String contrat) {
        for (Contrat item : listeContrat) {
            if (contrat.equals(item.getIdentification())) {
                contratChoisi = item;
            }
        }
    }

    public void calculerRemboursementSelonContrat(Client client) {
        choisirContrat(client.getContrat());
        for (Reclamation item : client.getListeReclamation()) {
            item.setMontantRemboursement(calculerRemboursementSelonSoin(item.getSoin(),
                    item.getMontantReclamation(),
                    client));
        }
    }

    private String calculerRemboursementSelonSoin(int soin, Dollar montantReclamation, Client client) {
        Dollar montantRemboursement = new Dollar ();

        for (Soin item : contratChoisi.getListeSoin()) {
            if (soin == item.getNumeroSoin()) {
                montantRemboursement = calculerPourcentageAvecMaximum(montantReclamation, item);
                if (item.getMaximumMensuel().compareTo(new Dollar()) > 0) {
                    montantRemboursement = calculerRemboursementSelonMaximumMensuel(client, montantRemboursement, item);
                }
            }
        }
        return montantRemboursement.toString();
    }

    protected Dollar calculerPourcentageAvecMaximum(Dollar montantReclamation, Soin soin) {
        Dollar montant = montantReclamation.calculerPourcentage(soin.getPourcentage());

        if (soin.getMaximum().compareTo(new Dollar()) > 0 && montant.compareTo(soin.getMaximum()) > 0) {
            montant = soin.getMaximum();
        }

        return montant;
    }

    protected Dollar calculerRemboursementSelonMaximumMensuel(Client client, Dollar montantRemboursement, Soin soin) {
        Dollar remboursementsACeJour = client.calculerTotalRemboursementPourUnSoin(soin.getNumeroSoin());
        Dollar remboursementsDuMoisActuel = remboursementsACeJour.additionner(montantRemboursement);

        if (remboursementsACeJour.compareTo(soin.getMaximumMensuel()) >= 0) {
            montantRemboursement = new Dollar();
        } else if (remboursementsDuMoisActuel.compareTo(soin.getMaximumMensuel()) > 0) {
            montantRemboursement = soin.getMaximumMensuel().soustraire(remboursementsACeJour);
        }
        return montantRemboursement;
    }

}


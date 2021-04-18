package contrat;

import client.Client;
import dollar.Dollar;

/**
 * Classe permettant de définir un Soin selon ses éléments essentiels:
 * - Numéro du soin
 * - Pourcentage à rembourser
 * - Valeur maximale à rembourser
 * - Valeur maximale à rembourser pour un mois
 */
public class Soin {

    private int numeroSoin;
    private int pourcentage;
    private Dollar maximum;
    private Dollar maximumMensuel = new Dollar("0.00$");

    public Soin(int numeroSoin, int pourcentage, Dollar maximum) {
        this.numeroSoin = validerSoinsDentaires(numeroSoin);
        this.pourcentage = pourcentage;
        this.maximum = maximum;
        setMaximumMensuel(numeroSoin);
    }

    /**
     * Uniformise l'indice des soins dentaires (entre 300 à 399) à une valeur de 300.
     * @param soin : indice de soin
     * @return l'indice de soin
     */
    public static int validerSoinsDentaires(int soin) {
        if (soin > 300 && soin <= 399){
            soin = 300;
        }
        return soin;
    }

    private void setMaximumMensuel(int numeroSoin) {
        int [] soin = {100, 175, 200, 500, 600};
        Dollar [] maximumMensuel = {new Dollar("250.00$"), new Dollar("200.00$"),
                new Dollar("250.00$"), new Dollar("150.00$"), new Dollar("300.00$")};

        for (int i = 0; i < soin.length; i++) {
            if (soin[i] == numeroSoin) {
                this.maximumMensuel = maximumMensuel[i];
            }
        }
    }

    public int getNumeroSoin() {
        return numeroSoin;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public Dollar getMaximum() {
        return maximum;
    }

    public Dollar getMaximumMensuel() {
        return maximumMensuel;
    }

}


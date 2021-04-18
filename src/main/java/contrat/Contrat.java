package contrat;

import dollar.Dollar;

import java.util.ArrayList;

/**
 * Classe abstraite qui défini les éléments essentiels constituant un Contrat:
 * - Une identification de type String
 * - Une ArrayList de type Soin contenant les soins remboursables par le contrat
 */
public abstract class Contrat {

    protected String identification;
    protected ArrayList<Soin> listeSoin;

    protected static final Dollar MAX_REMBOURSEMENT_NULL = new Dollar("0.00$");

    public String getIdentification() {
        return identification;
    }

    public ArrayList<Soin> getListeSoin() {
        return listeSoin;
    }

}

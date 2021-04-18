package contrat;

import java.util.ArrayList;

/**
 * Classe représentant les éléments essentiels du contrat C
 */
public class ContratC extends Contrat {

    public ContratC() {
        this.identification = "C";
        this.listeSoin = new ArrayList<>();
        listeSoin.add(new Soin(0, 90, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(100, 95, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(150, 85, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(175, 90, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(200, 90, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(300, 90, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(400, 90, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(500, 90, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(600, 75, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(700, 90, MAX_REMBOURSEMENT_NULL));
    }

}


package contrat;

import java.util.ArrayList;

/**
 * Classe représentant les éléments essentiels du contrat A
 */
public class ContratA extends Contrat {

    public ContratA() {
        this.identification = "A";
        this.listeSoin = new ArrayList<>();
        listeSoin.add(new Soin(0, 25, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(100, 35, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(150, 0, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(175, 50, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(200, 25, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(300, 0, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(400, 0, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(500, 25, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(600, 40, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(700, 0, MAX_REMBOURSEMENT_NULL));
    }

}

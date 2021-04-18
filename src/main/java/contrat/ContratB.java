package contrat;

import dollar.Dollar;
import java.util.ArrayList;

/**
 * Classe représentant les éléments essentiels du contrat B
 */
public class ContratB extends Contrat {

    public ContratB() {
        this.identification = "B";
        this.listeSoin = new ArrayList<>();
        listeSoin.add(new Soin(0, 50, new Dollar("40.00$")));
        listeSoin.add(new Soin(100, 50, new Dollar("50.00$")));
        listeSoin.add(new Soin(150, 0, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(175, 75, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(200, 100, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(300, 50, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(400, 0, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(500, 50, new Dollar("50.00$")));
        listeSoin.add(new Soin(600, 100, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(700, 70, MAX_REMBOURSEMENT_NULL));
    }

}

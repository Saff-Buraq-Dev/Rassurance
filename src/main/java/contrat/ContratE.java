package contrat;

import dollar.Dollar;
import java.util.ArrayList;

/**
 * Classe représentant les éléments essentiels du contrat E
 */
public class ContratE extends Contrat {

    public ContratE() {
        this.identification = "E";
        this.listeSoin = new ArrayList<>();
        listeSoin.add(new Soin(0, 15, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(100, 25, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(150, 15, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(175, 25, new Dollar("20.00$")));
        listeSoin.add(new Soin(200, 12, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(300, 60, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(400, 25, new Dollar("15.00$")));
        listeSoin.add(new Soin(500, 30, new Dollar("20.00$")));
        listeSoin.add(new Soin(600, 15, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(700, 22, MAX_REMBOURSEMENT_NULL));
    }

}

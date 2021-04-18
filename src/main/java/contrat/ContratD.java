package contrat;

import dollar.Dollar;
import java.util.ArrayList;

/**
 * Classe représentant les éléments essentiels du contrat D
 */
public class ContratD extends Contrat {

    public ContratD() {
        this.identification = "D";
        this.listeSoin = new ArrayList<>();
        listeSoin.add(new Soin(0, 100, new Dollar("85.00$")));
        listeSoin.add(new Soin(100, 100, new Dollar("75.00$")));
        listeSoin.add(new Soin(150, 100, new Dollar("150.00$")));
        listeSoin.add(new Soin(175, 95, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(200, 100, new Dollar("100.00$")));
        listeSoin.add(new Soin(300, 100, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(400, 100, new Dollar("65.00$")));
        listeSoin.add(new Soin(500, 100, MAX_REMBOURSEMENT_NULL));
        listeSoin.add(new Soin(600, 100, new Dollar("100.00$")));
        listeSoin.add(new Soin(700, 100, new Dollar("90.00$")));
    }

}


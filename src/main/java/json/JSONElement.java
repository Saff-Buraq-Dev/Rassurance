package json;

/**
 * Enum représentant les différentes valeurs de clefs utilisées dans les fichiers JSON.
 */
public enum JSONElement {
    DOSSIER("dossier"),
    DATE("date"),
    MESSAGE("message"),
    MOIS("mois"),
    MONTANT("montant"),
    RECLAMATION("reclamations"),
    REMBOURSEMENTS("remboursements"),
    SOIN("soin"),
    TOTAL("total"),
    NB_RECLAMATIONS_VALIDES("nombre de reclamations valides traitees"),
    NB_DEMANDES_REJETEES("nombre de demandes rejetees"),
    NB_DECLARATIONS_SOINS("nombre de soins declares pour chaque type de soin"),
    ;

    private final String nom;

    JSONElement(String nom) {
        this.nom = nom;
    }
    @Override
    public String toString() {
        return nom;
    }

}

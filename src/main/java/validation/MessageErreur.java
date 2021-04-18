package validation;

/**
 * Classe représentant les messages d'erreur apparaissant dans le programme.
 */
public enum MessageErreur {
    ARGUMENT_DOUBLE_INVALIDE("Il n'y a pas deux arguments à l'entrée."),
    ARGUMENT_UNIQUE_INVALIDE("S'il y a un seul argument, celui-ci doit être '-S' ou '-SR'."),
    AUCUNE_RECLAMATION("Il n'y a aucune réclamation dans la demande."),
    CLIENT_INVALIDE("Le numéro du client est invalide."),
    CONTRAT_INVALIDE("Le contrat est invalide."),
    DATE_INVALIDE("La date dans la réclamation " + Validation.compteurReclamation + " est invalide."),
    ERREUR_IO("Erreur I/O."),
    FICHIER_INEXISTANT("Le fichier d'entrée est introuvable."),
    FICHIER_JSON_INVALIDE("Le fichier JSON n'est pas valide."),
    MOIS_INVALIDE("Le mois est invalide."),
    MONTANT_INVALIDE("Le montant dans la réclamation " + Validation.compteurReclamation + " est invalide."),
    SOIN_INVALIDE("Le numéro du soin dans la réclamation " + Validation.compteurReclamation + " est invalide."),
    DIVISION_PAR_ZERO("Division par zero impossible"),
    ;


    private final String texte;

    MessageErreur(String texte) {
        this.texte = texte;
    }

    @Override
    public String toString() {
        return texte;
    }

}

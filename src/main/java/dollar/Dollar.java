package dollar;

import validation.MessageErreur;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *  Classe qui construit un objet Dollar à partir d'une chaine de caractere ou d'un int qui représente le montant.
 *  On ne fait pas de validation du montant à ce niveau, car on suppose que c'est deja fait avant la création d'un Dollar.
 */
public class Dollar implements Comparable<Dollar> {

    private final String montant;
    private static final String DEVISE = "$";

    public Dollar() {
        montant = "0.00" + DEVISE;
    }

    public Dollar(String montant) {
        this.montant = montant.replace(",",".");
    }

    private Dollar(int montant) {
        this.montant = montantEnDollar(montant);
    }

    private int montantEnCents() {
        return Integer.parseInt(montant.replace(".","")
                .replace(",", "")
                .replace(DEVISE,""));
    }

    private String montantEnDollar(int montant) {
        int dollar = montant / 100;
        int cents = montant % 100;
        String montantDollar = dollar + "." + cents + DEVISE;
        if (cents < 10)
            montantDollar = (cents == 0) ? dollar + ".00" + DEVISE :
                    dollar + ".0" + cents + DEVISE ;
        return montantDollar;
    }

    public Dollar additionner(Dollar dollar) {
        return new Dollar(montantEnCents() + dollar.montantEnCents());
    }

    public Dollar soustraire(Dollar dollar) {
        return new Dollar(montantEnCents() - dollar.montantEnCents());
    }

    private Dollar multiplier(int valeur) {
        BigDecimal resultat =
                new BigDecimal(montant.replace(DEVISE,""))
                        .multiply(BigDecimal.valueOf(valeur)).setScale(2,
                        RoundingMode.HALF_UP);
        return new Dollar(resultat.toString()+DEVISE);
    }

    private Dollar diviser(int valeur) {
        if(valeur == 0)
            throw new ArithmeticException(MessageErreur.DIVISION_PAR_ZERO.toString());
        BigDecimal resultat =
                new BigDecimal(montant.replace(DEVISE,""))
                        .divide(BigDecimal.valueOf(valeur), MathContext.DECIMAL32).setScale(2,
                        RoundingMode.HALF_UP);
        return new Dollar(resultat.toString()+DEVISE);
    }
	
    public Dollar calculerPourcentage(int pourcentage){
        return multiplier(pourcentage).diviser(100);
    }

    @Override
    public String toString() {
        return montant;
    }

    @Override
    public boolean equals(Object dollar) {
        boolean resultat = false;
        if (dollar instanceof Dollar) {
            if (montant.equals(((Dollar) dollar).montant)) {
                resultat = true;
            }
        }
        return resultat;
    }

    @Override
    public int compareTo(Dollar dollar) {
        int resultat = 0;
        if (this.montantEnCents() > dollar.montantEnCents()) {
            resultat = 1;
        } else if (this.montantEnCents() < dollar.montantEnCents()) {
            resultat = -1;
        }
        return resultat;
    }

}
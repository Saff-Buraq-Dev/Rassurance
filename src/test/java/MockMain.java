/**
 * Classe «mock» servant à tester les méthodes de MainAPI.
 */
public class MockMain {
    private static String resultat = "";

    public MockMain() {
        super();
    }

    public static void validerArguments(String[] args) {
        if (args.length == 1) {
            resultat = "argument = 1";
        } else if (args.length != 2) {
            resultat = "argument = 0 ou > 2";
        } else {
            resultat = "argument = 2";
        }
    }

    public static void validerArgumentUnique(String[] args) {
        switch (args[0]) {
            case "-S":
                resultat = "argument = -S";
                break;
            case "-SR":
                resultat = "argument = -SR";
                break;
            default:
                resultat = "argument est invalide";
                break;
        }
    }

    public static String getResultat() {
        return resultat;
    }

}

package validation;

/**
 * Cette classe permet de signaler une donnée invalide.
 * Cette classe hérite de la classe Exception.
 */
public class DonneeInvalideException extends Exception {


    public DonneeInvalideException(String message){
        super(message);
    }


}

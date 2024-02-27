package finance;

/**
 * Classe d'exception si le taux de change est introuvable
 */
public class RateUnavailableException extends Exception {
    public RateUnavailableException(String errorMessage) {
        super(errorMessage);
    }
}

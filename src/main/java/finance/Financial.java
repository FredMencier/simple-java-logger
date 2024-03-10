package finance;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Financial implements FinancialInterface {

    private static final Logger LOG = Logger.getLogger(Financial.class.getName());

    private final Map<String, Double> mapCurrenciesRate;

    public Financial() {
        mapCurrenciesRate = Map.of("CHFEUR", 1.06D, "CHFUSD", 1.14D);
    }

    /**
     * Calcule la moyenne d'une liste de double
     * @param doubleList
     * @return
     */
    public double calculateAverage(final List<Double> doubleList) {
        long in = System.currentTimeMillis();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Calculate Average with : %s".formatted(doubleList));
        }
        double sum = 0D;
        for (Double dbl : doubleList) {
            sum = sum + dbl;
        }
        double res = sum / doubleList.size();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Calculate Average in : %s ms".formatted (System.currentTimeMillis() - in));
        }
        return res;
    }

    /**
     * Calcule la somme d'une liste de double
     * @param doubleList
     * @return
     */
    public double calculateSum(final List<Double> doubleList) {
        double sum = 0D;
        for (Double dbl : doubleList) {
            sum = sum + dbl;
        }
        LOG.info("Calculate Sum with %s = %s".formatted(doubleList, sum));
        return sum;
    }

    /**
     * Calcule le change entre 2 monnaies
     * @param fromCurrency
     * @param toCurrency
     * @param amount
     * @return
     * @throws RateUnavailableException
     */
    public double calculateChange(final String fromCurrency, final String toCurrency, final double amount) throws RateUnavailableException {
        Double rate = mapCurrenciesRate.get(fromCurrency + toCurrency);
        if (rate == null) {
            String msg = "Currency rate not found for change %s/%s".formatted(fromCurrency, toCurrency);
            LOG.warn(msg);
            throw new RateUnavailableException(msg);
        }
        double change = amount * rate;
        LOG.info("Calculate change for %s/%s with amount %s = %s".formatted(fromCurrency, toCurrency, amount, change));
        return change;
    }

    /**
     * Donne la liste des monnaies disponibles
     * @return
     */
    public List<String> getAvailableCurrencies() {
        long in = System.currentTimeMillis();
        try {
            Thread.sleep(4000);
            List<String> currencies = List.of("CHF", "EUR", "USD");
            LOG.info("Return available currencies in : %s ms".formatted (System.currentTimeMillis() - in));
            return currencies;
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
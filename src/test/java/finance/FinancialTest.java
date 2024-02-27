package finance;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FinancialTest {

    private static final Logger LOG = Logger.getLogger(FinancialTest.class.getName());

    private Financial financial;
    private List<Double> doubleList;

    @Before
    public void init() {
        LOG.info("Initialisation du test");
        financial = new Financial();
        doubleList = Arrays.asList(1D, 2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D);
    }

    @After
    public void teardown() {
        System.out.println("Libération des resources");
        financial = null;
        doubleList = null;
    }

    @Test
    public void calculateAverage() {
        LOG.info("Test calculate average");
        double average = financial.calculateAverage(doubleList);
        Assert.assertEquals(5.5D, average, 0D);
    }

    @Test
    public void calculateSum() {
        LOG.info("Test calculate sum");
        double sum = financial.calculateSum(doubleList);
        Assert.assertEquals(55D, sum, 0D);
    }

    @Test
    public void calculateChange() throws RateUnavailableException {
        LOG.info("Test calculate change");
        double change = financial.calculateChange("CHF", "EUR", 1000D);
        Assert.assertEquals(1060D, change, 0D);
    }

    @Test(expected = RateUnavailableException.class)
    public void calculateChangeWithException() throws RateUnavailableException {
        financial.calculateChange("CHF", "JPY", 1000D);
    }

    @Test(timeout = 5000)
    public void getAvailableCurrencies() {
        Assert.assertEquals(3, financial.getAvailableCurrencies().size());
    }
}
/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.strategies;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Madimetja
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({financialmarketsimulator.strategies.PriceSmaCrossoverTest.class, financialmarketsimulator.strategies.MovingAverageFilterTest.class, financialmarketsimulator.strategies.MovingAverageCrossoverTest.class, financialmarketsimulator.strategies.PriceEmaCrossoverTest.class, financialmarketsimulator.strategies.SimpleRSITest.class, financialmarketsimulator.strategies.Simple_MACD_ADXTest.class, financialmarketsimulator.strategies.MovingAverageEnvelopeTest.class, financialmarketsimulator.strategies.PhantomTest.class, financialmarketsimulator.strategies.MACDStrategyTest.class})
public class StrategiesSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}

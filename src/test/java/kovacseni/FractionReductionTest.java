package kovacseni;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionReductionTest {
    @Test
    public void testGetNumberOfNotReductiveFractions() {
        Assertions.assertEquals(58, new FractionReduction().getNumberOfNotReductiveFractions());
    }
}
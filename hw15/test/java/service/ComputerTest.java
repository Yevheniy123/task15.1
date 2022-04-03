package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {

    @RepeatedTest(200)
    void getComputerDecisionTest() {
        int testDecision = new Computer().getComputerDecision();
        Assertions.assertTrue(testDecision >= 0 && testDecision < 3);
    }
}
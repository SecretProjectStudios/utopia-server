package secretprojectstudios.domain;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class BillTest {
    @Test
    public void shouldOutputWeights() {
        Bill bill = new Bill();
        Map<Ideal, Integer> passEffect = bill.getPassEffect();
        Map<Ideal, Integer> failEffect = bill.getFailEffect();
        for (Ideal ideal: passEffect.keySet()) {
            System.out.println(String.format("%s: %d", ideal.toString(), passEffect.get(ideal)));
        }
        System.out.println("------");
        for (Ideal ideal: failEffect.keySet()) {
            System.out.println(String.format("%s: %d", ideal.toString(), failEffect.get(ideal)));
        }
    }
}
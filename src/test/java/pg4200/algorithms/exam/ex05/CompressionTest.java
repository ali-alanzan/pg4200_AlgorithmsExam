package pg4200.algorithms.exam.ex05;

import org.junit.jupiter.api.Test;
import pg4200.algorithms.exam.Ex05;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompressionTest {
    @Test
    private void checkPreserveInformation(String movement ){

        byte[] compressed = Ex05.compress(movement);

        String decompressed = Ex05.decompress(compressed);

        assertEquals(movement, decompressed);
    }

    @Test
    public void testPreserveInformation(){

        checkPreserveInformation("1pe2e4");
        checkPreserveInformation("2pe7e5");
        checkPreserveInformation("1pc2c32pd7d63qd1a4!4pb7b5");
        checkPreserveInformation("1pf2f32pe7e53pg2g44qd8h4!");

    }



}

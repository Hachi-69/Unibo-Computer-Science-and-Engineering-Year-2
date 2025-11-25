package it.unibo.oop.lab.lambda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Simple test for {@link it.unibo.oop.lab.lambda.LambdaUtilities}.
 */
final class TestLambdaUtilities {

    private static final String A = "a";
    private static final String P_1 = "p1";
    private static final String P_2 = "p2";
    private static final String P_3 = "p3";
    private static final String N_1 = "n1";
    private static final String N_2 = "n2";

    /**
     * Test dup method.
     */
    @Test
    void testDup() {
        assertEquals(
            List.of(1, 101, 2, 102, 3, 103, 4, 104, 5, 105),
            LambdaUtilities.dup(List.of(1, 2, 3, 4, 5), x -> x + 100)
        );
        assertEquals(
            List.of(A, "aa", "b", "bb", "c", "cc"),
            LambdaUtilities.dup(List.of(A, "b", "c"), x -> x + x)
        );
    }

    /**
     * Test optFilter method.
     */
    @Test
    void testOptFilter() {
        assertEquals(
            List.of(Optional.empty(), Optional.of(2), Optional.empty(), Optional.of(4), Optional.empty(), Optional.of(6)),
            LambdaUtilities.optFilter(List.of(1, 2, 3, 4, 5, 6), x -> x % 2 == 0)
        );
        assertEquals(
            List.of(Optional.empty(), Optional.of("bcd"), Optional.of("qw"), Optional.empty(), Optional.empty()),
            LambdaUtilities.optFilter(List.of(A, "bcd", "qw", "e", ""), x -> x.length() > 1)
        );
    }

    /**
     * Test group method.
     */
    @Test
    void testGroup() {
        assertEquals(
            Map.of(
                "even", Set.of(2, 4),
                "odd", Set.of(1, 3, 5)
            ),
            LambdaUtilities.group(List.of(1, 2, 3, 4, 5), x -> x % 2 == 0 ? "even" : "odd")
        );
        assertEquals(
            Map.of(
                3, Set.of("abc", "qwe"),
                2, Set.of("zx", "cv"),
                1, Set.of("o")
            ),
            LambdaUtilities.group(List.of("abc", "qwe", "zx", "o", "cv"), String::length)
        );
    }

    /**
     * Test fill method.
     */
    @Test
    void testFill() {
        final var random = new Random();
        final var map = LambdaUtilities.fill(
            Map.of(
                P_1, Optional.of(1),
                P_2, Optional.of(2),
                N_1, Optional.empty(),
                P_3, Optional.of(3),
                N_2, Optional.empty()
            ),
            () -> random.nextInt(10) - 10
        );
        assertEquals(map.keySet(), Set.of(P_1, P_2, P_3, N_1, N_2));
        assertEquals(map.get(P_1), 1);
        assertEquals(map.get(P_2), 2);
        assertEquals(map.get(P_3), 3);
        assertTrue(map.get(N_1) < 0);
        assertTrue(map.get(N_2) < 0);
    }
}

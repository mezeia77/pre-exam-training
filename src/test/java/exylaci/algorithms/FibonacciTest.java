package exylaci.algorithms;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {
    @Test
    void getPrimsParameter() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Fibonacci().getPrims(-1));
        assertEquals("Invalid parameter!", exception.getMessage());
    }

    @Test
    void getPrims() {
        assertEquals("", new Fibonacci().getPrims(0));
        assertEquals("", new Fibonacci().getPrims(1));
        assertEquals("", new Fibonacci().getPrims(2));
        assertEquals("2, 3", new Fibonacci().getPrims(4));
        assertEquals("2, 3, 5, 13, 89", new Fibonacci().getPrims(11));
        assertEquals("2, 3, 5, 13, 89, 233, 1597", new Fibonacci().getPrims(20));
    }

    @Test
    void getPiecesPrims() {
        assertEquals(List.of(), new Fibonacci().getPiecesPrims(-1));
        assertEquals(List.of(), new Fibonacci().getPiecesPrims(0));
        assertEquals(List.of(2), new Fibonacci().getPiecesPrims(1));
        assertEquals(List.of(2,3), new Fibonacci().getPiecesPrims(2));
        assertEquals(List.of(2,3,5), new Fibonacci().getPiecesPrims(3));
        assertEquals(List.of(2,3,5,13,89,233,1597), new Fibonacci().getPiecesPrims(7));
    }
}
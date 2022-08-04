package io.crocker.jlox;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ASTPrinterTest {

    @Test
    void print() {
        List<Stmt> statements = Arrays.asList(
                new Stmt.Var(
                        new Token(TokenType.IDENTIFIER, "a", null, 1),
                        new Expr.Literal(2.0)
                )
        );

        assertEquals("(program\n\t(var a 2.0)\n)", (new ASTPrinter()).print(statements));
    }
}
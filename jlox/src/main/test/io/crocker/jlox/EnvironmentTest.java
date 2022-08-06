package io.crocker.jlox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnvironmentTest {

    @Test
    void defineThenGet() {
        Object want = "value";

        Environment env = new Environment();
        env.define("test", want);

        Object result = env.get(toToken("test"));
        assertEquals(want, result);
    }

    @Test
    void getUndefinedVariable() {
        assertThrows(RuntimeError.class, () -> {
            Environment env = new Environment();
            env.get(toToken("test"));
        });
    }

    @Test
    void assign() {
        Object want = "value";

        Environment env = new Environment();
        env.define("test", "initial");
        env.assign(toToken("test"), want);

        Object result = env.get(toToken("test"));
        assertEquals(want, result);
    }

    @Test
    void assignToUndefinedVariable() {
        assertThrows(RuntimeError.class, () -> {
            Environment env = new Environment();
            env.assign(toToken("test"), "value");
        });
    }

    private Token toToken(String name) {
        return new Token(TokenType.IDENTIFIER, name, null, 1);
    }
}
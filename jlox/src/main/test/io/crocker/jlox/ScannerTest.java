package io.crocker.jlox;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static io.crocker.jlox.TokenType.*;

class ScannerTest {

    @ParameterizedTest(name = "#{index} Input String: {0}")
    @MethodSource("scanTokensProvider")
    void scanTokens(String input, List<Token> wantTokens) {
        Scanner s = new Scanner(input);
        List<Token> gotTokens = s.scanTokens();

        assertEquals(wantTokens.size(), gotTokens.size());
        for (int i = 0; i < wantTokens.size(); i++) {
            Token want = wantTokens.get(i);
            Token got = gotTokens.get(i);

            assertEquals(want.type, got.type);
            assertEquals(want.lexeme, got.lexeme);
            assertEquals(want.literal, got.literal);
            assertEquals(want.line, got.line);
        }
    }

    static Stream<Arguments> scanTokensProvider() {
        return Stream.of(
                Arguments.arguments("", List.of(new Token(EOF, "", null, 1))),
                Arguments.arguments("(", Arrays.asList(
                        new Token(LEFT_PAREN, "(", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments(")", Arrays.asList(
                        new Token(RIGHT_PAREN, ")", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("{", Arrays.asList(
                        new Token(LEFT_BRACE, "{", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("}", Arrays.asList(
                        new Token(RIGHT_BRACE, "}", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments(",", Arrays.asList(
                        new Token(COMMA, ",", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments(".", Arrays.asList(
                        new Token(DOT, ".", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("-", Arrays.asList(
                        new Token(MINUS, "-", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("+", Arrays.asList(
                        new Token(PLUS, "+", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments(";", Arrays.asList(
                        new Token(SEMICOLON, ";", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("*", Arrays.asList(
                        new Token(STAR, "*", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("!", Arrays.asList(
                        new Token(BANG, "!", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("!=", Arrays.asList(
                        new Token(BANG_EQUAL, "!=", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("=", Arrays.asList(
                        new Token(EQUAL, "=", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("==", Arrays.asList(
                        new Token(EQUAL_EQUAL, "==", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("<", Arrays.asList(
                        new Token(LESS, "<", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("<=", Arrays.asList(
                        new Token(LESS_EQUAL, "<=", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments(">", Arrays.asList(
                        new Token(GREATER, ">", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments(">=", Arrays.asList(
                        new Token(GREATER_EQUAL, ">=", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("/", Arrays.asList(
                        new Token(SLASH, "/", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("// comment!", Arrays.asList(
                        // No other tokens
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("             // whitespace", Arrays.asList(
                        // Whitespace is ignored
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("\n", Arrays.asList(
                        // new lines increment the line counter
                        new Token(EOF, "", null, 2)
                )),
                Arguments.arguments("\"string\"", Arrays.asList(
                        new Token(STRING, "\"string\"", "string", 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("123", Arrays.asList(
                        new Token(NUMBER, "123", 123.0, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("3.14159", Arrays.asList(
                        new Token(NUMBER, "3.14159", 3.14159, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("test", Arrays.asList(
                        new Token(IDENTIFIER, "test", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("and", Arrays.asList(
                        new Token(AND, "and", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("class", Arrays.asList(
                        new Token(CLASS, "class", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("else", Arrays.asList(
                        new Token(ELSE, "else", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("false", Arrays.asList(
                        new Token(FALSE, "false", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("for", Arrays.asList(
                        new Token(FOR, "for", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("fun", Arrays.asList(
                        new Token(FUN, "fun", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("if", Arrays.asList(
                        new Token(IF, "if", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("nil", Arrays.asList(
                        new Token(NIL, "nil", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("or", Arrays.asList(
                        new Token(OR, "or", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("print", Arrays.asList(
                        new Token(PRINT, "print", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("return", Arrays.asList(
                        new Token(RETURN, "return", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("super", Arrays.asList(
                        new Token(SUPER, "super", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("this", Arrays.asList(
                        new Token(THIS, "this", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("true", Arrays.asList(
                        new Token(TRUE, "true", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("var", Arrays.asList(
                        new Token(VAR, "var", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("while", Arrays.asList(
                        new Token(WHILE, "while", null, 1),
                        new Token(EOF, "", null, 1)
                )),
                Arguments.arguments("var pi = 3.14", Arrays.asList(
                        new Token(VAR, "var", null, 1),
                        new Token(IDENTIFIER, "pi", null, 1),
                        new Token(EQUAL, "=", null, 1),
                        new Token(NUMBER, "3.14", 3.14, 1),
                        new Token(EOF, "", null, 1)
                ))
        );
    }
}
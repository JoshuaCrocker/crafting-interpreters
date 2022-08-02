package io.crocker.jlox;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static io.crocker.jlox.TokenType.*;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
        @ParameterizedTest(name = "#{index} Input String: {0}")
        @MethodSource("parseProvider")
        void parse(List<Token> tokens, String want) {
            Parser parser = new Parser(tokens);
            Expr expression = parser.parse();
            assertEquals(want, new ASTPrinter().print(expression));
        }

        static Stream<Arguments> parseProvider() {
            return Stream.of(
                    Arguments.arguments(
                            List.of(new Token(NUMBER, "2", 2.0, 1), new Token(EOF, "", null, 1)),
                            "2.0"
                    ),
                    Arguments.arguments(
                            List.of(
                                    new Token(LEFT_PAREN, "(", null, 1),
                                    new Token(NUMBER, "2", 2.0, 1),
new Token(PLUS, "+", null, 1),
                                    new Token(NUMBER, "4", 4.0, 1),
                                    new Token(RIGHT_PAREN, ")", null, 1),
                                    new Token(EOF, "", null, 1)
                            ),
                            "(group (+ 2.0 4.0))"
                    )
            );
        }
}
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

    // TODO
    // 
    //    @ParameterizedTest(name = "#{index} Input String: {0}")
    //    @MethodSource("parseProvider")
    //    void parse(List<Token> tokens, Expr wantExpression) {
    //        Parser parser = new Parser(tokens);
    //        Expr expression = parser.parse();
    //        assertEquals(wantExpression, expression);
    //    }
    //
    //    static Stream<Arguments> parseProvider() {
    //        return Stream.of(
    //                Arguments.arguments(
    //                        List.of(new Token(NUMBER, "2", 2.0, 1), new Token(EOF, "", null, 1)),
    //                        new Expr.Literal(2.0)
    //                )
    //        );
    //    }
}
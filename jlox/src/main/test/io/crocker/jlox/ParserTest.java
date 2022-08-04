package io.crocker.jlox;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static io.crocker.jlox.TokenType.*;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @ParameterizedTest(name = "#{index} Input String: {0}")
    @MethodSource("parseProvider")
    void parse(String filename, String want) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
        byte[] bytes = is.readAllBytes();
        Scanner scanner = new Scanner(new String(bytes, Charset.defaultCharset()));
        Parser parser = new Parser(scanner.scanTokens());
        List<Stmt> statements = parser.parse();
        assertEquals(want, new ASTPrinter().print(statements));
    }

    static Stream<Arguments> parseProvider() {
        return Stream.of(
                Arguments.arguments(
                        "parser-test/1_variables.lox",
                        "(program\n" +
                                "\t(var a 2.0)\n" +
                                ")"
                ),
                Arguments.arguments(
                        "parser-test/2_scope.lox",
                        "(program\n" +
                                "\t(var a 2.0)\n" +
                                "\t(var b 3.0)\n" +
                                "\t(block\n" +
                                "\t\t(var a 4.0)\n" +
                                "\t\t(print (+ a b))\n" +
                                "\t)\n" +
                                "\t(print (+ a b))\n" +
                                ")"
                )
        );
    }
}
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
        // TODO fix indentation
        // TODO fix indentation
        return Stream.of(
                Arguments.arguments(
                        "parser-test/1_variables.lox",
                        """
                                (program
                                	(var a 2.0)
                                )"""
                ),
                Arguments.arguments(
                        "parser-test/2_scope.lox",
                        """
                                (program
                                	(var a 2.0)
                                	(var b 3.0)
                                	(block
                                		(var a 4.0)
                                		(print (+ a b))
                                	)
                                	(print (+ a b))
                                )"""
                ),
                Arguments.arguments(
                        "parser-test/3_if.lox",
                        """
                                (program
                                	((if (< 3.0 5.0))
                                	(block
                                		(print true)
                                	)
                                	(block
                                		(print false)
                                	)
                                )
                                )"""
                ),
                Arguments.arguments(
                        "parser-test/4_while.lox",
                        """
                                (program
                                	(var i 0.0)
                                	((while (< i 10.0))
                                	(block
                                		(print (+ i 1.0))
                                		(expr (assign IDENTIFIER i null (+ i 1.0)))
                                	)
                                )
                                )"""
                ),
                Arguments.arguments(
                        "parser-test/5_for.lox",
                        """
                                (program
                                	(block
                                		(var i 0.0)
                                		((while (< i 10.0))
                                		(block
                                			(block
                                				(print (+ i 1.0))
                                			)
                                			(expr (assign IDENTIFIER i null (+ i 1.0)))
                                		)
                                	)
                                	)
                                )"""
                )
        );
    }
}
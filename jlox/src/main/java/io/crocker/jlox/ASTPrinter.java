package io.crocker.jlox;

import java.util.List;

public class ASTPrinter implements Expr.Visitor<String>, Stmt.Visitor<String> {
    private static int depth = 1;
    String print(List<Stmt> statements) {
        depth = 1;
        return parenthesize("program", statements.toArray(new Stmt[0]));
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        return parenthesize("assign " + expr.name, expr.value);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
         return null;
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return expr.name.lexeme;
    }

    @Override
    public String visitBlockStmt(Stmt.Block stmt) {
        depth++;
        String output = parenthesize("block", stmt.statements.toArray(new Stmt[0]));
        depth--;
        return output;
    }

    @Override
    public String visitExpressionStmt(Stmt.Expression stmt) {
        return parenthesize("expr", stmt.expression);
    }

    @Override
    public String visitIfStmt(Stmt.If stmt) {
        return null;
    }

    @Override
    public String visitPrintStmt(Stmt.Print stmt) {
        return parenthesize("print", stmt.expression);
    }

    @Override
    public String visitWhileStmt(Stmt.While stmt) {
        return null;
    }

    @Override
    public String visitVarStmt(Stmt.Var stmt) {
        return parenthesize("var " + stmt.name.lexeme, stmt.initializer);
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    private String parenthesize(String name, Stmt... stmts) {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(name).append("\n");
        for (Stmt stmt : stmts) {
            builder.append("\t".repeat(Math.max(0, depth)));
            builder.append(stmt.accept(this));
            builder.append("\n");
        }
        builder.append("\t".repeat(Math.max(0, depth-1)));
        builder.append(")");

        return builder.toString();
    }
}

package io.crocker.jlox;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.List;

public class ASTGrapher implements Expr.Visitor<String[]>, Stmt.Visitor<String[]> {
    private Graph graph;
    private String rootNodeID;

    public ASTGrapher(String name) {
        this.graph = new SingleGraph(name);
        this.graph.setAutoCreate(true);
        this.rootNodeID = toID(name);
        this.graph.addNode(this.rootNodeID);
    }

    public void run(Stmt[] statements) {
        for (Stmt statement : statements) {
            String[] ids = statement.accept(this);
            for (String id : ids) {
                this.graph.addEdge(this.rootNodeID + "_" + id, this.rootNodeID, id);
            }
        }
    }

    private String toID(String name) {
        return name.toLowerCase().replace(" ", "-");
    }

    @Override
    public String[] visitAssignExpr(Expr.Assign expr) {
        
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitBinaryExpr(Expr.Binary expr) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitCallExpr(Expr.Call expr) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitGroupingExpr(Expr.Grouping expr) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitLiteralExpr(Expr.Literal expr) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitLogicalExpr(Expr.Logical expr) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitUnaryExpr(Expr.Unary expr) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitVariableExpr(Expr.Variable expr) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitBlockStmt(Stmt.Block stmt) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitExpressionStmt(Stmt.Expression stmt) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitFunctionStmt(Stmt.Function stmt) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitIfStmt(Stmt.If stmt) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitPrintStmt(Stmt.Print stmt) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitReturnStmt(Stmt.Return stmt) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitWhileStmt(Stmt.While stmt) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }

    @Override
    public String[] visitVarStmt(Stmt.Var stmt) {
        List<String> ids = new ArrayList<String>();
        return ids.toArray(new String[0]);
    }
}

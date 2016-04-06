// Generated from GMLDot.g4 by ANTLR 4.5

        import java.util.HashMap;
        import java.util.ArrayList;
        import java.util.Set;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GMLDotParser}.
 */
public interface GMLDotListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(GMLDotParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(GMLDotParser.RContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#graph}.
	 * @param ctx the parse tree
	 */
	void enterGraph(GMLDotParser.GraphContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#graph}.
	 * @param ctx the parse tree
	 */
	void exitGraph(GMLDotParser.GraphContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(GMLDotParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(GMLDotParser.NodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#edge}.
	 * @param ctx the parse tree
	 */
	void enterEdge(GMLDotParser.EdgeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#edge}.
	 * @param ctx the parse tree
	 */
	void exitEdge(GMLDotParser.EdgeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#graphAttrDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGraphAttrDeclaration(GMLDotParser.GraphAttrDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#graphAttrDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGraphAttrDeclaration(GMLDotParser.GraphAttrDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#nodeAttrDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterNodeAttrDeclarations(GMLDotParser.NodeAttrDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#nodeAttrDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitNodeAttrDeclarations(GMLDotParser.NodeAttrDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#edgeAttrDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterEdgeAttrDeclarations(GMLDotParser.EdgeAttrDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#edgeAttrDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitEdgeAttrDeclarations(GMLDotParser.EdgeAttrDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#nodeAttrDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNodeAttrDeclaration(GMLDotParser.NodeAttrDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#nodeAttrDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNodeAttrDeclaration(GMLDotParser.NodeAttrDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#edgeAttrDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEdgeAttrDeclaration(GMLDotParser.EdgeAttrDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#edgeAttrDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEdgeAttrDeclaration(GMLDotParser.EdgeAttrDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#graphAttribute}.
	 * @param ctx the parse tree
	 */
	void enterGraphAttribute(GMLDotParser.GraphAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#graphAttribute}.
	 * @param ctx the parse tree
	 */
	void exitGraphAttribute(GMLDotParser.GraphAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#nodeAttribute}.
	 * @param ctx the parse tree
	 */
	void enterNodeAttribute(GMLDotParser.NodeAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#nodeAttribute}.
	 * @param ctx the parse tree
	 */
	void exitNodeAttribute(GMLDotParser.NodeAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#edgeAttribute}.
	 * @param ctx the parse tree
	 */
	void enterEdgeAttribute(GMLDotParser.EdgeAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#edgeAttribute}.
	 * @param ctx the parse tree
	 */
	void exitEdgeAttribute(GMLDotParser.EdgeAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#nonTranslatableAttribute}.
	 * @param ctx the parse tree
	 */
	void enterNonTranslatableAttribute(GMLDotParser.NonTranslatableAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#nonTranslatableAttribute}.
	 * @param ctx the parse tree
	 */
	void exitNonTranslatableAttribute(GMLDotParser.NonTranslatableAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GMLDotParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(GMLDotParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GMLDotParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(GMLDotParser.SectionContext ctx);
}
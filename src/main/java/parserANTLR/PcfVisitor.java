package parserANTLR;// Generated from /home/jperezr/Documents/LOGIN/Compilation et Interpretation/TP - Interpreter/src/Pcf.g4 by ANTLR 4.13.2

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PcfParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PcfVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code App}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApp(PcfParser.AppContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(PcfParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(PcfParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Fix}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFix(PcfParser.FixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Constant}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(PcfParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryOperation}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperation(PcfParser.BinaryOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Let}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet(PcfParser.LetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfZero}
	 * labeled alternative in {@link PcfParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfZero(PcfParser.IfZeroContext ctx);
}
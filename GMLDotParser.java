// Generated from GMLDot.g4 by ANTLR 4.5

        import java.util.HashMap;
        import java.util.ArrayList;
        import java.util.Set;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GMLDotParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, VALUE=14, NUMBER=15, INT=16, WORD=17, 
		CHAR=18, STRING=19, WS=20;
	public static final int
		RULE_r = 0, RULE_graph = 1, RULE_node = 2, RULE_edge = 3, RULE_graphAttrDeclaration = 4, 
		RULE_nodeAttrDeclarations = 5, RULE_edgeAttrDeclarations = 6, RULE_nodeAttrDeclaration = 7, 
		RULE_edgeAttrDeclaration = 8, RULE_graphAttribute = 9, RULE_nodeAttribute = 10, 
		RULE_edgeAttribute = 11, RULE_nonTranslatableAttribute = 12, RULE_section = 13;
	public static final String[] ruleNames = {
		"r", "graph", "node", "edge", "graphAttrDeclaration", "nodeAttrDeclarations", 
		"edgeAttrDeclarations", "nodeAttrDeclaration", "edgeAttrDeclaration", 
		"graphAttribute", "nodeAttribute", "edgeAttribute", "nonTranslatableAttribute", 
		"section"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'graph'", "'['", "']'", "'node'", "'edge'", "'label'", "'directed'", 
		"'comment'", "'URL'", "'id'", "'name'", "'source'", "'target'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "VALUE", "NUMBER", "INT", "WORD", "CHAR", "STRING", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "GMLDot.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	        
	        class Node {
	                private HashMap<String, String> attributes;
	                
	                public Node(HashMap<String, String> attributes){
	                        this.attributes = attributes;
	                }
	        }
	        
	        
	        class Edge {
	                private HashMap<String, String> attributes;
	                
	                public Edge(HashMap<String, String> attributes){
	                        this.attributes = attributes;
	                }
	        }
	        
	        
	        class Graph {
	                private ArrayList<Node> nodes;
	                private ArrayList<Edge> edges;
	                private HashMap<String, String> attributes;
	                
	                public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges,
	                         HashMap<String, String> attributes){
	                        this.nodes = nodes;
	                        this.edges = edges;
	                        this.attributes = new HashMap<String, String>();
	                        this.attributes.put("directed", "0");
	                        this.attributes.putAll(attributes);
	                }

	                
	                private void toDot(){
	                        
	                        boolean directed = this.attributes.get("directed").equals("1");
	                        if (directed){
	                                System.out.println("digraph G {");
	                        }   
	                        else{
	                                System.out.println("graph G {");
	                        }   

	                        this.attributes.remove("directed");
	                        
	                        
	                        Set<String> keys = attributes.keySet();
	                        for(String key: keys){
	                                System.out.println("\t" + key + " = " + attributes.get(key) + ";");
	                        }   
	                        
	                        
	                        for (Node node: nodes){
	                                String nodeString = "\t" + node.attributes.get("id") + " ["; 
	                                node.attributes.remove("id");
	                                keys = node.attributes.keySet();  //get all keys
	                                for(String key: keys)
	                                {   
	                                        nodeString +=  key + " = " + node.attributes.get(key) + ", ";
	                                }   
	                                if (keys.size() > 0)
	                                        nodeString = nodeString.substring(0, nodeString.length()-2);
	                                nodeString += "];\n";
	                                System.out.print(nodeString);
	                        }
	                        
	                        
	                        for (Edge edge: edges){
	                                String edgeString;
	                                if (directed) {
	                                        edgeString = "\t" + edge.attributes.get("source")
	                                                + " -> " + edge.attributes.get("target") + " [";
	                                }
	                                else {
	                                        edgeString = "\t" + edge.attributes.get("source")
	                                                + " -- " + edge.attributes.get("target") + " [";
	                                }
	                                edge.attributes.remove("source");
	                                edge.attributes.remove("target");
	                                keys = edge.attributes.keySet();  //get all keys
	                                for(String key: keys)
	                                {
	                                        edgeString +=  key + " = " + edge.attributes.get(key) + ", ";
	                                }
	                                if (keys.size() > 0)
	                                        edgeString = edgeString.substring(0, edgeString.length()-2);
	                                edgeString += "];\n";
	                                System.out.print(edgeString);
	                        }
	                        
	                        System.out.println("}");
	                }
	                        
	        }
	        

	public GMLDotParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RContext extends ParserRuleContext {
		public GraphContext graph() {
			return getRuleContext(GraphContext.class,0);
		}
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitR(this);
		}
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_r);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			graph();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GraphContext extends ParserRuleContext {
		public NodeContext node;
		public EdgeContext edge;
		public GraphAttrDeclarationContext graphAttrDeclaration;
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public List<EdgeContext> edge() {
			return getRuleContexts(EdgeContext.class);
		}
		public EdgeContext edge(int i) {
			return getRuleContext(EdgeContext.class,i);
		}
		public List<GraphAttrDeclarationContext> graphAttrDeclaration() {
			return getRuleContexts(GraphAttrDeclarationContext.class);
		}
		public GraphAttrDeclarationContext graphAttrDeclaration(int i) {
			return getRuleContext(GraphAttrDeclarationContext.class,i);
		}
		public GraphContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graph; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterGraph(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitGraph(this);
		}
	}

	public final GraphContext graph() throws RecognitionException {
		GraphContext _localctx = new GraphContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_graph);
		ArrayList<Node> nodes = new ArrayList<Node>(); 
		        ArrayList<Edge> edges = new ArrayList<Edge>(); 
		        HashMap<String, String> declarations = new HashMap<String, String>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(T__0);
			setState(31);
			match(T__1);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << WORD))) != 0)) {
				{
				setState(41);
				switch (_input.LA(1)) {
				case T__3:
					{
					setState(32);
					((GraphContext)_localctx).node = node();

					                nodes.add(((GraphContext)_localctx).node._node);
					        
					}
					break;
				case T__4:
					{
					setState(35);
					((GraphContext)_localctx).edge = edge();

					                edges.add(((GraphContext)_localctx).edge._edge);
					        
					}
					break;
				case T__5:
				case T__6:
				case T__7:
				case T__8:
				case WORD:
					{
					setState(38);
					((GraphContext)_localctx).graphAttrDeclaration = graphAttrDeclaration();

					                declarations.putAll(((GraphContext)_localctx).graphAttrDeclaration.declaration);
					        
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
			match(T__2);

			        Graph graph = new Graph(nodes, edges, declarations);
			        graph.toDot();

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeContext extends ParserRuleContext {
		public Node _node;
		public NodeAttrDeclarationsContext nodeAttrDeclarations;
		public NodeAttrDeclarationsContext nodeAttrDeclarations() {
			return getRuleContext(NodeAttrDeclarationsContext.class,0);
		}
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitNode(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_node);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(T__3);
			setState(50);
			match(T__1);
			setState(51);
			((NodeContext)_localctx).nodeAttrDeclarations = nodeAttrDeclarations();
			setState(52);
			match(T__2);

			        ((NodeContext)_localctx)._node =  new Node(((NodeContext)_localctx).nodeAttrDeclarations.declarations);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EdgeContext extends ParserRuleContext {
		public Edge _edge;
		public EdgeAttrDeclarationsContext edgeAttrDeclarations;
		public EdgeAttrDeclarationsContext edgeAttrDeclarations() {
			return getRuleContext(EdgeAttrDeclarationsContext.class,0);
		}
		public EdgeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_edge; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterEdge(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitEdge(this);
		}
	}

	public final EdgeContext edge() throws RecognitionException {
		EdgeContext _localctx = new EdgeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_edge);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(T__4);
			setState(56);
			match(T__1);
			setState(57);
			((EdgeContext)_localctx).edgeAttrDeclarations = edgeAttrDeclarations();
			setState(58);
			match(T__2);

			        ((EdgeContext)_localctx)._edge =  new Edge(((EdgeContext)_localctx).edgeAttrDeclarations.declarations);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GraphAttrDeclarationContext extends ParserRuleContext {
		public HashMap<String, String> declaration;
		public GraphAttributeContext graphAttribute;
		public Token VALUE;
		public NonTranslatableAttributeContext nonTranslatableAttribute;
		public GraphAttributeContext graphAttribute() {
			return getRuleContext(GraphAttributeContext.class,0);
		}
		public TerminalNode VALUE() { return getToken(GMLDotParser.VALUE, 0); }
		public NonTranslatableAttributeContext nonTranslatableAttribute() {
			return getRuleContext(NonTranslatableAttributeContext.class,0);
		}
		public SectionContext section() {
			return getRuleContext(SectionContext.class,0);
		}
		public GraphAttrDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphAttrDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterGraphAttrDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitGraphAttrDeclaration(this);
		}
	}

	public final GraphAttrDeclarationContext graphAttrDeclaration() throws RecognitionException {
		GraphAttrDeclarationContext _localctx = new GraphAttrDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_graphAttrDeclaration);
		((GraphAttrDeclarationContext)_localctx).declaration =  new HashMap<String, String>();
		try {
			setState(73);
			switch (_input.LA(1)) {
			case T__5:
			case T__6:
			case T__7:
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(61);
				((GraphAttrDeclarationContext)_localctx).graphAttribute = graphAttribute();
				setState(62);
				((GraphAttrDeclarationContext)_localctx).VALUE = match(VALUE);
				}

				        _localctx.declaration.put((((GraphAttrDeclarationContext)_localctx).graphAttribute!=null?_input.getText(((GraphAttrDeclarationContext)_localctx).graphAttribute.start,((GraphAttrDeclarationContext)_localctx).graphAttribute.stop):null), (((GraphAttrDeclarationContext)_localctx).VALUE!=null?((GraphAttrDeclarationContext)_localctx).VALUE.getText():null));

				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(66);
				((GraphAttrDeclarationContext)_localctx).nonTranslatableAttribute = nonTranslatableAttribute();
				setState(69);
				switch (_input.LA(1)) {
				case VALUE:
					{
					setState(67);
					match(VALUE);
					}
					break;
				case T__1:
					{
					setState(68);
					section();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}

				        System.out.printf("//Warning: the graph attribute \"%s\" was parsed but is not "
				                + "supported by DOT,so it will not be translated.\n", (((GraphAttrDeclarationContext)_localctx).nonTranslatableAttribute!=null?_input.getText(((GraphAttrDeclarationContext)_localctx).nonTranslatableAttribute.start,((GraphAttrDeclarationContext)_localctx).nonTranslatableAttribute.stop):null));

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeAttrDeclarationsContext extends ParserRuleContext {
		public HashMap<String, String> declarations;
		public NodeAttrDeclarationContext nodeAttrDeclaration;
		public List<NodeAttrDeclarationContext> nodeAttrDeclaration() {
			return getRuleContexts(NodeAttrDeclarationContext.class);
		}
		public NodeAttrDeclarationContext nodeAttrDeclaration(int i) {
			return getRuleContext(NodeAttrDeclarationContext.class,i);
		}
		public NodeAttrDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeAttrDeclarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterNodeAttrDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitNodeAttrDeclarations(this);
		}
	}

	public final NodeAttrDeclarationsContext nodeAttrDeclarations() throws RecognitionException {
		NodeAttrDeclarationsContext _localctx = new NodeAttrDeclarationsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_nodeAttrDeclarations);
		 ((NodeAttrDeclarationsContext)_localctx).declarations =  new HashMap<String, String>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << WORD))) != 0)) {
				{
				{
				setState(75);
				((NodeAttrDeclarationsContext)_localctx).nodeAttrDeclaration = nodeAttrDeclaration();
				_localctx.declarations.putAll(((NodeAttrDeclarationsContext)_localctx).nodeAttrDeclaration.declaration);
				}
				}
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EdgeAttrDeclarationsContext extends ParserRuleContext {
		public HashMap<String, String> declarations;
		public EdgeAttrDeclarationContext edgeAttrDeclaration;
		public List<EdgeAttrDeclarationContext> edgeAttrDeclaration() {
			return getRuleContexts(EdgeAttrDeclarationContext.class);
		}
		public EdgeAttrDeclarationContext edgeAttrDeclaration(int i) {
			return getRuleContext(EdgeAttrDeclarationContext.class,i);
		}
		public EdgeAttrDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_edgeAttrDeclarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterEdgeAttrDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitEdgeAttrDeclarations(this);
		}
	}

	public final EdgeAttrDeclarationsContext edgeAttrDeclarations() throws RecognitionException {
		EdgeAttrDeclarationsContext _localctx = new EdgeAttrDeclarationsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_edgeAttrDeclarations);
		 ((EdgeAttrDeclarationsContext)_localctx).declarations =  new HashMap<String, String>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << WORD))) != 0)) {
				{
				{
				setState(83);
				((EdgeAttrDeclarationsContext)_localctx).edgeAttrDeclaration = edgeAttrDeclaration();
				_localctx.declarations.putAll(((EdgeAttrDeclarationsContext)_localctx).edgeAttrDeclaration.declaration);
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeAttrDeclarationContext extends ParserRuleContext {
		public HashMap<String, String> declaration;
		public NodeAttributeContext nodeAttribute;
		public Token VALUE;
		public NonTranslatableAttributeContext nonTranslatableAttribute;
		public NodeAttributeContext nodeAttribute() {
			return getRuleContext(NodeAttributeContext.class,0);
		}
		public TerminalNode VALUE() { return getToken(GMLDotParser.VALUE, 0); }
		public NonTranslatableAttributeContext nonTranslatableAttribute() {
			return getRuleContext(NonTranslatableAttributeContext.class,0);
		}
		public SectionContext section() {
			return getRuleContext(SectionContext.class,0);
		}
		public NodeAttrDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeAttrDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterNodeAttrDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitNodeAttrDeclaration(this);
		}
	}

	public final NodeAttrDeclarationContext nodeAttrDeclaration() throws RecognitionException {
		NodeAttrDeclarationContext _localctx = new NodeAttrDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_nodeAttrDeclaration);
		 ((NodeAttrDeclarationContext)_localctx).declaration =  new HashMap<String, String>(); 
		try {
			setState(103);
			switch (_input.LA(1)) {
			case T__5:
			case T__7:
			case T__9:
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(91);
				((NodeAttrDeclarationContext)_localctx).nodeAttribute = nodeAttribute();
				setState(92);
				((NodeAttrDeclarationContext)_localctx).VALUE = match(VALUE);
				}

				        _localctx.declaration.put((((NodeAttrDeclarationContext)_localctx).nodeAttribute!=null?_input.getText(((NodeAttrDeclarationContext)_localctx).nodeAttribute.start,((NodeAttrDeclarationContext)_localctx).nodeAttribute.stop):null), (((NodeAttrDeclarationContext)_localctx).VALUE!=null?((NodeAttrDeclarationContext)_localctx).VALUE.getText():null));

				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(96);
				((NodeAttrDeclarationContext)_localctx).nonTranslatableAttribute = nonTranslatableAttribute();
				setState(99);
				switch (_input.LA(1)) {
				case VALUE:
					{
					setState(97);
					match(VALUE);
					}
					break;
				case T__1:
					{
					setState(98);
					section();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}

				        System.out.printf("//Warning: the node attribute \"%s\" was parsed but is not"
				                + " supported by DOT,so it will not be translated.\n", (((NodeAttrDeclarationContext)_localctx).nonTranslatableAttribute!=null?_input.getText(((NodeAttrDeclarationContext)_localctx).nonTranslatableAttribute.start,((NodeAttrDeclarationContext)_localctx).nonTranslatableAttribute.stop):null));

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EdgeAttrDeclarationContext extends ParserRuleContext {
		public HashMap<String, String> declaration;
		public EdgeAttributeContext edgeAttribute;
		public Token VALUE;
		public NonTranslatableAttributeContext nonTranslatableAttribute;
		public EdgeAttributeContext edgeAttribute() {
			return getRuleContext(EdgeAttributeContext.class,0);
		}
		public TerminalNode VALUE() { return getToken(GMLDotParser.VALUE, 0); }
		public NonTranslatableAttributeContext nonTranslatableAttribute() {
			return getRuleContext(NonTranslatableAttributeContext.class,0);
		}
		public SectionContext section() {
			return getRuleContext(SectionContext.class,0);
		}
		public EdgeAttrDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_edgeAttrDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterEdgeAttrDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitEdgeAttrDeclaration(this);
		}
	}

	public final EdgeAttrDeclarationContext edgeAttrDeclaration() throws RecognitionException {
		EdgeAttrDeclarationContext _localctx = new EdgeAttrDeclarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_edgeAttrDeclaration);
		 ((EdgeAttrDeclarationContext)_localctx).declaration =  new HashMap<String, String>(); 
		try {
			setState(117);
			switch (_input.LA(1)) {
			case T__5:
			case T__7:
			case T__11:
			case T__12:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(105);
				((EdgeAttrDeclarationContext)_localctx).edgeAttribute = edgeAttribute();
				setState(106);
				((EdgeAttrDeclarationContext)_localctx).VALUE = match(VALUE);
				}

				        _localctx.declaration.put((((EdgeAttrDeclarationContext)_localctx).edgeAttribute!=null?_input.getText(((EdgeAttrDeclarationContext)_localctx).edgeAttribute.start,((EdgeAttrDeclarationContext)_localctx).edgeAttribute.stop):null), (((EdgeAttrDeclarationContext)_localctx).VALUE!=null?((EdgeAttrDeclarationContext)_localctx).VALUE.getText():null));

				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(110);
				((EdgeAttrDeclarationContext)_localctx).nonTranslatableAttribute = nonTranslatableAttribute();
				setState(113);
				switch (_input.LA(1)) {
				case VALUE:
					{
					setState(111);
					match(VALUE);
					}
					break;
				case T__1:
					{
					setState(112);
					section();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}

				        System.out.printf("//Warning: the edge attribute \"%s\" was parsed but is not"
				                + " supported by DOT,so it will not be translated.\n", (((EdgeAttrDeclarationContext)_localctx).nonTranslatableAttribute!=null?_input.getText(((EdgeAttrDeclarationContext)_localctx).nonTranslatableAttribute.start,((EdgeAttrDeclarationContext)_localctx).nonTranslatableAttribute.stop):null));

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GraphAttributeContext extends ParserRuleContext {
		public GraphAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterGraphAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitGraphAttribute(this);
		}
	}

	public final GraphAttributeContext graphAttribute() throws RecognitionException {
		GraphAttributeContext _localctx = new GraphAttributeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_graphAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeAttributeContext extends ParserRuleContext {
		public NodeAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterNodeAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitNodeAttribute(this);
		}
	}

	public final NodeAttributeContext nodeAttribute() throws RecognitionException {
		NodeAttributeContext _localctx = new NodeAttributeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_nodeAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__9) | (1L << T__10))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EdgeAttributeContext extends ParserRuleContext {
		public EdgeAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_edgeAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterEdgeAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitEdgeAttribute(this);
		}
	}

	public final EdgeAttributeContext edgeAttribute() throws RecognitionException {
		EdgeAttributeContext _localctx = new EdgeAttributeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_edgeAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__11) | (1L << T__12))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonTranslatableAttributeContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(GMLDotParser.WORD, 0); }
		public NonTranslatableAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonTranslatableAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterNonTranslatableAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitNonTranslatableAttribute(this);
		}
	}

	public final NonTranslatableAttributeContext nonTranslatableAttribute() throws RecognitionException {
		NonTranslatableAttributeContext _localctx = new NonTranslatableAttributeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_nonTranslatableAttribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SectionContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(GMLDotParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GMLDotParser.WORD, i);
		}
		public List<TerminalNode> VALUE() { return getTokens(GMLDotParser.VALUE); }
		public TerminalNode VALUE(int i) {
			return getToken(GMLDotParser.VALUE, i);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GMLDotListener ) ((GMLDotListener)listener).exitSection(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_section);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__1);
			setState(132);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(128);
					match(WORD);
					setState(129);
					match(VALUE);
					}
					} 
				}
				setState(134);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(139);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(135);
					match(WORD);
					setState(136);
					section();
					}
					} 
				}
				setState(141);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WORD) {
				{
				{
				setState(142);
				match(WORD);
				setState(143);
				match(VALUE);
				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(149);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\26\u009a\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\7\3,\n\3\f\3\16\3/\13\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\5\6H\n\6\3\6\3\6\5\6L\n\6\3\7\3\7\3\7\7\7Q\n\7\f\7\16\7T\13\7\3\b\3\b"+
		"\3\b\7\bY\n\b\f\b\16\b\\\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tf\n\t"+
		"\3\t\3\t\5\tj\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nt\n\n\3\n\3\n\5\n"+
		"x\n\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\7\17\u0085\n"+
		"\17\f\17\16\17\u0088\13\17\3\17\3\17\7\17\u008c\n\17\f\17\16\17\u008f"+
		"\13\17\3\17\3\17\7\17\u0093\n\17\f\17\16\17\u0096\13\17\3\17\3\17\3\17"+
		"\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\5\3\2\b\13\5\2\b\b\n\n\f"+
		"\r\5\2\b\b\n\n\16\17\u0099\2\36\3\2\2\2\4 \3\2\2\2\6\63\3\2\2\2\b9\3\2"+
		"\2\2\nK\3\2\2\2\fR\3\2\2\2\16Z\3\2\2\2\20i\3\2\2\2\22w\3\2\2\2\24y\3\2"+
		"\2\2\26{\3\2\2\2\30}\3\2\2\2\32\177\3\2\2\2\34\u0081\3\2\2\2\36\37\5\4"+
		"\3\2\37\3\3\2\2\2 !\7\3\2\2!-\7\4\2\2\"#\5\6\4\2#$\b\3\1\2$,\3\2\2\2%"+
		"&\5\b\5\2&\'\b\3\1\2\',\3\2\2\2()\5\n\6\2)*\b\3\1\2*,\3\2\2\2+\"\3\2\2"+
		"\2+%\3\2\2\2+(\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/-\3\2"+
		"\2\2\60\61\7\5\2\2\61\62\b\3\1\2\62\5\3\2\2\2\63\64\7\6\2\2\64\65\7\4"+
		"\2\2\65\66\5\f\7\2\66\67\7\5\2\2\678\b\4\1\28\7\3\2\2\29:\7\7\2\2:;\7"+
		"\4\2\2;<\5\16\b\2<=\7\5\2\2=>\b\5\1\2>\t\3\2\2\2?@\5\24\13\2@A\7\20\2"+
		"\2AB\3\2\2\2BC\b\6\1\2CL\3\2\2\2DG\5\32\16\2EH\7\20\2\2FH\5\34\17\2GE"+
		"\3\2\2\2GF\3\2\2\2HI\3\2\2\2IJ\b\6\1\2JL\3\2\2\2K?\3\2\2\2KD\3\2\2\2L"+
		"\13\3\2\2\2MN\5\20\t\2NO\b\7\1\2OQ\3\2\2\2PM\3\2\2\2QT\3\2\2\2RP\3\2\2"+
		"\2RS\3\2\2\2S\r\3\2\2\2TR\3\2\2\2UV\5\22\n\2VW\b\b\1\2WY\3\2\2\2XU\3\2"+
		"\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\17\3\2\2\2\\Z\3\2\2\2]^\5\26\f\2"+
		"^_\7\20\2\2_`\3\2\2\2`a\b\t\1\2aj\3\2\2\2be\5\32\16\2cf\7\20\2\2df\5\34"+
		"\17\2ec\3\2\2\2ed\3\2\2\2fg\3\2\2\2gh\b\t\1\2hj\3\2\2\2i]\3\2\2\2ib\3"+
		"\2\2\2j\21\3\2\2\2kl\5\30\r\2lm\7\20\2\2mn\3\2\2\2no\b\n\1\2ox\3\2\2\2"+
		"ps\5\32\16\2qt\7\20\2\2rt\5\34\17\2sq\3\2\2\2sr\3\2\2\2tu\3\2\2\2uv\b"+
		"\n\1\2vx\3\2\2\2wk\3\2\2\2wp\3\2\2\2x\23\3\2\2\2yz\t\2\2\2z\25\3\2\2\2"+
		"{|\t\3\2\2|\27\3\2\2\2}~\t\4\2\2~\31\3\2\2\2\177\u0080\7\23\2\2\u0080"+
		"\33\3\2\2\2\u0081\u0086\7\4\2\2\u0082\u0083\7\23\2\2\u0083\u0085\7\20"+
		"\2\2\u0084\u0082\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u008d\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008a\7\23"+
		"\2\2\u008a\u008c\5\34\17\2\u008b\u0089\3\2\2\2\u008c\u008f\3\2\2\2\u008d"+
		"\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0094\3\2\2\2\u008f\u008d\3\2"+
		"\2\2\u0090\u0091\7\23\2\2\u0091\u0093\7\20\2\2\u0092\u0090\3\2\2\2\u0093"+
		"\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\3\2"+
		"\2\2\u0096\u0094\3\2\2\2\u0097\u0098\7\5\2\2\u0098\35\3\2\2\2\17+-GKR"+
		"Zeisw\u0086\u008d\u0094";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
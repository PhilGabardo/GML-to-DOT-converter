\documentclass[a4paper]{report}
\newif\ifshowcode
\showcodetrue
\usepackage{cite}
\usepackage{latexsym}
%\usepackage{html}
\usepackage{lscape}
\usepackage{listings}
\usepackage{graphicx}
\usepackage{color}
\definecolor{linkcolor}{rgb}{0, 0, 0.7}

\usepackage[%
backref,%
raiselinks,%
pdfhighlight=/O,%
pagebackref,%
hyperfigures,%
breaklinks,%
colorlinks,%
pdfpagemode=None,%
pdfstartview=FitBH,%
linkcolor={linkcolor},%
anchorcolor={linkcolor},%
citecolor={linkcolor},%
filecolor={linkcolor},%
menucolor={linkcolor},%
pagecolor={linkcolor},%
urlcolor={linkcolor}%
]{hyperref}

\setlength{\oddsidemargin}{0in}
\setlength{\evensidemargin}{0in}
\setlength{\topmargin}{0in}
\addtolength{\topmargin}{-\headheight}
\addtolength{\topmargin}{-\headsep}
\setlength{\textheight}{8.9in}
\setlength{\textwidth}{6.5in}
\setlength{\marginparwidth}{0.5in}

\title{GML-DOT 1.0 \\ A Graph Translation Tool}
\date{}
\author{Philip Gabardo}
\pagenumbering{roman}

\begin{document}
\maketitle
\tableofcontents

\chapter{Introduction}
\section{Summary}
The proposed project is a compiler that translates graphs represented in Graph Modelling Language (GML) to a visual PDF representation produced through DOT code.

 GML \cite{GML}is a file format standard that is used for representing graphs. GML's key features are portability, simplicity, extensibility and flexibility. The syntax consists of hierarchical key-value lists. Here is an example of a very simple GML file:
 \begin{verbatim}
graph [
	comment "This is a sample graph"
	directed 1
	id n42
	label "Hello, I am a graph"
	node [
		id 1
		label "node 1"
	]
	node [
		id 2
		label "node 2"
	]
	node [
		id 3
		label "node 3"
	]
	edge [
		source 1
		target 2
		label "Edge from node 1 to node 2"
	]
	edge [
		source 2
		target 3
		label "Edge from node 2 to node 3"
	]
	edge [
		source 3
		target 1
		label "Edge from node 3 to node 1"
	]
]
\end{verbatim}

There are several other languages that closely resemble GML, such as GDF\cite{GDF}, GraphML\cite{GRAPHML}, and DOT\cite{DOT}.

GML is widely used, and is the standard file format in the Graphlet graph editor system\cite{GRAPHLET}.

A major drawback of GML is its lack of direct graph translation to png or pdf images, which is essential to users would who like to visualize their graphs. Consequently, many users choose languages such as DOT, which can produce png and pdf images to represent their graphs, despite GML's attempt to become a universal file format for graphs.

 The purpose of this compiler is to give GML users the ability to visualize their graphs through translation to DOT code, which can be used to generate a nicely formatted png file. DOT syntax is very simple, and contains single line statements to declare nodes and edges. The compiler will provide a simple, structure-preserving translation. Here is an example of the DOT code and the visual representation (generated using the DOT code) of the graph described in the sample GML provided earlier:
\begin{verbatim}
graph G {
	label = "My graph";
	comment = "This is a test";
	1 [label = "Node 1"];
	2 [label = "Node 2"];
	3 [label = "Node 3"];
	4 [label = "test"];
	1 -- 2 [label = "Edge from node 1 to node 2"];
	2 -- 3 [label = "Edge from node 2 to node 3"];
	3 -- 1 [label = "Edge from node 3 to node 1"];
}

\end{verbatim} 


 \begin{center}
 \includegraphics[scale=0.5]{test}
 \end{center}
 
\section{Tools}
The following tools were used for this project:
\begin{itemize}
\item \textbf{ANTLR4}\cite{ANTLR4} was be used to generate DOT code from given GML code. ANTLR4 was chosen for two main reasons. First, ANTLR4 is proven to produce correct grammars. The generated parser accepts exactly the language specified in the grammar. If a recursive descent parser was used instead, there would be no immediate proof of correctness, and no easy way to test it. Secondly, ANTLR4 was chosen for its speedy development time. The parser and lexer rules can be constructed extremely fast.
\item \textbf{DOT}\cite{DOT} was used to draw the graphs. DOT was chosen because it is can automatically place nodes and edges such that there is minimal overlap (developing this kind of algorithm is outside the scope of this course). Furthermore, DOT has a command-line tool for producing png and pdf files, which many other graph languages that have automatic placement lack. For example, yWorks\cite{YWORKS} requires the user to import their graphs to an editor in order to view them.
\item \textbf{nuweb}\cite{NUWEB} was used to document the project. nuweb was chosen because it supports any programming language (there are no literate programming tools specificly targetted for ANTLR), it is documented well, and it has plenty of user support through forums. nuweb was chosen over noweb because noweb is heavily Unix-dependent, and requires many third party programs in order to be built.
\end{itemize}

\chapter{Implementation}
The high-level flow of steps for the compiler is as follows:
\begin{enumerate}
\item Parse the GML file to elicit an object based representation of the graphs.
\item Convert the object based representation of the graphs to DOT.
\item Convert the DOT code to png.
\end{enumerate}

This report will focus on the first two steps. The third step is trivial and can executed using the command: \begin{verbatim} dot -Tpng infile.dot -o outfile.png \end{verbatim}. 

The ANTLR compiler is organized as follows: 
@o GMLDot.g4 
@{
@<Grammar Name @>
@<Header @>
@<Members @>
@<Top Level Rule Name@>
@<Graph Rule@>
@<Node Rule@>
@<Edge Rule@>
@<Graph Attribute Declaration Rule@>
@<Node Attribute Declarations Rule@>
@<Edge Attribute Declarations Rule@>
@<Node Attribute Declaration Rule@>
@<Edge Attribute Declaration Rule@>
@<Graph Attribute Rule@>
@<Node Attribute Rule@>
@<Edge Attribute Rule@>
@<Unsupported Attribute Rule@>
@<Section Rule@>
@<Value Rule@>
@<Number Rule@>
@<Integer Rule@>
@<Word Rule@>
@<Character Rule@>
@<String Rule@>
@<Whitespace Rule@>
@}

The following sections will describe, in a detailed, top-down fashion, each of these components. The first section will cover the class definitions that are used to simplify the conversion process and the second section will cover the parsing process.

\section{Class Definitions}

Several non-default Java data structures are used in class definitions.
@d Header
@{
@@header {
	import java.util.HashMap;
	import java.util.ArrayList;
	import java.util.Set;
}
@}


Three classes are defined to simplify the conversion process.
@d Members 
@{
@@members {
	@<Node Class Definition@>
	@<Edge Class Definition@>
	@<Graph Class Definition@>
}
@}

The Node class is used to represent nodes declared in the GML. Each Node object contains a hash map that is used to store the attributes of the node. 
Each attribute name is stored as a key in the map, while each attribute value is stored as a value in the map.
@d Node Class Definition
@{
class Node {
	private HashMap<String, String> attributes;
	
	public Node(HashMap<String, String> attributes){
		this.attributes = attributes;
	}
}
@}

The Edge class is used to represent edges declared in the GML. Objects of the Edge class contain a hash map that is used to store the attributes of the edge. This hash map
is used in the same way as in the Node class.
@d Edge Class Definition
@{
class Edge {
	private HashMap<String, String> attributes;
	
	public Edge(HashMap<String, String> attributes){
		this.attributes = attributes;
	}
}
@}


The Graph class is used to represent the entire graph declared in the GML. A Graph object contains a list of Node objects, a list of Edge objects, and a 
hash map to store attributes of the graph. This hash map is used in the same way as in the Node and Edge classes. A Graph object also has toDot() method.
@d Graph Class Definition
@{
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

	@<toDot Definition@>	
}
@}


The toDot() method converts the object representation of the GML to DOT. 

@d toDot Definition
@{
private void toDot(){
	@<Print Header@>
	@<Print Graph Attributes@>
	@<Print Nodes@>
	@<Print Edges@>
	System.out.println("}");
}
@}

First, the 'directed' attribute is checked to determine the type of the DOT graph (digraph or a graph). The name of the DOT graph is set to G by default. The header (type and name) of the graph is printed to the screen. The 'directed' attribute is then deleted, so that it will not be printed with other graph attributes. 
@d Print Header
@{
boolean directed = this.attributes.get("directed").equals("1");
if (directed){
        System.out.println("digraph G {");
}   
else{
        System.out.println("graph G {");
}   

this.attributes.remove("directed");
@}

Next, the parsed attributes of the graph are printed to the screen in DOT format ($<$ATTRIBUTE$>$ = $<$VALUE$>$). This is easily done by iterating over the keys of the hash map of graph attributes.
@d Print Graph Attributes
@{
Set<String> keys = attributes.keySet();
for(String key: keys){
        System.out.println("\t" + key + " = " + attributes.get(key) + ";");
}   
@}

Then, the parsed nodes are printed in DOT format ($<$NODE\_ID$>$ [$<$ATTRIBUTE\_1$>$ = $<$VALUE\_1$>$, $<$ATTRIBUTE\_2$>$ = $<$VALUE\_2$>$, ..., $<$ATTRIBUTE\_N$>$ = $<$VALUE\_N$>$]). The 'id' attribute is printed first and then deleted from the hash map of attributes, so that it will not be printed with other node attributes. Then, the node attributes are printed. This is easily done by iterating over hash map of node attributes.
@d Print Nodes
@{
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
@}


Finally, the parsed edges are printed in DOT format ($<$SOURCE\_NODE\_ID$>$ (-- if undirected $|$ -$>$ if directed) $<$TARGET\_NODE\_ID$>$ [$<$ATTRIBUTE\_1$>$ = $<$VALUE\_1$>$, $<$ATTRIBUTE\_2$>$ = $<$VALUE\_2$>$, ..., $<$ATTRIBUTE\_N$>$ = $<$VALUE\_N$>$]. The 'source' and 'target' attributes are printed first and then deleted from the hash map of attributes, so that they will not be printed with other edge attributes. Then, the edge attributes are printed. This is done easily by iterating over the hash map of attributes for the edge. 
@d Print Edges
@{
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
@}


\section{Parsing}
The grammar is named GMLDot:
@d Grammar Name
@{
grammar GMLDot;
@}


The top level rule name is ``graph''.
@d Top Level Rule Name
@{
r: graph;
@}



The graph rule is initialized with two empty lists for its nodes and edges and a hashmap for its attributes. The rule recognizes the keyword ``graph'', followed by an opening square bracket, followed by a series of nodes, edges, or graph attribute declarations in no specific order (according to \cite{GML}, ``It (GML) should be flexible enough that a specific order of declarations is not needed''), terminated by a closing square bracket. Whenever a node is parsed, it is added to the list of nodes. Similarly, whenever an edge is parsed, it is added to the list of edges. Whenever a graph attribute is parsed, the attribute name and value is added as a key value pair to the graph attribute hash map. When the terminating square bracket is parsed, a Graph object is created using the objects initialized at the beginning of the rule. The toDot() method is called to print the DOT representation of the graph. 
@d Graph Rule
@{
graph
@@init {ArrayList<Node> nodes = new ArrayList<Node>(); 
	ArrayList<Edge> edges = new ArrayList<Edge>(); 
	HashMap<String, String> declarations = new HashMap<String, String>();}
: 'graph' '['
(
	node
	{
		nodes.add($node._node);
	}
 	|
 	edge
 	{
 		edges.add($edge._edge);
 	}
	|
	graphAttrDeclaration
	{
		declarations.putAll($graphAttrDeclaration.declaration);
	}
		)* 
']'
{
	Graph graph = new Graph(nodes, edges, declarations);
	graph.toDot();
};
@} 

The node rule returns a Node object. The rule recognizes the keyword ``node'' followed by an opening square bracket, followed by a series of node attribute declarations (nodeAttrDeclarations), followed by a terminating square bracket. A Node object is created at the end of the rule, using the declarations that were parsed as the hash map for the constructor.

@d Node Rule
@{
node returns [Node _node]: 'node' '[' nodeAttrDeclarations ']'
{
	$_node = new Node($nodeAttrDeclarations.declarations);
};
@}

The edge rule returns an Edge object. The rule works in a very similar fashion to how the node rule works.

@d Edge Rule
@{
edge returns [Edge _edge]: 'edge' '[' edgeAttrDeclarations ']'
{
	$_edge = new Edge($edgeAttrDeclarations.declarations);
};
@}

The nodeAttrDeclarations rule parses a series of node attribute declarations, and returns a hash map where the keys are attributes and the values are corresponding attribute values. The rule is initialized with an empty hash map. Every time a node attribute declaration (nodeAttrDeclaration) is parsed, its corresponding hash map is merged with the rule's hash map.
@d Node Attribute Declarations Rule
@{
nodeAttrDeclarations returns [HashMap<String, String> declarations]
@@init { $declarations = new HashMap<String, String>(); }
: (nodeAttrDeclaration {$declarations.putAll($nodeAttrDeclaration.declaration);})*;
@}

The edgeAttrDeclarations rule parses a series of edge attribute declarations, and returns a hash map where the keys are attributes and the values are corresponding attribute values. This rule works in a very similar fashion to how the nodeAttrDeclarations rule works.
@d Edge Attribute Declarations Rule
@{
edgeAttrDeclarations returns [HashMap<String, String> declarations]
@@init { $declarations = new HashMap<String, String>(); }
: (edgeAttrDeclaration {$declarations.putAll($edgeAttrDeclaration.declaration);})*;
@}


The graphAttrDeclaration rule parses a graph attribute and returns a hash map. If the attribute is translatable to DOT, then the hash map includes a single key-value pair where the key is the attribute and the value is the corresponding attribute value. If the attribute is not translatable to DOT, a comment is printed that warns the user that the graph attribute was parsed but will not be translated, and the hash map that is returned is empty. Non-translatable attribute are parsed to improve the flexibility of the translation. 
@d Graph Attribute Declaration Rule
@{
graphAttrDeclaration returns [HashMap<String, String> declaration]
@@init {$declaration = new HashMap<String, String>();}
:(graphAttribute VALUE) 
{
	$declaration.put($graphAttribute.text, $VALUE.text);
}
| (nonTranslatableAttribute (VALUE | section)) 
{
	System.out.printf("//Warning: the graph attribute \"%s\" was parsed but is not "
		+ "supported by DOT,so it will not be translated.\n", $nonTranslatableAttribute.text);
};
@}

The nodeAttrDeclaration rule parses a node attribute and returns a hash map. This rule works in a similar fashion to how the graphAttrDeclaration rule works.
@d Node Attribute Declaration Rule
@{
nodeAttrDeclaration returns [HashMap<String, String> declaration]
@@init { $declaration = new HashMap<String, String>(); }
: (nodeAttribute VALUE)
{
	$declaration.put($nodeAttribute.text, $VALUE.text);
} 
| (nonTranslatableAttribute (VALUE | section)) 
{
	System.out.printf("//Warning: the node attribute \"%s\" was parsed but is not"
		+ " supported by DOT,so it will not be translated.\n", $nonTranslatableAttribute.text);
};
@}

The edgeAttrDeclaration rule parses an edge attribute and returns a hash map. This rules works in a similar fashion to how the graphAttrDeclaration and nodeAttrDeclaration rules work.
@d Edge Attribute Declaration Rule
@{
edgeAttrDeclaration returns [HashMap<String, String> declaration]
@@init { $declaration = new HashMap<String, String>(); }
: (edgeAttribute VALUE)
{
	$declaration.put($edgeAttribute.text, $VALUE.text);
}
| (nonTranslatableAttribute (VALUE | section))
{
	System.out.printf("//Warning: the edge attribute \"%s\" was parsed but is not"
		+ " supported by DOT,so it will not be translated.\n", $nonTranslatableAttribute.text);
};
@}


There are four GML graph attribute rules that are directly translatable to DOT. Therefore, the graphAttribute rule is defined as follows:
@d Graph Attribute Rule
@{
graphAttribute: 'label' | 'directed' | 'comment' | 'URL';
@}

Similarly, there are four GML node attribute rules that are directly translatable to DOT. Therefore, the nodeAttribute rule is defined as follows:
@d Node Attribute Rule
@{
nodeAttribute: 'id' | 'name' | 'label' | 'comment';
@}

Likewise, there are four GML edge attribute rules that are directly translatable to DOT. Therefore, the edgeAttribute rule is defined as follows:
@d Edge Attribute Rule
@{
edgeAttribute: 'source' | 'target' | 'label' | 'comment';
@}

An unsupported GML attribute is defined as any word, in an attempt to make the compiler as flexible as possible. This works because the
graphAttributeDeclaration, nodeAttrDeclaration and edgeAttrDeclaration rules are structured to parse translatable attributes before attempting to parse
unsupported attributes.
@d Unsupported Attribute Rule
@{
nonTranslatableAttribute:  WORD;
@}


Some unsupported attributes have 'sections' as values. A section is defined recursively as zero or more attribute declarations (word-value pairs), followed by zero or more attribute declarations where the value is a section, followed by zero or more attribute declarations, enclosed in square brackets:
@d Section Rule
@{
section: '[' (WORD VALUE)*  (WORD section)* (WORD VALUE)* ']';
@}


A GML value is defined to be either a number or a string:
@d Value Rule
@{
VALUE: NUMBER | STRING;
@}

A number can either be a floating point number or an integer.
@d Number Rule
@{
NUMBER: INT ('.' INT)?;
@}

An integer is one or more digits.
@d Integer Rule
@{
INT: ('0' .. '9')+;
@}

A word is one or more characters.
@d Word Rule
@{
WORD: CHAR+;
@}

A character is defined to be any non-whitespace character.
@d Character Rule
@{
CHAR: ~[ \t\r\n];
@}

A String is defined to be a series of non-newline characters enclosed by quotation marks.
@d String Rule
@{
STRING: '"' (~[\r\n"] | '""')* '"';
@}

Whitespace is ignored while parsing.
@d Whitespace Rule
@{
WS : [ \t\r\n]+ -> skip;
@}

\section{Testing}

Two test benches were used for testing the compiler. 

The first test bench was generated manually, and consists of seven GML files and corresponding expected DOT files. The GML files were constructed to test edge cases of the compilation process. For each test case, the GML file was compiled and the outputted DOT file was diffed with the expected DOT file. If there were no differences, the test passed. Otherwise, the test failed.

The second test bench was derived from an online source \cite{TESTBENCH2}, and consists only of four GML files. Each GML file is extremely large. For each test case, the GML file was compiled and a png was generated fromt the outputted DOT file. The png was inspected for correctness. The inspection required randomly choosing two nodes connected by an edge in the png and checking for their existence in the GML. 

All test cases passed.
\newpage
\subsection{Test Bench 1}
\subsubsection{Test 1}
@o test1.gml
@{
graph [

]
@}

@o expected1.dot
@{
graph G {
}
@}
\newpage
\subsubsection{Test 2}
@o test2.gml
@{
graph [
URL "www.test.com"
node [
id 1
label "Node 1"
]
node [
id 2
label "Node 2"
]
node [
id 3
label "Node 3"
]
node [
id 4
label "test"
]
edge [
source 1
target 2
label "Edge from node 1 to node 2"
]
edge [
source 2
target 3
label "Edge from node 2 to node 3"
]
edge [
source 3
target 1 label
"Edge from node 3 to node 1"
]
comment "This is a test"
label "My graph"
defaultnodesize 4
]
@}

@o expected2.dot
@{
//Warning: the graph attribute "defaultnodesize" was parsed but is not supported by DOT,so it will not be translated.
graph G {
	label = "My graph";
	URL = "www.test.com";
	comment = "This is a test";
	1 [label = "Node 1"];
	2 [label = "Node 2"];
	3 [label = "Node 3"];
	4 [label = "test"];
	1 -- 2 [label = "Edge from node 1 to node 2"];
	2 -- 3 [label = "Edge from node 2 to node 3"];
	3 -- 1 [label = "Edge from node 3 to node 1"];
}
@}
\newpage
\subsubsection{Test 3}
@o test3.gml
@{
graph [
comment "This is a test"
node [
id 1
label "Node 1"
]
node [
id 2
label "Node 2"
]
edge [
source 1
target 2
label "Edge from node 1 to node 2"
]
node [
id 3
label "Node 3"
]
node [
id 4
label "test"
]
edge [
source 2
target 3
label "Edge from node 2 to node 3"
]
edge [
source 3
target 1 label
"Edge from node 3 to node 1"
]
label "My graph"
]
@}

@o expected3.dot
@{
graph G {
	label = "My graph";
	comment = "This is a test";
	1 [label = "Node 1"];
	2 [label = "Node 2"];
	3 [label = "Node 3"];
	4 [label = "test"];
	1 -- 2 [label = "Edge from node 1 to node 2"];
	2 -- 3 [label = "Edge from node 2 to node 3"];
	3 -- 1 [label = "Edge from node 3 to node 1"];
}
@}

\newpage
\subsubsection{Test 4}
@o test4.gml
@{
graph [
node [
id 1
label ""
]
node [
id 2
label ""
]
node [
id 3
label ""
]
edge [
source 1
target 2
label ""
]
edge [
source 2
target 3
label ""
]
edge [
source 3
target 1 label
""
]
comment ""
directed 1
isPlanar 1
]
@}

@o expected4.dot
@{
//Warning: the graph attribute "isPlanar" was parsed but is not supported by DOT,so it will not be translated.
digraph G {
	comment = "";
	1 [label = ""];
	2 [label = ""];
	3 [label = ""];
	1 -> 2 [label = ""];
	2 -> 3 [label = ""];
	3 -> 1 [label = ""];
}
@}
\newpage
\subsubsection{Test 5}
@o test5.gml
@{
graph  
[ hierarchic  1  
  directed  1  
  node  
  [ id  0  
    graphics  
    [ x 200.0     
      y 0.0  
    ]  
    LabelGraphics  
    [ text  "January" ]  
  ]  
  node  
  [ id  1  
    graphics  
    [ x 425.0  
      y 75.0  
    ]  
    LabelGraphics  
    [ text  "December"  ]  
  ]  
  edge  
  [ source  1  
    target  0  
    LabelGraphics  
    [ text  "Happy New Year!"  
      model "six_pos"  
      position  "head"  
    ]  
  ]  
] 
@}

@o expected5.dot
@{
//Warning: the graph attribute "hierarchic" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the node attribute "graphics" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the node attribute "LabelGraphics" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the node attribute "graphics" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the node attribute "LabelGraphics" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the edge attribute "LabelGraphics" was parsed but is not supported by DOT,so it will not be translated.
digraph G {
	0 [];
	1 [];
	1 -> 0 [];
}
@}
\newpage
\subsubsection{Test 6}
@o test6.gml
@{
graph  
[ hierarchic  1  
  directed  1  
  node  
  [ id  0  
  ]  
  node  
  [ id  1  
  ]  
  edge  
  [ source  1  
    target  0  
  ]  
] 
@}

@o expected6.dot
@{
//Warning: the graph attribute "hierarchic" was parsed but is not supported by DOT,so it will not be translated.
digraph G {
	0 [];
	1 [];
	1 -> 0 [];
}
@}

\newpage
\subsubsection{Test 7}
@o test7.gml
@{
graph [
 node [
 test "estseesest"
 id 7
 label "5"
 edgeAnchor "corners"
 labelAnchor "n"
 graphics [
 center [ x 82.0000 y 42.0000 ]
 w 16.0000
 h 16.0000
 type "rectangle"
 fill "#000000"
 ]
 ]
 node [
 id 15
 label "13"
 edgeAnchor "corners"
 labelAnchor "c"
 graphics [
 center [ x 73.0000 y 160.000 ]
 w 16.0000
 h 16.0000
 type "rectangle"
 fill "#FF0000"
 ]
 ]
 edge [
 label "24"
 labelAnchor "first"
 source 7
 target 15
 graphics [
 type "line"
 arrow "last"
 Line [
 point [ x 82.0000 y 42.0000 ]
 point [ x 10.0000 y 10.0000 ]
 point [ x 100.000 y 100.000 ]
 point [ x 80.0000 y 30.0000 ]
 point [ x 120.000 y 230.000 ]
 point [ x 73.0000 y 160.000 ]
 ]
 ]
 ]
] 
@}

@o expected7.dot
@{
//Warning: the node attribute "test" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the node attribute "edgeAnchor" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the node attribute "labelAnchor" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the node attribute "graphics" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the node attribute "edgeAnchor" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the node attribute "labelAnchor" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the node attribute "graphics" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the edge attribute "labelAnchor" was parsed but is not supported by DOT,so it will not be translated.
//Warning: the edge attribute "graphics" was parsed but is not supported by DOT,so it will not be translated.
graph G {
	7 [label = "5"];
	15 [label = "13"];
	7 -- 15 [label = "24"];
}
@}
\newpage
\begin{landscape}
\subsection{Test Bench 2}
\subsubsection{Test 1}
dolpins.gml\cite{TESTBENCH21}
\begin{center}
 \includegraphics[width=200mm, height=130mm, scale=0.1]{dolphins}
 \end{center}
\newpage
\subsubsection{Test 2}
adjnoun.gml\cite{TESTBENCH21}
\begin{center}
 \includegraphics[width=200mm, height=130mm, scale=0.1]{adjnoun}
 \end{center}
\newpage
\subsubsection{Test 3}
football.gml\cite{TESTBENCH21}
\begin{center}
 \includegraphics[width=200mm, height=130mm, scale=0.1]{football}
 \end{center}
\newpage
\subsubsection{Test 4}
karate.gml\cite{TESTBENCH21}
\begin{center}
 \includegraphics[width=200mm, height=130mm, scale=0.1]{karate}
 \end{center}
\end{landscape}
\nocite{*}
\bibliography{myrefs}
\bibliographystyle{plain}
\end{document}

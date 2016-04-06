

grammar GMLDot;


@header {
        import java.util.HashMap;
        import java.util.ArrayList;
        import java.util.Set;
}


@members {
        
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
        
}


r: graph;


graph
@init {ArrayList<Node> nodes = new ArrayList<Node>(); 
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


node returns [Node _node]: 'node' '[' nodeAttrDeclarations ']'
{
        $_node = new Node($nodeAttrDeclarations.declarations);
};


edge returns [Edge _edge]: 'edge' '[' edgeAttrDeclarations ']'
{
        $_edge = new Edge($edgeAttrDeclarations.declarations);
};


graphAttrDeclaration returns [HashMap<String, String> declaration]
@init {$declaration = new HashMap<String, String>();}
:(graphAttribute VALUE) 
{
        $declaration.put($graphAttribute.text, $VALUE.text);
}
| (nonTranslatableAttribute (VALUE | section)) 
{
        System.out.printf("//Warning: the graph attribute \"%s\" was parsed but is not "
                + "supported by DOT,so it will not be translated.\n", $nonTranslatableAttribute.text);
};


nodeAttrDeclarations returns [HashMap<String, String> declarations]
@init { $declarations = new HashMap<String, String>(); }
: (nodeAttrDeclaration {$declarations.putAll($nodeAttrDeclaration.declaration);})*;


edgeAttrDeclarations returns [HashMap<String, String> declarations]
@init { $declarations = new HashMap<String, String>(); }
: (edgeAttrDeclaration {$declarations.putAll($edgeAttrDeclaration.declaration);})*;


nodeAttrDeclaration returns [HashMap<String, String> declaration]
@init { $declaration = new HashMap<String, String>(); }
: (nodeAttribute VALUE)
{
        $declaration.put($nodeAttribute.text, $VALUE.text);
} 
| (nonTranslatableAttribute (VALUE | section)) 
{
        System.out.printf("//Warning: the node attribute \"%s\" was parsed but is not"
                + " supported by DOT,so it will not be translated.\n", $nonTranslatableAttribute.text);
};


edgeAttrDeclaration returns [HashMap<String, String> declaration]
@init { $declaration = new HashMap<String, String>(); }
: (edgeAttribute VALUE)
{
        $declaration.put($edgeAttribute.text, $VALUE.text);
}
| (nonTranslatableAttribute (VALUE | section))
{
        System.out.printf("//Warning: the edge attribute \"%s\" was parsed but is not"
                + " supported by DOT,so it will not be translated.\n", $nonTranslatableAttribute.text);
};


graphAttribute: 'label' | 'directed' | 'comment' | 'URL';


nodeAttribute: 'id' | 'name' | 'label' | 'comment';


edgeAttribute: 'source' | 'target' | 'label' | 'comment';


nonTranslatableAttribute:  WORD;


section: '[' (WORD VALUE)*  (WORD section)* (WORD VALUE)* ']';


VALUE: NUMBER | STRING;


NUMBER: INT ('.' INT)?;


INT: ('0' .. '9')+;


WORD: CHAR+;


CHAR: ~[ \t\r\n];


STRING: '"' (~[\r\n"] | '""')* '"';


WS : [ \t\r\n]+ -> skip;


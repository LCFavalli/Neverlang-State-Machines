package sm;

import neverlang.typesystem.defaults.DefaultDependencyGraph;
import neverlang.typesystem.defaults.Relation;
import neverlang.typesystem.graph.LSPGraph;
import neverlang.typesystem.typenv.EntryType;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static void dotExporter(LSPGraph graph, Writer writer){
        var depGraph = graph.getDependencyGraph();
        if(depGraph instanceof DefaultDependencyGraph dependencyGraph){
            var innerGraph = dependencyGraph.getInnerGraph();
            var exporter = new DOTExporter<EntryType, Relation>(t -> String.valueOf(t.hashCode()));
            exporter.setVertexAttributeProvider(entry -> {
                Map<String, Attribute> map = new HashMap<>();

                StringBuilder sb = new StringBuilder();
                sb.append(entry.token().text());
                if(entry.token().location() != null){
                    sb.append("\n").append(entry.token().location().range());
                }
                map.put("label", new DefaultAttribute<>(sb.toString(), AttributeType.STRING));
                return map;
            });
            exporter.setEdgeAttributeProvider(relation -> {
                Map<String, Attribute> map = new HashMap<>();
                map.put("label", new DefaultAttribute<>(relation, AttributeType.STRING));
                return map;
            });
            exporter.exportGraph(innerGraph, writer);
        }

//        DOTExporter<String, DefaultEdge> exporter2=new DOTExporter<>(v -> v.toString());
//        writer = new StringWriter();
//        exporter2.exportGraph(graph, writer);
    }
}

package org.upb.fmde.de.tests;

import java.io.IOException;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.upb.fmde.de.categories.concrete.finsets.FinSet;
import org.upb.fmde.de.categories.concrete.finsets.TotalFunction;
import org.upb.fmde.de.categories.concrete.graphs.Graph;
import org.upb.fmde.de.categories.concrete.graphs.GraphDiagram;
import org.upb.fmde.de.categories.concrete.graphs.GraphMorphism;
import org.upb.fmde.de.categories.concrete.graphs.Graphs;
import org.upb.fmde.de.rulerefinement.RuleRefiner;

public class TestRuleRefinement {
	private static final String diagrams = "diagrams/rulerefinement/";

	@BeforeClass
	public static void clear() {
		TestUtil.clear(diagrams);
	}

	@Test
	public void refineRule() throws IOException {
		RuleRefiner<Graph, GraphMorphism, Graphs> refiner = new RuleRefiner<Graph, GraphMorphism, Graphs>(
				Graphs.Graphs);
		GraphMorphism appendRule = createRuleAppendCard();
		GraphMorphism prependRule = createRulePreappendCard();
		GraphMorphism refinedRule = refiner.refine(appendRule, prependRule);
		GraphDiagram dia = new GraphDiagram();
		dia.arrows(refinedRule);
		dia.objects(refinedRule.src(), refinedRule.trg());

		dia.prettyPrint(diagrams, "refineRule_.dot");;
		
		System.out.println(refinedRule);
	}

	private Graph createHostGraph() {
		FinSet edges = new FinSet("Host Graph Edges");
		FinSet vertices = new FinSet("Host Graph Vertices", Arrays.asList("B"));
		TotalFunction src = new TotalFunction(edges, "src arrow", vertices);
		TotalFunction target = new TotalFunction(edges, "trg arrow", vertices);
		Graph g = new Graph("Host Graph", edges, vertices, src, target);
		return g;
	}

	private Graph createAppendCardGraph() {
		FinSet edges = new FinSet("Edges", Arrays.asList("B -> C"));
		FinSet vertices = new FinSet("vertices", Arrays.asList("B", "C"));

		TotalFunction srcFunction = new TotalFunction(edges, "src arrow",vertices);
		srcFunction.addMapping("B -> C", "B");
		TotalFunction targetFunction = new TotalFunction(edges, "trg arrow",
				vertices);
		targetFunction.addMapping("B -> C", "C");
		return new Graph("Right Side of Rule", edges, vertices, srcFunction, targetFunction);
	}

	private GraphMorphism createRuleAppendCard() {
		Graph left = createHostGraph();
		Graph right = createAppendCardGraph();
		TotalFunction edgeArrow = new TotalFunction(left.edges(), "edge arrow", right.edges());
		TotalFunction verticeFunction = new TotalFunction(left.vertices(), "vertice arrow", right.vertices());
		verticeFunction.addMapping("B", "B");
		GraphMorphism morph = new GraphMorphism("append Card", left, right, edgeArrow, verticeFunction);
		return morph;
	}

	private Graph createPreappendCardGraph() {
		FinSet edges = new FinSet("Edges", Arrays.asList("A -> B"));
		FinSet vertices = new FinSet("vertices", Arrays.asList("A", "B"));

		TotalFunction srcFunction = new TotalFunction(edges, "src arrow",vertices);
		srcFunction.addMapping("A -> B", "A");
		TotalFunction targetFunction = new TotalFunction(edges, "trg arrow",
				vertices);
		targetFunction.addMapping("A -> B", "B");
		return new Graph("Right Side of Rule", edges, vertices, srcFunction, targetFunction);
	}

	private GraphMorphism createRulePreappendCard() {
		Graph left = createHostGraph();
		Graph right = createPreappendCardGraph();
		TotalFunction edgeArrow = new TotalFunction(left.edges(), "edge arrow", right.edges());
		TotalFunction verticeFunction = new TotalFunction(left.vertices(), "vertice arrow", right.vertices());
		verticeFunction.addMapping("B", "B");
		GraphMorphism morph = new GraphMorphism("append Card", left, right, edgeArrow, verticeFunction);
		return morph;
	}
}

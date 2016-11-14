package org.upb.fmde.de.categories.concrete.graphs;

import org.upb.fmde.de.categories.LabelledCategory;
import org.upb.fmde.de.categories.concrete.finsets.FinSets;
import org.upb.fmde.de.categories.concrete.finsets.TotalFunction;

public class Graphs implements LabelledCategory<Graph, GraphMorphism> {

	public static Graphs Graphs = new Graphs();
	
	@Override
	public GraphMorphism compose(GraphMorphism f, GraphMorphism g) {
		// TODO (03) Implement graph morphism composition
		return new GraphMorphism(f.label() + ";" + g.label(), 
				f.src(), 
				g.trg(), 
				FinSets.FinSets.compose(f._E(), g._E()), 
				FinSets.FinSets.compose(f._V(), g._V()));
	}

	@Override
	public GraphMorphism id(Graph g) {
		// TODO (04) Id graph morphism 
		TotalFunction f_E = new TotalFunction(g.edges(), 
				"f_E", g.edges());
		g.edges().elts().forEach(e -> f_E.addMapping(e, e));
		
		TotalFunction f_V = new TotalFunction(g.vertices(), "f_V", g.vertices());
		g.vertices().elts().forEach(v -> f_V.addMapping(v, v));
		
		return new GraphMorphism("id " + g.label(), g, g, f_E, f_V);
	}
}

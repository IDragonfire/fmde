package org.upb.fmde.de.categories.concrete.tgraphs;

import org.upb.fmde.de.categories.LabelledCategory;
import org.upb.fmde.de.categories.concrete.finsets.TotalFunction;
import org.upb.fmde.de.categories.concrete.graphs.Graph;
import org.upb.fmde.de.categories.concrete.graphs.GraphMorphism;
import org.upb.fmde.de.categories.concrete.graphs.Graphs;

public class TGraphs implements LabelledCategory<TGraph, TGraphMorphism> {
	private final Graph typeGraph;
	
	public static TGraphs TGraphsFor(Graph typeGraph) {
		return new TGraphs(typeGraph);
	}
	
	public TGraphs(Graph typeGraph) {
		this.typeGraph = typeGraph;
	}
	
	@Override
	public TGraphMorphism compose(TGraphMorphism f, TGraphMorphism g) {
		// TODO (07) Composition of typed graph morphisms
		return new TGraphMorphism(f.label() + "; " + g.label(), 
				Graphs.Graphs.compose(f.src().type(), g.src().type()), 
				f.src(),
				f.trg());
	}

	@Override
	public TGraphMorphism id(TGraph f) {
		// TODO (08) id typed graph morphisms
		Graph g = f.type().src();
		TotalFunction f_E = new TotalFunction(g.edges(), 
				"f_E", g.edges());
		g.edges().elts().forEach(e -> f_E.addMapping(e, e));
		
		TotalFunction f_V = new TotalFunction(g.vertices(), "f_V", g.vertices());
		g.vertices().elts().forEach(v -> f_V.addMapping(v, v));
		
		GraphMorphism id = new GraphMorphism("id " + f.label(), g, g, f_E, f_V);
		return new TGraphMorphism("id " + f.label(), id, f, f);
	}
	
	@Override
	public String showOb(TGraph o) {
		return LabelledCategory.super.showOb(o) + ":" + o.type().trg().label();
	}
}

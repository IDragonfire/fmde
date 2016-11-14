package org.upb.fmde.de.categories.concrete.graphs;

import org.upb.fmde.de.categories.Category;
import org.upb.fmde.de.categories.ComparableArrow;
import org.upb.fmde.de.categories.LabelledArrow;
import org.upb.fmde.de.categories.concrete.finsets.FinSets;
import org.upb.fmde.de.categories.concrete.finsets.TotalFunction;

public class GraphMorphism extends LabelledArrow<Graph> implements ComparableArrow<GraphMorphism> {
	
	private TotalFunction f_E;
	private TotalFunction f_V;

	public GraphMorphism(String label, Graph srcGraph, Graph trgGraph, TotalFunction f_E, TotalFunction f_V) {
		super(label, srcGraph, trgGraph);
		this.f_E = f_E;
		this.f_V = f_V;
		ensureValidity();
	}

	private void ensureValidity(){
		String message = "GraphMorphism " + label + ": " + source.label() + " -> " + target.label() + " is not valid: ";
		Category.ensure(f_E.src().equals(source.edges()), message + f_E.src().label() + " does not match " + source.edges().label());
		Category.ensure(f_E.trg().equals(target.edges()) , message + f_E.trg().label() + " does not match " + target.edges().label());
		Category.ensure(f_V.src().equals(source.vertices()) , message + f_V.src().label() + " does not match " + source.vertices().label());
		Category.ensure(f_V.trg().equals(target.vertices()) , message + f_V.trg().label() + " does not match " + target.vertices().label());
		Category.ensure(isStructurePreserving() , message + "It is not structure preserving!");		
	}

	private boolean isStructurePreserving() {
		// TODO (01) Implement check for structure preservation
		TotalFunction trg1_fV = FinSets.FinSets.compose(src().trg(), _V());
		TotalFunction fE_trg2 = FinSets.FinSets.compose(_E(), trg().trg());
		
		TotalFunction src1_FV =  FinSets.FinSets.compose(src().src(), _V());
		TotalFunction fE_src2 = FinSets.FinSets.compose(_E(), trg().src());
		
		return trg1_fV.isTheSameAs(fE_trg2) && src1_FV.isTheSameAs(fE_src2);
	}

	public TotalFunction _E(){
		return f_E;
	}
	
	public TotalFunction _V(){
		return f_V;
	}

	@Override
	public boolean isTheSameAs(GraphMorphism a) {
		// TODO (02) Equality check for graph morphisms
		return _E().isTheSameAs(a._E()) && _V().isTheSameAs(a._V());
	}
}

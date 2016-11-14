package org.upb.fmde.de.categories.concrete.triplegraphs;

import org.upb.fmde.de.categories.ComparableArrow;
import org.upb.fmde.de.categories.LabelledArrow;
import org.upb.fmde.de.categories.concrete.graphs.GraphMorphism;
import org.upb.fmde.de.categories.concrete.graphs.Graphs;

public class TripleMorphism extends LabelledArrow<TripleGraph> implements ComparableArrow<TripleMorphism> {

	private GraphMorphism f_S;
	private GraphMorphism f_C;
	private GraphMorphism f_T;

	public TripleMorphism(String label, TripleGraph source, TripleGraph target, GraphMorphism f_S, GraphMorphism f_C,
			GraphMorphism f_T) {
		super(label, source, target);
		this.f_S = f_S;
		this.f_C = f_C;
		this.f_T = f_T;
		if (!isValid())
			throw new IllegalArgumentException("TripleMorphism " + label + " = (" + f_S.label() + ", " + f_C.label()
					+ ", " + f_T.label() + ") is not valid.");
	}

	private boolean isValid() {
		// TODO (11) Structure preservation
		// check G_C -> G_S
		GraphMorphism G_C_trg_to_G_S = Graphs.Graphs.compose(getF_C(), trg().getSigma());
		GraphMorphism G_C_src_to_G_S_src = Graphs.Graphs.compose(src().getSigma(), getF_S());
		// check G_C -> G_T
		GraphMorphism G_C_trg_to_G_T = Graphs.Graphs.compose(getF_C(), trg().getTau());
		GraphMorphism G_C_src_to_G_T_src = Graphs.Graphs.compose(src().getTau(), getF_T());
		return G_C_trg_to_G_S.isTheSameAs(G_C_src_to_G_S_src) && G_C_trg_to_G_T.isTheSameAs(G_C_src_to_G_T_src);
	}

	@Override
	public boolean isTheSameAs(TripleMorphism a) {
		// TODO (12) Equality
		return getF_S().isTheSameAs(a.getF_S()) && getF_C().isTheSameAs(a.getF_C()) && getF_T().isTheSameAs(a.getF_T());
	}

	public GraphMorphism getF_S() {
		return f_S;
	}

	public GraphMorphism getF_C() {
		return f_C;
	}

	public GraphMorphism getF_T() {
		return f_T;
	}
}

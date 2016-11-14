package org.upb.fmde.de.categories.concrete.typedtriplegraphs;

import org.upb.fmde.de.categories.ComparableArrow;
import org.upb.fmde.de.categories.LabelledArrow;
import org.upb.fmde.de.categories.concrete.graphs.Graphs;
import org.upb.fmde.de.categories.concrete.triplegraphs.TripleGraphs;
import org.upb.fmde.de.categories.concrete.triplegraphs.TripleMorphism;

public class TTripleMorphism extends LabelledArrow<TTripleGraph> implements ComparableArrow<TTripleMorphism> {

	private TripleMorphism f;

	public TTripleMorphism(String label, TripleMorphism f, TTripleGraph source, TTripleGraph target) {
		super(label, source, target);
		this.f = f;
		if (!isValid())
			throw new IllegalArgumentException("Typed TripleMorphism " + label + " is not valid.");
	}

	private boolean isValid() {
		// TODO (13) Structure preservation
		return src().getTypeMorphism().isTheSameAs(
				TripleGraphs.TripleGraphs.compose(getUntypedMorphism(), trg().getTypeMorphism()));
	}

	public TripleMorphism getUntypedMorphism() {
		return f;
	}

	@Override
	public boolean isTheSameAs(TTripleMorphism a) {
		// TODO (14) Equality
		return getUntypedMorphism().isTheSameAs(a.getUntypedMorphism())
				&& src().getTypeMorphism().isTheSameAs(a.src().getTypeMorphism())
				&& trg().getTypeMorphism().isTheSameAs(a.trg().getTypeMorphism());
	}

}

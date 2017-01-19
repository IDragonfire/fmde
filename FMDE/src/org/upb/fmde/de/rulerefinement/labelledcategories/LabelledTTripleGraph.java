package org.upb.fmde.de.rulerefinement.labelledcategories;

import org.upb.fmde.de.categories.concrete.typedtriplegraphs.TTripleGraph;

public class LabelledTTripleGraph extends TTripleGraph {

	public LabelledTTripleGraph(String label, LabelledTripleMorphism type) {
		super(label, type);
	}

}

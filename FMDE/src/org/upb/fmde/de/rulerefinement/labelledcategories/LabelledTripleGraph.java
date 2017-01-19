package org.upb.fmde.de.rulerefinement.labelledcategories;

import org.upb.fmde.de.categories.concrete.triplegraphs.TripleGraph;

public class LabelledTripleGraph extends TripleGraph {

	public LabelledTripleGraph(String label, LabelledGraph G_S, LabelledGraphMorphism sigma, LabelledGraph G_C, LabelledGraphMorphism tau, LabelledGraph G_T) {
		super(label, G_S, sigma, G_C, tau, G_T);
	}

}

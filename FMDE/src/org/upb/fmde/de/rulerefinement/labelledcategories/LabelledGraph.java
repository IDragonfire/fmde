package org.upb.fmde.de.rulerefinement.labelledcategories;

import org.upb.fmde.de.categories.concrete.graphs.Graph;

public class LabelledGraph extends Graph {

	public LabelledGraph(String label, LabelledFinSet edges, LabelledFinSet vertices, LabelledTotalFunction source, LabelledTotalFunction target) {
		super(label, edges, vertices, source, target);
	}

}

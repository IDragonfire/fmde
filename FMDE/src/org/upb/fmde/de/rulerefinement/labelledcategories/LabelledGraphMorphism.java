package org.upb.fmde.de.rulerefinement.labelledcategories;

import org.upb.fmde.de.categories.concrete.graphs.GraphMorphism;

public class LabelledGraphMorphism extends GraphMorphism {

	public LabelledGraphMorphism(String label, LabelledGraph srcGraph, LabelledGraph trgGraph, LabelledTotalFunction f_E, LabelledTotalFunction f_V) {
		super(label, srcGraph, trgGraph, f_E, f_V);
	}

}

package org.upb.fmde.de.rulerefinement.labelledcategories;

import org.upb.fmde.de.categories.concrete.tgraphs.TGraphMorphism;

public class LabelledTGraphMorphism extends TGraphMorphism {

	public LabelledTGraphMorphism(String label, LabelledGraphMorphism f, LabelledTGraph source, LabelledTGraph target) {
		super(label, f, source, target);
	}

}

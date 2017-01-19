package org.upb.fmde.de.rulerefinement.labelledcategories;

import org.upb.fmde.de.categories.concrete.triplegraphs.TripleMorphism;

public class LabelledTripleMorphism extends TripleMorphism {

	public LabelledTripleMorphism(String label, LabelledTripleGraph source, LabelledTripleGraph target, LabelledGraphMorphism f_S,
			LabelledGraphMorphism f_C, LabelledGraphMorphism f_T) {
		super(label, source, target, f_S, f_C, f_T);
	}

}

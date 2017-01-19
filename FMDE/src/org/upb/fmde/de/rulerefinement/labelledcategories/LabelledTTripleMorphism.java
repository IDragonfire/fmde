package org.upb.fmde.de.rulerefinement.labelledcategories;

import org.upb.fmde.de.categories.concrete.typedtriplegraphs.TTripleMorphism;

public class LabelledTTripleMorphism extends TTripleMorphism {

	public LabelledTTripleMorphism(String label, LabelledTripleMorphism f, LabelledTTripleGraph source, LabelledTTripleGraph target) {
		super(label, f, source, target);
	}

}

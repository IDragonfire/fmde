package org.upb.fmde.de.rulerefinement.labelledcategories;

import java.util.Arrays;
import java.util.List;

import org.upb.fmde.de.categories.Labelled;
import org.upb.fmde.de.categories.concrete.finsets.FinSet;

public class LabelledFinSet extends FinSet {

	public LabelledFinSet(String label, List<Labelled> elements) {
		super(label, elements);
	}

	public LabelledFinSet(String label, Labelled... elements) {
		this(label, Arrays.asList(elements));
	}
}

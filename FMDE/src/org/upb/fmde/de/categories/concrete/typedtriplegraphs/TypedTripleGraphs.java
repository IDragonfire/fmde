package org.upb.fmde.de.categories.concrete.typedtriplegraphs;

import org.upb.fmde.de.categories.LabelledCategory;
import org.upb.fmde.de.categories.concrete.triplegraphs.TripleGraphs;
import org.upb.fmde.de.categories.concrete.triplegraphs.TripleMorphism;

public class TypedTripleGraphs implements LabelledCategory<TTripleGraph, TTripleMorphism> {

	public static final TypedTripleGraphs TypedTripleGraphs = new TypedTripleGraphs();

	@Override
	public TTripleMorphism compose(TTripleMorphism f, TTripleMorphism g) {
		// TODO (15) Composition
		TripleMorphism t = TripleGraphs.TripleGraphs.compose(f.getUntypedMorphism(), g.getUntypedMorphism());
		return new TTripleMorphism(f.label() + ";" + g.label(), t, f.src(), f.trg());
	}

	@Override
	public TTripleMorphism id(TTripleGraph o) {
		// TODO (16) id
		TripleMorphism f = TripleGraphs.TripleGraphs.id(o.getTypeMorphism().src());
		return new TTripleMorphism("id " + o.label(), f, o, o);
	}

	@Override
	public String showOb(TTripleGraph o) {
		return LabelledCategory.super.showOb(o) + ":" + o.getTypeMorphism().trg().label();
	}
}

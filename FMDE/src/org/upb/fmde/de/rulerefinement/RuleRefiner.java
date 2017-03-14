package org.upb.fmde.de.rulerefinement;

import java.util.List;

import org.upb.fmde.de.categories.colimits.CoLimit;
import org.upb.fmde.de.categories.colimits.pushouts.CoSpan;

public class RuleRefiner<
	Ob, Arr,
	Cat extends RuleRefinableCategory<Ob, Arr>> {

	Cat c;
	
	public RuleRefiner(Cat c) {
		this.c = c;
	}
	
	/**
	 * @param rule_r
	 * @param rule_r_Prime
	 * @return rule_r_Star
	 */
	public Arr refine(Arr rule_r, Arr rule_r_Prime) {
		Ob L = c.source(rule_r);
		Ob L_Prime = c.source(rule_r_Prime);
		CoLimit<CoSpan<Arr>, Arr> pushout_L = c.pushout(c.buildSpan(L, L_Prime));

		Ob R = c.target(rule_r);
		Ob R_Prime = c.target(rule_r_Prime);
		CoLimit<CoSpan<Arr>, Arr> pushout_R = c.pushout(c.buildSpan(R, R_Prime));
		
		Arr rule_r_star = pushout_L.up.apply(pushout_R.obj);
		return rule_r_star;
	}
	
	
	private Arr mergeWithoutGluing(List<Arr> rules) {
		// handle trivial cases
		if (rules.size() < 1)
			throw new IllegalArgumentException("List must contain at least one rule!");
		else if (rules.size() == 1)
			return rules.get(0);
		
		// initialize variables with first rule
		Ob L_i = c.source(rules.get(0));
		Ob R_i = c.target(rules.get(0));
		CoLimit<CoSpan<Arr>, Arr> L_Bar_coProduct;
		CoLimit<CoSpan<Arr>, Arr> R_Bar_coProduct;
		Arr r_Bar = rules.get(0);
		
		// iteratively build the co-product of the rules' left-hand sides and right-hand sides
		for (int i = 1; i < rules.size(); i++) {
			L_Bar_coProduct = c.coproduct(L_i, c.source(rules.get(i)));
			R_Bar_coProduct = c.coproduct(R_i, c.target(rules.get(i)));
			
			// create an intermediate rule by utilizing the universal property of the co-product
			r_Bar = L_Bar_coProduct.up.apply(new CoSpan<Arr>(c,
															 c.compose(r_Bar, R_Bar_coProduct.obj.vert),
															 c.compose(rules.get(i), R_Bar_coProduct.obj.horiz)));
			L_i = c.target(L_Bar_coProduct.obj.horiz);
			R_i = c.target(R_Bar_coProduct.obj.horiz);
		}
		
		return r_Bar;
	};
	
	
	/**
	 * Merges several rules into one. The criterion for merging two elements together is the toString() representation of the element.
	 * 
	 * @param rules A list of the rules that shall be merged.
	 * @return An arrow for the merged rule.
	 */
	public Arr mergeOnLabels(List<Arr> rules) {
		Arr r_Bar = mergeWithoutGluing(rules);
		Ob R_Bar = this.c.target(r_Bar);
		Arr match = this.c.matchOnLabels(R_Bar);
		
		return c.epiMonoFactorize(c.compose(r_Bar, match)).second;
	}
}

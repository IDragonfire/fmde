package org.upb.fmde.de.rulerefinement;

import java.util.List;

import org.upb.fmde.de.categories.colimits.CoLimit;
import org.upb.fmde.de.categories.colimits.pushouts.CoSpan;
import org.upb.fmde.de.categories.colimits.pushouts.Span;

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
		Ob L_Tilde = c.extract(L, L_Prime);
		Arr L_Tilde2L = c.buildArrow(L_Tilde, L);
		Arr L_Tilde2L_Prime = c.buildArrow(L_Tilde, L_Prime);
		CoLimit<CoSpan<Arr>, Arr> pushout_L = c.pushout(new Span<Arr>(c, L_Tilde2L, L_Tilde2L_Prime));
		
//		Ob L_Star = c.target(pushout_L.obj.horiz);

		Ob R = c.target(rule_r);
		Ob R_Prime = c.target(rule_r_Prime);
		Ob R_Tilde = c.extract(R, R_Prime);
		Arr R_Tilde2R = c.buildArrow(R_Tilde, R);
		Arr R_Tilde2R_Prime = c.buildArrow(R_Tilde, R_Prime);
		CoLimit<CoSpan<Arr>, Arr> pushout_R = c.pushout(new Span<Arr>(c, R_Tilde2R, R_Tilde2R_Prime));
		
//		Ob R_Star = c.target(pushout_R.obj.horiz);
		
		Arr rule_r_star = pushout_L.up.apply(pushout_R.obj);
		return rule_r_star;
	}
	
	public Arr preMerge(List<Arr> rules) {
		if (rules.size() < 1)
			throw new IllegalArgumentException("List must contain at least one rule!");
		else if (rules.size() == 1)
			return rules.get(0);
		
		Ob L_i = c.source(rules.get(0));
		Ob R_i = c.target(rules.get(0));
		CoLimit<CoSpan<Arr>, Arr> L_Bar_coProduct;
		CoLimit<CoSpan<Arr>, Arr> R_Bar_coProduct;
		Arr r_Bar = rules.get(0);
		
		for (int i = 1; i < rules.size(); i++) {
			L_Bar_coProduct = c.coproduct(L_i, c.source(rules.get(i)));
			R_Bar_coProduct = c.coproduct(R_i, c.target(rules.get(i)));
			
			r_Bar = L_Bar_coProduct.up.apply(new CoSpan<Arr>(c,
															 c.compose(r_Bar, R_Bar_coProduct.obj.vert),
															 c.compose(rules.get(i), R_Bar_coProduct.obj.horiz)));
			L_i = c.target(L_Bar_coProduct.obj.horiz);
			R_i = c.target(R_Bar_coProduct.obj.horiz);
		}
		
		return r_Bar;
	};
	
	public Arr merge(Arr r_Bar, Arr mu_R) {
		Arr r_Prime = c.epiMonoFactorize(c.compose(r_Bar, mu_R)).second;
		return r_Prime;
	}
	
	public Arr mergeOnLabels(List<Arr> rules) {
		Arr r_Bar = preMerge(rules);
		Ob R_Bar = this.c.target(r_Bar);
		Arr match = this.c.matchOnLabels(R_Bar);
		
		return merge(r_Bar, match);
	}
}

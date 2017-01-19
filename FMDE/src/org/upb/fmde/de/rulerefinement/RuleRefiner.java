package org.upb.fmde.de.rulerefinement;

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
}

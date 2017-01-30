package org.upb.fmde.de.rulerefinement;

import org.upb.fmde.de.categories.colimits.pushouts.CategoryWithPushouts;
import org.upb.fmde.de.categories.colimits.pushouts.Corner;
import org.upb.fmde.de.categories.colimits.pushouts.Span;

public interface RuleRefinableCategory<Ob, Arr> 
	extends CategoryWithPushouts<Ob,Arr> {

	/**
	 * @param L
	 * @param L_Prime
	 * @return L_Tilde
	 */
	public Ob extract(Ob L, Ob L_Prime);
	
	/**
	 * @param L_Tilde
	 * @param L
	 * @return L_Tilde -> L
	 */
	public Arr buildArrow(Ob L_Tilde, Ob L);
	
	/**
	 * @param L
	 * @param L_Prime
	 * @return
	 */
	default Span<Arr> buildSpan(Ob L, Ob L_Prime) {
		Ob L_Tilde = this.extract(L, L_Prime);
		Arr L_Tilde2L = this.buildArrow(L_Tilde, L);
		Arr L_Tilde2L_Prime = this.buildArrow(L_Tilde, L_Prime);
		return new Span<Arr>(this, L_Tilde2L, L_Tilde2L_Prime);
	}

	public Corner<Arr> epiMonoFactorize(Arr a);
	
	public Arr matchOnLabels(Ob o);
}

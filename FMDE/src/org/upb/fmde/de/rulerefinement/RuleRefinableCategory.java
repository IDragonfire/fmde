package org.upb.fmde.de.rulerefinement;

import org.upb.fmde.de.categories.colimits.pushouts.CategoryWithPushouts;
import org.upb.fmde.de.categories.colimits.pushouts.Corner;

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
	
	public Corner<Arr> epiMonoFactorize(Arr a);
}

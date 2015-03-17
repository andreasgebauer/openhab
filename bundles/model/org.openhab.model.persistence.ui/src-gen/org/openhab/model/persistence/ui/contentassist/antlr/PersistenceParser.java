/*
* generated by Xtext
*/
package org.openhab.model.persistence.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.openhab.model.persistence.services.PersistenceGrammarAccess;

public class PersistenceParser extends AbstractContentAssistParser {
	
	@Inject
	private PersistenceGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.openhab.model.persistence.ui.contentassist.antlr.internal.InternalPersistenceParser createParser() {
		org.openhab.model.persistence.ui.contentassist.antlr.internal.InternalPersistenceParser result = new org.openhab.model.persistence.ui.contentassist.antlr.internal.InternalPersistenceParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getStrategyAccess().getAlternatives(), "rule__Strategy__Alternatives");
					put(grammarAccess.getFilterDetailsAccess().getAlternatives(), "rule__FilterDetails__Alternatives");
					put(grammarAccess.getTimeFilterAccess().getUnitAlternatives_1_0(), "rule__TimeFilter__UnitAlternatives_1_0");
					put(grammarAccess.getPersistenceConfigurationAccess().getItemsAlternatives_0_0(), "rule__PersistenceConfiguration__ItemsAlternatives_0_0");
					put(grammarAccess.getPersistenceConfigurationAccess().getItemsAlternatives_1_1_0(), "rule__PersistenceConfiguration__ItemsAlternatives_1_1_0");
					put(grammarAccess.getPersistenceConfigurationAccess().getAlternatives_3(), "rule__PersistenceConfiguration__Alternatives_3");
					put(grammarAccess.getPersistenceModelAccess().getGroup(), "rule__PersistenceModel__Group__0");
					put(grammarAccess.getPersistenceModelAccess().getGroup_4(), "rule__PersistenceModel__Group_4__0");
					put(grammarAccess.getPersistenceModelAccess().getGroup_4_3(), "rule__PersistenceModel__Group_4_3__0");
					put(grammarAccess.getPersistenceModelAccess().getGroup_6(), "rule__PersistenceModel__Group_6__0");
					put(grammarAccess.getPersistenceModelAccess().getGroup_7(), "rule__PersistenceModel__Group_7__0");
					put(grammarAccess.getCronStrategyAccess().getGroup(), "rule__CronStrategy__Group__0");
					put(grammarAccess.getFilterAccess().getGroup(), "rule__Filter__Group__0");
					put(grammarAccess.getThresholdFilterAccess().getGroup(), "rule__ThresholdFilter__Group__0");
					put(grammarAccess.getTimeFilterAccess().getGroup(), "rule__TimeFilter__Group__0");
					put(grammarAccess.getPersistenceConfigurationAccess().getGroup(), "rule__PersistenceConfiguration__Group__0");
					put(grammarAccess.getPersistenceConfigurationAccess().getGroup_1(), "rule__PersistenceConfiguration__Group_1__0");
					put(grammarAccess.getPersistenceConfigurationAccess().getGroup_2(), "rule__PersistenceConfiguration__Group_2__0");
					put(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0(), "rule__PersistenceConfiguration__Group_3_0__0");
					put(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_1(), "rule__PersistenceConfiguration__Group_3_0_1__0");
					put(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_1_3(), "rule__PersistenceConfiguration__Group_3_0_1_3__0");
					put(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_2(), "rule__PersistenceConfiguration__Group_3_0_2__0");
					put(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_2_3(), "rule__PersistenceConfiguration__Group_3_0_2_3__0");
					put(grammarAccess.getAllConfigAccess().getGroup(), "rule__AllConfig__Group__0");
					put(grammarAccess.getGroupConfigAccess().getGroup(), "rule__GroupConfig__Group__0");
					put(grammarAccess.getDECIMALAccess().getGroup(), "rule__DECIMAL__Group__0");
					put(grammarAccess.getDECIMALAccess().getGroup_1(), "rule__DECIMAL__Group_1__0");
					put(grammarAccess.getPersistenceModelAccess().getStrategiesAssignment_3(), "rule__PersistenceModel__StrategiesAssignment_3");
					put(grammarAccess.getPersistenceModelAccess().getDefaultsAssignment_4_2(), "rule__PersistenceModel__DefaultsAssignment_4_2");
					put(grammarAccess.getPersistenceModelAccess().getDefaultsAssignment_4_3_1(), "rule__PersistenceModel__DefaultsAssignment_4_3_1");
					put(grammarAccess.getPersistenceModelAccess().getFiltersAssignment_6_2(), "rule__PersistenceModel__FiltersAssignment_6_2");
					put(grammarAccess.getPersistenceModelAccess().getConfigsAssignment_7_2(), "rule__PersistenceModel__ConfigsAssignment_7_2");
					put(grammarAccess.getStrategyAccess().getNameAssignment_1(), "rule__Strategy__NameAssignment_1");
					put(grammarAccess.getCronStrategyAccess().getNameAssignment_1(), "rule__CronStrategy__NameAssignment_1");
					put(grammarAccess.getCronStrategyAccess().getCronExpressionAssignment_3(), "rule__CronStrategy__CronExpressionAssignment_3");
					put(grammarAccess.getFilterAccess().getNameAssignment_0(), "rule__Filter__NameAssignment_0");
					put(grammarAccess.getFilterAccess().getDefinitionAssignment_2(), "rule__Filter__DefinitionAssignment_2");
					put(grammarAccess.getThresholdFilterAccess().getValueAssignment_1(), "rule__ThresholdFilter__ValueAssignment_1");
					put(grammarAccess.getThresholdFilterAccess().getPercentAssignment_2(), "rule__ThresholdFilter__PercentAssignment_2");
					put(grammarAccess.getTimeFilterAccess().getValueAssignment_0(), "rule__TimeFilter__ValueAssignment_0");
					put(grammarAccess.getTimeFilterAccess().getUnitAssignment_1(), "rule__TimeFilter__UnitAssignment_1");
					put(grammarAccess.getPersistenceConfigurationAccess().getItemsAssignment_0(), "rule__PersistenceConfiguration__ItemsAssignment_0");
					put(grammarAccess.getPersistenceConfigurationAccess().getItemsAssignment_1_1(), "rule__PersistenceConfiguration__ItemsAssignment_1_1");
					put(grammarAccess.getPersistenceConfigurationAccess().getAliasAssignment_2_1(), "rule__PersistenceConfiguration__AliasAssignment_2_1");
					put(grammarAccess.getPersistenceConfigurationAccess().getStrategiesAssignment_3_0_1_2(), "rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2");
					put(grammarAccess.getPersistenceConfigurationAccess().getStrategiesAssignment_3_0_1_3_1(), "rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1");
					put(grammarAccess.getPersistenceConfigurationAccess().getFiltersAssignment_3_0_2_2(), "rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2");
					put(grammarAccess.getPersistenceConfigurationAccess().getFiltersAssignment_3_0_2_3_1(), "rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1");
					put(grammarAccess.getItemConfigAccess().getItemAssignment(), "rule__ItemConfig__ItemAssignment");
					put(grammarAccess.getGroupConfigAccess().getGroupAssignment_0(), "rule__GroupConfig__GroupAssignment_0");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.openhab.model.persistence.ui.contentassist.antlr.internal.InternalPersistenceParser typedParser = (org.openhab.model.persistence.ui.contentassist.antlr.internal.InternalPersistenceParser) parser;
			typedParser.entryRulePersistenceModel();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public PersistenceGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(PersistenceGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}

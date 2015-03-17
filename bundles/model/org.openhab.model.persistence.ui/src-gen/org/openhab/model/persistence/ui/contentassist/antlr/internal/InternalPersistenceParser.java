package org.openhab.model.persistence.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.openhab.model.persistence.services.PersistenceGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPersistenceParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'s'", "'m'", "'h'", "'d'", "';'", "'Strategies'", "'{'", "'}'", "'default'", "'='", "','", "'Filters'", "'Items'", "':'", "'>'", "'->'", "'strategy'", "'filter'", "'*'", "'.'", "'%'"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=4;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalPersistenceParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalPersistenceParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalPersistenceParser.tokenNames; }
    public String getGrammarFileName() { return "../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g"; }


     
     	private PersistenceGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(PersistenceGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start "entryRulePersistenceModel"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:60:1: entryRulePersistenceModel : rulePersistenceModel EOF ;
    public final void entryRulePersistenceModel() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:61:1: ( rulePersistenceModel EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:62:1: rulePersistenceModel EOF
            {
             before(grammarAccess.getPersistenceModelRule()); 
            pushFollow(FOLLOW_rulePersistenceModel_in_entryRulePersistenceModel61);
            rulePersistenceModel();

            state._fsp--;

             after(grammarAccess.getPersistenceModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePersistenceModel68); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePersistenceModel"


    // $ANTLR start "rulePersistenceModel"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:69:1: rulePersistenceModel : ( ( rule__PersistenceModel__Group__0 ) ) ;
    public final void rulePersistenceModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:73:2: ( ( ( rule__PersistenceModel__Group__0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:74:1: ( ( rule__PersistenceModel__Group__0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:74:1: ( ( rule__PersistenceModel__Group__0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:75:1: ( rule__PersistenceModel__Group__0 )
            {
             before(grammarAccess.getPersistenceModelAccess().getGroup()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:76:1: ( rule__PersistenceModel__Group__0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:76:2: rule__PersistenceModel__Group__0
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group__0_in_rulePersistenceModel94);
            rule__PersistenceModel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceModelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePersistenceModel"


    // $ANTLR start "entryRuleStrategy"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:88:1: entryRuleStrategy : ruleStrategy EOF ;
    public final void entryRuleStrategy() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:89:1: ( ruleStrategy EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:90:1: ruleStrategy EOF
            {
             before(grammarAccess.getStrategyRule()); 
            pushFollow(FOLLOW_ruleStrategy_in_entryRuleStrategy121);
            ruleStrategy();

            state._fsp--;

             after(grammarAccess.getStrategyRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStrategy128); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleStrategy"


    // $ANTLR start "ruleStrategy"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:97:1: ruleStrategy : ( ( rule__Strategy__Alternatives ) ) ;
    public final void ruleStrategy() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:101:2: ( ( ( rule__Strategy__Alternatives ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:102:1: ( ( rule__Strategy__Alternatives ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:102:1: ( ( rule__Strategy__Alternatives ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:103:1: ( rule__Strategy__Alternatives )
            {
             before(grammarAccess.getStrategyAccess().getAlternatives()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:104:1: ( rule__Strategy__Alternatives )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:104:2: rule__Strategy__Alternatives
            {
            pushFollow(FOLLOW_rule__Strategy__Alternatives_in_ruleStrategy154);
            rule__Strategy__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getStrategyAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStrategy"


    // $ANTLR start "entryRuleCronStrategy"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:116:1: entryRuleCronStrategy : ruleCronStrategy EOF ;
    public final void entryRuleCronStrategy() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:117:1: ( ruleCronStrategy EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:118:1: ruleCronStrategy EOF
            {
             before(grammarAccess.getCronStrategyRule()); 
            pushFollow(FOLLOW_ruleCronStrategy_in_entryRuleCronStrategy181);
            ruleCronStrategy();

            state._fsp--;

             after(grammarAccess.getCronStrategyRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCronStrategy188); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCronStrategy"


    // $ANTLR start "ruleCronStrategy"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:125:1: ruleCronStrategy : ( ( rule__CronStrategy__Group__0 ) ) ;
    public final void ruleCronStrategy() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:129:2: ( ( ( rule__CronStrategy__Group__0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:130:1: ( ( rule__CronStrategy__Group__0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:130:1: ( ( rule__CronStrategy__Group__0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:131:1: ( rule__CronStrategy__Group__0 )
            {
             before(grammarAccess.getCronStrategyAccess().getGroup()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:132:1: ( rule__CronStrategy__Group__0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:132:2: rule__CronStrategy__Group__0
            {
            pushFollow(FOLLOW_rule__CronStrategy__Group__0_in_ruleCronStrategy214);
            rule__CronStrategy__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCronStrategyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCronStrategy"


    // $ANTLR start "entryRuleFilter"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:144:1: entryRuleFilter : ruleFilter EOF ;
    public final void entryRuleFilter() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:145:1: ( ruleFilter EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:146:1: ruleFilter EOF
            {
             before(grammarAccess.getFilterRule()); 
            pushFollow(FOLLOW_ruleFilter_in_entryRuleFilter241);
            ruleFilter();

            state._fsp--;

             after(grammarAccess.getFilterRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFilter248); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFilter"


    // $ANTLR start "ruleFilter"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:153:1: ruleFilter : ( ( rule__Filter__Group__0 ) ) ;
    public final void ruleFilter() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:157:2: ( ( ( rule__Filter__Group__0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:158:1: ( ( rule__Filter__Group__0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:158:1: ( ( rule__Filter__Group__0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:159:1: ( rule__Filter__Group__0 )
            {
             before(grammarAccess.getFilterAccess().getGroup()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:160:1: ( rule__Filter__Group__0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:160:2: rule__Filter__Group__0
            {
            pushFollow(FOLLOW_rule__Filter__Group__0_in_ruleFilter274);
            rule__Filter__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFilterAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFilter"


    // $ANTLR start "entryRuleFilterDetails"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:172:1: entryRuleFilterDetails : ruleFilterDetails EOF ;
    public final void entryRuleFilterDetails() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:173:1: ( ruleFilterDetails EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:174:1: ruleFilterDetails EOF
            {
             before(grammarAccess.getFilterDetailsRule()); 
            pushFollow(FOLLOW_ruleFilterDetails_in_entryRuleFilterDetails301);
            ruleFilterDetails();

            state._fsp--;

             after(grammarAccess.getFilterDetailsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFilterDetails308); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFilterDetails"


    // $ANTLR start "ruleFilterDetails"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:181:1: ruleFilterDetails : ( ( rule__FilterDetails__Alternatives ) ) ;
    public final void ruleFilterDetails() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:185:2: ( ( ( rule__FilterDetails__Alternatives ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:186:1: ( ( rule__FilterDetails__Alternatives ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:186:1: ( ( rule__FilterDetails__Alternatives ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:187:1: ( rule__FilterDetails__Alternatives )
            {
             before(grammarAccess.getFilterDetailsAccess().getAlternatives()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:188:1: ( rule__FilterDetails__Alternatives )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:188:2: rule__FilterDetails__Alternatives
            {
            pushFollow(FOLLOW_rule__FilterDetails__Alternatives_in_ruleFilterDetails334);
            rule__FilterDetails__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getFilterDetailsAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFilterDetails"


    // $ANTLR start "entryRuleThresholdFilter"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:200:1: entryRuleThresholdFilter : ruleThresholdFilter EOF ;
    public final void entryRuleThresholdFilter() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:201:1: ( ruleThresholdFilter EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:202:1: ruleThresholdFilter EOF
            {
             before(grammarAccess.getThresholdFilterRule()); 
            pushFollow(FOLLOW_ruleThresholdFilter_in_entryRuleThresholdFilter361);
            ruleThresholdFilter();

            state._fsp--;

             after(grammarAccess.getThresholdFilterRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleThresholdFilter368); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleThresholdFilter"


    // $ANTLR start "ruleThresholdFilter"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:209:1: ruleThresholdFilter : ( ( rule__ThresholdFilter__Group__0 ) ) ;
    public final void ruleThresholdFilter() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:213:2: ( ( ( rule__ThresholdFilter__Group__0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:214:1: ( ( rule__ThresholdFilter__Group__0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:214:1: ( ( rule__ThresholdFilter__Group__0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:215:1: ( rule__ThresholdFilter__Group__0 )
            {
             before(grammarAccess.getThresholdFilterAccess().getGroup()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:216:1: ( rule__ThresholdFilter__Group__0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:216:2: rule__ThresholdFilter__Group__0
            {
            pushFollow(FOLLOW_rule__ThresholdFilter__Group__0_in_ruleThresholdFilter394);
            rule__ThresholdFilter__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getThresholdFilterAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleThresholdFilter"


    // $ANTLR start "entryRuleTimeFilter"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:228:1: entryRuleTimeFilter : ruleTimeFilter EOF ;
    public final void entryRuleTimeFilter() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:229:1: ( ruleTimeFilter EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:230:1: ruleTimeFilter EOF
            {
             before(grammarAccess.getTimeFilterRule()); 
            pushFollow(FOLLOW_ruleTimeFilter_in_entryRuleTimeFilter421);
            ruleTimeFilter();

            state._fsp--;

             after(grammarAccess.getTimeFilterRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTimeFilter428); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTimeFilter"


    // $ANTLR start "ruleTimeFilter"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:237:1: ruleTimeFilter : ( ( rule__TimeFilter__Group__0 ) ) ;
    public final void ruleTimeFilter() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:241:2: ( ( ( rule__TimeFilter__Group__0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:242:1: ( ( rule__TimeFilter__Group__0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:242:1: ( ( rule__TimeFilter__Group__0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:243:1: ( rule__TimeFilter__Group__0 )
            {
             before(grammarAccess.getTimeFilterAccess().getGroup()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:244:1: ( rule__TimeFilter__Group__0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:244:2: rule__TimeFilter__Group__0
            {
            pushFollow(FOLLOW_rule__TimeFilter__Group__0_in_ruleTimeFilter454);
            rule__TimeFilter__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTimeFilterAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTimeFilter"


    // $ANTLR start "entryRulePersistenceConfiguration"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:256:1: entryRulePersistenceConfiguration : rulePersistenceConfiguration EOF ;
    public final void entryRulePersistenceConfiguration() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:257:1: ( rulePersistenceConfiguration EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:258:1: rulePersistenceConfiguration EOF
            {
             before(grammarAccess.getPersistenceConfigurationRule()); 
            pushFollow(FOLLOW_rulePersistenceConfiguration_in_entryRulePersistenceConfiguration481);
            rulePersistenceConfiguration();

            state._fsp--;

             after(grammarAccess.getPersistenceConfigurationRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePersistenceConfiguration488); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePersistenceConfiguration"


    // $ANTLR start "rulePersistenceConfiguration"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:265:1: rulePersistenceConfiguration : ( ( rule__PersistenceConfiguration__Group__0 ) ) ;
    public final void rulePersistenceConfiguration() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:269:2: ( ( ( rule__PersistenceConfiguration__Group__0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:270:1: ( ( rule__PersistenceConfiguration__Group__0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:270:1: ( ( rule__PersistenceConfiguration__Group__0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:271:1: ( rule__PersistenceConfiguration__Group__0 )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getGroup()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:272:1: ( rule__PersistenceConfiguration__Group__0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:272:2: rule__PersistenceConfiguration__Group__0
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group__0_in_rulePersistenceConfiguration514);
            rule__PersistenceConfiguration__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceConfigurationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePersistenceConfiguration"


    // $ANTLR start "entryRuleAllConfig"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:284:1: entryRuleAllConfig : ruleAllConfig EOF ;
    public final void entryRuleAllConfig() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:285:1: ( ruleAllConfig EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:286:1: ruleAllConfig EOF
            {
             before(grammarAccess.getAllConfigRule()); 
            pushFollow(FOLLOW_ruleAllConfig_in_entryRuleAllConfig541);
            ruleAllConfig();

            state._fsp--;

             after(grammarAccess.getAllConfigRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAllConfig548); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAllConfig"


    // $ANTLR start "ruleAllConfig"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:293:1: ruleAllConfig : ( ( rule__AllConfig__Group__0 ) ) ;
    public final void ruleAllConfig() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:297:2: ( ( ( rule__AllConfig__Group__0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:298:1: ( ( rule__AllConfig__Group__0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:298:1: ( ( rule__AllConfig__Group__0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:299:1: ( rule__AllConfig__Group__0 )
            {
             before(grammarAccess.getAllConfigAccess().getGroup()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:300:1: ( rule__AllConfig__Group__0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:300:2: rule__AllConfig__Group__0
            {
            pushFollow(FOLLOW_rule__AllConfig__Group__0_in_ruleAllConfig574);
            rule__AllConfig__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAllConfigAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAllConfig"


    // $ANTLR start "entryRuleItemConfig"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:312:1: entryRuleItemConfig : ruleItemConfig EOF ;
    public final void entryRuleItemConfig() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:313:1: ( ruleItemConfig EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:314:1: ruleItemConfig EOF
            {
             before(grammarAccess.getItemConfigRule()); 
            pushFollow(FOLLOW_ruleItemConfig_in_entryRuleItemConfig601);
            ruleItemConfig();

            state._fsp--;

             after(grammarAccess.getItemConfigRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleItemConfig608); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleItemConfig"


    // $ANTLR start "ruleItemConfig"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:321:1: ruleItemConfig : ( ( rule__ItemConfig__ItemAssignment ) ) ;
    public final void ruleItemConfig() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:325:2: ( ( ( rule__ItemConfig__ItemAssignment ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:326:1: ( ( rule__ItemConfig__ItemAssignment ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:326:1: ( ( rule__ItemConfig__ItemAssignment ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:327:1: ( rule__ItemConfig__ItemAssignment )
            {
             before(grammarAccess.getItemConfigAccess().getItemAssignment()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:328:1: ( rule__ItemConfig__ItemAssignment )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:328:2: rule__ItemConfig__ItemAssignment
            {
            pushFollow(FOLLOW_rule__ItemConfig__ItemAssignment_in_ruleItemConfig634);
            rule__ItemConfig__ItemAssignment();

            state._fsp--;


            }

             after(grammarAccess.getItemConfigAccess().getItemAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleItemConfig"


    // $ANTLR start "entryRuleGroupConfig"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:340:1: entryRuleGroupConfig : ruleGroupConfig EOF ;
    public final void entryRuleGroupConfig() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:341:1: ( ruleGroupConfig EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:342:1: ruleGroupConfig EOF
            {
             before(grammarAccess.getGroupConfigRule()); 
            pushFollow(FOLLOW_ruleGroupConfig_in_entryRuleGroupConfig661);
            ruleGroupConfig();

            state._fsp--;

             after(grammarAccess.getGroupConfigRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroupConfig668); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleGroupConfig"


    // $ANTLR start "ruleGroupConfig"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:349:1: ruleGroupConfig : ( ( rule__GroupConfig__Group__0 ) ) ;
    public final void ruleGroupConfig() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:353:2: ( ( ( rule__GroupConfig__Group__0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:354:1: ( ( rule__GroupConfig__Group__0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:354:1: ( ( rule__GroupConfig__Group__0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:355:1: ( rule__GroupConfig__Group__0 )
            {
             before(grammarAccess.getGroupConfigAccess().getGroup()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:356:1: ( rule__GroupConfig__Group__0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:356:2: rule__GroupConfig__Group__0
            {
            pushFollow(FOLLOW_rule__GroupConfig__Group__0_in_ruleGroupConfig694);
            rule__GroupConfig__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGroupConfigAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGroupConfig"


    // $ANTLR start "entryRuleDECIMAL"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:368:1: entryRuleDECIMAL : ruleDECIMAL EOF ;
    public final void entryRuleDECIMAL() throws RecognitionException {
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:369:1: ( ruleDECIMAL EOF )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:370:1: ruleDECIMAL EOF
            {
             before(grammarAccess.getDECIMALRule()); 
            pushFollow(FOLLOW_ruleDECIMAL_in_entryRuleDECIMAL721);
            ruleDECIMAL();

            state._fsp--;

             after(grammarAccess.getDECIMALRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDECIMAL728); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDECIMAL"


    // $ANTLR start "ruleDECIMAL"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:377:1: ruleDECIMAL : ( ( rule__DECIMAL__Group__0 ) ) ;
    public final void ruleDECIMAL() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:381:2: ( ( ( rule__DECIMAL__Group__0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:382:1: ( ( rule__DECIMAL__Group__0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:382:1: ( ( rule__DECIMAL__Group__0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:383:1: ( rule__DECIMAL__Group__0 )
            {
             before(grammarAccess.getDECIMALAccess().getGroup()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:384:1: ( rule__DECIMAL__Group__0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:384:2: rule__DECIMAL__Group__0
            {
            pushFollow(FOLLOW_rule__DECIMAL__Group__0_in_ruleDECIMAL754);
            rule__DECIMAL__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDECIMALAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDECIMAL"


    // $ANTLR start "rule__Strategy__Alternatives"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:396:1: rule__Strategy__Alternatives : ( ( ruleCronStrategy ) | ( ( rule__Strategy__NameAssignment_1 ) ) );
    public final void rule__Strategy__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:400:1: ( ( ruleCronStrategy ) | ( ( rule__Strategy__NameAssignment_1 ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_ID) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==EOF||LA1_1==RULE_ID||(LA1_1>=18 && LA1_1<=19)) ) {
                    alt1=2;
                }
                else if ( (LA1_1==24) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:401:1: ( ruleCronStrategy )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:401:1: ( ruleCronStrategy )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:402:1: ruleCronStrategy
                    {
                     before(grammarAccess.getStrategyAccess().getCronStrategyParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleCronStrategy_in_rule__Strategy__Alternatives790);
                    ruleCronStrategy();

                    state._fsp--;

                     after(grammarAccess.getStrategyAccess().getCronStrategyParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:407:6: ( ( rule__Strategy__NameAssignment_1 ) )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:407:6: ( ( rule__Strategy__NameAssignment_1 ) )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:408:1: ( rule__Strategy__NameAssignment_1 )
                    {
                     before(grammarAccess.getStrategyAccess().getNameAssignment_1()); 
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:409:1: ( rule__Strategy__NameAssignment_1 )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:409:2: rule__Strategy__NameAssignment_1
                    {
                    pushFollow(FOLLOW_rule__Strategy__NameAssignment_1_in_rule__Strategy__Alternatives807);
                    rule__Strategy__NameAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getStrategyAccess().getNameAssignment_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Strategy__Alternatives"


    // $ANTLR start "rule__FilterDetails__Alternatives"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:418:1: rule__FilterDetails__Alternatives : ( ( ruleThresholdFilter ) | ( ruleTimeFilter ) );
    public final void rule__FilterDetails__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:422:1: ( ( ruleThresholdFilter ) | ( ruleTimeFilter ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==25) ) {
                alt2=1;
            }
            else if ( (LA2_0==RULE_INT) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:423:1: ( ruleThresholdFilter )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:423:1: ( ruleThresholdFilter )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:424:1: ruleThresholdFilter
                    {
                     before(grammarAccess.getFilterDetailsAccess().getThresholdFilterParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleThresholdFilter_in_rule__FilterDetails__Alternatives840);
                    ruleThresholdFilter();

                    state._fsp--;

                     after(grammarAccess.getFilterDetailsAccess().getThresholdFilterParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:429:6: ( ruleTimeFilter )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:429:6: ( ruleTimeFilter )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:430:1: ruleTimeFilter
                    {
                     before(grammarAccess.getFilterDetailsAccess().getTimeFilterParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleTimeFilter_in_rule__FilterDetails__Alternatives857);
                    ruleTimeFilter();

                    state._fsp--;

                     after(grammarAccess.getFilterDetailsAccess().getTimeFilterParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FilterDetails__Alternatives"


    // $ANTLR start "rule__TimeFilter__UnitAlternatives_1_0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:440:1: rule__TimeFilter__UnitAlternatives_1_0 : ( ( 's' ) | ( 'm' ) | ( 'h' ) | ( 'd' ) );
    public final void rule__TimeFilter__UnitAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:444:1: ( ( 's' ) | ( 'm' ) | ( 'h' ) | ( 'd' ) )
            int alt3=4;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt3=1;
                }
                break;
            case 12:
                {
                alt3=2;
                }
                break;
            case 13:
                {
                alt3=3;
                }
                break;
            case 14:
                {
                alt3=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:445:1: ( 's' )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:445:1: ( 's' )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:446:1: 's'
                    {
                     before(grammarAccess.getTimeFilterAccess().getUnitSKeyword_1_0_0()); 
                    match(input,11,FOLLOW_11_in_rule__TimeFilter__UnitAlternatives_1_0890); 
                     after(grammarAccess.getTimeFilterAccess().getUnitSKeyword_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:453:6: ( 'm' )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:453:6: ( 'm' )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:454:1: 'm'
                    {
                     before(grammarAccess.getTimeFilterAccess().getUnitMKeyword_1_0_1()); 
                    match(input,12,FOLLOW_12_in_rule__TimeFilter__UnitAlternatives_1_0910); 
                     after(grammarAccess.getTimeFilterAccess().getUnitMKeyword_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:461:6: ( 'h' )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:461:6: ( 'h' )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:462:1: 'h'
                    {
                     before(grammarAccess.getTimeFilterAccess().getUnitHKeyword_1_0_2()); 
                    match(input,13,FOLLOW_13_in_rule__TimeFilter__UnitAlternatives_1_0930); 
                     after(grammarAccess.getTimeFilterAccess().getUnitHKeyword_1_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:469:6: ( 'd' )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:469:6: ( 'd' )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:470:1: 'd'
                    {
                     before(grammarAccess.getTimeFilterAccess().getUnitDKeyword_1_0_3()); 
                    match(input,14,FOLLOW_14_in_rule__TimeFilter__UnitAlternatives_1_0950); 
                     after(grammarAccess.getTimeFilterAccess().getUnitDKeyword_1_0_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TimeFilter__UnitAlternatives_1_0"


    // $ANTLR start "rule__PersistenceConfiguration__ItemsAlternatives_0_0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:482:1: rule__PersistenceConfiguration__ItemsAlternatives_0_0 : ( ( ruleAllConfig ) | ( ruleItemConfig ) | ( ruleGroupConfig ) );
    public final void rule__PersistenceConfiguration__ItemsAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:486:1: ( ( ruleAllConfig ) | ( ruleItemConfig ) | ( ruleGroupConfig ) )
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==29) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_ID) ) {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==15||LA4_2==21||LA4_2==24||LA4_2==26) ) {
                    alt4=2;
                }
                else if ( (LA4_2==29) ) {
                    alt4=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:487:1: ( ruleAllConfig )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:487:1: ( ruleAllConfig )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:488:1: ruleAllConfig
                    {
                     before(grammarAccess.getPersistenceConfigurationAccess().getItemsAllConfigParserRuleCall_0_0_0()); 
                    pushFollow(FOLLOW_ruleAllConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_0_0984);
                    ruleAllConfig();

                    state._fsp--;

                     after(grammarAccess.getPersistenceConfigurationAccess().getItemsAllConfigParserRuleCall_0_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:493:6: ( ruleItemConfig )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:493:6: ( ruleItemConfig )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:494:1: ruleItemConfig
                    {
                     before(grammarAccess.getPersistenceConfigurationAccess().getItemsItemConfigParserRuleCall_0_0_1()); 
                    pushFollow(FOLLOW_ruleItemConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_0_01001);
                    ruleItemConfig();

                    state._fsp--;

                     after(grammarAccess.getPersistenceConfigurationAccess().getItemsItemConfigParserRuleCall_0_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:499:6: ( ruleGroupConfig )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:499:6: ( ruleGroupConfig )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:500:1: ruleGroupConfig
                    {
                     before(grammarAccess.getPersistenceConfigurationAccess().getItemsGroupConfigParserRuleCall_0_0_2()); 
                    pushFollow(FOLLOW_ruleGroupConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_0_01018);
                    ruleGroupConfig();

                    state._fsp--;

                     after(grammarAccess.getPersistenceConfigurationAccess().getItemsGroupConfigParserRuleCall_0_0_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__ItemsAlternatives_0_0"


    // $ANTLR start "rule__PersistenceConfiguration__ItemsAlternatives_1_1_0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:510:1: rule__PersistenceConfiguration__ItemsAlternatives_1_1_0 : ( ( ruleAllConfig ) | ( ruleItemConfig ) | ( ruleGroupConfig ) );
    public final void rule__PersistenceConfiguration__ItemsAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:514:1: ( ( ruleAllConfig ) | ( ruleItemConfig ) | ( ruleGroupConfig ) )
            int alt5=3;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==29) ) {
                alt5=1;
            }
            else if ( (LA5_0==RULE_ID) ) {
                int LA5_2 = input.LA(2);

                if ( (LA5_2==15||LA5_2==21||LA5_2==24||LA5_2==26) ) {
                    alt5=2;
                }
                else if ( (LA5_2==29) ) {
                    alt5=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:515:1: ( ruleAllConfig )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:515:1: ( ruleAllConfig )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:516:1: ruleAllConfig
                    {
                     before(grammarAccess.getPersistenceConfigurationAccess().getItemsAllConfigParserRuleCall_1_1_0_0()); 
                    pushFollow(FOLLOW_ruleAllConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_1_1_01050);
                    ruleAllConfig();

                    state._fsp--;

                     after(grammarAccess.getPersistenceConfigurationAccess().getItemsAllConfigParserRuleCall_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:521:6: ( ruleItemConfig )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:521:6: ( ruleItemConfig )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:522:1: ruleItemConfig
                    {
                     before(grammarAccess.getPersistenceConfigurationAccess().getItemsItemConfigParserRuleCall_1_1_0_1()); 
                    pushFollow(FOLLOW_ruleItemConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_1_1_01067);
                    ruleItemConfig();

                    state._fsp--;

                     after(grammarAccess.getPersistenceConfigurationAccess().getItemsItemConfigParserRuleCall_1_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:527:6: ( ruleGroupConfig )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:527:6: ( ruleGroupConfig )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:528:1: ruleGroupConfig
                    {
                     before(grammarAccess.getPersistenceConfigurationAccess().getItemsGroupConfigParserRuleCall_1_1_0_2()); 
                    pushFollow(FOLLOW_ruleGroupConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_1_1_01084);
                    ruleGroupConfig();

                    state._fsp--;

                     after(grammarAccess.getPersistenceConfigurationAccess().getItemsGroupConfigParserRuleCall_1_1_0_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__ItemsAlternatives_1_1_0"


    // $ANTLR start "rule__PersistenceConfiguration__Alternatives_3"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:538:1: rule__PersistenceConfiguration__Alternatives_3 : ( ( ( rule__PersistenceConfiguration__Group_3_0__0 ) ) | ( ';' ) );
    public final void rule__PersistenceConfiguration__Alternatives_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:542:1: ( ( ( rule__PersistenceConfiguration__Group_3_0__0 ) ) | ( ';' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==24) ) {
                alt6=1;
            }
            else if ( (LA6_0==15) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:543:1: ( ( rule__PersistenceConfiguration__Group_3_0__0 ) )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:543:1: ( ( rule__PersistenceConfiguration__Group_3_0__0 ) )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:544:1: ( rule__PersistenceConfiguration__Group_3_0__0 )
                    {
                     before(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0()); 
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:545:1: ( rule__PersistenceConfiguration__Group_3_0__0 )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:545:2: rule__PersistenceConfiguration__Group_3_0__0
                    {
                    pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0__0_in_rule__PersistenceConfiguration__Alternatives_31116);
                    rule__PersistenceConfiguration__Group_3_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:549:6: ( ';' )
                    {
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:549:6: ( ';' )
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:550:1: ';'
                    {
                     before(grammarAccess.getPersistenceConfigurationAccess().getSemicolonKeyword_3_1()); 
                    match(input,15,FOLLOW_15_in_rule__PersistenceConfiguration__Alternatives_31135); 
                     after(grammarAccess.getPersistenceConfigurationAccess().getSemicolonKeyword_3_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Alternatives_3"


    // $ANTLR start "rule__PersistenceModel__Group__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:564:1: rule__PersistenceModel__Group__0 : rule__PersistenceModel__Group__0__Impl rule__PersistenceModel__Group__1 ;
    public final void rule__PersistenceModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:568:1: ( rule__PersistenceModel__Group__0__Impl rule__PersistenceModel__Group__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:569:2: rule__PersistenceModel__Group__0__Impl rule__PersistenceModel__Group__1
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group__0__Impl_in_rule__PersistenceModel__Group__01167);
            rule__PersistenceModel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group__1_in_rule__PersistenceModel__Group__01170);
            rule__PersistenceModel__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__0"


    // $ANTLR start "rule__PersistenceModel__Group__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:576:1: rule__PersistenceModel__Group__0__Impl : ( () ) ;
    public final void rule__PersistenceModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:580:1: ( ( () ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:581:1: ( () )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:581:1: ( () )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:582:1: ()
            {
             before(grammarAccess.getPersistenceModelAccess().getPersistenceModelAction_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:583:1: ()
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:585:1: 
            {
            }

             after(grammarAccess.getPersistenceModelAccess().getPersistenceModelAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__0__Impl"


    // $ANTLR start "rule__PersistenceModel__Group__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:595:1: rule__PersistenceModel__Group__1 : rule__PersistenceModel__Group__1__Impl rule__PersistenceModel__Group__2 ;
    public final void rule__PersistenceModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:599:1: ( rule__PersistenceModel__Group__1__Impl rule__PersistenceModel__Group__2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:600:2: rule__PersistenceModel__Group__1__Impl rule__PersistenceModel__Group__2
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group__1__Impl_in_rule__PersistenceModel__Group__11228);
            rule__PersistenceModel__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group__2_in_rule__PersistenceModel__Group__11231);
            rule__PersistenceModel__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__1"


    // $ANTLR start "rule__PersistenceModel__Group__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:607:1: rule__PersistenceModel__Group__1__Impl : ( 'Strategies' ) ;
    public final void rule__PersistenceModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:611:1: ( ( 'Strategies' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:612:1: ( 'Strategies' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:612:1: ( 'Strategies' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:613:1: 'Strategies'
            {
             before(grammarAccess.getPersistenceModelAccess().getStrategiesKeyword_1()); 
            match(input,16,FOLLOW_16_in_rule__PersistenceModel__Group__1__Impl1259); 
             after(grammarAccess.getPersistenceModelAccess().getStrategiesKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__1__Impl"


    // $ANTLR start "rule__PersistenceModel__Group__2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:626:1: rule__PersistenceModel__Group__2 : rule__PersistenceModel__Group__2__Impl rule__PersistenceModel__Group__3 ;
    public final void rule__PersistenceModel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:630:1: ( rule__PersistenceModel__Group__2__Impl rule__PersistenceModel__Group__3 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:631:2: rule__PersistenceModel__Group__2__Impl rule__PersistenceModel__Group__3
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group__2__Impl_in_rule__PersistenceModel__Group__21290);
            rule__PersistenceModel__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group__3_in_rule__PersistenceModel__Group__21293);
            rule__PersistenceModel__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__2"


    // $ANTLR start "rule__PersistenceModel__Group__2__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:638:1: rule__PersistenceModel__Group__2__Impl : ( '{' ) ;
    public final void rule__PersistenceModel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:642:1: ( ( '{' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:643:1: ( '{' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:643:1: ( '{' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:644:1: '{'
            {
             before(grammarAccess.getPersistenceModelAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,17,FOLLOW_17_in_rule__PersistenceModel__Group__2__Impl1321); 
             after(grammarAccess.getPersistenceModelAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__2__Impl"


    // $ANTLR start "rule__PersistenceModel__Group__3"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:657:1: rule__PersistenceModel__Group__3 : rule__PersistenceModel__Group__3__Impl rule__PersistenceModel__Group__4 ;
    public final void rule__PersistenceModel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:661:1: ( rule__PersistenceModel__Group__3__Impl rule__PersistenceModel__Group__4 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:662:2: rule__PersistenceModel__Group__3__Impl rule__PersistenceModel__Group__4
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group__3__Impl_in_rule__PersistenceModel__Group__31352);
            rule__PersistenceModel__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group__4_in_rule__PersistenceModel__Group__31355);
            rule__PersistenceModel__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__3"


    // $ANTLR start "rule__PersistenceModel__Group__3__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:669:1: rule__PersistenceModel__Group__3__Impl : ( ( rule__PersistenceModel__StrategiesAssignment_3 )* ) ;
    public final void rule__PersistenceModel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:673:1: ( ( ( rule__PersistenceModel__StrategiesAssignment_3 )* ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:674:1: ( ( rule__PersistenceModel__StrategiesAssignment_3 )* )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:674:1: ( ( rule__PersistenceModel__StrategiesAssignment_3 )* )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:675:1: ( rule__PersistenceModel__StrategiesAssignment_3 )*
            {
             before(grammarAccess.getPersistenceModelAccess().getStrategiesAssignment_3()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:676:1: ( rule__PersistenceModel__StrategiesAssignment_3 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_ID) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:676:2: rule__PersistenceModel__StrategiesAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__PersistenceModel__StrategiesAssignment_3_in_rule__PersistenceModel__Group__3__Impl1382);
            	    rule__PersistenceModel__StrategiesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getPersistenceModelAccess().getStrategiesAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__3__Impl"


    // $ANTLR start "rule__PersistenceModel__Group__4"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:686:1: rule__PersistenceModel__Group__4 : rule__PersistenceModel__Group__4__Impl rule__PersistenceModel__Group__5 ;
    public final void rule__PersistenceModel__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:690:1: ( rule__PersistenceModel__Group__4__Impl rule__PersistenceModel__Group__5 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:691:2: rule__PersistenceModel__Group__4__Impl rule__PersistenceModel__Group__5
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group__4__Impl_in_rule__PersistenceModel__Group__41413);
            rule__PersistenceModel__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group__5_in_rule__PersistenceModel__Group__41416);
            rule__PersistenceModel__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__4"


    // $ANTLR start "rule__PersistenceModel__Group__4__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:698:1: rule__PersistenceModel__Group__4__Impl : ( ( rule__PersistenceModel__Group_4__0 )? ) ;
    public final void rule__PersistenceModel__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:702:1: ( ( ( rule__PersistenceModel__Group_4__0 )? ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:703:1: ( ( rule__PersistenceModel__Group_4__0 )? )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:703:1: ( ( rule__PersistenceModel__Group_4__0 )? )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:704:1: ( rule__PersistenceModel__Group_4__0 )?
            {
             before(grammarAccess.getPersistenceModelAccess().getGroup_4()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:705:1: ( rule__PersistenceModel__Group_4__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==19) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:705:2: rule__PersistenceModel__Group_4__0
                    {
                    pushFollow(FOLLOW_rule__PersistenceModel__Group_4__0_in_rule__PersistenceModel__Group__4__Impl1443);
                    rule__PersistenceModel__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getPersistenceModelAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__4__Impl"


    // $ANTLR start "rule__PersistenceModel__Group__5"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:715:1: rule__PersistenceModel__Group__5 : rule__PersistenceModel__Group__5__Impl rule__PersistenceModel__Group__6 ;
    public final void rule__PersistenceModel__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:719:1: ( rule__PersistenceModel__Group__5__Impl rule__PersistenceModel__Group__6 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:720:2: rule__PersistenceModel__Group__5__Impl rule__PersistenceModel__Group__6
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group__5__Impl_in_rule__PersistenceModel__Group__51474);
            rule__PersistenceModel__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group__6_in_rule__PersistenceModel__Group__51477);
            rule__PersistenceModel__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__5"


    // $ANTLR start "rule__PersistenceModel__Group__5__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:727:1: rule__PersistenceModel__Group__5__Impl : ( '}' ) ;
    public final void rule__PersistenceModel__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:731:1: ( ( '}' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:732:1: ( '}' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:732:1: ( '}' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:733:1: '}'
            {
             before(grammarAccess.getPersistenceModelAccess().getRightCurlyBracketKeyword_5()); 
            match(input,18,FOLLOW_18_in_rule__PersistenceModel__Group__5__Impl1505); 
             after(grammarAccess.getPersistenceModelAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__5__Impl"


    // $ANTLR start "rule__PersistenceModel__Group__6"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:746:1: rule__PersistenceModel__Group__6 : rule__PersistenceModel__Group__6__Impl rule__PersistenceModel__Group__7 ;
    public final void rule__PersistenceModel__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:750:1: ( rule__PersistenceModel__Group__6__Impl rule__PersistenceModel__Group__7 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:751:2: rule__PersistenceModel__Group__6__Impl rule__PersistenceModel__Group__7
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group__6__Impl_in_rule__PersistenceModel__Group__61536);
            rule__PersistenceModel__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group__7_in_rule__PersistenceModel__Group__61539);
            rule__PersistenceModel__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__6"


    // $ANTLR start "rule__PersistenceModel__Group__6__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:758:1: rule__PersistenceModel__Group__6__Impl : ( ( rule__PersistenceModel__Group_6__0 )? ) ;
    public final void rule__PersistenceModel__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:762:1: ( ( ( rule__PersistenceModel__Group_6__0 )? ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:763:1: ( ( rule__PersistenceModel__Group_6__0 )? )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:763:1: ( ( rule__PersistenceModel__Group_6__0 )? )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:764:1: ( rule__PersistenceModel__Group_6__0 )?
            {
             before(grammarAccess.getPersistenceModelAccess().getGroup_6()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:765:1: ( rule__PersistenceModel__Group_6__0 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==22) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:765:2: rule__PersistenceModel__Group_6__0
                    {
                    pushFollow(FOLLOW_rule__PersistenceModel__Group_6__0_in_rule__PersistenceModel__Group__6__Impl1566);
                    rule__PersistenceModel__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getPersistenceModelAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__6__Impl"


    // $ANTLR start "rule__PersistenceModel__Group__7"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:775:1: rule__PersistenceModel__Group__7 : rule__PersistenceModel__Group__7__Impl ;
    public final void rule__PersistenceModel__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:779:1: ( rule__PersistenceModel__Group__7__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:780:2: rule__PersistenceModel__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group__7__Impl_in_rule__PersistenceModel__Group__71597);
            rule__PersistenceModel__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__7"


    // $ANTLR start "rule__PersistenceModel__Group__7__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:786:1: rule__PersistenceModel__Group__7__Impl : ( ( rule__PersistenceModel__Group_7__0 )? ) ;
    public final void rule__PersistenceModel__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:790:1: ( ( ( rule__PersistenceModel__Group_7__0 )? ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:791:1: ( ( rule__PersistenceModel__Group_7__0 )? )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:791:1: ( ( rule__PersistenceModel__Group_7__0 )? )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:792:1: ( rule__PersistenceModel__Group_7__0 )?
            {
             before(grammarAccess.getPersistenceModelAccess().getGroup_7()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:793:1: ( rule__PersistenceModel__Group_7__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==23) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:793:2: rule__PersistenceModel__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__PersistenceModel__Group_7__0_in_rule__PersistenceModel__Group__7__Impl1624);
                    rule__PersistenceModel__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getPersistenceModelAccess().getGroup_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group__7__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_4__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:819:1: rule__PersistenceModel__Group_4__0 : rule__PersistenceModel__Group_4__0__Impl rule__PersistenceModel__Group_4__1 ;
    public final void rule__PersistenceModel__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:823:1: ( rule__PersistenceModel__Group_4__0__Impl rule__PersistenceModel__Group_4__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:824:2: rule__PersistenceModel__Group_4__0__Impl rule__PersistenceModel__Group_4__1
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_4__0__Impl_in_rule__PersistenceModel__Group_4__01671);
            rule__PersistenceModel__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group_4__1_in_rule__PersistenceModel__Group_4__01674);
            rule__PersistenceModel__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4__0"


    // $ANTLR start "rule__PersistenceModel__Group_4__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:831:1: rule__PersistenceModel__Group_4__0__Impl : ( 'default' ) ;
    public final void rule__PersistenceModel__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:835:1: ( ( 'default' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:836:1: ( 'default' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:836:1: ( 'default' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:837:1: 'default'
            {
             before(grammarAccess.getPersistenceModelAccess().getDefaultKeyword_4_0()); 
            match(input,19,FOLLOW_19_in_rule__PersistenceModel__Group_4__0__Impl1702); 
             after(grammarAccess.getPersistenceModelAccess().getDefaultKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4__0__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_4__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:850:1: rule__PersistenceModel__Group_4__1 : rule__PersistenceModel__Group_4__1__Impl rule__PersistenceModel__Group_4__2 ;
    public final void rule__PersistenceModel__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:854:1: ( rule__PersistenceModel__Group_4__1__Impl rule__PersistenceModel__Group_4__2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:855:2: rule__PersistenceModel__Group_4__1__Impl rule__PersistenceModel__Group_4__2
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_4__1__Impl_in_rule__PersistenceModel__Group_4__11733);
            rule__PersistenceModel__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group_4__2_in_rule__PersistenceModel__Group_4__11736);
            rule__PersistenceModel__Group_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4__1"


    // $ANTLR start "rule__PersistenceModel__Group_4__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:862:1: rule__PersistenceModel__Group_4__1__Impl : ( '=' ) ;
    public final void rule__PersistenceModel__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:866:1: ( ( '=' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:867:1: ( '=' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:867:1: ( '=' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:868:1: '='
            {
             before(grammarAccess.getPersistenceModelAccess().getEqualsSignKeyword_4_1()); 
            match(input,20,FOLLOW_20_in_rule__PersistenceModel__Group_4__1__Impl1764); 
             after(grammarAccess.getPersistenceModelAccess().getEqualsSignKeyword_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4__1__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_4__2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:881:1: rule__PersistenceModel__Group_4__2 : rule__PersistenceModel__Group_4__2__Impl rule__PersistenceModel__Group_4__3 ;
    public final void rule__PersistenceModel__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:885:1: ( rule__PersistenceModel__Group_4__2__Impl rule__PersistenceModel__Group_4__3 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:886:2: rule__PersistenceModel__Group_4__2__Impl rule__PersistenceModel__Group_4__3
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_4__2__Impl_in_rule__PersistenceModel__Group_4__21795);
            rule__PersistenceModel__Group_4__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group_4__3_in_rule__PersistenceModel__Group_4__21798);
            rule__PersistenceModel__Group_4__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4__2"


    // $ANTLR start "rule__PersistenceModel__Group_4__2__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:893:1: rule__PersistenceModel__Group_4__2__Impl : ( ( rule__PersistenceModel__DefaultsAssignment_4_2 ) ) ;
    public final void rule__PersistenceModel__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:897:1: ( ( ( rule__PersistenceModel__DefaultsAssignment_4_2 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:898:1: ( ( rule__PersistenceModel__DefaultsAssignment_4_2 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:898:1: ( ( rule__PersistenceModel__DefaultsAssignment_4_2 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:899:1: ( rule__PersistenceModel__DefaultsAssignment_4_2 )
            {
             before(grammarAccess.getPersistenceModelAccess().getDefaultsAssignment_4_2()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:900:1: ( rule__PersistenceModel__DefaultsAssignment_4_2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:900:2: rule__PersistenceModel__DefaultsAssignment_4_2
            {
            pushFollow(FOLLOW_rule__PersistenceModel__DefaultsAssignment_4_2_in_rule__PersistenceModel__Group_4__2__Impl1825);
            rule__PersistenceModel__DefaultsAssignment_4_2();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceModelAccess().getDefaultsAssignment_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4__2__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_4__3"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:910:1: rule__PersistenceModel__Group_4__3 : rule__PersistenceModel__Group_4__3__Impl ;
    public final void rule__PersistenceModel__Group_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:914:1: ( rule__PersistenceModel__Group_4__3__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:915:2: rule__PersistenceModel__Group_4__3__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_4__3__Impl_in_rule__PersistenceModel__Group_4__31855);
            rule__PersistenceModel__Group_4__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4__3"


    // $ANTLR start "rule__PersistenceModel__Group_4__3__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:921:1: rule__PersistenceModel__Group_4__3__Impl : ( ( rule__PersistenceModel__Group_4_3__0 )* ) ;
    public final void rule__PersistenceModel__Group_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:925:1: ( ( ( rule__PersistenceModel__Group_4_3__0 )* ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:926:1: ( ( rule__PersistenceModel__Group_4_3__0 )* )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:926:1: ( ( rule__PersistenceModel__Group_4_3__0 )* )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:927:1: ( rule__PersistenceModel__Group_4_3__0 )*
            {
             before(grammarAccess.getPersistenceModelAccess().getGroup_4_3()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:928:1: ( rule__PersistenceModel__Group_4_3__0 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==21) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:928:2: rule__PersistenceModel__Group_4_3__0
            	    {
            	    pushFollow(FOLLOW_rule__PersistenceModel__Group_4_3__0_in_rule__PersistenceModel__Group_4__3__Impl1882);
            	    rule__PersistenceModel__Group_4_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getPersistenceModelAccess().getGroup_4_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4__3__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_4_3__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:946:1: rule__PersistenceModel__Group_4_3__0 : rule__PersistenceModel__Group_4_3__0__Impl rule__PersistenceModel__Group_4_3__1 ;
    public final void rule__PersistenceModel__Group_4_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:950:1: ( rule__PersistenceModel__Group_4_3__0__Impl rule__PersistenceModel__Group_4_3__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:951:2: rule__PersistenceModel__Group_4_3__0__Impl rule__PersistenceModel__Group_4_3__1
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_4_3__0__Impl_in_rule__PersistenceModel__Group_4_3__01921);
            rule__PersistenceModel__Group_4_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group_4_3__1_in_rule__PersistenceModel__Group_4_3__01924);
            rule__PersistenceModel__Group_4_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4_3__0"


    // $ANTLR start "rule__PersistenceModel__Group_4_3__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:958:1: rule__PersistenceModel__Group_4_3__0__Impl : ( ',' ) ;
    public final void rule__PersistenceModel__Group_4_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:962:1: ( ( ',' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:963:1: ( ',' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:963:1: ( ',' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:964:1: ','
            {
             before(grammarAccess.getPersistenceModelAccess().getCommaKeyword_4_3_0()); 
            match(input,21,FOLLOW_21_in_rule__PersistenceModel__Group_4_3__0__Impl1952); 
             after(grammarAccess.getPersistenceModelAccess().getCommaKeyword_4_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4_3__0__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_4_3__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:977:1: rule__PersistenceModel__Group_4_3__1 : rule__PersistenceModel__Group_4_3__1__Impl ;
    public final void rule__PersistenceModel__Group_4_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:981:1: ( rule__PersistenceModel__Group_4_3__1__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:982:2: rule__PersistenceModel__Group_4_3__1__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_4_3__1__Impl_in_rule__PersistenceModel__Group_4_3__11983);
            rule__PersistenceModel__Group_4_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4_3__1"


    // $ANTLR start "rule__PersistenceModel__Group_4_3__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:988:1: rule__PersistenceModel__Group_4_3__1__Impl : ( ( rule__PersistenceModel__DefaultsAssignment_4_3_1 ) ) ;
    public final void rule__PersistenceModel__Group_4_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:992:1: ( ( ( rule__PersistenceModel__DefaultsAssignment_4_3_1 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:993:1: ( ( rule__PersistenceModel__DefaultsAssignment_4_3_1 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:993:1: ( ( rule__PersistenceModel__DefaultsAssignment_4_3_1 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:994:1: ( rule__PersistenceModel__DefaultsAssignment_4_3_1 )
            {
             before(grammarAccess.getPersistenceModelAccess().getDefaultsAssignment_4_3_1()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:995:1: ( rule__PersistenceModel__DefaultsAssignment_4_3_1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:995:2: rule__PersistenceModel__DefaultsAssignment_4_3_1
            {
            pushFollow(FOLLOW_rule__PersistenceModel__DefaultsAssignment_4_3_1_in_rule__PersistenceModel__Group_4_3__1__Impl2010);
            rule__PersistenceModel__DefaultsAssignment_4_3_1();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceModelAccess().getDefaultsAssignment_4_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_4_3__1__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_6__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1009:1: rule__PersistenceModel__Group_6__0 : rule__PersistenceModel__Group_6__0__Impl rule__PersistenceModel__Group_6__1 ;
    public final void rule__PersistenceModel__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1013:1: ( rule__PersistenceModel__Group_6__0__Impl rule__PersistenceModel__Group_6__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1014:2: rule__PersistenceModel__Group_6__0__Impl rule__PersistenceModel__Group_6__1
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_6__0__Impl_in_rule__PersistenceModel__Group_6__02044);
            rule__PersistenceModel__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group_6__1_in_rule__PersistenceModel__Group_6__02047);
            rule__PersistenceModel__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_6__0"


    // $ANTLR start "rule__PersistenceModel__Group_6__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1021:1: rule__PersistenceModel__Group_6__0__Impl : ( 'Filters' ) ;
    public final void rule__PersistenceModel__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1025:1: ( ( 'Filters' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1026:1: ( 'Filters' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1026:1: ( 'Filters' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1027:1: 'Filters'
            {
             before(grammarAccess.getPersistenceModelAccess().getFiltersKeyword_6_0()); 
            match(input,22,FOLLOW_22_in_rule__PersistenceModel__Group_6__0__Impl2075); 
             after(grammarAccess.getPersistenceModelAccess().getFiltersKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_6__0__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_6__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1040:1: rule__PersistenceModel__Group_6__1 : rule__PersistenceModel__Group_6__1__Impl rule__PersistenceModel__Group_6__2 ;
    public final void rule__PersistenceModel__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1044:1: ( rule__PersistenceModel__Group_6__1__Impl rule__PersistenceModel__Group_6__2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1045:2: rule__PersistenceModel__Group_6__1__Impl rule__PersistenceModel__Group_6__2
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_6__1__Impl_in_rule__PersistenceModel__Group_6__12106);
            rule__PersistenceModel__Group_6__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group_6__2_in_rule__PersistenceModel__Group_6__12109);
            rule__PersistenceModel__Group_6__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_6__1"


    // $ANTLR start "rule__PersistenceModel__Group_6__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1052:1: rule__PersistenceModel__Group_6__1__Impl : ( '{' ) ;
    public final void rule__PersistenceModel__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1056:1: ( ( '{' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1057:1: ( '{' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1057:1: ( '{' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1058:1: '{'
            {
             before(grammarAccess.getPersistenceModelAccess().getLeftCurlyBracketKeyword_6_1()); 
            match(input,17,FOLLOW_17_in_rule__PersistenceModel__Group_6__1__Impl2137); 
             after(grammarAccess.getPersistenceModelAccess().getLeftCurlyBracketKeyword_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_6__1__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_6__2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1071:1: rule__PersistenceModel__Group_6__2 : rule__PersistenceModel__Group_6__2__Impl rule__PersistenceModel__Group_6__3 ;
    public final void rule__PersistenceModel__Group_6__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1075:1: ( rule__PersistenceModel__Group_6__2__Impl rule__PersistenceModel__Group_6__3 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1076:2: rule__PersistenceModel__Group_6__2__Impl rule__PersistenceModel__Group_6__3
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_6__2__Impl_in_rule__PersistenceModel__Group_6__22168);
            rule__PersistenceModel__Group_6__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group_6__3_in_rule__PersistenceModel__Group_6__22171);
            rule__PersistenceModel__Group_6__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_6__2"


    // $ANTLR start "rule__PersistenceModel__Group_6__2__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1083:1: rule__PersistenceModel__Group_6__2__Impl : ( ( rule__PersistenceModel__FiltersAssignment_6_2 )* ) ;
    public final void rule__PersistenceModel__Group_6__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1087:1: ( ( ( rule__PersistenceModel__FiltersAssignment_6_2 )* ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1088:1: ( ( rule__PersistenceModel__FiltersAssignment_6_2 )* )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1088:1: ( ( rule__PersistenceModel__FiltersAssignment_6_2 )* )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1089:1: ( rule__PersistenceModel__FiltersAssignment_6_2 )*
            {
             before(grammarAccess.getPersistenceModelAccess().getFiltersAssignment_6_2()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1090:1: ( rule__PersistenceModel__FiltersAssignment_6_2 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_ID) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1090:2: rule__PersistenceModel__FiltersAssignment_6_2
            	    {
            	    pushFollow(FOLLOW_rule__PersistenceModel__FiltersAssignment_6_2_in_rule__PersistenceModel__Group_6__2__Impl2198);
            	    rule__PersistenceModel__FiltersAssignment_6_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getPersistenceModelAccess().getFiltersAssignment_6_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_6__2__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_6__3"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1100:1: rule__PersistenceModel__Group_6__3 : rule__PersistenceModel__Group_6__3__Impl ;
    public final void rule__PersistenceModel__Group_6__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1104:1: ( rule__PersistenceModel__Group_6__3__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1105:2: rule__PersistenceModel__Group_6__3__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_6__3__Impl_in_rule__PersistenceModel__Group_6__32229);
            rule__PersistenceModel__Group_6__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_6__3"


    // $ANTLR start "rule__PersistenceModel__Group_6__3__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1111:1: rule__PersistenceModel__Group_6__3__Impl : ( '}' ) ;
    public final void rule__PersistenceModel__Group_6__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1115:1: ( ( '}' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1116:1: ( '}' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1116:1: ( '}' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1117:1: '}'
            {
             before(grammarAccess.getPersistenceModelAccess().getRightCurlyBracketKeyword_6_3()); 
            match(input,18,FOLLOW_18_in_rule__PersistenceModel__Group_6__3__Impl2257); 
             after(grammarAccess.getPersistenceModelAccess().getRightCurlyBracketKeyword_6_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_6__3__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_7__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1138:1: rule__PersistenceModel__Group_7__0 : rule__PersistenceModel__Group_7__0__Impl rule__PersistenceModel__Group_7__1 ;
    public final void rule__PersistenceModel__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1142:1: ( rule__PersistenceModel__Group_7__0__Impl rule__PersistenceModel__Group_7__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1143:2: rule__PersistenceModel__Group_7__0__Impl rule__PersistenceModel__Group_7__1
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_7__0__Impl_in_rule__PersistenceModel__Group_7__02296);
            rule__PersistenceModel__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group_7__1_in_rule__PersistenceModel__Group_7__02299);
            rule__PersistenceModel__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_7__0"


    // $ANTLR start "rule__PersistenceModel__Group_7__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1150:1: rule__PersistenceModel__Group_7__0__Impl : ( 'Items' ) ;
    public final void rule__PersistenceModel__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1154:1: ( ( 'Items' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1155:1: ( 'Items' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1155:1: ( 'Items' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1156:1: 'Items'
            {
             before(grammarAccess.getPersistenceModelAccess().getItemsKeyword_7_0()); 
            match(input,23,FOLLOW_23_in_rule__PersistenceModel__Group_7__0__Impl2327); 
             after(grammarAccess.getPersistenceModelAccess().getItemsKeyword_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_7__0__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_7__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1169:1: rule__PersistenceModel__Group_7__1 : rule__PersistenceModel__Group_7__1__Impl rule__PersistenceModel__Group_7__2 ;
    public final void rule__PersistenceModel__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1173:1: ( rule__PersistenceModel__Group_7__1__Impl rule__PersistenceModel__Group_7__2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1174:2: rule__PersistenceModel__Group_7__1__Impl rule__PersistenceModel__Group_7__2
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_7__1__Impl_in_rule__PersistenceModel__Group_7__12358);
            rule__PersistenceModel__Group_7__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group_7__2_in_rule__PersistenceModel__Group_7__12361);
            rule__PersistenceModel__Group_7__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_7__1"


    // $ANTLR start "rule__PersistenceModel__Group_7__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1181:1: rule__PersistenceModel__Group_7__1__Impl : ( '{' ) ;
    public final void rule__PersistenceModel__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1185:1: ( ( '{' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1186:1: ( '{' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1186:1: ( '{' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1187:1: '{'
            {
             before(grammarAccess.getPersistenceModelAccess().getLeftCurlyBracketKeyword_7_1()); 
            match(input,17,FOLLOW_17_in_rule__PersistenceModel__Group_7__1__Impl2389); 
             after(grammarAccess.getPersistenceModelAccess().getLeftCurlyBracketKeyword_7_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_7__1__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_7__2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1200:1: rule__PersistenceModel__Group_7__2 : rule__PersistenceModel__Group_7__2__Impl rule__PersistenceModel__Group_7__3 ;
    public final void rule__PersistenceModel__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1204:1: ( rule__PersistenceModel__Group_7__2__Impl rule__PersistenceModel__Group_7__3 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1205:2: rule__PersistenceModel__Group_7__2__Impl rule__PersistenceModel__Group_7__3
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_7__2__Impl_in_rule__PersistenceModel__Group_7__22420);
            rule__PersistenceModel__Group_7__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceModel__Group_7__3_in_rule__PersistenceModel__Group_7__22423);
            rule__PersistenceModel__Group_7__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_7__2"


    // $ANTLR start "rule__PersistenceModel__Group_7__2__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1212:1: rule__PersistenceModel__Group_7__2__Impl : ( ( rule__PersistenceModel__ConfigsAssignment_7_2 )* ) ;
    public final void rule__PersistenceModel__Group_7__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1216:1: ( ( ( rule__PersistenceModel__ConfigsAssignment_7_2 )* ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1217:1: ( ( rule__PersistenceModel__ConfigsAssignment_7_2 )* )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1217:1: ( ( rule__PersistenceModel__ConfigsAssignment_7_2 )* )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1218:1: ( rule__PersistenceModel__ConfigsAssignment_7_2 )*
            {
             before(grammarAccess.getPersistenceModelAccess().getConfigsAssignment_7_2()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1219:1: ( rule__PersistenceModel__ConfigsAssignment_7_2 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_ID||LA13_0==29) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1219:2: rule__PersistenceModel__ConfigsAssignment_7_2
            	    {
            	    pushFollow(FOLLOW_rule__PersistenceModel__ConfigsAssignment_7_2_in_rule__PersistenceModel__Group_7__2__Impl2450);
            	    rule__PersistenceModel__ConfigsAssignment_7_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getPersistenceModelAccess().getConfigsAssignment_7_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_7__2__Impl"


    // $ANTLR start "rule__PersistenceModel__Group_7__3"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1229:1: rule__PersistenceModel__Group_7__3 : rule__PersistenceModel__Group_7__3__Impl ;
    public final void rule__PersistenceModel__Group_7__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1233:1: ( rule__PersistenceModel__Group_7__3__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1234:2: rule__PersistenceModel__Group_7__3__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceModel__Group_7__3__Impl_in_rule__PersistenceModel__Group_7__32481);
            rule__PersistenceModel__Group_7__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_7__3"


    // $ANTLR start "rule__PersistenceModel__Group_7__3__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1240:1: rule__PersistenceModel__Group_7__3__Impl : ( '}' ) ;
    public final void rule__PersistenceModel__Group_7__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1244:1: ( ( '}' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1245:1: ( '}' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1245:1: ( '}' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1246:1: '}'
            {
             before(grammarAccess.getPersistenceModelAccess().getRightCurlyBracketKeyword_7_3()); 
            match(input,18,FOLLOW_18_in_rule__PersistenceModel__Group_7__3__Impl2509); 
             after(grammarAccess.getPersistenceModelAccess().getRightCurlyBracketKeyword_7_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__Group_7__3__Impl"


    // $ANTLR start "rule__CronStrategy__Group__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1267:1: rule__CronStrategy__Group__0 : rule__CronStrategy__Group__0__Impl rule__CronStrategy__Group__1 ;
    public final void rule__CronStrategy__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1271:1: ( rule__CronStrategy__Group__0__Impl rule__CronStrategy__Group__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1272:2: rule__CronStrategy__Group__0__Impl rule__CronStrategy__Group__1
            {
            pushFollow(FOLLOW_rule__CronStrategy__Group__0__Impl_in_rule__CronStrategy__Group__02548);
            rule__CronStrategy__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__CronStrategy__Group__1_in_rule__CronStrategy__Group__02551);
            rule__CronStrategy__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CronStrategy__Group__0"


    // $ANTLR start "rule__CronStrategy__Group__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1279:1: rule__CronStrategy__Group__0__Impl : ( () ) ;
    public final void rule__CronStrategy__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1283:1: ( ( () ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1284:1: ( () )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1284:1: ( () )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1285:1: ()
            {
             before(grammarAccess.getCronStrategyAccess().getCronStrategyAction_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1286:1: ()
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1288:1: 
            {
            }

             after(grammarAccess.getCronStrategyAccess().getCronStrategyAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CronStrategy__Group__0__Impl"


    // $ANTLR start "rule__CronStrategy__Group__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1298:1: rule__CronStrategy__Group__1 : rule__CronStrategy__Group__1__Impl rule__CronStrategy__Group__2 ;
    public final void rule__CronStrategy__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1302:1: ( rule__CronStrategy__Group__1__Impl rule__CronStrategy__Group__2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1303:2: rule__CronStrategy__Group__1__Impl rule__CronStrategy__Group__2
            {
            pushFollow(FOLLOW_rule__CronStrategy__Group__1__Impl_in_rule__CronStrategy__Group__12609);
            rule__CronStrategy__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__CronStrategy__Group__2_in_rule__CronStrategy__Group__12612);
            rule__CronStrategy__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CronStrategy__Group__1"


    // $ANTLR start "rule__CronStrategy__Group__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1310:1: rule__CronStrategy__Group__1__Impl : ( ( rule__CronStrategy__NameAssignment_1 ) ) ;
    public final void rule__CronStrategy__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1314:1: ( ( ( rule__CronStrategy__NameAssignment_1 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1315:1: ( ( rule__CronStrategy__NameAssignment_1 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1315:1: ( ( rule__CronStrategy__NameAssignment_1 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1316:1: ( rule__CronStrategy__NameAssignment_1 )
            {
             before(grammarAccess.getCronStrategyAccess().getNameAssignment_1()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1317:1: ( rule__CronStrategy__NameAssignment_1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1317:2: rule__CronStrategy__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__CronStrategy__NameAssignment_1_in_rule__CronStrategy__Group__1__Impl2639);
            rule__CronStrategy__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getCronStrategyAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CronStrategy__Group__1__Impl"


    // $ANTLR start "rule__CronStrategy__Group__2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1327:1: rule__CronStrategy__Group__2 : rule__CronStrategy__Group__2__Impl rule__CronStrategy__Group__3 ;
    public final void rule__CronStrategy__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1331:1: ( rule__CronStrategy__Group__2__Impl rule__CronStrategy__Group__3 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1332:2: rule__CronStrategy__Group__2__Impl rule__CronStrategy__Group__3
            {
            pushFollow(FOLLOW_rule__CronStrategy__Group__2__Impl_in_rule__CronStrategy__Group__22669);
            rule__CronStrategy__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__CronStrategy__Group__3_in_rule__CronStrategy__Group__22672);
            rule__CronStrategy__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CronStrategy__Group__2"


    // $ANTLR start "rule__CronStrategy__Group__2__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1339:1: rule__CronStrategy__Group__2__Impl : ( ':' ) ;
    public final void rule__CronStrategy__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1343:1: ( ( ':' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1344:1: ( ':' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1344:1: ( ':' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1345:1: ':'
            {
             before(grammarAccess.getCronStrategyAccess().getColonKeyword_2()); 
            match(input,24,FOLLOW_24_in_rule__CronStrategy__Group__2__Impl2700); 
             after(grammarAccess.getCronStrategyAccess().getColonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CronStrategy__Group__2__Impl"


    // $ANTLR start "rule__CronStrategy__Group__3"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1358:1: rule__CronStrategy__Group__3 : rule__CronStrategy__Group__3__Impl ;
    public final void rule__CronStrategy__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1362:1: ( rule__CronStrategy__Group__3__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1363:2: rule__CronStrategy__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__CronStrategy__Group__3__Impl_in_rule__CronStrategy__Group__32731);
            rule__CronStrategy__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CronStrategy__Group__3"


    // $ANTLR start "rule__CronStrategy__Group__3__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1369:1: rule__CronStrategy__Group__3__Impl : ( ( rule__CronStrategy__CronExpressionAssignment_3 ) ) ;
    public final void rule__CronStrategy__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1373:1: ( ( ( rule__CronStrategy__CronExpressionAssignment_3 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1374:1: ( ( rule__CronStrategy__CronExpressionAssignment_3 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1374:1: ( ( rule__CronStrategy__CronExpressionAssignment_3 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1375:1: ( rule__CronStrategy__CronExpressionAssignment_3 )
            {
             before(grammarAccess.getCronStrategyAccess().getCronExpressionAssignment_3()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1376:1: ( rule__CronStrategy__CronExpressionAssignment_3 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1376:2: rule__CronStrategy__CronExpressionAssignment_3
            {
            pushFollow(FOLLOW_rule__CronStrategy__CronExpressionAssignment_3_in_rule__CronStrategy__Group__3__Impl2758);
            rule__CronStrategy__CronExpressionAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getCronStrategyAccess().getCronExpressionAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CronStrategy__Group__3__Impl"


    // $ANTLR start "rule__Filter__Group__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1394:1: rule__Filter__Group__0 : rule__Filter__Group__0__Impl rule__Filter__Group__1 ;
    public final void rule__Filter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1398:1: ( rule__Filter__Group__0__Impl rule__Filter__Group__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1399:2: rule__Filter__Group__0__Impl rule__Filter__Group__1
            {
            pushFollow(FOLLOW_rule__Filter__Group__0__Impl_in_rule__Filter__Group__02796);
            rule__Filter__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Filter__Group__1_in_rule__Filter__Group__02799);
            rule__Filter__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Filter__Group__0"


    // $ANTLR start "rule__Filter__Group__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1406:1: rule__Filter__Group__0__Impl : ( ( rule__Filter__NameAssignment_0 ) ) ;
    public final void rule__Filter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1410:1: ( ( ( rule__Filter__NameAssignment_0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1411:1: ( ( rule__Filter__NameAssignment_0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1411:1: ( ( rule__Filter__NameAssignment_0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1412:1: ( rule__Filter__NameAssignment_0 )
            {
             before(grammarAccess.getFilterAccess().getNameAssignment_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1413:1: ( rule__Filter__NameAssignment_0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1413:2: rule__Filter__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__Filter__NameAssignment_0_in_rule__Filter__Group__0__Impl2826);
            rule__Filter__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getFilterAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Filter__Group__0__Impl"


    // $ANTLR start "rule__Filter__Group__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1423:1: rule__Filter__Group__1 : rule__Filter__Group__1__Impl rule__Filter__Group__2 ;
    public final void rule__Filter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1427:1: ( rule__Filter__Group__1__Impl rule__Filter__Group__2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1428:2: rule__Filter__Group__1__Impl rule__Filter__Group__2
            {
            pushFollow(FOLLOW_rule__Filter__Group__1__Impl_in_rule__Filter__Group__12856);
            rule__Filter__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Filter__Group__2_in_rule__Filter__Group__12859);
            rule__Filter__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Filter__Group__1"


    // $ANTLR start "rule__Filter__Group__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1435:1: rule__Filter__Group__1__Impl : ( ':' ) ;
    public final void rule__Filter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1439:1: ( ( ':' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1440:1: ( ':' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1440:1: ( ':' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1441:1: ':'
            {
             before(grammarAccess.getFilterAccess().getColonKeyword_1()); 
            match(input,24,FOLLOW_24_in_rule__Filter__Group__1__Impl2887); 
             after(grammarAccess.getFilterAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Filter__Group__1__Impl"


    // $ANTLR start "rule__Filter__Group__2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1454:1: rule__Filter__Group__2 : rule__Filter__Group__2__Impl ;
    public final void rule__Filter__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1458:1: ( rule__Filter__Group__2__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1459:2: rule__Filter__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Filter__Group__2__Impl_in_rule__Filter__Group__22918);
            rule__Filter__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Filter__Group__2"


    // $ANTLR start "rule__Filter__Group__2__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1465:1: rule__Filter__Group__2__Impl : ( ( rule__Filter__DefinitionAssignment_2 ) ) ;
    public final void rule__Filter__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1469:1: ( ( ( rule__Filter__DefinitionAssignment_2 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1470:1: ( ( rule__Filter__DefinitionAssignment_2 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1470:1: ( ( rule__Filter__DefinitionAssignment_2 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1471:1: ( rule__Filter__DefinitionAssignment_2 )
            {
             before(grammarAccess.getFilterAccess().getDefinitionAssignment_2()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1472:1: ( rule__Filter__DefinitionAssignment_2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1472:2: rule__Filter__DefinitionAssignment_2
            {
            pushFollow(FOLLOW_rule__Filter__DefinitionAssignment_2_in_rule__Filter__Group__2__Impl2945);
            rule__Filter__DefinitionAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getFilterAccess().getDefinitionAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Filter__Group__2__Impl"


    // $ANTLR start "rule__ThresholdFilter__Group__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1488:1: rule__ThresholdFilter__Group__0 : rule__ThresholdFilter__Group__0__Impl rule__ThresholdFilter__Group__1 ;
    public final void rule__ThresholdFilter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1492:1: ( rule__ThresholdFilter__Group__0__Impl rule__ThresholdFilter__Group__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1493:2: rule__ThresholdFilter__Group__0__Impl rule__ThresholdFilter__Group__1
            {
            pushFollow(FOLLOW_rule__ThresholdFilter__Group__0__Impl_in_rule__ThresholdFilter__Group__02981);
            rule__ThresholdFilter__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ThresholdFilter__Group__1_in_rule__ThresholdFilter__Group__02984);
            rule__ThresholdFilter__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ThresholdFilter__Group__0"


    // $ANTLR start "rule__ThresholdFilter__Group__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1500:1: rule__ThresholdFilter__Group__0__Impl : ( '>' ) ;
    public final void rule__ThresholdFilter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1504:1: ( ( '>' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1505:1: ( '>' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1505:1: ( '>' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1506:1: '>'
            {
             before(grammarAccess.getThresholdFilterAccess().getGreaterThanSignKeyword_0()); 
            match(input,25,FOLLOW_25_in_rule__ThresholdFilter__Group__0__Impl3012); 
             after(grammarAccess.getThresholdFilterAccess().getGreaterThanSignKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ThresholdFilter__Group__0__Impl"


    // $ANTLR start "rule__ThresholdFilter__Group__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1519:1: rule__ThresholdFilter__Group__1 : rule__ThresholdFilter__Group__1__Impl rule__ThresholdFilter__Group__2 ;
    public final void rule__ThresholdFilter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1523:1: ( rule__ThresholdFilter__Group__1__Impl rule__ThresholdFilter__Group__2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1524:2: rule__ThresholdFilter__Group__1__Impl rule__ThresholdFilter__Group__2
            {
            pushFollow(FOLLOW_rule__ThresholdFilter__Group__1__Impl_in_rule__ThresholdFilter__Group__13043);
            rule__ThresholdFilter__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ThresholdFilter__Group__2_in_rule__ThresholdFilter__Group__13046);
            rule__ThresholdFilter__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ThresholdFilter__Group__1"


    // $ANTLR start "rule__ThresholdFilter__Group__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1531:1: rule__ThresholdFilter__Group__1__Impl : ( ( rule__ThresholdFilter__ValueAssignment_1 ) ) ;
    public final void rule__ThresholdFilter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1535:1: ( ( ( rule__ThresholdFilter__ValueAssignment_1 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1536:1: ( ( rule__ThresholdFilter__ValueAssignment_1 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1536:1: ( ( rule__ThresholdFilter__ValueAssignment_1 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1537:1: ( rule__ThresholdFilter__ValueAssignment_1 )
            {
             before(grammarAccess.getThresholdFilterAccess().getValueAssignment_1()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1538:1: ( rule__ThresholdFilter__ValueAssignment_1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1538:2: rule__ThresholdFilter__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__ThresholdFilter__ValueAssignment_1_in_rule__ThresholdFilter__Group__1__Impl3073);
            rule__ThresholdFilter__ValueAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getThresholdFilterAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ThresholdFilter__Group__1__Impl"


    // $ANTLR start "rule__ThresholdFilter__Group__2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1548:1: rule__ThresholdFilter__Group__2 : rule__ThresholdFilter__Group__2__Impl ;
    public final void rule__ThresholdFilter__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1552:1: ( rule__ThresholdFilter__Group__2__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1553:2: rule__ThresholdFilter__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__ThresholdFilter__Group__2__Impl_in_rule__ThresholdFilter__Group__23103);
            rule__ThresholdFilter__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ThresholdFilter__Group__2"


    // $ANTLR start "rule__ThresholdFilter__Group__2__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1559:1: rule__ThresholdFilter__Group__2__Impl : ( ( rule__ThresholdFilter__PercentAssignment_2 ) ) ;
    public final void rule__ThresholdFilter__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1563:1: ( ( ( rule__ThresholdFilter__PercentAssignment_2 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1564:1: ( ( rule__ThresholdFilter__PercentAssignment_2 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1564:1: ( ( rule__ThresholdFilter__PercentAssignment_2 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1565:1: ( rule__ThresholdFilter__PercentAssignment_2 )
            {
             before(grammarAccess.getThresholdFilterAccess().getPercentAssignment_2()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1566:1: ( rule__ThresholdFilter__PercentAssignment_2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1566:2: rule__ThresholdFilter__PercentAssignment_2
            {
            pushFollow(FOLLOW_rule__ThresholdFilter__PercentAssignment_2_in_rule__ThresholdFilter__Group__2__Impl3130);
            rule__ThresholdFilter__PercentAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getThresholdFilterAccess().getPercentAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ThresholdFilter__Group__2__Impl"


    // $ANTLR start "rule__TimeFilter__Group__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1582:1: rule__TimeFilter__Group__0 : rule__TimeFilter__Group__0__Impl rule__TimeFilter__Group__1 ;
    public final void rule__TimeFilter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1586:1: ( rule__TimeFilter__Group__0__Impl rule__TimeFilter__Group__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1587:2: rule__TimeFilter__Group__0__Impl rule__TimeFilter__Group__1
            {
            pushFollow(FOLLOW_rule__TimeFilter__Group__0__Impl_in_rule__TimeFilter__Group__03166);
            rule__TimeFilter__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TimeFilter__Group__1_in_rule__TimeFilter__Group__03169);
            rule__TimeFilter__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TimeFilter__Group__0"


    // $ANTLR start "rule__TimeFilter__Group__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1594:1: rule__TimeFilter__Group__0__Impl : ( ( rule__TimeFilter__ValueAssignment_0 ) ) ;
    public final void rule__TimeFilter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1598:1: ( ( ( rule__TimeFilter__ValueAssignment_0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1599:1: ( ( rule__TimeFilter__ValueAssignment_0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1599:1: ( ( rule__TimeFilter__ValueAssignment_0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1600:1: ( rule__TimeFilter__ValueAssignment_0 )
            {
             before(grammarAccess.getTimeFilterAccess().getValueAssignment_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1601:1: ( rule__TimeFilter__ValueAssignment_0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1601:2: rule__TimeFilter__ValueAssignment_0
            {
            pushFollow(FOLLOW_rule__TimeFilter__ValueAssignment_0_in_rule__TimeFilter__Group__0__Impl3196);
            rule__TimeFilter__ValueAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getTimeFilterAccess().getValueAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TimeFilter__Group__0__Impl"


    // $ANTLR start "rule__TimeFilter__Group__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1611:1: rule__TimeFilter__Group__1 : rule__TimeFilter__Group__1__Impl ;
    public final void rule__TimeFilter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1615:1: ( rule__TimeFilter__Group__1__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1616:2: rule__TimeFilter__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__TimeFilter__Group__1__Impl_in_rule__TimeFilter__Group__13226);
            rule__TimeFilter__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TimeFilter__Group__1"


    // $ANTLR start "rule__TimeFilter__Group__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1622:1: rule__TimeFilter__Group__1__Impl : ( ( rule__TimeFilter__UnitAssignment_1 ) ) ;
    public final void rule__TimeFilter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1626:1: ( ( ( rule__TimeFilter__UnitAssignment_1 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1627:1: ( ( rule__TimeFilter__UnitAssignment_1 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1627:1: ( ( rule__TimeFilter__UnitAssignment_1 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1628:1: ( rule__TimeFilter__UnitAssignment_1 )
            {
             before(grammarAccess.getTimeFilterAccess().getUnitAssignment_1()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1629:1: ( rule__TimeFilter__UnitAssignment_1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1629:2: rule__TimeFilter__UnitAssignment_1
            {
            pushFollow(FOLLOW_rule__TimeFilter__UnitAssignment_1_in_rule__TimeFilter__Group__1__Impl3253);
            rule__TimeFilter__UnitAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getTimeFilterAccess().getUnitAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TimeFilter__Group__1__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1643:1: rule__PersistenceConfiguration__Group__0 : rule__PersistenceConfiguration__Group__0__Impl rule__PersistenceConfiguration__Group__1 ;
    public final void rule__PersistenceConfiguration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1647:1: ( rule__PersistenceConfiguration__Group__0__Impl rule__PersistenceConfiguration__Group__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1648:2: rule__PersistenceConfiguration__Group__0__Impl rule__PersistenceConfiguration__Group__1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group__0__Impl_in_rule__PersistenceConfiguration__Group__03287);
            rule__PersistenceConfiguration__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group__1_in_rule__PersistenceConfiguration__Group__03290);
            rule__PersistenceConfiguration__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group__0"


    // $ANTLR start "rule__PersistenceConfiguration__Group__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1655:1: rule__PersistenceConfiguration__Group__0__Impl : ( ( rule__PersistenceConfiguration__ItemsAssignment_0 ) ) ;
    public final void rule__PersistenceConfiguration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1659:1: ( ( ( rule__PersistenceConfiguration__ItemsAssignment_0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1660:1: ( ( rule__PersistenceConfiguration__ItemsAssignment_0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1660:1: ( ( rule__PersistenceConfiguration__ItemsAssignment_0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1661:1: ( rule__PersistenceConfiguration__ItemsAssignment_0 )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getItemsAssignment_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1662:1: ( rule__PersistenceConfiguration__ItemsAssignment_0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1662:2: rule__PersistenceConfiguration__ItemsAssignment_0
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__ItemsAssignment_0_in_rule__PersistenceConfiguration__Group__0__Impl3317);
            rule__PersistenceConfiguration__ItemsAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceConfigurationAccess().getItemsAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group__0__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1672:1: rule__PersistenceConfiguration__Group__1 : rule__PersistenceConfiguration__Group__1__Impl rule__PersistenceConfiguration__Group__2 ;
    public final void rule__PersistenceConfiguration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1676:1: ( rule__PersistenceConfiguration__Group__1__Impl rule__PersistenceConfiguration__Group__2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1677:2: rule__PersistenceConfiguration__Group__1__Impl rule__PersistenceConfiguration__Group__2
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group__1__Impl_in_rule__PersistenceConfiguration__Group__13347);
            rule__PersistenceConfiguration__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group__2_in_rule__PersistenceConfiguration__Group__13350);
            rule__PersistenceConfiguration__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group__1"


    // $ANTLR start "rule__PersistenceConfiguration__Group__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1684:1: rule__PersistenceConfiguration__Group__1__Impl : ( ( rule__PersistenceConfiguration__Group_1__0 )* ) ;
    public final void rule__PersistenceConfiguration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1688:1: ( ( ( rule__PersistenceConfiguration__Group_1__0 )* ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1689:1: ( ( rule__PersistenceConfiguration__Group_1__0 )* )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1689:1: ( ( rule__PersistenceConfiguration__Group_1__0 )* )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1690:1: ( rule__PersistenceConfiguration__Group_1__0 )*
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getGroup_1()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1691:1: ( rule__PersistenceConfiguration__Group_1__0 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==21) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1691:2: rule__PersistenceConfiguration__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_1__0_in_rule__PersistenceConfiguration__Group__1__Impl3377);
            	    rule__PersistenceConfiguration__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getPersistenceConfigurationAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group__1__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group__2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1701:1: rule__PersistenceConfiguration__Group__2 : rule__PersistenceConfiguration__Group__2__Impl rule__PersistenceConfiguration__Group__3 ;
    public final void rule__PersistenceConfiguration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1705:1: ( rule__PersistenceConfiguration__Group__2__Impl rule__PersistenceConfiguration__Group__3 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1706:2: rule__PersistenceConfiguration__Group__2__Impl rule__PersistenceConfiguration__Group__3
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group__2__Impl_in_rule__PersistenceConfiguration__Group__23408);
            rule__PersistenceConfiguration__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group__3_in_rule__PersistenceConfiguration__Group__23411);
            rule__PersistenceConfiguration__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group__2"


    // $ANTLR start "rule__PersistenceConfiguration__Group__2__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1713:1: rule__PersistenceConfiguration__Group__2__Impl : ( ( rule__PersistenceConfiguration__Group_2__0 )? ) ;
    public final void rule__PersistenceConfiguration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1717:1: ( ( ( rule__PersistenceConfiguration__Group_2__0 )? ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1718:1: ( ( rule__PersistenceConfiguration__Group_2__0 )? )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1718:1: ( ( rule__PersistenceConfiguration__Group_2__0 )? )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1719:1: ( rule__PersistenceConfiguration__Group_2__0 )?
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getGroup_2()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1720:1: ( rule__PersistenceConfiguration__Group_2__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==26) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1720:2: rule__PersistenceConfiguration__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_2__0_in_rule__PersistenceConfiguration__Group__2__Impl3438);
                    rule__PersistenceConfiguration__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getPersistenceConfigurationAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group__2__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group__3"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1730:1: rule__PersistenceConfiguration__Group__3 : rule__PersistenceConfiguration__Group__3__Impl ;
    public final void rule__PersistenceConfiguration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1734:1: ( rule__PersistenceConfiguration__Group__3__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1735:2: rule__PersistenceConfiguration__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group__3__Impl_in_rule__PersistenceConfiguration__Group__33469);
            rule__PersistenceConfiguration__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group__3"


    // $ANTLR start "rule__PersistenceConfiguration__Group__3__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1741:1: rule__PersistenceConfiguration__Group__3__Impl : ( ( rule__PersistenceConfiguration__Alternatives_3 ) ) ;
    public final void rule__PersistenceConfiguration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1745:1: ( ( ( rule__PersistenceConfiguration__Alternatives_3 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1746:1: ( ( rule__PersistenceConfiguration__Alternatives_3 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1746:1: ( ( rule__PersistenceConfiguration__Alternatives_3 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1747:1: ( rule__PersistenceConfiguration__Alternatives_3 )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getAlternatives_3()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1748:1: ( rule__PersistenceConfiguration__Alternatives_3 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1748:2: rule__PersistenceConfiguration__Alternatives_3
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Alternatives_3_in_rule__PersistenceConfiguration__Group__3__Impl3496);
            rule__PersistenceConfiguration__Alternatives_3();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceConfigurationAccess().getAlternatives_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group__3__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_1__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1766:1: rule__PersistenceConfiguration__Group_1__0 : rule__PersistenceConfiguration__Group_1__0__Impl rule__PersistenceConfiguration__Group_1__1 ;
    public final void rule__PersistenceConfiguration__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1770:1: ( rule__PersistenceConfiguration__Group_1__0__Impl rule__PersistenceConfiguration__Group_1__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1771:2: rule__PersistenceConfiguration__Group_1__0__Impl rule__PersistenceConfiguration__Group_1__1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_1__0__Impl_in_rule__PersistenceConfiguration__Group_1__03534);
            rule__PersistenceConfiguration__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_1__1_in_rule__PersistenceConfiguration__Group_1__03537);
            rule__PersistenceConfiguration__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_1__0"


    // $ANTLR start "rule__PersistenceConfiguration__Group_1__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1778:1: rule__PersistenceConfiguration__Group_1__0__Impl : ( ',' ) ;
    public final void rule__PersistenceConfiguration__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1782:1: ( ( ',' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1783:1: ( ',' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1783:1: ( ',' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1784:1: ','
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getCommaKeyword_1_0()); 
            match(input,21,FOLLOW_21_in_rule__PersistenceConfiguration__Group_1__0__Impl3565); 
             after(grammarAccess.getPersistenceConfigurationAccess().getCommaKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_1__0__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_1__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1797:1: rule__PersistenceConfiguration__Group_1__1 : rule__PersistenceConfiguration__Group_1__1__Impl ;
    public final void rule__PersistenceConfiguration__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1801:1: ( rule__PersistenceConfiguration__Group_1__1__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1802:2: rule__PersistenceConfiguration__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_1__1__Impl_in_rule__PersistenceConfiguration__Group_1__13596);
            rule__PersistenceConfiguration__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_1__1"


    // $ANTLR start "rule__PersistenceConfiguration__Group_1__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1808:1: rule__PersistenceConfiguration__Group_1__1__Impl : ( ( rule__PersistenceConfiguration__ItemsAssignment_1_1 ) ) ;
    public final void rule__PersistenceConfiguration__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1812:1: ( ( ( rule__PersistenceConfiguration__ItemsAssignment_1_1 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1813:1: ( ( rule__PersistenceConfiguration__ItemsAssignment_1_1 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1813:1: ( ( rule__PersistenceConfiguration__ItemsAssignment_1_1 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1814:1: ( rule__PersistenceConfiguration__ItemsAssignment_1_1 )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getItemsAssignment_1_1()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1815:1: ( rule__PersistenceConfiguration__ItemsAssignment_1_1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1815:2: rule__PersistenceConfiguration__ItemsAssignment_1_1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__ItemsAssignment_1_1_in_rule__PersistenceConfiguration__Group_1__1__Impl3623);
            rule__PersistenceConfiguration__ItemsAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceConfigurationAccess().getItemsAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_1__1__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_2__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1829:1: rule__PersistenceConfiguration__Group_2__0 : rule__PersistenceConfiguration__Group_2__0__Impl rule__PersistenceConfiguration__Group_2__1 ;
    public final void rule__PersistenceConfiguration__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1833:1: ( rule__PersistenceConfiguration__Group_2__0__Impl rule__PersistenceConfiguration__Group_2__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1834:2: rule__PersistenceConfiguration__Group_2__0__Impl rule__PersistenceConfiguration__Group_2__1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_2__0__Impl_in_rule__PersistenceConfiguration__Group_2__03657);
            rule__PersistenceConfiguration__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_2__1_in_rule__PersistenceConfiguration__Group_2__03660);
            rule__PersistenceConfiguration__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_2__0"


    // $ANTLR start "rule__PersistenceConfiguration__Group_2__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1841:1: rule__PersistenceConfiguration__Group_2__0__Impl : ( '->' ) ;
    public final void rule__PersistenceConfiguration__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1845:1: ( ( '->' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1846:1: ( '->' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1846:1: ( '->' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1847:1: '->'
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getHyphenMinusGreaterThanSignKeyword_2_0()); 
            match(input,26,FOLLOW_26_in_rule__PersistenceConfiguration__Group_2__0__Impl3688); 
             after(grammarAccess.getPersistenceConfigurationAccess().getHyphenMinusGreaterThanSignKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_2__0__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_2__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1860:1: rule__PersistenceConfiguration__Group_2__1 : rule__PersistenceConfiguration__Group_2__1__Impl ;
    public final void rule__PersistenceConfiguration__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1864:1: ( rule__PersistenceConfiguration__Group_2__1__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1865:2: rule__PersistenceConfiguration__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_2__1__Impl_in_rule__PersistenceConfiguration__Group_2__13719);
            rule__PersistenceConfiguration__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_2__1"


    // $ANTLR start "rule__PersistenceConfiguration__Group_2__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1871:1: rule__PersistenceConfiguration__Group_2__1__Impl : ( ( rule__PersistenceConfiguration__AliasAssignment_2_1 ) ) ;
    public final void rule__PersistenceConfiguration__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1875:1: ( ( ( rule__PersistenceConfiguration__AliasAssignment_2_1 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1876:1: ( ( rule__PersistenceConfiguration__AliasAssignment_2_1 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1876:1: ( ( rule__PersistenceConfiguration__AliasAssignment_2_1 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1877:1: ( rule__PersistenceConfiguration__AliasAssignment_2_1 )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getAliasAssignment_2_1()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1878:1: ( rule__PersistenceConfiguration__AliasAssignment_2_1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1878:2: rule__PersistenceConfiguration__AliasAssignment_2_1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__AliasAssignment_2_1_in_rule__PersistenceConfiguration__Group_2__1__Impl3746);
            rule__PersistenceConfiguration__AliasAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceConfigurationAccess().getAliasAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_2__1__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1892:1: rule__PersistenceConfiguration__Group_3_0__0 : rule__PersistenceConfiguration__Group_3_0__0__Impl rule__PersistenceConfiguration__Group_3_0__1 ;
    public final void rule__PersistenceConfiguration__Group_3_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1896:1: ( rule__PersistenceConfiguration__Group_3_0__0__Impl rule__PersistenceConfiguration__Group_3_0__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1897:2: rule__PersistenceConfiguration__Group_3_0__0__Impl rule__PersistenceConfiguration__Group_3_0__1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0__0__Impl_in_rule__PersistenceConfiguration__Group_3_0__03780);
            rule__PersistenceConfiguration__Group_3_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0__1_in_rule__PersistenceConfiguration__Group_3_0__03783);
            rule__PersistenceConfiguration__Group_3_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0__0"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1904:1: rule__PersistenceConfiguration__Group_3_0__0__Impl : ( ':' ) ;
    public final void rule__PersistenceConfiguration__Group_3_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1908:1: ( ( ':' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1909:1: ( ':' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1909:1: ( ':' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1910:1: ':'
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getColonKeyword_3_0_0()); 
            match(input,24,FOLLOW_24_in_rule__PersistenceConfiguration__Group_3_0__0__Impl3811); 
             after(grammarAccess.getPersistenceConfigurationAccess().getColonKeyword_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0__0__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1923:1: rule__PersistenceConfiguration__Group_3_0__1 : rule__PersistenceConfiguration__Group_3_0__1__Impl rule__PersistenceConfiguration__Group_3_0__2 ;
    public final void rule__PersistenceConfiguration__Group_3_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1927:1: ( rule__PersistenceConfiguration__Group_3_0__1__Impl rule__PersistenceConfiguration__Group_3_0__2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1928:2: rule__PersistenceConfiguration__Group_3_0__1__Impl rule__PersistenceConfiguration__Group_3_0__2
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0__1__Impl_in_rule__PersistenceConfiguration__Group_3_0__13842);
            rule__PersistenceConfiguration__Group_3_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0__2_in_rule__PersistenceConfiguration__Group_3_0__13845);
            rule__PersistenceConfiguration__Group_3_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0__1"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1935:1: rule__PersistenceConfiguration__Group_3_0__1__Impl : ( ( rule__PersistenceConfiguration__Group_3_0_1__0 )? ) ;
    public final void rule__PersistenceConfiguration__Group_3_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1939:1: ( ( ( rule__PersistenceConfiguration__Group_3_0_1__0 )? ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1940:1: ( ( rule__PersistenceConfiguration__Group_3_0_1__0 )? )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1940:1: ( ( rule__PersistenceConfiguration__Group_3_0_1__0 )? )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1941:1: ( rule__PersistenceConfiguration__Group_3_0_1__0 )?
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_1()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1942:1: ( rule__PersistenceConfiguration__Group_3_0_1__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==27) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1942:2: rule__PersistenceConfiguration__Group_3_0_1__0
                    {
                    pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__0_in_rule__PersistenceConfiguration__Group_3_0__1__Impl3872);
                    rule__PersistenceConfiguration__Group_3_0_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0__1__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0__2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1952:1: rule__PersistenceConfiguration__Group_3_0__2 : rule__PersistenceConfiguration__Group_3_0__2__Impl ;
    public final void rule__PersistenceConfiguration__Group_3_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1956:1: ( rule__PersistenceConfiguration__Group_3_0__2__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1957:2: rule__PersistenceConfiguration__Group_3_0__2__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0__2__Impl_in_rule__PersistenceConfiguration__Group_3_0__23903);
            rule__PersistenceConfiguration__Group_3_0__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0__2"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0__2__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1963:1: rule__PersistenceConfiguration__Group_3_0__2__Impl : ( ( rule__PersistenceConfiguration__Group_3_0_2__0 )? ) ;
    public final void rule__PersistenceConfiguration__Group_3_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1967:1: ( ( ( rule__PersistenceConfiguration__Group_3_0_2__0 )? ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1968:1: ( ( rule__PersistenceConfiguration__Group_3_0_2__0 )? )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1968:1: ( ( rule__PersistenceConfiguration__Group_3_0_2__0 )? )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1969:1: ( rule__PersistenceConfiguration__Group_3_0_2__0 )?
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_2()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1970:1: ( rule__PersistenceConfiguration__Group_3_0_2__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==28) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1970:2: rule__PersistenceConfiguration__Group_3_0_2__0
                    {
                    pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__0_in_rule__PersistenceConfiguration__Group_3_0__2__Impl3930);
                    rule__PersistenceConfiguration__Group_3_0_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0__2__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1986:1: rule__PersistenceConfiguration__Group_3_0_1__0 : rule__PersistenceConfiguration__Group_3_0_1__0__Impl rule__PersistenceConfiguration__Group_3_0_1__1 ;
    public final void rule__PersistenceConfiguration__Group_3_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1990:1: ( rule__PersistenceConfiguration__Group_3_0_1__0__Impl rule__PersistenceConfiguration__Group_3_0_1__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1991:2: rule__PersistenceConfiguration__Group_3_0_1__0__Impl rule__PersistenceConfiguration__Group_3_0_1__1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__0__Impl_in_rule__PersistenceConfiguration__Group_3_0_1__03967);
            rule__PersistenceConfiguration__Group_3_0_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__1_in_rule__PersistenceConfiguration__Group_3_0_1__03970);
            rule__PersistenceConfiguration__Group_3_0_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1__0"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:1998:1: rule__PersistenceConfiguration__Group_3_0_1__0__Impl : ( 'strategy' ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2002:1: ( ( 'strategy' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2003:1: ( 'strategy' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2003:1: ( 'strategy' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2004:1: 'strategy'
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getStrategyKeyword_3_0_1_0()); 
            match(input,27,FOLLOW_27_in_rule__PersistenceConfiguration__Group_3_0_1__0__Impl3998); 
             after(grammarAccess.getPersistenceConfigurationAccess().getStrategyKeyword_3_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1__0__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2017:1: rule__PersistenceConfiguration__Group_3_0_1__1 : rule__PersistenceConfiguration__Group_3_0_1__1__Impl rule__PersistenceConfiguration__Group_3_0_1__2 ;
    public final void rule__PersistenceConfiguration__Group_3_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2021:1: ( rule__PersistenceConfiguration__Group_3_0_1__1__Impl rule__PersistenceConfiguration__Group_3_0_1__2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2022:2: rule__PersistenceConfiguration__Group_3_0_1__1__Impl rule__PersistenceConfiguration__Group_3_0_1__2
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__1__Impl_in_rule__PersistenceConfiguration__Group_3_0_1__14029);
            rule__PersistenceConfiguration__Group_3_0_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__2_in_rule__PersistenceConfiguration__Group_3_0_1__14032);
            rule__PersistenceConfiguration__Group_3_0_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1__1"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2029:1: rule__PersistenceConfiguration__Group_3_0_1__1__Impl : ( '=' ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2033:1: ( ( '=' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2034:1: ( '=' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2034:1: ( '=' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2035:1: '='
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getEqualsSignKeyword_3_0_1_1()); 
            match(input,20,FOLLOW_20_in_rule__PersistenceConfiguration__Group_3_0_1__1__Impl4060); 
             after(grammarAccess.getPersistenceConfigurationAccess().getEqualsSignKeyword_3_0_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1__1__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1__2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2048:1: rule__PersistenceConfiguration__Group_3_0_1__2 : rule__PersistenceConfiguration__Group_3_0_1__2__Impl rule__PersistenceConfiguration__Group_3_0_1__3 ;
    public final void rule__PersistenceConfiguration__Group_3_0_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2052:1: ( rule__PersistenceConfiguration__Group_3_0_1__2__Impl rule__PersistenceConfiguration__Group_3_0_1__3 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2053:2: rule__PersistenceConfiguration__Group_3_0_1__2__Impl rule__PersistenceConfiguration__Group_3_0_1__3
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__2__Impl_in_rule__PersistenceConfiguration__Group_3_0_1__24091);
            rule__PersistenceConfiguration__Group_3_0_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__3_in_rule__PersistenceConfiguration__Group_3_0_1__24094);
            rule__PersistenceConfiguration__Group_3_0_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1__2"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1__2__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2060:1: rule__PersistenceConfiguration__Group_3_0_1__2__Impl : ( ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2 ) ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2064:1: ( ( ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2065:1: ( ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2065:1: ( ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2066:1: ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2 )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getStrategiesAssignment_3_0_1_2()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2067:1: ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2067:2: rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2_in_rule__PersistenceConfiguration__Group_3_0_1__2__Impl4121);
            rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceConfigurationAccess().getStrategiesAssignment_3_0_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1__2__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1__3"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2077:1: rule__PersistenceConfiguration__Group_3_0_1__3 : rule__PersistenceConfiguration__Group_3_0_1__3__Impl ;
    public final void rule__PersistenceConfiguration__Group_3_0_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2081:1: ( rule__PersistenceConfiguration__Group_3_0_1__3__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2082:2: rule__PersistenceConfiguration__Group_3_0_1__3__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__3__Impl_in_rule__PersistenceConfiguration__Group_3_0_1__34151);
            rule__PersistenceConfiguration__Group_3_0_1__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1__3"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1__3__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2088:1: rule__PersistenceConfiguration__Group_3_0_1__3__Impl : ( ( rule__PersistenceConfiguration__Group_3_0_1_3__0 )* ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2092:1: ( ( ( rule__PersistenceConfiguration__Group_3_0_1_3__0 )* ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2093:1: ( ( rule__PersistenceConfiguration__Group_3_0_1_3__0 )* )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2093:1: ( ( rule__PersistenceConfiguration__Group_3_0_1_3__0 )* )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2094:1: ( rule__PersistenceConfiguration__Group_3_0_1_3__0 )*
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_1_3()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2095:1: ( rule__PersistenceConfiguration__Group_3_0_1_3__0 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==21) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2095:2: rule__PersistenceConfiguration__Group_3_0_1_3__0
            	    {
            	    pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1_3__0_in_rule__PersistenceConfiguration__Group_3_0_1__3__Impl4178);
            	    rule__PersistenceConfiguration__Group_3_0_1_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1__3__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1_3__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2113:1: rule__PersistenceConfiguration__Group_3_0_1_3__0 : rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl rule__PersistenceConfiguration__Group_3_0_1_3__1 ;
    public final void rule__PersistenceConfiguration__Group_3_0_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2117:1: ( rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl rule__PersistenceConfiguration__Group_3_0_1_3__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2118:2: rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl rule__PersistenceConfiguration__Group_3_0_1_3__1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl_in_rule__PersistenceConfiguration__Group_3_0_1_3__04217);
            rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1_3__1_in_rule__PersistenceConfiguration__Group_3_0_1_3__04220);
            rule__PersistenceConfiguration__Group_3_0_1_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1_3__0"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2125:1: rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl : ( ',' ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2129:1: ( ( ',' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2130:1: ( ',' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2130:1: ( ',' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2131:1: ','
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getCommaKeyword_3_0_1_3_0()); 
            match(input,21,FOLLOW_21_in_rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl4248); 
             after(grammarAccess.getPersistenceConfigurationAccess().getCommaKeyword_3_0_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1_3__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2144:1: rule__PersistenceConfiguration__Group_3_0_1_3__1 : rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl ;
    public final void rule__PersistenceConfiguration__Group_3_0_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2148:1: ( rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2149:2: rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl_in_rule__PersistenceConfiguration__Group_3_0_1_3__14279);
            rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1_3__1"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2155:1: rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl : ( ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1 ) ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2159:1: ( ( ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2160:1: ( ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2160:1: ( ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2161:1: ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1 )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getStrategiesAssignment_3_0_1_3_1()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2162:1: ( rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2162:2: rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1_in_rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl4306);
            rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceConfigurationAccess().getStrategiesAssignment_3_0_1_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2176:1: rule__PersistenceConfiguration__Group_3_0_2__0 : rule__PersistenceConfiguration__Group_3_0_2__0__Impl rule__PersistenceConfiguration__Group_3_0_2__1 ;
    public final void rule__PersistenceConfiguration__Group_3_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2180:1: ( rule__PersistenceConfiguration__Group_3_0_2__0__Impl rule__PersistenceConfiguration__Group_3_0_2__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2181:2: rule__PersistenceConfiguration__Group_3_0_2__0__Impl rule__PersistenceConfiguration__Group_3_0_2__1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__0__Impl_in_rule__PersistenceConfiguration__Group_3_0_2__04340);
            rule__PersistenceConfiguration__Group_3_0_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__1_in_rule__PersistenceConfiguration__Group_3_0_2__04343);
            rule__PersistenceConfiguration__Group_3_0_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2__0"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2188:1: rule__PersistenceConfiguration__Group_3_0_2__0__Impl : ( 'filter' ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2192:1: ( ( 'filter' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2193:1: ( 'filter' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2193:1: ( 'filter' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2194:1: 'filter'
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getFilterKeyword_3_0_2_0()); 
            match(input,28,FOLLOW_28_in_rule__PersistenceConfiguration__Group_3_0_2__0__Impl4371); 
             after(grammarAccess.getPersistenceConfigurationAccess().getFilterKeyword_3_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2__0__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2207:1: rule__PersistenceConfiguration__Group_3_0_2__1 : rule__PersistenceConfiguration__Group_3_0_2__1__Impl rule__PersistenceConfiguration__Group_3_0_2__2 ;
    public final void rule__PersistenceConfiguration__Group_3_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2211:1: ( rule__PersistenceConfiguration__Group_3_0_2__1__Impl rule__PersistenceConfiguration__Group_3_0_2__2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2212:2: rule__PersistenceConfiguration__Group_3_0_2__1__Impl rule__PersistenceConfiguration__Group_3_0_2__2
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__1__Impl_in_rule__PersistenceConfiguration__Group_3_0_2__14402);
            rule__PersistenceConfiguration__Group_3_0_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__2_in_rule__PersistenceConfiguration__Group_3_0_2__14405);
            rule__PersistenceConfiguration__Group_3_0_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2__1"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2219:1: rule__PersistenceConfiguration__Group_3_0_2__1__Impl : ( '=' ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2223:1: ( ( '=' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2224:1: ( '=' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2224:1: ( '=' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2225:1: '='
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getEqualsSignKeyword_3_0_2_1()); 
            match(input,20,FOLLOW_20_in_rule__PersistenceConfiguration__Group_3_0_2__1__Impl4433); 
             after(grammarAccess.getPersistenceConfigurationAccess().getEqualsSignKeyword_3_0_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2__1__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2__2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2238:1: rule__PersistenceConfiguration__Group_3_0_2__2 : rule__PersistenceConfiguration__Group_3_0_2__2__Impl rule__PersistenceConfiguration__Group_3_0_2__3 ;
    public final void rule__PersistenceConfiguration__Group_3_0_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2242:1: ( rule__PersistenceConfiguration__Group_3_0_2__2__Impl rule__PersistenceConfiguration__Group_3_0_2__3 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2243:2: rule__PersistenceConfiguration__Group_3_0_2__2__Impl rule__PersistenceConfiguration__Group_3_0_2__3
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__2__Impl_in_rule__PersistenceConfiguration__Group_3_0_2__24464);
            rule__PersistenceConfiguration__Group_3_0_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__3_in_rule__PersistenceConfiguration__Group_3_0_2__24467);
            rule__PersistenceConfiguration__Group_3_0_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2__2"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2__2__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2250:1: rule__PersistenceConfiguration__Group_3_0_2__2__Impl : ( ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2 ) ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2254:1: ( ( ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2255:1: ( ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2255:1: ( ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2256:1: ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2 )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getFiltersAssignment_3_0_2_2()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2257:1: ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2257:2: rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2_in_rule__PersistenceConfiguration__Group_3_0_2__2__Impl4494);
            rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceConfigurationAccess().getFiltersAssignment_3_0_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2__2__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2__3"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2267:1: rule__PersistenceConfiguration__Group_3_0_2__3 : rule__PersistenceConfiguration__Group_3_0_2__3__Impl ;
    public final void rule__PersistenceConfiguration__Group_3_0_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2271:1: ( rule__PersistenceConfiguration__Group_3_0_2__3__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2272:2: rule__PersistenceConfiguration__Group_3_0_2__3__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__3__Impl_in_rule__PersistenceConfiguration__Group_3_0_2__34524);
            rule__PersistenceConfiguration__Group_3_0_2__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2__3"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2__3__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2278:1: rule__PersistenceConfiguration__Group_3_0_2__3__Impl : ( ( rule__PersistenceConfiguration__Group_3_0_2_3__0 )* ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2282:1: ( ( ( rule__PersistenceConfiguration__Group_3_0_2_3__0 )* ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2283:1: ( ( rule__PersistenceConfiguration__Group_3_0_2_3__0 )* )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2283:1: ( ( rule__PersistenceConfiguration__Group_3_0_2_3__0 )* )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2284:1: ( rule__PersistenceConfiguration__Group_3_0_2_3__0 )*
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_2_3()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2285:1: ( rule__PersistenceConfiguration__Group_3_0_2_3__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==21) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2285:2: rule__PersistenceConfiguration__Group_3_0_2_3__0
            	    {
            	    pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2_3__0_in_rule__PersistenceConfiguration__Group_3_0_2__3__Impl4551);
            	    rule__PersistenceConfiguration__Group_3_0_2_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

             after(grammarAccess.getPersistenceConfigurationAccess().getGroup_3_0_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2__3__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2_3__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2303:1: rule__PersistenceConfiguration__Group_3_0_2_3__0 : rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl rule__PersistenceConfiguration__Group_3_0_2_3__1 ;
    public final void rule__PersistenceConfiguration__Group_3_0_2_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2307:1: ( rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl rule__PersistenceConfiguration__Group_3_0_2_3__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2308:2: rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl rule__PersistenceConfiguration__Group_3_0_2_3__1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl_in_rule__PersistenceConfiguration__Group_3_0_2_3__04590);
            rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2_3__1_in_rule__PersistenceConfiguration__Group_3_0_2_3__04593);
            rule__PersistenceConfiguration__Group_3_0_2_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2_3__0"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2315:1: rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl : ( ',' ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2319:1: ( ( ',' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2320:1: ( ',' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2320:1: ( ',' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2321:1: ','
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getCommaKeyword_3_0_2_3_0()); 
            match(input,21,FOLLOW_21_in_rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl4621); 
             after(grammarAccess.getPersistenceConfigurationAccess().getCommaKeyword_3_0_2_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2_3__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2334:1: rule__PersistenceConfiguration__Group_3_0_2_3__1 : rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl ;
    public final void rule__PersistenceConfiguration__Group_3_0_2_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2338:1: ( rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2339:2: rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl_in_rule__PersistenceConfiguration__Group_3_0_2_3__14652);
            rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2_3__1"


    // $ANTLR start "rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2345:1: rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl : ( ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1 ) ) ;
    public final void rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2349:1: ( ( ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2350:1: ( ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2350:1: ( ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2351:1: ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1 )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getFiltersAssignment_3_0_2_3_1()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2352:1: ( rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2352:2: rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1_in_rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl4679);
            rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceConfigurationAccess().getFiltersAssignment_3_0_2_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl"


    // $ANTLR start "rule__AllConfig__Group__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2366:1: rule__AllConfig__Group__0 : rule__AllConfig__Group__0__Impl rule__AllConfig__Group__1 ;
    public final void rule__AllConfig__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2370:1: ( rule__AllConfig__Group__0__Impl rule__AllConfig__Group__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2371:2: rule__AllConfig__Group__0__Impl rule__AllConfig__Group__1
            {
            pushFollow(FOLLOW_rule__AllConfig__Group__0__Impl_in_rule__AllConfig__Group__04713);
            rule__AllConfig__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__AllConfig__Group__1_in_rule__AllConfig__Group__04716);
            rule__AllConfig__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AllConfig__Group__0"


    // $ANTLR start "rule__AllConfig__Group__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2378:1: rule__AllConfig__Group__0__Impl : ( () ) ;
    public final void rule__AllConfig__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2382:1: ( ( () ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2383:1: ( () )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2383:1: ( () )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2384:1: ()
            {
             before(grammarAccess.getAllConfigAccess().getAllConfigAction_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2385:1: ()
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2387:1: 
            {
            }

             after(grammarAccess.getAllConfigAccess().getAllConfigAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AllConfig__Group__0__Impl"


    // $ANTLR start "rule__AllConfig__Group__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2397:1: rule__AllConfig__Group__1 : rule__AllConfig__Group__1__Impl ;
    public final void rule__AllConfig__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2401:1: ( rule__AllConfig__Group__1__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2402:2: rule__AllConfig__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__AllConfig__Group__1__Impl_in_rule__AllConfig__Group__14774);
            rule__AllConfig__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AllConfig__Group__1"


    // $ANTLR start "rule__AllConfig__Group__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2408:1: rule__AllConfig__Group__1__Impl : ( '*' ) ;
    public final void rule__AllConfig__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2412:1: ( ( '*' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2413:1: ( '*' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2413:1: ( '*' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2414:1: '*'
            {
             before(grammarAccess.getAllConfigAccess().getAsteriskKeyword_1()); 
            match(input,29,FOLLOW_29_in_rule__AllConfig__Group__1__Impl4802); 
             after(grammarAccess.getAllConfigAccess().getAsteriskKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AllConfig__Group__1__Impl"


    // $ANTLR start "rule__GroupConfig__Group__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2431:1: rule__GroupConfig__Group__0 : rule__GroupConfig__Group__0__Impl rule__GroupConfig__Group__1 ;
    public final void rule__GroupConfig__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2435:1: ( rule__GroupConfig__Group__0__Impl rule__GroupConfig__Group__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2436:2: rule__GroupConfig__Group__0__Impl rule__GroupConfig__Group__1
            {
            pushFollow(FOLLOW_rule__GroupConfig__Group__0__Impl_in_rule__GroupConfig__Group__04837);
            rule__GroupConfig__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GroupConfig__Group__1_in_rule__GroupConfig__Group__04840);
            rule__GroupConfig__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GroupConfig__Group__0"


    // $ANTLR start "rule__GroupConfig__Group__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2443:1: rule__GroupConfig__Group__0__Impl : ( ( rule__GroupConfig__GroupAssignment_0 ) ) ;
    public final void rule__GroupConfig__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2447:1: ( ( ( rule__GroupConfig__GroupAssignment_0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2448:1: ( ( rule__GroupConfig__GroupAssignment_0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2448:1: ( ( rule__GroupConfig__GroupAssignment_0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2449:1: ( rule__GroupConfig__GroupAssignment_0 )
            {
             before(grammarAccess.getGroupConfigAccess().getGroupAssignment_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2450:1: ( rule__GroupConfig__GroupAssignment_0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2450:2: rule__GroupConfig__GroupAssignment_0
            {
            pushFollow(FOLLOW_rule__GroupConfig__GroupAssignment_0_in_rule__GroupConfig__Group__0__Impl4867);
            rule__GroupConfig__GroupAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getGroupConfigAccess().getGroupAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GroupConfig__Group__0__Impl"


    // $ANTLR start "rule__GroupConfig__Group__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2460:1: rule__GroupConfig__Group__1 : rule__GroupConfig__Group__1__Impl ;
    public final void rule__GroupConfig__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2464:1: ( rule__GroupConfig__Group__1__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2465:2: rule__GroupConfig__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__GroupConfig__Group__1__Impl_in_rule__GroupConfig__Group__14897);
            rule__GroupConfig__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GroupConfig__Group__1"


    // $ANTLR start "rule__GroupConfig__Group__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2471:1: rule__GroupConfig__Group__1__Impl : ( '*' ) ;
    public final void rule__GroupConfig__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2475:1: ( ( '*' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2476:1: ( '*' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2476:1: ( '*' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2477:1: '*'
            {
             before(grammarAccess.getGroupConfigAccess().getAsteriskKeyword_1()); 
            match(input,29,FOLLOW_29_in_rule__GroupConfig__Group__1__Impl4925); 
             after(grammarAccess.getGroupConfigAccess().getAsteriskKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GroupConfig__Group__1__Impl"


    // $ANTLR start "rule__DECIMAL__Group__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2494:1: rule__DECIMAL__Group__0 : rule__DECIMAL__Group__0__Impl rule__DECIMAL__Group__1 ;
    public final void rule__DECIMAL__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2498:1: ( rule__DECIMAL__Group__0__Impl rule__DECIMAL__Group__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2499:2: rule__DECIMAL__Group__0__Impl rule__DECIMAL__Group__1
            {
            pushFollow(FOLLOW_rule__DECIMAL__Group__0__Impl_in_rule__DECIMAL__Group__04960);
            rule__DECIMAL__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DECIMAL__Group__1_in_rule__DECIMAL__Group__04963);
            rule__DECIMAL__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DECIMAL__Group__0"


    // $ANTLR start "rule__DECIMAL__Group__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2506:1: rule__DECIMAL__Group__0__Impl : ( RULE_INT ) ;
    public final void rule__DECIMAL__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2510:1: ( ( RULE_INT ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2511:1: ( RULE_INT )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2511:1: ( RULE_INT )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2512:1: RULE_INT
            {
             before(grammarAccess.getDECIMALAccess().getINTTerminalRuleCall_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__DECIMAL__Group__0__Impl4990); 
             after(grammarAccess.getDECIMALAccess().getINTTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DECIMAL__Group__0__Impl"


    // $ANTLR start "rule__DECIMAL__Group__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2523:1: rule__DECIMAL__Group__1 : rule__DECIMAL__Group__1__Impl ;
    public final void rule__DECIMAL__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2527:1: ( rule__DECIMAL__Group__1__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2528:2: rule__DECIMAL__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__DECIMAL__Group__1__Impl_in_rule__DECIMAL__Group__15019);
            rule__DECIMAL__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DECIMAL__Group__1"


    // $ANTLR start "rule__DECIMAL__Group__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2534:1: rule__DECIMAL__Group__1__Impl : ( ( rule__DECIMAL__Group_1__0 )? ) ;
    public final void rule__DECIMAL__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2538:1: ( ( ( rule__DECIMAL__Group_1__0 )? ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2539:1: ( ( rule__DECIMAL__Group_1__0 )? )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2539:1: ( ( rule__DECIMAL__Group_1__0 )? )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2540:1: ( rule__DECIMAL__Group_1__0 )?
            {
             before(grammarAccess.getDECIMALAccess().getGroup_1()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2541:1: ( rule__DECIMAL__Group_1__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==30) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2541:2: rule__DECIMAL__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__DECIMAL__Group_1__0_in_rule__DECIMAL__Group__1__Impl5046);
                    rule__DECIMAL__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getDECIMALAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DECIMAL__Group__1__Impl"


    // $ANTLR start "rule__DECIMAL__Group_1__0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2555:1: rule__DECIMAL__Group_1__0 : rule__DECIMAL__Group_1__0__Impl rule__DECIMAL__Group_1__1 ;
    public final void rule__DECIMAL__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2559:1: ( rule__DECIMAL__Group_1__0__Impl rule__DECIMAL__Group_1__1 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2560:2: rule__DECIMAL__Group_1__0__Impl rule__DECIMAL__Group_1__1
            {
            pushFollow(FOLLOW_rule__DECIMAL__Group_1__0__Impl_in_rule__DECIMAL__Group_1__05081);
            rule__DECIMAL__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DECIMAL__Group_1__1_in_rule__DECIMAL__Group_1__05084);
            rule__DECIMAL__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DECIMAL__Group_1__0"


    // $ANTLR start "rule__DECIMAL__Group_1__0__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2567:1: rule__DECIMAL__Group_1__0__Impl : ( '.' ) ;
    public final void rule__DECIMAL__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2571:1: ( ( '.' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2572:1: ( '.' )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2572:1: ( '.' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2573:1: '.'
            {
             before(grammarAccess.getDECIMALAccess().getFullStopKeyword_1_0()); 
            match(input,30,FOLLOW_30_in_rule__DECIMAL__Group_1__0__Impl5112); 
             after(grammarAccess.getDECIMALAccess().getFullStopKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DECIMAL__Group_1__0__Impl"


    // $ANTLR start "rule__DECIMAL__Group_1__1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2586:1: rule__DECIMAL__Group_1__1 : rule__DECIMAL__Group_1__1__Impl ;
    public final void rule__DECIMAL__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2590:1: ( rule__DECIMAL__Group_1__1__Impl )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2591:2: rule__DECIMAL__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__DECIMAL__Group_1__1__Impl_in_rule__DECIMAL__Group_1__15143);
            rule__DECIMAL__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DECIMAL__Group_1__1"


    // $ANTLR start "rule__DECIMAL__Group_1__1__Impl"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2597:1: rule__DECIMAL__Group_1__1__Impl : ( RULE_INT ) ;
    public final void rule__DECIMAL__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2601:1: ( ( RULE_INT ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2602:1: ( RULE_INT )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2602:1: ( RULE_INT )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2603:1: RULE_INT
            {
             before(grammarAccess.getDECIMALAccess().getINTTerminalRuleCall_1_1()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__DECIMAL__Group_1__1__Impl5170); 
             after(grammarAccess.getDECIMALAccess().getINTTerminalRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DECIMAL__Group_1__1__Impl"


    // $ANTLR start "rule__PersistenceModel__StrategiesAssignment_3"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2619:1: rule__PersistenceModel__StrategiesAssignment_3 : ( ruleStrategy ) ;
    public final void rule__PersistenceModel__StrategiesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2623:1: ( ( ruleStrategy ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2624:1: ( ruleStrategy )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2624:1: ( ruleStrategy )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2625:1: ruleStrategy
            {
             before(grammarAccess.getPersistenceModelAccess().getStrategiesStrategyParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleStrategy_in_rule__PersistenceModel__StrategiesAssignment_35208);
            ruleStrategy();

            state._fsp--;

             after(grammarAccess.getPersistenceModelAccess().getStrategiesStrategyParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__StrategiesAssignment_3"


    // $ANTLR start "rule__PersistenceModel__DefaultsAssignment_4_2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2634:1: rule__PersistenceModel__DefaultsAssignment_4_2 : ( ( RULE_ID ) ) ;
    public final void rule__PersistenceModel__DefaultsAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2638:1: ( ( ( RULE_ID ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2639:1: ( ( RULE_ID ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2639:1: ( ( RULE_ID ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2640:1: ( RULE_ID )
            {
             before(grammarAccess.getPersistenceModelAccess().getDefaultsStrategyCrossReference_4_2_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2641:1: ( RULE_ID )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2642:1: RULE_ID
            {
             before(grammarAccess.getPersistenceModelAccess().getDefaultsStrategyIDTerminalRuleCall_4_2_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__PersistenceModel__DefaultsAssignment_4_25243); 
             after(grammarAccess.getPersistenceModelAccess().getDefaultsStrategyIDTerminalRuleCall_4_2_0_1()); 

            }

             after(grammarAccess.getPersistenceModelAccess().getDefaultsStrategyCrossReference_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__DefaultsAssignment_4_2"


    // $ANTLR start "rule__PersistenceModel__DefaultsAssignment_4_3_1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2653:1: rule__PersistenceModel__DefaultsAssignment_4_3_1 : ( ( RULE_ID ) ) ;
    public final void rule__PersistenceModel__DefaultsAssignment_4_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2657:1: ( ( ( RULE_ID ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2658:1: ( ( RULE_ID ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2658:1: ( ( RULE_ID ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2659:1: ( RULE_ID )
            {
             before(grammarAccess.getPersistenceModelAccess().getDefaultsStrategyCrossReference_4_3_1_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2660:1: ( RULE_ID )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2661:1: RULE_ID
            {
             before(grammarAccess.getPersistenceModelAccess().getDefaultsStrategyIDTerminalRuleCall_4_3_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__PersistenceModel__DefaultsAssignment_4_3_15282); 
             after(grammarAccess.getPersistenceModelAccess().getDefaultsStrategyIDTerminalRuleCall_4_3_1_0_1()); 

            }

             after(grammarAccess.getPersistenceModelAccess().getDefaultsStrategyCrossReference_4_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__DefaultsAssignment_4_3_1"


    // $ANTLR start "rule__PersistenceModel__FiltersAssignment_6_2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2672:1: rule__PersistenceModel__FiltersAssignment_6_2 : ( ruleFilter ) ;
    public final void rule__PersistenceModel__FiltersAssignment_6_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2676:1: ( ( ruleFilter ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2677:1: ( ruleFilter )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2677:1: ( ruleFilter )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2678:1: ruleFilter
            {
             before(grammarAccess.getPersistenceModelAccess().getFiltersFilterParserRuleCall_6_2_0()); 
            pushFollow(FOLLOW_ruleFilter_in_rule__PersistenceModel__FiltersAssignment_6_25317);
            ruleFilter();

            state._fsp--;

             after(grammarAccess.getPersistenceModelAccess().getFiltersFilterParserRuleCall_6_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__FiltersAssignment_6_2"


    // $ANTLR start "rule__PersistenceModel__ConfigsAssignment_7_2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2687:1: rule__PersistenceModel__ConfigsAssignment_7_2 : ( rulePersistenceConfiguration ) ;
    public final void rule__PersistenceModel__ConfigsAssignment_7_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2691:1: ( ( rulePersistenceConfiguration ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2692:1: ( rulePersistenceConfiguration )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2692:1: ( rulePersistenceConfiguration )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2693:1: rulePersistenceConfiguration
            {
             before(grammarAccess.getPersistenceModelAccess().getConfigsPersistenceConfigurationParserRuleCall_7_2_0()); 
            pushFollow(FOLLOW_rulePersistenceConfiguration_in_rule__PersistenceModel__ConfigsAssignment_7_25348);
            rulePersistenceConfiguration();

            state._fsp--;

             after(grammarAccess.getPersistenceModelAccess().getConfigsPersistenceConfigurationParserRuleCall_7_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceModel__ConfigsAssignment_7_2"


    // $ANTLR start "rule__Strategy__NameAssignment_1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2702:1: rule__Strategy__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Strategy__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2706:1: ( ( RULE_ID ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2707:1: ( RULE_ID )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2707:1: ( RULE_ID )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2708:1: RULE_ID
            {
             before(grammarAccess.getStrategyAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Strategy__NameAssignment_15379); 
             after(grammarAccess.getStrategyAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Strategy__NameAssignment_1"


    // $ANTLR start "rule__CronStrategy__NameAssignment_1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2717:1: rule__CronStrategy__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__CronStrategy__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2721:1: ( ( RULE_ID ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2722:1: ( RULE_ID )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2722:1: ( RULE_ID )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2723:1: RULE_ID
            {
             before(grammarAccess.getCronStrategyAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__CronStrategy__NameAssignment_15410); 
             after(grammarAccess.getCronStrategyAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CronStrategy__NameAssignment_1"


    // $ANTLR start "rule__CronStrategy__CronExpressionAssignment_3"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2732:1: rule__CronStrategy__CronExpressionAssignment_3 : ( RULE_STRING ) ;
    public final void rule__CronStrategy__CronExpressionAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2736:1: ( ( RULE_STRING ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2737:1: ( RULE_STRING )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2737:1: ( RULE_STRING )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2738:1: RULE_STRING
            {
             before(grammarAccess.getCronStrategyAccess().getCronExpressionSTRINGTerminalRuleCall_3_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__CronStrategy__CronExpressionAssignment_35441); 
             after(grammarAccess.getCronStrategyAccess().getCronExpressionSTRINGTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CronStrategy__CronExpressionAssignment_3"


    // $ANTLR start "rule__Filter__NameAssignment_0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2747:1: rule__Filter__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Filter__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2751:1: ( ( RULE_ID ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2752:1: ( RULE_ID )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2752:1: ( RULE_ID )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2753:1: RULE_ID
            {
             before(grammarAccess.getFilterAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Filter__NameAssignment_05472); 
             after(grammarAccess.getFilterAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Filter__NameAssignment_0"


    // $ANTLR start "rule__Filter__DefinitionAssignment_2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2762:1: rule__Filter__DefinitionAssignment_2 : ( ruleFilterDetails ) ;
    public final void rule__Filter__DefinitionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2766:1: ( ( ruleFilterDetails ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2767:1: ( ruleFilterDetails )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2767:1: ( ruleFilterDetails )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2768:1: ruleFilterDetails
            {
             before(grammarAccess.getFilterAccess().getDefinitionFilterDetailsParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleFilterDetails_in_rule__Filter__DefinitionAssignment_25503);
            ruleFilterDetails();

            state._fsp--;

             after(grammarAccess.getFilterAccess().getDefinitionFilterDetailsParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Filter__DefinitionAssignment_2"


    // $ANTLR start "rule__ThresholdFilter__ValueAssignment_1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2777:1: rule__ThresholdFilter__ValueAssignment_1 : ( ruleDECIMAL ) ;
    public final void rule__ThresholdFilter__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2781:1: ( ( ruleDECIMAL ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2782:1: ( ruleDECIMAL )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2782:1: ( ruleDECIMAL )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2783:1: ruleDECIMAL
            {
             before(grammarAccess.getThresholdFilterAccess().getValueDECIMALParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleDECIMAL_in_rule__ThresholdFilter__ValueAssignment_15534);
            ruleDECIMAL();

            state._fsp--;

             after(grammarAccess.getThresholdFilterAccess().getValueDECIMALParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ThresholdFilter__ValueAssignment_1"


    // $ANTLR start "rule__ThresholdFilter__PercentAssignment_2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2792:1: rule__ThresholdFilter__PercentAssignment_2 : ( ( '%' ) ) ;
    public final void rule__ThresholdFilter__PercentAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2796:1: ( ( ( '%' ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2797:1: ( ( '%' ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2797:1: ( ( '%' ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2798:1: ( '%' )
            {
             before(grammarAccess.getThresholdFilterAccess().getPercentPercentSignKeyword_2_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2799:1: ( '%' )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2800:1: '%'
            {
             before(grammarAccess.getThresholdFilterAccess().getPercentPercentSignKeyword_2_0()); 
            match(input,31,FOLLOW_31_in_rule__ThresholdFilter__PercentAssignment_25570); 
             after(grammarAccess.getThresholdFilterAccess().getPercentPercentSignKeyword_2_0()); 

            }

             after(grammarAccess.getThresholdFilterAccess().getPercentPercentSignKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ThresholdFilter__PercentAssignment_2"


    // $ANTLR start "rule__TimeFilter__ValueAssignment_0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2815:1: rule__TimeFilter__ValueAssignment_0 : ( RULE_INT ) ;
    public final void rule__TimeFilter__ValueAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2819:1: ( ( RULE_INT ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2820:1: ( RULE_INT )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2820:1: ( RULE_INT )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2821:1: RULE_INT
            {
             before(grammarAccess.getTimeFilterAccess().getValueINTTerminalRuleCall_0_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__TimeFilter__ValueAssignment_05609); 
             after(grammarAccess.getTimeFilterAccess().getValueINTTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TimeFilter__ValueAssignment_0"


    // $ANTLR start "rule__TimeFilter__UnitAssignment_1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2830:1: rule__TimeFilter__UnitAssignment_1 : ( ( rule__TimeFilter__UnitAlternatives_1_0 ) ) ;
    public final void rule__TimeFilter__UnitAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2834:1: ( ( ( rule__TimeFilter__UnitAlternatives_1_0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2835:1: ( ( rule__TimeFilter__UnitAlternatives_1_0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2835:1: ( ( rule__TimeFilter__UnitAlternatives_1_0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2836:1: ( rule__TimeFilter__UnitAlternatives_1_0 )
            {
             before(grammarAccess.getTimeFilterAccess().getUnitAlternatives_1_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2837:1: ( rule__TimeFilter__UnitAlternatives_1_0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2837:2: rule__TimeFilter__UnitAlternatives_1_0
            {
            pushFollow(FOLLOW_rule__TimeFilter__UnitAlternatives_1_0_in_rule__TimeFilter__UnitAssignment_15640);
            rule__TimeFilter__UnitAlternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getTimeFilterAccess().getUnitAlternatives_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TimeFilter__UnitAssignment_1"


    // $ANTLR start "rule__PersistenceConfiguration__ItemsAssignment_0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2846:1: rule__PersistenceConfiguration__ItemsAssignment_0 : ( ( rule__PersistenceConfiguration__ItemsAlternatives_0_0 ) ) ;
    public final void rule__PersistenceConfiguration__ItemsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2850:1: ( ( ( rule__PersistenceConfiguration__ItemsAlternatives_0_0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2851:1: ( ( rule__PersistenceConfiguration__ItemsAlternatives_0_0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2851:1: ( ( rule__PersistenceConfiguration__ItemsAlternatives_0_0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2852:1: ( rule__PersistenceConfiguration__ItemsAlternatives_0_0 )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getItemsAlternatives_0_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2853:1: ( rule__PersistenceConfiguration__ItemsAlternatives_0_0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2853:2: rule__PersistenceConfiguration__ItemsAlternatives_0_0
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__ItemsAlternatives_0_0_in_rule__PersistenceConfiguration__ItemsAssignment_05673);
            rule__PersistenceConfiguration__ItemsAlternatives_0_0();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceConfigurationAccess().getItemsAlternatives_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__ItemsAssignment_0"


    // $ANTLR start "rule__PersistenceConfiguration__ItemsAssignment_1_1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2862:1: rule__PersistenceConfiguration__ItemsAssignment_1_1 : ( ( rule__PersistenceConfiguration__ItemsAlternatives_1_1_0 ) ) ;
    public final void rule__PersistenceConfiguration__ItemsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2866:1: ( ( ( rule__PersistenceConfiguration__ItemsAlternatives_1_1_0 ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2867:1: ( ( rule__PersistenceConfiguration__ItemsAlternatives_1_1_0 ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2867:1: ( ( rule__PersistenceConfiguration__ItemsAlternatives_1_1_0 ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2868:1: ( rule__PersistenceConfiguration__ItemsAlternatives_1_1_0 )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getItemsAlternatives_1_1_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2869:1: ( rule__PersistenceConfiguration__ItemsAlternatives_1_1_0 )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2869:2: rule__PersistenceConfiguration__ItemsAlternatives_1_1_0
            {
            pushFollow(FOLLOW_rule__PersistenceConfiguration__ItemsAlternatives_1_1_0_in_rule__PersistenceConfiguration__ItemsAssignment_1_15706);
            rule__PersistenceConfiguration__ItemsAlternatives_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getPersistenceConfigurationAccess().getItemsAlternatives_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__ItemsAssignment_1_1"


    // $ANTLR start "rule__PersistenceConfiguration__AliasAssignment_2_1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2878:1: rule__PersistenceConfiguration__AliasAssignment_2_1 : ( RULE_STRING ) ;
    public final void rule__PersistenceConfiguration__AliasAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2882:1: ( ( RULE_STRING ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2883:1: ( RULE_STRING )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2883:1: ( RULE_STRING )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2884:1: RULE_STRING
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getAliasSTRINGTerminalRuleCall_2_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__PersistenceConfiguration__AliasAssignment_2_15739); 
             after(grammarAccess.getPersistenceConfigurationAccess().getAliasSTRINGTerminalRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__AliasAssignment_2_1"


    // $ANTLR start "rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2893:1: rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2 : ( ( RULE_ID ) ) ;
    public final void rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2897:1: ( ( ( RULE_ID ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2898:1: ( ( RULE_ID ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2898:1: ( ( RULE_ID ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2899:1: ( RULE_ID )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getStrategiesStrategyCrossReference_3_0_1_2_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2900:1: ( RULE_ID )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2901:1: RULE_ID
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getStrategiesStrategyIDTerminalRuleCall_3_0_1_2_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_25774); 
             after(grammarAccess.getPersistenceConfigurationAccess().getStrategiesStrategyIDTerminalRuleCall_3_0_1_2_0_1()); 

            }

             after(grammarAccess.getPersistenceConfigurationAccess().getStrategiesStrategyCrossReference_3_0_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2"


    // $ANTLR start "rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2912:1: rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1 : ( ( RULE_ID ) ) ;
    public final void rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2916:1: ( ( ( RULE_ID ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2917:1: ( ( RULE_ID ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2917:1: ( ( RULE_ID ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2918:1: ( RULE_ID )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getStrategiesStrategyCrossReference_3_0_1_3_1_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2919:1: ( RULE_ID )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2920:1: RULE_ID
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getStrategiesStrategyIDTerminalRuleCall_3_0_1_3_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_15813); 
             after(grammarAccess.getPersistenceConfigurationAccess().getStrategiesStrategyIDTerminalRuleCall_3_0_1_3_1_0_1()); 

            }

             after(grammarAccess.getPersistenceConfigurationAccess().getStrategiesStrategyCrossReference_3_0_1_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1"


    // $ANTLR start "rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2931:1: rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2 : ( ( RULE_ID ) ) ;
    public final void rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2935:1: ( ( ( RULE_ID ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2936:1: ( ( RULE_ID ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2936:1: ( ( RULE_ID ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2937:1: ( RULE_ID )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getFiltersFilterCrossReference_3_0_2_2_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2938:1: ( RULE_ID )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2939:1: RULE_ID
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getFiltersFilterIDTerminalRuleCall_3_0_2_2_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__PersistenceConfiguration__FiltersAssignment_3_0_2_25852); 
             after(grammarAccess.getPersistenceConfigurationAccess().getFiltersFilterIDTerminalRuleCall_3_0_2_2_0_1()); 

            }

             after(grammarAccess.getPersistenceConfigurationAccess().getFiltersFilterCrossReference_3_0_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2"


    // $ANTLR start "rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2950:1: rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1 : ( ( RULE_ID ) ) ;
    public final void rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2954:1: ( ( ( RULE_ID ) ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2955:1: ( ( RULE_ID ) )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2955:1: ( ( RULE_ID ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2956:1: ( RULE_ID )
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getFiltersFilterCrossReference_3_0_2_3_1_0()); 
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2957:1: ( RULE_ID )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2958:1: RULE_ID
            {
             before(grammarAccess.getPersistenceConfigurationAccess().getFiltersFilterIDTerminalRuleCall_3_0_2_3_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_15891); 
             after(grammarAccess.getPersistenceConfigurationAccess().getFiltersFilterIDTerminalRuleCall_3_0_2_3_1_0_1()); 

            }

             after(grammarAccess.getPersistenceConfigurationAccess().getFiltersFilterCrossReference_3_0_2_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1"


    // $ANTLR start "rule__ItemConfig__ItemAssignment"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2969:1: rule__ItemConfig__ItemAssignment : ( RULE_ID ) ;
    public final void rule__ItemConfig__ItemAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2973:1: ( ( RULE_ID ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2974:1: ( RULE_ID )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2974:1: ( RULE_ID )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2975:1: RULE_ID
            {
             before(grammarAccess.getItemConfigAccess().getItemIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ItemConfig__ItemAssignment5926); 
             after(grammarAccess.getItemConfigAccess().getItemIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ItemConfig__ItemAssignment"


    // $ANTLR start "rule__GroupConfig__GroupAssignment_0"
    // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2984:1: rule__GroupConfig__GroupAssignment_0 : ( RULE_ID ) ;
    public final void rule__GroupConfig__GroupAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2988:1: ( ( RULE_ID ) )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2989:1: ( RULE_ID )
            {
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2989:1: ( RULE_ID )
            // ../org.openhab.model.persistence.ui/src-gen/org/openhab/model/persistence/ui/contentassist/antlr/internal/InternalPersistence.g:2990:1: RULE_ID
            {
             before(grammarAccess.getGroupConfigAccess().getGroupIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__GroupConfig__GroupAssignment_05957); 
             after(grammarAccess.getGroupConfigAccess().getGroupIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GroupConfig__GroupAssignment_0"

    // Delegated rules


 

    public static final BitSet FOLLOW_rulePersistenceModel_in_entryRulePersistenceModel61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePersistenceModel68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__0_in_rulePersistenceModel94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStrategy_in_entryRuleStrategy121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStrategy128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Strategy__Alternatives_in_ruleStrategy154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCronStrategy_in_entryRuleCronStrategy181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCronStrategy188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__CronStrategy__Group__0_in_ruleCronStrategy214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFilter_in_entryRuleFilter241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFilter248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Filter__Group__0_in_ruleFilter274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFilterDetails_in_entryRuleFilterDetails301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFilterDetails308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FilterDetails__Alternatives_in_ruleFilterDetails334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleThresholdFilter_in_entryRuleThresholdFilter361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleThresholdFilter368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ThresholdFilter__Group__0_in_ruleThresholdFilter394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTimeFilter_in_entryRuleTimeFilter421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTimeFilter428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TimeFilter__Group__0_in_ruleTimeFilter454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistenceConfiguration_in_entryRulePersistenceConfiguration481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePersistenceConfiguration488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group__0_in_rulePersistenceConfiguration514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAllConfig_in_entryRuleAllConfig541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAllConfig548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AllConfig__Group__0_in_ruleAllConfig574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleItemConfig_in_entryRuleItemConfig601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleItemConfig608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ItemConfig__ItemAssignment_in_ruleItemConfig634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupConfig_in_entryRuleGroupConfig661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroupConfig668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupConfig__Group__0_in_ruleGroupConfig694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDECIMAL_in_entryRuleDECIMAL721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDECIMAL728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DECIMAL__Group__0_in_ruleDECIMAL754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCronStrategy_in_rule__Strategy__Alternatives790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Strategy__NameAssignment_1_in_rule__Strategy__Alternatives807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleThresholdFilter_in_rule__FilterDetails__Alternatives840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTimeFilter_in_rule__FilterDetails__Alternatives857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__TimeFilter__UnitAlternatives_1_0890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__TimeFilter__UnitAlternatives_1_0910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__TimeFilter__UnitAlternatives_1_0930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__TimeFilter__UnitAlternatives_1_0950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAllConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_0_0984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleItemConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_0_01001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_0_01018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAllConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_1_1_01050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleItemConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_1_1_01067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroupConfig_in_rule__PersistenceConfiguration__ItemsAlternatives_1_1_01084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0__0_in_rule__PersistenceConfiguration__Alternatives_31116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__PersistenceConfiguration__Alternatives_31135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__0__Impl_in_rule__PersistenceModel__Group__01167 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__1_in_rule__PersistenceModel__Group__01170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__1__Impl_in_rule__PersistenceModel__Group__11228 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__2_in_rule__PersistenceModel__Group__11231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__PersistenceModel__Group__1__Impl1259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__2__Impl_in_rule__PersistenceModel__Group__21290 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__3_in_rule__PersistenceModel__Group__21293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__PersistenceModel__Group__2__Impl1321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__3__Impl_in_rule__PersistenceModel__Group__31352 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__4_in_rule__PersistenceModel__Group__31355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__StrategiesAssignment_3_in_rule__PersistenceModel__Group__3__Impl1382 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__4__Impl_in_rule__PersistenceModel__Group__41413 = new BitSet(new long[]{0x00000000000C0020L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__5_in_rule__PersistenceModel__Group__41416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4__0_in_rule__PersistenceModel__Group__4__Impl1443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__5__Impl_in_rule__PersistenceModel__Group__51474 = new BitSet(new long[]{0x0000000000C00000L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__6_in_rule__PersistenceModel__Group__51477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__PersistenceModel__Group__5__Impl1505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__6__Impl_in_rule__PersistenceModel__Group__61536 = new BitSet(new long[]{0x0000000000C00000L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__7_in_rule__PersistenceModel__Group__61539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_6__0_in_rule__PersistenceModel__Group__6__Impl1566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group__7__Impl_in_rule__PersistenceModel__Group__71597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_7__0_in_rule__PersistenceModel__Group__7__Impl1624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4__0__Impl_in_rule__PersistenceModel__Group_4__01671 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4__1_in_rule__PersistenceModel__Group_4__01674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__PersistenceModel__Group_4__0__Impl1702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4__1__Impl_in_rule__PersistenceModel__Group_4__11733 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4__2_in_rule__PersistenceModel__Group_4__11736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__PersistenceModel__Group_4__1__Impl1764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4__2__Impl_in_rule__PersistenceModel__Group_4__21795 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4__3_in_rule__PersistenceModel__Group_4__21798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__DefaultsAssignment_4_2_in_rule__PersistenceModel__Group_4__2__Impl1825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4__3__Impl_in_rule__PersistenceModel__Group_4__31855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4_3__0_in_rule__PersistenceModel__Group_4__3__Impl1882 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4_3__0__Impl_in_rule__PersistenceModel__Group_4_3__01921 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4_3__1_in_rule__PersistenceModel__Group_4_3__01924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__PersistenceModel__Group_4_3__0__Impl1952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_4_3__1__Impl_in_rule__PersistenceModel__Group_4_3__11983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__DefaultsAssignment_4_3_1_in_rule__PersistenceModel__Group_4_3__1__Impl2010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_6__0__Impl_in_rule__PersistenceModel__Group_6__02044 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_6__1_in_rule__PersistenceModel__Group_6__02047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__PersistenceModel__Group_6__0__Impl2075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_6__1__Impl_in_rule__PersistenceModel__Group_6__12106 = new BitSet(new long[]{0x0000000000040020L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_6__2_in_rule__PersistenceModel__Group_6__12109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__PersistenceModel__Group_6__1__Impl2137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_6__2__Impl_in_rule__PersistenceModel__Group_6__22168 = new BitSet(new long[]{0x0000000000040020L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_6__3_in_rule__PersistenceModel__Group_6__22171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__FiltersAssignment_6_2_in_rule__PersistenceModel__Group_6__2__Impl2198 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_6__3__Impl_in_rule__PersistenceModel__Group_6__32229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__PersistenceModel__Group_6__3__Impl2257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_7__0__Impl_in_rule__PersistenceModel__Group_7__02296 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_7__1_in_rule__PersistenceModel__Group_7__02299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__PersistenceModel__Group_7__0__Impl2327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_7__1__Impl_in_rule__PersistenceModel__Group_7__12358 = new BitSet(new long[]{0x0000000020040020L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_7__2_in_rule__PersistenceModel__Group_7__12361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__PersistenceModel__Group_7__1__Impl2389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_7__2__Impl_in_rule__PersistenceModel__Group_7__22420 = new BitSet(new long[]{0x0000000020040020L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_7__3_in_rule__PersistenceModel__Group_7__22423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceModel__ConfigsAssignment_7_2_in_rule__PersistenceModel__Group_7__2__Impl2450 = new BitSet(new long[]{0x0000000020000022L});
    public static final BitSet FOLLOW_rule__PersistenceModel__Group_7__3__Impl_in_rule__PersistenceModel__Group_7__32481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__PersistenceModel__Group_7__3__Impl2509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__CronStrategy__Group__0__Impl_in_rule__CronStrategy__Group__02548 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__CronStrategy__Group__1_in_rule__CronStrategy__Group__02551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__CronStrategy__Group__1__Impl_in_rule__CronStrategy__Group__12609 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__CronStrategy__Group__2_in_rule__CronStrategy__Group__12612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__CronStrategy__NameAssignment_1_in_rule__CronStrategy__Group__1__Impl2639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__CronStrategy__Group__2__Impl_in_rule__CronStrategy__Group__22669 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__CronStrategy__Group__3_in_rule__CronStrategy__Group__22672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__CronStrategy__Group__2__Impl2700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__CronStrategy__Group__3__Impl_in_rule__CronStrategy__Group__32731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__CronStrategy__CronExpressionAssignment_3_in_rule__CronStrategy__Group__3__Impl2758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Filter__Group__0__Impl_in_rule__Filter__Group__02796 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__Filter__Group__1_in_rule__Filter__Group__02799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Filter__NameAssignment_0_in_rule__Filter__Group__0__Impl2826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Filter__Group__1__Impl_in_rule__Filter__Group__12856 = new BitSet(new long[]{0x0000000002000010L});
    public static final BitSet FOLLOW_rule__Filter__Group__2_in_rule__Filter__Group__12859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__Filter__Group__1__Impl2887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Filter__Group__2__Impl_in_rule__Filter__Group__22918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Filter__DefinitionAssignment_2_in_rule__Filter__Group__2__Impl2945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ThresholdFilter__Group__0__Impl_in_rule__ThresholdFilter__Group__02981 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ThresholdFilter__Group__1_in_rule__ThresholdFilter__Group__02984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__ThresholdFilter__Group__0__Impl3012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ThresholdFilter__Group__1__Impl_in_rule__ThresholdFilter__Group__13043 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__ThresholdFilter__Group__2_in_rule__ThresholdFilter__Group__13046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ThresholdFilter__ValueAssignment_1_in_rule__ThresholdFilter__Group__1__Impl3073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ThresholdFilter__Group__2__Impl_in_rule__ThresholdFilter__Group__23103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ThresholdFilter__PercentAssignment_2_in_rule__ThresholdFilter__Group__2__Impl3130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TimeFilter__Group__0__Impl_in_rule__TimeFilter__Group__03166 = new BitSet(new long[]{0x0000000000007800L});
    public static final BitSet FOLLOW_rule__TimeFilter__Group__1_in_rule__TimeFilter__Group__03169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TimeFilter__ValueAssignment_0_in_rule__TimeFilter__Group__0__Impl3196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TimeFilter__Group__1__Impl_in_rule__TimeFilter__Group__13226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TimeFilter__UnitAssignment_1_in_rule__TimeFilter__Group__1__Impl3253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group__0__Impl_in_rule__PersistenceConfiguration__Group__03287 = new BitSet(new long[]{0x0000000005208000L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group__1_in_rule__PersistenceConfiguration__Group__03290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__ItemsAssignment_0_in_rule__PersistenceConfiguration__Group__0__Impl3317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group__1__Impl_in_rule__PersistenceConfiguration__Group__13347 = new BitSet(new long[]{0x0000000005208000L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group__2_in_rule__PersistenceConfiguration__Group__13350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_1__0_in_rule__PersistenceConfiguration__Group__1__Impl3377 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group__2__Impl_in_rule__PersistenceConfiguration__Group__23408 = new BitSet(new long[]{0x0000000005208000L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group__3_in_rule__PersistenceConfiguration__Group__23411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_2__0_in_rule__PersistenceConfiguration__Group__2__Impl3438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group__3__Impl_in_rule__PersistenceConfiguration__Group__33469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Alternatives_3_in_rule__PersistenceConfiguration__Group__3__Impl3496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_1__0__Impl_in_rule__PersistenceConfiguration__Group_1__03534 = new BitSet(new long[]{0x0000000020000020L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_1__1_in_rule__PersistenceConfiguration__Group_1__03537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__PersistenceConfiguration__Group_1__0__Impl3565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_1__1__Impl_in_rule__PersistenceConfiguration__Group_1__13596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__ItemsAssignment_1_1_in_rule__PersistenceConfiguration__Group_1__1__Impl3623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_2__0__Impl_in_rule__PersistenceConfiguration__Group_2__03657 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_2__1_in_rule__PersistenceConfiguration__Group_2__03660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__PersistenceConfiguration__Group_2__0__Impl3688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_2__1__Impl_in_rule__PersistenceConfiguration__Group_2__13719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__AliasAssignment_2_1_in_rule__PersistenceConfiguration__Group_2__1__Impl3746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0__0__Impl_in_rule__PersistenceConfiguration__Group_3_0__03780 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0__1_in_rule__PersistenceConfiguration__Group_3_0__03783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__PersistenceConfiguration__Group_3_0__0__Impl3811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0__1__Impl_in_rule__PersistenceConfiguration__Group_3_0__13842 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0__2_in_rule__PersistenceConfiguration__Group_3_0__13845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__0_in_rule__PersistenceConfiguration__Group_3_0__1__Impl3872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0__2__Impl_in_rule__PersistenceConfiguration__Group_3_0__23903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__0_in_rule__PersistenceConfiguration__Group_3_0__2__Impl3930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__0__Impl_in_rule__PersistenceConfiguration__Group_3_0_1__03967 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__1_in_rule__PersistenceConfiguration__Group_3_0_1__03970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__PersistenceConfiguration__Group_3_0_1__0__Impl3998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__1__Impl_in_rule__PersistenceConfiguration__Group_3_0_1__14029 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__2_in_rule__PersistenceConfiguration__Group_3_0_1__14032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__PersistenceConfiguration__Group_3_0_1__1__Impl4060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__2__Impl_in_rule__PersistenceConfiguration__Group_3_0_1__24091 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__3_in_rule__PersistenceConfiguration__Group_3_0_1__24094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_2_in_rule__PersistenceConfiguration__Group_3_0_1__2__Impl4121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1__3__Impl_in_rule__PersistenceConfiguration__Group_3_0_1__34151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1_3__0_in_rule__PersistenceConfiguration__Group_3_0_1__3__Impl4178 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl_in_rule__PersistenceConfiguration__Group_3_0_1_3__04217 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1_3__1_in_rule__PersistenceConfiguration__Group_3_0_1_3__04220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__PersistenceConfiguration__Group_3_0_1_3__0__Impl4248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl_in_rule__PersistenceConfiguration__Group_3_0_1_3__14279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_1_in_rule__PersistenceConfiguration__Group_3_0_1_3__1__Impl4306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__0__Impl_in_rule__PersistenceConfiguration__Group_3_0_2__04340 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__1_in_rule__PersistenceConfiguration__Group_3_0_2__04343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__PersistenceConfiguration__Group_3_0_2__0__Impl4371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__1__Impl_in_rule__PersistenceConfiguration__Group_3_0_2__14402 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__2_in_rule__PersistenceConfiguration__Group_3_0_2__14405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__PersistenceConfiguration__Group_3_0_2__1__Impl4433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__2__Impl_in_rule__PersistenceConfiguration__Group_3_0_2__24464 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__3_in_rule__PersistenceConfiguration__Group_3_0_2__24467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__FiltersAssignment_3_0_2_2_in_rule__PersistenceConfiguration__Group_3_0_2__2__Impl4494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2__3__Impl_in_rule__PersistenceConfiguration__Group_3_0_2__34524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2_3__0_in_rule__PersistenceConfiguration__Group_3_0_2__3__Impl4551 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl_in_rule__PersistenceConfiguration__Group_3_0_2_3__04590 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2_3__1_in_rule__PersistenceConfiguration__Group_3_0_2_3__04593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__PersistenceConfiguration__Group_3_0_2_3__0__Impl4621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl_in_rule__PersistenceConfiguration__Group_3_0_2_3__14652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_1_in_rule__PersistenceConfiguration__Group_3_0_2_3__1__Impl4679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AllConfig__Group__0__Impl_in_rule__AllConfig__Group__04713 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__AllConfig__Group__1_in_rule__AllConfig__Group__04716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__AllConfig__Group__1__Impl_in_rule__AllConfig__Group__14774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__AllConfig__Group__1__Impl4802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupConfig__Group__0__Impl_in_rule__GroupConfig__Group__04837 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__GroupConfig__Group__1_in_rule__GroupConfig__Group__04840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupConfig__GroupAssignment_0_in_rule__GroupConfig__Group__0__Impl4867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GroupConfig__Group__1__Impl_in_rule__GroupConfig__Group__14897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__GroupConfig__Group__1__Impl4925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DECIMAL__Group__0__Impl_in_rule__DECIMAL__Group__04960 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__DECIMAL__Group__1_in_rule__DECIMAL__Group__04963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__DECIMAL__Group__0__Impl4990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DECIMAL__Group__1__Impl_in_rule__DECIMAL__Group__15019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DECIMAL__Group_1__0_in_rule__DECIMAL__Group__1__Impl5046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DECIMAL__Group_1__0__Impl_in_rule__DECIMAL__Group_1__05081 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__DECIMAL__Group_1__1_in_rule__DECIMAL__Group_1__05084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__DECIMAL__Group_1__0__Impl5112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DECIMAL__Group_1__1__Impl_in_rule__DECIMAL__Group_1__15143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__DECIMAL__Group_1__1__Impl5170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStrategy_in_rule__PersistenceModel__StrategiesAssignment_35208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__PersistenceModel__DefaultsAssignment_4_25243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__PersistenceModel__DefaultsAssignment_4_3_15282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFilter_in_rule__PersistenceModel__FiltersAssignment_6_25317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistenceConfiguration_in_rule__PersistenceModel__ConfigsAssignment_7_25348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Strategy__NameAssignment_15379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__CronStrategy__NameAssignment_15410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__CronStrategy__CronExpressionAssignment_35441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Filter__NameAssignment_05472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFilterDetails_in_rule__Filter__DefinitionAssignment_25503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDECIMAL_in_rule__ThresholdFilter__ValueAssignment_15534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__ThresholdFilter__PercentAssignment_25570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__TimeFilter__ValueAssignment_05609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TimeFilter__UnitAlternatives_1_0_in_rule__TimeFilter__UnitAssignment_15640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__ItemsAlternatives_0_0_in_rule__PersistenceConfiguration__ItemsAssignment_05673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistenceConfiguration__ItemsAlternatives_1_1_0_in_rule__PersistenceConfiguration__ItemsAssignment_1_15706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__PersistenceConfiguration__AliasAssignment_2_15739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_25774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__PersistenceConfiguration__StrategiesAssignment_3_0_1_3_15813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__PersistenceConfiguration__FiltersAssignment_3_0_2_25852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__PersistenceConfiguration__FiltersAssignment_3_0_2_3_15891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ItemConfig__ItemAssignment5926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__GroupConfig__GroupAssignment_05957 = new BitSet(new long[]{0x0000000000000002L});

}
package org.openhab.model.ui.contentassist.antlr.internal; 

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
import org.openhab.model.services.ItemsGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalItemsParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Switch'", "'Rollershutter'", "'Number'", "'String'", "'Dimmer'", "'Contact'", "'DateTime'", "'Color'", "'Location'", "'AND'", "'OR'", "'NAND'", "'NOR'", "'AVG'", "'SUM'", "'MAX'", "'MIN'", "'COUNT'", "'<'", "'>'", "'('", "')'", "','", "'{'", "'}'", "'Group'", "':'", "'='"
    };
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__38=38;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
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


        public InternalItemsParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalItemsParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalItemsParser.tokenNames; }
    public String getGrammarFileName() { return "../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g"; }


     
     	private ItemsGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(ItemsGrammarAccess grammarAccess) {
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




    // $ANTLR start "entryRuleItemModel"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:60:1: entryRuleItemModel : ruleItemModel EOF ;
    public final void entryRuleItemModel() throws RecognitionException {
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:61:1: ( ruleItemModel EOF )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:62:1: ruleItemModel EOF
            {
             before(grammarAccess.getItemModelRule()); 
            pushFollow(FOLLOW_ruleItemModel_in_entryRuleItemModel61);
            ruleItemModel();

            state._fsp--;

             after(grammarAccess.getItemModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleItemModel68); 

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
    // $ANTLR end "entryRuleItemModel"


    // $ANTLR start "ruleItemModel"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:69:1: ruleItemModel : ( ( rule__ItemModel__Group__0 ) ) ;
    public final void ruleItemModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:73:2: ( ( ( rule__ItemModel__Group__0 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:74:1: ( ( rule__ItemModel__Group__0 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:74:1: ( ( rule__ItemModel__Group__0 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:75:1: ( rule__ItemModel__Group__0 )
            {
             before(grammarAccess.getItemModelAccess().getGroup()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:76:1: ( rule__ItemModel__Group__0 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:76:2: rule__ItemModel__Group__0
            {
            pushFollow(FOLLOW_rule__ItemModel__Group__0_in_ruleItemModel94);
            rule__ItemModel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getItemModelAccess().getGroup()); 

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
    // $ANTLR end "ruleItemModel"


    // $ANTLR start "entryRuleModelItem"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:88:1: entryRuleModelItem : ruleModelItem EOF ;
    public final void entryRuleModelItem() throws RecognitionException {
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:89:1: ( ruleModelItem EOF )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:90:1: ruleModelItem EOF
            {
             before(grammarAccess.getModelItemRule()); 
            pushFollow(FOLLOW_ruleModelItem_in_entryRuleModelItem121);
            ruleModelItem();

            state._fsp--;

             after(grammarAccess.getModelItemRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModelItem128); 

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
    // $ANTLR end "entryRuleModelItem"


    // $ANTLR start "ruleModelItem"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:97:1: ruleModelItem : ( ( rule__ModelItem__Group__0 ) ) ;
    public final void ruleModelItem() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:101:2: ( ( ( rule__ModelItem__Group__0 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:102:1: ( ( rule__ModelItem__Group__0 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:102:1: ( ( rule__ModelItem__Group__0 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:103:1: ( rule__ModelItem__Group__0 )
            {
             before(grammarAccess.getModelItemAccess().getGroup()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:104:1: ( rule__ModelItem__Group__0 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:104:2: rule__ModelItem__Group__0
            {
            pushFollow(FOLLOW_rule__ModelItem__Group__0_in_ruleModelItem154);
            rule__ModelItem__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getModelItemAccess().getGroup()); 

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
    // $ANTLR end "ruleModelItem"


    // $ANTLR start "entryRuleModelGroupItem"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:116:1: entryRuleModelGroupItem : ruleModelGroupItem EOF ;
    public final void entryRuleModelGroupItem() throws RecognitionException {
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:117:1: ( ruleModelGroupItem EOF )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:118:1: ruleModelGroupItem EOF
            {
             before(grammarAccess.getModelGroupItemRule()); 
            pushFollow(FOLLOW_ruleModelGroupItem_in_entryRuleModelGroupItem181);
            ruleModelGroupItem();

            state._fsp--;

             after(grammarAccess.getModelGroupItemRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModelGroupItem188); 

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
    // $ANTLR end "entryRuleModelGroupItem"


    // $ANTLR start "ruleModelGroupItem"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:125:1: ruleModelGroupItem : ( ( rule__ModelGroupItem__Group__0 ) ) ;
    public final void ruleModelGroupItem() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:129:2: ( ( ( rule__ModelGroupItem__Group__0 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:130:1: ( ( rule__ModelGroupItem__Group__0 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:130:1: ( ( rule__ModelGroupItem__Group__0 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:131:1: ( rule__ModelGroupItem__Group__0 )
            {
             before(grammarAccess.getModelGroupItemAccess().getGroup()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:132:1: ( rule__ModelGroupItem__Group__0 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:132:2: rule__ModelGroupItem__Group__0
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group__0_in_ruleModelGroupItem214);
            rule__ModelGroupItem__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getModelGroupItemAccess().getGroup()); 

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
    // $ANTLR end "ruleModelGroupItem"


    // $ANTLR start "entryRuleModelNormalItem"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:144:1: entryRuleModelNormalItem : ruleModelNormalItem EOF ;
    public final void entryRuleModelNormalItem() throws RecognitionException {
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:145:1: ( ruleModelNormalItem EOF )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:146:1: ruleModelNormalItem EOF
            {
             before(grammarAccess.getModelNormalItemRule()); 
            pushFollow(FOLLOW_ruleModelNormalItem_in_entryRuleModelNormalItem241);
            ruleModelNormalItem();

            state._fsp--;

             after(grammarAccess.getModelNormalItemRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModelNormalItem248); 

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
    // $ANTLR end "entryRuleModelNormalItem"


    // $ANTLR start "ruleModelNormalItem"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:153:1: ruleModelNormalItem : ( ( rule__ModelNormalItem__TypeAssignment ) ) ;
    public final void ruleModelNormalItem() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:157:2: ( ( ( rule__ModelNormalItem__TypeAssignment ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:158:1: ( ( rule__ModelNormalItem__TypeAssignment ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:158:1: ( ( rule__ModelNormalItem__TypeAssignment ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:159:1: ( rule__ModelNormalItem__TypeAssignment )
            {
             before(grammarAccess.getModelNormalItemAccess().getTypeAssignment()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:160:1: ( rule__ModelNormalItem__TypeAssignment )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:160:2: rule__ModelNormalItem__TypeAssignment
            {
            pushFollow(FOLLOW_rule__ModelNormalItem__TypeAssignment_in_ruleModelNormalItem274);
            rule__ModelNormalItem__TypeAssignment();

            state._fsp--;


            }

             after(grammarAccess.getModelNormalItemAccess().getTypeAssignment()); 

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
    // $ANTLR end "ruleModelNormalItem"


    // $ANTLR start "entryRuleModelItemType"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:172:1: entryRuleModelItemType : ruleModelItemType EOF ;
    public final void entryRuleModelItemType() throws RecognitionException {
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:173:1: ( ruleModelItemType EOF )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:174:1: ruleModelItemType EOF
            {
             before(grammarAccess.getModelItemTypeRule()); 
            pushFollow(FOLLOW_ruleModelItemType_in_entryRuleModelItemType301);
            ruleModelItemType();

            state._fsp--;

             after(grammarAccess.getModelItemTypeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModelItemType308); 

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
    // $ANTLR end "entryRuleModelItemType"


    // $ANTLR start "ruleModelItemType"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:181:1: ruleModelItemType : ( ( rule__ModelItemType__Alternatives ) ) ;
    public final void ruleModelItemType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:185:2: ( ( ( rule__ModelItemType__Alternatives ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:186:1: ( ( rule__ModelItemType__Alternatives ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:186:1: ( ( rule__ModelItemType__Alternatives ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:187:1: ( rule__ModelItemType__Alternatives )
            {
             before(grammarAccess.getModelItemTypeAccess().getAlternatives()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:188:1: ( rule__ModelItemType__Alternatives )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:188:2: rule__ModelItemType__Alternatives
            {
            pushFollow(FOLLOW_rule__ModelItemType__Alternatives_in_ruleModelItemType334);
            rule__ModelItemType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getModelItemTypeAccess().getAlternatives()); 

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
    // $ANTLR end "ruleModelItemType"


    // $ANTLR start "entryRuleModelBinding"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:200:1: entryRuleModelBinding : ruleModelBinding EOF ;
    public final void entryRuleModelBinding() throws RecognitionException {
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:201:1: ( ruleModelBinding EOF )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:202:1: ruleModelBinding EOF
            {
             before(grammarAccess.getModelBindingRule()); 
            pushFollow(FOLLOW_ruleModelBinding_in_entryRuleModelBinding361);
            ruleModelBinding();

            state._fsp--;

             after(grammarAccess.getModelBindingRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModelBinding368); 

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
    // $ANTLR end "entryRuleModelBinding"


    // $ANTLR start "ruleModelBinding"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:209:1: ruleModelBinding : ( ( rule__ModelBinding__Group__0 ) ) ;
    public final void ruleModelBinding() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:213:2: ( ( ( rule__ModelBinding__Group__0 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:214:1: ( ( rule__ModelBinding__Group__0 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:214:1: ( ( rule__ModelBinding__Group__0 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:215:1: ( rule__ModelBinding__Group__0 )
            {
             before(grammarAccess.getModelBindingAccess().getGroup()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:216:1: ( rule__ModelBinding__Group__0 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:216:2: rule__ModelBinding__Group__0
            {
            pushFollow(FOLLOW_rule__ModelBinding__Group__0_in_ruleModelBinding394);
            rule__ModelBinding__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getModelBindingAccess().getGroup()); 

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
    // $ANTLR end "ruleModelBinding"


    // $ANTLR start "ruleModelGroupFunction"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:229:1: ruleModelGroupFunction : ( ( rule__ModelGroupFunction__Alternatives ) ) ;
    public final void ruleModelGroupFunction() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:233:1: ( ( ( rule__ModelGroupFunction__Alternatives ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:234:1: ( ( rule__ModelGroupFunction__Alternatives ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:234:1: ( ( rule__ModelGroupFunction__Alternatives ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:235:1: ( rule__ModelGroupFunction__Alternatives )
            {
             before(grammarAccess.getModelGroupFunctionAccess().getAlternatives()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:236:1: ( rule__ModelGroupFunction__Alternatives )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:236:2: rule__ModelGroupFunction__Alternatives
            {
            pushFollow(FOLLOW_rule__ModelGroupFunction__Alternatives_in_ruleModelGroupFunction431);
            rule__ModelGroupFunction__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getModelGroupFunctionAccess().getAlternatives()); 

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
    // $ANTLR end "ruleModelGroupFunction"


    // $ANTLR start "rule__ModelItem__Alternatives_0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:247:1: rule__ModelItem__Alternatives_0 : ( ( ruleModelNormalItem ) | ( ruleModelGroupItem ) );
    public final void rule__ModelItem__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:251:1: ( ( ruleModelNormalItem ) | ( ruleModelGroupItem ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_ID||(LA1_0>=11 && LA1_0<=19)) ) {
                alt1=1;
            }
            else if ( (LA1_0==36) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:252:1: ( ruleModelNormalItem )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:252:1: ( ruleModelNormalItem )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:253:1: ruleModelNormalItem
                    {
                     before(grammarAccess.getModelItemAccess().getModelNormalItemParserRuleCall_0_0()); 
                    pushFollow(FOLLOW_ruleModelNormalItem_in_rule__ModelItem__Alternatives_0466);
                    ruleModelNormalItem();

                    state._fsp--;

                     after(grammarAccess.getModelItemAccess().getModelNormalItemParserRuleCall_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:258:6: ( ruleModelGroupItem )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:258:6: ( ruleModelGroupItem )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:259:1: ruleModelGroupItem
                    {
                     before(grammarAccess.getModelItemAccess().getModelGroupItemParserRuleCall_0_1()); 
                    pushFollow(FOLLOW_ruleModelGroupItem_in_rule__ModelItem__Alternatives_0483);
                    ruleModelGroupItem();

                    state._fsp--;

                     after(grammarAccess.getModelItemAccess().getModelGroupItemParserRuleCall_0_1()); 

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
    // $ANTLR end "rule__ModelItem__Alternatives_0"


    // $ANTLR start "rule__ModelItem__IconAlternatives_3_1_0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:269:1: rule__ModelItem__IconAlternatives_3_1_0 : ( ( RULE_ID ) | ( RULE_STRING ) );
    public final void rule__ModelItem__IconAlternatives_3_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:273:1: ( ( RULE_ID ) | ( RULE_STRING ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_ID) ) {
                alt2=1;
            }
            else if ( (LA2_0==RULE_STRING) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:274:1: ( RULE_ID )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:274:1: ( RULE_ID )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:275:1: RULE_ID
                    {
                     before(grammarAccess.getModelItemAccess().getIconIDTerminalRuleCall_3_1_0_0()); 
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ModelItem__IconAlternatives_3_1_0515); 
                     after(grammarAccess.getModelItemAccess().getIconIDTerminalRuleCall_3_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:280:6: ( RULE_STRING )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:280:6: ( RULE_STRING )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:281:1: RULE_STRING
                    {
                     before(grammarAccess.getModelItemAccess().getIconSTRINGTerminalRuleCall_3_1_0_1()); 
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__ModelItem__IconAlternatives_3_1_0532); 
                     after(grammarAccess.getModelItemAccess().getIconSTRINGTerminalRuleCall_3_1_0_1()); 

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
    // $ANTLR end "rule__ModelItem__IconAlternatives_3_1_0"


    // $ANTLR start "rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:291:1: rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0 : ( ( RULE_ID ) | ( RULE_STRING ) );
    public final void rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:295:1: ( ( RULE_ID ) | ( RULE_STRING ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                alt3=1;
            }
            else if ( (LA3_0==RULE_STRING) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:296:1: ( RULE_ID )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:296:1: ( RULE_ID )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:297:1: RULE_ID
                    {
                     before(grammarAccess.getModelGroupItemAccess().getArgsIDTerminalRuleCall_2_2_2_1_0_0()); 
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0564); 
                     after(grammarAccess.getModelGroupItemAccess().getArgsIDTerminalRuleCall_2_2_2_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:302:6: ( RULE_STRING )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:302:6: ( RULE_STRING )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:303:1: RULE_STRING
                    {
                     before(grammarAccess.getModelGroupItemAccess().getArgsSTRINGTerminalRuleCall_2_2_2_1_0_1()); 
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0581); 
                     after(grammarAccess.getModelGroupItemAccess().getArgsSTRINGTerminalRuleCall_2_2_2_1_0_1()); 

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
    // $ANTLR end "rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0"


    // $ANTLR start "rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:313:1: rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0 : ( ( RULE_ID ) | ( RULE_STRING ) );
    public final void rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:317:1: ( ( RULE_ID ) | ( RULE_STRING ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_STRING) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:318:1: ( RULE_ID )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:318:1: ( RULE_ID )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:319:1: RULE_ID
                    {
                     before(grammarAccess.getModelGroupItemAccess().getArgsIDTerminalRuleCall_2_2_2_2_1_0_0()); 
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0613); 
                     after(grammarAccess.getModelGroupItemAccess().getArgsIDTerminalRuleCall_2_2_2_2_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:324:6: ( RULE_STRING )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:324:6: ( RULE_STRING )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:325:1: RULE_STRING
                    {
                     before(grammarAccess.getModelGroupItemAccess().getArgsSTRINGTerminalRuleCall_2_2_2_2_1_0_1()); 
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0630); 
                     after(grammarAccess.getModelGroupItemAccess().getArgsSTRINGTerminalRuleCall_2_2_2_2_1_0_1()); 

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
    // $ANTLR end "rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0"


    // $ANTLR start "rule__ModelItemType__Alternatives"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:335:1: rule__ModelItemType__Alternatives : ( ( 'Switch' ) | ( 'Rollershutter' ) | ( 'Number' ) | ( 'String' ) | ( 'Dimmer' ) | ( 'Contact' ) | ( 'DateTime' ) | ( 'Color' ) | ( 'Location' ) | ( RULE_ID ) );
    public final void rule__ModelItemType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:339:1: ( ( 'Switch' ) | ( 'Rollershutter' ) | ( 'Number' ) | ( 'String' ) | ( 'Dimmer' ) | ( 'Contact' ) | ( 'DateTime' ) | ( 'Color' ) | ( 'Location' ) | ( RULE_ID ) )
            int alt5=10;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt5=1;
                }
                break;
            case 12:
                {
                alt5=2;
                }
                break;
            case 13:
                {
                alt5=3;
                }
                break;
            case 14:
                {
                alt5=4;
                }
                break;
            case 15:
                {
                alt5=5;
                }
                break;
            case 16:
                {
                alt5=6;
                }
                break;
            case 17:
                {
                alt5=7;
                }
                break;
            case 18:
                {
                alt5=8;
                }
                break;
            case 19:
                {
                alt5=9;
                }
                break;
            case RULE_ID:
                {
                alt5=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:340:1: ( 'Switch' )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:340:1: ( 'Switch' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:341:1: 'Switch'
                    {
                     before(grammarAccess.getModelItemTypeAccess().getSwitchKeyword_0()); 
                    match(input,11,FOLLOW_11_in_rule__ModelItemType__Alternatives663); 
                     after(grammarAccess.getModelItemTypeAccess().getSwitchKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:348:6: ( 'Rollershutter' )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:348:6: ( 'Rollershutter' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:349:1: 'Rollershutter'
                    {
                     before(grammarAccess.getModelItemTypeAccess().getRollershutterKeyword_1()); 
                    match(input,12,FOLLOW_12_in_rule__ModelItemType__Alternatives683); 
                     after(grammarAccess.getModelItemTypeAccess().getRollershutterKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:356:6: ( 'Number' )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:356:6: ( 'Number' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:357:1: 'Number'
                    {
                     before(grammarAccess.getModelItemTypeAccess().getNumberKeyword_2()); 
                    match(input,13,FOLLOW_13_in_rule__ModelItemType__Alternatives703); 
                     after(grammarAccess.getModelItemTypeAccess().getNumberKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:364:6: ( 'String' )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:364:6: ( 'String' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:365:1: 'String'
                    {
                     before(grammarAccess.getModelItemTypeAccess().getStringKeyword_3()); 
                    match(input,14,FOLLOW_14_in_rule__ModelItemType__Alternatives723); 
                     after(grammarAccess.getModelItemTypeAccess().getStringKeyword_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:372:6: ( 'Dimmer' )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:372:6: ( 'Dimmer' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:373:1: 'Dimmer'
                    {
                     before(grammarAccess.getModelItemTypeAccess().getDimmerKeyword_4()); 
                    match(input,15,FOLLOW_15_in_rule__ModelItemType__Alternatives743); 
                     after(grammarAccess.getModelItemTypeAccess().getDimmerKeyword_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:380:6: ( 'Contact' )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:380:6: ( 'Contact' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:381:1: 'Contact'
                    {
                     before(grammarAccess.getModelItemTypeAccess().getContactKeyword_5()); 
                    match(input,16,FOLLOW_16_in_rule__ModelItemType__Alternatives763); 
                     after(grammarAccess.getModelItemTypeAccess().getContactKeyword_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:388:6: ( 'DateTime' )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:388:6: ( 'DateTime' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:389:1: 'DateTime'
                    {
                     before(grammarAccess.getModelItemTypeAccess().getDateTimeKeyword_6()); 
                    match(input,17,FOLLOW_17_in_rule__ModelItemType__Alternatives783); 
                     after(grammarAccess.getModelItemTypeAccess().getDateTimeKeyword_6()); 

                    }


                    }
                    break;
                case 8 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:396:6: ( 'Color' )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:396:6: ( 'Color' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:397:1: 'Color'
                    {
                     before(grammarAccess.getModelItemTypeAccess().getColorKeyword_7()); 
                    match(input,18,FOLLOW_18_in_rule__ModelItemType__Alternatives803); 
                     after(grammarAccess.getModelItemTypeAccess().getColorKeyword_7()); 

                    }


                    }
                    break;
                case 9 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:404:6: ( 'Location' )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:404:6: ( 'Location' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:405:1: 'Location'
                    {
                     before(grammarAccess.getModelItemTypeAccess().getLocationKeyword_8()); 
                    match(input,19,FOLLOW_19_in_rule__ModelItemType__Alternatives823); 
                     after(grammarAccess.getModelItemTypeAccess().getLocationKeyword_8()); 

                    }


                    }
                    break;
                case 10 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:412:6: ( RULE_ID )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:412:6: ( RULE_ID )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:413:1: RULE_ID
                    {
                     before(grammarAccess.getModelItemTypeAccess().getIDTerminalRuleCall_9()); 
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ModelItemType__Alternatives842); 
                     after(grammarAccess.getModelItemTypeAccess().getIDTerminalRuleCall_9()); 

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
    // $ANTLR end "rule__ModelItemType__Alternatives"


    // $ANTLR start "rule__ModelGroupFunction__Alternatives"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:423:1: rule__ModelGroupFunction__Alternatives : ( ( ( 'AND' ) ) | ( ( 'OR' ) ) | ( ( 'NAND' ) ) | ( ( 'NOR' ) ) | ( ( 'AVG' ) ) | ( ( 'SUM' ) ) | ( ( 'MAX' ) ) | ( ( 'MIN' ) ) | ( ( 'COUNT' ) ) );
    public final void rule__ModelGroupFunction__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:427:1: ( ( ( 'AND' ) ) | ( ( 'OR' ) ) | ( ( 'NAND' ) ) | ( ( 'NOR' ) ) | ( ( 'AVG' ) ) | ( ( 'SUM' ) ) | ( ( 'MAX' ) ) | ( ( 'MIN' ) ) | ( ( 'COUNT' ) ) )
            int alt6=9;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt6=1;
                }
                break;
            case 21:
                {
                alt6=2;
                }
                break;
            case 22:
                {
                alt6=3;
                }
                break;
            case 23:
                {
                alt6=4;
                }
                break;
            case 24:
                {
                alt6=5;
                }
                break;
            case 25:
                {
                alt6=6;
                }
                break;
            case 26:
                {
                alt6=7;
                }
                break;
            case 27:
                {
                alt6=8;
                }
                break;
            case 28:
                {
                alt6=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:428:1: ( ( 'AND' ) )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:428:1: ( ( 'AND' ) )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:429:1: ( 'AND' )
                    {
                     before(grammarAccess.getModelGroupFunctionAccess().getANDEnumLiteralDeclaration_0()); 
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:430:1: ( 'AND' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:430:3: 'AND'
                    {
                    match(input,20,FOLLOW_20_in_rule__ModelGroupFunction__Alternatives875); 

                    }

                     after(grammarAccess.getModelGroupFunctionAccess().getANDEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:435:6: ( ( 'OR' ) )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:435:6: ( ( 'OR' ) )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:436:1: ( 'OR' )
                    {
                     before(grammarAccess.getModelGroupFunctionAccess().getOREnumLiteralDeclaration_1()); 
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:437:1: ( 'OR' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:437:3: 'OR'
                    {
                    match(input,21,FOLLOW_21_in_rule__ModelGroupFunction__Alternatives896); 

                    }

                     after(grammarAccess.getModelGroupFunctionAccess().getOREnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:442:6: ( ( 'NAND' ) )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:442:6: ( ( 'NAND' ) )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:443:1: ( 'NAND' )
                    {
                     before(grammarAccess.getModelGroupFunctionAccess().getNANDEnumLiteralDeclaration_2()); 
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:444:1: ( 'NAND' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:444:3: 'NAND'
                    {
                    match(input,22,FOLLOW_22_in_rule__ModelGroupFunction__Alternatives917); 

                    }

                     after(grammarAccess.getModelGroupFunctionAccess().getNANDEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:449:6: ( ( 'NOR' ) )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:449:6: ( ( 'NOR' ) )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:450:1: ( 'NOR' )
                    {
                     before(grammarAccess.getModelGroupFunctionAccess().getNOREnumLiteralDeclaration_3()); 
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:451:1: ( 'NOR' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:451:3: 'NOR'
                    {
                    match(input,23,FOLLOW_23_in_rule__ModelGroupFunction__Alternatives938); 

                    }

                     after(grammarAccess.getModelGroupFunctionAccess().getNOREnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:456:6: ( ( 'AVG' ) )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:456:6: ( ( 'AVG' ) )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:457:1: ( 'AVG' )
                    {
                     before(grammarAccess.getModelGroupFunctionAccess().getAVGEnumLiteralDeclaration_4()); 
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:458:1: ( 'AVG' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:458:3: 'AVG'
                    {
                    match(input,24,FOLLOW_24_in_rule__ModelGroupFunction__Alternatives959); 

                    }

                     after(grammarAccess.getModelGroupFunctionAccess().getAVGEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:463:6: ( ( 'SUM' ) )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:463:6: ( ( 'SUM' ) )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:464:1: ( 'SUM' )
                    {
                     before(grammarAccess.getModelGroupFunctionAccess().getSUMEnumLiteralDeclaration_5()); 
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:465:1: ( 'SUM' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:465:3: 'SUM'
                    {
                    match(input,25,FOLLOW_25_in_rule__ModelGroupFunction__Alternatives980); 

                    }

                     after(grammarAccess.getModelGroupFunctionAccess().getSUMEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:470:6: ( ( 'MAX' ) )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:470:6: ( ( 'MAX' ) )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:471:1: ( 'MAX' )
                    {
                     before(grammarAccess.getModelGroupFunctionAccess().getMAXEnumLiteralDeclaration_6()); 
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:472:1: ( 'MAX' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:472:3: 'MAX'
                    {
                    match(input,26,FOLLOW_26_in_rule__ModelGroupFunction__Alternatives1001); 

                    }

                     after(grammarAccess.getModelGroupFunctionAccess().getMAXEnumLiteralDeclaration_6()); 

                    }


                    }
                    break;
                case 8 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:477:6: ( ( 'MIN' ) )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:477:6: ( ( 'MIN' ) )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:478:1: ( 'MIN' )
                    {
                     before(grammarAccess.getModelGroupFunctionAccess().getMINEnumLiteralDeclaration_7()); 
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:479:1: ( 'MIN' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:479:3: 'MIN'
                    {
                    match(input,27,FOLLOW_27_in_rule__ModelGroupFunction__Alternatives1022); 

                    }

                     after(grammarAccess.getModelGroupFunctionAccess().getMINEnumLiteralDeclaration_7()); 

                    }


                    }
                    break;
                case 9 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:484:6: ( ( 'COUNT' ) )
                    {
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:484:6: ( ( 'COUNT' ) )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:485:1: ( 'COUNT' )
                    {
                     before(grammarAccess.getModelGroupFunctionAccess().getCOUNTEnumLiteralDeclaration_8()); 
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:486:1: ( 'COUNT' )
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:486:3: 'COUNT'
                    {
                    match(input,28,FOLLOW_28_in_rule__ModelGroupFunction__Alternatives1043); 

                    }

                     after(grammarAccess.getModelGroupFunctionAccess().getCOUNTEnumLiteralDeclaration_8()); 

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
    // $ANTLR end "rule__ModelGroupFunction__Alternatives"


    // $ANTLR start "rule__ItemModel__Group__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:498:1: rule__ItemModel__Group__0 : rule__ItemModel__Group__0__Impl rule__ItemModel__Group__1 ;
    public final void rule__ItemModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:502:1: ( rule__ItemModel__Group__0__Impl rule__ItemModel__Group__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:503:2: rule__ItemModel__Group__0__Impl rule__ItemModel__Group__1
            {
            pushFollow(FOLLOW_rule__ItemModel__Group__0__Impl_in_rule__ItemModel__Group__01076);
            rule__ItemModel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ItemModel__Group__1_in_rule__ItemModel__Group__01079);
            rule__ItemModel__Group__1();

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
    // $ANTLR end "rule__ItemModel__Group__0"


    // $ANTLR start "rule__ItemModel__Group__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:510:1: rule__ItemModel__Group__0__Impl : ( () ) ;
    public final void rule__ItemModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:514:1: ( ( () ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:515:1: ( () )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:515:1: ( () )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:516:1: ()
            {
             before(grammarAccess.getItemModelAccess().getItemModelAction_0()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:517:1: ()
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:519:1: 
            {
            }

             after(grammarAccess.getItemModelAccess().getItemModelAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ItemModel__Group__0__Impl"


    // $ANTLR start "rule__ItemModel__Group__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:529:1: rule__ItemModel__Group__1 : rule__ItemModel__Group__1__Impl ;
    public final void rule__ItemModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:533:1: ( rule__ItemModel__Group__1__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:534:2: rule__ItemModel__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__ItemModel__Group__1__Impl_in_rule__ItemModel__Group__11137);
            rule__ItemModel__Group__1__Impl();

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
    // $ANTLR end "rule__ItemModel__Group__1"


    // $ANTLR start "rule__ItemModel__Group__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:540:1: rule__ItemModel__Group__1__Impl : ( ( rule__ItemModel__ItemsAssignment_1 )* ) ;
    public final void rule__ItemModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:544:1: ( ( ( rule__ItemModel__ItemsAssignment_1 )* ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:545:1: ( ( rule__ItemModel__ItemsAssignment_1 )* )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:545:1: ( ( rule__ItemModel__ItemsAssignment_1 )* )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:546:1: ( rule__ItemModel__ItemsAssignment_1 )*
            {
             before(grammarAccess.getItemModelAccess().getItemsAssignment_1()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:547:1: ( rule__ItemModel__ItemsAssignment_1 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_ID||(LA7_0>=11 && LA7_0<=19)||LA7_0==36) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:547:2: rule__ItemModel__ItemsAssignment_1
            	    {
            	    pushFollow(FOLLOW_rule__ItemModel__ItemsAssignment_1_in_rule__ItemModel__Group__1__Impl1164);
            	    rule__ItemModel__ItemsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getItemModelAccess().getItemsAssignment_1()); 

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
    // $ANTLR end "rule__ItemModel__Group__1__Impl"


    // $ANTLR start "rule__ModelItem__Group__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:561:1: rule__ModelItem__Group__0 : rule__ModelItem__Group__0__Impl rule__ModelItem__Group__1 ;
    public final void rule__ModelItem__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:565:1: ( rule__ModelItem__Group__0__Impl rule__ModelItem__Group__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:566:2: rule__ModelItem__Group__0__Impl rule__ModelItem__Group__1
            {
            pushFollow(FOLLOW_rule__ModelItem__Group__0__Impl_in_rule__ModelItem__Group__01199);
            rule__ModelItem__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group__1_in_rule__ModelItem__Group__01202);
            rule__ModelItem__Group__1();

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
    // $ANTLR end "rule__ModelItem__Group__0"


    // $ANTLR start "rule__ModelItem__Group__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:573:1: rule__ModelItem__Group__0__Impl : ( ( rule__ModelItem__Alternatives_0 ) ) ;
    public final void rule__ModelItem__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:577:1: ( ( ( rule__ModelItem__Alternatives_0 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:578:1: ( ( rule__ModelItem__Alternatives_0 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:578:1: ( ( rule__ModelItem__Alternatives_0 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:579:1: ( rule__ModelItem__Alternatives_0 )
            {
             before(grammarAccess.getModelItemAccess().getAlternatives_0()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:580:1: ( rule__ModelItem__Alternatives_0 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:580:2: rule__ModelItem__Alternatives_0
            {
            pushFollow(FOLLOW_rule__ModelItem__Alternatives_0_in_rule__ModelItem__Group__0__Impl1229);
            rule__ModelItem__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getModelItemAccess().getAlternatives_0()); 

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
    // $ANTLR end "rule__ModelItem__Group__0__Impl"


    // $ANTLR start "rule__ModelItem__Group__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:590:1: rule__ModelItem__Group__1 : rule__ModelItem__Group__1__Impl rule__ModelItem__Group__2 ;
    public final void rule__ModelItem__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:594:1: ( rule__ModelItem__Group__1__Impl rule__ModelItem__Group__2 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:595:2: rule__ModelItem__Group__1__Impl rule__ModelItem__Group__2
            {
            pushFollow(FOLLOW_rule__ModelItem__Group__1__Impl_in_rule__ModelItem__Group__11259);
            rule__ModelItem__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group__2_in_rule__ModelItem__Group__11262);
            rule__ModelItem__Group__2();

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
    // $ANTLR end "rule__ModelItem__Group__1"


    // $ANTLR start "rule__ModelItem__Group__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:602:1: rule__ModelItem__Group__1__Impl : ( ( rule__ModelItem__NameAssignment_1 ) ) ;
    public final void rule__ModelItem__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:606:1: ( ( ( rule__ModelItem__NameAssignment_1 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:607:1: ( ( rule__ModelItem__NameAssignment_1 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:607:1: ( ( rule__ModelItem__NameAssignment_1 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:608:1: ( rule__ModelItem__NameAssignment_1 )
            {
             before(grammarAccess.getModelItemAccess().getNameAssignment_1()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:609:1: ( rule__ModelItem__NameAssignment_1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:609:2: rule__ModelItem__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__ModelItem__NameAssignment_1_in_rule__ModelItem__Group__1__Impl1289);
            rule__ModelItem__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getModelItemAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__ModelItem__Group__1__Impl"


    // $ANTLR start "rule__ModelItem__Group__2"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:619:1: rule__ModelItem__Group__2 : rule__ModelItem__Group__2__Impl rule__ModelItem__Group__3 ;
    public final void rule__ModelItem__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:623:1: ( rule__ModelItem__Group__2__Impl rule__ModelItem__Group__3 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:624:2: rule__ModelItem__Group__2__Impl rule__ModelItem__Group__3
            {
            pushFollow(FOLLOW_rule__ModelItem__Group__2__Impl_in_rule__ModelItem__Group__21319);
            rule__ModelItem__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group__3_in_rule__ModelItem__Group__21322);
            rule__ModelItem__Group__3();

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
    // $ANTLR end "rule__ModelItem__Group__2"


    // $ANTLR start "rule__ModelItem__Group__2__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:631:1: rule__ModelItem__Group__2__Impl : ( ( rule__ModelItem__LabelAssignment_2 )? ) ;
    public final void rule__ModelItem__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:635:1: ( ( ( rule__ModelItem__LabelAssignment_2 )? ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:636:1: ( ( rule__ModelItem__LabelAssignment_2 )? )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:636:1: ( ( rule__ModelItem__LabelAssignment_2 )? )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:637:1: ( rule__ModelItem__LabelAssignment_2 )?
            {
             before(grammarAccess.getModelItemAccess().getLabelAssignment_2()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:638:1: ( rule__ModelItem__LabelAssignment_2 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_STRING) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:638:2: rule__ModelItem__LabelAssignment_2
                    {
                    pushFollow(FOLLOW_rule__ModelItem__LabelAssignment_2_in_rule__ModelItem__Group__2__Impl1349);
                    rule__ModelItem__LabelAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelItemAccess().getLabelAssignment_2()); 

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
    // $ANTLR end "rule__ModelItem__Group__2__Impl"


    // $ANTLR start "rule__ModelItem__Group__3"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:648:1: rule__ModelItem__Group__3 : rule__ModelItem__Group__3__Impl rule__ModelItem__Group__4 ;
    public final void rule__ModelItem__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:652:1: ( rule__ModelItem__Group__3__Impl rule__ModelItem__Group__4 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:653:2: rule__ModelItem__Group__3__Impl rule__ModelItem__Group__4
            {
            pushFollow(FOLLOW_rule__ModelItem__Group__3__Impl_in_rule__ModelItem__Group__31380);
            rule__ModelItem__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group__4_in_rule__ModelItem__Group__31383);
            rule__ModelItem__Group__4();

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
    // $ANTLR end "rule__ModelItem__Group__3"


    // $ANTLR start "rule__ModelItem__Group__3__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:660:1: rule__ModelItem__Group__3__Impl : ( ( rule__ModelItem__Group_3__0 )? ) ;
    public final void rule__ModelItem__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:664:1: ( ( ( rule__ModelItem__Group_3__0 )? ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:665:1: ( ( rule__ModelItem__Group_3__0 )? )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:665:1: ( ( rule__ModelItem__Group_3__0 )? )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:666:1: ( rule__ModelItem__Group_3__0 )?
            {
             before(grammarAccess.getModelItemAccess().getGroup_3()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:667:1: ( rule__ModelItem__Group_3__0 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==29) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:667:2: rule__ModelItem__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__ModelItem__Group_3__0_in_rule__ModelItem__Group__3__Impl1410);
                    rule__ModelItem__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelItemAccess().getGroup_3()); 

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
    // $ANTLR end "rule__ModelItem__Group__3__Impl"


    // $ANTLR start "rule__ModelItem__Group__4"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:677:1: rule__ModelItem__Group__4 : rule__ModelItem__Group__4__Impl rule__ModelItem__Group__5 ;
    public final void rule__ModelItem__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:681:1: ( rule__ModelItem__Group__4__Impl rule__ModelItem__Group__5 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:682:2: rule__ModelItem__Group__4__Impl rule__ModelItem__Group__5
            {
            pushFollow(FOLLOW_rule__ModelItem__Group__4__Impl_in_rule__ModelItem__Group__41441);
            rule__ModelItem__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group__5_in_rule__ModelItem__Group__41444);
            rule__ModelItem__Group__5();

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
    // $ANTLR end "rule__ModelItem__Group__4"


    // $ANTLR start "rule__ModelItem__Group__4__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:689:1: rule__ModelItem__Group__4__Impl : ( ( rule__ModelItem__Group_4__0 )? ) ;
    public final void rule__ModelItem__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:693:1: ( ( ( rule__ModelItem__Group_4__0 )? ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:694:1: ( ( rule__ModelItem__Group_4__0 )? )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:694:1: ( ( rule__ModelItem__Group_4__0 )? )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:695:1: ( rule__ModelItem__Group_4__0 )?
            {
             before(grammarAccess.getModelItemAccess().getGroup_4()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:696:1: ( rule__ModelItem__Group_4__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==31) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:696:2: rule__ModelItem__Group_4__0
                    {
                    pushFollow(FOLLOW_rule__ModelItem__Group_4__0_in_rule__ModelItem__Group__4__Impl1471);
                    rule__ModelItem__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelItemAccess().getGroup_4()); 

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
    // $ANTLR end "rule__ModelItem__Group__4__Impl"


    // $ANTLR start "rule__ModelItem__Group__5"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:706:1: rule__ModelItem__Group__5 : rule__ModelItem__Group__5__Impl ;
    public final void rule__ModelItem__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:710:1: ( rule__ModelItem__Group__5__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:711:2: rule__ModelItem__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__ModelItem__Group__5__Impl_in_rule__ModelItem__Group__51502);
            rule__ModelItem__Group__5__Impl();

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
    // $ANTLR end "rule__ModelItem__Group__5"


    // $ANTLR start "rule__ModelItem__Group__5__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:717:1: rule__ModelItem__Group__5__Impl : ( ( rule__ModelItem__Group_5__0 )? ) ;
    public final void rule__ModelItem__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:721:1: ( ( ( rule__ModelItem__Group_5__0 )? ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:722:1: ( ( rule__ModelItem__Group_5__0 )? )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:722:1: ( ( rule__ModelItem__Group_5__0 )? )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:723:1: ( rule__ModelItem__Group_5__0 )?
            {
             before(grammarAccess.getModelItemAccess().getGroup_5()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:724:1: ( rule__ModelItem__Group_5__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==34) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:724:2: rule__ModelItem__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__ModelItem__Group_5__0_in_rule__ModelItem__Group__5__Impl1529);
                    rule__ModelItem__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelItemAccess().getGroup_5()); 

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
    // $ANTLR end "rule__ModelItem__Group__5__Impl"


    // $ANTLR start "rule__ModelItem__Group_3__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:746:1: rule__ModelItem__Group_3__0 : rule__ModelItem__Group_3__0__Impl rule__ModelItem__Group_3__1 ;
    public final void rule__ModelItem__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:750:1: ( rule__ModelItem__Group_3__0__Impl rule__ModelItem__Group_3__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:751:2: rule__ModelItem__Group_3__0__Impl rule__ModelItem__Group_3__1
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_3__0__Impl_in_rule__ModelItem__Group_3__01572);
            rule__ModelItem__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group_3__1_in_rule__ModelItem__Group_3__01575);
            rule__ModelItem__Group_3__1();

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
    // $ANTLR end "rule__ModelItem__Group_3__0"


    // $ANTLR start "rule__ModelItem__Group_3__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:758:1: rule__ModelItem__Group_3__0__Impl : ( '<' ) ;
    public final void rule__ModelItem__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:762:1: ( ( '<' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:763:1: ( '<' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:763:1: ( '<' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:764:1: '<'
            {
             before(grammarAccess.getModelItemAccess().getLessThanSignKeyword_3_0()); 
            match(input,29,FOLLOW_29_in_rule__ModelItem__Group_3__0__Impl1603); 
             after(grammarAccess.getModelItemAccess().getLessThanSignKeyword_3_0()); 

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
    // $ANTLR end "rule__ModelItem__Group_3__0__Impl"


    // $ANTLR start "rule__ModelItem__Group_3__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:777:1: rule__ModelItem__Group_3__1 : rule__ModelItem__Group_3__1__Impl rule__ModelItem__Group_3__2 ;
    public final void rule__ModelItem__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:781:1: ( rule__ModelItem__Group_3__1__Impl rule__ModelItem__Group_3__2 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:782:2: rule__ModelItem__Group_3__1__Impl rule__ModelItem__Group_3__2
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_3__1__Impl_in_rule__ModelItem__Group_3__11634);
            rule__ModelItem__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group_3__2_in_rule__ModelItem__Group_3__11637);
            rule__ModelItem__Group_3__2();

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
    // $ANTLR end "rule__ModelItem__Group_3__1"


    // $ANTLR start "rule__ModelItem__Group_3__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:789:1: rule__ModelItem__Group_3__1__Impl : ( ( rule__ModelItem__IconAssignment_3_1 ) ) ;
    public final void rule__ModelItem__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:793:1: ( ( ( rule__ModelItem__IconAssignment_3_1 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:794:1: ( ( rule__ModelItem__IconAssignment_3_1 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:794:1: ( ( rule__ModelItem__IconAssignment_3_1 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:795:1: ( rule__ModelItem__IconAssignment_3_1 )
            {
             before(grammarAccess.getModelItemAccess().getIconAssignment_3_1()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:796:1: ( rule__ModelItem__IconAssignment_3_1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:796:2: rule__ModelItem__IconAssignment_3_1
            {
            pushFollow(FOLLOW_rule__ModelItem__IconAssignment_3_1_in_rule__ModelItem__Group_3__1__Impl1664);
            rule__ModelItem__IconAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getModelItemAccess().getIconAssignment_3_1()); 

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
    // $ANTLR end "rule__ModelItem__Group_3__1__Impl"


    // $ANTLR start "rule__ModelItem__Group_3__2"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:806:1: rule__ModelItem__Group_3__2 : rule__ModelItem__Group_3__2__Impl ;
    public final void rule__ModelItem__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:810:1: ( rule__ModelItem__Group_3__2__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:811:2: rule__ModelItem__Group_3__2__Impl
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_3__2__Impl_in_rule__ModelItem__Group_3__21694);
            rule__ModelItem__Group_3__2__Impl();

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
    // $ANTLR end "rule__ModelItem__Group_3__2"


    // $ANTLR start "rule__ModelItem__Group_3__2__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:817:1: rule__ModelItem__Group_3__2__Impl : ( '>' ) ;
    public final void rule__ModelItem__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:821:1: ( ( '>' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:822:1: ( '>' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:822:1: ( '>' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:823:1: '>'
            {
             before(grammarAccess.getModelItemAccess().getGreaterThanSignKeyword_3_2()); 
            match(input,30,FOLLOW_30_in_rule__ModelItem__Group_3__2__Impl1722); 
             after(grammarAccess.getModelItemAccess().getGreaterThanSignKeyword_3_2()); 

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
    // $ANTLR end "rule__ModelItem__Group_3__2__Impl"


    // $ANTLR start "rule__ModelItem__Group_4__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:842:1: rule__ModelItem__Group_4__0 : rule__ModelItem__Group_4__0__Impl rule__ModelItem__Group_4__1 ;
    public final void rule__ModelItem__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:846:1: ( rule__ModelItem__Group_4__0__Impl rule__ModelItem__Group_4__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:847:2: rule__ModelItem__Group_4__0__Impl rule__ModelItem__Group_4__1
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_4__0__Impl_in_rule__ModelItem__Group_4__01759);
            rule__ModelItem__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group_4__1_in_rule__ModelItem__Group_4__01762);
            rule__ModelItem__Group_4__1();

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
    // $ANTLR end "rule__ModelItem__Group_4__0"


    // $ANTLR start "rule__ModelItem__Group_4__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:854:1: rule__ModelItem__Group_4__0__Impl : ( '(' ) ;
    public final void rule__ModelItem__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:858:1: ( ( '(' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:859:1: ( '(' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:859:1: ( '(' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:860:1: '('
            {
             before(grammarAccess.getModelItemAccess().getLeftParenthesisKeyword_4_0()); 
            match(input,31,FOLLOW_31_in_rule__ModelItem__Group_4__0__Impl1790); 
             after(grammarAccess.getModelItemAccess().getLeftParenthesisKeyword_4_0()); 

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
    // $ANTLR end "rule__ModelItem__Group_4__0__Impl"


    // $ANTLR start "rule__ModelItem__Group_4__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:873:1: rule__ModelItem__Group_4__1 : rule__ModelItem__Group_4__1__Impl rule__ModelItem__Group_4__2 ;
    public final void rule__ModelItem__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:877:1: ( rule__ModelItem__Group_4__1__Impl rule__ModelItem__Group_4__2 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:878:2: rule__ModelItem__Group_4__1__Impl rule__ModelItem__Group_4__2
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_4__1__Impl_in_rule__ModelItem__Group_4__11821);
            rule__ModelItem__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group_4__2_in_rule__ModelItem__Group_4__11824);
            rule__ModelItem__Group_4__2();

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
    // $ANTLR end "rule__ModelItem__Group_4__1"


    // $ANTLR start "rule__ModelItem__Group_4__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:885:1: rule__ModelItem__Group_4__1__Impl : ( ( rule__ModelItem__GroupsAssignment_4_1 ) ) ;
    public final void rule__ModelItem__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:889:1: ( ( ( rule__ModelItem__GroupsAssignment_4_1 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:890:1: ( ( rule__ModelItem__GroupsAssignment_4_1 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:890:1: ( ( rule__ModelItem__GroupsAssignment_4_1 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:891:1: ( rule__ModelItem__GroupsAssignment_4_1 )
            {
             before(grammarAccess.getModelItemAccess().getGroupsAssignment_4_1()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:892:1: ( rule__ModelItem__GroupsAssignment_4_1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:892:2: rule__ModelItem__GroupsAssignment_4_1
            {
            pushFollow(FOLLOW_rule__ModelItem__GroupsAssignment_4_1_in_rule__ModelItem__Group_4__1__Impl1851);
            rule__ModelItem__GroupsAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getModelItemAccess().getGroupsAssignment_4_1()); 

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
    // $ANTLR end "rule__ModelItem__Group_4__1__Impl"


    // $ANTLR start "rule__ModelItem__Group_4__2"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:902:1: rule__ModelItem__Group_4__2 : rule__ModelItem__Group_4__2__Impl rule__ModelItem__Group_4__3 ;
    public final void rule__ModelItem__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:906:1: ( rule__ModelItem__Group_4__2__Impl rule__ModelItem__Group_4__3 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:907:2: rule__ModelItem__Group_4__2__Impl rule__ModelItem__Group_4__3
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_4__2__Impl_in_rule__ModelItem__Group_4__21881);
            rule__ModelItem__Group_4__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group_4__3_in_rule__ModelItem__Group_4__21884);
            rule__ModelItem__Group_4__3();

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
    // $ANTLR end "rule__ModelItem__Group_4__2"


    // $ANTLR start "rule__ModelItem__Group_4__2__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:914:1: rule__ModelItem__Group_4__2__Impl : ( ( rule__ModelItem__Group_4_2__0 )* ) ;
    public final void rule__ModelItem__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:918:1: ( ( ( rule__ModelItem__Group_4_2__0 )* ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:919:1: ( ( rule__ModelItem__Group_4_2__0 )* )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:919:1: ( ( rule__ModelItem__Group_4_2__0 )* )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:920:1: ( rule__ModelItem__Group_4_2__0 )*
            {
             before(grammarAccess.getModelItemAccess().getGroup_4_2()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:921:1: ( rule__ModelItem__Group_4_2__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==33) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:921:2: rule__ModelItem__Group_4_2__0
            	    {
            	    pushFollow(FOLLOW_rule__ModelItem__Group_4_2__0_in_rule__ModelItem__Group_4__2__Impl1911);
            	    rule__ModelItem__Group_4_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getModelItemAccess().getGroup_4_2()); 

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
    // $ANTLR end "rule__ModelItem__Group_4__2__Impl"


    // $ANTLR start "rule__ModelItem__Group_4__3"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:931:1: rule__ModelItem__Group_4__3 : rule__ModelItem__Group_4__3__Impl ;
    public final void rule__ModelItem__Group_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:935:1: ( rule__ModelItem__Group_4__3__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:936:2: rule__ModelItem__Group_4__3__Impl
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_4__3__Impl_in_rule__ModelItem__Group_4__31942);
            rule__ModelItem__Group_4__3__Impl();

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
    // $ANTLR end "rule__ModelItem__Group_4__3"


    // $ANTLR start "rule__ModelItem__Group_4__3__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:942:1: rule__ModelItem__Group_4__3__Impl : ( ')' ) ;
    public final void rule__ModelItem__Group_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:946:1: ( ( ')' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:947:1: ( ')' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:947:1: ( ')' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:948:1: ')'
            {
             before(grammarAccess.getModelItemAccess().getRightParenthesisKeyword_4_3()); 
            match(input,32,FOLLOW_32_in_rule__ModelItem__Group_4__3__Impl1970); 
             after(grammarAccess.getModelItemAccess().getRightParenthesisKeyword_4_3()); 

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
    // $ANTLR end "rule__ModelItem__Group_4__3__Impl"


    // $ANTLR start "rule__ModelItem__Group_4_2__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:969:1: rule__ModelItem__Group_4_2__0 : rule__ModelItem__Group_4_2__0__Impl rule__ModelItem__Group_4_2__1 ;
    public final void rule__ModelItem__Group_4_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:973:1: ( rule__ModelItem__Group_4_2__0__Impl rule__ModelItem__Group_4_2__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:974:2: rule__ModelItem__Group_4_2__0__Impl rule__ModelItem__Group_4_2__1
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_4_2__0__Impl_in_rule__ModelItem__Group_4_2__02009);
            rule__ModelItem__Group_4_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group_4_2__1_in_rule__ModelItem__Group_4_2__02012);
            rule__ModelItem__Group_4_2__1();

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
    // $ANTLR end "rule__ModelItem__Group_4_2__0"


    // $ANTLR start "rule__ModelItem__Group_4_2__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:981:1: rule__ModelItem__Group_4_2__0__Impl : ( ',' ) ;
    public final void rule__ModelItem__Group_4_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:985:1: ( ( ',' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:986:1: ( ',' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:986:1: ( ',' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:987:1: ','
            {
             before(grammarAccess.getModelItemAccess().getCommaKeyword_4_2_0()); 
            match(input,33,FOLLOW_33_in_rule__ModelItem__Group_4_2__0__Impl2040); 
             after(grammarAccess.getModelItemAccess().getCommaKeyword_4_2_0()); 

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
    // $ANTLR end "rule__ModelItem__Group_4_2__0__Impl"


    // $ANTLR start "rule__ModelItem__Group_4_2__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1000:1: rule__ModelItem__Group_4_2__1 : rule__ModelItem__Group_4_2__1__Impl ;
    public final void rule__ModelItem__Group_4_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1004:1: ( rule__ModelItem__Group_4_2__1__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1005:2: rule__ModelItem__Group_4_2__1__Impl
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_4_2__1__Impl_in_rule__ModelItem__Group_4_2__12071);
            rule__ModelItem__Group_4_2__1__Impl();

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
    // $ANTLR end "rule__ModelItem__Group_4_2__1"


    // $ANTLR start "rule__ModelItem__Group_4_2__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1011:1: rule__ModelItem__Group_4_2__1__Impl : ( ( rule__ModelItem__GroupsAssignment_4_2_1 ) ) ;
    public final void rule__ModelItem__Group_4_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1015:1: ( ( ( rule__ModelItem__GroupsAssignment_4_2_1 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1016:1: ( ( rule__ModelItem__GroupsAssignment_4_2_1 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1016:1: ( ( rule__ModelItem__GroupsAssignment_4_2_1 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1017:1: ( rule__ModelItem__GroupsAssignment_4_2_1 )
            {
             before(grammarAccess.getModelItemAccess().getGroupsAssignment_4_2_1()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1018:1: ( rule__ModelItem__GroupsAssignment_4_2_1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1018:2: rule__ModelItem__GroupsAssignment_4_2_1
            {
            pushFollow(FOLLOW_rule__ModelItem__GroupsAssignment_4_2_1_in_rule__ModelItem__Group_4_2__1__Impl2098);
            rule__ModelItem__GroupsAssignment_4_2_1();

            state._fsp--;


            }

             after(grammarAccess.getModelItemAccess().getGroupsAssignment_4_2_1()); 

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
    // $ANTLR end "rule__ModelItem__Group_4_2__1__Impl"


    // $ANTLR start "rule__ModelItem__Group_5__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1032:1: rule__ModelItem__Group_5__0 : rule__ModelItem__Group_5__0__Impl rule__ModelItem__Group_5__1 ;
    public final void rule__ModelItem__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1036:1: ( rule__ModelItem__Group_5__0__Impl rule__ModelItem__Group_5__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1037:2: rule__ModelItem__Group_5__0__Impl rule__ModelItem__Group_5__1
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_5__0__Impl_in_rule__ModelItem__Group_5__02132);
            rule__ModelItem__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group_5__1_in_rule__ModelItem__Group_5__02135);
            rule__ModelItem__Group_5__1();

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
    // $ANTLR end "rule__ModelItem__Group_5__0"


    // $ANTLR start "rule__ModelItem__Group_5__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1044:1: rule__ModelItem__Group_5__0__Impl : ( '{' ) ;
    public final void rule__ModelItem__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1048:1: ( ( '{' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1049:1: ( '{' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1049:1: ( '{' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1050:1: '{'
            {
             before(grammarAccess.getModelItemAccess().getLeftCurlyBracketKeyword_5_0()); 
            match(input,34,FOLLOW_34_in_rule__ModelItem__Group_5__0__Impl2163); 
             after(grammarAccess.getModelItemAccess().getLeftCurlyBracketKeyword_5_0()); 

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
    // $ANTLR end "rule__ModelItem__Group_5__0__Impl"


    // $ANTLR start "rule__ModelItem__Group_5__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1063:1: rule__ModelItem__Group_5__1 : rule__ModelItem__Group_5__1__Impl rule__ModelItem__Group_5__2 ;
    public final void rule__ModelItem__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1067:1: ( rule__ModelItem__Group_5__1__Impl rule__ModelItem__Group_5__2 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1068:2: rule__ModelItem__Group_5__1__Impl rule__ModelItem__Group_5__2
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_5__1__Impl_in_rule__ModelItem__Group_5__12194);
            rule__ModelItem__Group_5__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group_5__2_in_rule__ModelItem__Group_5__12197);
            rule__ModelItem__Group_5__2();

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
    // $ANTLR end "rule__ModelItem__Group_5__1"


    // $ANTLR start "rule__ModelItem__Group_5__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1075:1: rule__ModelItem__Group_5__1__Impl : ( ( rule__ModelItem__BindingsAssignment_5_1 ) ) ;
    public final void rule__ModelItem__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1079:1: ( ( ( rule__ModelItem__BindingsAssignment_5_1 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1080:1: ( ( rule__ModelItem__BindingsAssignment_5_1 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1080:1: ( ( rule__ModelItem__BindingsAssignment_5_1 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1081:1: ( rule__ModelItem__BindingsAssignment_5_1 )
            {
             before(grammarAccess.getModelItemAccess().getBindingsAssignment_5_1()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1082:1: ( rule__ModelItem__BindingsAssignment_5_1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1082:2: rule__ModelItem__BindingsAssignment_5_1
            {
            pushFollow(FOLLOW_rule__ModelItem__BindingsAssignment_5_1_in_rule__ModelItem__Group_5__1__Impl2224);
            rule__ModelItem__BindingsAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getModelItemAccess().getBindingsAssignment_5_1()); 

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
    // $ANTLR end "rule__ModelItem__Group_5__1__Impl"


    // $ANTLR start "rule__ModelItem__Group_5__2"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1092:1: rule__ModelItem__Group_5__2 : rule__ModelItem__Group_5__2__Impl rule__ModelItem__Group_5__3 ;
    public final void rule__ModelItem__Group_5__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1096:1: ( rule__ModelItem__Group_5__2__Impl rule__ModelItem__Group_5__3 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1097:2: rule__ModelItem__Group_5__2__Impl rule__ModelItem__Group_5__3
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_5__2__Impl_in_rule__ModelItem__Group_5__22254);
            rule__ModelItem__Group_5__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group_5__3_in_rule__ModelItem__Group_5__22257);
            rule__ModelItem__Group_5__3();

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
    // $ANTLR end "rule__ModelItem__Group_5__2"


    // $ANTLR start "rule__ModelItem__Group_5__2__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1104:1: rule__ModelItem__Group_5__2__Impl : ( ( rule__ModelItem__Group_5_2__0 )* ) ;
    public final void rule__ModelItem__Group_5__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1108:1: ( ( ( rule__ModelItem__Group_5_2__0 )* ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1109:1: ( ( rule__ModelItem__Group_5_2__0 )* )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1109:1: ( ( rule__ModelItem__Group_5_2__0 )* )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1110:1: ( rule__ModelItem__Group_5_2__0 )*
            {
             before(grammarAccess.getModelItemAccess().getGroup_5_2()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1111:1: ( rule__ModelItem__Group_5_2__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==33) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1111:2: rule__ModelItem__Group_5_2__0
            	    {
            	    pushFollow(FOLLOW_rule__ModelItem__Group_5_2__0_in_rule__ModelItem__Group_5__2__Impl2284);
            	    rule__ModelItem__Group_5_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getModelItemAccess().getGroup_5_2()); 

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
    // $ANTLR end "rule__ModelItem__Group_5__2__Impl"


    // $ANTLR start "rule__ModelItem__Group_5__3"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1121:1: rule__ModelItem__Group_5__3 : rule__ModelItem__Group_5__3__Impl ;
    public final void rule__ModelItem__Group_5__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1125:1: ( rule__ModelItem__Group_5__3__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1126:2: rule__ModelItem__Group_5__3__Impl
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_5__3__Impl_in_rule__ModelItem__Group_5__32315);
            rule__ModelItem__Group_5__3__Impl();

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
    // $ANTLR end "rule__ModelItem__Group_5__3"


    // $ANTLR start "rule__ModelItem__Group_5__3__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1132:1: rule__ModelItem__Group_5__3__Impl : ( '}' ) ;
    public final void rule__ModelItem__Group_5__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1136:1: ( ( '}' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1137:1: ( '}' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1137:1: ( '}' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1138:1: '}'
            {
             before(grammarAccess.getModelItemAccess().getRightCurlyBracketKeyword_5_3()); 
            match(input,35,FOLLOW_35_in_rule__ModelItem__Group_5__3__Impl2343); 
             after(grammarAccess.getModelItemAccess().getRightCurlyBracketKeyword_5_3()); 

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
    // $ANTLR end "rule__ModelItem__Group_5__3__Impl"


    // $ANTLR start "rule__ModelItem__Group_5_2__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1159:1: rule__ModelItem__Group_5_2__0 : rule__ModelItem__Group_5_2__0__Impl rule__ModelItem__Group_5_2__1 ;
    public final void rule__ModelItem__Group_5_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1163:1: ( rule__ModelItem__Group_5_2__0__Impl rule__ModelItem__Group_5_2__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1164:2: rule__ModelItem__Group_5_2__0__Impl rule__ModelItem__Group_5_2__1
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_5_2__0__Impl_in_rule__ModelItem__Group_5_2__02382);
            rule__ModelItem__Group_5_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelItem__Group_5_2__1_in_rule__ModelItem__Group_5_2__02385);
            rule__ModelItem__Group_5_2__1();

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
    // $ANTLR end "rule__ModelItem__Group_5_2__0"


    // $ANTLR start "rule__ModelItem__Group_5_2__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1171:1: rule__ModelItem__Group_5_2__0__Impl : ( ',' ) ;
    public final void rule__ModelItem__Group_5_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1175:1: ( ( ',' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1176:1: ( ',' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1176:1: ( ',' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1177:1: ','
            {
             before(grammarAccess.getModelItemAccess().getCommaKeyword_5_2_0()); 
            match(input,33,FOLLOW_33_in_rule__ModelItem__Group_5_2__0__Impl2413); 
             after(grammarAccess.getModelItemAccess().getCommaKeyword_5_2_0()); 

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
    // $ANTLR end "rule__ModelItem__Group_5_2__0__Impl"


    // $ANTLR start "rule__ModelItem__Group_5_2__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1190:1: rule__ModelItem__Group_5_2__1 : rule__ModelItem__Group_5_2__1__Impl ;
    public final void rule__ModelItem__Group_5_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1194:1: ( rule__ModelItem__Group_5_2__1__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1195:2: rule__ModelItem__Group_5_2__1__Impl
            {
            pushFollow(FOLLOW_rule__ModelItem__Group_5_2__1__Impl_in_rule__ModelItem__Group_5_2__12444);
            rule__ModelItem__Group_5_2__1__Impl();

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
    // $ANTLR end "rule__ModelItem__Group_5_2__1"


    // $ANTLR start "rule__ModelItem__Group_5_2__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1201:1: rule__ModelItem__Group_5_2__1__Impl : ( ( rule__ModelItem__BindingsAssignment_5_2_1 ) ) ;
    public final void rule__ModelItem__Group_5_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1205:1: ( ( ( rule__ModelItem__BindingsAssignment_5_2_1 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1206:1: ( ( rule__ModelItem__BindingsAssignment_5_2_1 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1206:1: ( ( rule__ModelItem__BindingsAssignment_5_2_1 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1207:1: ( rule__ModelItem__BindingsAssignment_5_2_1 )
            {
             before(grammarAccess.getModelItemAccess().getBindingsAssignment_5_2_1()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1208:1: ( rule__ModelItem__BindingsAssignment_5_2_1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1208:2: rule__ModelItem__BindingsAssignment_5_2_1
            {
            pushFollow(FOLLOW_rule__ModelItem__BindingsAssignment_5_2_1_in_rule__ModelItem__Group_5_2__1__Impl2471);
            rule__ModelItem__BindingsAssignment_5_2_1();

            state._fsp--;


            }

             after(grammarAccess.getModelItemAccess().getBindingsAssignment_5_2_1()); 

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
    // $ANTLR end "rule__ModelItem__Group_5_2__1__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1222:1: rule__ModelGroupItem__Group__0 : rule__ModelGroupItem__Group__0__Impl rule__ModelGroupItem__Group__1 ;
    public final void rule__ModelGroupItem__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1226:1: ( rule__ModelGroupItem__Group__0__Impl rule__ModelGroupItem__Group__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1227:2: rule__ModelGroupItem__Group__0__Impl rule__ModelGroupItem__Group__1
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group__0__Impl_in_rule__ModelGroupItem__Group__02505);
            rule__ModelGroupItem__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelGroupItem__Group__1_in_rule__ModelGroupItem__Group__02508);
            rule__ModelGroupItem__Group__1();

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
    // $ANTLR end "rule__ModelGroupItem__Group__0"


    // $ANTLR start "rule__ModelGroupItem__Group__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1234:1: rule__ModelGroupItem__Group__0__Impl : ( () ) ;
    public final void rule__ModelGroupItem__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1238:1: ( ( () ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1239:1: ( () )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1239:1: ( () )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1240:1: ()
            {
             before(grammarAccess.getModelGroupItemAccess().getModelGroupItemAction_0()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1241:1: ()
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1243:1: 
            {
            }

             after(grammarAccess.getModelGroupItemAccess().getModelGroupItemAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ModelGroupItem__Group__0__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1253:1: rule__ModelGroupItem__Group__1 : rule__ModelGroupItem__Group__1__Impl rule__ModelGroupItem__Group__2 ;
    public final void rule__ModelGroupItem__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1257:1: ( rule__ModelGroupItem__Group__1__Impl rule__ModelGroupItem__Group__2 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1258:2: rule__ModelGroupItem__Group__1__Impl rule__ModelGroupItem__Group__2
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group__1__Impl_in_rule__ModelGroupItem__Group__12566);
            rule__ModelGroupItem__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelGroupItem__Group__2_in_rule__ModelGroupItem__Group__12569);
            rule__ModelGroupItem__Group__2();

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
    // $ANTLR end "rule__ModelGroupItem__Group__1"


    // $ANTLR start "rule__ModelGroupItem__Group__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1265:1: rule__ModelGroupItem__Group__1__Impl : ( 'Group' ) ;
    public final void rule__ModelGroupItem__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1269:1: ( ( 'Group' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1270:1: ( 'Group' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1270:1: ( 'Group' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1271:1: 'Group'
            {
             before(grammarAccess.getModelGroupItemAccess().getGroupKeyword_1()); 
            match(input,36,FOLLOW_36_in_rule__ModelGroupItem__Group__1__Impl2597); 
             after(grammarAccess.getModelGroupItemAccess().getGroupKeyword_1()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group__1__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group__2"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1284:1: rule__ModelGroupItem__Group__2 : rule__ModelGroupItem__Group__2__Impl ;
    public final void rule__ModelGroupItem__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1288:1: ( rule__ModelGroupItem__Group__2__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1289:2: rule__ModelGroupItem__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group__2__Impl_in_rule__ModelGroupItem__Group__22628);
            rule__ModelGroupItem__Group__2__Impl();

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
    // $ANTLR end "rule__ModelGroupItem__Group__2"


    // $ANTLR start "rule__ModelGroupItem__Group__2__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1295:1: rule__ModelGroupItem__Group__2__Impl : ( ( rule__ModelGroupItem__Group_2__0 )? ) ;
    public final void rule__ModelGroupItem__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1299:1: ( ( ( rule__ModelGroupItem__Group_2__0 )? ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1300:1: ( ( rule__ModelGroupItem__Group_2__0 )? )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1300:1: ( ( rule__ModelGroupItem__Group_2__0 )? )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1301:1: ( rule__ModelGroupItem__Group_2__0 )?
            {
             before(grammarAccess.getModelGroupItemAccess().getGroup_2()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1302:1: ( rule__ModelGroupItem__Group_2__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==37) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1302:2: rule__ModelGroupItem__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__ModelGroupItem__Group_2__0_in_rule__ModelGroupItem__Group__2__Impl2655);
                    rule__ModelGroupItem__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelGroupItemAccess().getGroup_2()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group__2__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1318:1: rule__ModelGroupItem__Group_2__0 : rule__ModelGroupItem__Group_2__0__Impl rule__ModelGroupItem__Group_2__1 ;
    public final void rule__ModelGroupItem__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1322:1: ( rule__ModelGroupItem__Group_2__0__Impl rule__ModelGroupItem__Group_2__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1323:2: rule__ModelGroupItem__Group_2__0__Impl rule__ModelGroupItem__Group_2__1
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2__0__Impl_in_rule__ModelGroupItem__Group_2__02692);
            rule__ModelGroupItem__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2__1_in_rule__ModelGroupItem__Group_2__02695);
            rule__ModelGroupItem__Group_2__1();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2__0"


    // $ANTLR start "rule__ModelGroupItem__Group_2__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1330:1: rule__ModelGroupItem__Group_2__0__Impl : ( ':' ) ;
    public final void rule__ModelGroupItem__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1334:1: ( ( ':' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1335:1: ( ':' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1335:1: ( ':' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1336:1: ':'
            {
             before(grammarAccess.getModelGroupItemAccess().getColonKeyword_2_0()); 
            match(input,37,FOLLOW_37_in_rule__ModelGroupItem__Group_2__0__Impl2723); 
             after(grammarAccess.getModelGroupItemAccess().getColonKeyword_2_0()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2__0__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1349:1: rule__ModelGroupItem__Group_2__1 : rule__ModelGroupItem__Group_2__1__Impl rule__ModelGroupItem__Group_2__2 ;
    public final void rule__ModelGroupItem__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1353:1: ( rule__ModelGroupItem__Group_2__1__Impl rule__ModelGroupItem__Group_2__2 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1354:2: rule__ModelGroupItem__Group_2__1__Impl rule__ModelGroupItem__Group_2__2
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2__1__Impl_in_rule__ModelGroupItem__Group_2__12754);
            rule__ModelGroupItem__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2__2_in_rule__ModelGroupItem__Group_2__12757);
            rule__ModelGroupItem__Group_2__2();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2__1"


    // $ANTLR start "rule__ModelGroupItem__Group_2__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1361:1: rule__ModelGroupItem__Group_2__1__Impl : ( ( rule__ModelGroupItem__TypeAssignment_2_1 ) ) ;
    public final void rule__ModelGroupItem__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1365:1: ( ( ( rule__ModelGroupItem__TypeAssignment_2_1 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1366:1: ( ( rule__ModelGroupItem__TypeAssignment_2_1 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1366:1: ( ( rule__ModelGroupItem__TypeAssignment_2_1 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1367:1: ( rule__ModelGroupItem__TypeAssignment_2_1 )
            {
             before(grammarAccess.getModelGroupItemAccess().getTypeAssignment_2_1()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1368:1: ( rule__ModelGroupItem__TypeAssignment_2_1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1368:2: rule__ModelGroupItem__TypeAssignment_2_1
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__TypeAssignment_2_1_in_rule__ModelGroupItem__Group_2__1__Impl2784);
            rule__ModelGroupItem__TypeAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getModelGroupItemAccess().getTypeAssignment_2_1()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2__1__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2__2"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1378:1: rule__ModelGroupItem__Group_2__2 : rule__ModelGroupItem__Group_2__2__Impl ;
    public final void rule__ModelGroupItem__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1382:1: ( rule__ModelGroupItem__Group_2__2__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1383:2: rule__ModelGroupItem__Group_2__2__Impl
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2__2__Impl_in_rule__ModelGroupItem__Group_2__22814);
            rule__ModelGroupItem__Group_2__2__Impl();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2__2"


    // $ANTLR start "rule__ModelGroupItem__Group_2__2__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1389:1: rule__ModelGroupItem__Group_2__2__Impl : ( ( rule__ModelGroupItem__Group_2_2__0 )? ) ;
    public final void rule__ModelGroupItem__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1393:1: ( ( ( rule__ModelGroupItem__Group_2_2__0 )? ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1394:1: ( ( rule__ModelGroupItem__Group_2_2__0 )? )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1394:1: ( ( rule__ModelGroupItem__Group_2_2__0 )? )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1395:1: ( rule__ModelGroupItem__Group_2_2__0 )?
            {
             before(grammarAccess.getModelGroupItemAccess().getGroup_2_2()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1396:1: ( rule__ModelGroupItem__Group_2_2__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==37) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1396:2: rule__ModelGroupItem__Group_2_2__0
                    {
                    pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2__0_in_rule__ModelGroupItem__Group_2__2__Impl2841);
                    rule__ModelGroupItem__Group_2_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelGroupItemAccess().getGroup_2_2()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2__2__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1412:1: rule__ModelGroupItem__Group_2_2__0 : rule__ModelGroupItem__Group_2_2__0__Impl rule__ModelGroupItem__Group_2_2__1 ;
    public final void rule__ModelGroupItem__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1416:1: ( rule__ModelGroupItem__Group_2_2__0__Impl rule__ModelGroupItem__Group_2_2__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1417:2: rule__ModelGroupItem__Group_2_2__0__Impl rule__ModelGroupItem__Group_2_2__1
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2__0__Impl_in_rule__ModelGroupItem__Group_2_2__02878);
            rule__ModelGroupItem__Group_2_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2__1_in_rule__ModelGroupItem__Group_2_2__02881);
            rule__ModelGroupItem__Group_2_2__1();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2__0"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1424:1: rule__ModelGroupItem__Group_2_2__0__Impl : ( ':' ) ;
    public final void rule__ModelGroupItem__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1428:1: ( ( ':' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1429:1: ( ':' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1429:1: ( ':' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1430:1: ':'
            {
             before(grammarAccess.getModelGroupItemAccess().getColonKeyword_2_2_0()); 
            match(input,37,FOLLOW_37_in_rule__ModelGroupItem__Group_2_2__0__Impl2909); 
             after(grammarAccess.getModelGroupItemAccess().getColonKeyword_2_2_0()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2__0__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1443:1: rule__ModelGroupItem__Group_2_2__1 : rule__ModelGroupItem__Group_2_2__1__Impl rule__ModelGroupItem__Group_2_2__2 ;
    public final void rule__ModelGroupItem__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1447:1: ( rule__ModelGroupItem__Group_2_2__1__Impl rule__ModelGroupItem__Group_2_2__2 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1448:2: rule__ModelGroupItem__Group_2_2__1__Impl rule__ModelGroupItem__Group_2_2__2
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2__1__Impl_in_rule__ModelGroupItem__Group_2_2__12940);
            rule__ModelGroupItem__Group_2_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2__2_in_rule__ModelGroupItem__Group_2_2__12943);
            rule__ModelGroupItem__Group_2_2__2();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2__1"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1455:1: rule__ModelGroupItem__Group_2_2__1__Impl : ( ( rule__ModelGroupItem__FunctionAssignment_2_2_1 ) ) ;
    public final void rule__ModelGroupItem__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1459:1: ( ( ( rule__ModelGroupItem__FunctionAssignment_2_2_1 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1460:1: ( ( rule__ModelGroupItem__FunctionAssignment_2_2_1 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1460:1: ( ( rule__ModelGroupItem__FunctionAssignment_2_2_1 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1461:1: ( rule__ModelGroupItem__FunctionAssignment_2_2_1 )
            {
             before(grammarAccess.getModelGroupItemAccess().getFunctionAssignment_2_2_1()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1462:1: ( rule__ModelGroupItem__FunctionAssignment_2_2_1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1462:2: rule__ModelGroupItem__FunctionAssignment_2_2_1
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__FunctionAssignment_2_2_1_in_rule__ModelGroupItem__Group_2_2__1__Impl2970);
            rule__ModelGroupItem__FunctionAssignment_2_2_1();

            state._fsp--;


            }

             after(grammarAccess.getModelGroupItemAccess().getFunctionAssignment_2_2_1()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2__1__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2__2"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1472:1: rule__ModelGroupItem__Group_2_2__2 : rule__ModelGroupItem__Group_2_2__2__Impl ;
    public final void rule__ModelGroupItem__Group_2_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1476:1: ( rule__ModelGroupItem__Group_2_2__2__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1477:2: rule__ModelGroupItem__Group_2_2__2__Impl
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2__2__Impl_in_rule__ModelGroupItem__Group_2_2__23000);
            rule__ModelGroupItem__Group_2_2__2__Impl();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2__2"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2__2__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1483:1: rule__ModelGroupItem__Group_2_2__2__Impl : ( ( rule__ModelGroupItem__Group_2_2_2__0 )? ) ;
    public final void rule__ModelGroupItem__Group_2_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1487:1: ( ( ( rule__ModelGroupItem__Group_2_2_2__0 )? ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1488:1: ( ( rule__ModelGroupItem__Group_2_2_2__0 )? )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1488:1: ( ( rule__ModelGroupItem__Group_2_2_2__0 )? )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1489:1: ( rule__ModelGroupItem__Group_2_2_2__0 )?
            {
             before(grammarAccess.getModelGroupItemAccess().getGroup_2_2_2()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1490:1: ( rule__ModelGroupItem__Group_2_2_2__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==31) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1490:2: rule__ModelGroupItem__Group_2_2_2__0
                    {
                    pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2__0_in_rule__ModelGroupItem__Group_2_2__2__Impl3027);
                    rule__ModelGroupItem__Group_2_2_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelGroupItemAccess().getGroup_2_2_2()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2__2__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1506:1: rule__ModelGroupItem__Group_2_2_2__0 : rule__ModelGroupItem__Group_2_2_2__0__Impl rule__ModelGroupItem__Group_2_2_2__1 ;
    public final void rule__ModelGroupItem__Group_2_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1510:1: ( rule__ModelGroupItem__Group_2_2_2__0__Impl rule__ModelGroupItem__Group_2_2_2__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1511:2: rule__ModelGroupItem__Group_2_2_2__0__Impl rule__ModelGroupItem__Group_2_2_2__1
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2__0__Impl_in_rule__ModelGroupItem__Group_2_2_2__03064);
            rule__ModelGroupItem__Group_2_2_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2__1_in_rule__ModelGroupItem__Group_2_2_2__03067);
            rule__ModelGroupItem__Group_2_2_2__1();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2__0"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1518:1: rule__ModelGroupItem__Group_2_2_2__0__Impl : ( '(' ) ;
    public final void rule__ModelGroupItem__Group_2_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1522:1: ( ( '(' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1523:1: ( '(' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1523:1: ( '(' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1524:1: '('
            {
             before(grammarAccess.getModelGroupItemAccess().getLeftParenthesisKeyword_2_2_2_0()); 
            match(input,31,FOLLOW_31_in_rule__ModelGroupItem__Group_2_2_2__0__Impl3095); 
             after(grammarAccess.getModelGroupItemAccess().getLeftParenthesisKeyword_2_2_2_0()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2__0__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1537:1: rule__ModelGroupItem__Group_2_2_2__1 : rule__ModelGroupItem__Group_2_2_2__1__Impl rule__ModelGroupItem__Group_2_2_2__2 ;
    public final void rule__ModelGroupItem__Group_2_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1541:1: ( rule__ModelGroupItem__Group_2_2_2__1__Impl rule__ModelGroupItem__Group_2_2_2__2 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1542:2: rule__ModelGroupItem__Group_2_2_2__1__Impl rule__ModelGroupItem__Group_2_2_2__2
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2__1__Impl_in_rule__ModelGroupItem__Group_2_2_2__13126);
            rule__ModelGroupItem__Group_2_2_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2__2_in_rule__ModelGroupItem__Group_2_2_2__13129);
            rule__ModelGroupItem__Group_2_2_2__2();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2__1"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1549:1: rule__ModelGroupItem__Group_2_2_2__1__Impl : ( ( rule__ModelGroupItem__ArgsAssignment_2_2_2_1 ) ) ;
    public final void rule__ModelGroupItem__Group_2_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1553:1: ( ( ( rule__ModelGroupItem__ArgsAssignment_2_2_2_1 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1554:1: ( ( rule__ModelGroupItem__ArgsAssignment_2_2_2_1 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1554:1: ( ( rule__ModelGroupItem__ArgsAssignment_2_2_2_1 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1555:1: ( rule__ModelGroupItem__ArgsAssignment_2_2_2_1 )
            {
             before(grammarAccess.getModelGroupItemAccess().getArgsAssignment_2_2_2_1()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1556:1: ( rule__ModelGroupItem__ArgsAssignment_2_2_2_1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1556:2: rule__ModelGroupItem__ArgsAssignment_2_2_2_1
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__ArgsAssignment_2_2_2_1_in_rule__ModelGroupItem__Group_2_2_2__1__Impl3156);
            rule__ModelGroupItem__ArgsAssignment_2_2_2_1();

            state._fsp--;


            }

             after(grammarAccess.getModelGroupItemAccess().getArgsAssignment_2_2_2_1()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2__1__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2__2"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1566:1: rule__ModelGroupItem__Group_2_2_2__2 : rule__ModelGroupItem__Group_2_2_2__2__Impl rule__ModelGroupItem__Group_2_2_2__3 ;
    public final void rule__ModelGroupItem__Group_2_2_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1570:1: ( rule__ModelGroupItem__Group_2_2_2__2__Impl rule__ModelGroupItem__Group_2_2_2__3 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1571:2: rule__ModelGroupItem__Group_2_2_2__2__Impl rule__ModelGroupItem__Group_2_2_2__3
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2__2__Impl_in_rule__ModelGroupItem__Group_2_2_2__23186);
            rule__ModelGroupItem__Group_2_2_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2__3_in_rule__ModelGroupItem__Group_2_2_2__23189);
            rule__ModelGroupItem__Group_2_2_2__3();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2__2"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2__2__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1578:1: rule__ModelGroupItem__Group_2_2_2__2__Impl : ( ( rule__ModelGroupItem__Group_2_2_2_2__0 )* ) ;
    public final void rule__ModelGroupItem__Group_2_2_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1582:1: ( ( ( rule__ModelGroupItem__Group_2_2_2_2__0 )* ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1583:1: ( ( rule__ModelGroupItem__Group_2_2_2_2__0 )* )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1583:1: ( ( rule__ModelGroupItem__Group_2_2_2_2__0 )* )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1584:1: ( rule__ModelGroupItem__Group_2_2_2_2__0 )*
            {
             before(grammarAccess.getModelGroupItemAccess().getGroup_2_2_2_2()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1585:1: ( rule__ModelGroupItem__Group_2_2_2_2__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==33) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1585:2: rule__ModelGroupItem__Group_2_2_2_2__0
            	    {
            	    pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2_2__0_in_rule__ModelGroupItem__Group_2_2_2__2__Impl3216);
            	    rule__ModelGroupItem__Group_2_2_2_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getModelGroupItemAccess().getGroup_2_2_2_2()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2__2__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2__3"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1595:1: rule__ModelGroupItem__Group_2_2_2__3 : rule__ModelGroupItem__Group_2_2_2__3__Impl ;
    public final void rule__ModelGroupItem__Group_2_2_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1599:1: ( rule__ModelGroupItem__Group_2_2_2__3__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1600:2: rule__ModelGroupItem__Group_2_2_2__3__Impl
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2__3__Impl_in_rule__ModelGroupItem__Group_2_2_2__33247);
            rule__ModelGroupItem__Group_2_2_2__3__Impl();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2__3"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2__3__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1606:1: rule__ModelGroupItem__Group_2_2_2__3__Impl : ( ')' ) ;
    public final void rule__ModelGroupItem__Group_2_2_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1610:1: ( ( ')' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1611:1: ( ')' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1611:1: ( ')' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1612:1: ')'
            {
             before(grammarAccess.getModelGroupItemAccess().getRightParenthesisKeyword_2_2_2_3()); 
            match(input,32,FOLLOW_32_in_rule__ModelGroupItem__Group_2_2_2__3__Impl3275); 
             after(grammarAccess.getModelGroupItemAccess().getRightParenthesisKeyword_2_2_2_3()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2__3__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2_2__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1633:1: rule__ModelGroupItem__Group_2_2_2_2__0 : rule__ModelGroupItem__Group_2_2_2_2__0__Impl rule__ModelGroupItem__Group_2_2_2_2__1 ;
    public final void rule__ModelGroupItem__Group_2_2_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1637:1: ( rule__ModelGroupItem__Group_2_2_2_2__0__Impl rule__ModelGroupItem__Group_2_2_2_2__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1638:2: rule__ModelGroupItem__Group_2_2_2_2__0__Impl rule__ModelGroupItem__Group_2_2_2_2__1
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2_2__0__Impl_in_rule__ModelGroupItem__Group_2_2_2_2__03314);
            rule__ModelGroupItem__Group_2_2_2_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2_2__1_in_rule__ModelGroupItem__Group_2_2_2_2__03317);
            rule__ModelGroupItem__Group_2_2_2_2__1();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2_2__0"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2_2__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1645:1: rule__ModelGroupItem__Group_2_2_2_2__0__Impl : ( ',' ) ;
    public final void rule__ModelGroupItem__Group_2_2_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1649:1: ( ( ',' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1650:1: ( ',' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1650:1: ( ',' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1651:1: ','
            {
             before(grammarAccess.getModelGroupItemAccess().getCommaKeyword_2_2_2_2_0()); 
            match(input,33,FOLLOW_33_in_rule__ModelGroupItem__Group_2_2_2_2__0__Impl3345); 
             after(grammarAccess.getModelGroupItemAccess().getCommaKeyword_2_2_2_2_0()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2_2__0__Impl"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2_2__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1664:1: rule__ModelGroupItem__Group_2_2_2_2__1 : rule__ModelGroupItem__Group_2_2_2_2__1__Impl ;
    public final void rule__ModelGroupItem__Group_2_2_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1668:1: ( rule__ModelGroupItem__Group_2_2_2_2__1__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1669:2: rule__ModelGroupItem__Group_2_2_2_2__1__Impl
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__Group_2_2_2_2__1__Impl_in_rule__ModelGroupItem__Group_2_2_2_2__13376);
            rule__ModelGroupItem__Group_2_2_2_2__1__Impl();

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2_2__1"


    // $ANTLR start "rule__ModelGroupItem__Group_2_2_2_2__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1675:1: rule__ModelGroupItem__Group_2_2_2_2__1__Impl : ( ( rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1 ) ) ;
    public final void rule__ModelGroupItem__Group_2_2_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1679:1: ( ( ( rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1680:1: ( ( rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1680:1: ( ( rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1681:1: ( rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1 )
            {
             before(grammarAccess.getModelGroupItemAccess().getArgsAssignment_2_2_2_2_1()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1682:1: ( rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1682:2: rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1_in_rule__ModelGroupItem__Group_2_2_2_2__1__Impl3403);
            rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1();

            state._fsp--;


            }

             after(grammarAccess.getModelGroupItemAccess().getArgsAssignment_2_2_2_2_1()); 

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
    // $ANTLR end "rule__ModelGroupItem__Group_2_2_2_2__1__Impl"


    // $ANTLR start "rule__ModelBinding__Group__0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1696:1: rule__ModelBinding__Group__0 : rule__ModelBinding__Group__0__Impl rule__ModelBinding__Group__1 ;
    public final void rule__ModelBinding__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1700:1: ( rule__ModelBinding__Group__0__Impl rule__ModelBinding__Group__1 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1701:2: rule__ModelBinding__Group__0__Impl rule__ModelBinding__Group__1
            {
            pushFollow(FOLLOW_rule__ModelBinding__Group__0__Impl_in_rule__ModelBinding__Group__03437);
            rule__ModelBinding__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelBinding__Group__1_in_rule__ModelBinding__Group__03440);
            rule__ModelBinding__Group__1();

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
    // $ANTLR end "rule__ModelBinding__Group__0"


    // $ANTLR start "rule__ModelBinding__Group__0__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1708:1: rule__ModelBinding__Group__0__Impl : ( ( rule__ModelBinding__TypeAssignment_0 ) ) ;
    public final void rule__ModelBinding__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1712:1: ( ( ( rule__ModelBinding__TypeAssignment_0 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1713:1: ( ( rule__ModelBinding__TypeAssignment_0 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1713:1: ( ( rule__ModelBinding__TypeAssignment_0 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1714:1: ( rule__ModelBinding__TypeAssignment_0 )
            {
             before(grammarAccess.getModelBindingAccess().getTypeAssignment_0()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1715:1: ( rule__ModelBinding__TypeAssignment_0 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1715:2: rule__ModelBinding__TypeAssignment_0
            {
            pushFollow(FOLLOW_rule__ModelBinding__TypeAssignment_0_in_rule__ModelBinding__Group__0__Impl3467);
            rule__ModelBinding__TypeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getModelBindingAccess().getTypeAssignment_0()); 

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
    // $ANTLR end "rule__ModelBinding__Group__0__Impl"


    // $ANTLR start "rule__ModelBinding__Group__1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1725:1: rule__ModelBinding__Group__1 : rule__ModelBinding__Group__1__Impl rule__ModelBinding__Group__2 ;
    public final void rule__ModelBinding__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1729:1: ( rule__ModelBinding__Group__1__Impl rule__ModelBinding__Group__2 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1730:2: rule__ModelBinding__Group__1__Impl rule__ModelBinding__Group__2
            {
            pushFollow(FOLLOW_rule__ModelBinding__Group__1__Impl_in_rule__ModelBinding__Group__13497);
            rule__ModelBinding__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ModelBinding__Group__2_in_rule__ModelBinding__Group__13500);
            rule__ModelBinding__Group__2();

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
    // $ANTLR end "rule__ModelBinding__Group__1"


    // $ANTLR start "rule__ModelBinding__Group__1__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1737:1: rule__ModelBinding__Group__1__Impl : ( '=' ) ;
    public final void rule__ModelBinding__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1741:1: ( ( '=' ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1742:1: ( '=' )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1742:1: ( '=' )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1743:1: '='
            {
             before(grammarAccess.getModelBindingAccess().getEqualsSignKeyword_1()); 
            match(input,38,FOLLOW_38_in_rule__ModelBinding__Group__1__Impl3528); 
             after(grammarAccess.getModelBindingAccess().getEqualsSignKeyword_1()); 

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
    // $ANTLR end "rule__ModelBinding__Group__1__Impl"


    // $ANTLR start "rule__ModelBinding__Group__2"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1756:1: rule__ModelBinding__Group__2 : rule__ModelBinding__Group__2__Impl ;
    public final void rule__ModelBinding__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1760:1: ( rule__ModelBinding__Group__2__Impl )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1761:2: rule__ModelBinding__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__ModelBinding__Group__2__Impl_in_rule__ModelBinding__Group__23559);
            rule__ModelBinding__Group__2__Impl();

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
    // $ANTLR end "rule__ModelBinding__Group__2"


    // $ANTLR start "rule__ModelBinding__Group__2__Impl"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1767:1: rule__ModelBinding__Group__2__Impl : ( ( rule__ModelBinding__ConfigurationAssignment_2 ) ) ;
    public final void rule__ModelBinding__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1771:1: ( ( ( rule__ModelBinding__ConfigurationAssignment_2 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1772:1: ( ( rule__ModelBinding__ConfigurationAssignment_2 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1772:1: ( ( rule__ModelBinding__ConfigurationAssignment_2 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1773:1: ( rule__ModelBinding__ConfigurationAssignment_2 )
            {
             before(grammarAccess.getModelBindingAccess().getConfigurationAssignment_2()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1774:1: ( rule__ModelBinding__ConfigurationAssignment_2 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1774:2: rule__ModelBinding__ConfigurationAssignment_2
            {
            pushFollow(FOLLOW_rule__ModelBinding__ConfigurationAssignment_2_in_rule__ModelBinding__Group__2__Impl3586);
            rule__ModelBinding__ConfigurationAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getModelBindingAccess().getConfigurationAssignment_2()); 

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
    // $ANTLR end "rule__ModelBinding__Group__2__Impl"


    // $ANTLR start "rule__ItemModel__ItemsAssignment_1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1791:1: rule__ItemModel__ItemsAssignment_1 : ( ruleModelItem ) ;
    public final void rule__ItemModel__ItemsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1795:1: ( ( ruleModelItem ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1796:1: ( ruleModelItem )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1796:1: ( ruleModelItem )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1797:1: ruleModelItem
            {
             before(grammarAccess.getItemModelAccess().getItemsModelItemParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleModelItem_in_rule__ItemModel__ItemsAssignment_13627);
            ruleModelItem();

            state._fsp--;

             after(grammarAccess.getItemModelAccess().getItemsModelItemParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__ItemModel__ItemsAssignment_1"


    // $ANTLR start "rule__ModelItem__NameAssignment_1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1806:1: rule__ModelItem__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__ModelItem__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1810:1: ( ( RULE_ID ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1811:1: ( RULE_ID )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1811:1: ( RULE_ID )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1812:1: RULE_ID
            {
             before(grammarAccess.getModelItemAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ModelItem__NameAssignment_13658); 
             after(grammarAccess.getModelItemAccess().getNameIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__ModelItem__NameAssignment_1"


    // $ANTLR start "rule__ModelItem__LabelAssignment_2"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1821:1: rule__ModelItem__LabelAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ModelItem__LabelAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1825:1: ( ( RULE_STRING ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1826:1: ( RULE_STRING )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1826:1: ( RULE_STRING )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1827:1: RULE_STRING
            {
             before(grammarAccess.getModelItemAccess().getLabelSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__ModelItem__LabelAssignment_23689); 
             after(grammarAccess.getModelItemAccess().getLabelSTRINGTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__ModelItem__LabelAssignment_2"


    // $ANTLR start "rule__ModelItem__IconAssignment_3_1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1836:1: rule__ModelItem__IconAssignment_3_1 : ( ( rule__ModelItem__IconAlternatives_3_1_0 ) ) ;
    public final void rule__ModelItem__IconAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1840:1: ( ( ( rule__ModelItem__IconAlternatives_3_1_0 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1841:1: ( ( rule__ModelItem__IconAlternatives_3_1_0 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1841:1: ( ( rule__ModelItem__IconAlternatives_3_1_0 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1842:1: ( rule__ModelItem__IconAlternatives_3_1_0 )
            {
             before(grammarAccess.getModelItemAccess().getIconAlternatives_3_1_0()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1843:1: ( rule__ModelItem__IconAlternatives_3_1_0 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1843:2: rule__ModelItem__IconAlternatives_3_1_0
            {
            pushFollow(FOLLOW_rule__ModelItem__IconAlternatives_3_1_0_in_rule__ModelItem__IconAssignment_3_13720);
            rule__ModelItem__IconAlternatives_3_1_0();

            state._fsp--;


            }

             after(grammarAccess.getModelItemAccess().getIconAlternatives_3_1_0()); 

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
    // $ANTLR end "rule__ModelItem__IconAssignment_3_1"


    // $ANTLR start "rule__ModelItem__GroupsAssignment_4_1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1852:1: rule__ModelItem__GroupsAssignment_4_1 : ( RULE_ID ) ;
    public final void rule__ModelItem__GroupsAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1856:1: ( ( RULE_ID ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1857:1: ( RULE_ID )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1857:1: ( RULE_ID )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1858:1: RULE_ID
            {
             before(grammarAccess.getModelItemAccess().getGroupsIDTerminalRuleCall_4_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ModelItem__GroupsAssignment_4_13753); 
             after(grammarAccess.getModelItemAccess().getGroupsIDTerminalRuleCall_4_1_0()); 

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
    // $ANTLR end "rule__ModelItem__GroupsAssignment_4_1"


    // $ANTLR start "rule__ModelItem__GroupsAssignment_4_2_1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1867:1: rule__ModelItem__GroupsAssignment_4_2_1 : ( RULE_ID ) ;
    public final void rule__ModelItem__GroupsAssignment_4_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1871:1: ( ( RULE_ID ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1872:1: ( RULE_ID )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1872:1: ( RULE_ID )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1873:1: RULE_ID
            {
             before(grammarAccess.getModelItemAccess().getGroupsIDTerminalRuleCall_4_2_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ModelItem__GroupsAssignment_4_2_13784); 
             after(grammarAccess.getModelItemAccess().getGroupsIDTerminalRuleCall_4_2_1_0()); 

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
    // $ANTLR end "rule__ModelItem__GroupsAssignment_4_2_1"


    // $ANTLR start "rule__ModelItem__BindingsAssignment_5_1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1882:1: rule__ModelItem__BindingsAssignment_5_1 : ( ruleModelBinding ) ;
    public final void rule__ModelItem__BindingsAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1886:1: ( ( ruleModelBinding ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1887:1: ( ruleModelBinding )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1887:1: ( ruleModelBinding )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1888:1: ruleModelBinding
            {
             before(grammarAccess.getModelItemAccess().getBindingsModelBindingParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_ruleModelBinding_in_rule__ModelItem__BindingsAssignment_5_13815);
            ruleModelBinding();

            state._fsp--;

             after(grammarAccess.getModelItemAccess().getBindingsModelBindingParserRuleCall_5_1_0()); 

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
    // $ANTLR end "rule__ModelItem__BindingsAssignment_5_1"


    // $ANTLR start "rule__ModelItem__BindingsAssignment_5_2_1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1897:1: rule__ModelItem__BindingsAssignment_5_2_1 : ( ruleModelBinding ) ;
    public final void rule__ModelItem__BindingsAssignment_5_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1901:1: ( ( ruleModelBinding ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1902:1: ( ruleModelBinding )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1902:1: ( ruleModelBinding )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1903:1: ruleModelBinding
            {
             before(grammarAccess.getModelItemAccess().getBindingsModelBindingParserRuleCall_5_2_1_0()); 
            pushFollow(FOLLOW_ruleModelBinding_in_rule__ModelItem__BindingsAssignment_5_2_13846);
            ruleModelBinding();

            state._fsp--;

             after(grammarAccess.getModelItemAccess().getBindingsModelBindingParserRuleCall_5_2_1_0()); 

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
    // $ANTLR end "rule__ModelItem__BindingsAssignment_5_2_1"


    // $ANTLR start "rule__ModelGroupItem__TypeAssignment_2_1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1912:1: rule__ModelGroupItem__TypeAssignment_2_1 : ( ruleModelItemType ) ;
    public final void rule__ModelGroupItem__TypeAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1916:1: ( ( ruleModelItemType ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1917:1: ( ruleModelItemType )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1917:1: ( ruleModelItemType )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1918:1: ruleModelItemType
            {
             before(grammarAccess.getModelGroupItemAccess().getTypeModelItemTypeParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleModelItemType_in_rule__ModelGroupItem__TypeAssignment_2_13877);
            ruleModelItemType();

            state._fsp--;

             after(grammarAccess.getModelGroupItemAccess().getTypeModelItemTypeParserRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__ModelGroupItem__TypeAssignment_2_1"


    // $ANTLR start "rule__ModelGroupItem__FunctionAssignment_2_2_1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1927:1: rule__ModelGroupItem__FunctionAssignment_2_2_1 : ( ruleModelGroupFunction ) ;
    public final void rule__ModelGroupItem__FunctionAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1931:1: ( ( ruleModelGroupFunction ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1932:1: ( ruleModelGroupFunction )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1932:1: ( ruleModelGroupFunction )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1933:1: ruleModelGroupFunction
            {
             before(grammarAccess.getModelGroupItemAccess().getFunctionModelGroupFunctionEnumRuleCall_2_2_1_0()); 
            pushFollow(FOLLOW_ruleModelGroupFunction_in_rule__ModelGroupItem__FunctionAssignment_2_2_13908);
            ruleModelGroupFunction();

            state._fsp--;

             after(grammarAccess.getModelGroupItemAccess().getFunctionModelGroupFunctionEnumRuleCall_2_2_1_0()); 

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
    // $ANTLR end "rule__ModelGroupItem__FunctionAssignment_2_2_1"


    // $ANTLR start "rule__ModelGroupItem__ArgsAssignment_2_2_2_1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1942:1: rule__ModelGroupItem__ArgsAssignment_2_2_2_1 : ( ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0 ) ) ;
    public final void rule__ModelGroupItem__ArgsAssignment_2_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1946:1: ( ( ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1947:1: ( ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1947:1: ( ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1948:1: ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0 )
            {
             before(grammarAccess.getModelGroupItemAccess().getArgsAlternatives_2_2_2_1_0()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1949:1: ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1949:2: rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0_in_rule__ModelGroupItem__ArgsAssignment_2_2_2_13939);
            rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0();

            state._fsp--;


            }

             after(grammarAccess.getModelGroupItemAccess().getArgsAlternatives_2_2_2_1_0()); 

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
    // $ANTLR end "rule__ModelGroupItem__ArgsAssignment_2_2_2_1"


    // $ANTLR start "rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1958:1: rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1 : ( ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0 ) ) ;
    public final void rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1962:1: ( ( ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0 ) ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1963:1: ( ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0 ) )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1963:1: ( ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0 ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1964:1: ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0 )
            {
             before(grammarAccess.getModelGroupItemAccess().getArgsAlternatives_2_2_2_2_1_0()); 
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1965:1: ( rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0 )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1965:2: rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0
            {
            pushFollow(FOLLOW_rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0_in_rule__ModelGroupItem__ArgsAssignment_2_2_2_2_13972);
            rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0();

            state._fsp--;


            }

             after(grammarAccess.getModelGroupItemAccess().getArgsAlternatives_2_2_2_2_1_0()); 

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
    // $ANTLR end "rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1"


    // $ANTLR start "rule__ModelNormalItem__TypeAssignment"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1974:1: rule__ModelNormalItem__TypeAssignment : ( ruleModelItemType ) ;
    public final void rule__ModelNormalItem__TypeAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1978:1: ( ( ruleModelItemType ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1979:1: ( ruleModelItemType )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1979:1: ( ruleModelItemType )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1980:1: ruleModelItemType
            {
             before(grammarAccess.getModelNormalItemAccess().getTypeModelItemTypeParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleModelItemType_in_rule__ModelNormalItem__TypeAssignment4005);
            ruleModelItemType();

            state._fsp--;

             after(grammarAccess.getModelNormalItemAccess().getTypeModelItemTypeParserRuleCall_0()); 

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
    // $ANTLR end "rule__ModelNormalItem__TypeAssignment"


    // $ANTLR start "rule__ModelBinding__TypeAssignment_0"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1989:1: rule__ModelBinding__TypeAssignment_0 : ( RULE_ID ) ;
    public final void rule__ModelBinding__TypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1993:1: ( ( RULE_ID ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1994:1: ( RULE_ID )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1994:1: ( RULE_ID )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:1995:1: RULE_ID
            {
             before(grammarAccess.getModelBindingAccess().getTypeIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ModelBinding__TypeAssignment_04036); 
             after(grammarAccess.getModelBindingAccess().getTypeIDTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__ModelBinding__TypeAssignment_0"


    // $ANTLR start "rule__ModelBinding__ConfigurationAssignment_2"
    // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:2004:1: rule__ModelBinding__ConfigurationAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ModelBinding__ConfigurationAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:2008:1: ( ( RULE_STRING ) )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:2009:1: ( RULE_STRING )
            {
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:2009:1: ( RULE_STRING )
            // ../org.openhab.model.item.ui/src-gen/org/openhab/model/ui/contentassist/antlr/internal/InternalItems.g:2010:1: RULE_STRING
            {
             before(grammarAccess.getModelBindingAccess().getConfigurationSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__ModelBinding__ConfigurationAssignment_24067); 
             after(grammarAccess.getModelBindingAccess().getConfigurationSTRINGTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__ModelBinding__ConfigurationAssignment_2"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleItemModel_in_entryRuleItemModel61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleItemModel68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ItemModel__Group__0_in_ruleItemModel94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelItem_in_entryRuleModelItem121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModelItem128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__0_in_ruleModelItem154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelGroupItem_in_entryRuleModelGroupItem181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModelGroupItem188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group__0_in_ruleModelGroupItem214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelNormalItem_in_entryRuleModelNormalItem241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModelNormalItem248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelNormalItem__TypeAssignment_in_ruleModelNormalItem274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelItemType_in_entryRuleModelItemType301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModelItemType308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItemType__Alternatives_in_ruleModelItemType334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelBinding_in_entryRuleModelBinding361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModelBinding368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelBinding__Group__0_in_ruleModelBinding394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupFunction__Alternatives_in_ruleModelGroupFunction431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelNormalItem_in_rule__ModelItem__Alternatives_0466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelGroupItem_in_rule__ModelItem__Alternatives_0483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ModelItem__IconAlternatives_3_1_0515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__ModelItem__IconAlternatives_3_1_0532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__ModelItemType__Alternatives663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__ModelItemType__Alternatives683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__ModelItemType__Alternatives703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__ModelItemType__Alternatives723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__ModelItemType__Alternatives743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__ModelItemType__Alternatives763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__ModelItemType__Alternatives783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__ModelItemType__Alternatives803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__ModelItemType__Alternatives823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ModelItemType__Alternatives842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__ModelGroupFunction__Alternatives875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__ModelGroupFunction__Alternatives896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__ModelGroupFunction__Alternatives917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__ModelGroupFunction__Alternatives938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__ModelGroupFunction__Alternatives959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__ModelGroupFunction__Alternatives980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__ModelGroupFunction__Alternatives1001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__ModelGroupFunction__Alternatives1022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__ModelGroupFunction__Alternatives1043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ItemModel__Group__0__Impl_in_rule__ItemModel__Group__01076 = new BitSet(new long[]{0x00000010000FF810L});
    public static final BitSet FOLLOW_rule__ItemModel__Group__1_in_rule__ItemModel__Group__01079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ItemModel__Group__1__Impl_in_rule__ItemModel__Group__11137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ItemModel__ItemsAssignment_1_in_rule__ItemModel__Group__1__Impl1164 = new BitSet(new long[]{0x00000010000FF812L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__0__Impl_in_rule__ModelItem__Group__01199 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__1_in_rule__ModelItem__Group__01202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Alternatives_0_in_rule__ModelItem__Group__0__Impl1229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__1__Impl_in_rule__ModelItem__Group__11259 = new BitSet(new long[]{0x00000004A0000020L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__2_in_rule__ModelItem__Group__11262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__NameAssignment_1_in_rule__ModelItem__Group__1__Impl1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__2__Impl_in_rule__ModelItem__Group__21319 = new BitSet(new long[]{0x00000004A0000020L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__3_in_rule__ModelItem__Group__21322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__LabelAssignment_2_in_rule__ModelItem__Group__2__Impl1349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__3__Impl_in_rule__ModelItem__Group__31380 = new BitSet(new long[]{0x00000004A0000020L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__4_in_rule__ModelItem__Group__31383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_3__0_in_rule__ModelItem__Group__3__Impl1410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__4__Impl_in_rule__ModelItem__Group__41441 = new BitSet(new long[]{0x00000004A0000020L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__5_in_rule__ModelItem__Group__41444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4__0_in_rule__ModelItem__Group__4__Impl1471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group__5__Impl_in_rule__ModelItem__Group__51502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5__0_in_rule__ModelItem__Group__5__Impl1529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_3__0__Impl_in_rule__ModelItem__Group_3__01572 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_3__1_in_rule__ModelItem__Group_3__01575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__ModelItem__Group_3__0__Impl1603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_3__1__Impl_in_rule__ModelItem__Group_3__11634 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_3__2_in_rule__ModelItem__Group_3__11637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__IconAssignment_3_1_in_rule__ModelItem__Group_3__1__Impl1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_3__2__Impl_in_rule__ModelItem__Group_3__21694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__ModelItem__Group_3__2__Impl1722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4__0__Impl_in_rule__ModelItem__Group_4__01759 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4__1_in_rule__ModelItem__Group_4__01762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__ModelItem__Group_4__0__Impl1790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4__1__Impl_in_rule__ModelItem__Group_4__11821 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4__2_in_rule__ModelItem__Group_4__11824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__GroupsAssignment_4_1_in_rule__ModelItem__Group_4__1__Impl1851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4__2__Impl_in_rule__ModelItem__Group_4__21881 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4__3_in_rule__ModelItem__Group_4__21884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4_2__0_in_rule__ModelItem__Group_4__2__Impl1911 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4__3__Impl_in_rule__ModelItem__Group_4__31942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__ModelItem__Group_4__3__Impl1970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4_2__0__Impl_in_rule__ModelItem__Group_4_2__02009 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4_2__1_in_rule__ModelItem__Group_4_2__02012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__ModelItem__Group_4_2__0__Impl2040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_4_2__1__Impl_in_rule__ModelItem__Group_4_2__12071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__GroupsAssignment_4_2_1_in_rule__ModelItem__Group_4_2__1__Impl2098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5__0__Impl_in_rule__ModelItem__Group_5__02132 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5__1_in_rule__ModelItem__Group_5__02135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__ModelItem__Group_5__0__Impl2163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5__1__Impl_in_rule__ModelItem__Group_5__12194 = new BitSet(new long[]{0x0000000A00000000L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5__2_in_rule__ModelItem__Group_5__12197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__BindingsAssignment_5_1_in_rule__ModelItem__Group_5__1__Impl2224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5__2__Impl_in_rule__ModelItem__Group_5__22254 = new BitSet(new long[]{0x0000000A00000000L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5__3_in_rule__ModelItem__Group_5__22257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5_2__0_in_rule__ModelItem__Group_5__2__Impl2284 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5__3__Impl_in_rule__ModelItem__Group_5__32315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__ModelItem__Group_5__3__Impl2343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5_2__0__Impl_in_rule__ModelItem__Group_5_2__02382 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5_2__1_in_rule__ModelItem__Group_5_2__02385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__ModelItem__Group_5_2__0__Impl2413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__Group_5_2__1__Impl_in_rule__ModelItem__Group_5_2__12444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__BindingsAssignment_5_2_1_in_rule__ModelItem__Group_5_2__1__Impl2471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group__0__Impl_in_rule__ModelGroupItem__Group__02505 = new BitSet(new long[]{0x00000010000FF810L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group__1_in_rule__ModelGroupItem__Group__02508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group__1__Impl_in_rule__ModelGroupItem__Group__12566 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group__2_in_rule__ModelGroupItem__Group__12569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__ModelGroupItem__Group__1__Impl2597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group__2__Impl_in_rule__ModelGroupItem__Group__22628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2__0_in_rule__ModelGroupItem__Group__2__Impl2655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2__0__Impl_in_rule__ModelGroupItem__Group_2__02692 = new BitSet(new long[]{0x00000000000FF810L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2__1_in_rule__ModelGroupItem__Group_2__02695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__ModelGroupItem__Group_2__0__Impl2723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2__1__Impl_in_rule__ModelGroupItem__Group_2__12754 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2__2_in_rule__ModelGroupItem__Group_2__12757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__TypeAssignment_2_1_in_rule__ModelGroupItem__Group_2__1__Impl2784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2__2__Impl_in_rule__ModelGroupItem__Group_2__22814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2__0_in_rule__ModelGroupItem__Group_2__2__Impl2841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2__0__Impl_in_rule__ModelGroupItem__Group_2_2__02878 = new BitSet(new long[]{0x000000001FF00000L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2__1_in_rule__ModelGroupItem__Group_2_2__02881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__ModelGroupItem__Group_2_2__0__Impl2909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2__1__Impl_in_rule__ModelGroupItem__Group_2_2__12940 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2__2_in_rule__ModelGroupItem__Group_2_2__12943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__FunctionAssignment_2_2_1_in_rule__ModelGroupItem__Group_2_2__1__Impl2970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2__2__Impl_in_rule__ModelGroupItem__Group_2_2__23000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2__0_in_rule__ModelGroupItem__Group_2_2__2__Impl3027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2__0__Impl_in_rule__ModelGroupItem__Group_2_2_2__03064 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2__1_in_rule__ModelGroupItem__Group_2_2_2__03067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__ModelGroupItem__Group_2_2_2__0__Impl3095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2__1__Impl_in_rule__ModelGroupItem__Group_2_2_2__13126 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2__2_in_rule__ModelGroupItem__Group_2_2_2__13129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__ArgsAssignment_2_2_2_1_in_rule__ModelGroupItem__Group_2_2_2__1__Impl3156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2__2__Impl_in_rule__ModelGroupItem__Group_2_2_2__23186 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2__3_in_rule__ModelGroupItem__Group_2_2_2__23189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2_2__0_in_rule__ModelGroupItem__Group_2_2_2__2__Impl3216 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2__3__Impl_in_rule__ModelGroupItem__Group_2_2_2__33247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__ModelGroupItem__Group_2_2_2__3__Impl3275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2_2__0__Impl_in_rule__ModelGroupItem__Group_2_2_2_2__03314 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2_2__1_in_rule__ModelGroupItem__Group_2_2_2_2__03317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__ModelGroupItem__Group_2_2_2_2__0__Impl3345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__Group_2_2_2_2__1__Impl_in_rule__ModelGroupItem__Group_2_2_2_2__13376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__ArgsAssignment_2_2_2_2_1_in_rule__ModelGroupItem__Group_2_2_2_2__1__Impl3403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelBinding__Group__0__Impl_in_rule__ModelBinding__Group__03437 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rule__ModelBinding__Group__1_in_rule__ModelBinding__Group__03440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelBinding__TypeAssignment_0_in_rule__ModelBinding__Group__0__Impl3467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelBinding__Group__1__Impl_in_rule__ModelBinding__Group__13497 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__ModelBinding__Group__2_in_rule__ModelBinding__Group__13500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__ModelBinding__Group__1__Impl3528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelBinding__Group__2__Impl_in_rule__ModelBinding__Group__23559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelBinding__ConfigurationAssignment_2_in_rule__ModelBinding__Group__2__Impl3586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelItem_in_rule__ItemModel__ItemsAssignment_13627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ModelItem__NameAssignment_13658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__ModelItem__LabelAssignment_23689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelItem__IconAlternatives_3_1_0_in_rule__ModelItem__IconAssignment_3_13720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ModelItem__GroupsAssignment_4_13753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ModelItem__GroupsAssignment_4_2_13784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelBinding_in_rule__ModelItem__BindingsAssignment_5_13815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelBinding_in_rule__ModelItem__BindingsAssignment_5_2_13846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelItemType_in_rule__ModelGroupItem__TypeAssignment_2_13877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelGroupFunction_in_rule__ModelGroupItem__FunctionAssignment_2_2_13908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__ArgsAlternatives_2_2_2_1_0_in_rule__ModelGroupItem__ArgsAssignment_2_2_2_13939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ModelGroupItem__ArgsAlternatives_2_2_2_2_1_0_in_rule__ModelGroupItem__ArgsAssignment_2_2_2_2_13972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelItemType_in_rule__ModelNormalItem__TypeAssignment4005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ModelBinding__TypeAssignment_04036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__ModelBinding__ConfigurationAssignment_24067 = new BitSet(new long[]{0x0000000000000002L});

}
package burp.config;

import burp.IBurpExtenderCallbacks;
import burp.IExtensionStateListener;
import burp.RegexRulesTable;

public class RulesConfig implements IExtensionStateListener {
    private IBurpExtenderCallbacks callbacks;
    public static final String CONFIG_KEY = "config_key";
    private RegexRulesTable rulesTable;

    public RulesConfig(IBurpExtenderCallbacks callbacks, RegexRulesTable rulesTable) {
        this.callbacks = callbacks;
        this.rulesTable = rulesTable;
    }

    public String getMatchRulesInString() {
        return this.callbacks.loadExtensionSetting(CONFIG_KEY);
    }

    @Override
    public void extensionUnloaded() {
        String rules = rulesTable.getMatchRulesInString();
        if (rules != null)
            callbacks.saveExtensionSetting(CONFIG_KEY, rules);
    }
}

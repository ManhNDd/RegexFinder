package burp;


import burp.config.RulesConfig;

/**
 * Burp Extender definition
 */

public class BurpExtender implements IBurpExtender {

    private static final String name = "RegexFinder";
    private static final String version = "0.2";
    private static final String tabName = "RegexFinder";

    protected RegexRulesTable rulesTable;
    protected RegexTab burpTab;
    private RulesConfig rulesConfig;


    @Override
    public void registerExtenderCallbacks(final IBurpExtenderCallbacks callbacks)
    {
        callbacks.setExtensionName(name);
        RegexScan scan = new RegexScan(callbacks);
        rulesTable = new RegexRulesTable(callbacks, scan);
        rulesConfig = new RulesConfig(callbacks, rulesTable);

        String rules = rulesConfig.getMatchRulesInString();
        if (rules != null)
            rulesTable.loadMatchRulesFromString(rules);

        callbacks.registerExtensionStateListener(rulesConfig);

        burpTab = new RegexTab(tabName, callbacks);
        burpTab.addComponent(rulesTable);

        callbacks.registerScannerCheck(scan);
    }

}


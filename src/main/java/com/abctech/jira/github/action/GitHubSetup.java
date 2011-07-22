package com.abctech.jira.github.action;

import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: andremar
 * Date: 7/22/11
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class GitHubSetup extends JiraWebActionSupport {
    private static Logger log = Logger.getLogger(GitHubSetup.class);
    private final PluginSettingsFactory settingsFactory;
    public GitHubSetup(PluginSettingsFactory settingsFactory) {
        this.settingsFactory = settingsFactory;
    }
    public String getWSUrl() {
        String ws = (String) settingsFactory.createGlobalSettings().get("github.ws.url");
        if (ws != null) {
            return ws;
        } else { // No setting saved
            return "Please set a WS url";
        }
    }

    public String doExecute() throws Exception {
        log.debug("We are in doExecute for Setup:" + request.getParameter("ws"));
        if (request.getParameter("ws") != null) {
            // We have submitted something
            settingsFactory.createGlobalSettings().put("github.ws.url", request.getParameter("ws"));
            return getRedirect("GitHubSetup.jspa");
        } else {
            return "success";
        }
    }
}

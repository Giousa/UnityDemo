package com.giou.unitydemo.model;

import java.util.Map;

/**
 * Created by 7 on 2016/7/24.
 */
public class AndroidToUnityModel {
    private String commandId;
    private Map<String, String> commandParams;

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public void setCommandParams(Map<String, String> commandParams) {
        this.commandParams = commandParams;
    }

    public String getCommandId() {
        return this.commandId;
    }

    public Map<String, String> getCommandParams() {
        return this.commandParams;
    }

}

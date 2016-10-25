package com.giou.unitydemo.model;

/**
 * Description:
 * Author：Giousa
 * Date：2016/7/24
 */
public class ClientInfo {
    public ClientInfo() {
    }
    private String clientId;
    private int teamId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}

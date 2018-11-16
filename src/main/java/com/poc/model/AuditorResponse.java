package com.poc.model;

import java.util.ArrayList;
import java.util.List;

public class AuditorResponse {

    public List<String> getAuditors() {
        return auditors;
    }

    public void addAuditor(String name) {
        this.auditors.add(name);
    }

    public void setAuditors(List<String> auditors) {
        this.auditors = auditors;
    }

    public List<String> auditors= new ArrayList<String>();

}

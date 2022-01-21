package com.example.meminder;

import java.io.Serializable;

public class nameS_Helper implements Serializable {
    String nameS;

    public nameS_Helper() {
    }

    public nameS_Helper(String nameS) {
        this.nameS = nameS;
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS;
    }
}

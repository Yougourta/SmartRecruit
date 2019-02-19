package fr.smartrecruit.data;

import java.io.Serializable;

public class Tips  implements Serializable {
    private String conseil;
    private String type;



    public Tips(String conseil, String type) {
        this.conseil = conseil;
        this.type = type;

    }

    public String getConseil() {
        return conseil;
    }

    public String getType() {
        return type;
    }



}

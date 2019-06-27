package application.pojo;

import java.util.Scanner;

public class CookingTime implements java.io.Serializable {
    private String totalTime;

    public CookingTime(String time) {
        this.totalTime = time;
        System.out.println("time input id" + totalTime + "!");
    }

    public CookingTime() {
        //System.out.println("This is the cook time constructor!");
    }

    public String returnTime() {
        return this.totalTime;
    }
}

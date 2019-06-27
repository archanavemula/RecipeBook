package application.pojo;

import java.util.Scanner;

public class Directions implements java.io.Serializable {
    private String directions;

    public Directions(String directions) {
      //  System.out.println("This is the directions constructor!");
        this.directions = directions;
    }

    public Directions() {
       // System.out.println("This is the directions constructor!");
    }

    public int returnDirectionCount() {
        return 1;
    }

    public String returnDirections() {
        return this.directions;
    }
}

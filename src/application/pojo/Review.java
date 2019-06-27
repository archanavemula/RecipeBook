package application.pojo;

import java.util.Scanner;

public class Review implements java.io.Serializable {
    private int stars;
    private String reviewString;

    public Review(int givenStars, String reviewString) {
        this.stars = givenStars;
        this.reviewString = reviewString;
        System.out.println("Review of " + this.stars + "/5 stars" + " and a review of " + this.reviewString +".");
    }

    public Review(int givenStars) {
        this.stars = givenStars;
        System.out.println("Review of " + this.stars + "/5 stars!");
    }

    public Review() {
       // System.out.println("This is the review constructor!");
    }

    public int returnStars() {
        return this.stars;
    }

    public String returnReviewString() {
        return this.reviewString;
    }
}

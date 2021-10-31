package com.application.bmr.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BodyMassIndex {

    private String username;
    private Gender gender;
    private String somatotype;
    private String weight;
    private String height;
    private String age;
    private String difficultyLevel;
    private String targetFigure;
    private String goal;
    private String proteinGoal;
    private String proteinGoalOther;

    private int getBMR(){
        Double bmr;
        double pomocna1 = 0, pomocna2 = 0, pomocna3 = 0, pomocna4 = 0;

        switch (gender){
            case MALE:
                pomocna1 = 13.7 * Double.parseDouble(weight);
                pomocna2 = 5 * Integer.parseInt(height);
                pomocna3 = 6.8 * Double.parseDouble(age);
                pomocna4 = 66;
                break;

            case FEMALE: {
                pomocna1 = 9.6 * Double.parseDouble(weight);
                pomocna2 = 1.85 * Integer.parseInt(height);
                pomocna3 = 4.7 * Double.parseDouble(age);
                pomocna4 = 655.0935;
                break;
            }
        }

        bmr = pomocna4 + pomocna1 + pomocna2 - pomocna3;
        return bmr.intValue();
    }

    private int resolveDifficultyLevel(){
        double multiplier = 0;

        switch (difficultyLevel){
            case "1":
                multiplier = 1.2;
                break;
            case "2":
                multiplier = 1.375;
                break;
            case "3":
                multiplier = 1.55;
                break;
            case "4":
                multiplier = 1.725;
                break;
            case "5":
                multiplier = 1.9;
                break;
        }

        Double pomocna = getBMR() * multiplier;
        return pomocna.intValue();
    }

    private int getTargetTDEE() {
        int tdee = 0;
        switch (targetFigure){
            case "1":
                tdee = resolveDifficultyLevel() - Integer.parseInt(goal);
                break;
            case "2":
                tdee = resolveDifficultyLevel() + Integer.parseInt(goal);
                break;
        }
        return tdee;
    }

    private int calculateProteins() {
        return calculateProteinsWeight() * 4;
    }

    private int calculateProteinsWeight(){
        Double pomocna = Double.parseDouble(weight) * Double.parseDouble(proteinGoalOther);
        return pomocna.intValue();
    }

    private int calculateFats() {
        double nasobok = 0;

        switch (somatotype){
            case "1":
                nasobok = 0.25;
                break;
            case "2":
                nasobok = 0.3;
                break;
            case "3":
                nasobok = 0.35;
                break;
        }

        Long targetFats = Math.round((getTargetTDEE() * nasobok) * 100) / 100;
        return targetFats.intValue();
    }

    private int calculateFatsWeight(){
        return calculateFats() / 9;
    }

    private int calculateCarbohydrates() {
        return getTargetTDEE() - calculateFats() - calculateProteins();
    }

    private int calculateCarbohydrateWeight(){
        return calculateCarbohydrates() / 4;
    }

    @Override
    public String toString() {
        return "UserBMR{" +
                "username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", somatotype='" + somatotype + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", vek='" + age + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", targetFigure='" + targetFigure + '\'' +
                ", goal='" + goal + '\'' +
                ", proteinGoal='" + proteinGoal + '\'' +
                ", proteinGoalOther='" + proteinGoalOther + '\'' +
                '}';
    }

    private enum Gender {
        MALE, FEMALE, OTHER
    }
}

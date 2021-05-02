package com.application.customer.entity;

import com.application.customer.validation.CourseCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Random;

public class Customer {
    private String firstName;

    @NotNull(message = "Reguired")
    @Size(min = 1,message = "is required")
    private String lastName;

    @NotNull(message = "Reguired")
    @Min(value = 0, message = "The number must be more than zero")
    @Max(value = 10, message = "The number must be less than ten")
    private Integer freePassess;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "5 chars/digits only")
    private String postalCode;

    @CourseCode
    private String courseCode;

    private int sumNum;

    public Customer(){
        sumNum = new Random().nextInt(5000);
    }

    public int getSumNum() {
        return sumNum;
    }

    public void setSumNum(int sumNum) {
        this.sumNum = sumNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFreePassess() {
        return freePassess;
    }

    public void setFreePassess(Integer freePassess) {
        this.freePassess = freePassess;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}

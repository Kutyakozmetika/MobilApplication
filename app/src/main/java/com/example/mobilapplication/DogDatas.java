package com.example.mobilapplication;

public class DogDatas {
    private String isAgressive;
    private String furType;
    private String age;
    private String dogBreed;
    private String usersName;

    public DogDatas(String isAgressive, String furType, String age, String dogBreed, String usersName) {
        this.isAgressive = isAgressive;
        this.furType = furType;
        this.age = age;
        this.dogBreed = dogBreed;
        this.usersName = usersName;
    }

    public String getIsAgressive() {
        return isAgressive;
    }

    public void setIsAgressive(String isAgressive) {
        this.isAgressive = isAgressive;
    }

    public String getFurType() {
        return furType;
    }

    public void setFurType(String furType) {
        this.furType = furType;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }
}

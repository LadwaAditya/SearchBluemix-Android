package com.ladwa.aditya.bluemixsearch;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aditya on 19-Sep-16.
 */

public class Person implements Parcelable {
    private String name;
    private String age;
    private String id;

    protected Person(Parcel in) {
        name = in.readString();
        age = in.readString();
        id = in.readString();
    }

    public Person() {
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(age);
        parcel.writeString(id);
    }
}

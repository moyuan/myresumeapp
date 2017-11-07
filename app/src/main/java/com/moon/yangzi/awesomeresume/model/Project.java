package com.moon.yangzi.awesomeresume.model;

/**
 * Created by yang on 11/5/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.moon.yangzi.awesomeresume.util.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Project implements Parcelable {

    public String id;

    public String name;

    public Date startDate;

    public Date endDate;

    public List<String> details;

    public Project() {
        id = UUID.randomUUID().toString();
    }

    protected Project(Parcel in) {
        id = in.readString();
        name = in.readString();
        details = in.createStringArrayList();
        startDate = DateUtils.stringToDate(in.readString());
        endDate = DateUtils.stringToDate(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeStringList(details);
        dest.writeString(DateUtils.dateToString(startDate));
        dest.writeString(DateUtils.dateToString(endDate));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
}

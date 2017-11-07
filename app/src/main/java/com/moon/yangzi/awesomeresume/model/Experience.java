package com.moon.yangzi.awesomeresume.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.moon.yangzi.awesomeresume.util.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by yang on 11/5/2017.
 */

public class Experience implements Parcelable {

    public String id;

    public String company;

    public String title;

    public Date startDate;

    public Date endDate;

    public List<String> details;

    public Experience() { id = UUID.randomUUID().toString(); }


    protected Experience(Parcel in) {
        id = in.readString();
        company = in.readString();
        title = in.readString();
        details = in.createStringArrayList();
        startDate = DateUtils.stringToDate(in.readString());
        endDate = DateUtils.stringToDate((in.readString()));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(company);
        dest.writeString(title);
        dest.writeStringList(details);
        dest.writeString(DateUtils.dateToString(startDate));
        dest.writeString(DateUtils.dateToString(endDate));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Experience> CREATOR = new Creator<Experience>() {
        @Override
        public Experience createFromParcel(Parcel in) {
            return new Experience(in);
        }

        @Override
        public Experience[] newArray(int size) {
            return new Experience[size];
        }
    };
}

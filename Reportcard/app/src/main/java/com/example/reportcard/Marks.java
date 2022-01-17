package com.example.reportcard;

public class Marks {
    private String mSubject;
    private String mMarks;

    public Marks(String Subject,String Marks){
    mSubject=Subject;
    mMarks=Marks;
    }

    public String getmMarks() {
        return mMarks;
    }

    public String getmSubject() {
        return mSubject;
    }
}

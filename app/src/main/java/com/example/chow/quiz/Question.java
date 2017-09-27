package com.example.chow.quiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by per6 on 9/15/17.
 */

public class Question implements Parcelable {

    private String questionText;
    private boolean ans;

    public Question(String questionText, boolean ans) {
        this.questionText = questionText;
        this.ans = ans;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean checkAns(boolean chosenAns)
    {
        return chosenAns == ans;
    }

    protected Question(Parcel in) {
        questionText = in.readString();
        ans = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionText);
        dest.writeByte((byte) (ans ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
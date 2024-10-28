package com.nttung.quiz;

public class Question {
    private int mTestResId;
    private boolean mAnswerTrue;

    public Question(int mTestResId, boolean mAnswerTrue) {
        this.mTestResId = mTestResId;
        this.mAnswerTrue = mAnswerTrue;
    }

    public int getmTestResId() {
        return mTestResId;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }

    public void setmTestResId(int mTestResId) {
        this.mTestResId = mTestResId;
    }

    public void setmAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }
}

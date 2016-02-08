package com.example.osulld13.digitalcollections;

/**
 * Created by osulld13 on 07/02/16.
 */
public class Document{

    public String mPid; // PID
    public String mDrisFolderNumber; // identifier_DRIS_FOLDER"
    public String mGenre; // genre
    public String mLang; // language
    public String mTypeOfResource; // typeOfResource
    public String mText; // allText

    public Document(String pid, String drisFolderNumber, String genre){
        this.mPid = pid;
        this.mDrisFolderNumber = drisFolderNumber;
        this.mGenre = genre;
    }
    /*
        Getter Methods
     */

    public String getmPid() {
        return mPid;
    }

    public String getmDrisFolderNumber() {
        return mDrisFolderNumber;
    }

    public String getmGenre() {
        return mGenre;
    }

    public String getmLang() {
        return mLang;
    }

    public String getmTypeOfResource() {
        return mTypeOfResource;
    }

    public String getmText() {
        return mText;
    }

    /*
        Setter Methods
     */

    public void setmPid(String mPid) {
        this.mPid = mPid;
    }

    public void setmDrisFolderNumber(String mDrisFolderNumber) {
        this.mDrisFolderNumber = mDrisFolderNumber;
    }

    public void setmGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    public void setmLang(String mLang) {
        this.mLang = mLang;
    }

    public void setmTypeOfResource(String mTypeOfResource) {
        this.mTypeOfResource = mTypeOfResource;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String toString(){
        return "Document: " + this.mPid + " " + this.mDrisFolderNumber + " " + this.mGenre + "\n";
    }
}

package com.example.books;

public class Books {
    private String mBooks;
    private  String mAuthors;

    public Books(String books,String authors) {
        mBooks=books;
        mAuthors=authors;
    }
    public String getmBooks(){
        return mBooks;
    }
    public String getmAuthors(){
        return mAuthors;
    }
}

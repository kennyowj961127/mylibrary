package com.example.mylibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mylibrary.model.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS_KEY = "already_read_books";
    private static final String WANT_TO_READ_BOOKS_KEY = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS_KEY = "currently_reading_books";
    private static final String FAVORITE_BOOKS_KEY = "favorite_books";
    private static Utils instance;
    private final SharedPreferences sharedPreferences;

    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAllBooks()) {
            initData();
        }
        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getWantToReadBooks()) {
            editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavoriteBooks()) {
            editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;

    public static synchronized Utils getInstance(Context context) {
        if (null == instance) {
            instance = new Utils(context);
        }
        return instance;
    }

    private void initData() {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(
                1,
                "The Heaven & Earth Grocery Store (2023 B&N Book of the Year)",
                "James McBride",
                99,
                "https://prodimage.images-bn.com/pimages/9780593422946_p0_v9_s1200x630.jpg",
                "From the author of Deacon King Kong and National Book Award winner The Good Lord Bird comes a gorgeous novel about a small town and the bonds of community that are formed between marginalized groups in order to survive.",
                "THE RUNAWAY NEW YORK TIMES BESTSELLER\n" +
                        "\n" +
                        "“A murder mystery locked inside a Great American Novel . . . Charming, smart, heart-blistering, and heart-healing.” —Danez Smith, The New York Times Book Review\n" +
                        "\n" +
                        "“We all need—we all deserve—this vibrant, love-affirming novel that bounds over any difference that claims to separate us.” —Ron Charles, The Washington Post\n" +
                        "\n" +
                        "From James McBride, author of the bestselling Oprah’s Book Club pick Deacon King Kong and the National Book Award–winning The Good Lord Bird, a novel about small-town secrets and the people who keep them\n" +
                        "\n" +
                        "In 1972, when workers in Pottstown, Pennsylvania, were digging the foundations for a new development, the last thing they expected to find was a skeleton at the bottom of a well. Who the skeleton was and how it got there were two of the long-held secrets kept by the residents of Chicken Hill, the dilapidated neighborhood where immigrant Jews and African Americans lived side by side and shared ambitions and sorrows. Chicken Hill was where Moshe and Chona Ludlow lived when Moshe integrated his theater and where Chona ran the Heaven & Earth Grocery Store. When the state came looking for a deaf boy to institutionalize him, it was Chona and Nate Timblin, the Black janitor at Moshe’s theater and the unofficial leader of the Black community on Chicken Hill, who worked together to keep the boy safe.\n" +
                        "\n" +
                        "As these characters’ stories overlap and deepen, it becomes clear how much the people who live on the margins of white, Christian America struggle and what they must do to survive. When the truth is finally revealed about what happened on Chicken Hill and the part the town’s white establishment played in it, McBride shows us that even in dark times, it is love and community—heaven and earth—that sustain us.\n" +
                        "\n" +
                        "Bringing his masterly storytelling skills and his deep faith in humanity to The Heaven & Earth Grocery Store, James McBride has written a novel as compassionate as Deacon King Kong and as inventive as The Good Lord Bird."
        ));
        books.add(new Book(
                2,
                "The Heaven & Earth Grocery Store (2023 B&N Book of the Year)",
                "James McBride",
                99,
                "https://m.media-amazon.com/images/I/91msxUN2DWL._SY522_.jpg",
                "From the author of Deacon King Kong and National Book Award winner The Good Lord Bird comes a gorgeous novel about a small town and the bonds of community that are formed between marginalized groups in order to survive.",
                "THE RUNAWAY NEW YORK TIMES BESTSELLER\n" +
                        "\n" +
                        "“A murder mystery locked inside a Great American Novel . . . Charming, smart, heart-blistering, and heart-healing.” —Danez Smith, The New York Times Book Review\n" +
                        "\n" +
                        "“We all need—we all deserve—this vibrant, love-affirming novel that bounds over any difference that claims to separate us.” —Ron Charles, The Washington Post\n" +
                        "\n" +
                        "From James McBride, author of the bestselling Oprah’s Book Club pick Deacon King Kong and the National Book Award–winning The Good Lord Bird, a novel about small-town secrets and the people who keep them\n" +
                        "\n" +
                        "In 1972, when workers in Pottstown, Pennsylvania, were digging the foundations for a new development, the last thing they expected to find was a skeleton at the bottom of a well. Who the skeleton was and how it got there were two of the long-held secrets kept by the residents of Chicken Hill, the dilapidated neighborhood where immigrant Jews and African Americans lived side by side and shared ambitions and sorrows. Chicken Hill was where Moshe and Chona Ludlow lived when Moshe integrated his theater and where Chona ran the Heaven & Earth Grocery Store. When the state came looking for a deaf boy to institutionalize him, it was Chona and Nate Timblin, the Black janitor at Moshe’s theater and the unofficial leader of the Black community on Chicken Hill, who worked together to keep the boy safe.\n" +
                        "\n" +
                        "As these characters’ stories overlap and deepen, it becomes clear how much the people who live on the margins of white, Christian America struggle and what they must do to survive. When the truth is finally revealed about what happened on Chicken Hill and the part the town’s white establishment played in it, McBride shows us that even in dark times, it is love and community—heaven and earth—that sustain us.\n" +
                        "\n" +
                        "Bringing his masterly storytelling skills and his deep faith in humanity to The Heaven & Earth Grocery Store, James McBride has written a novel as compassionate as Deacon King Kong and as inventive as The Good Lord Bird."
        ));
        books.add(new Book(
                3,
                "The Heaven & Earth Grocery Store (2023 B&N Book of the Year)",
                "James McBride",
                99,
                "https://m.media-amazon.com/images/I/619ncDeLijL._SY522_.jpg",
                "From the author of Deacon King Kong and National Book Award winner The Good Lord Bird comes a gorgeous novel about a small town and the bonds of community that are formed between marginalized groups in order to survive.",
                "THE RUNAWAY NEW YORK TIMES BESTSELLER\n" +
                        "\n" +
                        "“A murder mystery locked inside a Great American Novel . . . Charming, smart, heart-blistering, and heart-healing.” —Danez Smith, The New York Times Book Review\n" +
                        "\n" +
                        "“We all need—we all deserve—this vibrant, love-affirming novel that bounds over any difference that claims to separate us.” —Ron Charles, The Washington Post\n" +
                        "\n" +
                        "From James McBride, author of the bestselling Oprah’s Book Club pick Deacon King Kong and the National Book Award–winning The Good Lord Bird, a novel about small-town secrets and the people who keep them\n" +
                        "\n" +
                        "In 1972, when workers in Pottstown, Pennsylvania, were digging the foundations for a new development, the last thing they expected to find was a skeleton at the bottom of a well. Who the skeleton was and how it got there were two of the long-held secrets kept by the residents of Chicken Hill, the dilapidated neighborhood where immigrant Jews and African Americans lived side by side and shared ambitions and sorrows. Chicken Hill was where Moshe and Chona Ludlow lived when Moshe integrated his theater and where Chona ran the Heaven & Earth Grocery Store. When the state came looking for a deaf boy to institutionalize him, it was Chona and Nate Timblin, the Black janitor at Moshe’s theater and the unofficial leader of the Black community on Chicken Hill, who worked together to keep the boy safe.\n" +
                        "\n" +
                        "As these characters’ stories overlap and deepen, it becomes clear how much the people who live on the margins of white, Christian America struggle and what they must do to survive. When the truth is finally revealed about what happened on Chicken Hill and the part the town’s white establishment played in it, McBride shows us that even in dark times, it is love and community—heaven and earth—that sustain us.\n" +
                        "\n" +
                        "Bringing his masterly storytelling skills and his deep faith in humanity to The Heaven & Earth Grocery Store, James McBride has written a novel as compassionate as Deacon King Kong and as inventive as The Good Lord Bird."
        ));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.apply();
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS_KEY, null), type);
        return books;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS_KEY);
                editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS_KEY);
                editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS_KEY);
                editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavorite(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS_KEY);
                editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book, OnSharedPreferenceDeletedCallback callback) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS_KEY);
                        editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        callback.onDeleted();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // implement the call back function
    public interface OnSharedPreferenceDeletedCallback {
        void onDeleted();
    }
}

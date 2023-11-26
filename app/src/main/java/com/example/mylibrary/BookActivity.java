package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.util.Utils;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    public static final String TAG = "BOOKACTIVITY";
    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBook(incomingBook);
                    handleCurrentlyreadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }
    }

    public void initViews() {
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtdescription);

        btnAddToWantRead = findViewById(R.id.btnAddToWantToReadList);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);

        bookImage = findViewById(R.id.imgBook);
    }

    public void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this).asBitmap().load(book.getImageUrl()).into(bookImage);
    }

    /**
     * Enable and Disable button
     * Add the book to Already Read Book ArrayList
     *
     * @param book
     */

    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
                break;
            }
        }

        if (existInAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
        } else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyRead(book)) {
                        Toast.makeText(BookActivity.this, "Booked added.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFavoriteBooks(Book incomingBook) {
    }

    private void handleCurrentlyreadingBooks(Book incomingBook) {
    }

    private void handleWantToReadBook(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for (Book b : wantToReadBooks) {
            if (b.getId() == book.getId()) {
                existInWantToReadBooks = true;
                break;
            }
        }

        if (existInWantToReadBooks) {
            btnAddToWantRead.setEnabled(false);
        } else {
            btnAddToWantRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToWantToRead(book)) {
                        Toast.makeText(BookActivity.this, "Booked added.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}
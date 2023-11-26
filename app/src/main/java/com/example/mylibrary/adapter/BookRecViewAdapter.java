package com.example.mylibrary.adapter;

import static com.example.mylibrary.BookActivity.BOOK_ID_KEY;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mylibrary.BookActivity;
import com.example.mylibrary.R;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.util.Utils;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    private static final String TAG = "BookRecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();
    private final Context mContext;

    private final String parentActivity;

    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: Called ");
        holder.txtName.setText(books.get(holder.getAdapterPosition()).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageUrl()).into(holder.imgBook);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(mContext, books.get(holder.getAdapterPosition()).getName() + " Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtDescription.setText(books.get(position).getShortDesc());

        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelativeLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
            if (parentActivity.equals("allBooks")) {
                holder.btnDelete.setVisibility(View.GONE);
            } else if (parentActivity.equals("alreadyRead")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bookName = books.get(position).getName();
                        Book bookPosition = books.get(position);
//                        if(Utils.getInstance().removeFromAlreadyRead(books.get(position))){
//                            Toast.makeText(mContext, bookName + " removed", Toast.LENGTH_SHORT).show();
//                            notifyDataSetChanged();
//                        }else{
//                            Toast.makeText(mContext, "Something wrong happened, Please try again", Toast.LENGTH_SHORT).show();
//                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete " + bookName + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeFromAlreadyRead(bookPosition, new Utils.OnSharedPreferenceDeletedCallback() {
                                    @Override
                                    public void onDeleted() {
                                        setBooks(Utils.getInstance(mContext).getAlreadyReadBooks());
                                    }
                                })) {
                                    Toast.makeText(mContext, bookName + " removed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }
        } else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelativeLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView parent;
        private final ImageView imgBook;
        private final TextView txtName;

        private final ImageView downArrow;
        private final ImageView upArrow;
        private final RelativeLayout expandedRelativeLayout;
        private final TextView txtAuthor;
        private final TextView txtDescription;

        private final TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            txtName = itemView.findViewById(R.id.txtBookNames);
            imgBook = itemView.findViewById(R.id.imgBooks);

            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRelativeLayout = itemView.findViewById(R.id.expandedRelativeLayout);
            txtAuthor = itemView.findViewById(R.id.txtAuthors);
            txtDescription = itemView.findViewById(R.id.txtShortDescs);

            btnDelete = itemView.findViewById(R.id.btnDelete);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

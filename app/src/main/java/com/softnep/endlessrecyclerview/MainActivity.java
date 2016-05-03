package com.softnep.endlessrecyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvItems = (RecyclerView) findViewById(R.id.rvContacts);
        final List<Contact> allContacts = Contact.createContactsList(10, 0);
        final ContactsAdapter adapter = new ContactsAdapter(allContacts);
        rvItems.setAdapter(adapter);


        //these lines have been added to allow git to track changes to the project directory
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        final StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rvItems.setLayoutManager(gridLayoutManager);
        rvItems.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                List<Contact> moreContacts = Contact.createContactsList(10, page);
                int curSize = adapter.getItemCount();
                allContacts.addAll(moreContacts);
                adapter.notifyItemRangeInserted(curSize, allContacts.size() - 1);
            }
        });
    }


}
package fr.smartrecruit.view.fragments;

import android.os.Bundle;
import android.widget.ListView;

import fr.smartrecruit.R;

public class AdvicesFragment {
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
    }

    private void setContentView(int activity_main) {
    }
}
}

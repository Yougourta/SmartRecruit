package fr.smartrecruit.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fr.smartrecruit.R;
import fr.smartrecruit.view.activities.MainActivity;

public class AdvicesAdapter {
    private ListView mListView;
    private String[] prenoms = new String[]{
            "conseil1","conseil2", "conseil3"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, prenoms);
        mListView.setAdapter(adapter);
    }

    private void setContentView(int activity_main) {
    }
}
}


package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CustomRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        ReallyCoolSQLiteOpenHelper db = ReallyCoolSQLiteOpenHelper.getInstance(this);
        List<ItemObject> shoppingList = db.getAllAsList();

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new CustomRecyclerViewAdapter(shoppingList);
        mRecyclerView.setAdapter(mAdapter);

        handleIntent(getIntent());



//        recyclerView.setAdapter(new CustomRecyclerViewAdapter(list));

//        List<ItemObject> list = ReallyCoolSQLiteOpenHelper.getInstance(this).getAllAsList();
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this,MainActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent){
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            List<ItemObject> itemsISearchedFor = ReallyCoolSQLiteOpenHelper.getInstance(this).searchForItems(query);
            if(!query.equals("")) {
                mAdapter.replaceData(itemsISearchedFor);
            }
        }
    }
}

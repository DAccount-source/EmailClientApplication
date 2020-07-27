package com.akanksha.emailclientapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity implements DrawerItemRecyclerViewAdapter.ClickListener {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerRecyclerView;
    Toolbar toolbar;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerRecyclerView = (RecyclerView) findViewById(R.id.left_drawer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mDrawerRecyclerView.setLayoutManager(linearLayoutManager);

        toolbar = findViewById(R.id.toolbar);


        DrawerItemRecyclerViewAdapter adapter = new DrawerItemRecyclerViewAdapter(mNavigationDrawerItemTitles, this);
        mDrawerRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

        setTitle(getString(R.string.app_name));

    }

    @Override
    public void onItemClicked(int position) {
        selectItem(position);
    }

    private void selectItem(int position) {

        Fragment fragment = null;

        toastMsg(position);
        setTitle(mNavigationDrawerItemTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerRecyclerView);

        switch (position) {
            case 0:
//                fragment = new ConnectFragment();
                break;
            case 1:
//                fragment = new FixturesFragment();
                break;
            case 2:
//                fragment = new TableFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        toolbar.setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(
                this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    private void toastMsg(int position){
        Toast.makeText(this, mNavigationDrawerItemTitles[position], Toast.LENGTH_SHORT).show();
    }

}
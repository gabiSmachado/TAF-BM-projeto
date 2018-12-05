package com.example.taf.taf_bm.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.taf.taf_bm.R;
import com.example.taf.taf_bm.helpers.ExpandableListAdapter;
import com.example.taf.taf_bm.model.MenuModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        //tx.replace(R.id.main_content_fragment, new FragmentMenu1());
        tx.commit();
        TextView textView = findViewById(R.id.description);
        textView.setText("Simule seu Teste de Aptidão Física, com o Simulador TAF BM.\n\n" +
                         "Baseado nos testes aplicados na Brigada Militar do Rio Grande do Sul.");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass = null;
        int id = item.getItemId();

        if(id == R.id.home){
            fragmentClass = MainActivity.class;
        } else if (id == R.id.taf) {
           // fragmentClass = FragmentMenu2.class;
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_content_fragment, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareMenuData() {
        MenuModel menuModel = new MenuModel("Início", true, false, R.drawable.ic_home_black);
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("TAF", true, true,R.drawable.ic_directions_run_black);
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Normal", false, false,0);
        childModelsList.add(childModel);

        childModel = new MenuModel("Aluno", false, false,0);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel("AFE", true, true,R.drawable.ic_directions_walk_black);
        headerList.add(menuModel);
        childModelsList = new ArrayList<>();
        childModel = new MenuModel("Lesão Membros Sup.", false, false,0);
        childModelsList.add(childModel);

        childModel = new MenuModel("Lesão Membros Inf.", false, false,0);
        childModelsList.add(childModel);

        childModel = new MenuModel("Lesão Membros Sup. e Inf.", false, false,0);
        childModelsList.add(childModel);

        childModel = new MenuModel("Lesão na Coluna Vertebral", false, false,0);
        childModelsList.add(childModel);

        childModel = new MenuModel("Lesão Memb. Sup. e Coluna", false, false,0);
        childModelsList.add(childModel);

        childModel = new MenuModel("Lesão Memb. Inf. e Coluna", false, false,0);
        childModelsList.add(childModel);

        childModel = new MenuModel("Problemas Cardiacos", false, false,0);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel("Sobre", true, false, R.drawable.ic_info_outline_black);
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }


    }

    private void populateExpandableList() {
        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Fragment fragment = null;
                Class fragmentClass = null;
                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        switch (headerList.get(groupPosition).menuName){
                            case "Início":
                                fragmentClass = HomeFragment.class ;
                                break;
                            case "Sobre":
                                fragmentClass = InformationActivity.class ;
                                break;
                        }
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (fragment != null) {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.main_content_fragment, fragment);
                            ft.commit();
                        }

                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        //expandableListView.collapseGroup(groupPosition);
                        onBackPressed();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    Fragment fragment = null;
                    Class fragmentClass = null;
                    Bundle bundle = new Bundle();
                    switch (model.menuName){
                        case "Normal":
                            fragmentClass = TafActivity.class;
                            bundle.putString("test","TAF Normal");
                            bundle.putInt("test_type",1);
                            break;
                        case "Aluno":
                            fragmentClass = TafActivity.class;
                            bundle.putString("test","TAF Aluno");
                            bundle.putInt("test_type",2);
                            break;
                        case "Lesão Membros Sup.":
                            fragmentClass = UpperLimbInjuriesActivity.class;
                            break;
                        case "Lesão Membros Inf.":
                            fragmentClass = LowerLimbInjuriesActivity.class;
                            break;
                        case "Lesão Membros Sup. e Inf.":
                            fragmentClass = SitupsAndMiles.class;
                            break;
                        case "Lesão na Coluna Vertebral":
                            fragmentClass = MilesActivity.class;
                            bundle.putString("test","AFE Lesão na Coluna Vertebral");
                            break;
                        case "Lesão Memb. Sup. e Coluna":
                            fragmentClass = MilesActivity.class;
                            bundle.putString("test","AFE Lesão Memb. Sup. e Coluna");
                            break;
                        case "Lesão Memb. Inf. e Coluna":
                            fragmentClass = MilesActivity.class;
                            bundle.putString("test","AFE Lesão Memb. Inf. e Coluna");
                            break;
                        case "Problemas Cardiacos":
                            fragmentClass = HeartProblemsActivity.class;
                            break;
                    }

                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (fragment != null) {
                        fragment.setArguments(bundle);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.main_content_fragment, fragment);
                        ft.commit();
                    }

                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    //expandableListView.collapseGroup(groupPosition);
                    onBackPressed();
                }
                return false;
            }
        });
    }
}



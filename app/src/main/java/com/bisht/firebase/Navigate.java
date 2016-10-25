package com.bisht.firebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Navigate extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);


        FragmentManager a= getSupportFragmentManager();
        a.beginTransaction().replace(R.id.content_frame,new BlankFragment()).commit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void alpha(View view)
    {   Intent chooser=null;

        if(view.getId()==R.id.shutdown)
        {
            Intent intent =new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));

            intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            String[] to={"messi040895@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            intent.putExtra(Intent.EXTRA_SUBJECT,"shutdown");
            intent.putExtra(Intent.EXTRA_TEXT,"pc shutdownuvyviyvyviyv kro bhai ");
            intent.setType("message/rfc822");
            try {
                startActivity(intent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Navigate.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
            //Intent.createChooser(intent,"Send Email");
            //startActivity(intent);
        }
        if(view.getId()==R.id.loggoff)
        {
            Intent intent =new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));

            intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            String[] to={"messi040895@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            intent.putExtra(Intent.EXTRA_SUBJECT,"logoff");
            intent.putExtra(Intent.EXTRA_TEXT,"pc logoff ho jaa ");
            intent.setType("message/rfc822");
            try {
                startActivity(intent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Navigate.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
            //Intent.createChooser(intent,"Send Email");
            //startActivity(intent);
        }
        if(view.getId()==R.id.sendEmail)
        { String url = "http://192.168.43.158:8000/?q=";
            Intent i = new Intent();
            i.setPackage("com.android.chrome");
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

            //Intent.createChooser(intent,"Send Email");
            //startActivity(intent);
        }

    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_camera) {

            Intent i = new Intent(Navigate.this, Chat_main_activity.class);
            startActivity(i);

            //fragmentManager.beginTransaction().replace(R.id.content_frame,new BlankFragment()).commit();
        } else if (id == R.id.nav_gallery) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new BlankFragment2()).commit();

        } else if (id == R.id.nav_slideshow){



        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

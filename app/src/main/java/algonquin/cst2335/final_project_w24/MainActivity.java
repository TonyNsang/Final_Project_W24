package algonquin.cst2335.final_project_w24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import algonquin.cst2335.final_project_w24.Dictionary.DictionaryActivity;
import algonquin.cst2335.final_project_w24.SunApp.SunActivity;
import algonquin.cst2335.final_project_w24.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        Toolbar toolbar = variableBinding.myToolbar;
        setSupportActionBar(toolbar);


        Log.w("MainActivity", "In OnCreate()-widget");
        Button sunBtn = variableBinding.sunButton;
        Button recipeBtn = variableBinding.recipeButton;
        Button dictionaryBtn = variableBinding.dictionaryButton;
        Button songBtn = variableBinding.deezerButton;
        //SunRise App Activity
        sunBtn.setOnClickListener(click -> {
            Intent songPage = new Intent(MainActivity.this, SunActivity.class);
            startActivity(songPage);
        });
        //Recipe App activity
        /*recipeBtn.setOnClickListener(click->{
            Intent recipePage = new Intent(MainActivity.this, RecipeFinder.class);
            startActivity(recipePage);
        });*/
        //Dictionary App Activity
        dictionaryBtn.setOnClickListener(click -> {
            Intent dictionaryPage = new Intent(MainActivity.this, DictionaryActivity.class);
            startActivity(dictionaryPage);
        });
        //DeezerSong App Activity
        /*songBtn.setOnClickListener(click -> {
            Intent songPage = new Intent(MainActivity.this, SongSearch.class);
            startActivity(songPage);
        });*/

    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId() == R.id.dictionaryIcon) {
            Intent dictionaryPage = new Intent(MainActivity.this, DictionaryActivity.class);
        startActivity(dictionaryPage);
            return true;
//        } else if (item.getItemId() == R.id.sunIcon) {
//
//        } else if (item.getItemId() == R.id.sunIcon) {
//            Intent songPage = new Intent(MainActivity.this, SongSearch.class);
//            startActivity(songPage);
//            return true;
        }else {

        }
        return false;
    }

}
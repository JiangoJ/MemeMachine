package com.example.review1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ImageViewCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Field to hold the roll result text
    TextView rollResult;

    //Field to hold # of rolls
    TextView rollCount;

    //Field to hold roll button
    Button rollButton;

    //Field to hold score
    int score;

    //Field to hold random dice value
    Random rand;

    //Fields to hold Dice
    int die1;
    int die2;
    int die3;

    //Fields for Tiers
    TextView tier1;
    TextView tier2;
    TextView tier3;
    TextView tier4;
    TextView tier5;
    TextView tier6;

   //Fields for tier scores
    int t1 =0,t2 =0 ,t3 = 0,t4 = 0 ,t5 = 0,t6=0;

    //roll Number
    int rollNumber;






    //Field to hold ScoreText
    TextView scoreText;

    //ArrayList to hold all 3 dice values
    ArrayList<Integer> dice;

    //Arraylist to hold all images
    ArrayList<ImageView> diceImageViews;

    //ArrayList to hold all tiers
    ArrayList<TextView> tierList;

    //ArrayList to hold tierlistCounts
    ArrayList<Integer> tierCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);

            }
        });


        //set initial score
        score = 0;

        rollNumber = 0;

        //Assigning views to fields
        rollResult = (TextView) findViewById(R.id.rollResult);
        //rollButton = (Button) findViewById(R.id.rollButton);
        scoreText = (TextView) findViewById(R.id.scoreText);
        rollCount = (TextView) findViewById(R.id.rollCount);


        tier1 = (TextView) findViewById(R.id.Tier1);
        tier2 = (TextView) findViewById(R.id.Tier2);
        tier3 = (TextView) findViewById(R.id.Tier3);
        tier4 = (TextView) findViewById(R.id.Tier4);
        tier5 = (TextView) findViewById(R.id.Tier5);
        tier6 = (TextView) findViewById(R.id.Tier6);



        //Initialize the RNG
        rand = new Random();

        //Create ArrayList container for dice values
        dice = new ArrayList<Integer>();

        //Create arraylist container for tier list
        tierList = new ArrayList<TextView>();

        tierCounts = new ArrayList<Integer>();


        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);

        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);

        tierList.add(tier1);
        tierList.add(tier2);
        tierList.add(tier3);
        tierList.add(tier4);
        tierList.add(tier5);
        tierList.add(tier6);

        tierCounts.add(t1);
        tierCounts.add(t2);
        tierCounts.add(t3);
        tierCounts.add(t4);
        tierCounts.add(t5);
        tierCounts.add(t6);

    }

    public void rollDice(View v)
    {
        rollNumber++;

        rollResult.setText("Clicked");


        //roll Dice
        die1 = rand.nextInt(6)+1;
        die2 = rand.nextInt(6)+1;
        die3 = rand.nextInt(6)+1;

        //set dice values into an ArrayList
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for(int dieOfSet = 0; dieOfSet<3; dieOfSet++)
            {

                //Choosing the correct picture
                String imageName = "die_" + dice.get(dieOfSet) + ".png";

                //Holds the index for the tiers given a dice
                int tierIndex = dice.get(dieOfSet) -1;

                //temporary value to hold count
                int countTemp = tierCounts.get(tierIndex);
                countTemp ++;
                tierCounts.set(tierIndex, countTemp);



                //System.out.println(tierCounts.get(dice.get(dieOfSet-1)));

                //String that will replace the Tier Count textView
                String newScore = "Tier " + dice.get(dieOfSet) + ": " + tierCounts.get(tierIndex);

                tierList.get(tierIndex).setText(newScore);

                try{

                    //Accesses the assets folder
                    InputStream stream = getAssets().open(imageName);
                    Drawable d = Drawable.createFromStream(stream,null);
                    diceImageViews.get(dieOfSet).setImageDrawable(d);

                }catch (IOException e){
                    e.printStackTrace();
                }


            }










        //String statement that displays how many points you get
        String msg;

        if(die1 == die2 && die2 == die3)
        {
            int scorePlus = die1 * 100;
            score += scorePlus;
            msg = "You're a lucky bitch. You get " + scorePlus + " points...";
        }

        else if(die1 == die2 || die2 == die3 || die1 == die3)
        {
            int scorePlus = 50;
            score += scorePlus;
            msg = "Good shit. You get " + scorePlus + " points...";


        }

        else{
            msg = "You suck. 0 points...";
        }







        //msg = "You rolled a " + die1 + " " + die2 + " " +  die3;
        //Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();




        scoreText.setText("Score: " + score);
        rollResult.setText(msg);
        rollCount.setText("Rolls: " + rollNumber);



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}

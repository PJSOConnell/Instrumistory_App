package com.example.pj.instrumistory;



import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

public class InstrumentActivity extends AppCompatActivity {
    public MediaPlayer mp = null;
    private boolean isInFront;
    public int refID;
    public int play = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.instrument_toolbar);
        setSupportActionBar(myToolbar);


        Intent i = getIntent();


        int[] drumsong = {R.raw.artistdeadpulse_tracksynco,R.raw.artistdeadpulse_trackdeadweight,R.raw.artistdeadpulse_trackdrum_bass_jigsaw,R.raw.artistnone_trackstraight_four};

        int[] guitarsong = {R.raw.artistdeadpulse_track7_string_riff,R.raw.artistdeadpulse_trackimperia_lead,R.raw.artistnone_trackchords,R.raw.artistnone_trackprogression_in_d,R.raw.artistnone_trackstrat};

        int[] basssong = {R.raw.artistdeadpulse_trackfretless,R.raw.artistnone_trackbassline,R.raw.artistnone_trackfunkybass,R.raw.artistnone_trackstraight_bassline};

        int[] pianosong = {R.raw.artistdeadpulse_trackpiano_and_guitar,R.raw.artistnone_trackblues_piano,R.raw.artistnone_trackblues_progression,R.raw.artistnone_trackclassical_piece};
        String[] facttext = null; //10 random facts - this is dynamic.

        int[] songres = null;

        final TextView info = (TextView) findViewById(R.id.information);

        //Get the intent from the previous screen and manipulates it.

        int position = i.getExtras().getInt("id");

        refID = position;

        if (position == 0) {//Image ID position 0 - Drums
            facttext = getResources().getStringArray(R.array.drumfacts);
            songres = drumsong;

                info.setText(getResources().getString(R.string.drum_info));
                getSupportActionBar().setTitle(R.string.drumtitle);

        } else if (position == 1) {//Image Position 1 - Electric Guitar
            facttext = getResources().getStringArray(R.array.guitarfacts);
            songres = guitarsong;

                info.setText(getResources().getString(R.string.electric_info));
                getSupportActionBar().setTitle(R.string.electrictitle);

        } else if (position == 2) {//Image Position 2 - Bass Guitar
            facttext = getResources().getStringArray(R.array.bassfacts);
            songres = basssong;

                info.setText(getResources().getString(R.string.bass_info));
                getSupportActionBar().setTitle(R.string.basstitle);

        } else if (position == 3) {//Image Position 3 - Piano
            facttext = getResources().getStringArray(R.array.pianofacts);
            songres = pianosong;

            info.setText(getResources().getString(R.string.piano_info));
            getSupportActionBar().setTitle(R.string.pianotitle);

        }

        final String[] setfacttext = facttext;

            String randomStr = facttext[new Random().nextInt(facttext.length)];//Picks a random string from the
            // facts array.



        final int[] resID = songres;


        final TextView fact = (TextView) findViewById(R.id.fact);


            fact.setText(randomStr);//Sets the TextView to whatever the random string is.


        final Button refresher = (Button) findViewById(R.id.refresh);

            refresher.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    int j = (int) (Math.random() * setfacttext.length);

                    String random = setfacttext[new Random().nextInt(setfacttext.length)];


                    fact.setText(random);


                }
            });




        final TextView songtitle = (TextView) findViewById(R.id.songtitle);//This is referred to
        //in an onClick method, changes the TextView to track name.

        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.instrument);

            imageView.setImageResource(imageAdapter.mThumbIds[position]);

        final Button music = (Button) findViewById(R.id.playmusic);


            music.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    if (play == resID.length) {
                        play = 0;
                    }

                    //int j = (int) (Math.random()*resID.length);//Picks a random resID

                    final int songID = resID[play];//Assigns it to songID

                    String resname = null;
                    String trackname = null;

                    if (mp == null) {
                        mp = MediaPlayer.create(getApplicationContext(), songID);
                        songOver();
                        mp.start();



                        resname = getResources().getString(songID);//Plucks String from ResID

                        int x = resname.lastIndexOf('/');
                        int y = resname.lastIndexOf('.');

                        trackname = resname.substring(x + 1, y);
                        trackname = trackname.replaceAll("_", " ");
                        trackname = trackname.replaceAll("artist", "Artist: ");
                        trackname = trackname.replaceAll("track", "Track: ");
                        trackname = trackname.replaceAll("ttng", "TTNG");

                        StringBuffer res = new StringBuffer();

                        String[] strArr = trackname.split(" ");

                        for (String str : strArr) {
                            char[] stringArray = str.trim().toCharArray();
                            stringArray[0] = Character.toUpperCase(stringArray[0]);
                            str = new String(stringArray);

                            res.append(str).append(" ");
                        }

                        trackname = res.toString();
                        int order = play + 1;

                        songtitle.setText("Track " + order + " - " + trackname);
                        Toast.makeText(getApplicationContext(), "NOW PLAYING - " + trackname, Toast.LENGTH_SHORT)
                                .show();
                        Button player = (Button) findViewById(R.id.playmusic);
                        player.setText("Stop Music");
                        if (play < resID.length) {
                            play++;
                        }


                    } else {
                        stopPlaying();

                    }

                } // END onClick()
            }); //Ends the button

    }

    public void homePageReturn(View view) {
        Intent intentHome = new Intent(this, HomePageActivity.class);
        startActivity(intentHome);
    }

    public void goToMap(View view) {

        Intent intentMap = new Intent(this, MapsActivity.class);
        intentMap.putExtra("id", refID);
        startActivity(intentMap);
    }




    public void stopPlaying() {
        if (mp != null) {
            TextView stop = (TextView) findViewById(R.id.songtitle);
            Button player = (Button) findViewById(R.id.playmusic);
            player.setText("Next Track");
            stop.setText("Nothing is playing right now.");
            mp.stop();
            mp.release();
            mp = null;

        }
    }

    public void songOver() {
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlaying();
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        isInFront = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isInFront = false;
    }

    @Override
    public void onStop() {//If current activity is not in focus, turn off Media Player.
        if(!isInFront) {
            stopPlaying();
            super.onStop();
        }
    }


}



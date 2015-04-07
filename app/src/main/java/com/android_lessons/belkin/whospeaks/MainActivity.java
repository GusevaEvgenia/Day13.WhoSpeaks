package com.android_lessons.belkin.whospeaks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;
    private int mCatSound, mChickenSound, mCowSound, mDogSound, mDuckSound, mSheepSound;
    private int mCountLoadedSound;
    private Context mContext;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Кто сказал мяу?");

        mContext = this;

        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        mAssetManager = getAssets();

        // получим идентификаторы
        mCatSound = loadSound("cat.ogg");
        mChickenSound = loadSound("chicken.ogg");
        mCowSound = loadSound("cow.ogg");
        mDogSound = loadSound("dog.ogg");
        mDuckSound = loadSound("duck.ogg");
        mSheepSound = loadSound("sheep.ogg");

        ImageButton cowImageButton = (ImageButton) this.findViewById(R.id.imageButtonCow);
        cowImageButton.setOnClickListener(onClickListener);

        ImageButton chickenImageButton = (ImageButton) this.findViewById(R.id.imageButtonChicken);
        chickenImageButton.setOnClickListener(onClickListener);

        ImageButton catImageButton = (ImageButton) this.findViewById(R.id.imageButtonCat);
        catImageButton.setOnClickListener(onClickListener);

        ImageButton duckImageButton = (ImageButton) this.findViewById(R.id.imageButtonDuck);
        duckImageButton.setOnClickListener(onClickListener);

        ImageButton sheepImageButton = (ImageButton) this.findViewById(R.id.imageButtonSheep);
        sheepImageButton.setOnClickListener(onClickListener);

        ImageButton dogImageButton = (ImageButton) this.findViewById(R.id.imageButtonDog);
        dogImageButton.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageButtonCow:
                    playSound(mCowSound);
                    break;
                case R.id.imageButtonChicken:
                    playSound(mChickenSound);
                    break;
                case R.id.imageButtonCat:
                    playSound(mCatSound);
                    break;
                case R.id.imageButtonDuck:
                    playSound(mDuckSound);
                    break;
                case R.id.imageButtonSheep:
                    playSound(mSheepSound);
                    break;
                case R.id.imageButtonDog:
                    playSound(mDogSound);
                    break;
            }
        }
    };

    private void playSound(int sound) {
        if (sound > 0)
            mSoundPool.play(sound, 1, 1, 1, 0, 1);
    }


    private int loadSound(String fileName) {
        AssetFileDescriptor afd = null;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
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

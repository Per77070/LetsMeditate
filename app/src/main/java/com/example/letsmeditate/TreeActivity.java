package com.example.letsmeditate;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class TreeActivity extends AppCompatActivity {

    SeekBar posistionBar;
    SeekBar volumeBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;
    MediaPlayer mp;
    int totalTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);


        elapsedTimeLabel = (TextView)findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = (TextView)findViewById(R.id.remainingTimeLabel);

    mp = MediaPlayer.create(this,R.raw.happytree);
    mp.setLooping(true);
    mp.seekTo(0);
    mp.setVolume(0.5f,0.5f);
    totalTime = mp.getDuration();

    posistionBar = (SeekBar) findViewById(R.id.positionBar);
    posistionBar.setMax(totalTime);
posistionBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
if(fromUser){
    mp.seekTo(progress);
    posistionBar.setProgress(progress);


}
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
});





volumeBar = (SeekBar) findViewById(R.id.volumeBar);
volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float volumeNum = progress /100f;
    mp.setVolume(volumeNum,volumeNum);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
});


new Thread(new Runnable() {
    @Override
    public void run() {
        while (mp!=null){
            try{
Message msg = new Message();
msg.what = mp.getCurrentPosition();
handler.sendMessage(msg);
Thread.sleep(1000);

            }catch (InterruptedException e){

            }
        }
    }
}).start();

    }

    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int currentPosition = msg.what;
            posistionBar.setProgress(currentPosition);
            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = createTimeLabel(currentPosition);

            remainingTimeLabel.setText(remainingTime);
        }
    };

    private String createTimeLabel(int time) {

        String timeLabel = "";

        int min = time/1000/60;
        int sec = time/1000;

        timeLabel = min + ":";
        if (sec<10) timeLabel += "0";


        timeLabel+= sec;
        return timeLabel;
    };

    public void playButton(View view) {

        if(!mp.isPlaying()){

mp.start();
        }else{

            mp.pause();


        }

    }
}

package ca.georgebrown.game2011.CasinoSlots;

import android.app.Activity;
import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class PlayActivity extends Activity {

    private Paint paint;
    private Canvas gameCanvas;
    private Bitmap gameBitmap;
    private ImageView gameFrame;
    private TextView cashText;
    private TextView betText;
    private int winnings;
    private int playerBet = 1;
    private int playerMoney = 200;
    private int aljons;
    private int josephs;
    private int kyles;
    private int michaels;
    private int denniss;
    private int jamess;
    private int bridgettes;

    int[] personWeight = {1, 2, 4, 8, 16, 32, 32};// the odds of getting each person out of the total

    private ImageView reel_one;
    private ImageView reel_two;
    private ImageView reel_three;
    private ImageView[] reels = {reel_one, reel_two, reel_three};

    private Bitmap aljonBitmap;
    private Bitmap bridgetteBitmap;
    private Bitmap dennisBitmap;
    private Bitmap jamesBitmap;
    private Bitmap josephBitmap;
    private Bitmap kyleBitmap;
    private Bitmap michaelBitmap;
    Bitmap[] bitmapPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //add images
        aljonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aljon);
        bridgetteBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bridgette);
        dennisBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dennis);
        jamesBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.james);
        josephBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.joseph);
        kyleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kyle);
        michaelBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.michael);

        //add texts
        cashText = (TextView) findViewById(R.id.cashText);
        cashText.setTextColor(Color.RED);
        cashText.setText("Cash"+Integer.toString(playerMoney));
        betText = (TextView) findViewById(R.id.betText);
        betText.setTextColor(Color.RED);
        betText.setText("Bet: "+Integer.toString(playerBet));


        reel_one = (ImageView) findViewById(R.id.reel);
        reel_two = (ImageView) findViewById(R.id.reel2);
        reel_three = (ImageView) findViewById(R.id.reel3);
        reels[0] = reel_one;
        reels[1] = reel_two;
        reels[2] = reel_three;

        gameFrame = (ImageView) findViewById(R.id.canvasImageView);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        gameBitmap = Bitmap.createBitmap(width+500, height+500, Bitmap.Config.ARGB_8888);
        gameCanvas = new Canvas(gameBitmap);

        paint = new Paint();
        gameCanvas.drawColor(Color.BLACK);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);

        gameFrame.setImageBitmap(gameBitmap);


        //Buttons
        Button Bet1ButtonVariable = (Button) findViewById(R.id.betBut);
        Bet1ButtonVariable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playerBet=1;
                betText.setText("Bet: "+Integer.toString(playerBet));
            }
        });

        Button bet5ButtonVariable = (Button) findViewById(R.id.betBut5);
        bet5ButtonVariable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playerBet=5;
                betText.setText("Bet: "+Integer.toString(playerBet));
            }
        });

        Button bet15ButtonVariable = (Button) findViewById(R.id.betBut15);
        bet15ButtonVariable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playerBet=15;
                betText.setText("Bet: "+Integer.toString(playerBet));
            }
        });

        Button spinButton = (Button) findViewById(R.id.SpinButton);
        spinButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(playerMoney>=playerBet)
                    Spin();
            }
        });

        Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playerMoney=200;
                cashText.setText("Cash: "+Integer.toString(playerMoney));
            }
        });

        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });


    }



    private int checkRoll() {
        int sumWeight = 0;

        for (int i = 0; i < 7; i++) {
            sumWeight += personWeight[i];
        }
        int rnd = new Random().nextInt(sumWeight);
        for (int i = 0; i < 7; i++) {
            if (rnd < personWeight[i])
                return i;
            rnd -= personWeight[i];
        }
        return 6;
    }



    private void Reels() {

        for (int spin = 0; spin < 3; spin++) {

            switch ( checkRoll()) {
                case 6  :
                    aljons++;
                    reels[spin].setImageBitmap(aljonBitmap );
                    break;
                case 5:
                    reels[spin].setImageBitmap(dennisBitmap );
                    denniss++;
                    break;
                case 3:
                    reels[spin].setImageBitmap(michaelBitmap );
                    michaels++;
                    break;
                case 2:
                    reels[spin].setImageBitmap(jamesBitmap );
                    jamess++;
                    break;
                case 4:
                    reels[spin].setImageBitmap(josephBitmap );
                    josephs++;
                    break;
                case 0:
                    reels[spin].setImageBitmap(kyleBitmap );
                    kyles++;
                    break;
                case 1:
                    reels[spin].setImageBitmap(bridgetteBitmap);
                    bridgettes++;
                    break;
            }
        }

    }
    private void determineWinnings()
    {
        if (aljons == 0) {
            if (denniss == 2) {
                winnings = playerBet * 1;
            }
            else if(josephs == 2) {
                winnings = playerBet * 2;
            }
            else if (michaels == 2) {
                winnings = playerBet * 3;
            }
            else if (jamess == 2) {
                winnings = playerBet * 5;
            }
            else if(denniss == 3) {
                winnings = playerBet * 10;
            }
            else if (josephs == 3) {
                winnings = playerBet * 20;
            }

            else if (michaels == 3) {
                winnings = playerBet * 30;
            }
            else if (jamess == 3) {
                winnings = playerBet * 40;
            }

            else if (bridgettes == 2) {
                winnings = playerBet * 70;
            }
            else if (bridgettes == 3) {
                winnings = playerBet * 150;
            }


            else if (kyles == 2) {
                winnings = playerBet * 100;
             }
            else if (kyles == 3) {
                winnings = playerBet * 500;
                Toast.makeText(getApplicationContext(), "You won the Jackpot", Toast.LENGTH_LONG).show();
            }
        }
        else
            winnings = 0;

        playerMoney += winnings;
        resetPeopleTally();
        cashText.setText("Cash: "+Integer.toString(playerMoney));
    }

    private void resetPeopleTally() {
        kyles = 0;
        bridgettes = 0;
        jamess = 0;
        aljons = 0;
        denniss = 0;
        josephs = 0;
        michaels = 0;
    }
    private void Spin() {

        playerMoney-= playerBet;
        cashText.setText("Cash: "+Integer.toString(playerMoney));
        Reels();
        determineWinnings();
    }
}

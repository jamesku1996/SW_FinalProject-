package com.jamesku.sample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.Toast;

import com.jamesku.framework.Game;
import com.jamesku.framework.Graphics;
import com.jamesku.framework.Image;
import com.jamesku.framework.Input.TouchEvent;
import com.jamesku.framework.Screen;
import com.jamesku.framework.implementation.AndroidGame;

public class MultiplayerGameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }
    public enum ConnectionType{
        Server, Client
    }

    private final Object lock = new Object();

    GameState state = GameState.Ready;
    ConnectionType connectionType;

    // Variable Setup



    public static Vector<Ball> balls = new Vector<>();

    private Image soccer,soccer2,soccer3,soccer4,soccer5,soccer6,soccer7,soccer8;
    private Animation socceranim;

    private Image sand,sand2,sand3,sand4,sand5,sand6,sand7,sand8,sand9,sand10,sand11,sand12,sand13,sand14,sand15;
    private Animation sandanim;

    private Image cool,cool2,cool3,cool4;
    private Animation coolanim;

    private   Image magic,magic2,magic3,magic4,magic5,magic6,magic7,magic8,magic9,magic10,magic11,magic12,magic13;
    private   Image magic14,magic15,magic16,magic17,magic18,magic19,magic20,magic21;
    private Image windowsXP;
    private  Animation magicanim;

    private Image basketball,basketball2,basketball3,basketball4,basketball5,basketball6,basketball7,basketball8,basketball9,basketball10;
    private Image basketball11,basketball12,basketball13,basketball14,basketball15;
    private Animation basketballanim;
    private Image soc,soc3,soc4,soc5,soc6,soc7,soc8,soc9,soc10;
    private Animation socanim;
    private Image pokemon1,pokemon2,pokemon3,pokemon4,pokemon5,pokemon6,pokemon7,pokemon8,pokemon9,pokemon10;
    private Image pokemon11,pokemon12,pokemon13,pokemon14,pokemon15,pokemon16,pokemon17,pokemon18,pokemon19,pokemon20;
    private Image pokemon21,pokemon22,pokemon23,pokemon24,pokemon25,pokemon26,pokemon27,pokemon28,pokemon29,pokemon30;
    private Animation pokenmonanim;


    private static int psudoTime;



    int livesLeft = 1;
    Paint paint, paint2;

    private int gravity;
    private int addBallFreq;
    private int addBallCounter;
    private int score;
    private int HP;
    final private int MAX_HP = 15;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    ServerSocket serverSocket;
    Socket client;

    public MultiplayerGameScreen(Game game, Socket socket, ConnectionType conType) {
        super(game);

        this.connectionType = conType;
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        psudoTime = 0;

        gravity = 1;
        addBallFreq = 5;
        addBallCounter = 0;
        score = 0;
        HP = MAX_HP;

        // Initialize game objects here

        soccer = Assets.soccer;
        soccer2 = Assets.soccer2;
        soccer3 = Assets.soccer3;
        soccer4 = Assets.soccer4;
        soccer5 = Assets.soccer5;
        soccer6 = Assets.soccer6;
        soccer7 = Assets.soccer7;
        soccer8 = Assets.soccer8;

        socceranim = new Animation();
        socceranim.addFrame(soccer,100);
        socceranim.addFrame(soccer2,100);
        socceranim.addFrame(soccer3,100);
        socceranim.addFrame(soccer4,100);
        socceranim.addFrame(soccer5,100);
        socceranim.addFrame(soccer6,100);
        socceranim.addFrame(soccer7,100);
        socceranim.addFrame(soccer8,100);

        sand = Assets.sand;
        sand2 = Assets.sand2;
        sand3 = Assets.sand3;
        sand4 = Assets.sand4;
        sand5 = Assets.sand5;
        sand6 = Assets.sand6;
        sand7 = Assets.sand7;
        sand8 = Assets.sand8;
        sand9 = Assets.sand9;
        sand10 = Assets.sand10;
        sand11 = Assets.sand11;
        sand12 = Assets.sand12;
        sand13 = Assets.sand13;
        sand14 = Assets.sand14;
        sand15 = Assets.sand15;

        sandanim = new Animation();
        sandanim.addFrame(sand,50);
        sandanim.addFrame(sand2,50);
        sandanim.addFrame(sand3,50);
        sandanim.addFrame(sand4,50);
        sandanim.addFrame(sand5,50);
        sandanim.addFrame(sand6,50);
        sandanim.addFrame(sand7,50);
        sandanim.addFrame(sand8,50);
        sandanim.addFrame(sand9,50);
        sandanim.addFrame(sand10,50);
        sandanim.addFrame(sand11,50);
        sandanim.addFrame(sand12,50);
        sandanim.addFrame(sand13,50);
        sandanim.addFrame(sand14,50);
        sandanim.addFrame(sand15, 50);

        cool = Assets.cool;
        cool2 = Assets.cool2;
        cool3 = Assets.cool3;
        cool4 = Assets.cool4;

        coolanim = new Animation();
        coolanim.addFrame(cool,100);
        coolanim.addFrame(cool2,100);
        coolanim.addFrame(cool3,100);
        coolanim.addFrame(cool4,100);

        magic = Assets.magic;
        magic2 = Assets.magic2;
        magic3 = Assets.magic3;
        magic4 = Assets.magic4;
        magic5 = Assets.magic5;
        magic6 = Assets.magic6;
        magic7 = Assets.magic7;
        magic8 = Assets.magic8;
        magic9 = Assets.magic9;
        magic10 = Assets.magic10;
        magic11 = Assets.magic11;
        magic12 = Assets.magic12;
        magic13 = Assets.magic13;
        magic14 = Assets.magic14;
        magic15 = Assets.magic15;
        magic16 = Assets.magic16;
        magic17 = Assets.magic17;
        magic18 = Assets.magic18;
        magic19 = Assets.magic19;
        magic20 = Assets.magic20;
        magic21 = Assets.magic21;
        windowsXP = Assets.windowsXP;

        magicanim = new Animation();
        magicanim.addFrame(magic,200);
        magicanim.addFrame(magic2,200);
        magicanim.addFrame(magic3,200);
        magicanim.addFrame(magic4,200);
        magicanim.addFrame(magic5,200);
        magicanim.addFrame(magic6, 200);
        magicanim.addFrame(magic7,200);
        magicanim.addFrame(magic8,200);
        magicanim.addFrame(magic9,200);
        magicanim.addFrame(magic10,200);
        magicanim.addFrame(magic11,200);
        magicanim.addFrame(magic12,200);
        magicanim.addFrame(magic13,200);
        magicanim.addFrame(magic14,200);
        magicanim.addFrame(magic15, 200);
        magicanim.addFrame(magic16,200);
        magicanim.addFrame(magic17,200);
        magicanim.addFrame(magic18,200);
        magicanim.addFrame(magic19,200);
        magicanim.addFrame(magic20,200);
        magicanim.addFrame(magic21,200);

        basketball = Assets.basketball;
        basketball2 = Assets.basketball2;
        basketball3 = Assets.basketball3;
        basketball4 = Assets.basketball4;
        basketball5 = Assets.basketball5;
        basketball6 = Assets.basketball6;
        basketball7 = Assets.basketball7;
        basketball8 = Assets.basketball8;
        basketball9 = Assets.basketball9;
        basketball10 = Assets.basketball10;
        basketball11 = Assets.basketball11;
        basketball12 = Assets.basketball12;
        basketball13 = Assets.basketball13;
        basketball14 = Assets.basketball14;
        basketball15 = Assets.basketball15;

        basketballanim = new Animation();
        basketballanim.addFrame(basketball,100);
        basketballanim.addFrame(basketball2,100);
        basketballanim.addFrame(basketball3,100);
        basketballanim.addFrame(basketball4,100);
        basketballanim.addFrame(basketball5,100);
        basketballanim.addFrame(basketball6,100);
        basketballanim.addFrame(basketball7,100);
        basketballanim.addFrame(basketball8,100);
        basketballanim.addFrame(basketball9,100);
        basketballanim.addFrame(basketball10,100);
        basketballanim.addFrame(basketball11,100);
        basketballanim.addFrame(basketball12,100);
        basketballanim.addFrame(basketball13,100);
        basketballanim.addFrame(basketball14,100);
        basketballanim.addFrame(basketball15,100);

        soc= Assets.soc;
        soc3= Assets.soc3;
        soc4= Assets.soc4;
        soc5= Assets.soc5;
        soc6= Assets.soc6;
        soc7= Assets.soc7;
        soc8= Assets.soc8;
        soc9= Assets.soc9;
        soc10= Assets.soc10;

        socanim = new Animation();
        socanim.addFrame(soc,100);
        socanim.addFrame(soc3,100);
        socanim.addFrame(soc4,100);
        socanim.addFrame(soc5,100);
        socanim.addFrame(soc6,100);
        socanim.addFrame(soc7,100);
        socanim.addFrame(soc8,100);
        socanim.addFrame(soc9,100);
        socanim.addFrame(soc10,100);

        pokemon1 = Assets.pokemon1;
        pokemon2 = Assets.pokemon2;
        pokemon3 = Assets.pokemon3;
        pokemon4 = Assets.pokemon4;
        pokemon5 = Assets.pokemon5;
        pokemon6 = Assets.pokemon6;
        pokemon7 = Assets.pokemon7;
        pokemon8 = Assets.pokemon8;
        pokemon9 = Assets.pokemon9;
        pokemon10 = Assets.pokemon10;
        pokemon11 = Assets.pokemon11;
        pokemon12 = Assets.pokemon12;
        pokemon13 = Assets.pokemon13;
        pokemon14 = Assets.pokemon14;
        pokemon15 = Assets.pokemon15;
        pokemon16 = Assets.pokemon16;
        pokemon17 = Assets.pokemon17;
        pokemon18 = Assets.pokemon18;
        pokemon19 = Assets.pokemon19;
        pokemon20 = Assets.pokemon20;
        pokemon21 = Assets.pokemon21;
        pokemon22 = Assets.pokemon22;
        pokemon23 = Assets.pokemon23;
        pokemon24 = Assets.pokemon24;
        pokemon25 = Assets.pokemon25;
        pokemon26 = Assets.pokemon26;
        pokemon27 = Assets.pokemon27;
        pokemon28 = Assets.pokemon28;
        pokemon29 = Assets.pokemon29;
        pokemon30 = Assets.pokemon30;
        pokenmonanim = new Animation();

        pokenmonanim.addFrame(pokemon1,100);
        pokenmonanim.addFrame(pokemon2,100);
        pokenmonanim.addFrame(pokemon3,100);
        pokenmonanim.addFrame(pokemon4,100);
        pokenmonanim.addFrame(pokemon5,100);
        pokenmonanim.addFrame(pokemon6,100);
        pokenmonanim.addFrame(pokemon7,100);
        pokenmonanim.addFrame(pokemon8,100);
        pokenmonanim.addFrame(pokemon9,100);
        pokenmonanim.addFrame(pokemon10,100);
        pokenmonanim.addFrame(pokemon11,100);
        pokenmonanim.addFrame(pokemon12,100);
        pokenmonanim.addFrame(pokemon13,100);
        pokenmonanim.addFrame(pokemon14,100);
        pokenmonanim.addFrame(pokemon15,100);
        pokenmonanim.addFrame(pokemon16,100);
        pokenmonanim.addFrame(pokemon17,100);
        pokenmonanim.addFrame(pokemon18,100);
        pokenmonanim.addFrame(pokemon19,100);
        pokenmonanim.addFrame(pokemon20,100);
        pokenmonanim.addFrame(pokemon21,100);
        pokenmonanim.addFrame(pokemon22,100);
        pokenmonanim.addFrame(pokemon23,100);
        pokenmonanim.addFrame(pokemon24,100);
        pokenmonanim.addFrame(pokemon25,100);
        pokenmonanim.addFrame(pokemon26,100);
        pokenmonanim.addFrame(pokemon27,100);
        pokenmonanim.addFrame(pokemon28,100);
        pokenmonanim.addFrame(pokemon29,100);
        pokenmonanim.addFrame(pokemon30,100);





        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                readMessage();
            }
        }).start();

    }

    private void readMessage() {
        while (!socket.isClosed()) {
            String line = null;
            try {
                line = reader.readLine();
                //Log.d("READ", "readMessage: " + line);
                if(line != null && line.equals("I am dead")){
                    state = GameState.GameOver;
                    break;
                }else {
                    addBallMultiPlayer(line);
                }
                // TODO deal with message you received
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void writeMessage(String message) {
        writer.println(message);
        writer.flush();
    }



    @Override
    public void update(float deltaTime) {
        psudoTime++;
        List touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins.
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List touchEvents, float deltaTime) {


        // 3. Call individual update() methods here.
        // This is where all the game updates happen.

        removeTouchedBalls(touchEvents);

        if(balls.size() == 0 && connectionType == ConnectionType.Server)
            addBalls(1);
        else if(balls.size()<10 && (score/50 > balls.size()-1) && connectionType == ConnectionType.Server){
            addBalls(1);
        }
        updateBalls();

        if(HP <= 0){
            state = GameState.GameOver;
            writeMessage("I am dead");
        }

    }

    private void addBalls(int numberOfBallsAdded) {
        for (int i = 0; i < numberOfBallsAdded; i++) {
            int randX = (int) (Math.random() * 800);
            int randSpeedX = (int) (Math.random() * 20);
            int randkind = (int) (Math.random() * 7);


            Ball newBall = new Ball(randX, 0);

            if(randkind == 0){
                newBall.setRadius(47);
                newBall.setElapsedTime(50);
                newBall.setAnimation(sandanim);
            }else if(randkind == 1){
                newBall.setRadius(18);
                newBall.setElapsedTime(25);
                newBall.setAnimation(socceranim);
            }else if(randkind == 2){
                newBall.setRadius(34);
                newBall.setElapsedTime(50);
                newBall.setAnimation(coolanim);
            }else  if(randkind == 3){
                newBall.setRadius(26);
                newBall.setElapsedTime(50);
                newBall.setAnimation(magicanim);
            }else  if(randkind == 4){
                newBall.setRadius(32);
                newBall.setElapsedTime(50);
                newBall.setAnimation(basketballanim);
            }else  if(randkind == 5){
                newBall.setRadius(37);
                newBall.setElapsedTime(50);
                newBall.setAnimation(socanim);
            }else  if(randkind == 6){
                newBall.setRadius(35);
                newBall.setElapsedTime(50);
                newBall.setAnimation(pokenmonanim);
            }



            newBall.setSpeedX(randSpeedX - 10);
            newBall.setSpeedY(0);
            newBall.setKind(randkind);

            synchronized (lock) {
                balls.add(newBall);
            }
        }
    }

    private String ballToString(Ball b){
        StringBuffer rt = new StringBuffer();
        rt.append(b.getKind());
        rt.append('_');
        rt.append(b.getCenterX());
        rt.append('_');
        rt.append(b.getSpeedX()*(-1));
        rt.append('_');
        rt.append(b.getSpeedY() * (-1));
        rt.append('_');
        rt.append(b.getRadius());

        return  rt.toString();
    }

    private void addBallMultiPlayer(String in){
        if(in == null)
            return;
        String[] token;
        token = in.split("_");
        if(token.length != 5)
            return;
        Ball b = new Ball(Integer.parseInt(token[1]),0);
        b.setSpeedX(Integer.parseInt(token[2]));
        b.setSpeedY(Integer.parseInt(token[3]));
        b.setKind(Integer.parseInt(token[0]));
        b.setRadius(Integer.parseInt(token[4]));

        if (b.getKind() == 0) {
            b.setAnimation(sandanim);
        } else if (b.getKind() == 1) {
            b.setAnimation(socceranim);
        } else if(b.getKind() == 2){
            b.setAnimation(coolanim);
        } else if(b.getKind() == 3){
            b.setAnimation(magicanim);
        }

        synchronized (lock) {
            balls.add(b);
        }
    }



    private void updateBalls(){
        synchronized (lock) {
            int len = balls.size();

            for (int i = len - 1; i >= 0; i--) {
                Ball ball = balls.get(i);
                if (!ball.isVisible()) {
                    balls.remove(i);
                    HP--;
                } else {
                    if (ball.getSpeedY() + gravity < 10)
                        ball.setSpeedY(ball.getSpeedY() + gravity);
                    else if(ball.getSpeedY() > 15)
                        ball.setSpeedY((int)(ball.getSpeedY() * 0.7) + 5);
                    else
                        ball.setSpeedY(10);


                    //ball.update();

                    ball.update();
                }
                if(ball.getCenterY()+ball.getRadius() < 0){
                    writeMessage(ballToString(ball));
                    balls.remove(i);
                }
            }
        }
    }

    private void removeTouchedBalls(List touchEvents){
        //  System.out.println(touchEvents.size());
        for (Object o: touchEvents) {
            TouchEvent event = (TouchEvent)o;
            int len = balls.size();
            for(int i = len-1; i>=0; i--){
                Ball b = balls.get(i);
                if(inCircle(event, b.getCenterX(), b.getCenterY(), b.getRadius()+5) ) {


                    b.setLastTouchTime(psudoTime);

                    if (event.type == TouchEvent.TOUCH_DOWN) {
                        //throw the ball
                       // score += 5;
                       // b.touchedBounce();
                        b.setHoldTime(1);
                        break;
                    }
                    else if(event.type == TouchEvent.TOUCH_UP){
                        if(b.getHoldTime() == 1){
                            b.touchedBounce();
                            score += 5;
                        }
                        else{
                            int ranX = (int)(Math.random() * b.getHoldTime()) - b.getHoldTime()/2;
                           b.setSpeedX(ranX);
                            if(-b.getHoldTime()*2 > -50 ){
                                b.setSpeedY(-50);
                            }
                            else
                                b.setSpeedY(-b.getHoldTime()*2);
                            score += 10;
                        }


                        b.setHoldTime(0);
                        break;
                    }

                }
                else if(inCircle(event, b.getCenterX(), b.getCenterY(), b.getRadius() + 10) &&
                        (event.type == TouchEvent.TOUCH_DRAGGED || event.type == TouchEvent.TOUCH_HOLD)){
                    b.setHoldTime(0);
                }
            }
        }

    }

    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }
    private boolean inCircle(TouchEvent event, int x, int y, int radius){
        Log.d("circlex=" + x, "inCircle: ");
        Log.d("circley=" + y, "inCircle: ");
        Log.d("x=" + event.x, "inCircle: ");
        Log.d("y=" + event.y, "inCircle: ");
        int distance2 = (event.x - x) * (event.x - x) + (event.y - y) * (event.y - y);
        if(distance2 < radius*radius) {
            Log.d("INININ", "inCircle: ");
            return true;

        }
        Log.d("OUTOUTOUT", "inCircle: ");
        return false;
    }

    private void updatePaused(List touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 200, 480, 200)) {

                    if (!inBounds(event, 0, 0, 35, 35)) {
                        resume();
                    }
                }

                if (inBounds(event, 60, 400, 360, 100)) {
                    for(Ball b : balls){
                        b.setVisible(false);
                    }
                    nullify();
                    goToMenu();
                }
            }
        }
    }

    private void updateGameOver(List touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 0, 0, 800, 480)) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }



    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        Rect r = new Rect(0, 0, 480, 800);
        g.drawImage(windowsXP, 0, 0, 0, 0, 480, 800, r);


    synchronized (lock) {
            for (Ball b : balls) {
                b.draw(g);
                //g.drawImage(socceranim.getImage(), b.getCenterX(), b.getCenterY());
            }
        }
        // First draw the game elements.


        // Example:

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();

        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }



    public void animate() {
        // anim.update(10);
        // hanim.update(50);
        //socceranim.update(50);
        //sandanim.update(25);
    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;


        sandanim = null;
        socceranim = null;
        sand = null;
        sand2 = null;
        sand3 = null;
        sand4 = null;
        sand5 = null;
        sand6 = null;
        sand7 = null;
        sand8 = null;
        sand9 = null;
        sand10 = null;
        sand11 = null;
        sand12 = null;
        sand13 = null;
        sand14 = null;
        sand15 = null;
        soccer = null;
        soccer2 = null;
        soccer3 = null;
        soccer4 = null;
        soccer5 = null;
        soccer6 = null;
        soccer7 = null;
        soccer8 = null;

        coolanim = null;
        cool = null;
        cool2 = null;
        cool3 = null;
        cool4 = null;

        magicanim = null;
        magic= null;
        magic2= null;
        magic3= null;
        magic4= null;
        magic5= null;
        magic6= null;
        magic7= null;
        magic8= null;
        magic9= null;
        magic10= null;
        magic11= null;
        magic12= null;
        magic13= null;
        magic14= null;
        magic15= null;
        magic16= null;
        magic17= null;
        magic18= null;
        magic19= null;
        magic20= null;
        magic21= null;


        balls.clear();
        ;
        // Call garbage collector to clean up memory.
        System.gc();

    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        Rect dstRect = new Rect();
        dstRect.set(0, 350, 480, 450);
        g.drawImage(Assets.taptostart, 0, 100, 0, 0, 480, 800, dstRect);

    }

    private void drawScoreHP() {
        Graphics g = game.getGraphics();

        Paint paint = new Paint();
        paint.setColor(0xFFB30059);
        paint.setTextSize(25);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        g.drawString("Score: " + score, 350, 58, paint);
        g.drawString("HP: ", 25, 58, paint);
        g.drawRect(70, 35, HP*200/MAX_HP+10, 30, 0xFFE2F5A7);
        g.drawRect(75, 40, HP*200/MAX_HP, 20, 0xFF85E61D );

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

        drawScoreHP();

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        Rect dstRect = new Rect();
        dstRect.set(0, 200, 480, 400);
        g.drawImage(Assets.resume, 0, 100, 0, 0, 480, 800, dstRect);

        dstRect.set(60, 400, 420, 500);
        g.drawImage(Assets.Menu, 100, 400, 0, 0, 480, 800, dstRect);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        Rect dstRect = new Rect();
      
        if(HP <= 0) {
            dstRect.set(0, 200, 480, 400);
            g.drawImage(Assets.gameover, 0, 100, 0, 0, 480, 800, dstRect);

        }else {
            dstRect.set(0, 200, 480, 400);
            g.drawImage(Assets.youwin, 0, 100, 0, 0, 480, 800, dstRect);
        }

        dstRect.set(100, 400, 340, 500);
        g.drawImage(Assets.taptoreturn, 100, 400, 0, 0, 480, 800, dstRect);
    }

    @Override
    public void pause() {
        if (state == GameState.Running) {
            state = GameState.Paused;
            Assets.theme.pause();
        }

    }

    @Override
    public void resume() {
        if (state == GameState.Paused) {
            state = GameState.Running;
            Assets.theme.play();
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }

    private void goToMenu() {
        // TODO Auto-generated method stub
        game.setScreen(new MainMenuScreen(game));

    }



    public static int getPsudoTime() {
        return psudoTime;
    }
}

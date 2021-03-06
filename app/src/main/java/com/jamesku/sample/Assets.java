package com.jamesku.sample;

import com.jamesku.framework.Image;
import com.jamesku.framework.Music;
import com.jamesku.framework.Sound;


public class Assets {

    public static Image menu, splash, background, character, character2, character3, heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
    public static Image tiledirt, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, characterJump, characterDown;
    public static Image button;
    public static Sound click;
    public static Music theme;
    public static Image soccer,soccer2,soccer3,soccer4,soccer5,soccer6,soccer7,soccer8;
    public static Image sand,sand2,sand3,sand4,sand5,sand6,sand7,sand8,sand9,sand10,sand11,sand12,sand13,sand14,sand15;
    public static Image cool,cool2,cool3,cool4;
    public static Image magic,magic2,magic3,magic4,magic5,magic6,magic7,magic8,magic9,magic10,magic11,magic12,magic13;
    public static Image magic14,magic15,magic16,magic17,magic18,magic19,magic20,magic21;
    public static Image title;
    public static Image oneplayer,twoplayer;
    public static Image createaroom,joinaroom;
    public static Image zero,one,two,three,four,five,six,seven,eight,nine,dot,delete,enter;
    public static Image hostip;
    public static Image waiting,waiting2,waiting3,waiting4,waiting5,waiting6,waiting7,waiting8,waiting9,waiting10,waiting11,waiting12;
    public static Image gameover,youwin,taptoreturn;
    public static Image windowsXP;
    public static Image Menu,resume,taptostart;
    public static Image basketball,basketball2,basketball3,basketball4,basketball5,basketball6,basketball7,basketball8,basketball9,basketball10;
    public static Image basketball11,basketball12,basketball13,basketball14,basketball15;
    public static Image soc,soc3,soc4,soc5,soc6,soc7,soc8,soc9,soc10;
    public static Image pokemon1,pokemon2,pokemon3,pokemon4,pokemon5,pokemon6,pokemon7,pokemon8,pokemon9,pokemon10;
    public static Image pokemon11,pokemon12,pokemon13,pokemon14,pokemon15,pokemon16,pokemon17,pokemon18,pokemon19,pokemon20;
    public static Image pokemon21,pokemon22,pokemon23,pokemon24,pokemon25,pokemon26,pokemon27,pokemon28,pokemon29,pokemon30;


    public static void load(SampleGame sampleGame) {
        // TODO Auto-generated method stub
        theme = sampleGame.getAudio().createMusic("menutheme.mp3");
        theme.setLooping(true);
        theme.setVolume(0.85f);
        theme.play();
    }

}
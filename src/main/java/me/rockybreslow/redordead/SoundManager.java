package me.rockybreslow.redordead;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import java.io.File;

public class SoundManager {
    public static final Music MENU_MUSIC = TinySound.loadMusic("sound/menu/menu_theme.ogg");

    public static final Music[] STORY = {
            TinySound.loadMusic("sound/menu/menu_1.ogg"),
            TinySound.loadMusic("sound/menu/menu_2.ogg"),
            TinySound.loadMusic("sound/menu/menu_3.ogg"),
            TinySound.loadMusic("sound/menu/menu_4.ogg"),
            TinySound.loadMusic("sound/menu/menu_5.ogg"),
            TinySound.loadMusic("sound/menu/menu_6.ogg"),
            TinySound.loadMusic("sound/menu/menu_7.ogg"),
    };
    public static final Music STORY_MUSIC = TinySound.loadMusic("sound/menu/menu_background.ogg");

    public static final Music GAME_MUSIC = TinySound.loadMusic("sound/music2.ogg");

    public static final Music END_MUSIC = TinySound.loadMusic("sound/end.ogg");

    private static final Sound[] WOOSH = {
            TinySound.loadSound("sound/woosh/woosh_1.ogg"),
            TinySound.loadSound("sound/woosh/woosh_2.ogg"),
            TinySound.loadSound("sound/woosh/woosh_3.ogg"),
            TinySound.loadSound("sound/woosh/woosh_4.ogg"),
    };
    public static void playWoosh() {
        WOOSH[(int) (Math.random() * WOOSH.length - 1)].play();
    }

    private static final Sound[] BEEP = {
            TinySound.loadSound("sound/beep/beep_1.ogg"),
            TinySound.loadSound("sound/beep/beep_2.ogg"),
            TinySound.loadSound("sound/beep/beep_3.ogg"),
            TinySound.loadSound("sound/beep/beep_4.ogg"),
            TinySound.loadSound("sound/beep/beep_5.ogg"),
            TinySound.loadSound("sound/beep/beep_6.ogg"),
            TinySound.loadSound("sound/beep/beep_7.ogg"),
            TinySound.loadSound("sound/beep/beep_8.ogg"),
    };
    public static void playBeep() {
        BEEP[(int) (Math.random() * BEEP.length - 1)].play();
    }

    private static final Sound[] DARN = {
            TinySound.loadSound("sound/darn/darn_1.ogg"),
            TinySound.loadSound("sound/darn/darn_2.ogg"),
            TinySound.loadSound("sound/darn/darn_3.ogg"),
            TinySound.loadSound("sound/darn/darn_4.ogg"),
            TinySound.loadSound("sound/darn/darn_5.ogg"),
    };
    public static void playDarn() {
        DARN[(int) (Math.random() * DARN.length - 1)].play();
    }
}

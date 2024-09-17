package com.mai.audio;

import java.io.InputStream;

public enum Music {

    CONFIG{
        public InputStream getAudio(){
            return getClass().getResourceAsStream("/com/mai/Sounds/M_CONFIG.wav");
        }
    };

    public abstract InputStream getAudio();
}

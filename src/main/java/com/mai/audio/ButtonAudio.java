package com.mai.audio;

import java.io.InputStream;

public enum ButtonAudio {

    CANCEL {
        public InputStream getAudio() {
            return getClass().getResourceAsStream("/com/mai/Menu_sounds/C_CANCEL.wav");
        }
    },
    MOVE {
        public InputStream getAudio() {
            return getClass().getResourceAsStream("/com/mai/Menu_sounds/C_MOVE.wav");
        }
    },
    SELECT {
        public InputStream getAudio() {
            return getClass().getResourceAsStream("/com/mai/Menu_sounds/C_SELECT.wav");
        }
    },
    START {
        public InputStream getAudio() {
            return getClass().getResourceAsStream("/com/mai/Menu_sounds/C_START.wav");
        }
    },
    OK {
        public InputStream getAudio() {
            return getClass().getResourceAsStream("/com/mai/Menu_sounds/C_OK.wav");
        }
    };

    public abstract InputStream getAudio();

}

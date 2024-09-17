package com.mai.enums;

public enum FXMLPart {

    MENU{
        public String getPath(){
            return "/com/mai/FxmlFiles/Game/GameMenu.fxml";
        }
    },
    GAME{
        public String getPath(){
            return "/com/mai/FxmlFiles/Game/Game.fxml";
        }
    },
    GAMEOVER {
        public String getPath(){
            return "/com/mai/FxmlFiles/Game/GameOver.fxml";
        }
    },
    GAMECONFIG{
        public String getPath(){
            return "/com/mai/FxmlFiles/Game/GameConfig.fxml";
        }
    },
    TITLEBAR{
        public String getPath(){
            return "/com/mai/FxmlFiles/Game/TitleBar.fxml";
        }
    };

    public abstract String getPath();

}

package com.mai;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import com.goxr3plus.fxborderlessscene.borderless.CustomStage;
import javafx.application.Application;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.mai.bootstrap.BootstrapData;
import com.mai.enums.FXMLPart;
import com.mai.gameinit.GameApplicationWindowed;
import com.mai.scenes.gamemenu.GameMenuController;
import com.mai.scenes.gamemenu.GameMenuScene;
import com.mai.scenes.titlebar.TitlebarScene;
import com.mai.service.AIService;
import com.mai.service.UserService;

public class JFXApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BootstrapData.loadData();

        GameApplicationWindowed gameApplicationWindowed = new GameApplicationWindowed(UserService.user, AIService.aiList);

        VBox rootBox = gameApplicationWindowed.getNewRootBox(800, 600, "com/mai/Styling/app.css", "primaryBackground");

        CustomStage customStage = gameApplicationWindowed.getNewCustomStage("TGOW", "com/mai/Images/app/icon.png");

        BorderlessScene borderlessScene = gameApplicationWindowed.getNewBorderlessScene(customStage, rootBox);

        TitlebarScene titlebarScene = gameApplicationWindowed.getNewTitleBar(customStage, borderlessScene);

        rootBox.getChildren().add(titlebarScene.getRoot());

        GameMenuScene gameMenuScene = new GameMenuScene(new GameMenuController(), FXMLPart.MENU);

        rootBox.getChildren().add(gameMenuScene.getRoot());
        VBox.setVgrow(gameMenuScene.getRoot(), Priority.ALWAYS);

        customStage.show();
    }

}

package com.mai.scenes.gameover;

import com.mai.scenes.game.logic.GameData;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import com.mai.audio.ButtonAudio;
import com.mai.audio.Sound;
import com.mai.data.User;
import com.mai.enums.GameOverType;
import com.mai.scenes.game.Parts.AvatarBox;
import com.mai.scenes.abstractscene.AbstractController;
import com.mai.audio.SoundPlayer;
import com.mai.scenes.gamemenu.GameMenuController;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOverController extends AbstractController implements Initializable {

    private final GameData gameData;
    private final GameMenuController gameMenuController;

    @FXML
    private HBox avatarContainer;

    @FXML
    private Circle playerAvatarCircle;

    @FXML
    private ImageView avatarView;

    @FXML
    private Label playerLabel;

    @FXML
    private Label winLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Button returnButton;

    public GameOverController(GameData gameData, GameMenuController gameMenuController) {
        this.gameData = gameData;
        this.gameMenuController = gameMenuController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configButton();

        if(this.gameData.getPlayerOneScore() > this.gameData.getPlayerTwoScore()){
            setWinner(gameData.getPlayer1(), String.valueOf(gameData.getPlayerOneScore()), "Has won with a score of:");
        } else if(this.gameData.getPlayerOneScore() < this.gameData.getPlayerTwoScore()) {
            SoundPlayer.playAudioFile(Sound.LOST.getAudio());
            setWinner(gameData.getPlayer2(), String.valueOf(gameData.getPlayerTwoScore()), "Has won with a score of:");
        } else {
            SoundPlayer.playAudioFile(Sound.LOST.getAudio());

            User tempUser = new User();
            tempUser.setPlayerColour("WHITE");
            tempUser.setPlayerName("GOD WON");
            tempUser.setProfilePictureUrl("/com/mai/Images/App/Spurdo.png");
            setWinner(tempUser,"" ,"Draw, you both lost");
        }

    }

    private void setWinner(User user, String score, String winText){
        setAvatar(user);
        setLabels(score, user.getPlayerName(), winText);
    }

    private void setAvatar(User user) {
        String path = user.getProfilePictureUrl();

        //idk why sometimes need trim
        InputStream stream = getClass().getResourceAsStream(path.trim());

        if(stream != null){
            avatarContainer.getChildren().add(new AvatarBox(120, new Image(stream), user.getPlayerColour()));
        } else {
            System.out.println("Couldn't find profile picture");
        }

    }

    private void setLabels(String score, String username, String winText){
        scoreLabel.setText(String.valueOf(score));
        playerLabel.setText(username);
        winLabel.setText(winText);
    }

    private void configButton(){
        returnButton.setOnMouseEntered(select());
    }

    private EventHandler<? super MouseEvent> select(){
        return (EventHandler<MouseEvent>) event -> SoundPlayer.playAudioFile(ButtonAudio.SELECT.getAudio());
    }

    @FXML
    private void returnToMain(){
        SoundPlayer.playAudioFile(ButtonAudio.OK.getAudio());
        this.gameMenuController.setGameConfigScreen();
    }

}

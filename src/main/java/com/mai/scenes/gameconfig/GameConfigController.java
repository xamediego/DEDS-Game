package com.mai.scenes.gameconfig;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.mai.audio.Music;
import com.mai.audio.MusicPlayer;
import com.mai.audio.SoundPlayer;
import com.mai.audio.ButtonAudio;
import com.mai.data.User;
import com.mai.enums.Difficulty;
import com.mai.enums.GameType;
import com.mai.parts.NumberField;
import com.mai.scenes.abstractscene.AbstractController;
import com.mai.scenes.game.logic.GameBoard;
import com.mai.scenes.game.logic.GameData;
import com.mai.scenes.game.logic.Space;
import com.mai.scenes.gamemenu.GameMenuController;
import com.mai.service.AIService;
import com.mai.service.UserService;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameConfigController extends AbstractController implements Initializable {

    private final GameMenuController gameMenuController;

    @FXML
    public Label gameInfo;

    @FXML
    private ComboBox<Difficulty> aITypes;

    @FXML
    private Button startButton;

    @FXML
    private Button startButtonNonAI;

    @FXML
    private TextField xSize;

    @FXML
    private TextField ySize;

    @FXML
    private TextField minSizeField;

    @FXML
    private TextField maxSizeField;

    MusicPlayer musicPlayer;

    public GameConfigController(GameMenuController gameMenuController) {
        this.gameMenuController = gameMenuController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureChoiceBox();
        configTextFields();
        config_m();
    }

    private void config_m() {
        InputStream stream = Music.CONFIG.getAudio();
        MusicPlayer.setMusic(stream);
        MusicPlayer.loopMusic();
    }

    private void configTextFields() {
        NumberField.makeNumberField(xSize, "([\\d])*?");
        xSize.setText("7");
        configAmountField(xSize);

        NumberField.makeNumberField(ySize, "([\\d])*?");
        ySize.setText("7");
        configAmountField(ySize);

        NumberField.makeNumberField(minSizeField, "([\\d])*?");
        minSizeField.setText("50");
        configSizeField(minSizeField);

        NumberField.makeNumberField(maxSizeField, "([\\d])*?");
        maxSizeField.setText("50");
        configSizeField(maxSizeField);
    }


    private void configAmountField(TextField numberField) {
        numberField.textProperty().addListener((observableValue, s, t1) -> startButton.setDisable(observableValue.getValue() == null || observableValue.getValue().isEmpty() || Integer.parseInt(numberField.getText()) < 7));
    }

    private void configSizeField(TextField numberField) {
        numberField.textProperty().addListener((observableValue, s, t1) -> startButton.setDisable(observableValue.getValue() == null || observableValue.getValue().isEmpty() || Integer.parseInt(numberField.getText()) < 10 || Integer.parseInt(numberField.getText()) > 150));
    }


    @FXML
    private void configureChoiceBox() {
        List<Difficulty> difficulties = List.of(Difficulty.values());
        aITypes.getItems().addAll(difficulties);

        aITypes.setCellFactory(lv -> {
            ListCell<Difficulty> cell = new ListCell<>() {
                @Override
                protected void updateItem(Difficulty item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null){
                        setText(item.name());
                    }
                }
            };

            cell.setOnMouseEntered(event -> SoundPlayer.playAudioFile(ButtonAudio.MOVE.getAudio()));

            return cell;
        });

        aITypes.getSelectionModel().selectFirst();
    }

    @FXML
    private void startGame() {
        configGame(GameType.AI);
    }

    @FXML
    private void starNonAIGame() {
        configGame(GameType.NON_AI);
    }

    private void configGame(GameType gameType){
        MusicPlayer.stopMusic();

        GameBoard gameBoard = getNewBoard();
        GameData gameData = createGameData(gameBoard);

        int minSize = Integer.parseInt(minSizeField.getText());
        int maxSize = Integer.parseInt(maxSizeField.getText());

        if (gameType == GameType.AI){
            this.gameMenuController.setAiGame(gameData, minSize, maxSize);
        } else {
            this.gameMenuController.setPlayerGame(gameData, minSize, maxSize);
        }
    }


    private GameData createGameData(GameBoard gameBoard) {
        if (UserService.user != null) return getGameData(UserService.user, gameBoard);

        User tempUser = getTempUser();

        return getGameData(tempUser, gameBoard);
    }

    private GameBoard getNewBoard() {
        int xGrooteBord = Integer.parseInt(xSize.getText());
        int yGrooteBord = Integer.parseInt(ySize.getText());

        GameBoard gameBoard = new GameBoard(xGrooteBord, yGrooteBord, new Space[xGrooteBord][yGrooteBord]);
        gameBoard.configBoard();

        return gameBoard;
    }

    private User getTempUser() {
        User tempUser = new User();

        tempUser.setPlayerName("Anon");
        tempUser.setPlayerColour("#5ef77f");
        tempUser.setProfilePictureUrl("/com/mai/Images/App/defaultProfImage.png");

        tempUser.setPlayerNumber(1);
        tempUser.setAttackDropOff(2);
        tempUser.setRange(3);

        return tempUser;
    }

    private GameData getGameData(User user, GameBoard gameBoard) {
        return new GameData(4, 4, 1, user, AIService.getAiPlayer(aITypes.getValue() , 2), gameBoard);
    }

    @FXML
    private void select() {
        SoundPlayer.playAudioFile(ButtonAudio.SELECT.getAudio());
    }

    @FXML
    private void start() {
        SoundPlayer.playAudioFile(ButtonAudio.START.getAudio());
    }

    @FXML
    private void move() {
        SoundPlayer.playAudioFile(ButtonAudio.MOVE.getAudio());
    }


}

package com.mai.scenes.game.Parts;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import com.mai.data.User;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class UserInfoBox extends VBox {

    private AvatarBox avatarBox;
    private final Label playerLabel;
    private final Label scoreLabel;
    private final Label teamLabel;

    public UserInfoBox(User user, int score, String team) {
        playerLabel = new Label(user.getPlayerName());
        scoreLabel = new Label(String.valueOf(score));
        teamLabel = new Label(team);

        this.setSpacing(5);
        this.setFillWidth(true);
        this.setAlignment(Pos.CENTER);

        configBox();
    }

    public UserInfoBox(User user, int score, String team, int size) {
        playerLabel = new Label(user.getPlayerName());
        scoreLabel = new Label(String.valueOf(score));
        teamLabel = new Label(team);

        this.setSpacing(5);
        this.setFillWidth(true);
        this.setAlignment(Pos.CENTER);

        if (user.getProfilePictureUrl() != null) {
            String path = user.getProfilePictureUrl().trim();

            InputStream stream = Objects.requireNonNull(this.getClass().getResourceAsStream(path));

            avatarBox = new AvatarBox(size, new Image(stream), user.getPlayerColour());
        } else {
            String defaultPP = "/com/mai/images/app/defaultProfImage.png";

            InputStream stream = Objects.requireNonNull(this.getClass().getResourceAsStream(defaultPP));

            avatarBox = new AvatarBox(size, new Image(stream), user.getPlayerColour());
        }

        configBox();
    }

    private void configBox() {
        if (avatarBox != null) {
            this.getChildren().add(avatarBox);
        }

        this.getChildren().add(playerLabel);
        this.getChildren().add(scoreLabel);
        this.getChildren().add(teamLabel);
    }

    public AvatarBox getAvatarBox() {
        return avatarBox;
    }

    public Label getPlayerLabel() {
        return playerLabel;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Label getTeamLabel() {
        return teamLabel;
    }

}

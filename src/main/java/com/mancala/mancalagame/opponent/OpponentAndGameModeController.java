package com.mancala.mancalagame.opponent;

import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.RadioButton;
        import javafx.stage.Stage;
        import java.io.IOException;

public class OpponentAndGameModeController {
    @FXML
    private RadioButton traditionalRadio;

    @FXML
    private RadioButton arcadeRadio;

    @FXML
    private RadioButton humanRadio;

    @FXML
    private RadioButton computerRadio;

    @FXML
    private Button startButton;

    @FXML
    private void handleGameModeSelection() {
        if (traditionalRadio.isSelected()) {
            // Call Traditional Game Mode class
            System.out.println("Traditional game mode selected");
        } else if (arcadeRadio.isSelected()) {
            // Call Arcade Game Mode class
            System.out.println("Arcade game mode selected");
        }
        // Enable start button if a game mode and opponent is selected
        startButton.setDisable(!((humanRadio.isSelected() || computerRadio.isSelected()) &&
                (traditionalRadio.isSelected() || arcadeRadio.isSelected())));
    }

    @FXML
    private void handleOpponentSelection() {
        // If human is selected, load login page
        if (humanRadio.isSelected()) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("PlayerLogin.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) humanRadio.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (computerRadio.isSelected()) {
            // Enable start button if a game mode and opponent is selected
            startButton.setDisable(!((humanRadio.isSelected() || computerRadio.isSelected()) &&
                    (traditionalRadio.isSelected() || arcadeRadio.isSelected())));
        }
    }

    @FXML
    private void handleStartGame() {
        // Start game
        System.out.println("Game started!");
    }
}

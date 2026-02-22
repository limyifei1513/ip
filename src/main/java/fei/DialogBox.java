package fei;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Visually highlights the dialog text (useful for warnings/errors/important messages).
     */
    public void errorDialogStyle() {
        // Highlight the label like a "pill" with a colored background and border.
        dialog.setStyle(
                "-fx-background-color: #ffcdcd;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #ff0000;" +
                "-fx-border-radius: 8;" +
                "-fx-padding: 6 10 6 10;"
        );
    }

    public void feiDialogStyle() {
        dialog.setStyle(
                "-fx-background-color: #90feef;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #0066ff;" +
                "-fx-border-radius: 8;" +
                "-fx-padding: 6 10 6 10;"
        );
    }

    public void userDialogStyle() {
        dialog.setStyle(
                "-fx-background-color: #42ffc0;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #33ff63;" +
                "-fx-border-radius: 8;" +
                "-fx-padding: 6 10 6 10;"
        );
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.userDialogStyle();
        return db;
    }

    public static DialogBox getFeiDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.feiDialogStyle();
        return db;
    }

    
}

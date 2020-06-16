package id322029638_id31582270.GUI;

import java.util.ArrayList;
import java.util.Optional;

import id322029638_id31582270.interfaces.ElectionUIListenable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ElectionView implements ElectionAbstractView {
	ArrayList<Node> menu = new ArrayList<>();
	StackPane bgPane = new StackPane();
	ArrayList<ElectionUIListenable> listners = new ArrayList<>();
	Button btnBack = new Button("Back");
	Stage primStage;

	public void registerListner(ElectionUIListenable listner) {
		listners.add(listner);
	}

	public void showAddedAlert(String name) {
		Alert citAdded = new Alert(AlertType.INFORMATION, name + " was added");
		citAdded.setTitle("Succesfull!");
		citAdded.setHeaderText(null);
		citAdded.showAndWait();
	}
		
	public String askAdditionalInfoString(String question) {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Asking additional information.");
		dialog.setHeaderText(question);
		dialog.setContentText("Please enter here:");
		
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	public boolean askAdditionalInfoBoolean(String opt) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Do you got "+opt);
		alert.setHeaderText("Do you got "+opt);
		alert.setContentText("Choose your option.");

		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");

		alert.getButtonTypes().setAll(buttonYes, buttonNo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonYes) {
			return true;
		} else if (result.get() == buttonNo) {
			return false;
		}
		return false;
	}

	public void showErrorAlert(String err) {
		Alert citAdded = new Alert(AlertType.ERROR, err);
		citAdded.setTitle("Failed!");
		citAdded.setHeaderText(null);
		citAdded.showAndWait();
	}

	public void changeWindowSize(int hight, int width) {
		primStage.setMinHeight(hight);
		primStage.setMinWidth(width);
		primStage.setHeight(hight);
		primStage.setWidth(width);
	}

	public ElectionView(Stage primaryStage) {
		primStage = primaryStage;
		primaryStage.setTitle("Elections");
		bgPane.setBackground(
				new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

		bgPane.getChildren().add(new CreateMenuView(listners, bgPane).getPane());
		bgPane.setMinSize(150, 150);

		primaryStage.setMinHeight(260);
		primaryStage.setMinWidth(620);
		primaryStage.setScene(new Scene(bgPane, 600, 200));
		primaryStage.show();
	}

}

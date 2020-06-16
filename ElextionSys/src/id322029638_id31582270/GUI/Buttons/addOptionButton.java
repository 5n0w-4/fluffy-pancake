package id322029638_id31582270.GUI.Buttons;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import id322029638_id31582270.GUI.StartElectionsView;
import id322029638_id31582270.GUI.AddOptions.AddBBoxView;
import id322029638_id31582270.GUI.AddOptions.AddCitizenView;
import id322029638_id31582270.GUI.AddOptions.AddPartyRepView;
import id322029638_id31582270.GUI.AddOptions.AddPartyView;
import id322029638_id31582270.GUI.ShowOptions.ShowBBoxInfo;
import id322029638_id31582270.GUI.ShowOptions.ShowCitizenInfo;
import id322029638_id31582270.GUI.ShowOptions.ShowPartyInfo;
import id322029638_id31582270.GUI.ShowOptions.ShowResultInfo;
import id322029638_id31582270.interfaces.ElectionUIListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class addOptionButton extends Button {

	public addOptionButton(String text, StackPane bgPane, ArrayList<ElectionUIListenable> listners) {
		super(text);

		this.getStylesheets().add("file:///C:/css/custBtn.css");
		this.setMaxSize(190, 25);

		this.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				bgPane.getChildren().clear();
				switch (text) {
				case "Add Citizen":
					bgPane.getChildren().add(new AddCitizenView(listners, bgPane).getPane());
					listners.get(0).changeWindowSize(250, 320);
					break;
				case "Add Ballot Box":
					bgPane.getChildren().add(new AddBBoxView(listners, bgPane).getPane());
					listners.get(0).changeWindowSize(240, 350);
					break;
				case "Add Party":
					bgPane.getChildren().add(new AddPartyView(listners, bgPane).getPane());
					listners.get(0).changeWindowSize(200, 280);
					break;
				case "Add Representative":
					bgPane.getChildren().add(new AddPartyRepView(listners, bgPane).getPane());
					listners.get(0).changeWindowSize(190, 420);
					break;
				case "Show Citizens":
					bgPane.getChildren().add(new ShowCitizenInfo(listners, bgPane).getPane());
					listners.get(0).changeWindowSize(300, 300);
					break;
				case "Show Ballot Boxes":
					bgPane.getChildren().add(new ShowBBoxInfo(listners, bgPane).getPane());
					listners.get(0).changeWindowSize(300, 350);
					break;
				case "Show Partys":
					bgPane.getChildren().add(new ShowPartyInfo(listners, bgPane).getPane());
					listners.get(0).changeWindowSize(300, 350);
					break;
				case "Show Results":
					bgPane.getChildren().add(new ShowResultInfo(listners, bgPane).getPane());
					listners.get(0).changeWindowSize(300, 350);
					break;
				case "Start Voting":
					bgPane.getChildren().add(new StartElectionsView(listners, bgPane).getPane());
					listners.get(0).changeWindowSize(205, 250);
					break;
				default:
					break;
				}

			}
		});

	}

}

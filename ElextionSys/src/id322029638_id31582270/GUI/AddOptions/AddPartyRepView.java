package id322029638_id31582270.GUI.AddOptions;

import java.util.ArrayList;

import id322029638_id31582270.GUI.Buttons.BackButton;
import id322029638_id31582270.interfaces.ElectionUIListenable;
import id322029638_id31582270.logic.Party;
import id322029638_id31582270.population.Voter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AddPartyRepView {
	VBox addViewPane;
	ArrayList<ElectionUIListenable> listners;

	public AddPartyRepView(ArrayList<ElectionUIListenable> listners, StackPane bgPane) {
		this.listners = listners;

		addViewPane = new VBox();

		Label lblTitle = new Label("Add Party Representative");
		lblTitle.setFont(new Font("Curve", 35));
		addViewPane.getChildren().add(lblTitle);

		Label lblID = new Label("ID:");
//		TextField tfID = new TextField();

		ComboBox<String> cbIDs = new ComboBox<String>();
		cbIDs.setEditable(true);
		for (ElectionUIListenable electionUIListenable : listners) {
			for (Voter voter : electionUIListenable.viewAsksGetCitizen()) {
				cbIDs.getItems().add(voter.getId());
			}
		}

		ComboBox<String> cbPartys = new ComboBox<String>();
		for (ElectionUIListenable electionUIListenable : listners) {
			for (Party party : electionUIListenable.viewAsksGetPartys()) {
				cbPartys.getItems().add(party.getName());
			}
		}

		BackButton btnBack = new BackButton(bgPane, listners);

		Button btnAdd = new Button("ADD");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ElectionUIListenable electionUIListenable : listners) {
					electionUIListenable.setRepIsPressed((String) cbIDs.getValue(), (String) cbPartys.getValue());
				}
				cbIDs.setValue(null);
				cbPartys.setValue(null);
			}
		});

		GridPane gpBox = new GridPane();

		gpBox.add(lblID, 0, 1);
		gpBox.add(cbIDs, 1, 1);

		gpBox.add(cbPartys, 2, 1);

		gpBox.add(btnAdd, 2, 2);
		gpBox.add(btnBack, 0, 2);

		gpBox.setVgap(20);
		gpBox.setHgap(10);
		gpBox.setAlignment(Pos.CENTER);

		addViewPane.getChildren().add(gpBox);
		addViewPane.setAlignment(Pos.TOP_CENTER);
	}

	public VBox getPane() {
		return this.addViewPane;
	}
}

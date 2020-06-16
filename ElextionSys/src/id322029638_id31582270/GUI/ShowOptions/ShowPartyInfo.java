package id322029638_id31582270.GUI.ShowOptions;

import java.util.ArrayList;

import id322029638_id31582270.GUI.Buttons.BackButton;
import id322029638_id31582270.GUI.ShowOptions.Tabels.ShowPartyInfoTableView;
import id322029638_id31582270.interfaces.ElectionUIListenable;
import id322029638_id31582270.logic.Party;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ShowPartyInfo extends Button {
	VBox addViewPane;
	ArrayList<ElectionUIListenable> listners;

	public ShowPartyInfo(ArrayList<ElectionUIListenable> listners, StackPane bgPane) {
		this.listners = listners;

		addViewPane = new VBox();

		GridPane gpContent = new GridPane();

		Label lblTitle = new Label("Show Party Info");
		lblTitle.setFont(new Font("Curve", 35));
		addViewPane.getChildren().add(lblTitle);

		ShowPartyInfoTableView<Party> tvBoxes = new ShowPartyInfoTableView<Party>(
				FXCollections.observableList(listners.get(0).viewAsksGetPartys()));

		BackButton btnBack = new BackButton(bgPane, listners);

		gpContent.add(tvBoxes, 0, 0, 3, 3);
		gpContent.add(btnBack, 0, 3);

		gpContent.setVgap(10);
		gpContent.setHgap(10);
		gpContent.setAlignment(Pos.CENTER);

		addViewPane.getChildren().add(gpContent);
		addViewPane.setAlignment(Pos.CENTER);
	}

	public VBox getPane() {
		return this.addViewPane;
	}
}

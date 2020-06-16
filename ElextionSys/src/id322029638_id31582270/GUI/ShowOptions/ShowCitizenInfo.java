package id322029638_id31582270.GUI.ShowOptions;

import java.util.ArrayList;

import id322029638_id31582270.GUI.Buttons.BackButton;
import id322029638_id31582270.GUI.ShowOptions.Tabels.ShowCitizenInfoTableView;
import id322029638_id31582270.interfaces.ElectionUIListenable;
import id322029638_id31582270.population.Voter;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ShowCitizenInfo extends Button {
	VBox addViewPane;
	ArrayList<ElectionUIListenable> listners;

	public ShowCitizenInfo(ArrayList<ElectionUIListenable> listners, StackPane bgPane) {
		this.listners = listners;

		addViewPane = new VBox();

		GridPane gpContent = new GridPane();

		Label lblTitle = new Label("Show Citizen Info");
		Label lblFilter = new Label("Filter");

		lblTitle.setFont(new Font("Curve", 35));
		addViewPane.getChildren().add(lblTitle);

		ShowCitizenInfoTableView<Voter> tvVoters = new ShowCitizenInfoTableView<Voter>(
				FXCollections.observableList(listners.get(0).viewAsksGetCitizen()));

		ComboBox<String> cbTableViewOptions = new ComboBox<String>();
		cbTableViewOptions.getItems().addAll("All options", "Voter Options", "Infected options", "Solider Options",
				"Rep options");
		cbTableViewOptions.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String opt = cbTableViewOptions.getValue();
				switch (opt) {
				case "All options":
					tvVoters.setAllTableView();
					break;
				case "Voter Options":
					tvVoters.setVoterTableView();
					break;
				case "Infected options":
					tvVoters.setInfectedTableView();
					break;
				case "Solider Options":
					tvVoters.setSoliderTableView();
					break;

				default:
					break;
				}
			}
		});

		BackButton btnBack = new BackButton(bgPane, listners);

		gpContent.add(tvVoters, 0, 0, 4, 3);
		gpContent.add(lblFilter, 2, 3);
		gpContent.add(cbTableViewOptions, 3, 3);
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

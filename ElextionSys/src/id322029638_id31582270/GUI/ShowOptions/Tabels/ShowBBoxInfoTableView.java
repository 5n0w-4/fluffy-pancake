package id322029638_id31582270.GUI.ShowOptions.Tabels;

import id322029638_id31582270.interfaces.RepresentativeMarker;
import id322029638_id31582270.logic.BBox;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowBBoxInfoTableView<T extends BBox<?>> extends TableView<T> {
	TableColumn<T, String> adress;
	TableColumn<T, String> typeOfBox;
	TableColumn<T, Integer> id;
	TableColumn<T, Double> precentageOfVotes;

	public ShowBBoxInfoTableView(ObservableList<T> items) {
		super(items);
		adress = new TableColumn<T, String>("Adress");
		typeOfBox = new TableColumn<T, String>("Type");
		id = new TableColumn<T, Integer>("ID");
		precentageOfVotes = new TableColumn<T, Double>("% of Votes");

		adress.setCellValueFactory(new PropertyValueFactory<T, String>("adress"));
		precentageOfVotes.setCellValueFactory(new PropertyValueFactory<T, Double>("percentageOfVotes"));
		id.setCellValueFactory(new PropertyValueFactory<T, Integer>("thisId"));

		typeOfBox.setCellValueFactory(cellData -> {
			T v = cellData.getValue();
			String val = v.getTypeOfThisBox().getSimpleName();
			return new ReadOnlyStringWrapper(val);

		});

		this.getColumns().setAll(adress, id, precentageOfVotes, typeOfBox);
	}

}

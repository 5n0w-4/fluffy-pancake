package id322029638_id31582270.GUI.ShowOptions.Tabels;

import java.util.ArrayList;

import id322029638_id31582270.logic.BBox;
import id322029638_id31582270.logic.ElectionResult;
import id322029638_id31582270.logic.Elections;
import id322029638_id31582270.logic.Party;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowResultInfoTableView<T extends BBox<?>> extends TableView<T> {
	TableColumn<T, String> adress;
	TableColumn<T, String> typeOfBox;
	TableColumn<T, Integer> id;
	TableColumn<T, Double> precentageOfVotes;
	ObservableList<T> items;

	public ShowResultInfoTableView(ObservableList<T> items) {
		super(items);
		this.items = items;

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
		
		this.setRowFactory(tr -> new TableRow<T>() {
			TableView votes;
			
			{
				this.selectedProperty().addListener((observable,wasSelected,newBool) -> {
					if (newBool) {
						votes = createVotesSubTable(this.getItem());
						this.getChildren().add(votes);
					}else {
						this.getChildren().remove(votes);
					}
					this.requestLayout();
				});
			}
			
			@Override
			protected double computePrefHeight(double width) {
				if (isSelected()) {
					return super.computePrefHeight(width)+votes.prefHeight(100);
				}
				return super.computePrefHeight(width);
			}
			
			@Override
			protected void layoutChildren() {
				super.layoutChildren();
				if(isSelected()) {
					   double width = getWidth();
	                    double paneHeight = votes.prefHeight(width);
	                    votes.resizeRelocate(0, getHeight()-paneHeight, width, paneHeight);
				}
			}
		});

		this.getColumns().setAll(adress, id, precentageOfVotes, typeOfBox);
	}
	
	private TableView<ElectionResult> createVotesSubTable(BBox<?> box) {
		TableView<ElectionResult> votesTable = new TableView<ElectionResult>();	
		votesTable.setMaxHeight(50);
		
		TableColumn<ElectionResult, String> name = new TableColumn<ElectionResult, String>("Name");
		TableColumn<ElectionResult, Integer> votesToParty = new TableColumn<ElectionResult, Integer>("Votes");
		TableColumn<ElectionResult, Integer> totalVoters = new TableColumn<ElectionResult, Integer>("Total Voters");
		TableColumn<ElectionResult, Double> precentage = new TableColumn<ElectionResult, Double>("% of votes");
		
		
		precentage.setCellValueFactory(new PropertyValueFactory<ElectionResult , Double>("precentage"));
		votesToParty.setCellValueFactory(new PropertyValueFactory<ElectionResult , Integer>("gotVotes"));
		totalVoters.setCellValueFactory(new PropertyValueFactory<ElectionResult , Integer>("outOf"));

		votesTable.setItems(FXCollections.observableList(box.getElectionRes()));
		
		
		name.setCellValueFactory(cellData -> {
			ElectionResult v = cellData.getValue();
			String val = v.getParty().getName();
			return new ReadOnlyStringWrapper(val);

		});



	
		votesTable.getColumns().setAll(name,votesToParty,totalVoters,precentage);

		
		return votesTable;
	}

}

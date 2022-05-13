package gui.client;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import entity.Order;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EditTimeCell extends TableCell<Order, LocalTime> {
	public static final String DefaultFormat = "HH:mm";
	private boolean enterflag=false;

	@SuppressWarnings("unused")
	private DateTimeFormatter formatter;
	@SuppressWarnings("unused")
	private ObjectProperty<LocalDateTime> dateTimeValue = new SimpleObjectProperty<>(LocalDateTime.now());
	private ObjectProperty<String> format = new SimpleObjectProperty<String>() {
		public void set(String newValue) {
			super.set(newValue);
			formatter = DateTimeFormatter.ofPattern(newValue);
		}
	};

	// Text field for editing
	private final TextField textField = new TextField();

	public EditTimeCell() {
		format.set(DefaultFormat);

        itemProperty().addListener((obx, oldItem, newItem) -> {
            if (newItem == null) {
                setText(null);
            } else {
                setText(newItem.toString());
            }
        });
		setGraphic(textField);
		setContentDisplay(ContentDisplay.TEXT_ONLY);

		
		textField.setOnAction(evt -> {
			//flag is because this commitEdit is triggered and also the loose focus is triggered
			//and result is 2 commitEdit instead of 1.
			//so flag is fix
			enterflag=true;
			commitEdit(LocalTime.parse(textField.getText()));
			enterflag=false;
		});
		
		
		textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			if (!enterflag && !isNowFocused) {
				commitEdit(LocalTime.parse(textField.getText()));
			}
		});
		textField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				textField.setText((getItem().toString()));
				cancelEdit();
				event.consume();
			} 
			
		});
	}

	// set the text of the text field and display the graphic
	@Override
	public void startEdit() {
		super.startEdit();
		textField.setText(getItem().toString());
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		textField.requestFocus();
	}

	// revert to text display
	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setContentDisplay(ContentDisplay.TEXT_ONLY);
	}

	// commits the edit. Update property if possible and revert to text display
	@Override
	public void commitEdit(LocalTime item) {

		// This block is necessary to support commit on losing focus, because the
		// baked-in mechanism
		// sets our editing state to false before we can intercept the loss of focus.
		// The default commitEdit(...) method simply bails if we are not editing...
		if (!isEditing() && !item.equals(getItem())) {
			TableView<Order> table = getTableView();
			if (table != null) {
				TableColumn<Order, LocalTime> column = getTableColumn();
				CellEditEvent<Order, LocalTime> event = new CellEditEvent<>(table,
						new TablePosition<Order, LocalTime>(table, getIndex(), column), TableColumn.editCommitEvent(),
						item);
				Event.fireEvent(column, event);
			}
		}

		super.commitEdit(item);
		setContentDisplay(ContentDisplay.TEXT_ONLY);
	}
}
package gui.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import entity.Order;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MyDateCell extends TableCell<Order, LocalDate> {
    
    private final DateTimeFormatter formatter ;
    private final DatePicker datePicker ;
    private String oldVal=null;
    
    public MyDateCell() {
    		
        formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT) ;
        datePicker = new DatePicker() ;
       
        //Saves previous date
        datePicker.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
        	oldVal = (datePicker.getEditor().getText());    	
        });
        
        datePicker.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
                datePicker.setValue(datePicker.getConverter().fromString(datePicker.getEditor().getText()));
                commitEdit(LocalDate.from(datePicker.getValue()));
            }
            if (event.getCode() == KeyCode.ESCAPE) {
            	datePicker.getEditor().setText(oldVal);
                cancelEdit();
            }
        });
        
        datePicker.setDayCellFactory(picker -> {
            DateCell cell = new DateCell();
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                datePicker.setValue(cell.getItem());
                if (event.getClickCount() == 2) {
                    datePicker.hide();
                    commitEdit(LocalDate.from(cell.getItem()));
                }
                event.consume();
            });
            cell.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    commitEdit(LocalDate.from(datePicker.getValue()));
                }
            });
            cell.addEventFilter(KeyEvent.KEY_PRESSED,(KeyEvent event) -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                	datePicker.getEditor().setText(oldVal.toString());
                    cancelEdit();
                }
            });
            return cell ;
        });
        
         
        //Update value when focus gets lost
        datePicker.focusedProperty().addListener((src, ov, nv) -> {
            if (!nv) {
                datePicker.getEditor().setText(datePicker.getConverter().toString(datePicker.getValue()));
                commitEdit(LocalDate.from(datePicker.getValue()));
            }
        });
		
        
        contentDisplayProperty().bind(Bindings.when(editingProperty())
                .then(ContentDisplay.GRAPHIC_ONLY)
                .otherwise(ContentDisplay.TEXT_ONLY));
    }
    
    @Override
    public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(formatter.format(date));
            setGraphic(datePicker);
        }
    }
    
    @Override
    public void startEdit() {
        super.startEdit();
        if (!isEmpty()) {
            datePicker.setValue(getItem().withYear(LocalDate.now().getYear()));
        }
    }

}
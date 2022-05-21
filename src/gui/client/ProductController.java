package gui.client;

import common.MyListener;
import entity.AbstractProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProductController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;
    
    @FXML
    private Label typeLabel;

    @FXML
    private ImageView img;
    
    private AbstractProduct product;
    private MyListener myListener;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(product);
    }

    public void setData(AbstractProduct product, MyListener myListener) {
        this.product = product;
        this.myListener = myListener;
        nameLabel.setText(product.getName());
        priceLable.setText(product.getPrice() + "$");
        img.setImage(new Image(getClass().getResourceAsStream(product.getImage())));
    }
}

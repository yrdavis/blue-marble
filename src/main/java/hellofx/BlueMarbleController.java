package hellofx;

import java.io.InputStream;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * @author Yvonne Davis
 * @version 1
 * This program retrieves a daily image of earth from NASA's EPIC site by date.
 */
public class BlueMarbleController {

	@FXML
	private RadioButton enhanced;

	@FXML
	private ImageView image;

	@FXML
	private RadioButton natural;

	@FXML
	private ToggleGroup Quality;

	@FXML
	private DatePicker datePicker;

	@FXML
	private Label text;

	@FXML
	private RadioButton blackAndWhite;

	private static BlueMarble blueMarble = new BlueMarble();

	public void initialize() {

		// System.out.println(" In the initializer");
		// enhanced option not visible
		// enhanced.setDisable(true);

		// Today's date in the date picker
		datePicker.setValue(LocalDate.now());

		// Convert date to string
		// blueMarble.setDate(String.format("%d-%02d-%02d",
		// datePicker.getValue().getYear(),
		// datePicker.getValue().getMonthValue(),
		// datePicker.getValue().getDayOfMonth()));
		// blueMarble.getImage();
		// Set to most recent image from NASA. Convert input stream to image
		// image.setImage(new Image(blueMarble.getImage()));
		// image.setImage(new Image(blueMarble.getImage()));

		// Set quality to natural
		natural.setSelected(true);
	}

	// convert date to string
	private String selectedDate() {

		String date;
		date = datePicker.getValue().getYear() + "-" + datePicker.getValue().getMonthValue() + "-"
				+ datePicker.getValue().getDayOfMonth();
		return date + "";

	}

	// Update date in date picker
	public void updateDate(ActionEvent event) {
		// boolean before = datePicker.getValue().isBefore(LocalDate.of(2015, 7, 1));
		// boolean before =
		// boolean future = datePicker.getValue(LocalDate.now());
		LocalDate today = LocalDate.now();
		// setDisable(before || future);
		// enhanced.setSelected(false);
		blueMarble.setDate(selectedDate());

		if (datePicker.getValue().isAfter(today)) {
			JOptionPane.showMessageDialog(null, "You selected a future date! Pick another date.");
		}

		if (datePicker.getValue().isBefore(LocalDate.of(2015, 7, 1))) {
			JOptionPane.showMessageDialog(null, "No image available efore 7/1/2015. Pick another date.");
		}

		if (datePicker.getValue().isAfter(LocalDate.of(2018, 6, 30))) {
			enhanced.setDisable(true);

		} else {
			enhanced.setDisable(false);
		}
		// natural.setSelected(false);
		image.setImage(new Image(blueMarble.getImage()));

	}


	@FXML
	void naturalSelected(ActionEvent event) {
		blueMarble.setDate(selectedDate());
		if (Quality.getSelectedToggle().equals(natural)) {
			image.setImage(new Image(blueMarble.getImage()));
		} else {
			updateDate(event);
		}
	}

	@FXML
	void enhancedSelected(ActionEvent event) {
		blueMarble.setDate(selectedDate());
		if (Quality.getSelectedToggle().equals(enhanced)) {
			blueMarble.setEnhanced(true);
			image.setImage(new Image(blueMarble.getImage()));
		} else {
			updateDate(event);
		}
	}

	@FXML
	void convertToBlackAndWhiteImage(ActionEvent event) {

		blueMarble.setDate(selectedDate());
		// image.setImage(new Image(blueMarble.getImage()));
		if (Quality.getSelectedToggle().equals(blackAndWhite)) {
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setSaturation(-1);
			image.setEffect(colorAdjust);
		} else {
			image.setEffect(null);
		}
	}

}

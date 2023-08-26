package edu.poly.shopbangiay.raven.datechooser.render;

import edu.poly.shopbangiay.raven.datechooser.DateBetween;
import edu.poly.shopbangiay.raven.datechooser.DateChooser;

import java.util.Date;

public interface DateChooserRender {
    public String renderLabelCurrentDate(DateChooser dateChooser, Date date);

    public String renderTextFieldDate(DateChooser dateChooser, Date date);

    public String renderTextFieldDateBetween(DateChooser dateChooser, DateBetween dateBetween);
}

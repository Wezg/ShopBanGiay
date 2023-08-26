package edu.poly.shopbangiay.raven.datechooser.listener;

import edu.poly.shopbangiay.raven.datechooser.DateBetween;

import java.util.Date;

public interface DateChooserListener {
    public void dateChanged(Date date, DateChooserAction action);

    public void dateBetweenChanged(DateBetween date, DateChooserAction action);
}

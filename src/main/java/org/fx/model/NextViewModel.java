package org.fx.model;

import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlRootElement(name = "next")
public class NextViewModel {
    private final StringProperty text;

    public NextViewModel() {
        text = new SimpleStringProperty();
    }

    public NextViewModel(final String text) {
        this.text = new SimpleStringProperty(text);
    }

    public StringProperty getTextProperty() {
        return text;
    }

    public String getText() {
        return text.get();
    }

    public void setText(final String text) {
        this.text.set(text);
    }
}

package edu.sandiego.comp305;

import java.util.List;

public interface Event {
    String getDescription();
    List<EventChoice> getChoices();
}

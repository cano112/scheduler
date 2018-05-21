package pl.edu.agh.wiet.studiesplanner.parser.exceptions;

import pl.edu.agh.wiet.studiesplanner.model.parser.DocumentLink;

public class SheetParseException extends RuntimeException {

    private final DocumentLink link;

    public SheetParseException(DocumentLink link) {
        this.link = link;
    }

    public SheetParseException(String message, DocumentLink link) {
        super(message);
        this.link = link;
    }

    public SheetParseException(String message, Throwable cause, DocumentLink link) {
        super(message, cause);
        this.link = link;
    }

    public SheetParseException(Throwable cause, DocumentLink link) {
        super(cause);
        this.link = link;
    }

    public DocumentLink getLink() {
        return link;
    }
}

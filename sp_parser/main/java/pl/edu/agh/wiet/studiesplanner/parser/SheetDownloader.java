import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface SheetDownloader {
    public GoogleSheetDownloader download() throws GeneralSecurityException, IOException;
    public List<List<Object>> getValues();
}

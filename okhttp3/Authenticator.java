package okhttp3;

import java.io.IOException;
import javax.annotation.Nullable;

public interface Authenticator {
    public static final Authenticator NONE = new 1();

    @Nullable
    Request authenticate(Route route, Response response) throws IOException;
}

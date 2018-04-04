package okhttp3.internal.cache;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import io.fabric.sdk.android.services.network.HttpRequest;
import javax.annotation.Nullable;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.StatusLine;

public final class CacheStrategy {
    @Nullable
    public final Response cacheResponse;
    @Nullable
    public final Request networkRequest;

    CacheStrategy(Request networkRequest, Response cacheResponse) {
        this.networkRequest = networkRequest;
        this.cacheResponse = cacheResponse;
    }

    public static boolean isCacheable(Response response, Request request) {
        switch (response.code()) {
            case Callback.DEFAULT_DRAG_ANIMATION_DURATION /*200*/:
            case 203:
            case 204:
            case 300:
            case 301:
            case StatusLine.HTTP_PERM_REDIRECT /*308*/:
            case 404:
            case 405:
            case 410:
            case 414:
            case 501:
                break;
            case 302:
            case StatusLine.HTTP_TEMP_REDIRECT /*307*/:
                if (response.header(HttpRequest.HEADER_EXPIRES) == null && response.cacheControl().maxAgeSeconds() == -1 && !response.cacheControl().isPublic() && !response.cacheControl().isPrivate()) {
                    return false;
                }
            default:
                return false;
        }
        return (response.cacheControl().noStore() || request.cacheControl().noStore()) ? false : true;
    }
}

package com.tado.android.rest.mock;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bJ\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\bH\u0002¨\u0006\f"}, d2 = {"Lcom/tado/android/rest/mock/TemplateMatcher;", "", "()V", "getTemplateForRequest", "Lcom/tado/android/rest/mock/MockDataPair;", "request", "Lokhttp3/Request;", "templates", "", "pathSegmentsToString", "", "pathSegments", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: TemplateMatcher.kt */
public final class TemplateMatcher {
    public static final TemplateMatcher INSTANCE = new TemplateMatcher();

    private TemplateMatcher() {
    }

    @Nullable
    public final MockDataPair getTemplateForRequest(@NotNull Request request, @NotNull List<MockDataPair> templates) {
        MockDataPair mockDataPair;
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(templates, "templates");
        for (MockDataPair next : templates) {
            int i;
            boolean z;
            MockDataPair it = next;
            if (Intrinsics.areEqual(it.getRequest().getMethod(), request.method())) {
                String str = (String) StringsKt__StringsKt.split$default((CharSequence) it.getRequest().getUrl(), new String[]{"?"}, false, 0, 6, null).get(0);
                TemplateMatcher templateMatcher = INSTANCE;
                List pathSegments = request.url().pathSegments();
                Intrinsics.checkExpressionValueIsNotNull(pathSegments, "request.url().pathSegments()");
                if (Intrinsics.areEqual(str, templateMatcher.pathSegmentsToString(pathSegments))) {
                    Iterable<String> $receiver$iv = StringsKt__StringsKt.split$default((CharSequence) StringsKt__StringsKt.split$default((CharSequence) it.getRequest().getUrl(), new String[]{"?"}, false, 0, 6, null).get(1), new String[]{"&"}, false, 0, 6, null);
                    if (($receiver$iv instanceof Collection) && ((Collection) $receiver$iv).isEmpty()) {
                        i = 1;
                    } else {
                        for (String it2 : $receiver$iv) {
                            str = request.url().query();
                            if (str != null) {
                                List split$default = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{"&"}, false, 0, 6, null);
                                if (split$default != null && split$default.contains(it2)) {
                                    i = 1;
                                    continue;
                                    if (i == 0) {
                                        z = false;
                                        break;
                                    }
                                }
                            }
                            z = false;
                            continue;
                            if (i == 0) {
                                z = false;
                                break;
                            }
                        }
                        i = 1;
                    }
                    if (i != 0) {
                        i = 1;
                        continue;
                        if (i != 0) {
                            mockDataPair = next;
                            break;
                        }
                    }
                }
            }
            z = false;
            continue;
            if (i != 0) {
                mockDataPair = next;
                break;
            }
        }
        mockDataPair = null;
        return mockDataPair;
    }

    private final String pathSegmentsToString(List<String> pathSegments) {
        StringBuilder out = new StringBuilder();
        int size = pathSegments.size();
        for (int i = 0; i < size; i++) {
            out.append('/');
            out.append((String) pathSegments.get(i));
        }
        String stringBuilder = out.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "out.toString()");
        return stringBuilder;
    }
}

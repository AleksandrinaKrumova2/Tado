package kotlin.io;

import com.tado.android.installation.CreateHomeContactDetailsActivity;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a(\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a(\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002¢\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002¢\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002¢\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006+"}, d2 = {"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, k = 5, mv = {1, 1, 9}, xi = 1, xs = "kotlin/io/FilesKt")
/* compiled from: Utils.kt */
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    public static final boolean copyRecursively(@org.jetbrains.annotations.NotNull java.io.File r1, @org.jetbrains.annotations.NotNull java.io.File r2, boolean r3, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.io.File, ? super java.io.IOException, ? extends kotlin.io.OnErrorAction> r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.io.FilesKt__UtilsKt.copyRecursively(java.io.File, java.io.File, boolean, kotlin.jvm.functions.Function2):boolean
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r2 = "$receiver";
        r0 = r16;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r2);
        r2 = "target";
        r0 = r17;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r2);
        r2 = "onError";
        r0 = r19;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r2);
        r2 = r16.exists();
        if (r2 != 0) goto L_0x003e;
    L_0x001e:
        r2 = new kotlin.io.NoSuchFileException;
        r4 = 0;
        r5 = "The source file doesn't exist.";
        r6 = 2;
        r7 = 0;
        r3 = r16;
        r2.<init>(r3, r4, r5, r6, r7);
        r0 = r19;
        r1 = r16;
        r2 = r0.invoke(r1, r2);
        r2 = (kotlin.io.OnErrorAction) r2;
        r5 = kotlin.io.OnErrorAction.TERMINATE;
        r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r5);
        r2 = r2 ^ 1;
        return r2;
        r5 = kotlin.io.FilesKt__FileTreeWalkKt.walkTopDown(r16);	 Catch:{ TerminateException -> 0x00e8 }
        r2 = new kotlin.io.FilesKt__UtilsKt$copyRecursively$2;	 Catch:{ TerminateException -> 0x00e8 }
        r0 = r19;	 Catch:{ TerminateException -> 0x00e8 }
        r2.<init>(r0);	 Catch:{ TerminateException -> 0x00e8 }
        r2 = (kotlin.jvm.functions.Function2) r2;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r5.onFail(r2);	 Catch:{ TerminateException -> 0x00e8 }
        r12 = r2.iterator();	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r12.hasNext();	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 == 0) goto L_0x011c;	 Catch:{ TerminateException -> 0x00e8 }
        r3 = r12.next();	 Catch:{ TerminateException -> 0x00e8 }
        r3 = (java.io.File) r3;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r3.exists();	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 != 0) goto L_0x0083;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = new kotlin.io.NoSuchFileException;	 Catch:{ TerminateException -> 0x00e8 }
        r4 = 0;	 Catch:{ TerminateException -> 0x00e8 }
        r5 = "The source file doesn't exist.";	 Catch:{ TerminateException -> 0x00e8 }
        r6 = 2;	 Catch:{ TerminateException -> 0x00e8 }
        r7 = 0;	 Catch:{ TerminateException -> 0x00e8 }
        r2.<init>(r3, r4, r5, r6, r7);	 Catch:{ TerminateException -> 0x00e8 }
        r0 = r19;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r0.invoke(r3, r2);	 Catch:{ TerminateException -> 0x00e8 }
        r2 = (kotlin.io.OnErrorAction) r2;	 Catch:{ TerminateException -> 0x00e8 }
        r5 = kotlin.io.OnErrorAction.TERMINATE;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r5);	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 == 0) goto L_0x0054;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = 0;	 Catch:{ TerminateException -> 0x00e8 }
        goto L_0x003d;	 Catch:{ TerminateException -> 0x00e8 }
        r0 = r16;	 Catch:{ TerminateException -> 0x00e8 }
        r10 = toRelativeString(r3, r0);	 Catch:{ TerminateException -> 0x00e8 }
        r4 = new java.io.File;	 Catch:{ TerminateException -> 0x00e8 }
        r0 = r17;	 Catch:{ TerminateException -> 0x00e8 }
        r4.<init>(r0, r10);	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r4.exists();	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 == 0) goto L_0x00dd;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r3.isDirectory();	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 == 0) goto L_0x00a2;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r4.isDirectory();	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 != 0) goto L_0x00dd;	 Catch:{ TerminateException -> 0x00e8 }
        if (r18 != 0) goto L_0x00c2;	 Catch:{ TerminateException -> 0x00e8 }
        r11 = 1;	 Catch:{ TerminateException -> 0x00e8 }
        if (r11 == 0) goto L_0x00dd;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = new kotlin.io.FileAlreadyExistsException;	 Catch:{ TerminateException -> 0x00e8 }
        r5 = "The destination file already exists.";	 Catch:{ TerminateException -> 0x00e8 }
        r2.<init>(r3, r4, r5);	 Catch:{ TerminateException -> 0x00e8 }
        r0 = r19;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r0.invoke(r4, r2);	 Catch:{ TerminateException -> 0x00e8 }
        r2 = (kotlin.io.OnErrorAction) r2;	 Catch:{ TerminateException -> 0x00e8 }
        r5 = kotlin.io.OnErrorAction.TERMINATE;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r5);	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 == 0) goto L_0x0054;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = 0;	 Catch:{ TerminateException -> 0x00e8 }
        goto L_0x003d;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r4.isDirectory();	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 == 0) goto L_0x00d3;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = deleteRecursively(r4);	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 != 0) goto L_0x00d1;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = 1;	 Catch:{ TerminateException -> 0x00e8 }
        r11 = r2;	 Catch:{ TerminateException -> 0x00e8 }
        goto L_0x00a5;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = 0;	 Catch:{ TerminateException -> 0x00e8 }
        goto L_0x00cf;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r4.delete();	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 != 0) goto L_0x00db;	 Catch:{ TerminateException -> 0x00e8 }
        r11 = 1;	 Catch:{ TerminateException -> 0x00e8 }
        goto L_0x00a5;	 Catch:{ TerminateException -> 0x00e8 }
        r11 = 0;	 Catch:{ TerminateException -> 0x00e8 }
        goto L_0x00a5;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r3.isDirectory();	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 == 0) goto L_0x00ec;	 Catch:{ TerminateException -> 0x00e8 }
        r4.mkdirs();	 Catch:{ TerminateException -> 0x00e8 }
        goto L_0x0054;	 Catch:{ TerminateException -> 0x00e8 }
    L_0x00e8:
        r9 = move-exception;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = 0;	 Catch:{ TerminateException -> 0x00e8 }
        goto L_0x003d;	 Catch:{ TerminateException -> 0x00e8 }
        r6 = 0;	 Catch:{ TerminateException -> 0x00e8 }
        r7 = 4;	 Catch:{ TerminateException -> 0x00e8 }
        r8 = 0;	 Catch:{ TerminateException -> 0x00e8 }
        r5 = r18;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = copyTo$default(r3, r4, r5, r6, r7, r8);	 Catch:{ TerminateException -> 0x00e8 }
        r6 = r2.length();	 Catch:{ TerminateException -> 0x00e8 }
        r14 = r3.length();	 Catch:{ TerminateException -> 0x00e8 }
        r2 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1));	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 == 0) goto L_0x0054;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = new java.io.IOException;	 Catch:{ TerminateException -> 0x00e8 }
        r5 = "Source file wasn't copied completely, length of destination file differs.";	 Catch:{ TerminateException -> 0x00e8 }
        r2.<init>(r5);	 Catch:{ TerminateException -> 0x00e8 }
        r0 = r19;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = r0.invoke(r3, r2);	 Catch:{ TerminateException -> 0x00e8 }
        r2 = (kotlin.io.OnErrorAction) r2;	 Catch:{ TerminateException -> 0x00e8 }
        r5 = kotlin.io.OnErrorAction.TERMINATE;	 Catch:{ TerminateException -> 0x00e8 }
        r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r5);	 Catch:{ TerminateException -> 0x00e8 }
        if (r2 == 0) goto L_0x0054;
        r2 = 0;
        goto L_0x003d;
        r2 = 1;
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__UtilsKt.copyRecursively(java.io.File, java.io.File, boolean, kotlin.jvm.functions.Function2):boolean");
    }

    @NotNull
    public static /* bridge */ /* synthetic */ File createTempDir$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        return createTempDir(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (File) null : file);
    }

    @NotNull
    public static final File createTempDir(@NotNull String prefix, @Nullable String suffix, @Nullable File directory) {
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        File dir = File.createTempFile(prefix, suffix, directory);
        dir.delete();
        if (dir.mkdir()) {
            Intrinsics.checkExpressionValueIsNotNull(dir, "dir");
            return dir;
        }
        throw new IOException("Unable to create temporary directory " + dir + '.');
    }

    @NotNull
    public static /* bridge */ /* synthetic */ File createTempFile$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        return createTempFile(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (File) null : file);
    }

    @NotNull
    public static final File createTempFile(@NotNull String prefix, @Nullable String suffix, @Nullable File directory) {
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        File createTempFile = File.createTempFile(prefix, suffix, directory);
        Intrinsics.checkExpressionValueIsNotNull(createTempFile, "File.createTempFile(prefix, suffix, directory)");
        return createTempFile;
    }

    @NotNull
    public static final String getExtension(@NotNull File $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        String name = $receiver.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, CreateHomeContactDetailsActivity.INTENT_NAME);
        return StringsKt__StringsKt.substringAfterLast(name, '.', "");
    }

    @NotNull
    public static final String getInvariantSeparatorsPath(@NotNull File $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (File.separatorChar != '/') {
            String path = $receiver.getPath();
            Intrinsics.checkExpressionValueIsNotNull(path, "path");
            return StringsKt__StringsJVMKt.replace$default(path, File.separatorChar, '/', false, 4, null);
        }
        path = $receiver.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        return path;
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull File $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        String name = $receiver.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, CreateHomeContactDetailsActivity.INTENT_NAME);
        return StringsKt__StringsKt.substringBeforeLast$default(name, ".", null, 2, null);
    }

    @NotNull
    public static final String toRelativeString(@NotNull File $receiver, @NotNull File base) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(base, "base");
        String toRelativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt($receiver, base);
        if (toRelativeStringOrNull$FilesKt__UtilsKt != null) {
            return toRelativeStringOrNull$FilesKt__UtilsKt;
        }
        throw new IllegalArgumentException("this and base files have different roots: " + $receiver + " and " + base + '.');
    }

    @NotNull
    public static final File relativeTo(@NotNull File $receiver, @NotNull File base) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(base, "base");
        return new File(toRelativeString($receiver, base));
    }

    @NotNull
    public static final File relativeToOrSelf(@NotNull File $receiver, @NotNull File base) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(base, "base");
        String p1 = toRelativeStringOrNull$FilesKt__UtilsKt($receiver, base);
        return p1 != null ? new File(p1) : $receiver;
    }

    @Nullable
    public static final File relativeToOrNull(@NotNull File $receiver, @NotNull File base) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(base, "base");
        String p1 = toRelativeStringOrNull$FilesKt__UtilsKt($receiver, base);
        return p1 != null ? new File(p1) : null;
    }

    private static final String toRelativeStringOrNull$FilesKt__UtilsKt(@NotNull File $receiver, File base) {
        FilePathComponents thisComponents = normalize$FilesKt__UtilsKt(FilesKt__FilePathComponentsKt.toComponents($receiver));
        FilePathComponents baseComponents = normalize$FilesKt__UtilsKt(FilesKt__FilePathComponentsKt.toComponents(base));
        if ((Intrinsics.areEqual(thisComponents.getRoot(), baseComponents.getRoot()) ^ 1) != 0) {
            return null;
        }
        int baseCount = baseComponents.getSize();
        int thisCount = thisComponents.getSize();
        int i = 0;
        int maxSameCount = Math.min(thisCount, baseCount);
        while (i < maxSameCount && Intrinsics.areEqual((File) thisComponents.getSegments().get(i), (File) baseComponents.getSegments().get(i))) {
            i++;
        }
        int sameCount = i;
        StringBuilder res = new StringBuilder();
        i = baseCount - 1;
        if (i >= sameCount) {
            while (!Intrinsics.areEqual(((File) baseComponents.getSegments().get(i)).getName(), "..")) {
                res.append("..");
                if (i != sameCount) {
                    res.append(File.separatorChar);
                }
                if (i != sameCount) {
                    i--;
                }
            }
            return null;
        }
        if (sameCount < thisCount) {
            if (sameCount < baseCount) {
                res.append(File.separatorChar);
            }
            Iterable drop = CollectionsKt___CollectionsKt.drop(thisComponents.getSegments(), sameCount);
            Appendable appendable = res;
            String str = File.separator;
            Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
            CollectionsKt___CollectionsKt.joinTo$default(drop, appendable, str, null, null, 0, null, null, 124, null);
        }
        return res.toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ File copyTo$default(File file, File file2, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 8192;
        }
        return copyTo(file, file2, z, i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.NotNull
    public static final java.io.File copyTo(@org.jetbrains.annotations.NotNull java.io.File r11, @org.jetbrains.annotations.NotNull java.io.File r12, boolean r13, int r14) {
        /*
        r9 = 1;
        r3 = 0;
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r1);
        r1 = "target";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r1);
        r1 = r11.exists();
        if (r1 != 0) goto L_0x0022;
    L_0x0014:
        r1 = new kotlin.io.NoSuchFileException;
        r4 = "The source file doesn't exist.";
        r5 = 2;
        r2 = r11;
        r6 = r3;
        r1.<init>(r2, r3, r4, r5, r6);
        r1 = (java.lang.Throwable) r1;
        throw r1;
    L_0x0022:
        r1 = r12.exists();
        if (r1 == 0) goto L_0x003f;
    L_0x0028:
        if (r13 != 0) goto L_0x0037;
    L_0x002a:
        if (r9 == 0) goto L_0x003f;
    L_0x002c:
        r1 = new kotlin.io.FileAlreadyExistsException;
        r2 = "The destination file already exists.";
        r1.<init>(r11, r12, r2);
        r1 = (java.lang.Throwable) r1;
        throw r1;
    L_0x0037:
        r1 = r12.delete();
        if (r1 == 0) goto L_0x002a;
    L_0x003d:
        r9 = 0;
        goto L_0x002a;
    L_0x003f:
        r1 = r11.isDirectory();
        if (r1 == 0) goto L_0x0056;
    L_0x0045:
        r1 = r12.mkdirs();
        if (r1 != 0) goto L_0x008a;
    L_0x004b:
        r1 = new kotlin.io.FileSystemException;
        r2 = "Failed to create target directory.";
        r1.<init>(r11, r12, r2);
        r1 = (java.lang.Throwable) r1;
        throw r1;
    L_0x0056:
        r1 = r12.getParentFile();
        if (r1 == 0) goto L_0x005f;
    L_0x005c:
        r1.mkdirs();
    L_0x005f:
        r1 = new java.io.FileInputStream;
        r1.<init>(r11);
        r1 = (java.io.Closeable) r1;
        r3 = (java.lang.Throwable) r3;
        r0 = r1;
        r0 = (java.io.FileInputStream) r0;	 Catch:{ Throwable -> 0x0095, all -> 0x009f }
        r7 = r0;
        r2 = new java.io.FileOutputStream;	 Catch:{ Throwable -> 0x0095, all -> 0x009f }
        r2.<init>(r12);	 Catch:{ Throwable -> 0x0095, all -> 0x009f }
        r2 = (java.io.Closeable) r2;	 Catch:{ Throwable -> 0x0095, all -> 0x009f }
        r4 = 0;
        r4 = (java.lang.Throwable) r4;	 Catch:{ Throwable -> 0x0095, all -> 0x009f }
        r0 = r2;
        r0 = (java.io.FileOutputStream) r0;	 Catch:{ Throwable -> 0x008b }
        r8 = r0;
        r7 = (java.io.InputStream) r7;	 Catch:{ Throwable -> 0x008b }
        r8 = (java.io.OutputStream) r8;	 Catch:{ Throwable -> 0x008b }
        kotlin.io.ByteStreamsKt.copyTo(r7, r8, r14);	 Catch:{ Throwable -> 0x008b }
        kotlin.io.CloseableKt.closeFinally(r2, r4);	 Catch:{ Throwable -> 0x0095, all -> 0x009f }
        kotlin.io.CloseableKt.closeFinally(r1, r3);
    L_0x008a:
        return r12;
    L_0x008b:
        r4 = move-exception;
        throw r4;	 Catch:{ all -> 0x008d }
    L_0x008d:
        r5 = move-exception;
        r10 = r5;
        r5 = r4;
        r4 = r10;
        kotlin.io.CloseableKt.closeFinally(r2, r5);	 Catch:{ Throwable -> 0x0095, all -> 0x009f }
        throw r4;	 Catch:{ Throwable -> 0x0095, all -> 0x009f }
    L_0x0095:
        r2 = move-exception;
        throw r2;	 Catch:{ all -> 0x0097 }
    L_0x0097:
        r3 = move-exception;
        r10 = r3;
        r3 = r2;
        r2 = r10;
    L_0x009b:
        kotlin.io.CloseableKt.closeFinally(r1, r3);
        throw r2;
    L_0x009f:
        r2 = move-exception;
        goto L_0x009b;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__UtilsKt.copyTo(java.io.File, java.io.File, boolean, int):java.io.File");
    }

    public static /* bridge */ /* synthetic */ boolean copyRecursively$default(File file, File file2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return copyRecursively(file, file2, z, (i & 4) != 0 ? FilesKt__UtilsKt$copyRecursively$1.INSTANCE : function2);
    }

    public static final boolean deleteRecursively(@NotNull File $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        boolean accumulator$iv = true;
        for (File file : FilesKt__FileTreeWalkKt.walkBottomUp($receiver)) {
            accumulator$iv = (file.delete() || !file.exists()) && accumulator$iv;
        }
        return accumulator$iv;
    }

    public static final boolean startsWith(@NotNull File $receiver, @NotNull File other) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        FilePathComponents components = FilesKt__FilePathComponentsKt.toComponents($receiver);
        FilePathComponents otherComponents = FilesKt__FilePathComponentsKt.toComponents(other);
        if ((Intrinsics.areEqual(components.getRoot(), otherComponents.getRoot()) ^ 1) == 0 && components.getSize() >= otherComponents.getSize()) {
            return components.getSegments().subList(0, otherComponents.getSize()).equals(otherComponents.getSegments());
        }
        return false;
    }

    public static final boolean startsWith(@NotNull File $receiver, @NotNull String other) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return startsWith($receiver, new File(other));
    }

    public static final boolean endsWith(@NotNull File $receiver, @NotNull File other) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        FilePathComponents components = FilesKt__FilePathComponentsKt.toComponents($receiver);
        FilePathComponents otherComponents = FilesKt__FilePathComponentsKt.toComponents(other);
        if (otherComponents.isRooted()) {
            return Intrinsics.areEqual($receiver, other);
        }
        int shift = components.getSize() - otherComponents.getSize();
        if (shift < 0) {
            return false;
        }
        return components.getSegments().subList(shift, components.getSize()).equals(otherComponents.getSegments());
    }

    public static final boolean endsWith(@NotNull File $receiver, @NotNull String other) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return endsWith($receiver, new File(other));
    }

    @NotNull
    public static final File normalize(@NotNull File $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        FilePathComponents $receiver2 = FilesKt__FilePathComponentsKt.toComponents($receiver);
        File root = $receiver2.getRoot();
        Iterable normalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt($receiver2.getSegments());
        String str = File.separator;
        Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
        return resolve(root, CollectionsKt___CollectionsKt.joinToString$default(normalize$FilesKt__UtilsKt, str, null, null, 0, null, null, 62, null));
    }

    private static final FilePathComponents normalize$FilesKt__UtilsKt(@NotNull FilePathComponents $receiver) {
        return new FilePathComponents($receiver.getRoot(), normalize$FilesKt__UtilsKt($receiver.getSegments()));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.util.List<java.io.File> normalize$FilesKt__UtilsKt(@org.jetbrains.annotations.NotNull java.util.List<? extends java.io.File> r5) {
        /*
        r1 = new java.util.ArrayList;
        r2 = r5.size();
        r1.<init>(r2);
        r1 = (java.util.List) r1;
        r3 = r5.iterator();
    L_0x000f:
        r2 = r3.hasNext();
        if (r2 == 0) goto L_0x0069;
    L_0x0015:
        r0 = r3.next();
        r0 = (java.io.File) r0;
        r2 = r0.getName();
        if (r2 != 0) goto L_0x0025;
    L_0x0021:
        r1.add(r0);
        goto L_0x000f;
    L_0x0025:
        r4 = r2.hashCode();
        switch(r4) {
            case 46: goto L_0x002d;
            case 1472: goto L_0x0037;
            default: goto L_0x002c;
        };
    L_0x002c:
        goto L_0x0021;
    L_0x002d:
        r4 = ".";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x0021;
    L_0x0036:
        goto L_0x000f;
    L_0x0037:
        r4 = "..";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x0021;
    L_0x0040:
        r2 = r1.isEmpty();
        if (r2 != 0) goto L_0x0065;
    L_0x0046:
        r2 = kotlin.collections.CollectionsKt___CollectionsKt.last(r1);
        r2 = (java.io.File) r2;
        r2 = r2.getName();
        r4 = "..";
        r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4);
        r2 = r2 ^ 1;
        if (r2 == 0) goto L_0x0065;
    L_0x005b:
        r2 = r1.size();
        r2 = r2 + -1;
        r1.remove(r2);
        goto L_0x000f;
    L_0x0065:
        r1.add(r0);
        goto L_0x000f;
    L_0x0069:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__UtilsKt.normalize$FilesKt__UtilsKt(java.util.List):java.util.List<java.io.File>");
    }

    @NotNull
    public static final File resolve(@NotNull File $receiver, @NotNull File relative) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(relative, "relative");
        if (FilesKt__FilePathComponentsKt.isRooted(relative)) {
            return relative;
        }
        String baseName = $receiver.toString();
        Intrinsics.checkExpressionValueIsNotNull(baseName, "baseName");
        File file = ((((CharSequence) baseName).length() == 0) || StringsKt__StringsKt.endsWith$default((CharSequence) baseName, File.separatorChar, false, 2, null)) ? new File(baseName + relative) : new File(baseName + File.separatorChar + relative);
        return file;
    }

    @NotNull
    public static final File resolve(@NotNull File $receiver, @NotNull String relative) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(relative, "relative");
        return resolve($receiver, new File(relative));
    }

    @NotNull
    public static final File resolveSibling(@NotNull File $receiver, @NotNull File relative) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(relative, "relative");
        FilePathComponents components = FilesKt__FilePathComponentsKt.toComponents($receiver);
        return resolve(resolve(components.getRoot(), components.getSize() == 0 ? new File("..") : components.subPath(0, components.getSize() - 1)), relative);
    }

    @NotNull
    public static final File resolveSibling(@NotNull File $receiver, @NotNull String relative) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(relative, "relative");
        return resolveSibling($receiver, new File(relative));
    }
}
